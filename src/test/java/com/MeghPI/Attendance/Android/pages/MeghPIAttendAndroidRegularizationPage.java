package com.MeghPI.Attendance.Android.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.HasSettings;
import utils.Pramod;
import java.time.Duration;
import utils.Utils;
public class MeghPIAttendAndroidRegularizationPage {


    private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidRegularizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((HasSettings) driver).setSetting("enforceXPath1", true);

    }

    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Regularization') and contains(@content-desc,'Tab')]")
    private WebElement RegularizationTabOnEmpAttendance;

    

	
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]")
    private WebElement QuickActionInRegularizationTabOfEmpAttendance;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Apply Regularization\"]")
    private WebElement ApplyRegularizationTabOnEmpAttendance;
    
    @FindBy(xpath = "//android.widget.EditText")
    private WebElement RegulirizationReasonSearchTextField;
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Overtime') and contains(@content-desc,'Tab')]")
    private WebElement OvertimeTabOnEmpAttendance;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Monthly Allowed')]" +
            "//android.view.View[contains(@content-desc,'Rejected')]")
private WebElement rejectedCountOnMonthlyCard;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Monthly Allowed')]" +
            "//android.view.View[contains(@content-desc,'Pending')]")
private WebElement pendingCountOnMonthlyCard;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Revoked')]")
    private WebElement RevokedTabOnEmpAttendance;


    @FindBy(xpath = "//android.widget.Button[@content-desc=\"REVOKE\"]")
    private WebElement RegulirizationRequestRevokeButton;
  
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'202')]/android.view.View")
    private WebElement MonthYearHeaderClickable;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Overtime') and contains(@content-desc,'Tab')]")
    private WebElement OTTab;

    
    @FindBy(xpath = "//android.view.View[@content-desc=\"For Other\"]")
    private WebElement RegulirizationRequestForOthersButton;
    
  

    @FindBy(xpath = "//android.widget.EditText")
    private WebElement RegulirizationRequestEmpNameField;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Emptwentytwo Morning shift (Emp022)\"]")
    private WebElement RegulirizationRequestEmpNameSelected;
    
  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
    private WebElement RegulirizationRequestFromDateAdmin;
  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
    private WebElement RegulirizationRequestFromTimeAdmin;
    
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[3]")
    private WebElement RegulirizationRequestToDateAdmin;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[4]")
    private WebElement RegulirizationRequestToTimeAdmin;
  
    @FindBy(xpath = "//android.widget.ScrollView/android.view.View[4]")
    private WebElement RegulirizationRequestTypeDropDownAdmin;
    
    @FindBy(xpath = "//android.view.View[@content-desc=\"Miss Punch\"]")
    private WebElement RegulirizationTypeSelected;
  

    
    
    
    
    
    
    
    
    
    
    
    
    

    public boolean RegularizationTabOnEmpAttendance() {
        
        try {
        	Thread.sleep(2000);
    		utils.waitForEle(RegularizationTabOnEmpAttendance, "visible", "", 30);
    		RegularizationTabOnEmpAttendance.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }
    
    public boolean QuickActionInRegularizationTabOfEmpAttendance() {
        
        try {
    		utils.waitForEle(QuickActionInRegularizationTabOfEmpAttendance, "visible", "", 20);
    		QuickActionInRegularizationTabOfEmpAttendance.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }
    
    
    public boolean ApplyRegularizationTabOnEmpAttendance() {
        
        try {
    		utils.waitForEle(ApplyRegularizationTabOnEmpAttendance, "visible", "", 20);
    		ApplyRegularizationTabOnEmpAttendance.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }
	
    public boolean RegulirizationReasonSearchTextField(String reason) {
        
        try {
    		utils.waitForEle(RegulirizationReasonSearchTextField, "visible", "", 30);
    		RegulirizationReasonSearchTextField.click();
    		RegulirizationReasonSearchTextField.sendKeys(reason);
    		
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }  
    
    public boolean validateAttendanceCardByDateAndStatus(String dateFromExcel, String expectedStatus) {
        try {
            LocalDate date = LocalDate.parse(dateFromExcel);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            String formattedDate = date.format(formatter);

            System.out.println("🔍 Searching for attendance card with date: " + formattedDate + " and status: " + expectedStatus);

            AndroidDriver driverAndroid = (AndroidDriver) driver;
            boolean found = false;
            int maxScrolls = 10;

            for (int i = 0; i < maxScrolls; i++) {

                List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath(
                        "//android.view.View[contains(@content-desc,'" + formattedDate + "') and " +
                        "contains(@content-desc,'" + expectedStatus + "')]"
                    )
                );

                if (!cards.isEmpty()) {
                    WebElement card = cards.get(0);
                    System.out.println("✅ Found matching attendance card:");
                    System.out.println("📄 " + card.getAttribute("contentDescription"));
                    found = true;
                    break;
                }

                System.out.println("↕️ Scrolling to find more cards... Attempt " + (i + 1));

                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", 0);
                scrollParams.put("top", 0);
                scrollParams.put("width", driverAndroid.manage().window().getSize().getWidth());
                scrollParams.put("height", driverAndroid.manage().window().getSize().getHeight());
                scrollParams.put("direction", "down");
                scrollParams.put("percent", 0.8);

                driverAndroid.executeScript("mobile: scrollGesture", scrollParams);
            }

            if (!found) {
                exceptionDesc = "❌ No attendance card found for Date: " + formattedDate +
                                " and Status: " + expectedStatus;
                System.out.println(exceptionDesc);
            }

            return found;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while validating attendance card: " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }



    public boolean selectDate(String expectedDate) {
        try {
            // Parse the input date (format: yyyy-MM-dd)
            LocalDate date = LocalDate.parse(expectedDate);
            int day = date.getDayOfMonth();
            String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = date.getYear();

            // Build content-desc as per actual calendar structure
            String contentDesc = String.format("%d, %s, %s %d, %d", day, dayName, monthName, day, year);

            System.out.println("📅 Looking for date element: " + contentDesc);

            // Find the date element
            WebElement dateElement = driver.findElement(AppiumBy.accessibilityId(contentDesc));

            // Wait for visibility and click
            utils.waitForEle(dateElement, "visible", "", 30);
            dateElement.click();

            System.out.println("✅ Clicked on date: " + expectedDate);

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            System.out.println("❌ Error while selecting date: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean selectToDate(String dateToSelect) {
        try {
            // Parse the given date (expected format: yyyy-MM-dd)
            LocalDate date = LocalDate.parse(dateToSelect);

            int day = date.getDayOfMonth();
            String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = date.getYear();

            // Build correct content-desc exactly like UI shows
            String contentDesc = String.format("%d, %s, %s %d, %d", day, dayOfWeek, month, day, year);
            System.out.println("Looking for button with content-desc: " + contentDesc);

            // Scroll until the element is visible and click it
            WebElement dateBtn = ((AndroidDriver) driver).findElement(
                AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))"
                )
            );

            utils.waitForEle(dateBtn, "visible", "", 30);
            dateBtn.click();

            System.out.println("✅ Clicked on date: " + dateToSelect);

        } catch (Exception e) {
            exceptionDesc = "Failed to click on date (" + dateToSelect + "): " + e.getMessage();
            System.out.println("❌ " + exceptionDesc);
            return false;
        }
        return true;
    }
    
    
 public boolean RegulirizationSearchTextField(String empid) {
        
        try {
    		utils.waitForEle(RegulirizationReasonSearchTextField, "visible", "", 20);
    		RegulirizationReasonSearchTextField.click();
    		RegulirizationReasonSearchTextField.sendKeys(empid);
    		Pramod.hideKeyboardSmart(driver);
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }  
 
 
 public boolean validateAttendanceCardByDateAndStatusOnAmin(String dateFromExcel, String expectedStatus) {
	    try {
	        // Format date from yyyy-MM-dd → "dd MMM yyyy"
	        LocalDate date = LocalDate.parse(dateFromExcel);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
	        String formattedDate = date.format(formatter);

	        System.out.println("🔍 Searching for attendance card with date: " + formattedDate + " and status: " + expectedStatus);

	        AndroidDriver driverAndroid = (AndroidDriver) driver;
	        boolean found = false;
	        int maxScrolls = 10;

	        for (int i = 0; i < maxScrolls; i++) {
	            // Locate any matching card in the current visible view
	            List<WebElement> cards = driverAndroid.findElements(
	                    AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + formattedDate + "') and contains(@content-desc,'" + expectedStatus + "')]")
	            );

	            if (!cards.isEmpty()) {
	                WebElement card = cards.get(0);
	                String contentDesc = card.getAttribute("contentDescription");

	                System.out.println("✅ Found matching attendance card:");
	                System.out.println("📄 " + contentDesc);

	                utils.waitForEle(card, "visible", "", 15);
	                found = true;
	                break;
	            }

	            // Scroll down safely using coordinates
	            System.out.println("↕️ Scrolling to find more cards... Attempt " + (i + 1));

	            Map<String, Object> scrollParams = new HashMap<>();
	            scrollParams.put("left", 300);     // starting x coordinate
	            scrollParams.put("top", 300);      // starting y coordinate
	            scrollParams.put("width", 500);    // width of scrollable area
	            scrollParams.put("height", 1200);  // height of scrollable area
	            scrollParams.put("direction", "down");
	            scrollParams.put("percent", 0.8);

	            driverAndroid.executeScript("mobile: scrollGesture", scrollParams);
	        }

	        if (!found) {
	            exceptionDesc = "❌ No attendance card found for Date: " + formattedDate + " and Status: " + expectedStatus;
	            System.out.println(exceptionDesc);
	        }

	        return found;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while validating attendance card: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}

 
 public boolean clickForwardAndBackwardArrow(String dateFromExcel) {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        // 1️⃣ Support both yyyyMMdd and yyyy-MM-dd
	        LocalDate date;
	        if (dateFromExcel.contains("-")) {
	            date = LocalDate.parse(dateFromExcel, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        } else {
	            date = LocalDate.parse(dateFromExcel, DateTimeFormatter.ofPattern("yyyyMMdd"));
	        }

	        String monthYear = date.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));
	        System.out.println("🔍 Searching for month-year: " + monthYear);

	        // 2️⃣ Find the month container
	        WebElement monthContainer = driverAndroid.findElement(
	            AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + monthYear + "')]")
	        );

	        // 3️⃣ Get forward/backward arrows
	        List<WebElement> arrows = monthContainer.findElements(By.xpath("./android.widget.ImageView"));
	        if (arrows.size() < 2) {
	            exceptionDesc = "❌ Expected 2 arrows but found " + arrows.size() + " for month: " + monthYear;
	            System.out.println(exceptionDesc);
	            return false;
	        }

	        // 4️⃣ Click forward arrow
	        WebElement forwardArrow = arrows.get(0);
	        utils.waitForEle(forwardArrow, "visible", "", 5);
	        forwardArrow.click();
	        System.out.println("➡️ Clicked forward arrow for " + monthYear);

	        Thread.sleep(1500);

	        // 5️⃣ Click backward arrow
	        WebElement backwardArrow = arrows.get(1);
	        utils.waitForEle(backwardArrow, "visible", "", 5);
	        backwardArrow.click();
	        System.out.println("⬅️ Clicked backward arrow for " + monthYear);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while clicking forward/backward arrow: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}

 public boolean clickOvertimeTabOnEmpAttendance() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(OvertimeTabOnEmpAttendance, "visible", "", 30);
	        OvertimeTabOnEmpAttendance.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}

 
 public boolean clickRejectedCountOnMonthlyCard() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(rejectedCountOnMonthlyCard, "visible", "", 30);
	        rejectedCountOnMonthlyCard.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}

 public boolean clickPendingCountOnMonthlyCard() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(pendingCountOnMonthlyCard, "visible", "", 30);
	        pendingCountOnMonthlyCard.click();
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
 
 






 public boolean clickRevokedTabOnEmpAttendance() {
	    try {
	        Thread.sleep(2000); // small delay for stability
	        utils.waitForEle(RevokedTabOnEmpAttendance, "visible", "", 20);
	        RevokedTabOnEmpAttendance.click();
	        System.out.println("✅ Clicked on 'Revoked' tab successfully.");
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while clicking on 'Revoked' tab: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}

 public boolean RegulirizationRequestRevokeButton() {
	    try {
	        Thread.sleep(2000); // small delay for stability
	        utils.waitForEle(RegulirizationRequestRevokeButton, "visible", "", 20);
	        RegulirizationRequestRevokeButton.click();
	        System.out.println("✅ Clicked on 'Revoked' tab successfully.");
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while clicking on 'Revoked' tab: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}
 
 
 public boolean validateAttendanceStatusByDate(String dateFromExcel, String expectedStatus) {
	    try {
	        System.out.println("🔍 Validating that attendance card for " + dateFromExcel + " has status: " + expectedStatus);

	        // Convert date from Excel format (yyyy-MM-dd) → UI format (dd MMM yyyy)
	        LocalDate date = LocalDate.parse(dateFromExcel);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
	        String formattedDate = date.format(uiFormatter);

	        // XPath to locate the card that contains both the formatted date and expected status
	        String cardXpath = "//android.view.View[contains(@content-desc,'" + formattedDate + "') and contains(@content-desc,'" + expectedStatus + "')]";

	        // Wait for the card (max 10 seconds)
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardXpath)));

	        // Retrieve the content-desc for verification
	        String cardDetails = card.getAttribute("content-desc");
	        System.out.println("✅ Found card:\n" + cardDetails);

	        // Check if status matches
	        boolean statusMatch = cardDetails.contains(expectedStatus);
	        if (statusMatch) {
	            System.out.println("✅ Status for " + formattedDate + " is correctly marked as " + expectedStatus.toUpperCase());
	        } else {
	            System.out.println("❌ Status for " + formattedDate + " does not match expected: " + expectedStatus);
	        }

	        return statusMatch;

	    } catch (Exception e) {
	        System.out.println("❌ Error while validating status for date: " + dateFromExcel + " → " + e.getMessage());
	        return false;
	    }
	}

 public boolean select31beforeDate(String expectedDate) {
	    try {
	        // Parse the input date (format: "dd MMM yyyy")
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
	        LocalDate date = LocalDate.parse(expectedDate, inputFormatter);

	        // Check if the date is more than 31 days old
	        LocalDate today = LocalDate.now();
	        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(date, today);

	        if (daysBetween > 31) {
	            System.out.println("⚠️ Date " + expectedDate + " is older than 31 days. Not selectable.");
	            return true; // not an error, just not selectable
	        }

	        int day = date.getDayOfMonth();
	        String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        int year = date.getYear();

	        // Build content-desc as per actual calendar structure
	        String contentDesc = String.format("%d, %s, %s %d, %d", day, dayName, monthName, day, year);
	        System.out.println("📅 Looking for date element: " + contentDesc);

	        // Find and click the date element if enabled
	        WebElement dateElement = driver.findElement(AppiumBy.accessibilityId(contentDesc));

	        utils.waitForEle(dateElement, "visible", "", 30);

	        if (!dateElement.isEnabled()) {
	            System.out.println("⚠️ Date " + expectedDate + " is disabled on the calendar.");
	            return true; // not an error — it's intentionally disabled
	        }

	        dateElement.click();
	        System.out.println("✅ Clicked on date: " + expectedDate);
	        return true;

	    } catch (NoSuchElementException e) {
	        System.out.println("⚠️ Date " + expectedDate + " not found in calendar view.");
	        return true; // not an error
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("❌ Error while selecting date: " + e.getMessage());
	        return false;
	    }
	}

 public boolean validateLeaveCardCount(String statusType, String expectedCount) {
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
	        String cardDesc = card.getAttribute("contentDescription").trim();

	        System.out.println("📋 Found card: " + cardDesc);

	        // Split into lines
	        String[] parts = cardDesc.split("\\n");
	        if (parts.length != 2) {
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

 public boolean clickMonthYearInnerView() {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        // Find parent <android.view.View> that contains year (e.g., 2025)
	        List<WebElement> parentViews = driverAndroid.findElements(
	                AppiumBy.xpath("//android.view.View[contains(@content-desc,'202')]")
	        );

	        if (parentViews.isEmpty()) {
	            exceptionDesc = "⚠️ No month-year view found on screen.";
	            System.out.println(exceptionDesc);
	            return false;
	        }

	        WebElement parentView = parentViews.get(0);

	        // Find its inner <android.view.View> child
	        WebElement innerView = parentView.findElement(By.xpath("./android.view.View"));

	        utils.waitForEle(innerView, "visible", "", 30);
	        innerView.click();

	        System.out.println("✅ Clicked on inner view for month-year: " + parentView.getAttribute("content-desc"));
	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while clicking inner view of month-year element: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}

 
 public boolean AdminRegularizationFilterButton() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(MonthYearHeaderClickable, "visible", "", 50);
	        MonthYearHeaderClickable.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 

 public boolean OTTab() {
	    try {
	        Thread.sleep(1000);
	        utils.waitForEle(OTTab, "visible", "", 30);
	        OTTab.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 

 
 



 
 
 public boolean RegulirizationRequestForOthersButton() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestForOthersButton, "visible", "", 30);
	        RegulirizationRequestForOthersButton.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean RegulirizationRequestEmpNameSelected() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestEmpNameSelected, "visible", "", 30);
	        RegulirizationRequestEmpNameSelected.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean inputRegularizationEmpName(String empId) {
	    try {
	        WebElement empField = new WebDriverWait(driver, Duration.ofSeconds(15))
	                .until(ExpectedConditions.visibilityOfElementLocated(
	                        AppiumBy.xpath("//android.widget.EditText[1]")
	                ));

	        empField.click();
	        empField.sendKeys(empId);

	        Pramod.hideKeyboardSmart(driver);
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = "Error entering Emp Name: " + e.getMessage();
	        return false;
	    }
	}
 
 
 
 
 public boolean RegulirizationRequestFromDateAdmin() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestFromDateAdmin, "visible", "", 30);
	        RegulirizationRequestFromDateAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean RegulirizationRequestFromTimeAdmin() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestFromTimeAdmin, "visible", "", 30);
	        RegulirizationRequestFromTimeAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 
 public boolean RegulirizationRequestToDateAdmin() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestToDateAdmin, "visible", "", 30);
	        RegulirizationRequestToDateAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean RegulirizationRequestToTimeAdmin() {
	    try {
	        
	        utils.waitForEle(RegulirizationRequestToTimeAdmin, "visible", "", 30);
	        RegulirizationRequestToTimeAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean RegulirizationTypeSelected() {
	    
	    try {
			utils.waitForEle(RegulirizationTypeSelected, "visible", "", 30);
			RegulirizationTypeSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
 
 public boolean RegulirizationRequestTypeDropDownAdmin() {
	    try {

	        // 1. First click
	        utils.waitForEle(RegulirizationRequestTypeDropDownAdmin, "visible", "", 30);
	        RegulirizationRequestTypeDropDownAdmin.click();
	        Thread.sleep(500);

	        // 2. Try to check if Miss Punch is visible
	        boolean isVisible = false;
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	            wait.until(ExpectedConditions.visibilityOf(RegulirizationTypeSelected));
	            isVisible = true;
	        } catch (Exception ignore) {
	            isVisible = false;
	        }

	        // 3. If not visible → click dropdown again
	        if (!isVisible) {
	            System.out.println("⚠️ Miss Punch not visible — clicking dropdown again");

	            RegulirizationRequestTypeDropDownAdmin.click();
	            Thread.sleep(500);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }

	    return true;
	}

 
 
 
 


	
	
    public String getExceptionDesc() {
        return exceptionDesc;
    }	
	
}
