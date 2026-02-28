package com.MeghPI.Attendance.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LogResults;
import utils.Utils;

public class MeghPIAttendanceEmployeePage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	LogResults logResults = new LogResults();

	public String reason = "";
	public int holidayCountTillToday = 0;
	public int absentCount = 0;
	public int holidayCount = 0;
	public int workingDayCount = 0;   // <-
	public static String lastAbsentCount = "";
	
	public MeghPIAttendanceEmployeePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[@id='dtAttendanceEmployee_filter']/label/input")
	private WebElement SearchTextFieldOnEmpTabOnAdmin; //1stTestCas
	
	@FindBy(xpath = "//p[@class='user_email_id']")
	private WebElement EmpIdOnEmpTabOnAdmin;

	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']//th")
	public List<WebElement> EmpAttendaDateHeader;

	@FindBy(id = "attendanceTableScroll")
	private WebElement AttendanceHorizontalScrollDiv;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']/tbody/tr[1]/td[8]/div/div/img")
	public WebElement EmpAttendanceLoaded;
	
	@FindBy(xpath = "//input[@aria-controls='dtEmployeeAttendance']")
	public WebElement EmpAttendanceTabSearchTextField;
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemAttnFinalStatus-StatusDescdtEmployeeAttendance']")
	public WebElement EmpAttendanceTabSearchStatusSearchOption;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[1]")
	public List<WebElement> EmpAttendanceDatetRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[2]")
	public List<WebElement> EmpAttendanceInTimetRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[3]")
	public List<WebElement> EmpAttendanceOutTimetRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[6]")
	public List<WebElement> EmpAttendanceShiftRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[7]")
	public List<WebElement> EmpAttendanceStatustRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[4]")
	public List<WebElement> EmpAttendanceWorkingHourstRow;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[5]")
	public List<WebElement> EmpAttendanceOThourstRow;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[6]")
	public WebElement EmpAttendanceShiftRows;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[8]/div/div/img")
	public List<WebElement> EmpAttendanceTable3dots;
	
	@FindBy(xpath = "//a[text()='Apply Leave']")
	public List<WebElement> EmpAttendanceTable3dotsApplyLeaveOption;
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly Summary')]")
	public WebElement EmpAttendanceTabMonthlySummary;
	
	@FindBy(xpath = "//p[@id='leaveCount']")
	public WebElement EmpAttendanceTabMonthlySummaryLeaveCount;
	
	
	@FindBy(xpath = "//span[@id='appliedRegularizationCount']")
	public WebElement EmpAttendanceTabMonthlySummaryRegularizationCount;
	
	@FindBy(xpath = "//span[@id='totalGraceAllowed']")
	public WebElement EmpAttendanceTabMonthlySummaryGraceTimeAllowCount;
	
	@FindBy(xpath = "//span[@id='graceTaken']")
	public WebElement EmpAttendanceTabMonthlySummaryGraceTimeTakenCount;
	
	
	@FindBy(xpath = "//span[@id='presentDayCount']")
	public WebElement EmpAttendanceTabMonthlySummaryPresentDaysCount;
	
	@FindBy(xpath = "//span[@id='absentDayCount']")
	public WebElement EmpAttendanceTabMonthlySummaryAbsentDaysCount;
	
	@FindBy(xpath = "//span[@id='totalOvertimeHr']")
	public WebElement EmpAttendanceTabMonthlySummaryOTHoursCount;
	
	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr/td[1]/div/div/p[1]")
	public List<WebElement> EmpNameDisplayed;
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemOTMinutesdtAttendanceEmployee']")
	public WebElement OTHoursCheckBox;
	
	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[5]")
	public WebElement OTHoursRowValidation;
	
	@FindBy(xpath = "(//button[@id='btnPrevious'])[2]")
	private WebElement MonthFilterBackButton; //8th TestCase
	
	
	public boolean validateHeaderAttendanceValues(String empId, String empName,
            String expectedPresent,
            String expectedAbsent,
            String expectedLeave,
            String expectedOTHours) {
try {
int maxRetries = 5;
int delayMs = 5000;

for (int attempt = 1; attempt <= maxRetries; attempt++) {

System.out.println("🔄 Attempt: " + attempt + "/" + maxRetries);

// --- Validate employee ID ---
String expectedEmpId = "#" + empId.trim();
String actualEmpId = EmpIdOnEmpTabOnAdmin.getText().trim();

if (!actualEmpId.equalsIgnoreCase(expectedEmpId)) {
System.out.println("❌ Employee ID mismatch. Expected: " + expectedEmpId + " | Actual: " + actualEmpId);
return false;
}

// ================================
// FETCH HEADER VALUES
// ================================
String presentXpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[2]";
String absentXpath  = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[3]";
String leaveXpath   = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[4]";
String otXpath      = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[5]";

String actualPresent = driver.findElement(By.xpath(presentXpath)).getText().trim();
String actualAbsent  = driver.findElement(By.xpath(absentXpath)).getText().trim();
String actualLeave   = driver.findElement(By.xpath(leaveXpath)).getText().trim();
String actualOTHours = driver.findElement(By.xpath(otXpath)).getText().trim();

System.out.println("Expected Present: " + expectedPresent + " | Actual: " + actualPresent);
System.out.println("Expected Absent : " + expectedAbsent  + " | Actual: " + actualAbsent);
System.out.println("Expected Leave  : " + expectedLeave   + " | Actual: " + actualLeave);
System.out.println("Expected OTHr   : " + expectedOTHours + " | Actual: " + actualOTHours);

// ================================
// COMPARE VALUES
// ================================
boolean matched =
actualPresent.equals(expectedPresent)
&& actualAbsent.equals(expectedAbsent)
&& actualLeave.equals(expectedLeave)
&& actualOTHours.equals(expectedOTHours);

if (matched) {
System.out.println("✅ Header values matched successfully.");
return true;
}

// --- Retry if mismatch ---
if (attempt < maxRetries) {
System.out.println("❌ Header mismatch. Retrying after refresh...");

driver.navigate().refresh();

Thread.sleep(4000);
MonthFilterBackButton.click();
SearchTextFieldOnEmpTabOnAdmin.clear();
SearchTextFieldOnEmpTabOnAdmin.sendKeys(empName);

Thread.sleep(delayMs);
}
}

exceptionDesc = "Header Present/Absent/Leave/OT did not match after " + maxRetries + " retries.";
return false;

} catch (Exception e) {
exceptionDesc = e.getMessage();
return false;
}
}

	
	
	public boolean getHolidayCountTillToday() {
	    try {
	        // Today's day number
	        int todayDate = LocalDate.now().getDayOfMonth();

	        // Table row (Employee row)
	        WebElement table = driver.findElement(By.id("dtAttendanceEmployee"));

	        // First row inside tbody
	        WebElement firstRow = table.findElement(By.cssSelector("tbody tr"));

	        // All <td> cells
	        List<WebElement> dayCells = firstRow.findElements(By.cssSelector("td"));

	        int holidayCount = 0;

	        int startIndex = 5; // date columns start after 5 summary columns

	        for (int i = 0; i < todayDate; i++) {
	            WebElement cell = dayCells.get(startIndex + i);

	            String value = cell.getText().trim();

	            if (value.equalsIgnoreCase("H")) {
	                holidayCount++;
	            }
	        }

	        // Store in class variable
	        holidayCountTillToday = holidayCount;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }

	    return true;
	}
	
	
	
	
	/*
	public boolean validateAttendanceRow(
	        String dateYYYYMMDD,
	        String expectedInTime,
	        String expectedOutTime,
	        String expectedShift,
	        String expectedStatus) {

	    try {
	        // Fix: parse yyyy-MM-dd input
	        LocalDate date = LocalDate.parse(dateYYYYMMDD, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate;

	        // Fetch values (use .get(0))
	        String actualInTime  = EmpAttendanceInTimetRow.getText().trim();
	        String actualOutTime = EmpAttendanceOutTimetRow.getText().trim();
	        String actualShift   = EmpAttendanceShiftRow.getText().trim();
	        String actualStatus  = EmpAttendanceStatustRow.getText().trim();

	        System.out.println("Expected → In:" + expectedInTime +
	                ", Out:" + expectedOutTime +
	                ", Shift:" + expectedShift +
	                ", Status:" + expectedStatus);

	        System.out.println("Actual   → In:" + actualInTime +
	                ", Out:" + actualOutTime +
	                ", Shift:" + actualShift +
	                ", Status:" + actualStatus);

	        if (!actualInTime.contains(expectedInTime)) {
	            exceptionDesc = "InTime mismatch. Expected: " + expectedInTime + ", Found: " + actualInTime;
	            return false;
	        }

	        if (!actualOutTime.contains(expectedOutTime)) {
	            exceptionDesc = "OutTime mismatch. Expected: " + expectedOutTime + ", Found: " + actualOutTime;
	            return false;
	        }

	        if (!actualShift.contains(expectedShift)) {
	            exceptionDesc = "Shift mismatch. Expected: " + expectedShift + ", Found: " + actualShift;
	            return false;
	        }

	        if (!actualStatus.contains(expectedStatus)) {
	            exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + actualStatus;
	            return false;
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}



*/



	
	
	public boolean EmpAttendanceLoaded() {
		try {

			utils.waitForEle(EmpAttendanceLoaded, "visible", "", 30);
			EmpAttendanceLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EmpAttendanceTabSearchTextField(String deptname) {
		try {

			utils.waitForEle(EmpAttendanceTabSearchTextField,  "visible", "", 10);
			
			EmpAttendanceTabSearchTextField.clear();
			EmpAttendanceTabSearchTextField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EmpAttendanceTabSearchStatusSearchOption() {
		try {

			utils.waitForEle(EmpAttendanceTabSearchStatusSearchOption, "visible", "", 30);
			EmpAttendanceTabSearchStatusSearchOption.click();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EmpAttendanceShiftRow(String firstname) {
	    try {
	        utils.waitForEle(EmpAttendanceShiftRows, "visible", "", 15);
	       

	        String uiName = EmpAttendanceShiftRows.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected first name: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean validateAttendanceRow(
	        String dateYYYYMMDD,
	        String expectedInTime,
	        String expectedOutTime,
	        String expectedShift,
	        String expectedStatus) {

	    try {

	    	  // Fix: parse yyyy-MM-dd input
	        LocalDate date = LocalDate.parse(dateYYYYMMDD, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate;

	        // Loop through all rows
	        for (int i = 0; i < EmpAttendanceDatetRow.size(); i++) {

	            String uiDate = EmpAttendanceDatetRow.get(i).getText().trim();

	            // Match date row
	            if (uiDate.contains(finalDateUI)) {

	                String actualInTime  = EmpAttendanceInTimetRow.get(i).getText().trim();
	                String actualOutTime = EmpAttendanceOutTimetRow.get(i).getText().trim();
	                String actualShift   = EmpAttendanceShiftRow.get(i).getText().trim();
	                String actualStatus  = EmpAttendanceStatustRow.get(i).getText().trim();

	                // Debug logs
	                System.out.println("Expected → " +
	                        "Date:" + finalDateUI +
	                        ", In:" + expectedInTime +
	                        ", Out:" + expectedOutTime +
	                        ", Shift:" + expectedShift +
	                        ", Status:" + expectedStatus);

	                System.out.println("Actual   → " +
	                        "In:" + actualInTime +
	                        ", Out:" + actualOutTime +
	                        ", Shift:" + actualShift +
	                        ", Status:" + actualStatus);

	                // Compare
	                if (!actualInTime.contains(expectedInTime)) {
	                    exceptionDesc = "InTime mismatch. Expected: " + expectedInTime + ", Found: " + actualInTime;
	                    return false;
	                }

	                if (!actualOutTime.contains(expectedOutTime)) {
	                    exceptionDesc = "OutTime mismatch. Expected: " + expectedOutTime + ", Found: " + actualOutTime;
	                    return false;
	                }

	                if (!actualShift.contains(expectedShift)) {
	                    exceptionDesc = "Shift mismatch. Expected: " + expectedShift + ", Found: " + actualShift;
	                    return false;
	                }

	                if (!actualStatus.contains(expectedStatus)) {
	                    exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + actualStatus;
	                    return false;
	                }

	                return true; // all match
	            }
	        }

	        // If date not found
	        exceptionDesc = "No row found for date: " + finalDateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}

	public boolean validateAttendanceRowForOT(
	        String dateInput,
	        String expectedIn,
	        String expectedOut,
	        String expectedShift,
	        String expectedStatus,
	        String expectedWorkingHrs,
	        String expectedOTHours) {

	    try {
	        // Accepts yyyy-MM-dd or yyyyMMdd
	        LocalDate date = dateInput.contains("-") ?
	                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd")) :
	                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate;

	        // Find row by matching date column
	        for (int i = 0; i < EmpAttendanceDatetRow.size(); i++) {

	            String uiDate = EmpAttendanceDatetRow.get(i).getText().trim();

	            if (uiDate.contains(finalDateUI)) {

	                String actualIn = EmpAttendanceInTimetRow.get(i).getText().trim();
	                String actualOut = EmpAttendanceOutTimetRow.get(i).getText().trim();
	                String actualShift = EmpAttendanceShiftRow.get(i).getText().trim();
	                String actualStatus = EmpAttendanceStatustRow.get(i).getText().trim();
	                String actualWorkHrs = EmpAttendanceWorkingHourstRow.get(i).getText().trim();
	                String actualOTHrs = EmpAttendanceOThourstRow.get(i).getText().trim();

	                // Logging to console for debugging
	                System.out.println("============== VALIDATING ROW ==============");
	                System.out.println("Expected → Date:" + finalDateUI +
	                        ", In:" + expectedIn +
	                        ", Out:" + expectedOut +
	                        ", Shift:" + expectedShift +
	                        ", Status:" + expectedStatus +
	                        ", WorkingHrs:" + expectedWorkingHrs +
	                        ", OTHrs:" + expectedOTHours);

	                System.out.println("Actual   → In:" + actualIn +
	                        ", Out:" + actualOut +
	                        ", Shift:" + actualShift +
	                        ", Status:" + actualStatus +
	                        ", WorkingHrs:" + actualWorkHrs +
	                        ", OTHrs:" + actualOTHrs);
	                System.out.println("============================================");

	                // Comparison
	                if (!actualIn.contains(expectedIn)) {
	                    exceptionDesc = "InTime mismatch. Expected: " + expectedIn + ", Found: " + actualIn;
	                    return false;
	                }

	                if (!actualOut.contains(expectedOut)) {
	                    exceptionDesc = "OutTime mismatch. Expected: " + expectedOut + ", Found: " + actualOut;
	                    return false;
	                }

	                if (!actualShift.contains(expectedShift)) {
	                    exceptionDesc = "Shift mismatch. Expected: " + expectedShift + ", Found: " + actualShift;
	                    return false;
	                }

	                if (!actualStatus.contains(expectedStatus)) {
	                    exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + actualStatus;
	                    return false;
	                }

	                if (!actualWorkHrs.contains(expectedWorkingHrs)) {
	                    exceptionDesc = "Working Hours mismatch. Expected: " + expectedWorkingHrs + ", Found: " + actualWorkHrs;
	                    return false;
	                }

	                if (!actualOTHrs.contains(expectedOTHours)) {
	                    exceptionDesc = "OT Hours mismatch. Expected: " + expectedOTHours + ", Found: " + actualOTHrs;
	                    return false;
	                }

	                return true;
	            }
	        }

	        exceptionDesc = "No attendance row found for date: " + finalDateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Exception while validating row: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean click3DotsAndApplyLeave(String dateFromExcel) {
	    try {
	        // parse date yyyy-MM-dd
	        LocalDate date = LocalDate.parse(dateFromExcel, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate; // e.g. Mon, 03-11-2025

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // find the exact row by date (use normalize-space to avoid weird whitespace)
	        String rowXpath = "//table[@id='dtEmployeeAttendance']//tbody//tr[td[1][normalize-space()='" + finalDateUI + "']]";
	        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

	        // find and click the dropdown-toggle in that row (the clickable element)
	        WebElement toggle = row.findElement(By.cssSelector(".dropdown-toggle"));
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
	        wait.until(ExpectedConditions.elementToBeClickable(toggle)).click();

	        // small wait for menu to become visible (menu is inside the row in your DOM)
	        By applyLocatorRelative = By.xpath(".//ul[contains(@class,'dropdown-menu')]/li/a[normalize-space()='Apply Leave']");

	        // wait for the apply leave link to appear within that row
	        // use visibilityOfElementLocated on a locator rooted at the row by finding element with relative xpath
	        WebElement applyLink = wait.until(driver1 -> {
	            try {
	                WebElement el = row.findElement(applyLocatorRelative);
	                return el.isDisplayed() ? el : null;
	            } catch (Exception ex) {
	                return null;
	            }
	        });

	        if (applyLink == null) {
	            exceptionDesc = "Apply Leave link not found for date: " + finalDateUI;
	            return false;
	        }

	        // try normal click, fallback to JS click
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(applyLink)).click();
	        } catch (Exception clickEx) {
	            // fallback
	            js.executeScript("arguments[0].click();", applyLink);
	        }

	        // optionally wait until the modal opens (if Apply Leave opens a modal with id #phLeaveForMeEditor)
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#phLeaveForMeEditor")));
	        } catch (Exception ignored) {
	            // modal may open differently; ignore if not necessary
	        }

	        return true;
	    } catch (Exception e) {
	        exceptionDesc = "Exception in click3DotsAndApplyLeave: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}


	public boolean click3DotsAndRegularization(String dateFromExcel) {
	    try {
	        // parse yyyy-MM-dd
	        LocalDate date = LocalDate.parse(dateFromExcel, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate; // Mon, 03-11-2025

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // find the row for this date
	        String rowXpath = "//table[@id='dtEmployeeAttendance']//tbody//tr[td[1][normalize-space()='" + finalDateUI + "']]";
	        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

	        // find & click the 3-dots dropdown
	        WebElement toggle = row.findElement(By.cssSelector(".dropdown-toggle"));
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
	        wait.until(ExpectedConditions.elementToBeClickable(toggle)).click();

	        // LOCATOR for Regularization inside same row
	        By regularizationLocator = By.xpath(".//ul[contains(@class,'dropdown-menu')]/li/a[normalize-space()='Regularization']");

	        // wait until the item becomes visible inside same row
	        WebElement regularizationLink = wait.until(driver1 -> {
	            try {
	                WebElement el = row.findElement(regularizationLocator);
	                return el.isDisplayed() ? el : null;
	            } catch (Exception ex) {
	                return null;
	            }
	        });

	        if (regularizationLink == null) {
	            exceptionDesc = "Regularization option not found for date: " + finalDateUI;
	            return false;
	        }

	        // try normal click → fallback JS click
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(regularizationLink)).click();
	        } catch (Exception clickEx) {
	            js.executeScript("arguments[0].click();", regularizationLink);
	        }

	        // OPTIONAL if Regularization opens a popup (update ID based on your page)
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#phRegularizationEditor")));
	        } catch (Exception ignored) {}

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Exception in click3DotsAndRegularization: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}



	
	
	public boolean  EmpAttendanceTabMonthlySummary()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EmpAttendanceTabMonthlySummary, "visible", "", 20);
			EmpAttendanceTabMonthlySummary.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpAttendanceTabMonthlySummaryLeaveCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryLeaveCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryLeaveCount.getText().trim();

	        if (uiText.equals(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Leave count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}

	
	
	
	public boolean EmpAttendanceTabMonthlySummaryRegularizationCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryRegularizationCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryRegularizationCount.getText().trim();

	        if (uiText.equals(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Leave count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	public boolean EmpAttendanceTabMonthlySummaryGraceTimeAllowCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryGraceTimeAllowCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryGraceTimeAllowCount.getText().trim();

	        if (uiText.contains(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Leave count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	public boolean EmpAttendanceTabMonthlySummaryGraceTimeTakenCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryGraceTimeTakenCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryGraceTimeTakenCount.getText().trim();

	        if (uiText.contains(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Leave count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	public boolean EmpAttendanceTabMonthlySummaryPresentDaysCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryPresentDaysCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryPresentDaysCount.getText().trim();

	        if (uiText.contains(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Present count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmpAttendanceTabMonthlySummaryAbsentDaysCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryAbsentDaysCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryAbsentDaysCount.getText().trim();

	        if (uiText.contains(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "Absent count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	public boolean EmpAttendanceTabMonthlySummaryOTHoursCount(String expectedCount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpAttendanceTabMonthlySummaryOTHoursCount, "visible", "", 20);

	        String uiText = EmpAttendanceTabMonthlySummaryOTHoursCount.getText().trim();

	        if (uiText.contains(expectedCount)) {
	            return true;
	        } else {
	            exceptionDesc = "OT count mismatch. Expected: " + expectedCount + ", Found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean countAbsentStatus() {
	    try {
	        // All rows in attendance table
	        List<WebElement> rows = driver.findElements(
	                By.xpath("//table[@id='dtEmployeeAttendance']//tbody/tr")
	        );

	        int count = 0;

	        for (WebElement row : rows) {
	            WebElement statusCell = row.findElement(By.xpath("./td[7]"));

	            String statusText = statusCell.getText().trim();

	            if (statusText.equalsIgnoreCase("Absent")) {
	                count++;
	            }
	        }

	        // Store in global variable
	        absentCount = count;

	        return true;
	    } catch (Exception e) {
	        System.out.println("Error while counting Absent status: " + e.getMessage());
	        return false;
	    }
	}
	
	
	public boolean getHolidayCount() {
	    try {
	        holidayCount = 0;  // reset

	        // table rows
	        List<WebElement> rows = driver.findElements(By.cssSelector("#dtEmployeeAttendance tbody tr"));

	        for (WebElement row : rows) {
	            // status column (7th column)
	            WebElement statusCell = row.findElement(By.cssSelector("td:nth-child(7)"));
	            String status = statusCell.getText().trim();

	            if (status.equalsIgnoreCase("Weekly Off") ||
	                status.equalsIgnoreCase("Holiday")) {
	                holidayCount++;
	            }
	        }

	        return true; // success
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	
	public boolean getWorkingDayCount() {
	    try {
	        workingDayCount = 0; // reset

	        // Today's date in dd MMM yyyy format (same as UI)
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

	        List<WebElement> rows = driver.findElements(
	                By.xpath("//table[@id='dtEmployeeAttendance']//tbody/tr")
	        );

	        for (WebElement row : rows) {

	            // ---- 1. Read UI date from first column ----
	            String dateText = row.findElement(By.xpath("./td[1]")).getText().trim();
	            LocalDate rowDate = LocalDate.parse(dateText, uiFormat);

	            // Consider only rows UP TO CURRENT DATE
	            if (rowDate.isAfter(today)) {
	                continue;
	            }

	            // ---- 2. Read status column (7th column) ----
	            String status = row.findElement(By.xpath("./td[7]")).getText().trim();

	            // ---- 3. Exclude non-working statuses ----
	            if (status.equalsIgnoreCase("Holiday") ||
	                status.equalsIgnoreCase("Weekly Off") ||
	                status.equalsIgnoreCase("Leave")) {
	                continue;
	            }

	            // ---- 4. Remaining rows are WORKING DAYS ----
	            workingDayCount++;
	        }

	        return true;

	    } catch (Exception e) {
	        System.out.println("Error while calculating working days: " + e.getMessage());
	        return false;
	    }
	}
	
	
	
	
	
	
	public boolean EmpNameDisplayed(String firstname) {
	    try {

	        for (WebElement emp : EmpNameDisplayed) {

	            String uiName = emp.getText().trim();

	            // If any row contains the firstname → success
	            if (uiName.contains(firstname)) {
	                return true;
	            }
	        }

	        // If reached here → no match found
	        exceptionDesc = "Firstname '" + firstname + "' not found in employee list.";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean  OTHoursCheckBox()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(OTHoursCheckBox, "visible", "", 20);
			OTHoursCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean OTHoursRowValidation(String firstname) {
	    try {

	        

	            String uiName = OTHoursRowValidation.getText().trim();

	            // If any row contains the firstname → success
	            if (uiName.contains(firstname)) {
	                return true;
	            }

	        // If reached here → no match found
	        exceptionDesc = "OTHours '" + firstname + "' not found in employee list.";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean validateAttendanceRowForWeekOff(
	        String dateYYYYMMDD,
	        String expectedStatus) {

	    try {

	    	  // Fix: parse yyyy-MM-dd input
	        LocalDate date = LocalDate.parse(dateYYYYMMDD, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate;

	        // Loop through all rows
	        for (int i = 0; i < EmpAttendanceDatetRow.size(); i++) {

	            String uiDate = EmpAttendanceDatetRow.get(i).getText().trim();

	            // Match date row
	            if (uiDate.contains(finalDateUI)) {

	               
	                String actualStatus  = EmpAttendanceStatustRow.get(i).getText().trim();

	                // Debug logs
	                System.out.println("Expected → " +
	                        "Date:" + finalDateUI  +
	                        ", Status:" + expectedStatus);

	                System.out.println("Actual   → " +
	                        
	                        ", Status:" + actualStatus);

	                // Compare
	               

	                if (!actualStatus.contains(expectedStatus)) {
	                    exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + actualStatus;
	                    return false;
	                }

	                return true; // all match
	            }
	        }

	        // If date not found
	        exceptionDesc = "No row found for date: " + finalDateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	
	

	public boolean getAbsentCountFromRow() {
	    try {
	        // Base row
	        String base = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]";

	        // Count all "A" statuses from column 6 onwards
	        List<WebElement> absentList = driver.findElements(
	            By.xpath(base + "/td[position() >= 6][normalize-space(text())='A']")
	        );

	        // store value so test class can access
	        lastAbsentCount = String.valueOf(absentList.size());

	        return true;   // success

	    } catch (Exception e) {
	        lastAbsentCount = null;  // so test class knows failure
	        return false;            // failure
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
}
