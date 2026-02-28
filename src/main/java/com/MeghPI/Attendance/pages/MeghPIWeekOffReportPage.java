package com.MeghPI.Attendance.pages;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghPIWeekOffReportPage {

	
	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	public MeghPIWeekOffReportPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Weekly-off']")
	private WebElement WeekOffReportButton; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//input[@aria-controls='dtEmployeeWeeklyOffReport']")
	private WebElement WeekOffReportSearchTextField; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "(//input[@id='chkdivDrpItemNewWeekOffDatedtEmployeeWeeklyOffReport'])[1]")
	private WebElement WeekOffReportDaySearchCheckBox; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "(//label[text()='Date'])[1]")
	private WebElement WeekOffReportDateSearchCheckBox; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//label[text()='Department']")
	private WebElement WeekOffReportDeptSearchCheckBox; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//label[text()='Designation']")
	private WebElement WeekOffReportDesignationSearchCheckBox; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//label[text()='Company Location']")
	private WebElement WeekOffReportCmpLocationSearchCheckBox; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[2]")
	private WebElement WeekOffReportDayResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[3]")
	private WebElement WeekOffReportDateResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[4]")
	private WebElement WeekOffReportDayTypeResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[5]")
	private WebElement WeekOffReportDeptResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[6]")
	private WebElement WeekOffReportDesignationResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[7]")
	private WebElement WeekOffReportCmpLocationResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement WeekOffReportRowResult; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "(//button[@id='btnFilterEmployeeWeeklyOffReport'])[2]")
	private WebElement WeekOffReportFilterButton; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//a[@id='weekOffRequest_tab']")
	private WebElement WeekOffButtonInAttendance; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//button[text()='Apply Week-Off']")
	private WebElement ApplyWeekOffButtonInAttendance; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//a[text()='Week-Off for Others']")
	private WebElement ApplyWeekOffForOthersButtonInAttendance; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//span[@id='select2-drpEmployeeOther-container']")
	private WebElement EmpDrpDownWeekoffApply; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpEmployeeOther-results']")
	private WebElement EmpNameInputWeekoffApply; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//ul[@id='select2-drpEmployeeOther-results']/li[1]")
	private WebElement EmpNameSelectedWeekoffApply; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//input[@id='txtOldWeekOffDateOther']/../input[2]")
	private WebElement OldWeekoffDateTextField; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//input[@id='txtNewWeekOffDateOther']/../input[2]")
	private WebElement NewWeekoffDateTextField; //1stTestCase WeekOffReport
	
	
	@FindBy(xpath = "//input[@id='txtWeekOffQuickOtherReason']")
	private WebElement WeekOffReason; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//button[@id='btnWeekOffQuickOtherApply']")
	private WebElement WeekOffApplySaveButton; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "(//p[text()='Attendance'])[1]")
	private WebElement EmployeeAttendanceButton; // 4th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[2]")
	private WebElement WeekOffReportDateResultOnEmp; //1stTestCase WeekOffReport
	
	@FindBy(xpath = "//table[@id='dtEmployeeWeeklyOffReport']/tbody/tr[1]/td[6]")
	private WebElement WeekOffReportCmpLocationResultOnEmp; //1stTestCase WeekOffReport
	
	
	
	
	
	
	
	
	public boolean  WeekOffReportButton()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(WeekOffReportButton, "visible", "", 15);
			return Utils.safeClick(driver, WeekOffReportButton);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  WeekOffReportSearchTextField(String locationName)
	{
		try {
			utils.waitForEle(WeekOffReportSearchTextField, "visible", "", 15);
			WeekOffReportSearchTextField.clear();
			WeekOffReportSearchTextField.sendKeys(locationName);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  WeekOffReportDaySearchCheckBox()
	{
		try {
			utils.waitForEle(WeekOffReportDaySearchCheckBox, "visible", "", 15);
			WeekOffReportDaySearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  WeekOffReportRowResult()
	{
		try {
			utils.waitForEle(WeekOffReportRowResult, "visible", "", 15);
			WeekOffReportRowResult.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WeekOffReportDateSearchCheckBox()
	{
		try {
			Thread.sleep(1000);
			utils.waitForEle(WeekOffReportDateSearchCheckBox, "visible", "", 15);
			WeekOffReportDateSearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WeekOffReportDeptSearchCheckBox()
	{
		try {
			utils.waitForEle(WeekOffReportDeptSearchCheckBox, "visible", "", 15);
			WeekOffReportDeptSearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WeekOffReportDesignationSearchCheckBox()
	{
		try {
			utils.waitForEle(WeekOffReportDesignationSearchCheckBox, "visible", "", 15);
			WeekOffReportDesignationSearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WeekOffReportCmpLocationSearchCheckBox()
	{
		try {
			utils.waitForEle(WeekOffReportCmpLocationSearchCheckBox, "visible", "", 15);
			WeekOffReportCmpLocationSearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffReportDayResult(String day) {
	    try {
	        utils.waitForEle(WeekOffReportDayResult, "visible", "", 15);

	        String actualDay = WeekOffReportDayResult.getText().trim();
	        if (!actualDay.contains(day)) {
	            throw new Exception("Mismatch in WeekOff day. Expected: " + day + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean WeekOffReportDateResult(String date) {
	    try {
	        utils.waitForEle(WeekOffReportDateResult, "visible", "", 15);

	        // Try parsing the input date dynamically
	        LocalDate parsedDate;
	        if (date.contains("-")) {
	            if (date.indexOf('-') == 4) {
	                // Format: yyyy-MM-dd
	                parsedDate = LocalDate.parse(date);
	            } else {
	                // Format: dd-MM-yyyy
	                DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                parsedDate = LocalDate.parse(date, inputFmt);
	            }
	        } else {
	            throw new Exception("Invalid date format: " + date);
	        }

	        // Convert to dd-MM-yyyy for UI comparison
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String expectedDate = parsedDate.format(uiFormatter);

	        String actualDate = WeekOffReportDateResult.getText().trim();

	        if (!actualDate.contains(expectedDate)) {
	            throw new Exception("Mismatch in WeekOff date. Expected: " + expectedDate + ", Found: " + actualDate);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}


	public boolean WeekOffReportDayTypeResult(String day) {
	    try {
	        utils.waitForEle(WeekOffReportDayTypeResult, "visible", "", 15);

	        String actualDay = WeekOffReportDayTypeResult.getText().trim();
	        if (!actualDay.contains(day)) {
	            throw new Exception("Mismatch in WeekOff dayType. Expected: " + day + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean WeekOffReportDeptResult(String Dept) {
	    try {
	        utils.waitForEle(WeekOffReportDeptResult, "visible", "", 15);

	        String actualDay = WeekOffReportDeptResult.getText().trim();
	        if (!actualDay.contains(Dept)) {
	            throw new Exception("Mismatch in Dept. Expected: " + Dept + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean WeekOffReportDesignationResult(String Dept) {
	    try {
	        utils.waitForEle(WeekOffReportDesignationResult, "visible", "", 15);

	        String actualDay = WeekOffReportDesignationResult.getText().trim();
	        if (!actualDay.contains(Dept)) {
	            throw new Exception("Mismatch in Designation. Expected: " + Dept + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean WeekOffReportCmpLocationResult(String Dept) {
	    try {
	        utils.waitForEle(WeekOffReportCmpLocationResult, "visible", "", 15);

	        String actualDay = WeekOffReportCmpLocationResult.getText().trim();
	        if (!actualDay.contains(Dept)) {
	            throw new Exception("Mismatch in CmpLocation. Expected: " + Dept + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean  WeekOffReportFilterButton()
	{
		try {
			utils.waitForEle(WeekOffReportFilterButton, "visible", "", 15);
			WeekOffReportFilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffReportFilterResultValidation(String Dept) {
	    try {
	        utils.waitForEle(WeekOffReportRowResult, "visible", "", 15);

	        String actualDay = WeekOffReportRowResult.getText().trim();
	        if (!actualDay.contains(Dept)) {
	            throw new Exception("Mismatch in Filtered Result. Expected: " + Dept + ", Found: " + actualDay);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean WeekOffRptFilteredDateValidation(String date) {
	    try {
	        utils.waitForEle(WeekOffReportDateResult, "visible", "", 30);

	        // Convert input date (yyyy-MM-dd) to UI format (dd-MM-yyyy)
	        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get the actual date text from UI
	        String actualDate = WeekOffReportDateResult.getText().trim();

	        if (!actualDate.contains(formattedDate)) {
	            throw new Exception("Date mismatch: expected '" + formattedDate + "', but found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean FilteredDateValidation(String year, String month) {
	    try {
	        utils.waitForEle(WeekOffReportDateResult, "visible", "", 30);

	        // Convert month name (e.g., "October") → month number ("10")
	        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
	        Month monthEnum = Month.from(monthFormatter.parse(month));
	        String monthNumber = String.format("%02d", monthEnum.getValue()); // "10" for October

	        String actualDate = WeekOffReportDateResult.getText().trim(); // e.g., "04-10-2025"

	        // Parse the UI date
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate parsedDate = LocalDate.parse(actualDate, uiFormatter);

	        String actualMonth = String.format("%02d", parsedDate.getMonthValue());
	        String actualYear = String.valueOf(parsedDate.getYear());

	        // Validate both month and year
	        if (!actualMonth.equals(monthNumber) || !actualYear.equals(year)) {
	            throw new Exception("Date mismatch: expected month/year '" + monthNumber + "-" + year +
	                    "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean WeekOffButtonInAttendance() {
	    try {
	        // Wait for button
	        utils.waitForEle(WeekOffButtonInAttendance, "visible", "", 15);

	        // Try clicking
	        WeekOffButtonInAttendance.click();

	        // If not displayed → retry
	        if (!WeekOffButtonInAttendance.isDisplayed()) {

	            // Retry Steps
	            driver.navigate().refresh();
	            Thread.sleep(1500);

	            utils.waitForEle(EmployeeAttendanceButton, "clickable", "", 10);
	            EmployeeAttendanceButton.click();

	            utils.waitForEle(WeekOffButtonInAttendance, "clickable", "", 10);
	            WeekOffButtonInAttendance.click();
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = (e.getMessage() != null) ? e.getMessage() : "Unknown error";
	        return false;
	    }
	}

	
	public boolean  ApplyWeekOffButtonInAttendance()
	{
		try {
			utils.waitForEle(ApplyWeekOffButtonInAttendance, "visible", "", 15);
			ApplyWeekOffButtonInAttendance.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ApplyWeekOffForOthersButtonInAttendance()
	{
		try {
			utils.waitForEle(ApplyWeekOffForOthersButtonInAttendance, "visible", "", 15);
			ApplyWeekOffForOthersButtonInAttendance.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EmpDrpDownWeekoffApply()
	{
		try {
			utils.waitForEle(EmpDrpDownWeekoffApply, "visible", "", 15);
			EmpDrpDownWeekoffApply.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EmpNameInputWeekoffApply(String name)
	{
		try {
			utils.waitForEle(EmpNameInputWeekoffApply, "visible", "", 15);
			EmpNameInputWeekoffApply.clear();
			EmpNameInputWeekoffApply.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EmpNameSelectedWeekoffApply()
	{
		try {
			utils.waitForEle(EmpNameSelectedWeekoffApply, "visible", "", 15);
			EmpNameSelectedWeekoffApply.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OldWeekoffDateTextField()
	{
		try {
			utils.waitForEle(OldWeekoffDateTextField, "visible", "", 15);
			OldWeekoffDateTextField.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateSelected(String OldDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('txtOldWeekOffDateOther').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('txtOldWeekOffDateOther')._flatpickr) {" +
		            "  document.getElementById('txtOldWeekOffDateOther')._flatpickr.setDate('" + OldDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on txtOldWeekOffDateOther'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean  NewWeekoffDateTextField()
	{
		try {
			utils.waitForEle(NewWeekoffDateTextField, "visible", "", 15);
			NewWeekoffDateTextField.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean NewWeekOffDateSelected(String NewDate) {
	    try {
	        Thread.sleep(2000); // small delay to ensure picker loads properly

	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly (in case the input is protected)
	        js.executeScript("document.getElementById('txtNewWeekOffDateOther').removeAttribute('readonly');");

	        // Clear existing value (both from the DOM and Flatpickr instance)
	        js.executeScript(
	            "const input = document.getElementById('txtNewWeekOffDateOther');" +
	            "if (input) {" +
	            "  input.value = '';" + // clear any old text
	            "  if (input._flatpickr) {" +
	            "    input._flatpickr.clear();" + // clear Flatpickr selection
	            "    input._flatpickr.setDate('" + NewDate + "', true);" + // set new date
	            "  } else {" +
	            "    input.value = '" + NewDate + "';" + // fallback if flatpickr not initialized
	            "  }" +
	            "} else {" +
	            "  throw new Error('Element txtNewWeekOffDateOther not found');" +
	            "}"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean  WeekOffReason(String name)
	{
		try {
			utils.waitForEle(WeekOffReason, "visible", "", 15);
			WeekOffReason.clear();
			WeekOffReason.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WeekOffApplySaveButton()
	{
		try {
			utils.waitForEle(WeekOffApplySaveButton, "visible", "", 15);
			WeekOffApplySaveButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean WeekOffReportDateResultOnEmp(String date) {
	    try {
	        utils.waitForEle(WeekOffReportDateResultOnEmp, "visible", "", 15);

	        // Try parsing the input date dynamically
	        LocalDate parsedDate;
	        if (date.contains("-")) {
	            if (date.indexOf('-') == 4) {
	                // Format: yyyy-MM-dd
	                parsedDate = LocalDate.parse(date);
	            } else {
	                // Format: dd-MM-yyyy
	                DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                parsedDate = LocalDate.parse(date, inputFmt);
	            }
	        } else {
	            throw new Exception("Invalid date format: " + date);
	        }

	        // Convert to dd-MM-yyyy for UI comparison
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String expectedDate = parsedDate.format(uiFormatter);

	        String actualDate = WeekOffReportDateResultOnEmp.getText().trim();

	        if (!actualDate.contains(expectedDate)) {
	            throw new Exception("Mismatch in WeekOff date. Expected: " + expectedDate + ", Found: " + actualDate);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean WeekOffReportCmpLocationResultOnEmp(String Dept) {
	    try {
	        utils.waitForEle(WeekOffReportCmpLocationResultOnEmp, "visible", "", 15);

	        String actualDay = WeekOffReportCmpLocationResultOnEmp.getText().trim();
	        if (!actualDay.contains(Dept)) {
	            throw new Exception("Mismatch in CmpLocation. Expected: " + Dept + ", Found: " + actualDay);
	        }

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
		exceptionDesc = this.exceptionDesc;
	}
	
}
