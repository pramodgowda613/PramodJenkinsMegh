
package com.MeghPI.Attendance.pages;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;


public class MeghPIDailyAttendanceReportsPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	public MeghPIDailyAttendanceReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Daily Attendance Report']")
	private WebElement DailyAttendanceReportButton; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement DailyAttendanceReportEmpNameRowDisplay; //1stTestCase DailyAttendanceReport
	

	@FindBy(xpath = "//input[@aria-controls='dtDailyAttendanceReport']")
	private WebElement DailyAttendanceReportSearchTextField; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//label[text()='Attn Date']")
	private WebElement AttendanceDateCheckBox; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//label[text()='Hours Work']")
	private WebElement WorkingHourCheckBox; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//label[text()='InTime']")
	private WebElement InTimeCheckBox; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//label[text()='OutTime']")
	private WebElement OutTimeCheckBox; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//input[contains(@id,'hkdivDrpItemAttnFinalStatus')]")
	private WebElement StatusCheckBox; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[2]")
	private WebElement AttendanceDateValidation; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[8]")
	private WebElement AttendanceInTimeValidation; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[9]")
	private WebElement AttendanceOutTimeValidation; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[10]")
	private WebElement AttendanceStatusValidation; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "(//button[@id='btnFilterDailyAttendanceReport'])[2]")
	private WebElement DailyAttendanceRptFilterButton; //1stTestCase DailyAttendanceReport
	
	@FindBy(xpath = "//table[@id='dtDailyAttendanceReport']/tbody/tr[1]/td[7]")
	private WebElement DailyAttendanceRptWorkingHourValidation; //1stTestCase DailyAttendanceReport
	
	
	
	public boolean  DailyAttendanceReportButton()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(DailyAttendanceReportButton, "visible", "", 15);
			return Utils.safeClick(driver, DailyAttendanceReportButton);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	
	
	public boolean  DailyAttendanceReportEmpNameRowDisplay()
	{
		try {
		
			utils.waitForEle(DailyAttendanceReportEmpNameRowDisplay, "visible", "", 30);
			driver.navigate().refresh();
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DailyAttendanceReportSearchTextField(String empname)
	{
		try {
		
			utils.waitForEle(DailyAttendanceReportSearchTextField, "visible", "", 15);
			DailyAttendanceReportSearchTextField.clear();
			DailyAttendanceReportSearchTextField.sendKeys(empname);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean Attendancestatusvalidation(String expectedEmpName, String expectedAttnDate, 
	        String expectedHoursWork, String expectedInTime, 
	        String expectedOutTime, String expectedStatus) {

	    try {
	        utils.waitForEle(DailyAttendanceReportEmpNameRowDisplay, "visible", "", 30);

	        LocalDate date = LocalDate.parse(expectedAttnDate);
	        String formattedExpectedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='dtDailyAttendanceReport']//tbody/tr"));
	        boolean recordFound = false;

	        for (WebElement row : rows) {

	            // get only employee name (first <p>)
	            String empName = row.findElement(By.cssSelector(".user_details p:first-child")).getText().trim();

	            List<WebElement> cols = row.findElements(By.tagName("td"));
	            if (cols.size() < 10) continue;

	            String attnDate = cols.get(1).getText().trim();
	            String hoursWork = cols.get(6).getText().trim();
	            String inTime = cols.get(7).getText().trim();
	            String outTime = cols.get(8).getText().trim();
	            String status = cols.get(9).getText().trim();

	            if (empName.contains(expectedEmpName) &&
	                attnDate.contains(formattedExpectedDate)) {

	                recordFound = true;

	                if (!hoursWork.contains(expectedHoursWork))
	                    throw new Exception("Hours Work mismatch: expected '" + expectedHoursWork + "', found '" + hoursWork + "'");

	                if (!inTime.contains(expectedInTime))
	                    throw new Exception("InTime mismatch: expected '" + expectedInTime + "', found '" + inTime + "'");

	                if (!outTime.contains(expectedOutTime))
	                    throw new Exception("OutTime mismatch: expected '" + expectedOutTime + "', found '" + outTime + "'");

	                if (!status.contains(expectedStatus))
	                    throw new Exception("Status mismatch: expected '" + expectedStatus + "', found '" + status + "'");

	                break;
	            }
	        }

	        if (!recordFound)
	            throw new Exception("Record not found for Employee: " + expectedEmpName + " on Date: " + formattedExpectedDate);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}



	
	public boolean  AttendanceDateCheckBox()
	{
		try {
		
			utils.waitForEle(AttendanceDateCheckBox, "visible", "", 30);
			AttendanceDateCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  WorkingHourCheckBox()
	{
		try {
		
			utils.waitForEle(WorkingHourCheckBox, "visible", "", 30);
			WorkingHourCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean  InTimeCheckBox()
	{
		try {
		
			utils.waitForEle(InTimeCheckBox, "visible", "", 30);
			InTimeCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OutTimeCheckBox()
	{
		try {
		
			utils.waitForEle(OutTimeCheckBox, "visible", "", 30);
			OutTimeCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  StatusCheckBox()
	{
		try {
		
			utils.waitForEle(StatusCheckBox, "visible", "", 30);
			StatusCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceDateValidation(String date) {
	    try {
	        utils.waitForEle(AttendanceDateValidation, "visible", "", 30);

	        String actualDate = AttendanceDateValidation.getText().trim();

	        // Compare UI date with expected date
	        if (!actualDate.contains(date)) {
	            throw new Exception("Date mismatch: expected '" + date + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean AttendanceInTimeValidation(String date) {
	    try {
	        utils.waitForEle(AttendanceInTimeValidation, "visible", "", 30);

	        String actualDate = AttendanceInTimeValidation.getText().trim();

	        // Compare UI date with expected date
	        if (!actualDate.contains(date)) {
	            throw new Exception("InTime mismatch: expected '" + date + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean AttendanceOutTimeValidation(String date) {
	    try {
	        utils.waitForEle(AttendanceOutTimeValidation, "visible", "", 30);

	        String actualDate = AttendanceOutTimeValidation.getText().trim();

	        // Compare UI date with expected date
	        if (!actualDate.contains(date)) {
	            throw new Exception("Out Time mismatch: expected '" + date + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean AttendanceStatusValidation(String date) {
	    try {
	        utils.waitForEle(AttendanceStatusValidation, "visible", "", 30);

	        String actualDate = AttendanceStatusValidation.getText().trim();

	        // Compare UI date with expected date
	        if (!actualDate.contains(date)) {
	            throw new Exception("Status mismatch: expected '" + date + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean  DailyAttendanceRptFilterButton()
	{
		try {
		
			utils.waitForEle(DailyAttendanceRptFilterButton, "visible", "", 30);
			DailyAttendanceRptFilterButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean FilteredDateValidation(String year, String month) {
	    try {
	        utils.waitForEle(AttendanceDateValidation, "visible", "", 30);

	        // Convert month name (e.g., "October") to month number ("10")
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
	        Month monthEnum = Month.from(inputFormatter.parse(month));
	        String monthNumber = String.format("%02d", monthEnum.getValue()); // "10" for October

	        // Expected substring pattern like "-10-2025"
	        String expectedPattern = "-" + monthNumber + "-" + year;

	        String actualDate = AttendanceDateValidation.getText().trim();

	        if (!actualDate.contains(expectedPattern)) {
	            throw new Exception("Date mismatch: expected month/year '" + expectedPattern + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean DailyAttendanceRptFilteredDateValidation(String date) {
	    try {
	        utils.waitForEle(AttendanceDateValidation, "visible", "", 30);

	        // Convert input date (yyyy-MM-dd) to UI format (dd-MM-yyyy)
	        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get the actual date text from UI
	        String actualDate = AttendanceDateValidation.getText().trim();

	        if (!actualDate.contains(formattedDate)) {
	            throw new Exception("Date mismatch: expected '" + formattedDate + "', but found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean DailyAttendanceRptWorkingHourValidation(String workinghour) {
	    try {
	        utils.waitForEle(DailyAttendanceRptWorkingHourValidation, "visible", "", 30);

	        String actualDate = DailyAttendanceRptWorkingHourValidation.getText().trim();

	        // Compare UI date with expected date
	        if (!actualDate.contains(workinghour)) {
	            throw new Exception("Working Hour mismatch: expected '" + workinghour + "', found '" + actualDate + "'");
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
