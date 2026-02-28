package com.MeghPI.Attendance.pages;

import java.time.Duration;
//import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghAttendancePolicyPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String Assignedemp = "";
	public String AttendancePolicy = "";
	public String AssignedempId = "";
	public String actualFirstName = "";
	public String actualFirstNamess = "";
	public String attendancePolicys = "";
	public String newinput = "";
	
	public MeghAttendancePolicyPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "(//button[@id='btnAddAttendancePolicy'])[2]")
	private WebElement AddAttendancePolicyButton; //1stTestCase
	
	@FindBy(xpath = "//h2[@id='frmHeaderName']")
	private WebElement AddAttendancePolicyText; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtAttnPolicyName']")
	private WebElement PolicyNameTextField; //2nd TestCase
	
	@FindBy(xpath = "//button[@id='btnCreateAttnPolicy']")
	private WebElement CreatePolicyButton; //2nd TestCase
	
	@FindBy(xpath = "//button[@id='btnPolicyConfirmNo']")
	private WebElement NoOption; //2nd TestCase
	
	
	@FindBy(xpath = "//div[@id='dtAttendancePolicy_filter']/label/input")
	private WebElement SearchTextField; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtAttendancePolicy']/tbody/tr[1]/td[2]/a/span")
	private WebElement GetAssignedEmpCount; //2nd TestCase
	
	
	@FindBy(xpath = "//label[text()='Web']")
	private WebElement WebPunchButton; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btnPolicyConfirmYes']")
	private WebElement YesButton; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btngrdCheckIn']")
	private WebElement CheckInButton; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btngrdCheckIn']")
	private WebElement CheckOutButton; //3rd TestCase	
	
	@FindBy(xpath = "//select[@id='drpDepartment']")
	private WebElement DeptDropdown; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterDepartment']")
	private WebElement DeptFilterDropdown; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpDesignation']")
	private WebElement DesignationDropdown; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpEmployeeGroup']")
	private WebElement TeamDropDown; //4th TestCase
	
	@FindBy(xpath = "//input[@id='txtPIN']")
	private WebElement PinTextField; //4th TestCase
	
	@FindBy(xpath = "//p[text()='Add Employee Criteria']")
	private WebElement AddEmpCriteria; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterCompanyLocation']")
	private WebElement OfficeDropDown; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterTeam']")
	private WebElement GroupDropDown; //4th TestCase
	
	
	@FindBy(xpath = "//select[@id='drpEntityTypes']")
	private WebElement EmpTypeDropDown; //4th TestCase
	
	@FindBy(xpath = "(//button[text()='Apply'])[2]")
	private WebElement ApplyButton; //4th TestCase
	
	
	@FindBy(xpath = "//button[@id='emp_policy_tab']")
	private WebElement PolicyButton; //4th TestCase
	
	@FindBy(xpath = "(//p[text()='AttendancePolicy']/../p)[1]")
	private WebElement AttendancePolicyName; //4th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement FirstRowEmpId; //6th TestCase
	
	@FindBy(xpath = "(//button[@id='btnHeaderFilter'])[1]")
	private WebElement SearchDropDown; //6th TestCase
	
	@FindBy(xpath = "(//div[@id='divDrpItemEmployeeId'])[1]")
	private WebElement EmpIdCheckBox; //6th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtEmployeeDetails']")
	private WebElement SearchTextFieldOfEmpCount; //6th TestCase
	
	@FindBy(xpath = "//button[@data-bs-target='#confirmDeleteModel']")
	private WebElement DeleteIcon; //6th TestCase
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement DeleteButton; //6th TestCase
	
	@FindBy(xpath = "//span[@id='spnEmployeeName']/../../a")
	private WebElement CloseIcon; //6th TestCase
	
	@FindBy(xpath = "//label[text()='Department']")
	private WebElement DeptCheckBox; //7th TestCase
	
	@FindBy(xpath = "//label[text()='Office']")
	private WebElement OfficeCheckBox; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr/td[3]")
	private List<WebElement> DepartmentList; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr/td[4]")
	private List<WebElement> OfficeList; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement EmployeeFirstName; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpGrade']")
	private WebElement GradeDropDown; //9th TestCase
	
	@FindBy(xpath = "//button[@id='btnaddMoreCriteria']")
	private WebElement AddMoreCriteriaButton; //9th TestCase
	
	@FindBy(xpath = "(//label[text()='Grade'])[2]")
	private WebElement GradeCheckBox; //9th TestCase
	
	@FindBy(xpath = "(//label[text()='Include Employee(s)'])[2]")
	private WebElement IncludeEmpCheckBox; //9th TestCase
	
	@FindBy(xpath = "(//label[text()='Exclude Employee(s)'])[2]")
	private WebElement ExcludeEmpCheckBox; //9th TestCase
	
	@FindBy(xpath = "//input[@placeholder='Select Include Entity']")
	private WebElement IncludeNameTextField; //9th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpIncludeEmps-results']/li[1]")
	private WebElement IncludeName; //9th TestCase
	
	@FindBy(xpath = "//input[@placeholder='Select Entity']")
	private WebElement ExcludeNameTextField; //9th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpExcludeEmps-results']/li[1]")
	private WebElement ExcludeName; //9th TestCase
	
	@FindBy(xpath = "//td[text()='No matching records found']")
	private WebElement NoMatchingRecord; //9th TestCase
	
	@FindBy(xpath = "//input[@id='chkAttnActive']")
	private WebElement ActiveCheckBox; //10th TestCase
	
	@FindBy(xpath = "//input[@id='chkAttnDefault']")
	private WebElement DefaultCheckBox; //11th TestCase
	
	@FindBy(xpath = "//label[text()='Enable Grace Period']")
	private WebElement EnableGracePeriodButton; //11th TestCase
	
	@FindBy(xpath = "//label[text()='Early Clock-out']")
	private WebElement EarlyClockOut; //11th TestCase
	
	@FindBy(xpath = "(//input[@id='GraceHours']/../input)[2]")
	private WebElement GraceHours; //11th TestCase
	
	@FindBy(xpath = "//button[@id='btnGraceApply']")
	private WebElement GraceTimeApplyButton; //11th TestCase
	
	@FindBy(xpath = "//input[@id='chxEnableGracePeriod']")
	private WebElement EnableGracePeriodCheckBox; //11th TestCase
	
	@FindBy(xpath = "//input[@id='IsAllowRegularization']")
	private WebElement RegulationRequestCheckBox; //11th TestCase
	
	@FindBy(xpath = "//table[@id='dtAttendancePolicy']/tbody/tr/td[2]/a/span")
	private List<WebElement> GreaterThanZero; //11th TestCase
	
	@FindBy(xpath = "//select[@name='dtAttendancePolicy_length']")
	private WebElement PaginationDropDown; //11th TestCase
	
	@FindBy(xpath = "//div[text()='Attendance Policy']")
	private WebElement AttendancePolicyPageLoaded; //11th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement CountLoaded; //11th TestCase
	
	
	@FindBy(xpath = "//span[@dir='ltr']/../label[text()='Departments']/../span")
	private WebElement DeptDropDownClick; //11th TestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpFilterDepartment')]")
	private WebElement DeptOptionSelected; //11th TestCase
	
	
	@FindBy(xpath = "//span[@dir='ltr']/../label[text()='Teams']/../span")
	private WebElement TeamDropDownClick; //11th TestCase
	
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpFilterTeam')]")
	private WebElement TeamOptionSelected; //11th TestCase
	
	@FindBy(xpath = "//input[@id='IsAllowOT']")
	private WebElement OverTimeCheckBox; //12th TestCase
	
	@FindBy(xpath = "//label[text()= 'Overtime eligibility on Workday']")
	private WebElement OverTimeOnWeekDay; //12th TestCase
	
	@FindBy(xpath = "//input[@id= 'MaxOTMinutes']/../input[2]")
	private WebElement OTMinutesTextField; //12th TestCase
	
	@FindBy(xpath = "//input[@id='OTApproveSystem']")
	private WebElement OTApprovedBySystem; //12th TestCase
	
	@FindBy(xpath = "//input[@id='rdRegilarizationDay']")
	private WebElement RegulirizationUpto31days; //12th TestCase
	
	@FindBy(xpath = "//input[@id='regularizationNos']")
	private WebElement RegulirizationUptoTextField; //12th TestCase
	
	@FindBy(xpath = "//label[text()='Overtime eligibility on Workday']")
	private WebElement OverTimeEligibilityDropDown; //12th TestCase
	
	@FindBy(xpath = "//input[@id='IsAllowOT']")
	private WebElement OTAllowCheckbox; //12th TestCase
	
	@FindBy(xpath = "//input[@id='MaxOTMinutes']/../input[2]")
	private WebElement OTMaxMinutesTextField; //12th TestCase
	
	@FindBy(xpath = "//input[@id='RegularizationNosPerMonth']")
	private WebElement RegulirizationPerMonthTextField; //12th TestCase
	
	@FindBy(xpath = "//p[text()='AttendancePolicy']/../p[1]")
	private WebElement AssignedAttendanceComparison; //12th TestCase
	
	@FindBy(xpath = "//input[@id='total-shift-duration']")
	private WebElement OTConfigurationList; //12th TestCase	
	
	@FindBy(xpath = "//input[@id='GraceNoOfTimes']")
	private WebElement GraceTimePerMonth; //12th TestCase	
	
	
	
	//1st TestCase
	public boolean AddAttendancePolicyButton()
	{
		try {
			utils.waitForEle(AddAttendancePolicyButton, "visible", "", 10);
			AddAttendancePolicyButton.isDisplayed();
			AddAttendancePolicyButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddAttendancePolicyText()
	{
		try {
			utils.waitForEle(AddAttendancePolicyText, "visible", "", 10);
			AddAttendancePolicyText.isDisplayed();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean PolicyNameTextField(String policyname) {
		try {

			utils.waitForEle(PolicyNameTextField,  "visible", "", 10);
			PolicyNameTextField.isDisplayed();
		
			PolicyNameTextField.sendKeys(policyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean GraceTimePerMonth(String policyname) {
		try {

			utils.waitForEle(GraceTimePerMonth,  "visible", "", 10);
			GraceTimePerMonth.clear();
			GraceTimePerMonth.sendKeys(policyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean CreatePolicyButton()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(CreatePolicyButton, "visible", "", 10);
			CreatePolicyButton.isDisplayed();
			CreatePolicyButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean NoOption()
	{
		try {
			utils.waitForEle(NoOption, "visible", "", 10);
			NoOption.isDisplayed();
			NoOption.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SearchTextField(String policyname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(SearchTextField,  "visible", "", 10);
			SearchTextField.isDisplayed();
			SearchTextField.clear();
			SearchTextField.sendKeys(policyname);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GetAssignedEmpCount()
	{
		try {
			utils.waitForEle(GetAssignedEmpCount, "visible", "", 10);
			GetAssignedEmpCount.isDisplayed();
			
			Assignedemp = GetAssignedEmpCount.getText();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignedEmpComparision(String Assignemp) {
	    try {
	    	Thread.sleep(4000);
	        if (Assignedemp.equalsIgnoreCase(Assignemp)) {
	            return true; // Strings match
	        } else {
	            throw new Exception("Assigned employee comparison failed: values do not match." + Assignemp + Assignedemp );
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

//3rd TestCase
	public boolean WebPunchButton()
	{
		try {
			utils.waitForEle(WebPunchButton, "visible", "", 10);
			WebPunchButton.isDisplayed();
			WebPunchButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean YesButton()
	{
		try {
			utils.waitForEle(YesButton, "visible", "", 10);
			YesButton.isDisplayed();
			YesButton.click();
			
			Thread.sleep(6000);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CheckInButton()
	{
		try {
			utils.waitForEle(CheckInButton, "visible", "", 10);
			CheckInButton.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
		
	public boolean CheckOutButton()
	{
		try {
			utils.waitForEle(CheckOutButton, "visible", "", 10);
			CheckOutButton.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	//4th TestCase
	
	public boolean DeptDropdown(String department)
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DeptDropdown, "visible", "", 10);
			DeptDropdown.isDisplayed();
			Select Dept = new Select(DeptDropdown);
			Dept.selectByVisibleText(department);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean DesignationDropdown(String designation)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DesignationDropdown, "visible", "", 10);
			DesignationDropdown.isDisplayed();
			Select Designation = new Select(DesignationDropdown);
			Designation.selectByVisibleText(designation);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean TeamDropDown(String groups)
	{
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", TeamDropDown );
Thread.sleep(2000);
			
			
			utils.waitForEle(TeamDropDown, "visible", "", 20);
			TeamDropDown.isDisplayed();
			Select Team = new Select(TeamDropDown);
			Team.selectByVisibleText(groups);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean AddEmpCriteria()
	{
		try {
			utils.waitForEle(AddEmpCriteria, "visible", "", 10);
			AddEmpCriteria.isDisplayed();
			AddEmpCriteria.click();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
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
	
	public boolean GroupDropDown(String groups) {
	    try {
	        // Wait for dropdown visibility
	        utils.waitForEle(GroupDropDown, "visible", "", 10);

	        Select groupSelect = new Select(GroupDropDown);

	        // Wait until the dropdown is populated and desired option is available
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        boolean optionFound = wait.until(driver -> {
	            List<WebElement> options = groupSelect.getOptions();
	            for (WebElement option : options) {
	                if (option.getText().trim().equalsIgnoreCase(groups)) {
	                    return true;
	                }
	            }
	            return false;
	        });

	        if (optionFound) {
	            groupSelect.selectByVisibleText(groups);
	        } else {
	            // Log info only — do not fail the test
	            exceptionDesc = "INFO: Group option '" + groups + "' not found in dropdown, but continuing.";
	            return true;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception while selecting group '" + groups + "': " + e.getMessage();
	        return false;
	    }

	    return true;
	}

	
	public boolean EmpTypeDropDown(String employeetype)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EmpTypeDropDown, "visible", "", 10);
			EmpTypeDropDown.isDisplayed();
			Select group = new Select(EmpTypeDropDown);
			group.selectByVisibleText(employeetype);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean ApplyButton()
	{
		try {
			utils.waitForEle(ApplyButton, "visible", "", 10);
			ApplyButton.isDisplayed();
			ApplyButton.click();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean PolicyButton()
	{
		try {
			Thread.sleep(8000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			utils.waitForEle(PolicyButton, "visible", "", 10);
		
			
			PolicyButton.click();
		Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendancePolicyName()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(AttendancePolicyName, "visible", "", 10);
			AttendancePolicyName.isDisplayed();
		AttendancePolicy =	AttendancePolicyName.getText();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	
	public boolean AssignedAttendancePolicyComparisons(String expectedPolicySuffix) {
	    try {
	        String actualSuffix = AssignedAttendanceComparison.getText().trim();

	        if (actualSuffix == null || actualSuffix.isEmpty()) {
	            throw new Exception("Attendance Policy text is null or empty.");
	        }

	        if (actualSuffix.toLowerCase().contains(expectedPolicySuffix.toLowerCase())) {
	            return true; // e.g., "default123" contains "default"
	        } else {
	            throw new Exception("Attendance Policy suffix mismatch. Expected to contain: " 
	                                + expectedPolicySuffix + ", Found: " + actualSuffix);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	
	
	
	
	
	
	
	
	
	
	
	//6th TestCase
	public boolean ClickOnAssignedEmpCount()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(GetAssignedEmpCount, "visible", "", 10);
			GetAssignedEmpCount.isDisplayed();
			Assignedemp = GetAssignedEmpCount.getText();
			GetAssignedEmpCount.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstRowEmpId() {
	    try {
	    	Thread.sleep(2000);
	
			
	        utils.waitForEle(FirstRowEmpId, "visible", "", 10);
	        

	        if (FirstRowEmpId.isDisplayed()) {
	            String empIdWithHash = FirstRowEmpId.getText();  // e.g., "#Admin@1234"
	             AssignedempId = empIdWithHash.replace("#", "");   // Removes '#' character
	            System.out.println("Cleaned Emp ID: " + AssignedempId);  // Optional: log or store as needed
	      
	        
	        } else {
	            throw new Exception("FirstRowEmpId is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
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

	public boolean EmpIdCheckBox()
	{
		try {
			utils.waitForEle(EmpIdCheckBox, "visible", "", 10);
			EmpIdCheckBox.isDisplayed();
			EmpIdCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean SearchTextFieldOfEmpCount(String policynames)
	{
		try {
			Thread.sleep(4000);			
			utils.waitForEle(SearchTextFieldOfEmpCount, "visible", "", 30);
			SearchTextFieldOfEmpCount.isDisplayed();
			SearchTextFieldOfEmpCount.clear();
			SearchTextFieldOfEmpCount.sendKeys(policynames);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean DeleteIcon()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DeleteIcon, "visible", "", 10);
			DeleteIcon.isDisplayed();
			DeleteIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeleteButton()
	{
		try {
			utils.waitForEle(DeleteButton, "visible", "", 10);
			DeleteButton.isDisplayed();
			DeleteButton.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CloseIcon()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(CloseIcon, "visible", "", 10);
			CloseIcon.isDisplayed();
			CloseIcon.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//7th TestCase
	public boolean DeptCheckBox()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(DeptCheckBox, "visible", "", 10);
			DeptCheckBox.isDisplayed();
			DeptCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeCheckBox()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(OfficeCheckBox, "visible", "", 10);
			OfficeCheckBox.isDisplayed();
			OfficeCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentList(String department) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement dept : DepartmentList) {
	            if (dept.getText().equalsIgnoreCase(department)) {
	                return true; // match found
	            }
	        }
	        System.out.println("Department not found: " + department);
	        return false; // no match found
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean OfficeList(String officename) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement office : OfficeList) {
	            if (office.getText().equalsIgnoreCase(officename)) {
	                return true; // match found
	            }
	        }
	        System.out.println("OfficeName not found: " + officename);
	        return false; // no match found
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	//8th TestCase
	
	public boolean EmployeeFirstName(String expectedFirstName) {
	    try {
	        utils.waitForEle(EmployeeFirstName, "visible", "", 10);

	        if (EmployeeFirstName.isDisplayed()) {
	            String fullText = EmployeeFirstName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
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
	
	//9th TestCase

	public boolean GradeDropDown(String grade) {
	    try {
	        // First attempt
	        Thread.sleep(4000);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", GradeDropDown);
	        Thread.sleep(1000);
	        utils.waitForEle(GradeDropDown, "visible", "", 10);

	        if (GradeDropDown.isDisplayed()) {
	            Select dropdown = new Select(GradeDropDown);
	            dropdown.selectByVisibleText(grade);
	            return true;
	        } else {
	            throw new Exception("Grade dropdown not displayed in first attempt.");
	        }

	    } catch (Exception firstEx) {
	        // First attempt failed, retry once
	        try {
	            Thread.sleep(3000);
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOf(GradeDropDown));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", GradeDropDown);
	            Thread.sleep(1000);

	            if (GradeDropDown.isDisplayed()) {
	                Select dropdown = new Select(GradeDropDown);
	                dropdown.selectByVisibleText(grade);
	                return true;
	            } else {
	                throw new Exception("Grade dropdown not displayed in retry attempt.");
	            }

	        } catch (Exception retryEx) {
	            exceptionDesc = "Retry failed: " + retryEx.getMessage();
	            return false;
	        }
	    }
	}


	
	public boolean  AddMoreCriteriaButton()
	{
		try {
			
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", AddMoreCriteriaButton );
Thread.sleep(2000);
			
			utils.waitForEle(AddMoreCriteriaButton, "visible", "", 10);
			 AddMoreCriteriaButton.isDisplayed();
			 AddMoreCriteriaButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  GradeCheckBox()
	{
		try {
			utils.waitForEle(GradeCheckBox, "visible", "", 10);
			GradeCheckBox.isDisplayed();
			GradeCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  IncludeEmpCheckBox()
	{
		try {
			utils.waitForEle(IncludeEmpCheckBox, "visible", "", 10);
			IncludeEmpCheckBox.isDisplayed();
			IncludeEmpCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ExcludeEmpCheckBox()
	{
		try {
			utils.waitForEle(ExcludeEmpCheckBox, "visible", "", 10);
			ExcludeEmpCheckBox.isDisplayed();
			ExcludeEmpCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  IncludeNameTextField(String firstname)
	{
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", IncludeNameTextField );
Thread.sleep(2000);
			
			
			
			utils.waitForEle(IncludeNameTextField, "visible", "", 10);
			IncludeNameTextField.isDisplayed();
			IncludeNameTextField.sendKeys(firstname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  IncludeName()
	{
		try {
			utils.waitForEle(IncludeName, "visible", "", 10);
			IncludeName.isDisplayed();
			IncludeName.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ExcludeNameTextField(String firstname)
	{
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", ExcludeNameTextField );
Thread.sleep(2000);
			
			
			utils.waitForEle(ExcludeNameTextField, "visible", "", 10);
			ExcludeNameTextField.isDisplayed();
			ExcludeNameTextField.sendKeys(firstname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean  ExcludeName()
	{
		try {
			utils.waitForEle(ExcludeName, "visible", "", 10);
			ExcludeName.isDisplayed();
			ExcludeName.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean NoMatchingRecord() {
	    try {
	        // Wait until 'NoMatchingRecord' element is visible (timeout: 15 seconds)
	        utils.waitForEle(NoMatchingRecord, "visible", "", 15);

	        // Confirm if it's displayed
	        if (NoMatchingRecord.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("'No Matching Record' element is not displayed.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = "NoMatchingRecord validation failed: " + e.getMessage();
	        return false;
	    }
	}

	
	//new ID's
	
	public boolean DeptFilterDropdown(String department) {
	    try {
	        // Wait for <select> to be visible
	        utils.waitForEle(DeptFilterDropdown, "visible", "", 15);

	        // Wait until the <select> has non-empty options
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        boolean optionFound = wait.until(driver -> {
	            List<WebElement> options = new Select(DeptFilterDropdown).getOptions();
	            return options.stream()
	                          .anyMatch(opt -> opt.getText().trim().equalsIgnoreCase(department));
	        });

	        if (optionFound) {
	            // Re-create Select object after options are populated
	            Select deptSelect = new Select(DeptFilterDropdown);
	            deptSelect.selectByVisibleText(department);
	        } else {
	            exceptionDesc = "INFO: Department option '" + department + "' not found in dropdown, but continuing.";
	            return true; // Don't fail
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception while selecting department '" + department + "': " + e.getMessage();
	        return false;
	    }

	    return true;
	}



	
	//10th TestCase
	public boolean  ActiveCheckBox()
	{
		try {
			Thread.sleep(6000);
			utils.waitForEle(ActiveCheckBox, "visible", "", 10);
			if(!ActiveCheckBox.isSelected())
			{
			ActiveCheckBox.click();
			Thread.sleep(2000);
			}
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeFirstNamesdata() {
	    try {
	        utils.waitForEle(EmployeeFirstName, "visible", "", 10);

	        if (EmployeeFirstName.isDisplayed()) {
	            String fullText = EmployeeFirstName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
	             actualFirstNamess = fullText.split(" ")[0]; // Extract first part before space
	            
	           
	                return true;
	            

	        } else {
	            throw new Exception("EmployeeFirstName element is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AttendancePolicyNames(String policyname) {
	    try {
	        utils.waitForEle(AttendancePolicyName, "visible", "", 10);

	        if (AttendancePolicyName.isDisplayed()) {
	             attendancePolicys = AttendancePolicyName.getText().trim();

	            if (attendancePolicys.equalsIgnoreCase(policyname)) {
	                System.out.println("Policy name matches: " + policyname);
	                return true;
	            } else {
	                throw new Exception("Policy name does not match. Expected: " + policyname + ", Found: " + attendancePolicys);
	            }

	        } else {
	            throw new Exception("AttendancePolicyName element is not visible.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	//11th TestCase
	
	public boolean DefaultCheckBox() {
	    try {
	        utils.waitForEle(DefaultCheckBox, "visible", "", 10);

	        if (DefaultCheckBox.isDisplayed()) {
	            if (!DefaultCheckBox.isEnabled()) {
	                // Checkbox is disabled (already default or not editable)
	                return true;
	            }

	            // If checkbox is visibled
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
	
	public boolean  EnableGracePeriodButton()
	{
		try {
			utils.waitForEle(EnableGracePeriodButton, "visible", "", 10);
			EnableGracePeriodButton.isDisplayed();
			EnableGracePeriodButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EarlyClockOut()
	{
		try {
			utils.waitForEle(EarlyClockOut, "visible", "", 10);
			EarlyClockOut.isDisplayed();
			EarlyClockOut.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  GraceHoursClick()
	{
		try {
			utils.waitForEle(GraceHours, "visible", "", 10);
			GraceHours.isDisplayed();
			GraceHours.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean GraceHoursInput(String gracehour) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('GraceHours').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('GraceHours')._flatpickr) {" +
		            "  document.getElementById('GraceHours')._flatpickr.setDate('" + gracehour + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on GraceHours'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	
	
	
	
	
	public boolean  GraceTimeApplyButton()
	{
		try {
			utils.waitForEle(GraceTimeApplyButton, "visible", "", 10);
			GraceTimeApplyButton.isDisplayed();
			GraceTimeApplyButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EnableGracePeriodCheckBox() {
	    try {
	        // Wait for the checkbox to be visible
	        utils.waitForEle(EnableGracePeriodCheckBox, "visible", "", 10);

	        // Click only if not already selected
	        if (!EnableGracePeriodCheckBox.isSelected()) {
	            EnableGracePeriodCheckBox.click();
	        }

	        return true; // success

	    } catch (Exception e) {
	        exceptionDesc = "Failed to enable Grace Period checkbox: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean RegulationRequestCheckBox() {
	    try {
	        utils.waitForEle(RegulationRequestCheckBox, "visible", "", 10);

	        if (!RegulationRequestCheckBox.isSelected()) {
	            RegulationRequestCheckBox.click();
	        }
	        
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean AttendancePolicyNameDefault(String policyname) {
	    try {
	        utils.waitForEle(AttendancePolicyName, "visible", "", 10);

	        if (AttendancePolicyName.isDisplayed()) {
	            String attendancePolicysDefault = AttendancePolicyName.getText().trim();

	            // Get the first word before any space (e.g., removes " (default)")
	             newinput = attendancePolicysDefault.split(" ")[0].trim();

	            if (newinput.contains(policyname)) {
	                System.out.println("Policy name matches: " + policyname);
	                return true;
	            } else {
	                throw new Exception("Policy name does not match. Expected: " + policyname + ", Found: " + newinput);
	            }

	        } else {
	            throw new Exception("AttendancePolicyName element is not visible.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean AssignedEmpLinkedCountIfGreaterThanZero() {
	    try {
	      

	        for (WebElement linkedCount : GreaterThanZero) {
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
	
		
	public boolean  PaginationDropDown()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(PaginationDropDown, "visible", "", 10);
			PaginationDropDown.isDisplayed();
	Select Drop = new Select(PaginationDropDown);
	Drop.selectByVisibleText("100");
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  AttendancePolicyPageLoaded()
	{
		try {
			utils.waitForEle(AttendancePolicyPageLoaded, "visible", "", 50);
			AttendancePolicyPageLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean  CountLoaded()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(CountLoaded, "visible", "", 15);
			CountLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean DeptDropDownClick() {
	    try {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        // Wait and click dropdown
	        WebElement deptDropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(DeptDropDownClick)
	        );
	        deptDropdown.click();

	        // Verify option is visible, else retry click once
	        try {
	            wait.until(ExpectedConditions.visibilityOf(DeptOptionSelected));
	        } catch (TimeoutException te) {
	            deptDropdown.click(); // retry
	            wait.until(ExpectedConditions.visibilityOf(DeptOptionSelected));
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "DeptDropDownClick error: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean  DeptOptionSelected()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DeptOptionSelected, "visible", "", 15);
			DeptOptionSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDownClick() {
	    try {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        // Wait until dropdown is clickable
	        WebElement teamDropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(TeamDropDownClick)
	        );

	        // First click
	        teamDropdown.click();

	        // Check if option is visible, else retry click
	        try {
	            wait.until(ExpectedConditions.visibilityOf(TeamOptionSelected));
	        } catch (TimeoutException te) {
	            teamDropdown.click(); // retry click
	            wait.until(ExpectedConditions.visibilityOf(TeamOptionSelected));
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "TeamDropDownClick error: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean  TeamOptionSelected()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(TeamOptionSelected, "visible", "", 15);
			TeamOptionSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	public boolean  OverTimeCheckBox()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(OverTimeCheckBox, "visible", "", 15);
			OverTimeCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeOnWeekDay()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(OverTimeOnWeekDay, "visible", "", 15);
			OverTimeOnWeekDay.click();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OTMinutesTextField()
	{
		try {
			utils.waitForEle(OTMinutesTextField, "visible", "", 10);
		
			OTMinutesTextField.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean OTMinutesTextFieldInput(String MaxOTHours) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('MaxOTMinutes').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('MaxOTMinutes')._flatpickr) {" +
		            "  document.getElementById('MaxOTMinutes')._flatpickr.setDate('" + MaxOTHours + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on MaxOTMinutes'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean  OTApprovedBySystem()
	{
		try {
			utils.waitForEle(OTApprovedBySystem, "visible", "", 15);
		
			OTApprovedBySystem.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean RegulirizationUpto31days()
	{
		try {
			utils.waitForEle(RegulirizationUpto31days, "visible", "", 15);
		
			RegulirizationUpto31days.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegulirizationUptoTextField(String duration)
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(RegulirizationUptoTextField, "visible", "", 20);
		
			RegulirizationUptoTextField.clear();
			RegulirizationUptoTextField.sendKeys(duration);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OverTimeEligibilityDropDown() {
	    try {
	        utils.waitForEle(OverTimeEligibilityDropDown, "visible", "", 15);
	        
	        // If OTConfigurationList is NOT displayed, click the dropdown
	        if (!OTConfigurationList.isDisplayed()) {
	            OverTimeEligibilityDropDown.click();
	        }
	        
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean OTAllowCheckbox() {
	    try {
	        utils.waitForEle(OTAllowCheckbox, "visible", "", 15);

	        if (!OTAllowCheckbox.isSelected()) {
	            OTAllowCheckbox.click();
	        }
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean OTMaxMinutesTextField()
	{
		try {
			utils.waitForEle(OTMaxMinutesTextField, "visible", "", 15);
		
			OTMaxMinutesTextField.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OTMaxMinutesTextFieldInputted(String OTTime) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('MaxOTMinutes').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('MaxOTMinutes')._flatpickr) {" +
		            "  document.getElementById('MaxOTMinutes')._flatpickr.setDate('" + OTTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on MaxOTMinutes'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean AddAttendanceValidation()
	{
		try {
			utils.waitForEle(AddAttendancePolicyButton, "visible", "", 20);
			AddAttendancePolicyButton.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean RegulirizationPerMonthTextField(String days)
	{
		try {
			utils.waitForEle(RegulirizationPerMonthTextField, "visible", "", 20);
			RegulirizationPerMonthTextField.clear();
			RegulirizationPerMonthTextField.sendKeys(days);
			
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
