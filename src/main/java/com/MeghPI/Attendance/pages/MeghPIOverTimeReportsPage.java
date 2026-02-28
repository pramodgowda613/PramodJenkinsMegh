package com.MeghPI.Attendance.pages;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghPIOverTimeReportsPage {

	
	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	
	
	
	public MeghPIOverTimeReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Over Time']")
	private WebElement OverTimeButton; //1stTestCase All Tab

	@FindBy(xpath = "(//a[@id='OverTimeAllDataReport_Tab'])[1]")
	private WebElement OverTimeReportAllTabButton; //1stTestCase All Tab

	@FindBy(xpath = "//input[@aria-controls='dtOTReport']")
	private WebElement OverTimeReportAllTabSearchTextField; //1stTestCase All Tab

	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]")
	private WebElement OverTimeReportAllTabSearchResult; //1stTestCase All Tab

	@FindBy(xpath = "(//button[@id='btnFilterOTReport'])[2]")
	private WebElement OverTimeReportFilterButton; //1stTestCase All Tab

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement OverTimeReportEmpIDRecord; //1stTestCase All Tab
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement OverTimeReportEmpNameRecord; //1stTestCase All Tab
	
	@FindBy(xpath = "//label[text()='Last Name']")
	private WebElement OverTimeReportLastNameCheckBox; //1stTestCase All Tab
	
	@FindBy(xpath = "//label[text()='Enroll ID']")
	private WebElement OverTimeReportEnrollIDCheckBox; //1stTestCase All Tab
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement OverTimeReportEmailButton; //1stTestCase All Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[2]")
	private WebElement OverTimeReportDateRow; //1stTestCase All Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[1]")
	private WebElement EmpFirstRecord; //1stTestCase All Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[4]/span")
	private WebElement EmpFirstRecordOTHours; //1stTestCase All Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[3]")
	private WebElement EmpFirstRecordDayType; //1stTestCase All Tab
	
	@FindBy(xpath = "//a[@id='overTime_tab']")
	private WebElement OverTimeRequestTab; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@aria-controls='dtOverTime']")
	private WebElement OverTimeRequestSearchTextField; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//a[@id='OverTimeAllDataReport_Tab'][2]")
	private WebElement OverTimeRequestApprovedTab; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement OverTimeRequestEmpNameRow; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[2]")
	private WebElement OverTimeRequestOTDateRow; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[6]")
	private WebElement OverTimeRequestStatusRow; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//a[@id='OverTimeAllDataReport_Tab'][3]")
	private WebElement OverTimeRequestRejectionTab; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//a[@id='OverTimeAllDataReport_Tab'][4]")
	private WebElement OverTimeRequestPendingTab; //1stTestCase Approved Tab
	
	
	
	@FindBy(xpath = "//a[@id='overTime_tab']")
	private WebElement OverTimeTabOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@aria-controls='dtOverTime']")
	private WebElement OverTimeTabSearchTextFieldOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement OverTimeEmpName; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[2]")
	private WebElement OverTimeEmpDateRow; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[7]/div/button[2]")
	private WebElement OverTimeEmpApproveButton; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='txtApprovalComments']")
	private WebElement OverTimeEmpApprveCmts; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//button[@id='btnApprove']")
	private WebElement OverTimeEmpApproveConfirmButton; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[7]/div/button[1]")
	private WebElement OverTimeEmpRejectButton; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='txtRejectReason']")
	private WebElement OverTimeEmpRejectReason; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//button[@id='btnRejectRequest']")
	private WebElement OverTimeEmpRejectConfirmButton; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='txtAllowOTTime']")
	private WebElement OverTimeHoursUpdate; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[1]/div/div/p[1]")
	private List<WebElement> OverTimeEmpNameList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[2]")
	private List<WebElement> OverTimeEmpDateRowList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[7]/div/button[2]")
	private List<WebElement> OverTimeEmpApproveButtonList; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr/td[1]/div/div/p[1]")
	private List<WebElement> OverTimeRequestEmpNameRowList; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr/td[2]")
	private List<WebElement> OverTimeRequestOTDateRowList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr/td[6]")
	private List<WebElement> OverTimeRequestStatusRowList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[7]/div/button[1]")
	private WebElement OverTimeEmpRejectButtonList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr[1]/td[1]")
	private WebElement OverTimeReportDateRowOnEmp; //1stTestCase All Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr/td[1]")
	private List<WebElement> OverTimeRequestOTDateRowListOnEmp; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtOTReport']/tbody/tr/td[5]")
	private List<WebElement> OverTimeRequestStatusRowListOnEmp; //1stTestCase Approved Tab
	
	
	public boolean  OverTimeButton()
	{
		try {
			utils.waitForEle(OverTimeButton, "visible", "", 10);
		
			OverTimeButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  OverTimeReportAllTabButton()
	{
		try {
			utils.waitForEle(OverTimeReportAllTabButton, "visible", "", 10);
		
			OverTimeReportAllTabButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  OverTimeReportAllTabSearchTextField(String empid)
	{
		try {
			utils.waitForEle(OverTimeReportAllTabSearchTextField, "visible", "", 15);
		
			OverTimeReportAllTabSearchTextField.clear();
			OverTimeReportAllTabSearchTextField.sendKeys(empid);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeReportFilterButton()
	{
		try {
			utils.waitForEle(OverTimeReportFilterButton, "visible", "", 10);
		
			OverTimeReportFilterButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeReportAllTabSearchResult()
	{
		try {
			utils.waitForEle(OverTimeReportAllTabSearchResult, "visible", "", 20);
	
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
		
	
	
	
	public boolean verifyStatusByDate(String inputDate, String expectedStatus) {
	    try {
	        // Wait for the attendance table to be visible
	        utils.waitForEle(OverTimeReportAllTabSearchResult, "visible", "", 20);

	        // Find all rows in the table
	        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr[1]"));

	        boolean recordFound = false;

	        for (WebElement row : rows) {
	            // Get the date cell text
	            String dateText = row.findElement(By.xpath(".//td[2]")).getText().trim();

	            // Match date using 'contains' since format includes day like 'Thu, 10-02-2025'
	            if (dateText.contains(inputDate)) {
	                recordFound = true;

	                // Get the status text (assuming it's the 6th column based on your HTML)
	                String statusText = row.findElement(By.xpath(".//td[6]")).getText().trim();

	                if (statusText.equalsIgnoreCase(expectedStatus)) {
	                    return true; // match found
	                } else {
	                    exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + statusText;
	                    return false;
	                }
	            }
	        }

	        if (!recordFound) {
	            exceptionDesc = "No record found for date containing: " + inputDate;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }

	    return true;
	}
	
	public boolean OverTimeReportEmpIDRecord() {
	    try {
	        utils.waitForEle(OverTimeReportEmpIDRecord, "visible", "", 10);

	        String fullText = OverTimeReportEmpIDRecord.getText(); // e.g., "#Emp008"
	        getempid = fullText.split("#")[1]; // directly get "Emp008"

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}

	
	public boolean OverTimeReportEmpIDRecordValidation(String empid) {
	    try {
	        utils.waitForEle(OverTimeReportEmpIDRecord, "visible", "", 10);
	        
	        String actualText = OverTimeReportEmpIDRecord.getText().trim();
	        if (actualText.contains(empid)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected EmpID not found. Actual text: " + actualText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}

	public boolean OverTimeReportEmpNameRecord() {
	    try {
	        utils.waitForEle(OverTimeReportEmpNameRecord, "visible", "", 10);

	        String getemplastnames = OverTimeReportEmpNameRecord.getText().trim();
	        // Example: "Empofive Morning shift"

	        // Split by space
	        String[] parts = getemplastnames.split(" ");
	        
	        if (parts.length >= 2) {
	            // The second word should be "Morning"
	             getemplastname = parts[1];
	            System.out.println("Extracted Shift Name: " + getemplastname);
	            return true;
	        } else {
	            exceptionDesc = "Unexpected text format: " + getemplastnames;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}

	public boolean OverTimeReportEmpNameRecordValidation(String empname) {
	    try {
	        utils.waitForEle(OverTimeReportEmpNameRecord, "visible", "", 10);
	        
	        String actualText = OverTimeReportEmpNameRecord.getText().trim();
	        if (actualText.contains(empname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected Emp Name not found. Actual text: " + actualText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean  OverTimeReportLastNameCheckBox()
	{
		try {
			utils.waitForEle(OverTimeReportLastNameCheckBox, "visible", "", 10);
		
			OverTimeReportLastNameCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  OverTimeReportEnrollIDCheckBox()
	{
		try {
			utils.waitForEle(OverTimeReportEnrollIDCheckBox, "visible", "", 10);
		
			OverTimeReportEnrollIDCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  OverTimeReportEmailButton()
	{
		try {
			utils.waitForEle(OverTimeReportEmailButton, "visible", "", 10);
		
			OverTimeReportEmailButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OverTimeReportDateRow(String expectedMonthName) {
	    try {
	        utils.waitForEle(OverTimeReportDateRow, "visible", "", 10);

	        String dateText = OverTimeReportDateRow.getText(); // e.g., "Fri, 03-10-2025"

	        // Extract the dd-mm-yyyy part
	        String[] parts = dateText.split(","); // ["Fri", " 03-10-2025"]
	        if (parts.length < 2) return false;

	        String datePart = parts[1].trim(); // "03-10-2025"
	        String[] dateElements = datePart.split("-"); // ["03", "10", "2025"]
	        int monthNum = Integer.parseInt(dateElements[1]); // 10

	        // Convert month number to name
	        String actualMonthName = java.time.Month.of(monthNum)
	                                  .getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);

	        // Compare with expected month
	        if (actualMonthName.equalsIgnoreCase(expectedMonthName)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected month: " + expectedMonthName + ", but found: " + actualMonthName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean OverTimeReportDateRowValidation(String expectedDate) {
	    try {
	        utils.waitForEle(OverTimeReportDateRow, "visible", "", 10);

	        String[] datas = OverTimeReportDateRow.getText().split(","); // ["Fri", " 03-10-2025"]
	        if (datas.length < 2) {
	            exceptionDesc = "Date row text format is invalid: " + OverTimeReportDateRow.getText();
	            return false;
	        }

	          
	        String datePart = datas[1].trim(); // "03-10-2025"

	        if (!datePart.contains(expectedDate)) {
	            exceptionDesc = "Expected date: " + expectedDate + ", but found: " + datePart;
	            return false;
	        }

	        return true; // matches expected date

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	

	public boolean ValidationEmpWithDateAndOT(String expectedHours, String expectedDate, String expectedEmpName) {
	    try {
	        // Wait for OT report table row
	        utils.waitForEle(OverTimeReportAllTabSearchResult, "visible", "", 10);

	        // Extract entire row text
	        String rowText = EmpFirstRecord.getText();
	        // Example: "Empofive Morning shift #Emp010 Fri, 03-10-2025 P 00:29 Hr / 00:29 Hr Auto approved by system. Approved"

	        // Validate Employee Name
	        if (!rowText.toLowerCase().contains(expectedEmpName.toLowerCase())) {
	            exceptionDesc = "Employee name mismatch. Expected: " + expectedEmpName + " | Found: " + rowText;
	            return false;
	        }

	        // Validate Date (format: Fri, 03-10-2025 → contains "03-10-2025")
	       String rowTexts = OverTimeReportDateRow.getText();
	        if (!rowTexts.contains(expectedDate)) {
	            exceptionDesc = "Date mismatch. Expected: " + expectedDate + " | Found: " + rowTexts;
	            return false;
	        }

	        // Validate OT Hours (format like "00:29 Hr")
	       String OThours = EmpFirstRecordOTHours.getText();
	        if (!OThours.contains(expectedHours)) {
	            exceptionDesc = "OT hours mismatch. Expected: " + expectedHours + " | Found: " + OThours;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean EmpFirstRecordDayType(String daytype) {
	    try {
	        utils.waitForEle(EmpFirstRecordDayType, "visible", "", 10);
	        String actualText = EmpFirstRecordDayType.getText().trim();

	        if (actualText.contains(daytype)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected day type: " + daytype + " but found: " + actualText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	//Approved Tab
	
	public boolean  OverTimeRequestApprovedTab()
	{
		try {
			utils.waitForEle(OverTimeRequestApprovedTab, "visible", "", 15);
		
			OverTimeRequestApprovedTab.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ValidationEmpWithDateAndApproveStatus(String status, String expectedDate, String expectedEmpName) {
	    try {

	        utils.waitForEle(OverTimeRequestEmpNameRowList.get(0), "visible", "", 10);

	        boolean matchFound = false;

	        // Loop through table rows based on index
	        for (int i = 0; i < OverTimeRequestEmpNameRowList.size(); i++) {

	            String empNameText = OverTimeRequestEmpNameRowList.get(i).getText().trim();
	            String dateText = OverTimeRequestOTDateRowList.get(i).getText().trim();
	            String statusText = OverTimeRequestStatusRowList.get(i).getText().trim();

	            // Check all three validations for the same row index
	            if (empNameText.contains(expectedEmpName)
	                    && dateText.contains(expectedDate)
	                    && statusText.contains(status)) {

	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            exceptionDesc = "No matching record found. Expected -> Emp: " + expectedEmpName
	                    + ", Date: " + expectedDate + ", Status: " + status;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }

	    return true;
	}

	
	
	public boolean  OverTimeRequestRejectionTab()
	{
		try {
			utils.waitForEle(OverTimeRequestRejectionTab, "visible", "", 15);
		
			OverTimeRequestRejectionTab.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  OverTimeRequestPendingTab()
	{
		try {
			utils.waitForEle(OverTimeRequestPendingTab, "visible", "", 15);
		
			OverTimeRequestPendingTab.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeTabOnAdmin()
	{
		try {
			utils.waitForEle(OverTimeTabOnAdmin, "visible", "", 20);
		
			OverTimeTabOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeTabSearchTextFieldOnAdmin(String empname)
	{
		try {
			utils.waitForEle(OverTimeTabSearchTextFieldOnAdmin, "visible", "", 20);
		
			OverTimeTabSearchTextFieldOnAdmin.clear();
			OverTimeTabSearchTextFieldOnAdmin.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OverTimeEmpName(String empname, String expectedDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);

	        String dateUI = LocalDate.parse(expectedDate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(OverTimeEmpNameList.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < OverTimeEmpNameList.size(); i++) {

	            String uiEmpName  = OverTimeEmpNameList.get(i).getText().trim();
	            String uiDate     = OverTimeEmpDateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + ": Name=" + uiEmpName + " | Date=" + uiDate);

	            boolean nameMatch = uiEmpName.contains(empname);
	            boolean dateMatch = uiDate.contains(dateUI);

	            if (nameMatch && dateMatch) {

	                // Click Approve button for the same row
	                utils.waitForEle(OverTimeEmpApproveButtonList.get(i), "clickable", "", 15);
	                OverTimeEmpApproveButtonList.get(i).click();

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " + empname +
	                        " | Expected Date: " + dateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	public boolean  OverTimeEmpApprveCmts(String empname)
	{
		try {
			utils.waitForEle(OverTimeEmpApprveCmts, "visible", "", 20);
		
			OverTimeEmpApprveCmts.clear();
			OverTimeEmpApprveCmts.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeHoursUpdate(String empname)
	{
		try {
			utils.waitForEle(OverTimeHoursUpdate, "visible", "", 20);
		
			OverTimeHoursUpdate.clear();
			OverTimeHoursUpdate.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	public boolean  OverTimeEmpApproveConfirmButton()
	{
		try {
			utils.waitForEle(OverTimeEmpApproveConfirmButton, "visible", "", 15);
		
			OverTimeEmpApproveConfirmButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OverTimeReject(String empname, String expectedDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 (UI Format)
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedDateUI = LocalDate.parse(expectedDate, inputFormat).format(uiFormat);

	        // Wait till OT table is visible
	        utils.waitForEle(OverTimeEmpNameList.get(0), "visible", "", 20);

	        int rowCount = OverTimeEmpNameList.size();

	        for (int i = 0; i < rowCount; i++) {

	            String uiEmpName = OverTimeEmpNameList.get(i).getText().trim();
	            String uiDate = OverTimeEmpDateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + " | UI Name: " + uiEmpName + " | UI Date: " + uiDate);

	            if (uiEmpName.contains(empname) && uiDate.contains(expectedDateUI)) {

	                // Click Reject button of the same row
	                WebElement rejectBtn = driver.findElement(
	                        By.xpath("//table[@id='dtOverTime']/tbody/tr[" + (i + 1) + "]/td[7]/div/button[1]")
	                );

	                utils.waitForEle(rejectBtn, "clickable", "", 10);
	                rejectBtn.click();

	                return true;
	            }
	        }

	        exceptionDesc = "No matching OT record found. Emp: " + empname +
	                        " | Expected Date: " + expectedDateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	
	public boolean  OverTimeEmpRejectReason(String empname)
	{
		try {
			utils.waitForEle(OverTimeEmpRejectReason, "visible", "", 20);
		
			OverTimeEmpRejectReason.clear();
			OverTimeEmpRejectReason.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OverTimeEmpRejectConfirmButton()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(OverTimeEmpRejectConfirmButton, "visible", "", 15);
		
			OverTimeEmpRejectConfirmButton.click();
			Thread.sleep(2000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OverTimeReportDateRowValidationOnEmp(String expectedDate) {
	    try {
	        utils.waitForEle(OverTimeReportDateRowOnEmp, "visible", "", 10);

	        String[] datas = OverTimeReportDateRowOnEmp.getText().split(","); // ["Fri", " 03-10-2025"]
	        if (datas.length < 2) {
	            exceptionDesc = "Date row text format is invalid: " + OverTimeReportDateRow.getText();
	            return false;
	        }

	          
	        String datePart = datas[1].trim(); // "03-10-2025"

	        if (!datePart.contains(expectedDate)) {
	            exceptionDesc = "Expected date: " + expectedDate + ", but found: " + datePart;
	            return false;
	        }

	        return true; // matches expected date

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean ValidationEmpWithDateAndApproveStatusOnEmp(String status, String expectedDate) {
	    try {

	        utils.waitForEle(OverTimeRequestOTDateRowListOnEmp.get(0), "visible", "", 10);

	        boolean matchFound = false;

	        // Loop through table rows based on index
	        for (int i = 0; i < OverTimeRequestOTDateRowListOnEmp.size(); i++) {

	            String dateText = OverTimeRequestOTDateRowListOnEmp.get(i).getText().trim();
	            String statusText = OverTimeRequestStatusRowListOnEmp.get(i).getText().trim();

	            // Check all three validations for the same row index
	            if (dateText.contains(expectedDate)
	                    && statusText.contains(status)) {

	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            exceptionDesc = "No matching record found. Expected -> Date: " + expectedDate
	                    + ", Date: " + expectedDate + ", Status: " + status;
	            return false;
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