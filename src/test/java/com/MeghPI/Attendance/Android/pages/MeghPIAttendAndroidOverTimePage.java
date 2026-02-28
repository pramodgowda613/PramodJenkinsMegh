package com.MeghPI.Attendance.Android.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.HasSettings;
import utils.Pramod;
import java.time.Duration;
import utils.Utils;

public class MeghPIAttendAndroidOverTimePage {
	private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidOverTimePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((HasSettings) driver).setSetting("enforceXPath1", true);

    }

    
    @FindBy(xpath = "//android.view.View[@content-desc[contains(.,'Overtime Requests')]]/android.view.View[1]")
    private WebElement FilterButtonOfOTTabInAdmin;
	
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"APPROVE\"]")
    private WebElement OTApproveButton;
    
  

  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
    private WebElement AllowOTHoursTimeInput;
  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
    private WebElement ApprovalCommentsInput;
	
    @FindBy(xpath = "//android.widget.Button[translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='APPROVE']")
    private WebElement OTApproveConfirmButton;


    @FindBy(xpath = "//android.widget.Button[@content-desc=\"REJECT\"]")
    private WebElement RejectConfirmButton;
  

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public boolean clickOvertimeTab(String tabName) {
        try {
            String xpath = "//android.view.View[contains(@content-desc,'Overtime Requests')]" +
                           "/android.view.View[contains(@content-desc,'" + tabName + "')]";

            WebElement tabElement = driver.findElement(By.xpath(xpath));
            utils.waitForEle(tabElement, "clickable", "", 30);
            tabElement.click();
            Thread.sleep(800); // small delay for UI to stabilize
            return true;

        } catch (Exception e) {
            exceptionDesc = "Error while clicking on Overtime tab '" + tabName + "': " + e.getMessage();
            return false;
        }
    }

    public boolean validateOTCardCount(String statusType, String expectedCount) {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            // Normalize inputs
            statusType = statusType.trim();
            expectedCount = expectedCount.trim();

            // XPath to locate exact match like "1\nPending"
            String xpath = String.format("//android.view.View[contains(@content-desc, '%s') and contains(@content-desc, '%s')]",
                    expectedCount, statusType);

            System.out.println("🔍 Searching with XPath: " + xpath);

            List<WebElement> cards = driverAndroid.findElements(AppiumBy.xpath(xpath));

            if (cards.isEmpty()) {
                exceptionDesc = "⚠️ No card found for status: " + statusType + " (Expected Count: " + expectedCount + ")";
                System.out.println(exceptionDesc);
                return false;
            }

            // Pick the first matching card
            WebElement card = cards.get(0);
            String cardDesc = card.getAttribute("content-desc").trim();

            System.out.println("📋 Found card: " + cardDesc);

            // Split into lines
            String[] parts = cardDesc.split("\\n");
            if (parts.length < 2) {
                exceptionDesc = "⚠️ Unexpected content-desc format: " + cardDesc;
                System.out.println(exceptionDesc);
                return false;
            }

            String actualCount = parts[0].trim();
            String actualStatus = parts[1].trim();

            // Validation
            if (actualStatus.equalsIgnoreCase(statusType) && actualCount.equals(expectedCount)) {
                System.out.println("✅ Count matches for " + statusType + ": " + actualCount);
                return true;
            } else {
                exceptionDesc = String.format("❌ Count mismatch for %s — Expected: %s, Actual: %s",
                        statusType, expectedCount, actualCount);
                System.out.println(exceptionDesc);
                return false;
            }

        } catch (Exception e) {
            exceptionDesc = "❌ Error while validating card count for " + statusType + ": " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }








 
    public boolean validateAttendanceByDateStatusAndOverstay(String dateInput, String expectedStatus, String expectedOverstay) {
        try {
            // Parse date from input (supports both yyyyMMdd and yyyy-MM-dd)
            LocalDate date = dateInput.contains("-")
                    ? LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

            // Format for matching UI content-desc (e.g. "Monday - 03 Nov 2025")
            String weekday = date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
            String fullDate = weekday + " - " + formattedDate;

            // Try up to 5 scrolls to find the matching card
            for (int i = 0; i < 5; i++) {
                List<WebElement> cards = driver.findElements(By.xpath("//android.view.View[contains(@content-desc,'" + formattedDate + "')]"));
                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");

                    // Check date, status, and overstay
                    boolean dateMatch = content.contains(fullDate);
                    boolean statusMatch = content.contains(expectedStatus);
                    boolean overstayMatch = content.contains("Over Stay\n" + expectedOverstay)
                            || content.contains("Over Stay\n" + expectedOverstay + " Hrs");

                    if (dateMatch && statusMatch && overstayMatch) {
                        return true; // ✅ Found matching record
                    }
                }

                // Scroll up to load next set of cards
                ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 0, "top", 800, "width", 720, "height", 1200,
                        "direction", "up", "percent", 0.8
                ));
            }

            // If not found after scrolling
            exceptionDesc = "No matching card found for " + fullDate +
                    " | Expected Status: " + expectedStatus +
                    " | Expected Overstay: " + expectedOverstay;
            return false;

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }

    public boolean validateAttendanceByDateStatusOverstayAndApprovedOT(
            String dateInput, String expectedStatus, String expectedOverstay, String expectedApprovedOT) {
        try {
            // Parse date from input (supports both yyyyMMdd and yyyy-MM-dd)
            LocalDate date = dateInput.contains("-")
                    ? LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

            // Format for matching UI content-desc (e.g. "Monday - 03 Nov 2025")
            String weekday = date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
            String fullDate = weekday + " - " + formattedDate;

            AndroidDriver driverAndroid = (AndroidDriver) driver;

            // Try up to 5 scrolls to find the matching card
            for (int i = 0; i < 5; i++) {
                List<WebElement> cards = driverAndroid.findElements(
                        By.xpath("//android.view.View[contains(@content-desc,'" + formattedDate + "')]"));

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");

                    // Check date, status, overstay, and approved OT
                    boolean dateMatch = content.contains(fullDate);
                    boolean statusMatch = content.contains(expectedStatus);
                    boolean overstayMatch = content.contains("Over Stay\n" + expectedOverstay)
                            || content.contains("Over Stay\n" + expectedOverstay + " Hrs");
                    boolean approvedOTMatch = content.contains("Approved OT\n" + expectedApprovedOT)
                            || content.contains("Approved OT\n" + expectedApprovedOT + " Hrs");

                    if (dateMatch && statusMatch && overstayMatch && approvedOTMatch) {
                        System.out.println("✅ Attendance card matches all criteria!");
                        return true; // Found matching record
                    }
                }

                // Scroll up to load next set of cards
                ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 0, "top", 800, "width", 720, "height", 1200,
                        "direction", "up", "percent", 0.8
                ));
            }

            // If not found after scrolling
            exceptionDesc = "No matching card found for " + fullDate +
                    " | Expected Status: " + expectedStatus +
                    " | Expected Overstay: " + expectedOverstay +
                    " | Expected Approved OT: " + expectedApprovedOT;
            System.out.println(exceptionDesc);
            return false;

        } catch (Exception e) {
            exceptionDesc = "Error while validating attendance card: " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }



 
    
    public boolean verifyOvertimeCardByEmpDateStatus(String empId, String dateInput) {
        try {
            // --- Step 1: Format date ---
            LocalDate date = dateInput.contains("-")
                    ? LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

            DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
            String formattedDate = date.format(uiFormatter);
            String empIdWithHash = "#" + empId;

            System.out.println("🔍 Searching for OT card with EmpID: " + empIdWithHash
                    + ", Date: " + formattedDate + ", Status: " );

            AndroidDriver driverAndroid = (AndroidDriver) driver;
            boolean found = false;
            int maxScrolls = 15;

            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;
            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {
                // --- Step 2: Get all cards that contain empId (scroll target) ---
                List<WebElement> cards = driverAndroid.findElements(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empIdWithHash + "')]")
                );

                if (!cards.isEmpty()) {
                    String currentTopCard = cards.get(0).getAttribute("content-desc");
                    if (currentTopCard.equals(lastTopCard)) break; // stop if end of list
                    lastTopCard = currentTopCard;
                }

                // --- Step 3: Check each card with contains() for all fields ---
                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    if (content.contains(empIdWithHash) &&
                        content.contains(formattedDate) ) {
                        System.out.println("✅ Found OT card matching all criteria!");
                        found = true;
                        return true;
                    }
                }

                // --- Step 4: Scroll down ---
                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", 0);
                scrollParams.put("top", (int)(screenHeight * 0.2));
                scrollParams.put("width", screenWidth);
                scrollParams.put("height", (int)(screenHeight * 0.7));
                scrollParams.put("direction", "down");
                scrollParams.put("percent", 0.8);

                driverAndroid.executeScript("mobile: scrollGesture", scrollParams);
                Thread.sleep(1000);
                System.out.println("↕️ Scrolling to find more OT cards... Attempt " + (i + 1));
            }

            if (!found) {
                exceptionDesc = "❌ No overtime card found for EmpID: " + empIdWithHash
                        + ", Date: " + formattedDate + ", Status: " ;
                System.out.println(exceptionDesc);
            }

            return found;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while verifying OT card: " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }











    
    
    public boolean FilterButtonOfOTTabInAdmin() {
        try {
            Thread.sleep(1500);

            // Step 1: Try to find the month-year header dynamically (e.g., "Nov 2025")
            List<WebElement> monthYearList = driver.findElements(
                By.xpath("//android.view.View[contains(@content-desc,' 20')]")
            );

            WebElement monthYearElement = null;
            if (!monthYearList.isEmpty()) {
                monthYearElement = monthYearList.get(0);
            } else {
                // Scroll to bring month-year into view
            
                monthYearList = driver.findElements(
                    By.xpath("//android.view.View[contains(@content-desc,' 20')]")
                );
                if (!monthYearList.isEmpty()) {
                    monthYearElement = monthYearList.get(0);
                }
            }

            if (monthYearElement == null) {
                throw new NoSuchElementException("Month-Year element not found even after scroll.");
            }

            // Step 2: From that node, get the first sibling view (Filter Button)
            WebElement filterButton = driver.findElement(
                By.xpath("//android.view.View[contains(@content-desc,'" 
                    + monthYearElement.getAttribute("content-desc").trim() 
                    + "')]/following-sibling::android.view.View[1]")
            );

            // Step 3: Wait for clickable and click
            utils.waitForEle(filterButton, "clickable", "", 30);
            filterButton.click();

            return true;

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }

    public boolean OTApproveButton() {
	    try {
	     
	        utils.waitForEle(OTApproveButton, "visible", "", 30);
	        OTApproveButton.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}

    
 
    public boolean AllowOTHoursTimeInput(String time) {
	    try {
	     
	        utils.waitForEle(AllowOTHoursTimeInput, "visible", "", 50);
	        AllowOTHoursTimeInput.click();
	        AllowOTHoursTimeInput.sendKeys(time);
	        Pramod.hideKeyboardSmart(driver);
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
    public boolean ApprovalCommentsInput(String msg) {
	    try {
	     
	        utils.waitForEle(ApprovalCommentsInput, "visible", "", 50);
	        ApprovalCommentsInput.click();
	        ApprovalCommentsInput.sendKeys(msg);
	        Pramod.hideKeyboardSmart(driver);
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
    public boolean OTApproveConfirmButton() {
        try {
            By approveBtnLocator = By.xpath(
                "//android.widget.Button[translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='APPROVE']"
            );

            // Wait dynamically for element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement approveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(approveBtnLocator));

            utils.waitForEle(approveBtn, "clickable", "", 15);
            approveBtn.click();

            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }

 
    public boolean rejectOTCardByEmpAndDate(String empId, String dateFromExcel) {
        try {
            // --- Step 1: Format date ---
            LocalDate date = LocalDate.parse(dateFromExcel);
            DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
            String formattedDate = date.format(uiFormatter);
            String empIdTag = "#" + empId;

            System.out.println("🔍 Searching for OT card to reject with EmpID: " + empIdTag + ", Date: " + formattedDate);

            AndroidDriver driverAndroid = (AndroidDriver) driver;
            boolean found = false;
            int maxScrolls = 15;

            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;
            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {
                // --- Step 2: Get all cards containing EmpID ---
                List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empIdTag + "')]")
                );

                if (!cards.isEmpty()) {
                    String currentTopCard = cards.get(0).getAttribute("content-desc");
                    if (currentTopCard.equals(lastTopCard)) break; // end of list
                    lastTopCard = currentTopCard;
                }

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    if (content.contains(empIdTag) && content.contains(formattedDate)) {
                        // --- Step 3: Find REJECT button inside this card ---
                        List<WebElement> buttons = card.findElements(AppiumBy.xpath(".//android.widget.Button[contains(@content-desc,'REJECT')]"));
                        if (!buttons.isEmpty()) {
                            buttons.get(0).click();
                            System.out.println("✅ Clicked REJECT on OT card for EmpID: " + empIdTag + ", Date: " + formattedDate);
                            return true;
                        } else {
                            System.out.println("❌ REJECT button not found inside the OT card");
                            return false;
                        }
                    }
                }

                // --- Step 4: Scroll down to load more cards ---
                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", 0);
                scrollParams.put("top", (int)(screenHeight * 0.2));
                scrollParams.put("width", screenWidth);
                scrollParams.put("height", (int)(screenHeight * 0.7));
                scrollParams.put("direction", "down");
                scrollParams.put("percent", 0.8);

                driverAndroid.executeScript("mobile: scrollGesture", scrollParams);
                Thread.sleep(1000);
                System.out.println("↕️ Scrolling to find more OT cards... Attempt " + (i + 1));
            }

            System.out.println("❌ No OT card found to reject for EmpID: " + empIdTag + ", Date: " + formattedDate);
            return false;

        } catch (Exception e) {
            System.out.println("❌ Error while rejecting OT card: " + e.getMessage());
            return false;
        }
    }


    
    
    public boolean RejectConfirmButton() {
	    try {
	     
	        utils.waitForEle(RejectConfirmButton, "visible", "", 50);
	        RejectConfirmButton.click();
	       
	     
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
    
    
    
    public boolean validateAttendanceByEmpDateStatusOverstayAndOvertime(
            String empId, String dateInput, String expectedStatus, String expectedOverstay, String expectedOvertime) {
        try {
            // Parse date from input (supports both yyyyMMdd and yyyy-MM-dd)
            LocalDate date = dateInput.contains("-")
                    ? LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    : LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

            // Format for matching UI content-desc (e.g. "Monday - 03 Nov 2025")
            String weekday = date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
            String fullDate = weekday + " - " + formattedDate;

            String empIdWithHash = "#" + empId;

            AndroidDriver driverAndroid = (AndroidDriver) driver;

            // Try up to 5 scrolls to find the matching card
            for (int i = 0; i < 5; i++) {
                List<WebElement> cards = driverAndroid.findElements(
                        By.xpath("//android.view.View[contains(@content-desc,'" + empIdWithHash + "')]"));

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");

                    // Check EmpID, Date, Status, Overstay, and Overtime
                    boolean empMatch = content.contains(empIdWithHash);
                    boolean dateMatch = content.contains(fullDate);
                    boolean statusMatch = content.contains(expectedStatus);
                    boolean overstayMatch = content.contains("Over Stay\n" + expectedOverstay)
                            || content.contains("Over Stay\n" + expectedOverstay + " Hrs");
                    boolean overtimeMatch = content.contains("Overtime\n" + expectedOvertime)
                            || content.contains("Overtime\n" + expectedOvertime + " Hrs");

                    if (empMatch && dateMatch && statusMatch && overstayMatch && overtimeMatch) {
                        System.out.println("✅ Attendance card matches all criteria!");
                        return true; // Found matching record
                    }
                }

                // Scroll up to load next set of cards
                ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 0, "top", 800, "width", 720, "height", 1200,
                        "direction", "up", "percent", 0.8
                ));
            }

            // If not found after scrolling
            exceptionDesc = "No matching card found for EmpID: " + empIdWithHash +
                    " | Date: " + fullDate +
                    " | Status: " + expectedStatus +
                    " | Overstay: " + expectedOverstay +
                    " | Overtime: " + expectedOvertime;
            System.out.println(exceptionDesc);
            return false;

        } catch (Exception e) {
            exceptionDesc = "Error while validating attendance card: " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }
 
    
    
    
    
 


	
	
    public String getExceptionDesc() {
        return exceptionDesc;
    }	
	
	
	
}
