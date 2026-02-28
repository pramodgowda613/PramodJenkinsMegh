package com.MeghPI.Attendance.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class MeghPIMusterRollReportsPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	public MeghPIMusterRollReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Muster Roll Report']")
	private WebElement MusterRollReportButton; //1stTestCase MusterRollReport
	
	@FindBy(xpath = "//input[@aria-controls='dtMusterRollReport']")
	private WebElement MusterRollReportSearchTextField; //1stTestCase MusterRollReport
	
	@FindBy(xpath = "//table[@id='dtMusterRollReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement MusterRollReportEmpNameRow; //1stTestCase MusterRollReport
	
	@FindBy(xpath = "//table[@id='dtMusterRollReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement MusterRollReportEmpIDRow; //1stTestCase MusterRollReport
	
	@FindBy(xpath = "(//button[@id='btnFilterMusterRollReport'])[2]")
	private WebElement MusterRollReportFilterButton; //1stTestCase MusterRollReport
	
	
	
	public boolean  MusterRollReportButton()
	{
		try {
			utils.waitForEle(MusterRollReportButton, "visible", "", 15);
		
			MusterRollReportButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  MusterRollReportSearchTextField(String Emp)
	{
		try {
			utils.waitForEle(MusterRollReportSearchTextField, "visible", "", 15);
			MusterRollReportSearchTextField.clear();
			MusterRollReportSearchTextField.sendKeys(Emp);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  MusterRollReportFilterButton()
	{
		try {
			utils.waitForEle(MusterRollReportFilterButton, "visible", "", 15);
		
			MusterRollReportFilterButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean MusterRollMonthlyReportEmpIDRecord() {
	    try {
	        utils.waitForEle(MusterRollReportEmpIDRow, "visible", "", 10);

	        String fullText = MusterRollReportEmpIDRow.getText(); // e.g., "#Emp008"
	        getempid = fullText.split("#")[1]; // directly get "Emp008"

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollMonthlyReportEmpNameRecord() {
	    try {
	        utils.waitForEle(MusterRollReportEmpNameRow, "visible", "", 10);

	        String getemplastnames = MusterRollReportEmpNameRow.getText().trim();
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
	
	public boolean MusterRollReportEmpIDRecordValidation(String empid) {
	    try {
	        utils.waitForEle(MusterRollReportEmpIDRow, "visible", "", 10);
	        
	        String actualText = MusterRollReportEmpIDRow.getText().trim();
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
	
	public boolean MusterRollReportEmpNameRecordValidation(String empname) {
	    try {
	        utils.waitForEle(MusterRollReportEmpNameRow, "visible", "", 10);
	        
	        String actualText = MusterRollReportEmpNameRow.getText().trim();
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
	
	public boolean  MusterRollReportEmpIDRecord()
	{
		
		try {
			Thread.sleep(4000);
			utils.waitForEle(MusterRollReportEmpNameRow, "visible", "", 30);
		
			MusterRollReportEmpNameRow.isDisplayed();
			driver.navigate().refresh();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean MusterRollDateHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("P"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean MusterRollWeekOFffHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("W"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollLeaveHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount, String empName) {

	    int maxRetries = 3;      // 🔁 Number of retries
	    int delayMs = 5000;      // ⏳ Wait 5 seconds before each retry

	    for (int attempt = 1; attempt <= maxRetries; attempt++) {
	        try {
	            System.out.println("🔄 Attempt: " + attempt + "/" + maxRetries);

	            // Extract only day (e.g., 2025-10-02 → "02")
	            String day = expectedDate.substring(8, 10);

	            // Find all header cells
	            List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	            int targetColIndex = -1;

	            // Find matching date column
	            for (int i = 0; i < headers.size(); i++) {
	                String headerText = headers.get(i).getText().trim();

	                if (headerText.startsWith(day)) {
	                    targetColIndex = i + 1;
	                    break;
	                }
	            }

	            if (targetColIndex == -1) {
	                throw new Exception("Date header not found for day: " + day);
	            }

	            // --- Status Cell ---
	            WebElement statusCell = driver.findElement(By.xpath(
	                    "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	            ));

	            String actualStatus = statusCell.getText().trim();
	            System.out.println("Expected Status: " + expectedStatus);
	            System.out.println("Actual Status: " + actualStatus);

	            // ❌ Status mismatch?
	            if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	                if (attempt == maxRetries) {
	                    exceptionDesc = "Status mismatch even after retries.";
	                    return false;
	                }

	                System.out.println("❌ Status mismatch. Refreshing page & retrying...");

	                driver.navigate().refresh();
	                Thread.sleep(3000);

	                // 🔍 Re-search employee
	                MusterRollReportSearchTextField.clear();
	                MusterRollReportSearchTextField.sendKeys(empName);

	                Thread.sleep(delayMs); // Wait before retry
	                continue;
	            }

	            // ---- Count total 'L' ----
	            List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));

	            long actualPCount = allCells.stream()
	                    .filter(cell -> cell.getText().trim().equalsIgnoreCase("L"))
	                    .count();

	            System.out.println("Expected L Count: " + expectedPCount);
	            System.out.println("Actual L Count: " + actualPCount);

	            if (actualPCount != expectedPCount) {
	                if (attempt == maxRetries) {
	                    exceptionDesc = "L count mismatch even after retries.";
	                    return false;
	                }

	                System.out.println("❌ L Count mismatch. Refreshing page & retrying...");
	                driver.navigate().refresh();
	                Thread.sleep(3000);

	                MusterRollReportSearchTextField.clear();
	                MusterRollReportSearchTextField.sendKeys(empName);

	                Thread.sleep(delayMs);
	                continue;
	            }

	            // All validations passed
	            return true;

	        } catch (Exception e) {
	            exceptionDesc = e.getMessage();

	            if (attempt == maxRetries) {
	                return false;
	            }

	            System.out.println("⚠️ Exception occurred. Retrying...");
	            driver.navigate().refresh();
	            try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	            MusterRollReportSearchTextField.clear();
	            MusterRollReportSearchTextField.sendKeys(empName);

	            try {
					Thread.sleep(delayMs);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }

	    return false;
	}

	
	public boolean MusterRollHalfDayHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("HD"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollSinglePunchHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("SP"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollHolidayHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("H"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollPresentOnWeekoffHolidayHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("PHW"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollPresentOnWeekoffHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("PW"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollPresentOnHolidayHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("PH"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean MusterRollHalfdayOnWeekoffHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("HDW"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean MusterRollHalfDayOnHolidayHeaderMatchWithTableData(String expectedDate, String expectedStatus, int expectedPCount) {
	    try {
	        // Extract the day from the expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//thead//th"));
	        int targetColIndex = -1;

	        // Find the header that matches the given date
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate the cell (status) for that date and employee (first row here)
	        WebElement statusCell = driver.findElement(By.xpath(
	                "(//table[@id='dtMusterRollReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualStatus = statusCell.getText().trim();

	        // ✅ Step 1: Verify the status for the given date
	        if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	            throw new Exception("Status mismatch for " + expectedDate + ": Expected '" + expectedStatus + "', Found '" + actualStatus + "'");
	        }

	        // ✅ Step 2: Verify total 'P' count for the same employee row
	        List<WebElement> allCells = driver.findElements(By.xpath("//table[@id='dtMusterRollReport']//tbody//tr[1]/td"));
	        long actualPCount = allCells.stream()
	                .filter(cell -> cell.getText().trim().equalsIgnoreCase("HDH"))
	                .count();

	        if (actualPCount != expectedPCount) {
	            throw new Exception("P count mismatch: Expected '" + expectedPCount + "', Found '" + actualPCount + "'");
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
