package com.MeghPI.Attendance.pages;


//import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghHolidayPolicyPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
	
	
	
	
	public MeghHolidayPolicyPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[text()='Holidays']")
	private WebElement HolidaysButtonFromSidebar; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnAddHolidayPolicy'])[2]")
	private WebElement AddHolidayPolicyButton; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtPolicyName']")
	private WebElement HolidayPolicyNameTextField; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpYear']")
	private WebElement FinancialYear; //1stTestCase
	
	@FindBy(xpath = "//a[@id='btnNextSave']")
	private WebElement NextStepButton; //1stTestCase
	
	@FindBy(xpath = "//a[@id='btnHolidayPolicySave']")
	private WebElement CreatePolicySaveButton; //1stTestCase
	
	@FindBy(xpath = "//div[@id='v-pills-grade']/div/div/div[2]/div[1]/div/div/div/p[1]")
	private WebElement HolidayPolicyNameInEmp; //1stTestCase
	
	@FindBy(xpath = "//div[@id='dtHolidayPolicy_filter']/label/input")
	private WebElement HolidayPolicySearchTextField; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtHolidayPolicy']/tbody/tr[1]/td[1]/p/span")
	private WebElement HolidayPolicyFirstRecord; //2ndTestCase
	
	
	@FindBy(xpath = "//table[@id='dtHolidayPolicy']/tbody/tr[1]/td[4]/a/button/img")
	private WebElement HolidayPolicyEditIcon; //3rdTestCase
	
	
	@FindBy(xpath = "//a[@id='btnaddCustomHoliday']")
	private WebElement CreateCustomHoliday; //4thTestCase
	
	@FindBy(xpath = "//input[@id='txtCustomHolidayName']")
	private WebElement HolidayNameTextField; //4thTestCase
	
	@FindBy(xpath = "//input[@id='txtCustomHolidayDate']")
	private WebElement DateOfHoliday; //4thTestCase
	
	@FindBy(xpath = "//select[@id='drpCustomHolidayCountry']")
	private WebElement CountryDropDown; //4thTestCase
	
	@FindBy(xpath = "//select[@id='drpCustomHolidayState']")
	private WebElement StateDropDown; //4thTestCase
	
	@FindBy(xpath = "//button[@id='btnAddCustomHoliday']")
	private WebElement AddHolidayButton; //4thTestCase
	
	@FindBy(xpath = "//table[@id='tblHolidayDetailForCustomTab']/tbody/tr/td[1]")
	private List<WebElement> HolidayList; //4thTestCase
	
	@FindBy(xpath = "//div[@id='dtHolidayDetail_filter']/label/input")
	private WebElement HolidaySearchTextField; //5thTestCase
	
	@FindBy(xpath = "//table[@id='dtHolidayDetail']/tbody/tr[1]/td[2]")
	private WebElement HolidayResult; //5thTestCase
	
	@FindBy(xpath = "//table[@id='dtHolidayPolicy']/tbody/tr[1]/td[2]/a")
	private WebElement AssignedEmpCountLink; //7thTestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement FirstEmpRecordInAssignedEmpCountPage; //7thTestCase
	
	@FindBy(xpath = "//div[@id='dtEmployeeDetails_filter']/label/input")
	private WebElement SearchTextFieldInAssignedEmpCountPage; //7thTestCase
	
	@FindBy(xpath = "//table[@id='dtHolidayPolicy']/tbody/tr[1]/td[4]/button/img")
	private WebElement HolidayPolicyDeleteIcon; //8thTestCase
	
	@FindBy(xpath = "//input[@id='txtCustomHolidayDate']/../input[2]")
	private WebElement DateTextField; //8thTestCase
	
	
	
	
	public boolean  HolidaysButtonFromSidebar()
	{
		try {
			utils.waitForEle(HolidaysButtonFromSidebar, "visible", "", 10);
			HolidaysButtonFromSidebar.isDisplayed();
			HolidaysButtonFromSidebar.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddHolidayPolicyButton()
	{
		try {
			utils.waitForEle(AddHolidayPolicyButton, "visible", "", 10);
			AddHolidayPolicyButton.isDisplayed();
			AddHolidayPolicyButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HolidayPolicyNameTextField(String Holidaypolicyname)
	{
		try {
			Thread.sleep(8000);			
			utils.waitForEle(HolidayPolicyNameTextField, "visible", "", 10);
			HolidayPolicyNameTextField.isDisplayed();
			HolidayPolicyNameTextField.clear();
			HolidayPolicyNameTextField.sendKeys(Holidaypolicyname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean FinancialYear(String year)
	{
		try {
			Thread.sleep(2000);
		
			utils.waitForEle(FinancialYear, "visible", "", 10);
			FinancialYear.isDisplayed();
			Select dept = new Select(FinancialYear);
			dept.selectByVisibleText(year);
			Thread.sleep(4000);
			 utils.waitForEle(HolidayResult, "visible", "", 10);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  NextStepButton()
	{
		try {
			utils.waitForEle(NextStepButton, "visible", "", 10);
			NextStepButton.isDisplayed();
			NextStepButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  CreatePolicySaveButton()
	{
		try {
			utils.waitForEle(CreatePolicySaveButton, "visible", "", 10);
			CreatePolicySaveButton.isDisplayed();
			CreatePolicySaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HolidayPolicyNameInEmp(String policyname) {
	    try {
	    	Thread.sleep(6000);
	    	
	    HolidayPolicyName	= HolidayPolicyNameInEmp.getText();
	        if (HolidayPolicyName.equalsIgnoreCase(policyname)) {
	            return true; // Strings match
	        } else {
	            throw new Exception("Assigned Policy Name comparison failed: values do not match." + HolidayPolicyName + "=" + policyname );
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean HolidayPolicySearchTextField(String Holidaypolicyname)
	{
		try {
			Thread.sleep(8000);			
			utils.waitForEle(HolidayPolicySearchTextField, "visible", "", 10);
			HolidayPolicySearchTextField.isDisplayed();
			HolidayPolicySearchTextField.clear();
			HolidayPolicySearchTextField.sendKeys(Holidaypolicyname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean HolidayPolicyFirstRecord(String Holidaypolicyname) {
	    try {
	        Thread.sleep(4000); // Ideally replace with explicit wait
	        utils.waitForEle(HolidayPolicyFirstRecord, "visible", "", 10);

	        if (HolidayPolicyFirstRecord == null) {
	            throw new Exception("HolidayPolicyFirstRecord element is null.");
	        }

	        String actualText = HolidayPolicyFirstRecord.getText();
	        if (actualText.contains(Holidaypolicyname)) {
	            return true;
	        } else {
	            throw new Exception("Policy name not matched. Expected: '" + Holidaypolicyname + "', Actual: '" + actualText + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  HolidayPolicyEditIcon()
	{
		try {
			utils.waitForEle(HolidayPolicyEditIcon, "visible", "", 10);
			HolidayPolicyEditIcon.isDisplayed();
			HolidayPolicyEditIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	public boolean  CreateCustomHoliday()
	{
		try {
			utils.waitForEle(CreateCustomHoliday, "visible", "", 10);
			CreateCustomHoliday.isDisplayed();
			CreateCustomHoliday.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HolidayNameTextField(String Holidayname)
	{
		try {
			Thread.sleep(8000);			
			utils.waitForEle(HolidayNameTextField, "visible", "", 10);
			HolidayNameTextField.isDisplayed();
			HolidayNameTextField.clear();
			HolidayNameTextField.sendKeys(Holidayname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DateOfHoliday(String dateofholiday)
	{
		try {
			Thread.sleep(8000);			
			utils.waitForEle(DateOfHoliday, "visible", "", 10);
			DateOfHoliday.isDisplayed();
			DateOfHoliday.clear();
			DateOfHoliday.sendKeys(dateofholiday);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CountryDropDown()
	{
		try {
			Thread.sleep(2000);
		
			utils.waitForEle(CountryDropDown, "visible", "", 10);
			CountryDropDown.isDisplayed();
			Select dept = new Select(CountryDropDown);
			dept.selectByIndex(1);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean StateDropDown()
	{
		try {
			Thread.sleep(2000);
		
			utils.waitForEle(StateDropDown, "visible", "", 10);
			StateDropDown.isDisplayed();
			Select dept = new Select(StateDropDown);
			dept.selectByVisibleText("Gujarat");
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddHolidayButton()
	{
		try {
			utils.waitForEle(AddHolidayButton, "visible", "", 10);
			AddHolidayButton.isDisplayed();
			AddHolidayButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HolidayList(String holidayname) {
	    try {
	        if (HolidayList == null || HolidayList.isEmpty()) {
	            throw new Exception("Holiday list is empty or null.");
	        }

	        for (WebElement eachHoliday : HolidayList) {
	            String text = eachHoliday.getText();
	            if (text != null && text.contains(holidayname)) {
	                return true; // Found the matching holiday name
	            }
	        }

	        throw new Exception("Holiday name not found in the list: " + holidayname);
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	public boolean HolidaySearchTextField(String holidayname)
	{
		try {
			Thread.sleep(8000);			
			utils.waitForEle(HolidaySearchTextField, "visible", "", 10);
			HolidaySearchTextField.isDisplayed();
			HolidaySearchTextField.clear();
			HolidaySearchTextField.sendKeys(holidayname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HolidayResult(String Holidayname) {
	    try {
	        Thread.sleep(4000); // Ideally replace with explicit wait
	        utils.waitForEle(HolidayPolicyFirstRecord, "visible", "", 10);

	        if (HolidayResult == null) {
	            throw new Exception("HolidayPolicyFirstRecord element is null.");
	        }

	         actualholiday = HolidayResult.getText();
	        if (actualholiday.contains(Holidayname)) {
	            return true;
	        } else {
	            throw new Exception("Policy name not matched. Expected: '" + Holidayname + "', Actual: '" + actualholiday + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean DefaultHolidayPolicy(String DefaultText) {
	    try {
	        Thread.sleep(4000); // Ideally replace with explicit wait
	        utils.waitForEle(HolidayPolicyNameInEmp, "visible", "", 10);

	        if (HolidayPolicyNameInEmp == null) {
	            throw new Exception("HolidayPolicyNameInEmp element is null.");
	        }

	         Defaultholiday = HolidayPolicyNameInEmp.getText();
	        if (Defaultholiday.contains(DefaultText)) {
	            return true;
	        } else {
	            throw new Exception("Policy name not matched. Expected: '" + DefaultText + "', Actual: '" + Defaultholiday + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean  AssignedEmpCountLink()
	{
		try {
			utils.waitForEle(AssignedEmpCountLink, "visible", "", 15);
		
			AssignedEmpCountLink.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstEmpRecordInAssignedEmpCountPage() {
	    try {
	        // Wait until element is visible
	        utils.waitForEle(FirstEmpRecordInAssignedEmpCountPage, "visible", "", 30);

	        // Check if displayed
	        if (FirstEmpRecordInAssignedEmpCountPage.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("First employee record is not visible on Assigned Employee Count Page.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean SearchTextFieldInAssignedEmpCountPage(String EmpName)
	{
		try {
			Thread.sleep(4000);			
			utils.waitForEle(SearchTextFieldInAssignedEmpCountPage, "visible", "", 30);
		
			SearchTextFieldInAssignedEmpCountPage.clear();
			SearchTextFieldInAssignedEmpCountPage.sendKeys(EmpName);
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstEmpRecord(String empfirstname) {
	    try {
	        // Wait until element is visible
	    	Thread.sleep(4000);
	        utils.waitForEle(FirstEmpRecordInAssignedEmpCountPage, "visible", "", 30);

	        // Retrieve text from the element
	        String assignedEmp = FirstEmpRecordInAssignedEmpCountPage.getText();

	        if (assignedEmp != null && assignedEmp.contains(empfirstname)) {
	            return true;
	        } else {
	            throw new Exception("Mismatch or missing: Expected first employee name to contain '" 
	                + empfirstname + "', but found: '" + assignedEmp + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean  HolidayPolicyDeleteIcon()
	{
		try {
			utils.waitForEle(HolidayPolicyDeleteIcon, "visible", "", 15);
		
			HolidayPolicyDeleteIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean FirstEmpRecordComparison() {
	    try {
	        // Small wait before checking
	        Thread.sleep(4000);

	        // Wait for the element to be visible
	        utils.waitForEle(FirstEmpRecordInAssignedEmpCountPage, "visible", "", 30);

	        // Check if element is displayed
	        if (FirstEmpRecordInAssignedEmpCountPage.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("First employee record is not displayed even after being visible.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean HolidayResults() {
	    try {
	        Thread.sleep(4000); // Ideally replace with explicit wait
	        utils.waitForEle(HolidayResult, "visible", "", 10);

	            return true;
	        }
	    catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  DateTextField()
	{
		try {
			utils.waitForEle(DateTextField, "visible", "", 15);
		
			DateTextField.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DateInputted(String Date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('txtCustomHolidayDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('txtCustomHolidayDate')._flatpickr) {" +
		            "  document.getElementById('txtCustomHolidayDate')._flatpickr.setDate('" + Date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on txtCustomHolidayDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
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
