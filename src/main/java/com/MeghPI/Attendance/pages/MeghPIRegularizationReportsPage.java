package com.MeghPI.Attendance.pages;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;


public class MeghPIRegularizationReportsPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String selectedentityname = "";
	
	public MeghPIRegularizationReportsPage(WebDriver driver) {
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
	
	@FindBy(xpath = "//a[text()='Apply Regularization']")
	private WebElement ApplyRegularizationButtonEmp; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='PunchInDate']/../input[2]")
	private WebElement EmpRegularizationInDate; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='RegQuickInTime']/../input[2]")
	private WebElement EmpRegularizationInTime; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='PunchOutDate']/../input[2]")
	private WebElement EmpRegularizationOutDate; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='RegQuickOutTime']/../input[2]")
	private WebElement EmpRegularizationOutTime; //1stTestCase All Reports
	
	@FindBy(xpath = "//select[@id='regularizationQuickType']")
	private WebElement EmpRegularizationType; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='regularizationQuickReason']")
	private WebElement EmpRegularizationReason; //1stTestCase All Reports
	
	@FindBy(xpath = "//button[@id='btnRegularizationQuickApply']")
	private WebElement EmpRegularizationApplySaveButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//p[text()='Regularization Report']")
	private WebElement RegularizationReportButton; //1stTestCase All Reports
	
	@FindBy(xpath = "(//a[@id='RegularizationReport_Tab'])[1]")
	private WebElement RegularizationAllReportButton; //1stTestCase All Reports
	
	@FindBy(xpath = "(//input[@aria-controls='dtRegularizationReport'])[1]")
	private WebElement RegularizationAllReportSearchTextField; //1stTestCase All Reports
	
	@FindBy(xpath = "//label[text()='Employee ID']")
	private WebElement RegularizationAllReportEmpIdCheckBox; //1stTestCase All Reports
	
	@FindBy(xpath = "(//label[text()='Status'])[1]")
	private WebElement RegularizationAllReportStatusCheckBox; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement RegularizationAllReportEmpIdSearchResult; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[2]")
	private WebElement RegularizationAllReportDateSearchResult; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[6]")
	private WebElement RegularizationAllReportStatusSearchResult; //1stTestCase All Reports
	
	@FindBy(xpath = "(//button[@id='btnFilterRegularizationReport'])[2]")
	private WebElement RegularizationAllReportFilterButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement RegularizationAllReportSearchResult; //1stTestCase All Reports
	
	@FindBy(xpath = "(//span[@id='showMonthyear'])[2]")
	private WebElement MonthFilterButton; //1stTestCase All Reports
	
	@FindBy(xpath = "(//button[@id='btnPrevious'])[2]")
	private WebElement PreviousMonthSelectionButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@aria-controls='dtRegularizationRquest']")
	private WebElement RegularizationRequestSearchTextField; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> RegularizationRequestSearchResultEmpID; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr/td[2]")
	private List<WebElement> RegularizationRequestSearchResultdate; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr/td[6]")
	private List<WebElement> RegularizationRequestSearchResultStatus; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[1]")
	private List<WebElement> RegularizationRequestSearchResultAdminSideEmpName; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[2]")
	private List<WebElement> RegularizationRequestSearchResultAdminSideDate; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[6]/div/button[2]")
	private List<WebElement> RegularizationRequestSearchResultAdminSideApprovedButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//button[@id='btnApproveRequest']")
	private WebElement ApproveButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[6]/div/button[1]")
	private List<WebElement> RegularizationRequestSearchResultAdminSideRejectButton; //1stTestCase All Reports
	
	@FindBy(xpath = "//input[@id='txtRejectReason']")
	private WebElement RejectReasonTextField; //1stTestCase All Reports
	
	@FindBy(xpath = "//button[@id='btnRejectRequest']")
	private WebElement RejectButton; //1stTestCase All Reports
	
	@FindBy(xpath = "(//a[@id='RegularizationReport_Tab'])[2]")
	private WebElement RegulirizationApprovedTab; //1stTestCase Approved Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement RegulirizationApprovedTabEmpNameRow; //1stTestCase Approved Reports
	
	@FindBy(xpath = "(//a[@id='RegularizationReport_Tab'])[3]")
	private WebElement RegulirizationRejectedTab; //1stTestCase Rejected Reports
	
	@FindBy(xpath = "(//a[@id='RegularizationReport_Tab'])[4]")
	private WebElement RegulirizationPendingTab; //1stTestCase Rejected Reports
	
	
	@FindBy(xpath = "//button[text()='Apply Regularization']")
	private WebElement RegulirizationRequestTabOnAdmin; //1stTestCase Rejected Reports
	
	@FindBy(xpath = "//a[text()='Regularization for Me']")
	private WebElement RegulirizationRequestFormeOnAdmin; //1stTestCase Rejected Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr/td[2]")
	private List<WebElement> RegularizationDateList; //1stTestCase All Reports
	
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[1]")
	private WebElement RegularizationAllReportDateSearchResultOnEmp; //1stTestCase All Reports
	
	@FindBy(xpath = "//table[@id='dtRegularizationReport']/tbody/tr[1]/td[5]")
	private WebElement RegularizationAllReportStatusSearchResultOnEmp; //1stTestCase All Reports
	
	
	
	
	
	
	public boolean RegulirizationRequestTabOnAdmin() {
		try {

			utils.waitForEle(RegulirizationRequestTabOnAdmin, "visible", "", 20);
			RegulirizationRequestTabOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegulirizationRequestFormeOnAdmin() {
		try {

			utils.waitForEle(RegulirizationRequestFormeOnAdmin, "visible", "", 20);
			RegulirizationRequestFormeOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
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
	
	//Regulirization request
	
	public boolean ApplyRegularizationButtonEmp() {
		try {

			utils.waitForEle(ApplyRegularizationButtonEmp, "visible", "", 10);
			ApplyRegularizationButtonEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationInDate() {
		try {

			utils.waitForEle(EmpRegularizationInDate, "visible", "", 10);
			EmpRegularizationInDate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationInDateInputted(String RegularizationFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('PunchInDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('PunchInDate')._flatpickr) {" +
		            "  document.getElementById('PunchInDate')._flatpickr.setDate('" + RegularizationFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on PunchInDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean EmpRegularizationInTime() {
		try {

			utils.waitForEle(EmpRegularizationInTime, "visible", "", 10);
			EmpRegularizationInTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationInTimeInputted(String RegularizationFromTime) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('RegQuickInTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('RegQuickInTime')._flatpickr) {" +
		            "  document.getElementById('RegQuickInTime')._flatpickr.setDate('" + RegularizationFromTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on RegQuickInTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean EmpRegularizationOutDate() {
		try {

			utils.waitForEle(EmpRegularizationOutDate, "visible", "", 10);
			EmpRegularizationOutDate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationOutDateInputted(String RegularizationToDate) {
		 try {
			
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('PunchOutDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('PunchOutDate')._flatpickr) {" +
		            "  document.getElementById('PunchOutDate')._flatpickr.setDate('" + RegularizationToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on PunchOutDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean EmpRegularizationOutTime() {
		try {

			utils.waitForEle(EmpRegularizationOutTime, "visible", "", 10);
			EmpRegularizationOutTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationOutTimeInputted(String RegularizationOutTime) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Ensure the field is editable
	        js.executeScript("document.getElementById('RegQuickOutTime').removeAttribute('readonly');");

	        // Set time via Flatpickr API
	        js.executeScript(
	            "if (document.getElementById('RegQuickOutTime')._flatpickr) {" +
	            "  document.getElementById('RegQuickOutTime')._flatpickr.setDate(arguments[0], true);" +
	            "} else { throw new Error('Flatpickr not initialized on RegQuickOutTime'); }",
	            RegularizationOutTime
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean EmpRegularizationType() {
		try {

			Thread.sleep(4000);
			utils.waitForEle(EmpRegularizationType, "visible", "", 10);
			Select sel = new Select(EmpRegularizationType);
			sel.selectByVisibleText("Device not Working");
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationReason(String reason) {
		try {

			utils.waitForEle(EmpRegularizationReason, "visible", "", 10);
			EmpRegularizationReason.clear();
			EmpRegularizationReason.sendKeys(reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpRegularizationApplySaveButton() {
		try {

			utils.waitForEle(EmpRegularizationApplySaveButton, "visible", "", 10);
			EmpRegularizationApplySaveButton.click();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//1st All report
	public boolean RegularizationReportButton() {
		try {
			Thread.sleep(2000);
			utils.waitForEle(RegularizationReportButton, "visible", "", 15);
			RegularizationReportButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportButton() {
		try {

			utils.waitForEle(RegularizationAllReportButton, "visible", "", 15);
			RegularizationAllReportButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportSearchTextField(String empid) {
		try {

			utils.waitForEle(RegularizationAllReportSearchTextField, "visible", "", 15);
			RegularizationAllReportSearchTextField.clear();
			RegularizationAllReportSearchTextField.sendKeys(empid);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportEmpIdCheckBox() {
		try {

			utils.waitForEle(RegularizationAllReportEmpIdCheckBox, "visible", "", 15);
			RegularizationAllReportEmpIdCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportStatusCheckBox() {
		try {

			utils.waitForEle(RegularizationAllReportStatusCheckBox, "visible", "", 15);
			RegularizationAllReportStatusCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportFilterButton() {
		try {

			utils.waitForEle(RegularizationAllReportFilterButton, "visible", "", 15);
			RegularizationAllReportFilterButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportDateSearchResult(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(RegularizationAllReportDateSearchResult, "visible", "", 15);

	        // Input date is yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);

	        // Convert to UI date format (dd-MM-yyyy)
	        String expectedUiDate = parsedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get actual text from UI (e.g. "Tue, 28-10-2025")
	        String actualText = RegularizationAllReportDateSearchResult.getText().trim();

	        // ✅ Check if UI text contains the formatted date (ignore weekday)
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean RegularizationAllReportEmpIdSearchResult(String empid) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(RegularizationAllReportEmpIdSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = RegularizationAllReportEmpIdSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(empid)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + empid +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean RegularizationAllReportStatusSearchResult(String status) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(RegularizationAllReportStatusSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = RegularizationAllReportStatusSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(status)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + status +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean RegularizationAllReportSearchResult() {
		try {

			utils.waitForEle(RegularizationAllReportSearchResult, "visible", "", 15);
			RegularizationAllReportSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAllReportMonthFilterButton(String expectedMonth) {
	    try {
	        utils.waitForEle(MonthFilterButton, "visible", "", 15);

	        String displayedMonthYear = MonthFilterButton.getText().trim();
	        int maxTries = 12;
	        int tries = 0;

	        while (!displayedMonthYear.toLowerCase().contains(expectedMonth.toLowerCase()) 
	                && tries < maxTries) {

	            // Click previous month button
	            PreviousMonthSelectionButton.click();
	            Thread.sleep(1200);

	            // Re–fetch updated month from MonthFilterButton (NOT previous button)
	            utils.waitForEle(MonthFilterButton, "visible", "", 10);
	            displayedMonthYear = MonthFilterButton.getText().trim();

	            // Handle cases where UI returns empty text on first attempt
	            if (displayedMonthYear.isEmpty()) {
	                Thread.sleep(500);
	                displayedMonthYear = MonthFilterButton.getText().trim();
	            }

	            tries++;
	        }

	        // Final validation
	        if (displayedMonthYear.toLowerCase().contains(expectedMonth.toLowerCase())) {
	            return true;
	        } else {
	            exceptionDesc = "Expected month not found. Expected: " 
	                    + expectedMonth + " but found: " + displayedMonthYear;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	public boolean RegularizationRequestSearchTextField(String Date) {
		try {

			utils.waitForEle(RegularizationRequestSearchTextField, "visible", "", 15);
			RegularizationRequestSearchTextField.clear();
			RegularizationRequestSearchTextField.sendKeys(Date);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ValidationEmpWithDateAndStatus(String status, String expectedDate, String expectedEmpName) {
	    try {
	        // Wait for all required elements to load
	        Thread.sleep(2000);

	        // Convert expected date (yyyy-MM-dd) → dd-MM-yyyy
	        String expectedUiDate = "";
	        try {
	            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            LocalDate parsed = LocalDate.parse(expectedDate, inputFmt);
	            expectedUiDate = parsed.format(uiFmt);
	        } catch (Exception e) {
	            System.out.println("⚠️ Date format conversion failed, using raw input date.");
	            expectedUiDate = expectedDate;
	        }

	        int rowCount = RegularizationRequestSearchResultEmpID.size();

	        for (int i = 0; i < rowCount; i++) {
	            String empText = RegularizationRequestSearchResultEmpID.get(i).getText().trim();
	            String dateText = RegularizationRequestSearchResultdate.get(i).getText().trim();
	            String statusText = RegularizationRequestSearchResultStatus.get(i).getText().trim();

	            // Normalize UI date (handle formats like "Tue, 28-10-2025")
	            if (dateText.contains(",")) {
	                dateText = dateText.substring(dateText.indexOf(",") + 1).trim();
	            }

	            // ✅ Check Employee Name
	            if (!empText.equalsIgnoreCase(expectedEmpName) && 
	                !empText.toLowerCase().contains(expectedEmpName.toLowerCase())) {
	                continue;
	            }

	            // ✅ Check Date match
	            if (!dateText.equals(expectedUiDate)) {
	                continue;
	            }

	            // ✅ Check Status match
	            if (statusText.equalsIgnoreCase(status) || 
	                statusText.toLowerCase().contains(status.toLowerCase())) {
	                System.out.println("✅ Match found -> Emp: " + empText + ", Date: " + dateText + ", Status: " + statusText);
	                return true;
	            }
	        }

	        // ❌ No match found
	        exceptionDesc = "No matching record found for Employee: " + expectedEmpName
	                + ", Date: " + expectedUiDate + ", Status: " + status;
	        System.out.println(exceptionDesc);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean AdminApprovedToRegulirization(String expectedDate, String expectedEmpName) {
	    try {
	        // Wait for elements to load properly
	        Thread.sleep(2000);

	        int rowCount = RegularizationRequestSearchResultAdminSideEmpName.size();

	        for (int i = 0; i < rowCount; i++) {
	            String empText = RegularizationRequestSearchResultAdminSideEmpName.get(i).getText().trim();
	            String dateText = RegularizationRequestSearchResultAdminSideDate.get(i).getText().trim();

	            // ✅ Match Employee Name
	            if (!empText.toLowerCase().contains(expectedEmpName.toLowerCase())) {
	                continue;
	            }

	            // ✅ Match Date
	            if (!dateText.contains(expectedDate)) {
	                continue;
	            }

	            // ✅ Found matching row → click "Approve" button
	            utils.waitForEle(RegularizationRequestSearchResultAdminSideApprovedButton.get(i), "clickable", "", 10);
	            RegularizationRequestSearchResultAdminSideApprovedButton.get(i).click();

	            return true; // Successfully clicked
	        }

	        // ❌ No matching record found
	        exceptionDesc = "No matching record found for Employee: " + expectedEmpName + " with Date: " + expectedDate;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean ApproveButton() {
		try {

			utils.waitForEle(ApproveButton, "visible", "", 15);
			ApproveButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean AdminRejectedToRegulirization(String expectedDate, String expectedEmpName) {
	    try {
	        // Wait for elements to load properly
	        Thread.sleep(2000);

	        int rowCount = RegularizationRequestSearchResultAdminSideEmpName.size();

	        for (int i = 0; i < rowCount; i++) {
	            String empText = RegularizationRequestSearchResultAdminSideEmpName.get(i).getText().trim();
	            String dateText = RegularizationRequestSearchResultAdminSideDate.get(i).getText().trim();

	            // ✅ Match Employee Name
	            if (!empText.toLowerCase().contains(expectedEmpName.toLowerCase())) {
	                continue;
	            }

	            // ✅ Match Date
	            if (!dateText.contains(expectedDate)) {
	                continue;
	            }

	            // ✅ Found matching row → click "Approve" button
	            utils.waitForEle(RegularizationRequestSearchResultAdminSideRejectButton.get(i), "clickable", "", 10);
	            RegularizationRequestSearchResultAdminSideRejectButton.get(i).click();

	            return true; // Successfully clicked
	        }

	        // ❌ No matching record found
	        exceptionDesc = "No matching record found for Employee: " + expectedEmpName + " with Date: " + expectedDate;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	public boolean RejectReasonTextField(String Reason) {
		try {

			utils.waitForEle(RejectReasonTextField, "visible", "", 15);
			RejectReasonTextField.clear();
			RejectReasonTextField.sendKeys(Reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RejectButton() {
		try {

			utils.waitForEle(RejectButton, "visible", "", 15);
			RejectButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegulirizationApprovedTab() {
		try {

			utils.waitForEle(RegulirizationApprovedTab, "visible", "", 15);
			RegulirizationApprovedTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ValidationEmpWithDateAndApproveStatus(String status, String expectedDate, String expectedEmpName) {
	    try {
	        // Wait for OT report table row
	        utils.waitForEle(RegulirizationApprovedTabEmpNameRow, "visible", "", 10);

	        // Extract entire row text
	        String rowText = RegulirizationApprovedTabEmpNameRow.getText();
	        // Example: "Empofive Morning shift #Emp010 Fri, 03-10-2025 P 00:29 Hr / 00:29 Hr Auto approved by system. Approved"

	        // Validate Employee Name
	        if (!rowText.toLowerCase().contains(expectedEmpName.toLowerCase())) {
	            exceptionDesc = "Employee name mismatch. Expected: " + expectedEmpName + " | Found: " + rowText;
	            return false;
	        }

	        // Validate Date (format: Fri, 03-10-2025 → contains "03-10-2025")
	       String rowTexts = RegularizationAllReportDateSearchResult.getText();
	        if (!rowTexts.contains(expectedDate)) {
	            exceptionDesc = "Date mismatch. Expected: " + expectedDate + " | Found: " + rowTexts;
	            return false;
	        }

	        // Validate OT Hours (format like "00:29 Hr")
	       String Approvestatus = RegularizationAllReportStatusSearchResult.getText();
	        if (!Approvestatus.contains(status)) {
	            exceptionDesc = "OT hours mismatch. Expected: " + status + " | Found: " + Approvestatus;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean RegulirizationRejectedTab() {
		try {

			utils.waitForEle(RegulirizationRejectedTab, "visible", "", 15);
			RegulirizationRejectedTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegulirizationPendingTab() {
		try {

			utils.waitForEle(RegulirizationPendingTab, "visible", "", 15);
			RegulirizationPendingTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean RegularizationDateList(String expdate) {
	    try {
	        utils.waitForEle(RegularizationAllReportSearchResult, "visible", "", 15);

	        boolean matchFound = false;

	        for (WebElement date : RegularizationDateList) {
	            String actualDate = date.getText().trim();

	            if (actualDate.contains(expdate)) {
	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            throw new Exception("Expected date not found. Expected: " + expdate);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }

	    return true;
	}

	
	
	public boolean RegularizationAllReportDateSearchResultOnEmp(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(RegularizationAllReportDateSearchResultOnEmp, "visible", "", 15);

	        // Input date is yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);

	        // Convert to UI date format (dd-MM-yyyy)
	        String expectedUiDate = parsedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get actual text from UI (e.g. "Tue, 28-10-2025")
	        String actualText = RegularizationAllReportDateSearchResultOnEmp.getText().trim();

	        // ✅ Check if UI text contains the formatted date (ignore weekday)
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean RegularizationAllReportStatusSearchResultOnEmp(String status) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(RegularizationAllReportStatusSearchResultOnEmp, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = RegularizationAllReportStatusSearchResultOnEmp.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(status)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + status +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	
}
