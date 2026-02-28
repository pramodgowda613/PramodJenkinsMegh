package com.MeghPI.Attendance.Android.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
public class MeghPIAttendAndroidWeekOffPage {

	private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidWeekOffPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((HasSettings) driver).setSetting("enforceXPath1", true);

    }

    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Week Off') and contains(@content-desc,'Tab')]")
    private WebElement WeekOffTabOnEmpAttendance;

    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Apply Week-Off\"]")
    private WebElement ApplyWeekOffFromQuickActionOfEmpAttendance;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")
    private WebElement WeekOFfNewDateField;
  
  //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]

  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"REJECT\"]")
    private WebElement ApplyWeekOffRejectButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Emptwentyfour Morning shift (Emp024)\"]")
    private WebElement WeekOffRequestEmpNameSelected;
    
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
    private WebElement NewWeekOffDateClickedOnAdmin;
  
  

    
    
    public boolean ApplyWeekOffRejectButton() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(ApplyWeekOffRejectButton, "visible", "", 30);
	        ApplyWeekOffRejectButton.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
    
    
    public boolean WeekOffTabOnEmpAttendance() {
	    try {
	        Thread.sleep(2000);
	        utils.waitForEle(WeekOffTabOnEmpAttendance, "visible", "", 30);
	        WeekOffTabOnEmpAttendance.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
    
    public boolean ApplyWeekOffFromQuickActionOfEmpAttendance() {
	    try {
	 
	        utils.waitForEle(ApplyWeekOffFromQuickActionOfEmpAttendance, "visible", "", 30);
	        ApplyWeekOffFromQuickActionOfEmpAttendance.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
    
    
    public boolean WeekOFfNewDateField() {
	    try {
	 Thread.sleep(1000);
	        utils.waitForEle(WeekOFfNewDateField, "visible", "", 30);
	        WeekOFfNewDateField.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
    public boolean selectFirstSaturdayDate(String expectedDate) {
        try {
            // FIX → Accept dd-MM-yyyy from Excel
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(expectedDate, inputFormatter);

            int day = date.getDayOfMonth();
            String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = date.getYear();

            // Build content-desc based on your UI
            String baseDesc = String.format("%d, %s, %s %d, %d",
                    day, dayName, monthName, day, year);

            System.out.println("📅 Searching for date: " + baseDesc);

            WebElement dateElement = null;

            // Try without ", Today"
            try {
                dateElement = driver.findElement(AppiumBy.accessibilityId(baseDesc));
            } catch (Exception ignore) {}

            // Try with ", Today"
            if (dateElement == null) {
                String todayDesc = baseDesc + ", Today";
                System.out.println("📅 Trying Today variant: " + todayDesc);

                try {
                    dateElement = driver.findElement(AppiumBy.accessibilityId(todayDesc));
                } catch (Exception ignore) {}
            }

            if (dateElement == null) {
                System.out.println("❌ Date not found in calendar: " + expectedDate);
                return false;
            }

            utils.waitForEle(dateElement, "visible", "", 30);
            dateElement.click();

            System.out.println("✅ Clicked on date: " + expectedDate);
            return true;

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            System.out.println("❌ Error in selectDate(): " + e.getMessage());
            return false;
        }
    }


 
    public boolean selectWeekoffNewDate(String dateToSelect) {
        try {
            // FIX → Parse dd-MM-yyyy (your Excel format)
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateToSelect, inputFmt);

            int day = date.getDayOfMonth();
            String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = date.getYear();

            // Actual UI format: "14, Friday, November 14, 2025"
            String normalDesc = String.format(
                    "%d, %s, %s %d, %d",
                    day, dayOfWeek, month, day, year
            );

            // UI alternative: "..., Today"
            String todayDesc = normalDesc + ", Today";

            System.out.println("🔎 Normal Desc = " + normalDesc);
            System.out.println("🔎 Today Desc  = " + todayDesc);

            WebElement dateBtn = null;

            AndroidDriver driverA = (AndroidDriver) driver;

            // Try Normal format first
            try {
                dateBtn = driverA.findElement(
                        AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true))" +
                                        ".scrollIntoView(new UiSelector().description(\"" + normalDesc + "\"))"
                        )
                );
            } catch (Exception e) {
                System.out.println("🔄 Normal format not found, trying Today format...");
            }

            // Try Today format
            if (dateBtn == null) {
                try {
                    dateBtn = driverA.findElement(
                            AppiumBy.androidUIAutomator(
                                    "new UiScrollable(new UiSelector().scrollable(true))" +
                                            ".scrollIntoView(new UiSelector().description(\"" + todayDesc + "\"))"
                            )
                    );
                } catch (Exception e) {
                    System.out.println("❌ Both formats not found!");
                    return false;
                }
            }

            // Wait & Click
            utils.waitForEle(dateBtn, "visible", "", 30);
            dateBtn.click();

            System.out.println("✅ Date clicked: " + dateToSelect);
            return true;

        } catch (Exception e) {
            exceptionDesc = "Failed to click date (" + dateToSelect + "): " + e.getMessage();
            System.out.println("❌ " + exceptionDesc);
            return false;
        }
    }


    public boolean validateWeekoffChangeCard(String oldDateExcel, String newDateExcel, String expectedStatus) {
        try {
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

            String oldDateUI = LocalDate.parse(oldDateExcel, inputFmt).format(uiFmt);
            String newDateUI = LocalDate.parse(newDateExcel, inputFmt).format(uiFmt);
            String statusLower = expectedStatus.toLowerCase(Locale.ENGLISH);

            System.out.println("🔍 Searching card with:");
            System.out.println("   📅 Old Week-Off Date : " + oldDateUI);
            System.out.println("   📅 New Week-Off Date : " + newDateUI);
            System.out.println("   🔖 Status            : " + expectedStatus);

            AndroidDriver driverAndroid = (AndroidDriver) driver;

            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lower = "abcdefghijklmnopqrstuvwxyz";

            // UI LABELS MUST MATCH EXACTLY
            String xpath =
                "//android.view.View[" +
                "contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'old week-off date')" +
                " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + oldDateUI.toLowerCase() + "')" +
                " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + statusLower + "')" +
                " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'new week-off date')" +
                " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + newDateUI.toLowerCase() + "')" +
                "]";

            System.out.println("🔎 Final XPath: " + xpath);

            Dimension size = driverAndroid.manage().window().getSize();
            int width = size.width;
            int height = size.height;

            for (int i = 0; i < 12; i++) {

                List<WebElement> cards = driverAndroid.findElements(AppiumBy.xpath(xpath));

                if (!cards.isEmpty()) {
                    WebElement card = cards.get(0);
                    utils.waitForEle(card, "visible", "", 30);

                    System.out.println("✅ Found Card:\n" + card.getAttribute("content-desc"));
                    return true;
                }

                // scroll with coordinates
                HashMap<String, Object> scroll = new HashMap<>();
                scroll.put("left", 0);
                scroll.put("top", (int) (height * 0.25));
                scroll.put("width", width);
                scroll.put("height", (int) (height * 0.6));
                scroll.put("direction", "down");
                scroll.put("percent", 0.85);

                System.out.println("↕️ Scrolling attempt " + (i + 1));
                driverAndroid.executeScript("mobile: scrollGesture", scroll);
                Thread.sleep(700);
            }

            exceptionDesc = "❌ Card NOT FOUND for Old:" + oldDateUI + ", New:" + newDateUI + ", Status:" + expectedStatus;
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Error validating week-off card: " + e.getMessage();
            return false;
        }
    }



 
    public boolean clickWeekOffTab(String tabName) {
        try {
            // Matches content-desc like: "1\nPending"
        	String xpath = "//android.view.View[contains(@content-desc,'" + tabName + "')]";


            System.out.println("🔎 XPath used: " + xpath);

            WebElement tabElement = driver.findElement(By.xpath(xpath));

            utils.waitForEle(tabElement, "clickable", "", 30);
            tabElement.click();
            Thread.sleep(800);

            return true;

        } catch (Exception e) {
            exceptionDesc = "Error while clicking WeekOff tab '" + tabName + "': " + e.getMessage();
            return false;
        }
    }



    public boolean verifyWeekoffCardByEmpOldNewDate(String empId, String oldDate, String newDate) {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            String empHash = "#" + empId;

            // Detect correct input date format
            DateTimeFormatter inputFmt;

            if (oldDate.contains("-")) {
                // Check if dd-MM-yyyy or yyyy-MM-dd
                if (oldDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                } else {
                    inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }
            } else {
                inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            }

            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

            String oldDateUi = LocalDate.parse(oldDate, inputFmt).format(uiFmt);
            String newDateUi = LocalDate.parse(newDate, inputFmt).format(uiFmt);

            System.out.println("🔍 Searching WeekOff card for:");
            System.out.println("   EmpID : " + empHash);
            System.out.println("   Old Date : " + oldDateUi);
            System.out.println("   New Date : " + newDateUi);

            boolean found = false;
            int maxScrolls = 15;

            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;

            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {

                // Step 1 – Find all cards related to emp id
                List<WebElement> cards = driverAndroid.findElements(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
                );

                if (!cards.isEmpty()) {
                    String currentTop = cards.get(0).getAttribute("content-desc");
                    if (currentTop.equals(lastTopCard)) break; // Stop if same card repeated
                    lastTopCard = currentTop;
                }

                // Step 2 – Inspect each card
                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    if (content.contains(empHash) &&
                            content.contains("Old Week-Off Date") &&
                            content.contains(oldDateUi) &&
                            content.contains("New Week-Off Date") &&
                            content.contains(newDateUi)) {

                        System.out.println("✅ Found matching WeekOff card!");
                        return true;
                    }
                }

                // Step 3 – Scroll down
                Map<String, Object> params = new HashMap<>();
                params.put("left", 0);
                params.put("top", (int) (screenHeight * 0.2));
                params.put("width", screenWidth);
                params.put("height", (int) (screenHeight * 0.7));
                params.put("direction", "down");
                params.put("percent", 0.85);

                driverAndroid.executeScript("mobile: scrollGesture", params);
                Thread.sleep(900);

                System.out.println("↕️ Scrolling... attempt " + (i + 1));
            }

            // If not found
            exceptionDesc = "❌ WeekOff card NOT found for EmpId=" + empHash +
                    ", Old Date=" + oldDateUi + ", New Date=" + newDateUi;

            System.out.println(exceptionDesc);
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while verifying WeekOff card: " + e.getMessage();
            System.out.println(exceptionDesc);
            return false;
        }
    }

    public boolean approveWeekoffCard(String empId, String oldDate, String newDate) {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            String empHash = "#" + empId;

            // ---- DATE FORMAT HANDLING ----
            DateTimeFormatter inputFmt;

            if (oldDate.contains("-")) {
                if (oldDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                } else {
                    inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }
            } else {
                inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            }

            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

            String oldDateUi = LocalDate.parse(oldDate, inputFmt).format(uiFmt);
            String newDateUi = LocalDate.parse(newDate, inputFmt).format(uiFmt);

            System.out.println("🔍 Searching WeekOff card for:");
            System.out.println("   EmpID : " + empHash);
            System.out.println("   Old Date : " + oldDateUi);
            System.out.println("   New Date : " + newDateUi);

            int maxScrolls = 15;
            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;
            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {

                // FIND CARD BASED ON EMP + OLD DATE + NEW DATE
                List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
                );

                if (!cards.isEmpty()) {
                    String newTop = cards.get(0).getAttribute("content-desc");
                    if (newTop.equals(lastTopCard)) break;
                    lastTopCard = newTop;
                }

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    // Check match
                    if (content.contains(empHash) &&
                        content.contains("Old Week-Off Date") &&
                        content.contains(oldDateUi) &&
                        content.contains("New Week-Off Date") &&
                        content.contains(newDateUi)) {

                        System.out.println("✅ Found matching WeekOff card!");

                        // CLICK APPROVE BUTTON INSIDE THIS CARD
                        try {
                            WebElement approveBtn = card.findElement(
                                    AppiumBy.xpath(".//android.widget.Button[@content-desc='APPROVE']")
                            );

                            utils.waitForEle(approveBtn, "clickable", "", 30);
                            approveBtn.click();

                            System.out.println("🟢 APPROVE button clicked successfully!");
                            return true;

                        } catch (Exception e) {
                            exceptionDesc = "❌ Found card but APPROVE button not clickable: " + e.getMessage();
                            return false;
                        }
                    }
                }

                // SCROLL DOWN
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("left", 0);
                params.put("top", (int)(screenHeight * 0.2));
                params.put("width", screenWidth);
                params.put("height", (int)(screenHeight * 0.7));
                params.put("direction", "down");
                params.put("percent", 0.85);

                driverAndroid.executeScript("mobile: scrollGesture", params);
                Thread.sleep(900);

                System.out.println("↕️ Scrolling... attempt " + (i + 1));
            }

            exceptionDesc = "❌ WeekOff card NOT found for EmpId=" + empHash +
                    ", Old Date=" + oldDateUi + ", New Date=" + newDateUi;
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while verifying WeekOff card: " + e.getMessage();
            return false;
        }
    }

    public boolean approveOrRejectWeekoffCard(String empId, String oldDate, String newDate, String action) {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            String empHash = "#" + empId;

            // ---- DATE FORMAT HANDLING ----
            DateTimeFormatter inputFmt;

            if (oldDate.contains("-")) {
                if (oldDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                } else {
                    inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }
            } else {
                inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            }

            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

            String oldDateUi = LocalDate.parse(oldDate, inputFmt).format(uiFmt);
            String newDateUi = LocalDate.parse(newDate, inputFmt).format(uiFmt);

            System.out.println("🔍 Searching WeekOff card for:");
            System.out.println("   EmpID : " + empHash);
            System.out.println("   Old Date : " + oldDateUi);
            System.out.println("   New Date : " + newDateUi);
            System.out.println("   Action  : " + action);

            int maxScrolls = 15;
            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;
            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {

                List<WebElement> cards = driverAndroid.findElements(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
                );

                if (!cards.isEmpty()) {
                    String newTop = cards.get(0).getAttribute("content-desc");
                    if (newTop.equals(lastTopCard)) break;
                    lastTopCard = newTop;
                }

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    if (content.contains(empHash) &&
                            content.contains("Old Week-Off Date") &&
                            content.contains(oldDateUi) &&
                            content.contains("New Week-Off Date") &&
                            content.contains(newDateUi)) {

                        System.out.println("✅ Found matching WeekOff card!");

                        try {
                            // Action → APPROVE or REJECT
                            WebElement actionBtn = card.findElement(
                                    AppiumBy.xpath(".//android.widget.Button[@content-desc='" + action + "']")
                            );

                            utils.waitForEle(actionBtn, "clickable", "", 30);
                            actionBtn.click();

                            System.out.println("🟢 '" + action + "' button clicked successfully!");
                            return true;

                        } catch (Exception e) {
                            exceptionDesc = "❌ Found card but '" + action + "' button not clickable: " + e.getMessage();
                            return false;
                        }
                    }
                }

                // Scroll
                Map<String, Object> params = new HashMap<>();
                params.put("left", 0);
                params.put("top", (int) (screenHeight * 0.2));
                params.put("width", screenWidth);
                params.put("height", (int) (screenHeight * 0.7));
                params.put("direction", "down");
                params.put("percent", 0.85);

                driverAndroid.executeScript("mobile: scrollGesture", params);
                Thread.sleep(900);

                System.out.println("↕️ Scrolling... attempt " + (i + 1));
            }

            exceptionDesc = "❌ WeekOff card NOT found for EmpId=" + empHash +
                    ", Old Date=" + oldDateUi + ", New Date=" + newDateUi;
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while verifying WeekOff card: " + e.getMessage();
            return false;
        }
    }

    
    
    public boolean verifyWeekoffStatusCard(String empId, String oldDate, String expectedStatus) {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            String empHash = "#" + empId;

            // ---- DATE FORMAT FIX ----
            DateTimeFormatter inputFmt;
            if (oldDate.contains("-")) {
                if (oldDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                } else {
                    inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }
            } else {
                inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            }

            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            String oldDateUi = LocalDate.parse(oldDate, inputFmt).format(uiFmt);

            System.out.println("🔍 Searching WeekOff card for:");
            System.out.println("   EmpID  : " + empHash);
            System.out.println("   OldDate: " + oldDateUi);
            System.out.println("   Status : " + expectedStatus);

            int maxScrolls = 15;
            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;
            String lastTopCard = "";

            for (int i = 0; i < maxScrolls; i++) {

                List<WebElement> cards = driverAndroid.findElements(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
                );

                if (!cards.isEmpty()) {
                    String newTop = cards.get(0).getAttribute("content-desc");
                    if (newTop.equals(lastTopCard)) break;
                    lastTopCard = newTop;
                }

                for (WebElement card : cards) {
                    String content = card.getAttribute("content-desc");
                    System.out.println("🔹 Inspecting card:\n" + content);

                    // ---- MATCH ONLY OLD DATE + STATUS ----
                    if (content.contains(oldDateUi) &&
                        content.contains(expectedStatus)) {

                        System.out.println("✅ WeekOff card matched with Status!");

                        return true;
                    }
                }

                // ---- SCROLL ----
                Map<String, Object> params = new HashMap<>();
                params.put("left", 0);
                params.put("top", (int)(screenHeight * 0.2));
                params.put("width", screenWidth);
                params.put("height", (int)(screenHeight * 0.7));
                params.put("direction", "down");
                params.put("percent", 0.85);

                driverAndroid.executeScript("mobile: scrollGesture", params);
                Thread.sleep(900);

                System.out.println("↕️ Scrolling... attempt " + (i + 1));
            }

            exceptionDesc = "❌ WeekOff card NOT found for: OldDate=" + oldDateUi +
                    ", Status=" + expectedStatus;
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Error while verifying WeekOff status card: " + e.getMessage();
            return false;
        }
    }


    	public boolean validateWeekoffCardCount(String statusType, String expectedCount) {
    	    try {
    	        AndroidDriver driverAndroid = (AndroidDriver) driver;

    	        statusType = statusType.trim();
    	        expectedCount = expectedCount.trim();

    	        // Fetch ONLY summary cards: they always have exactly 2-line content-desc
    	        List<WebElement> allViews = driverAndroid.findElements(
    	                AppiumBy.xpath("//android.view.View[@content-desc]")
    	        );

    	        for (WebElement card : allViews) {

    	            String desc = card.getAttribute("content-desc").trim();
    	            String[] parts = desc.split("\\n");

    	            // Skip detailed week-off cards (more than 2 lines)
    	            if (parts.length != 2) continue;

    	            String actualCount = parts[0].trim();
    	            String actualStatus = parts[1].trim();

    	            // Match status like: Approved, Rejected, Revoked, Pending, All
    	            if (actualStatus.equalsIgnoreCase(statusType)) {

    	                if (actualCount.equals(expectedCount)) {
    	                    System.out.println("✅ Match → " + statusType +
    	                            " Expected: " + expectedCount +
    	                            " Actual: " + actualCount);
    	                    return true;
    	                } else {
    	                    exceptionDesc = "❌ Count mismatch for " + statusType +
    	                            " — Expected: " + expectedCount + ", Actual: " + actualCount;
    	                    return false;
    	                }
    	            }
    	        }

    	        exceptionDesc = "⚠️ Status card not found: " + statusType;
    	        return false;

    	    } catch (Exception e) {
    	        exceptionDesc = "❌ Error validating card count: " + e.getMessage();
    	        return false;
    	    }
    	}

    	public boolean WeekOffRequestEmpNameSelected() {
    	    try {
    	        
    	        utils.waitForEle(WeekOffRequestEmpNameSelected, "visible", "", 30);
    	        WeekOffRequestEmpNameSelected.click();
    	    } catch (Exception e) {
    	        exceptionDesc = e.getMessage().toString();
    	        return false;
    	    }
    	    return true;
    	}
    
    	public boolean NewWeekOffDateClickedOnAdmin() {
    	    try {
    	        
    	        utils.waitForEle(NewWeekOffDateClickedOnAdmin, "visible", "", 30);
    	        NewWeekOffDateClickedOnAdmin.click();
    	    } catch (Exception e) {
    	        exceptionDesc = e.getMessage().toString();
    	        return false;
    	    }
    	    return true;
    	}
    	
    

	
	
    public String getExceptionDesc() {
        return exceptionDesc;
    }	
}
