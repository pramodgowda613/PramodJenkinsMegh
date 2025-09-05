package com.MeghPI.Attendance.pages;


import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;


public class MeghPINormalAttendancePage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String selectedentityname = "";
	
	public MeghPINormalAttendancePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[1]")
	private WebElement EmpRecordsOnAttendance; //1stTestCase
	
	@FindBy(xpath = "//button[text()='Apply Regularization']")
	private WebElement ApplyRegularizationButton; //1stTestCase
	
	@FindBy(xpath = "//a[text()='Regularization for Others']")
	private WebElement ApplyRegularizationForOthersButton; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpEntityQuickOther-container']")
	private WebElement EmployeeSelectionDropDown; //1stTestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpEntityQuickOther-results']")
	private WebElement EmployeeSelectionInputField; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpEntityQuickOther-results']/li[1]")
	private WebElement EmployeeSelectedFromDrp; //1stTestCase
	
	@FindBy(xpath = "//input[@id='PunchQuickOtherInDate']/../input[2]")
	private WebElement RegularizationFromDate; //1stTestCase
	
	@FindBy(xpath = "//input[@id='RegQuickOtherInTime']/../input[2]")
	private WebElement RegularizationFromTime; //1stTestCase
	
	@FindBy(xpath = "//input[@id='PunchQuickOtherOutDate']/../input[2]")
	private WebElement RegularizationToDate; //1stTestCase
	
	@FindBy(xpath = "//input[@id='RegQuickOtherOutTime']/../input[2]")
	private WebElement RegularizationToTime; //1stTestCase
	
	@FindBy(xpath = "//select[@id='regularizationQuickOtherType']")
	private WebElement RegularizationType; //1stTestCase
	
	@FindBy(xpath = "//input[@id='regularizationQuickOtherReason']")
	private WebElement RegularizationReason; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnRegularizationQuickOtherApply']")
	private WebElement RegularizationApplyButton; //1stTestCase
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean EmpRecordsOnAttendance() {
		try {

			utils.waitForEle(EmpRecordsOnAttendance, "visible", "", 10);
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApplyRegularizationButton() {
		try {

			utils.waitForEle(ApplyRegularizationButton, "visible", "", 10);
			ApplyRegularizationButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApplyRegularizationForOthersButton() {
		try {

			utils.waitForEle(ApplyRegularizationForOthersButton, "visible", "", 10);
			ApplyRegularizationForOthersButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeSelectionDropDown() {
		try {

			utils.waitForEle(EmployeeSelectionDropDown, "visible", "", 10);
			EmployeeSelectionDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeSelectionInputField(String Empname) {
		try {

			utils.waitForEle(EmployeeSelectionInputField, "visible", "", 10);
		
			EmployeeSelectionInputField.sendKeys(Empname);
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeSelectedFromDrp() {
		try {

			utils.waitForEle(EmployeeSelectedFromDrp, "visible", "", 30);
			EmployeeSelectedFromDrp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationFromDateClick() {
		try {

			utils.waitForEle(RegularizationFromDate, "visible", "", 10);
			RegularizationFromDate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationFromDateInputted(String RegularizationFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('PunchQuickOtherInDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('PunchQuickOtherInDate')._flatpickr) {" +
		            "  document.getElementById('PunchQuickOtherInDate')._flatpickr.setDate('" + RegularizationFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on PunchQuickOtherInDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean RegularizationFromTimeClicked() {
		try {

			utils.waitForEle(RegularizationFromTime, "visible", "", 10);
			RegularizationFromTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationFromTimeInputted(String RegularizationFromTime) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('RegQuickOtherInTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('RegQuickOtherInTime')._flatpickr) {" +
		            "  document.getElementById('RegQuickOtherInTime')._flatpickr.setDate('" + RegularizationFromTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on RegQuickOtherInTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean RegularizationToDateClicked() {
		try {

			utils.waitForEle(RegularizationToDate, "visible", "", 10);
			RegularizationToDate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationToDateInputted(String RegularizationToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('PunchQuickOtherOutDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('PunchQuickOtherOutDate')._flatpickr) {" +
		            "  document.getElementById('PunchQuickOtherOutDate')._flatpickr.setDate('" + RegularizationToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on PunchQuickOtherOutDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean RegularizationToTimeClicked() {
		try {
Thread.sleep(4000);
			utils.waitForEle(RegularizationToTime, "visible", "", 10);
			RegularizationToTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationToTimeInputted(String RegularizationToTime) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('RegQuickOtherOutTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('RegQuickOtherOutTime')._flatpickr) {" +
		            "  document.getElementById('RegQuickOtherOutTime')._flatpickr.setDate('" + RegularizationToTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on PunchQuickOtherOutDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean RegularizationType() {
		try {

			Thread.sleep(4000);
			utils.waitForEle(RegularizationType, "visible", "", 10);
			Select sel = new Select(RegularizationType);
			sel.selectByVisibleText("Device not Working");
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean RegularizationReason(String reason) {
		try {

			utils.waitForEle(RegularizationReason, "visible", "", 10);
			RegularizationReason.clear();
			RegularizationReason.sendKeys(reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationApplyButton() {
		try {

			utils.waitForEle(RegularizationApplyButton, "visible", "", 10);
			RegularizationApplyButton.click();
			
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
		exceptionDesc = exceptionDesc;
	}
	
}
