 package com.MeghPI.Attendance.pages;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import groovyjarjarantlr4.v4.parse.ANTLRParser.element_return;
import utils.Utils;

public class MeghLeavePage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	
	
	public MeghLeavePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[text()='Apply Leave']")
	private WebElement ApplyLeaveButton; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveType-container']")
	private WebElement LeaveTypeDropdown; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveType-results']/li[contains(text(), 'Sick')]")
	private WebElement SickLeaveSelected; //1stTestCase
	
	@FindBy(xpath = "//input[@id='fromDateMyLeave']/../input[2]")
	private WebElement FromDateTextField; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpFromLeaveDurationMyLeave-container']")
	private WebElement LeaveDurationOne; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFromLeaveDurationMyLeave-results']/li[contains(text(), 'Full')]")
	private WebElement FullDayDurationOne; //1stTestCase
	
	@FindBy(xpath = "//input[@id='toDateMyLeave']/../input[2]")
	private WebElement ToDateTextField; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpToLeaveDurationMyLeave-container']")
	private WebElement LeaveDurationTwo; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpToLeaveDurationMyLeave-results']/li[contains(text(), 'Full')]")
	private WebElement FullDayDurationTwo; //1stTestCase
	
	@FindBy(xpath = "(//input[@id='txtReason'])[1]")
	private WebElement ReasonTextField; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnApplyMyLeave'])[1]")
	private WebElement RequestApplyButton; //1stTestCase
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement LeaveSearchTextField; //1stTestCase
	
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[1]")
	private WebElement LeaveTypeInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[2]")
	private WebElement LeaveFromDateInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[7]")
	private WebElement LeaveStatusInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[3]")
	private WebElement LeaveFromDateInTableInAdmin; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[8]")
	private WebElement LeaveStatusInTableInAdmin; //1stTestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemLeave-LeaveType-NamedtLeave']")
	private WebElement LeaveTypeOptionSelected; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtRejectReason']")
	private WebElement LeaveRejectionReason; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnRejectRequest']")
	private WebElement LeaveRejectButton; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnApproveRequest']")
	private WebElement LeaveApproveButton; //1stTestCase
	
	@FindBy(xpath = "//select[@name='dtLeave_length']")
	private WebElement LeaveModulePaginationInAdmin; //1stTestCase
	
	@FindBy(xpath = "//select[@name='dtMyLeaveRequest_length']")
	private WebElement LeaveModulePaginationInEmp; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnRevokeRequest']")
	private WebElement RevokeButton; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveType-results']/li[contains(text(), 'Casual')]")
	private WebElement CasualLeaveSelected; //1stTestCase
	
	@FindBy(xpath = "//a[text()='Leave for Others']")
	private WebElement LeaveForOthersButton; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForOthersEmp-container']")
	private WebElement EmployeeSelectionDropDown; //5th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForOthersEmp-results']")
	private WebElement EmployeeInputField; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForOthersEmp-results']")
	private WebElement EmpNameDisplayedAndSelected; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForOthersLeaveType-container']")
	private WebElement LeaveTypeDropDownOnAdmin; //5h TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForOthersLeaveType-results']")
	private WebElement LeaveTypeInputtedOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForOthersLeaveType-results']")
	private WebElement LeaveTypeSelectedOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@id='fromDate']/../input[2]")
	private WebElement FromDateOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpFromLeaveDuration-container']")
	private WebElement FromLeaveDurationDropdownAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpFromLeaveDuration-results']")
	private WebElement FromLeaveDurationInputtedInAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFromLeaveDuration-results']/li[contains(text(), 'Full')]")
	private WebElement FromLeaveDurationSelectedInAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@id='toDate']/../input[2]")
	private WebElement ToDateTextFieldInAdmin; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpToLeaveDuration-container']")
	private WebElement ToLeaveDurationDropDownInAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpToLeaveDuration-results']/li[contains(text(), 'Full')]")
	private WebElement ToLeaveDurationFullDaySelectedInAdmin; //5th TestCase
	
	@FindBy(xpath = "(//button[@id='btnApplyForOther'])[1]")
	private WebElement ApplyForOthersButton; //5th TestCase
	
	@FindBy(xpath = "//a[text()='Leave for Me']")
	private WebElement ApplyForMeButton; //6th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForMeLeaveType-container']")
	private WebElement LeaveTypeSelectionDropDownOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForMeLeaveType-results']")
	private WebElement LeaveTypeNameTextFieldOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForMeLeaveType-results']/li[1]")
	private WebElement LeaveTypeSelectedOfLeaveForMe; //6th TestCase
	
	
	@FindBy(xpath = "//input[@id='fromDateForMe']/../input[2]")
	private WebElement FromDateClickedLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpFromLeaveDurationForMe']")
	private WebElement FromLeaveDurationSelectedOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='toDateForMe']/../input[2]")
	private WebElement ToDateClickedLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpToLeaveDurationForMe']")
	private WebElement ToLeaveDurationSelectedOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='txtReasonForMe']")
	private WebElement LeaveReasonOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//button[@id='btnApplyForMe']")
	private WebElement ApplyButtonOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemLeave-Entity-EmployeeIddtLeave']")
	private WebElement EmpIDCheckBoxSearchOption; //7th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemRemarksdtLeave']")
	private WebElement ReasonCheckBoxSearchOption;//7th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[1]/div[1]/div/p[2]")
	private WebElement EmpIDInTable; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[6]/span")
	private WebElement ReasonInTable; //7th TestCase
	
	@FindBy(xpath = "(//button[@id='AttendanceFilter'])[2]")
	private WebElement FilterButton; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterCompanyLocation']")
	private WebElement OfficeDropDownSelected; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterDepartment']/../span")
	private WebElement DeptDropDownClicked; //8th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterDepartment-results']/li")
	private WebElement DeptDropDownSelected; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterTeam']/../span")
	private WebElement TeamDropDownClicked; //8th TestCase
	
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterTeam-results']/li")
	private WebElement TeamDropDownSelected; //8th TestCase
	
	
	@FindBy(xpath = "//button[@id='btnFilter']")
	private WebElement SaveFilterButton; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]")
	private WebElement FilterResults; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpLeaveTypes']")
	private WebElement LeaveTypesFilter; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpStatusFilter']")
	private WebElement LeaveStatusFilter; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[2]")
	private List<WebElement> LeaveTypesearchResults; //8th TestCase
	
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[8]")
	private List<WebElement> LeaveStatusSearchResults; //8th TestCase
	
	
	@FindBy(xpath = "//select[@id='drpFilterCompanyLocation']/../span")
	private WebElement OfficeDrpDownClicked; //8th TestCase
	
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpFilterCompanyLocation-results']/../../li/input")
	private WebElement OfficeNameInputted; //8th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterCompanyLocation-results']/li[1]")
	private WebElement OfficeNameSelectedOfFilter; //8th TestCase
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly Leave')]")
	private WebElement MonthlyLeaveSummaryDrpIcon; //8th TestCase
	
	@FindBy(xpath = "//p[text()='Applied Leaves']/../..")
	private WebElement StatisticsDisplayed; //8th TestCase
	
	
	public boolean ApplyLeaveButton()
	{
		try {
			utils.waitForEle(ApplyLeaveButton, "visible", "", 10);
			
			ApplyLeaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeDropdown()
	{
		try {
			utils.waitForEle(LeaveTypeDropdown, "visible", "", 10);
			
			LeaveTypeDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SickLeaveSelected()
	{
		try {
			utils.waitForEle(SickLeaveSelected, "visible", "", 10);
			
			SickLeaveSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean CasualLeaveSelected()
	{
		try {
			utils.waitForEle(CasualLeaveSelected, "visible", "", 10);
			
			CasualLeaveSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateTextField()
	{
		try {
			utils.waitForEle(FromDateTextField, "visible", "", 10);
			
			FromDateTextField.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateSelected(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDateMyLeave').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDateMyLeave')._flatpickr) {" +
		            "  document.getElementById('fromDateMyLeave')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDateMyLeave'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean LeaveDurationOne()
	{
		try {
			utils.waitForEle(LeaveDurationOne, "visible", "", 10);
			
			LeaveDurationOne.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FullDayDurationOne()
	{
		try {
			utils.waitForEle(FullDayDurationOne, "visible", "", 10);
			
			FullDayDurationOne.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextField()
	{
		try {
			utils.waitForEle(ToDateTextField, "visible", "", 10);
			
			ToDateTextField.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateSelected(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDateMyLeave').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDateMyLeave')._flatpickr) {" +
		            "  document.getElementById('toDateMyLeave')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDateMyLeave'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean LeaveDurationTwo()
	{
		try {
			utils.waitForEle(LeaveDurationTwo, "visible", "", 10);
			
			LeaveDurationTwo.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FullDayDurationTwo()
	{
		try {
			utils.waitForEle(FullDayDurationTwo, "visible", "", 10);
			
			FullDayDurationTwo.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ReasonTextField(String LeaveReason) {
		try {

			utils.waitForEle(ReasonTextField,  "visible", "", 10);
			ReasonTextField.clear();
			ReasonTextField.sendKeys(LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RequestApplyButton()
	{
		try {
			utils.waitForEle(RequestApplyButton, "visible", "", 10);
			
			RequestApplyButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveSearchTextField(String LeaveType)
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(LeaveSearchTextField, "visible", "", 10);
			
			LeaveSearchTextField.clear();
			LeaveSearchTextField.sendKeys(LeaveType);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean verifyLeaveRowInAdmin(String expectedEmpName, String expectedLeaveType, String expectedDate, String expectedStatus) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Convert yyyy-MM-dd → dd-MM-yyyy
	        String formattedDate = expectedDate.substring(8, 10) + "-" +
	                               expectedDate.substring(5, 7) + "-" +
	                               expectedDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    List<WebElement> cells = row.findElements(By.cssSelector("td"));
	                    if (cells.size() < 8) continue;

	                    // Extract employee name from first cell (inside .user_details p)
	                    String empName = cells.get(0).findElement(By.cssSelector(".user_details p.font_weight_medium"))
	                                          .getText().trim();

	                    String leaveType = cells.get(1).getText().trim();
	                    String fromDate = cells.get(2).getText().trim();
	                    String toDate = cells.get(3).getText().trim();
	                    String status = cells.get(7).getText().trim();

	                    // Normalize dates (remove weekday prefix)
	                    fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "");
	                    toDate = toDate.replaceAll("^[A-Za-z]{3},\\s*", "");

	                    // Match employee name (contains check to allow partial matches)
	                    if (empName.contains(expectedEmpName)
	                            && leaveType.equalsIgnoreCase(expectedLeaveType)
	                            && (fromDate.equals(formattedDate) || toDate.equals(formattedDate))
	                            && status.equalsIgnoreCase(expectedStatus)) {
	                        return true;
	                    }
	                } catch (StaleElementReferenceException ignored) {
	                    // Row may have reloaded; skip
	                }
	            }

	            // Scroll down for more rows
	            js.executeScript("window.scrollBy(0, 500);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("No matching row found for EmpName: " + expectedEmpName +
	                ", LeaveType: " + expectedLeaveType +
	                ", Date: " + expectedDate +
	                ", Status: " + expectedStatus);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}




	public boolean verifyLeaveRowInEmp(String expectedLeaveType, String expectedDate, String expectedStatus) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Convert yyyy-MM-dd → dd-MM-yyyy
	        String formattedExpectedDate = expectedDate.substring(8, 10) + "-"
	                                     + expectedDate.substring(5, 7) + "-"
	                                     + expectedDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    // Fetch values dynamically
	                    String leaveType = row.findElement(By.cssSelector("td:nth-child(1) span")).getText().trim();
	                    String fromDate = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
	                    String toDate = row.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
	                    String status = row.findElement(By.cssSelector("td:nth-child(7) p")).getText().trim();

	                    // Normalize table dates
	                    fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();
	                    toDate = toDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();

	                    // Match all conditions
	                    if (leaveType.equalsIgnoreCase(expectedLeaveType)
	                            && (fromDate.equals(formattedExpectedDate) || toDate.equals(formattedExpectedDate))
	                            && status.equalsIgnoreCase(expectedStatus)) {
	                        return true;
	                    }

	                } catch (StaleElementReferenceException ignored) {}
	            }

	            // Scroll and retry if not found
	            js.executeScript("window.scrollBy(0, 500);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("Row not found for LeaveType: " + expectedLeaveType +
	                ", Date: " + expectedDate + ", Status: " + expectedStatus);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	
	
	public boolean LeaveTypeOptionSelected()
	{
		try {
			utils.waitForEle(RequestApplyButton, "visible", "", 10);
			
			LeaveTypeOptionSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	
	public boolean approveLeaveByDate(String excelDate) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Convert Excel date "2025-08-04" → "04-08-2025"
	        String formattedDate = excelDate.substring(8, 10) + "-" +
	                               excelDate.substring(5, 7) + "-" +
	                               excelDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;
	        boolean found = false;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

	            for (WebElement row : rows) {
	                // Extract From and To dates (columns 3 & 4)
	                List<WebElement> dateCells = row.findElements(By.cssSelector("td"));
	                if (dateCells.size() < 4) continue;

	                String fromDate = dateCells.get(2).getText().trim();
	                String toDate = dateCells.get(3).getText().trim();

	                // Remove day names (Mon, Tue, etc.)
	                fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "");
	                toDate = toDate.replaceAll("^[A-Za-z]{3},\\s*", "");

	                // Match formatted date
	                if (fromDate.equals(formattedDate) || toDate.equals(formattedDate)) {
	                    List<WebElement> approveButtons = row.findElements(By.cssSelector(".approve_btn"));
	                    if (!approveButtons.isEmpty() && approveButtons.get(0).isDisplayed()) {
	                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", approveButtons.get(0));
	                        Thread.sleep(500);
	                        approveButtons.get(0).click();
	                        found = true;
	                        break;
	                    }
	                }
	            }

	            if (found) return true;

	            // Scroll to load more rows
	            js.executeScript("window.scrollBy(0, 500);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("No matching row with Approve button found for date: " + excelDate);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean clickRejectButtonByDate(String excelDate) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Convert Excel date "2025-08-04" → "04-08-2025"
	        String formattedDate = excelDate.substring(8, 10) + "-" +
	                               excelDate.substring(5, 7) + "-" +
	                               excelDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;
	        boolean found = false;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

	            for (WebElement row : rows) {
	                // Adjusted to correct date column index (3rd td)
	                List<WebElement> dateCells = row.findElements(By.cssSelector("td"));
	                if (dateCells.size() < 3) continue;

	                String leaveDate = dateCells.get(2).getText().trim();
	                leaveDate = leaveDate.replaceAll("^[A-Za-z]{3},\\s*", "");

	                if (leaveDate.equals(formattedDate)) {
	                    // Search for Reject button dynamically
	                    List<WebElement> rejectButtons = row.findElements(By.cssSelector(".reject_btn"));
	                    if (!rejectButtons.isEmpty() && rejectButtons.get(0).isDisplayed()) {
	                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", rejectButtons.get(0));
	                        Thread.sleep(500);
	                        rejectButtons.get(0).click();
	                        found = true;
	                        break;
	                    }
	                }
	            }

	            if (found) return true;

	            // Scroll down to load more rows dynamically
	            js.executeScript("window.scrollBy(0, 500);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("No matching row with Reject button found for date: " + excelDate);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}






	
	
	
	
	
	public boolean LeaveRejectionReason(String reason)
	{
		try {
			utils.waitForEle(LeaveRejectionReason, "visible", "", 10);
			
			LeaveRejectionReason.clear();
			LeaveRejectionReason.sendKeys(reason);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LeaveRejectButton()
	{
		try {
			utils.waitForEle(LeaveRejectButton, "visible", "", 10);
			
			LeaveRejectButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LeaveApproveButton()
	{
		try {
			utils.waitForEle(LeaveApproveButton, "visible", "", 10);
			
			LeaveApproveButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	public boolean LeaveModulePaginationInAdmin()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(LeaveModulePaginationInAdmin, "visible", "", 10);
			
		Select sel = new Select(LeaveModulePaginationInAdmin);
		sel.selectByVisibleText("100");
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveModulePaginationInEmp()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(LeaveModulePaginationInEmp, "visible", "", 10);
			
		Select sel = new Select(LeaveModulePaginationInEmp);
		sel.selectByVisibleText("100");
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
//2nd TestCase
	public boolean clickRevokeButtonByDate(String excelDate) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Convert "2025-08-04" → "04-08-2025"
	        String formattedDate = excelDate.substring(8, 10) + "-"
	                             + excelDate.substring(5, 7) + "-"
	                             + excelDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;
	        boolean found = false;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dtMyLeaveRequest tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("#dtMyLeaveRequest tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    // Get "From" date from column (check your table structure)
	                    String fromDate = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
	                    fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();

	                    // Match formatted date
	                    if (fromDate.equals(formattedDate)) {
	                        List<WebElement> revokeButtons = row.findElements(By.cssSelector(".revoke_btn"));
	                        if (!revokeButtons.isEmpty() && revokeButtons.get(0).isDisplayed()) {
	                            js.executeScript("arguments[0].scrollIntoView(true);", revokeButtons.get(0));
	                            Thread.sleep(800);
	                            revokeButtons.get(0).click();
	                            found = true;
	                            break;
	                        }
	                    }
	                } catch (StaleElementReferenceException ignored) {}
	            }

	            if (found) return true;

	            // Scroll for more rows
	            js.executeScript("window.scrollBy(0, 400);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("No matching row with Revoke button found for date: " + excelDate);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean RevokeButton()
	{
		try {
			utils.waitForEle(RevokeButton, "visible", "", 10);
			
			RevokeButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//5th TestCase
	
	public boolean LeaveForOthersButton()
	{
		try {
			utils.waitForEle(LeaveForOthersButton, "visible", "", 10);
			
			LeaveForOthersButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeSelectionDropDown()
	{
		try {
			utils.waitForEle(EmployeeSelectionDropDown, "visible", "", 10);
			
			EmployeeSelectionDropDown.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeInputField(String empname)
	{
		try {
			utils.waitForEle(EmployeeInputField, "visible", "", 10);
			
			EmployeeInputField.clear();
			EmployeeInputField.sendKeys(empname);
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpNameDisplayedAndSelected()
	{
		try {
			utils.waitForEle(EmpNameDisplayedAndSelected, "visible", "", 10);
			
			EmpNameDisplayedAndSelected.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeDropDownOnAdmin()
	{
		try {
			utils.waitForEle(LeaveTypeDropDownOnAdmin, "visible", "", 10);
			
			LeaveTypeDropDownOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeInputtedOnAdmin(String leavetype)
	{
		try {
			utils.waitForEle(LeaveTypeInputtedOnAdmin, "visible", "", 10);
			
			LeaveTypeInputtedOnAdmin.clear();
			LeaveTypeInputtedOnAdmin.sendKeys(leavetype);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectedOnAdmin()
	{
		try {
			utils.waitForEle(LeaveTypeSelectedOnAdmin, "visible", "", 10);
			
			LeaveTypeSelectedOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateOnAdmin()
	{
		try {
			utils.waitForEle(FromDateOnAdmin, "visible", "", 10);
			
			FromDateOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateOnAdminSelected(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDate')._flatpickr) {" +
		            "  document.getElementById('fromDate')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean FromLeaveDurationDropdownAdmin()
	{
		try {
			utils.waitForEle(FromLeaveDurationDropdownAdmin, "visible", "", 10);
			
			FromLeaveDurationDropdownAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromLeaveDurationSelectedInAdmin()
	{
		try {
			utils.waitForEle(FromLeaveDurationSelectedInAdmin, "visible", "", 10);
			
			FromLeaveDurationSelectedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextFieldInAdmin()
	{
		try {
			utils.waitForEle(ToDateTextFieldInAdmin, "visible", "", 10);
			
			ToDateTextFieldInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextFieldInAdminSelected(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDate')._flatpickr) {" +
		            "  document.getElementById('toDate')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean ToLeaveDurationDropDownInAdmin()
	{
		try {
			utils.waitForEle(ToLeaveDurationDropDownInAdmin, "visible", "", 10);
			
			ToLeaveDurationDropDownInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToLeaveDurationFullDaySelectedInAdmin()
	{
		try {
			utils.waitForEle(ToLeaveDurationFullDaySelectedInAdmin, "visible", "", 10);
			
			ToLeaveDurationFullDaySelectedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ApplyForOthersButton()
	{
		try {
			utils.waitForEle(ApplyForOthersButton, "visible", "", 10);
			
			ApplyForOthersButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//6th TestCase
	
	public boolean ApplyForMeButton()
	{
		try {
			utils.waitForEle(ApplyForMeButton, "visible", "", 10);
			
			ApplyForMeButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectionDropDownOfLeaveForMe()
	{
		try {
			utils.waitForEle(LeaveTypeSelectionDropDownOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeSelectionDropDownOfLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeNameTextFieldOfLeaveForMe(String LeaveType)
	{
		try {
			utils.waitForEle(LeaveTypeNameTextFieldOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeNameTextFieldOfLeaveForMe.clear();
			LeaveTypeNameTextFieldOfLeaveForMe.sendKeys( LeaveType);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectedOfLeaveForMe()
	{
		try {
			utils.waitForEle(LeaveTypeSelectedOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeSelectedOfLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateClickedLeaveForMe()
	{
		try {
			utils.waitForEle(FromDateClickedLeaveForMe, "visible", "", 10);
			
			FromDateClickedLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateSelectedLeaveForMe(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDateForMe').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDateForMe')._flatpickr) {" +
		            "  document.getElementById('fromDateForMe')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDateForMe'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean FromLeaveDurationSelectedOfLeaveForMe(String LeaveDuration)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(FromLeaveDurationSelectedOfLeaveForMe, "visible", "", 10);
			
		Select sel = new Select(FromLeaveDurationSelectedOfLeaveForMe);
		sel.selectByVisibleText(LeaveDuration);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateClickedLeaveForMe()
	{
		try {
			utils.waitForEle(ToDateClickedLeaveForMe, "visible", "", 10);
			
			ToDateClickedLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateSelectedLeaveForMe(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDateForMe').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDateForMe')._flatpickr) {" +
		            "  document.getElementById('toDateForMe')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDateForMe'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean ToLeaveDurationSelectedOfLeaveForMe(String LeaveDuration)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(ToLeaveDurationSelectedOfLeaveForMe, "visible", "", 10);
			
		Select sel = new Select(ToLeaveDurationSelectedOfLeaveForMe);
		sel.selectByVisibleText(LeaveDuration);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveReasonOfLeaveForMe(String LeaveReason)
	{
		try {
			utils.waitForEle(LeaveReasonOfLeaveForMe, "visible", "", 10);
			
			LeaveReasonOfLeaveForMe.clear();
			LeaveReasonOfLeaveForMe.sendKeys( LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApplyButtonOfLeaveForMe()
	{
		try {
			utils.waitForEle(ApplyButtonOfLeaveForMe, "visible", "", 10);
			
			ApplyButtonOfLeaveForMe.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	//7th TestCase
	
	public boolean EmpIDCheckBoxSearchOption()
	{
		try {
			utils.waitForEle(EmpIDCheckBoxSearchOption, "visible", "", 10);
			
			EmpIDCheckBoxSearchOption.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ReasonCheckBoxSearchOption()
	{
		try {
			utils.waitForEle(ReasonCheckBoxSearchOption, "visible", "", 10);
			
			ReasonCheckBoxSearchOption.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpIDInTable(String Empid) {
	    try {
	        utils.waitForEle(EmpIDInTable, "visible", "", 10);

	        String actualText = EmpIDInTable.getText().trim();
	        if (actualText.contains(Empid)) {
	            return true;
	            
	        } else {
	            throw new Exception("Employee ID not found in table. Expected: " + Empid + ", Found: " + actualText);
	        }
	       

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean ReasonInTable(String reason) {
	    try {
	        utils.waitForEle(ReasonInTable, "visible", "", 10);

	        String actualText = ReasonInTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Request Reason not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	//8th TestCase
	public boolean FilterButton()
	{
		try {
			utils.waitForEle(FilterButton, "visible", "", 10);
			
			FilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeDropDownSelected(String officename)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(OfficeDropDownSelected, "visible", "", 10);
			
			Select sel = new Select(OfficeDropDownSelected);
			sel.deselectByVisibleText(officename);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeptDropDownClicked()
	{
		try {
			utils.waitForEle(DeptDropDownClicked, "visible", "", 10);
			
			DeptDropDownClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeptDropDownSelected()
	{
		try {
			utils.waitForEle(DeptDropDownSelected, "visible", "", 10);
			
			DeptDropDownSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDownClicked()
	{
		try {
			utils.waitForEle(TeamDropDownClicked, "visible", "", 10);
			
			TeamDropDownClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDownSelected()
	{
		try {
			utils.waitForEle(TeamDropDownSelected, "visible", "", 10);
			
			TeamDropDownSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SaveFilterButton()
	{
		try {
			utils.waitForEle(SaveFilterButton, "visible", "", 10);
			
			SaveFilterButton.click();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean FilterResults() {
	    try {
	        utils.waitForEle(FilterResults, "visible", "", 10);

	        if (FilterResults.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("FilterResults element is not displayed.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean LeaveTypesFilter(String LeaveType) {
	    try {
	        // Scroll into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", LeaveTypesFilter);

	        // Wait until dropdown is visible
	        utils.waitForEle(LeaveTypesFilter, "visible", "", 10);

	        // Select option by visible text
	        Select sel = new Select(LeaveTypesFilter);
	        sel.selectByVisibleText(LeaveType);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean LeaveStatusFilter(String LeaveStatus) {
	    try {
	        // Scroll into view
	    	Thread.sleep(3000);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", LeaveStatusFilter);

	        // Wait until dropdown is visible
	   
	        utils.waitForEle(LeaveStatusFilter, "visible", "", 10);
	        Thread.sleep(3000);
	        // Select option by visible text
	        Select sel = new Select(LeaveStatusFilter);
	        sel.selectByVisibleText(LeaveStatus);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean LeaveTypesearchResults(String LeaveType) {
	    try {
	        // Small wait to ensure elements are present
	        Thread.sleep(2000);

	        for (WebElement leave : LeaveTypesearchResults) {
	            if (leave.getText().trim().toLowerCase().contains(LeaveType.toLowerCase())) {
	                return true; // Found matching leave type (partial match)
	            }
	        }

	        throw new Exception("No matching leave type found: " + LeaveType);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	
	public boolean LeaveStatusSearchResults(String Leavestatus) {
	    try {
	        // Small wait to ensure elements are loaded
	        Thread.sleep(2000);

	        for (WebElement statusElement : LeaveStatusSearchResults) {
	            if (statusElement.getText().trim().toLowerCase().contains(Leavestatus.toLowerCase())) {
	                return true; // Found matching leave status (partial match)
	            }
	        }

	        throw new Exception("No matching leave status found: " + Leavestatus);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean OfficeDrpDownClicked()
	{
		try {
			utils.waitForEle(OfficeDrpDownClicked, "visible", "", 10);
			
			OfficeDrpDownClicked.click();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean OfficeNameInputted(String officename)
	{
		try {
			utils.waitForEle(OfficeNameInputted, "visible", "", 10);
			
			OfficeNameInputted.sendKeys(officename);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OfficeNameSelectedOfFilter()
	{
		try {
			utils.waitForEle(OfficeNameSelectedOfFilter, "visible", "", 10);
			
			OfficeNameSelectedOfFilter.click();
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean compareLeaveStatistics() {
	    try {
	        // --- Step 1: Get UI Statistics ---
	        int uiApplied = Integer.parseInt(driver.findElement(By.id("AppliedLeaves")).getText().trim());
	        int uiPending = Integer.parseInt(driver.findElement(By.id("PendingLeaves")).getText().trim());
	        int uiApproved = Integer.parseInt(driver.findElement(By.id("ApprovedLeaves")).getText().trim());
	        int uiRejected = Integer.parseInt(driver.findElement(By.id("RejectedLeaves")).getText().trim());
	        int uiRevoked = Integer.parseInt(driver.findElement(By.id("RevokedLeaves")).getText().trim());
	        int uiCancelled = Integer.parseInt(driver.findElement(By.id("CancelledLeaves")).getText().trim());
	        
	        System.out.println(uiApplied + "Applied");
	        System.out.println(uiPending + "pending");
	        System.out.println(uiApproved + "Approved");
	        System.out.println(uiRejected + "Rejected");
	        System.out.println(uiRevoked + "Revoked");
	       

	        // --- Step 2: Get Table Data ---
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtLeave']/tbody/tr/td[8]"));
	        int tableTotal = statusCells.size();
	        int tablePending = 0, tableApproved = 0, tableRejected = 0, tableRevoked = 0, tableCancelled = 0;

	        for (WebElement cell : statusCells) {
	            String status = cell.getText().trim().toLowerCase();
	            if (status.contains("pending")) tablePending++;
	            else if (status.contains("approved")) tableApproved++;
	            else if (status.contains("rejected")) tableRejected++;
	            else if (status.contains("revoked")) tableRevoked++;
	            else if (status.contains("cancelled")) tableCancelled++;
	        }

	        // --- Step 3: Comparison ---
	        boolean allMatch = true;
	        if (uiApplied != tableTotal) {
	            System.out.println("Mismatch: Applied Leaves UI=" + uiApplied + ", Table=" + tableTotal);
	            allMatch = false;
	        }
	        if (uiPending != tablePending) {
	            System.out.println("Mismatch: Pending UI=" + uiPending + ", Table=" + tablePending);
	            allMatch = false;
	        }
	        if (uiApproved != tableApproved) {
	            System.out.println("Mismatch: Approved UI=" + uiApproved + ", Table=" + tableApproved);
	            allMatch = false;
	        }
	        if (uiRejected != tableRejected) {
	            System.out.println("Mismatch: Rejected UI=" + uiRejected + ", Table=" + tableRejected);
	            allMatch = false;
	        }
	        if (uiRevoked != tableRevoked) {
	            System.out.println("Mismatch: Revoked UI=" + uiRevoked + ", Table=" + tableRevoked);
	            allMatch = false;
	        }
	        if (uiCancelled != tableCancelled) {
	            System.out.println("Mismatch: Cancelled UI=" + uiCancelled + ", Table=" + tableCancelled);
	            allMatch = false;
	        }

	        if (allMatch) {
	            System.out.println("Comparison Passed: UI and Table values match.");
	        } else {
	            System.out.println("Comparison Failed: UI and Table values do not match.");
	        }

	        return allMatch;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("Error in compareLeaveStatistics: " + e.getMessage());
	        return false;
	    }
	}





	
	public Map<String, Integer> getLeaveStatusCounts() {
	    Map<String, Integer> statusCountMap = new HashMap<>();
	    try {
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtLeave']/tbody/tr/td[8]"));

	        for (WebElement cell : statusCells) {
	            String statusText = cell.getText().trim();
	            statusCountMap.put(statusText, statusCountMap.getOrDefault(statusText, 0) + 1);
	        }

	        System.out.println("Table Status Counts: " + statusCountMap);

	    } catch (Exception e) {
	        System.out.println("Error fetching table counts: " + e.getMessage());
	        exceptionDesc = e.getMessage();
	    }
	    return statusCountMap;
	}
	
	
	
	public boolean MonthlyLeaveSummaryDrpIcon()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(MonthlyLeaveSummaryDrpIcon, "visible", "", 10);
			
			MonthlyLeaveSummaryDrpIcon.click();
			
			utils.waitForEle(StatisticsDisplayed, "visible", "", 10);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean compareLeaveStatisticsOfEmp() {
	    try {
	        // --- Step 1: Get UI Statistics ---
	        int uiApplied = Integer.parseInt(driver.findElement(By.id("AppliedLeaves")).getText().trim());
	        int uiPending = Integer.parseInt(driver.findElement(By.id("PendingLeaves")).getText().trim());
	        int uiApproved = Integer.parseInt(driver.findElement(By.id("ApprovedLeaves")).getText().trim());
	        int uiRejected = Integer.parseInt(driver.findElement(By.id("RejectedLeaves")).getText().trim());
	        int uiRevoked = Integer.parseInt(driver.findElement(By.id("RevokedLeaves")).getText().trim());
	        int uiCancelled = Integer.parseInt(driver.findElement(By.id("CancelledLeaves")).getText().trim());
	        
	        System.out.println(uiApplied + "Applied");
	        System.out.println(uiPending + "pending");
	        System.out.println(uiApproved + "Approved");
	        System.out.println(uiRejected + "Rejected");
	        System.out.println(uiRevoked + "Revoked");
	       

	        // --- Step 2: Get Table Data ---
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtMyLeaveRequest']/tbody/tr/td[7]"));
	        int tableTotal = statusCells.size();
	        int tablePending = 0, tableApproved = 0, tableRejected = 0, tableRevoked = 0, tableCancelled = 0;

	        for (WebElement cell : statusCells) {
	            String status = cell.getText().trim().toLowerCase();
	            if (status.contains("pending")) tablePending++;
	            else if (status.contains("approved")) tableApproved++;
	            else if (status.contains("rejected")) tableRejected++;
	            else if (status.contains("revoked")) tableRevoked++;
	            else if (status.contains("cancelled")) tableCancelled++;
	        }

	        // --- Step 3: Comparison ---
	        boolean allMatch = true;
	        if (uiApplied != tableTotal) {
	            System.out.println("Mismatch: Applied Leaves UI=" + uiApplied + ", Table=" + tableTotal);
	            allMatch = false;
	        }
	        if (uiPending != tablePending) {
	            System.out.println("Mismatch: Pending UI=" + uiPending + ", Table=" + tablePending);
	            allMatch = false;
	        }
	        if (uiApproved != tableApproved) {
	            System.out.println("Mismatch: Approved UI=" + uiApproved + ", Table=" + tableApproved);
	            allMatch = false;
	        }
	        if (uiRejected != tableRejected) {
	            System.out.println("Mismatch: Rejected UI=" + uiRejected + ", Table=" + tableRejected);
	            allMatch = false;
	        }
	        if (uiRevoked != tableRevoked) {
	            System.out.println("Mismatch: Revoked UI=" + uiRevoked + ", Table=" + tableRevoked);
	            allMatch = false;
	        }
	        if (uiCancelled != tableCancelled) {
	            System.out.println("Mismatch: Cancelled UI=" + uiCancelled + ", Table=" + tableCancelled);
	            allMatch = false;
	        }

	        if (allMatch) {
	            System.out.println("Comparison Passed: UI and Table values match.");
	        } else {
	            System.out.println("Comparison Failed: UI and Table values do not match.");
	        }

	        return allMatch;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("Error in compareLeaveStatistics: " + e.getMessage());
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = exceptionDesc;
	}
	
	
}
