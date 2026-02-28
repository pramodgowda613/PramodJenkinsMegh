package com.MeghPI.Attendance.pages;


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
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghPIRegularizationRequestPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String reason = "";
	
	public MeghPIRegularizationRequestPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//table[@id='dtMyRegularization']/tbody/tr/td[1]")
	private WebElement EmpDateInTable; //1stTestCase

	@FindBy(xpath = "//table[@id='dtMyRegularization']/tbody/tr/td[4]")
	private WebElement EmpRegularizationReqStatusInTable; //1stTestCase

	@FindBy(xpath = "//div[@id='cardMyRegPendingCount']/div")
	private WebElement PendingTabClicked; //1stTestCase

	@FindBy(xpath = "//button[contains(text(), 'Regularization Summary')]")
	private WebElement RegularizationSummaryClicked; //1stTestCase
	
	
	@FindBy(xpath = "//div[@id='cardRegApproveCount']/div")
	private WebElement ApprovedTabInAdminClicked; //1stTestCase

	@FindBy(xpath = "//button[contains(text(), 'Total Employee')]")
	private WebElement TotalEmpSummaryCountClickedInAdmin; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[2]")
	private WebElement EmpDateInAdminTable; //1stTestCase

	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[5]")
	private WebElement EmpRegularizationReqStatusInAdminTable; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[1]/div/div/p[2]")
	private WebElement EmpIdInAdminTable; //1stTestCase
	
	@FindBy(xpath = "//div[@id='cardMyRegApprovedCount']/div")
	private WebElement ApprovedTabClicked; //1stTestCase
	
	@FindBy(xpath = "(//button[text()='Reject All'])[2]")
	private WebElement RejectAllButton; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtRejectAllReason']")
	private WebElement RejectReasonTextField; //1stTestCase
	
	@FindBy(xpath = "(//button[text()='Reject All'])[3]")
	private WebElement RejectAllConfirmButton; //1stTestCase
	
	@FindBy(xpath = "//div[@id='cardRegRejectCount']")
	private WebElement RejectedTabClickedOnAdmin; //1stTestCase
	
	@FindBy(xpath = "//div[@id='cardMyRegRejectedCount']")
	private WebElement RejectedTabClickedOnEmp; //1stTestCase
	
	@FindBy(xpath = "//div[@id='dtAttendanceEmployee_filter']/label/input")
	private WebElement SearchTextFieldOnEmpTabOnAdmin; //1stTestCase
	
	
	
	@FindBy(xpath = "//p[@class='user_email_id']")
	private WebElement EmpIdOnEmpTabOnAdmin;

	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']//th")
	public List<WebElement> EmpAttendaDateHeader;

	@FindBy(id = "attendanceTableScroll")
	private WebElement AttendanceHorizontalScrollDiv;


	@FindBy(xpath="//div[@id='cardRegPendingCount']")
	private WebElement PendingTabClickonAdmin;
	
	@FindBy(xpath="//table[@id='dtRegularizationRquest']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement EmpFirstNameRowonAdmin;
	
	@FindBy(xpath="//table[@id='dtRegularizationRquest']/tbody/tr[1]/td[2]")
	private WebElement EmpRegularizationDateRowonAdmin;
	
	@FindBy(xpath="//table[@id='dtRegularizationRquest']/tbody/tr[1]/td[4]")
	private WebElement EmpRegularizationReasonRowonAdmin;
	
	@FindBy(xpath="(//label[text()='Reason'])[1]")
	private WebElement ReasonSearchCheckBox;
	
	@FindBy(xpath="//input[@aria-controls='dtMyRegularization']")
	private WebElement SearchTextFieldOnEmpRegularizationTab;
	
	@FindBy(xpath="//table[@id='dtMyRegularization']/tbody/tr/td[3]")
	private WebElement ReasonSearchResultOnEmpRegularizationTab;
	
	@FindBy(xpath="//div[text()='Regularization already exists.']")
	private WebElement RegularizationAlreadyExistsErrorMsg;
	
	@FindBy(xpath="(//button[text()='Approve All'])[2]")
	private WebElement ApproveAllButtonInAdmin;
	
	@FindBy(xpath="(//button[text()='Approve All'])[3]")
	private WebElement ApproveAllConfirmButtonInAdmin;

	@FindBy(xpath="//div[@id='filter-button']/button")
	private WebElement FilterButtonOnRegularizationRequestTab;
	
	@FindBy(xpath="//button[@id='btnFilter']")
	private WebElement FilterApplyButtonOnRegularizationRequestTab;
	
	@FindBy(xpath="//table[@id='dtRegularizationRquest']/tbody/tr/td[1]/div/div/p[1]")
	private WebElement EmpNameDisplayed;
	
	@FindBy(xpath="(//button[text()='Revoke'])[1]")
	private WebElement RevokeButton;
	
	@FindBy(xpath="//button[@id='btnRevoke']")
	private WebElement RevokeConfirmButton;
	
	@FindBy(xpath="//button[@id='btnShiftRevokeRequest']")
	private WebElement ShiftRequestRevokeConfirmButton;
	
	
	@FindBy(xpath="//div[@id='cardMyRegRevokeCount']/div")
	private WebElement RevokeSummaryTab;
	
	@FindBy(xpath="//div[@id='cardRegRevokeCount']/div")
	private WebElement RevokeSummaryTabOnAdmin;
	
	@FindBy(xpath="//div[text()='You have not enough balance to add regularization request!']")
	private WebElement RegularizationlimitMsg;
	
	@FindBy(xpath="//select[@id='drpStatusFilter']")
	private WebElement SelectFilterOption;
	
	
	@FindBy(xpath = "//table[@id='dtMyRegularization']/tbody/tr/td[1]")
	private List<WebElement> EmpDateInTableList; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyRegularization']/tbody/tr/td[4]")
	private List<WebElement> EmpRegularizationReqStatusInTableList; //1stTestCase
	
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[2]")
	private List<WebElement> EmpDateInAdminTableList; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[5]")
	private List<WebElement> EmpRegularizationReqStatusInAdminTableList; //1stTestCase
	
	
	@FindBy(xpath = "//table[@id='dtRegularizationRquest']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> EmpIdInAdminTableList; //1stTestCase
	
	@FindBy(xpath="//table[@id='dtRegularizationRquest']/tbody/tr/td[4]")
	private List<WebElement> EmpRegularizationReasonRowonAdminList;
	
	
	@FindBy(xpath = "(//span[@id='showMonthyear'])[2]")
	private WebElement MonthFilterContains; //8th TestCase
	
	@FindBy(xpath = "(//button[@id='btnPrevious'])[2]")
	private WebElement MonthFilterBackButton; //8th TestCase
	
	public boolean RegularizationlimitMsg() {
		try {

			utils.waitForEle(RegularizationlimitMsg, "visible", "", 15);
	
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean FilterButtonOnRegularizationRequestTab() {
		try {

			utils.waitForEle(FilterButtonOnRegularizationRequestTab, "visible", "", 15);
			FilterButtonOnRegularizationRequestTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FilterApplyButtonOnRegularizationRequestTab() {
		try {

			utils.waitForEle(FilterApplyButtonOnRegularizationRequestTab, "visible", "", 15);
			FilterApplyButtonOnRegularizationRequestTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	public boolean ApproveAllButtonInAdmin() {
		try {

			utils.waitForEle(ApproveAllButtonInAdmin, "visible", "", 15);
			ApproveAllButtonInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ApproveAllConfirmButtonInAdmin() {
		try {

			utils.waitForEle(ApproveAllConfirmButtonInAdmin, "visible", "", 15);
			ApproveAllConfirmButtonInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RegularizationAlreadyExistsErrorMsg() {
		try {

			utils.waitForEle(RegularizationAlreadyExistsErrorMsg, "visible", "", 15);
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	public boolean PendingTabClicked() {
		try {

			utils.waitForEle(PendingTabClicked, "visible", "", 15);
			PendingTabClicked.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ReasonSearchCheckBox() {
		try {

			utils.waitForEle(ReasonSearchCheckBox, "visible", "", 15);
			ReasonSearchCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RegularizationSummaryClicked() {
		try {
Thread.sleep(4000);
			utils.waitForEle(RegularizationSummaryClicked, "visible", "", 15);
			RegularizationSummaryClicked.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ValidateEmpStatusOnEmpAccount(String date, String status) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Wait for table to load
	        utils.waitForEle(EmpDateInTable, "visible", "", 25);
	        // Iterate through table rows
	        for (int i = 0; i < EmpDateInTableList.size(); i++) {

	            String actualDate = EmpDateInTableList.get(i).getText().trim();
	            String actualStatus = EmpRegularizationReqStatusInTableList.get(i).getText().trim();

	            // Debug
	            System.out.println("Row: " + (i + 1));
	            System.out.println("Actual Date: " + actualDate);
	            System.out.println("Actual Status: " + actualStatus);

	            // Match date + status
	            if (actualDate.contains(expectedUIDate) && actualStatus.equalsIgnoreCase(status)) {
	                return true;
	            }
	        }

	        // Not found in any row
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	

	public boolean TotalEmpSummaryCountClickedInAdmin() {
		try {
Thread.sleep(4000);
			utils.waitForEle(TotalEmpSummaryCountClickedInAdmin, "visible", "", 15);
			TotalEmpSummaryCountClickedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ApprovedTabInAdminClicked() {
		try {

			utils.waitForEle(ApprovedTabInAdminClicked, "visible", "", 15);
			ApprovedTabInAdminClicked.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}


	public boolean ValidateEmpStatusOnAdminAccount(String date, String status, String empId) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter =
	                DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        String expectedEmpId = "#" + empId.trim();

	        // Wait for table load
			utils.waitForEle(EmpDateInAdminTable, "visible", "", 25);

	        boolean found = false;

	        // Iterate all rows
	        for (int i = 0; i < EmpDateInAdminTableList.size(); i++) {

	            String actualDate = EmpDateInAdminTableList.get(i).getText().trim();
	            String actualStatus = EmpRegularizationReqStatusInAdminTableList.get(i).getText().trim();
	            String actualEmpId = EmpIdInAdminTableList.get(i).getText().trim();

	            // Debug
	            System.out.println("Row " + i);
	            System.out.println("Actual Date: " + actualDate);
	            System.out.println("Actual EmpID: " + actualEmpId);
	            System.out.println("Actual Status: " + actualStatus);

	            boolean dateMatch = actualDate.contains(expectedUIDate);
	            boolean statusMatch = actualStatus.contains(status);
	            boolean empIdMatch = actualEmpId.contains(expectedEmpId);

	            if (dateMatch && statusMatch && empIdMatch) {
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            System.out.println("❌ No matching row found in Admin table.");
	        } else {
	            System.out.println("✅ Row matched successfully.");
	        }

	        return found;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	
	public boolean ApprovedTabClicked() {
		try {

			utils.waitForEle(ApprovedTabClicked, "visible", "", 15);
			ApprovedTabClicked.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RejectAllButton() {
		try {

			utils.waitForEle(RejectAllButton, "visible", "", 15);
			RejectAllButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean RejectReasonTextField(String reason) {
		try {

			utils.waitForEle(RejectReasonTextField, "visible", "", 15);
			RejectReasonTextField.clear();
			RejectReasonTextField.sendKeys(reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RejectAllConfirmButton() {
		try {
Thread.sleep(2000);
			utils.waitForEle(RejectAllConfirmButton, "visible", "", 20);
			RejectAllConfirmButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RejectedTabClickedOnAdmin() {
		try {

			utils.waitForEle(RejectedTabClickedOnAdmin, "visible", "", 15);
			RejectedTabClickedOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RejectedTabClickedOnEmp() {
		try {

			utils.waitForEle(RejectedTabClickedOnEmp, "visible", "", 15);
			RejectedTabClickedOnEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	private int getAttendanceColumnIndex(String formattedDate) throws InterruptedException {
	    int maxScrolls = 20;
	    String lastHeader = "";
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    for (int scroll = 0; scroll < maxScrolls; scroll++) {

	        for (int i = 0; i < EmpAttendaDateHeader.size(); i++) {
	            String headerText = EmpAttendaDateHeader.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase(formattedDate)) {
	                return i + 1;
	            }
	        }

	        // Stop scrolling if no header movement
	        String currentHeader = EmpAttendaDateHeader.get(0).getText().trim();
	        if (currentHeader.equals(lastHeader)) {
	            break;
	        }
	        lastHeader = currentHeader;

	        // Scroll right
	        js.executeScript("arguments[0].scrollLeft += 300;", AttendanceHorizontalScrollDiv);
	        Thread.sleep(500);
	    }

	    return -1;
	}

	
	
	public boolean validateAttendanceStatus(String empId, String empName, String date, String expectedStatus) {
	    try {
	        int maxRetries = 5;    // 🔥 Increase retries here
	        int delayMs = 5000;    // 🔥 Delay between retries (3 seconds)

	        for (int attempt = 1; attempt <= maxRetries; attempt++) {

	            System.out.println("🔄 Attempt: " + attempt + "/" + maxRetries);

	            // --- Validate employee ID ---
	            String expectedEmpId = "#" + empId.trim();
	            String actualEmpId = EmpIdOnEmpTabOnAdmin.getText().trim();

	            if (!actualEmpId.equalsIgnoreCase(expectedEmpId)) {
	                System.out.println("❌ Employee ID mismatch. Expected: " + expectedEmpId + " | Actual: " + actualEmpId);
	                return false;
	            }

	            // --- Convert date into dd MMM ---
	            LocalDate inputDate = LocalDate.parse(date);
	            DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH);
	            String formattedDate = inputDate.format(uiFormat);

	            int columnIndex = getAttendanceColumnIndex(formattedDate);

	            if (columnIndex == -1) {
	                System.out.println("❌ Date column not found after scrolling: " + formattedDate);
	                return false;
	            }

	            // --- Read status ---
	            String xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[" + columnIndex + "]";
	            WebElement statusCell = driver.findElement(By.xpath(xpath));
	            String actualStatus = statusCell.getText().trim();

	            System.out.println("Expected Status: " + expectedStatus);
	            System.out.println("Actual Status:   " + actualStatus);

	            // --- Success? ---
	            if (actualStatus.equalsIgnoreCase(expectedStatus)) {
	                System.out.println("✅ Status matched successfully");
	                return true;
	            }

	            // --- Retry if mismatch ---
	            if (attempt < maxRetries) {
	                System.out.println("❌ Status mismatch. Refreshing and retrying...");

	                driver.navigate().refresh();
	                Thread.sleep(4000);

	                // Re-search employee after refresh
	                SearchTextFieldOnEmpTabOnAdmin.clear();
	                SearchTextFieldOnEmpTabOnAdmin.sendKeys(empName);
	                Thread.sleep(delayMs); // retry delay
	            }
	        }

	        // After all retries
	        exceptionDesc = "Status did not match even after retrying " + maxRetries + " times.";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	
	public boolean SearchTextFieldOnEmpTabOnAdmin(String empname) {
		try {

			utils.waitForEle(SearchTextFieldOnEmpTabOnAdmin, "visible", "", 15);
			SearchTextFieldOnEmpTabOnAdmin.clear();
			SearchTextFieldOnEmpTabOnAdmin.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean PendingTabClickonAdmin() {
		try {

			utils.waitForEle(PendingTabClickonAdmin, "visible", "", 15);
			PendingTabClickonAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	
	public boolean EmpFirstNameRowonAdmin(String firstname) {
	    try {
	        utils.waitForEle(EmpFirstNameRowonAdmin, "visible", "", 15);
	      reason =  EmpRegularizationReasonRowonAdmin.getText();
	       

	        String uiName = EmpFirstNameRowonAdmin.getText().trim();

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
	
	public boolean EmpRegularizationReasonRowonAdmin(String firstname) {
	    try {

	        // Wait until at least one row is visible
	        utils.waitForEle(EmpRegularizationReasonRowonAdmin, "visible", "", 15);

	        for (WebElement row : EmpRegularizationReasonRowonAdminList) {

	            String uiName = row.getText().trim();

	            if (uiName.contains(firstname)) {
	                return true;   // FOUND → success
	            }
	        }

	        // If no row matched
	        exceptionDesc = "Firstname not found in any row → Expected: " + firstname;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	public boolean EmpRegularizationDateRowonAdmin(String dateFromExcel) {
	    try {
	        // Excel Format: dd-MM-yyyy   (04-11-2025)
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);

	        LocalDate parsedDate = LocalDate.parse(dateFromExcel, inputFormat);
	        String expectedUiDate = parsedDate.format(uiFormat);

	        utils.waitForEle(EmpRegularizationDateRowonAdmin, "visible", "", 15);

	        String uiText = EmpRegularizationDateRowonAdmin.getText().trim();

	        if (uiText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected UI date: " + expectedUiDate + ", but found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	
	
	
	
	
	public boolean SearchTextFieldOnEmpRegularizationTab(String reason) {
		try {

			utils.waitForEle(SearchTextFieldOnEmpRegularizationTab, "visible", "", 15);
			SearchTextFieldOnEmpRegularizationTab.clear();
			SearchTextFieldOnEmpRegularizationTab.sendKeys(reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ReasonSearchResultOnEmpRegularizationTab(String firstname) {
	    try {
	        utils.waitForEle(ReasonSearchResultOnEmpRegularizationTab, "visible", "", 15);
	       

	        String uiName = ReasonSearchResultOnEmpRegularizationTab.getText().trim();

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
	
	
	public boolean EmpNameDisplayed(String firstname) {
	    try {
	        utils.waitForEle(EmpNameDisplayed, "visible", "", 20);
	       

	        String uiName = EmpNameDisplayed.getText().trim();

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
	
	
	
	public boolean RevokeButton() {
		try {

			utils.waitForEle(RevokeButton, "visible", "", 20);
			RevokeButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RevokeConfirmButton() {
		try {
Thread.sleep(2000);
			utils.waitForEle(RevokeConfirmButton, "visible", "", 20);
			RevokeConfirmButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean RevokeSummaryTab() {
		try {

			utils.waitForEle(RevokeSummaryTab, "visible", "", 20);
			RevokeSummaryTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean RevokeSummaryTabOnAdmin() {
		try {

			utils.waitForEle(RevokeSummaryTabOnAdmin, "visible", "", 20);
			RevokeSummaryTabOnAdmin.click();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectFilterOption(String status) {
		try {
Thread.sleep(4000);
			utils.waitForEle(SelectFilterOption, "visible", "", 20);
		Select sele = new Select(SelectFilterOption);
		sele.selectByVisibleText(status);
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ValidateEmpStatusOnAdminAccountForFilterResult(String status, String empId) {
	    try {
	        String expectedEmpId = "#" + empId.trim();

	        // -------- FIRST ATTEMPT --------
	        boolean firstCheck = checkStatusAndEmp(status, expectedEmpId);

	        if (firstCheck) {
	            Thread.sleep(2000);
	            driver.navigate().refresh();
	            Thread.sleep(2000);
	            return true;
	        }

	        // If elements not found OR mismatch -> click back button and retry
	        try {
	            MonthFilterBackButton.click();
	            Thread.sleep(1500);
	        } catch (Exception e) {
	            System.out.println("⚠ MonthFilterBackButton not clickable: " + e.getMessage());
	        }

	        // -------- SECOND ATTEMPT --------
	        boolean secondCheck = checkStatusAndEmp(status, expectedEmpId);

	        if (secondCheck) {
	            Thread.sleep(2000);
	            driver.navigate().refresh();
	            Thread.sleep(2000);
	            return true;
	        }

	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	// -----------------------------------------------------
	// Helper method for clean validation
	// -----------------------------------------------------
	private boolean checkStatusAndEmp(String status, String expectedEmpId) {

	    try {
	        utils.waitForEle(EmpRegularizationReqStatusInAdminTable, "visible", "", 10);

	        String actualStatus = EmpRegularizationReqStatusInAdminTable.getText().trim();
	        String actualEmpId = EmpIdInAdminTable.getText().trim();

	        boolean statusMatch = actualStatus.contains(status);
	        boolean empIdMatch = actualEmpId.contains(expectedEmpId);

	        System.out.println("Expected EmpID : " + expectedEmpId);
	        System.out.println("Actual EmpID   : " + actualEmpId);
	        System.out.println("Expected Status: " + status);
	        System.out.println("Actual Status  : " + actualStatus);

	        return statusMatch && empIdMatch;

	    } catch (Exception e) {
	        System.out.println("Element not found in checkStatusAndEmp: " + e.getMessage());
	        return false;
	    }
	}



	
	
	public boolean MonthFilterContains(String date) {
	    try {
	    	Thread.sleep(4000);
	        // Convert "2025-08-13" → "August 2025"
	        LocalDate inputDate = LocalDate.parse(date);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
	        String expectedMonth = inputDate.format(formatter);

	        System.out.println("Expected Month: " + expectedMonth);

	        // loop max 12 times (full year), avoid infinite loop
	        for (int i = 0; i < 12; i++) {

	            utils.waitForEle(MonthFilterContains, "visible", "", 20);

	            String uiMonth = MonthFilterContains.getText().trim();
	            System.out.println("UI Month: " + uiMonth);

	            // ✅ If match found, return true
	            if (uiMonth.equalsIgnoreCase(expectedMonth)) {
	                return true;
	            }

	            // ✅ If not match → click back and retry
	            MonthFilterBackButton.click();
	          

	            // Now go again inside Attendance > Month filter (since you returned back)
	            // NOTE: You must call your AttendanceButton method here
	            
	        }

	        // ❌ Month never found after 12 checks
	        exceptionDesc = "Month not matched within navigation loops";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	
	public boolean ShiftRequestRevokeConfirmButton() {
		try {

			utils.waitForEle(ShiftRequestRevokeConfirmButton, "visible", "", 15);
			ShiftRequestRevokeConfirmButton.click();
			
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
		exceptionDesc = this.exceptionDesc;
	}


}
