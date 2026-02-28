package com.MeghPI.Attendance.Android.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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

public class MeghPIAttendAndroidLeavePage {
	private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((HasSettings) driver).setSetting("enforceXPath1", true);

    }

    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Request') and contains(@content-desc,'Tab')]")
    public WebElement LeaveRequestTab;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Apply Leave\"]")
    public WebElement QuickActionLeaveOption;
  
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Leave\"]")
    public WebElement LeaveButtonOnAdminDashBoard;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Emptwentyfive Morning shift (Emp025)\"]")
    public WebElement EmpSelectedForLeaveForOthers;

  

    @FindBy(xpath = "//android.widget.ScrollView/android.view.View[4]")
    public WebElement LeaveTypeDropDownOnAdmin;
  
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[1]")
    public WebElement LeaveFromDateOnAdmin;
  

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]")
    public WebElement LeaveDurationOneOnAdmin;
  
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")
    public WebElement LeaveToDateOnAdmin;
  
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[6]")
    public WebElement LeaveDurationTwoOnAdmin;

    @FindBy(xpath = "//android.view.View[@content-desc=\"First Half Day\"]")
    public WebElement FirstHalfDayLeaveDuration;
  
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Casual Leave')]")
    private WebElement CasualLeaveTypeSelected;  //3rd TC
    
    
    @FindBy(xpath = "//android.view.View[@content-desc=\"Second Half Day\"]")
    private WebElement SecondHalfDayLeaveDuration;  //3rd TC
  
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Day(s)')]")
    private WebElement ZeroLeaveBalance;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Balance') and contains(@content-desc,'Tab')]")
    private WebElement BalanceTabOnAdmin;

    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Emptwentyfive')]")
    private WebElement employeeNameClickOnBalance;

 
    
    
    
    
    

    
    public boolean LeaveButtonOnEmpDashBoard() {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            // Scroll until Leave button is visible
            WebElement leaveCard = driverAndroid.findElement(
                AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().className(\"android.widget.ImageView\")" +
                    ".descriptionContains(\"Leave\"))"
                )
            );

            // Wait until clickable
            WebDriverWait wait = new WebDriverWait(driverAndroid, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(leaveCard));

            leaveCard.click();
            System.out.println("✅ Leave button clicked successfully.");

            // Wait for Leave tab (Replace LeaveTabOfEmp with your locator)
            utils.waitForEle(LeaveRequestTab, "visible", "", 40);

            return true;

        } catch (Exception e) {
            System.out.println("❌ Error while clicking Leave button: " + e.getMessage());
            exceptionDesc = e.getMessage();
            return false;
        }
    }

    
    
    
 public boolean QuickActionLeaveOption() {
        
        try {
			utils.waitForEle(QuickActionLeaveOption, "visible", "", 30);
			QuickActionLeaveOption.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
    
 public boolean validateLeaveCard(String fromDateExcel, String toDateExcel, String totalDays, String expectedStatus) {
	    try {
	        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDateExcel, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDateExcel, inputFmt).format(uiFmt);
	        String statusLower = expectedStatus.toLowerCase(Locale.ENGLISH);

	        System.out.println("🔍 Searching Leave Card:");
	        System.out.println("   📅 From Date  : " + fromDateUI);
	        System.out.println("   📅 To Date    : " + toDateUI);
	        System.out.println("   📊 Total Days : " + totalDays);
	        System.out.println("   🔖 Status     : " + expectedStatus);

	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lower = "abcdefghijklmnopqrstuvwxyz";

	        // Build dynamic XPath using translate() for case-insensitive match
	        String xpath =
	            "//android.view.View[" +
	            "contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + statusLower + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'from')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + fromDateUI.toLowerCase() + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'to')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + toDateUI.toLowerCase() + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + totalDays.toLowerCase() + "')" +
	            "]";

	        System.out.println("🔎 Final XPath: " + xpath);

	        Dimension size = driverAndroid.manage().window().getSize();
	        int width = size.width;
	        int height = size.height;

	        // Scroll 12 times max
	        for (int i = 0; i < 12; i++) {

	            List<WebElement> cards = driverAndroid.findElements(AppiumBy.xpath(xpath));

	            if (!cards.isEmpty()) {
	                WebElement card = cards.get(0);
	                utils.waitForEle(card, "visible", "", 30);

	                System.out.println("✅ Found Leave Card:\n" + card.getAttribute("content-desc"));
	                return true;
	            }

	            // Scroll
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

	        exceptionDesc = "❌ Leave Card NOT FOUND for From:" + fromDateUI +
	                        ", To:" + toDateUI +
	                        ", Days:" + totalDays +
	                        ", Status:" + expectedStatus;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error validating leave card: " + e.getMessage();
	        return false;
	    }
	}

    
 public boolean clickLeaveTab(String tabName) {
	    try {
	        // TabName examples: "Applied", "Pending", "Approved", "Rejected", "Revoked", "Cancelled"
	        
	        // Case-insensitive match & ignores the number before tab name
	        String xpath = "//android.view.View[" +
	                       "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" +
	                       tabName.toLowerCase() + "')" +
	                       "]";

	        WebElement tabElement = driver.findElement(By.xpath(xpath));
	        utils.waitForEle(tabElement, "clickable", "", 30);
	        tabElement.click();
	        Thread.sleep(800);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while clicking Leave tab '" + tabName + "': " + e.getMessage();
	        return false;
	    }
	}

 
    
 public boolean LeaveButtonOnAdminDashBoard() {
     
     try {
			utils.waitForEle(LeaveButtonOnAdminDashBoard, "visible", "", 30);
			LeaveButtonOnAdminDashBoard.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}  
    
    
 public boolean approveLeaveCard(String empId, String leaveType, String fromDate, String toDate) {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String empHash = "#" + empId;

	        // ---------------- DATE FORMAT HANDLING ----------------
	        DateTimeFormatter inputFmt;

	        if (fromDate.contains("-")) {
	            if (fromDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
	                inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            } else {
	                inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            }
	        } else {
	            inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
	        }

	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDate, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDate, inputFmt).format(uiFmt);

	        System.out.println("🔍 Searching Leave Card:");
	        System.out.println("   👤 EmpID      : " + empHash);
	        System.out.println("   🏷 Leave Type : " + leaveType);
	        System.out.println("   📅 From Date  : " + fromDateUI);
	        System.out.println("   📅 To Date    : " + toDateUI);

	        // ---------------- SCROLL LOGIC ----------------
	        int maxScrolls = 15;
	        Dimension size = driverAndroid.manage().window().getSize();
	        int screenHeight = size.height;
	        int screenWidth = size.width;

	        String lastTopCard = "";

	        for (int i = 0; i < maxScrolls; i++) {

	            // Find all cards containing Emp ID
	            List<WebElement> cards = driverAndroid.findElements(
	                AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
	            );

	            if (!cards.isEmpty()) {
	                String newTop = cards.get(0).getAttribute("content-desc");
	                if (lastTopCard.equals(newTop)) {
	                    break; // no new cards appearing → end scroll
	                }
	                lastTopCard = newTop;
	            }

	            // Inspect each card for matching details
	            for (WebElement card : cards) {
	                String content = card.getAttribute("content-desc").trim();
	                System.out.println("🔹 Inspecting card:\n" + content);

	                boolean matches =
	                        content.contains(empHash) &&
	                        content.contains(leaveType) &&
	                        content.contains("From") &&
	                        content.contains(fromDateUI) &&
	                        content.contains("To") &&
	                        content.contains(toDateUI);

	                if (matches) {
	                    System.out.println("✅ Matching Leave Card Found!");

	                    // APPROVE button inside this card
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

	            // Perform scroll
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

	        exceptionDesc = "❌ Leave card NOT found for EmpId=" + empHash +
	                ", LeaveType=" + leaveType +
	                ", From=" + fromDateUI +
	                ", To=" + toDateUI;

	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while approving Leave card: " + e.getMessage();
	        return false;
	    }
	}

 public boolean handleLeaveCard(String empId, String leaveType, String fromDate, String toDate, String actionType) {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String empHash = "#" + empId;

	        // ---------------- DATE FORMAT HANDLING ----------------
	        DateTimeFormatter inputFmt;

	        if (fromDate.contains("-")) {
	            if (fromDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
	                inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            } else {
	                inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            }
	        } else {
	            inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
	        }

	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDate, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDate, inputFmt).format(uiFmt);

	        System.out.println("🔍 Searching Leave Card:");
	        System.out.println("   👤 EmpID      : " + empHash);
	        System.out.println("   🏷 Leave Type : " + leaveType);
	        System.out.println("   📅 From Date  : " + fromDateUI);
	        System.out.println("   📅 To Date    : " + toDateUI);
	        System.out.println("   🎯 Action     : " + actionType);

	        // ---------------- SCROLL LOGIC ----------------
	        int maxScrolls = 15;
	        Dimension size = driverAndroid.manage().window().getSize();
	        int screenHeight = size.height;
	        int screenWidth = size.width;

	        String lastTopCard = "";

	        for (int i = 0; i < maxScrolls; i++) {

	            // Find all cards containing Emp ID
	            List<WebElement> cards = driverAndroid.findElements(
	                AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
	            );

	            if (!cards.isEmpty()) {
	                String newTop = cards.get(0).getAttribute("content-desc");
	                if (lastTopCard.equals(newTop)) {
	                    break; // No new cards appearing
	                }
	                lastTopCard = newTop;
	            }

	            // Inspect each card
	            for (WebElement card : cards) {
	                String content = card.getAttribute("content-desc").trim();
	                System.out.println("🔹 Inspecting card:\n" + content);

	                boolean matches =
	                        content.contains(empHash) &&
	                        content.contains(leaveType) &&
	                        content.contains("From") &&
	                        content.contains(fromDateUI) &&
	                        content.contains("To") &&
	                        content.contains(toDateUI);

	                if (matches) {
	                    System.out.println("✅ Matching Leave Card Found!");

	                    try {
	                        // Dynamic button: REJECT or APPROVE
	                        WebElement actionBtn = card.findElement(
	                                AppiumBy.xpath(".//android.widget.Button[@content-desc='" + actionType.toUpperCase() + "']")
	                        );

	                        utils.waitForEle(actionBtn, "clickable", "", 30);
	                        actionBtn.click();

	                        System.out.println("🟢 " + actionType.toUpperCase() + " button clicked successfully!");
	                        return true;

	                    } catch (Exception e) {
	                        exceptionDesc = "❌ Found card but " + actionType + " button not clickable: " + e.getMessage();
	                        return false;
	                    }
	                }
	            }

	            // Scroll
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

	        exceptionDesc = "❌ Leave card NOT found for EmpId=" + empHash +
	                ", LeaveType=" + leaveType +
	                ", From=" + fromDateUI +
	                ", To=" + toDateUI;

	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while selecting Leave card: " + e.getMessage();
	        return false;
	    }
	}

    
 public boolean validateLeaveCardAndClickOnRevoke(String fromDateExcel, String toDateExcel, String totalDays, String expectedStatus) {
	    try {
	        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDateExcel, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDateExcel, inputFmt).format(uiFmt);
	        String statusLower = expectedStatus.toLowerCase(Locale.ENGLISH);

	        System.out.println("🔍 Searching Leave Card:");
	        System.out.println("   📅 From Date  : " + fromDateUI);
	        System.out.println("   📅 To Date    : " + toDateUI);
	        System.out.println("   📊 Total Days : " + totalDays);
	        System.out.println("   🔖 Status     : " + expectedStatus);

	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lower = "abcdefghijklmnopqrstuvwxyz";

	        // Build case-insensitive matching XPath
	        String xpath =
	            "//android.view.View[" +
	            "contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + statusLower + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'from')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + fromDateUI.toLowerCase() + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), 'to')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + toDateUI.toLowerCase() + "')" +
	            " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + totalDays.toLowerCase() + "')" +
	            "]";

	        System.out.println("🔎 Final XPath: " + xpath);

	        Dimension size = driverAndroid.manage().window().getSize();
	        int width = size.width;
	        int height = size.height;

	        // Scroll and search
	        for (int i = 0; i < 12; i++) {

	            List<WebElement> cards = driverAndroid.findElements(AppiumBy.xpath(xpath));

	            if (!cards.isEmpty()) {
	                WebElement card = cards.get(0);
	                utils.waitForEle(card, "visible", "", 30);

	                System.out.println("✅ Found Leave Card:\n" + card.getAttribute("content-desc"));

	                // 🔥 CLICK REVOKE BUTTON
	                try {
	                    WebElement revokeBtn = card.findElement(
	                            AppiumBy.xpath(".//android.widget.Button[@content-desc='REVOKE']")
	                    );

	                    utils.waitForEle(revokeBtn, "clickable", "", 30);
	                    revokeBtn.click();

	                    System.out.println("🟢 REVOKE button clicked successfully!");
	                    return true;

	                } catch (Exception e) {
	                    exceptionDesc = "❌ Leave card found but REVOKE button not clickable: " + e.getMessage();
	                    return false;
	                }
	            }

	            // Scroll
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

	        exceptionDesc = "❌ Leave Card NOT FOUND for From:" + fromDateUI +
	                        ", To:" + toDateUI +
	                        ", Days:" + totalDays +
	                        ", Status:" + expectedStatus;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error validating and revoking leave card: " + e.getMessage();
	        return false;
	    }
	}

 public boolean validateLeaveCardForRevoked(String empId, String leaveType, String fromDate, String toDate, String expectedStatus) {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String empHash = "#" + empId;

	        // ---------------- DATE FORMAT HANDLING ----------------
	        DateTimeFormatter inputFmt;

	        if (fromDate.contains("-")) {
	            if (fromDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
	                inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            } else {
	                inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            }
	        } else {
	            inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
	        }

	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDate, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDate, inputFmt).format(uiFmt);
	        String statusLower = expectedStatus.toLowerCase();

	        System.out.println("🔍 Validating Leave Card:");
	        System.out.println("   👤 EmpID      : " + empHash);
	        System.out.println("   🏷 Leave Type : " + leaveType);
	        System.out.println("   📅 From Date  : " + fromDateUI);
	        System.out.println("   📅 To Date    : " + toDateUI);
	        System.out.println("   🔖 Status     : " + expectedStatus);

	        // ---------------- SCROLL LOGIC ----------------
	        int maxScrolls = 15;
	        Dimension size = driverAndroid.manage().window().getSize();
	        int screenHeight = size.height;
	        int screenWidth = size.width;

	        String lastTopCard = "";

	        for (int i = 0; i < maxScrolls; i++) {

	            // Find all cards for this Emp ID
	            List<WebElement> cards = driverAndroid.findElements(
	                AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empHash + "')]")
	            );

	            if (!cards.isEmpty()) {
	                String newTop = cards.get(0).getAttribute("content-desc");
	                if (lastTopCard.equals(newTop)) {
	                    break; // same card → no more scrolling
	                }
	                lastTopCard = newTop;
	            }

	            // Validate each card
	            for (WebElement card : cards) {
	                String content = card.getAttribute("content-desc").trim();
	                System.out.println("🔹 Checking card:\n" + content);

	                boolean matches =
	                        content.contains(empHash) &&
	                        content.contains(leaveType) &&
	                        content.toLowerCase().contains(statusLower) &&
	                        content.contains("From") &&
	                        content.contains(fromDateUI) &&
	                        content.contains("To") &&
	                        content.contains(toDateUI);

	                if (matches) {
	                    System.out.println("✅ VALID Leave Card Found & Matches All Conditions!");
	                    return true;
	                }
	            }

	            // Perform scroll
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

	        exceptionDesc = "❌ Leave card NOT found for EmpId=" + empHash +
	                ", LeaveType=" + leaveType +
	                ", Status=" + expectedStatus +
	                ", From=" + fromDateUI +
	                ", To=" + toDateUI;

	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while validating Leave card: " + e.getMessage();
	        return false;
	    }
	}

 
 
 public boolean validateLeaveCardInRevokedcard(String fromDateExcel, String toDateExcel, String totalDays, String expectedStatus) {
	    try {
	        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

	        String fromDateUI = LocalDate.parse(fromDateExcel, inputFmt).format(uiFmt);
	        String toDateUI   = LocalDate.parse(toDateExcel, inputFmt).format(uiFmt);
	        String statusLower = expectedStatus.toLowerCase(Locale.ENGLISH);

	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lower = "abcdefghijklmnopqrstuvwxyz";

	        // New Simplified & Accurate XPath for your card structure
	        String xpath =
	            "//android.view.View[" +
	                "contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + statusLower + "')" +
	                " and contains(@content-desc, '" + fromDateUI + "')" +
	                " and contains(@content-desc, '" + toDateUI + "')" +
	                " and contains(translate(@content-desc,'" + upper + "','" + lower + "'), '" + totalDays.toLowerCase() + "')" +
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

	                System.out.println("✅ Found Leave Card:\n" + card.getAttribute("content-desc"));
	                return true;
	            }

	            // Scroll down
	            HashMap<String, Object> scroll = new HashMap<>();
	            scroll.put("left", 0);
	            scroll.put("top", height * 0.20);
	            scroll.put("width", width);
	            scroll.put("height", height * 0.60);
	            scroll.put("direction", "down");
	            scroll.put("percent", 0.90);

	            driverAndroid.executeScript("mobile: scrollGesture", scroll);
	            Thread.sleep(600);
	        }

	        exceptionDesc = "❌ Leave Card NOT FOUND for From:" + fromDateUI +
	                        ", To:" + toDateUI +
	                        ", Days:" + totalDays +
	                        ", Status:" + expectedStatus;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error validating leave card: " + e.getMessage();
	        return false;
	    }
	}

 
 public boolean EmpSelectedForLeaveForOthers() {
	    try {
	        
	        utils.waitForEle(EmpSelectedForLeaveForOthers, "visible", "", 30);
	        EmpSelectedForLeaveForOthers.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean LeaveTypeDropDownOnAdmin() {
	    try {
	        
	        utils.waitForEle(LeaveTypeDropDownOnAdmin, "visible", "", 30);
	        LeaveTypeDropDownOnAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean LeaveFromDateOnAdmin() {
	    try {
	        
	        utils.waitForEle(LeaveFromDateOnAdmin, "visible", "", 30);
	        LeaveFromDateOnAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
    
 public boolean LeaveDurationOneOnAdmin() {
	    try {
	        
	        utils.waitForEle(LeaveDurationOneOnAdmin, "visible", "", 30);
	        LeaveDurationOneOnAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean LeaveToDateOnAdmin() {
	    try {
	        
	        utils.waitForEle(LeaveToDateOnAdmin, "visible", "", 30);
	        LeaveToDateOnAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 public boolean LeaveDurationTwoOnAdmin() {
	    try {
	        
	        utils.waitForEle(LeaveDurationTwoOnAdmin, "visible", "", 30);
	        LeaveDurationTwoOnAdmin.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 
 public boolean FirstHalfDayLeaveDuration() {
	    try {
	        
	        utils.waitForEle(FirstHalfDayLeaveDuration, "visible", "", 30);
	        FirstHalfDayLeaveDuration.click();
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
 
 
 public boolean CasualLeaveTypeSelected() {
	    
	    try {
	    	Thread.sleep(1000);
			utils.waitForEle(CasualLeaveTypeSelected, "visible", "", 20);
			CasualLeaveTypeSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
 
 public boolean SecondHalfDayLeaveDuration() {
	    
	    try {
	    	Thread.sleep(1000);
			utils.waitForEle(SecondHalfDayLeaveDuration, "visible", "", 20);
			SecondHalfDayLeaveDuration.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
 
 
 
 
 public boolean validateEmployeeNameInLeaveCard(String empNameSubstring) {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        String nameLower = empNameSubstring.trim().toLowerCase(Locale.ENGLISH);

	        String xpath = "//android.view.View[@content-desc]";

	        Dimension size = driverAndroid.manage().window().getSize();
	        int width = size.width;
	        int height = size.height;

	        for (int i = 0; i < 12; i++) {

	            List<WebElement> cards = driverAndroid.findElements(AppiumBy.xpath(xpath));

	            for (WebElement card : cards) {

	                String desc = card.getAttribute("content-desc").trim();
	                String lowerDesc = desc.toLowerCase(Locale.ENGLISH);

	                // detailed cards always contain multiple lines & employee name on top
	                if (desc.split("\\n").length >= 3) {

	                    if (lowerDesc.contains(nameLower)) {
	                        System.out.println("✅ Employee name match found in card:");
	                        System.out.println(desc);
	                        return true;
	                    }
	                }
	            }

	            // Scroll down
	            HashMap<String, Object> scroll = new HashMap<>();
	            scroll.put("left", 0);
	            scroll.put("top", (int) (height * 0.25));
	            scroll.put("width", width);
	            scroll.put("height", (int) (height * 0.6));
	            scroll.put("direction", "down");
	            scroll.put("percent", 0.85);

	            driverAndroid.executeScript("mobile: scrollGesture", scroll);
	            Thread.sleep(700);
	        }

	        exceptionDesc = "❌ Employee name not found in any leave card: " + empNameSubstring;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error validating employee name in leave card: " + e.getMessage();
	        return false;
	    }
	}

 public boolean ZeroLeaveBalance(String zero) {
	    try {
	        utils.waitForEle(ZeroLeaveBalance, "visible", "", 20);

	        String uiText = ZeroLeaveBalance.getAttribute("content-desc").trim();
	        System.out.println("UI Message: " + uiText);

	        if (uiText.equals(zero + " Day(s)")) {
	            return true;
	        } else {
	            exceptionDesc = "Expected: " + zero + " Day(s) but found: " + uiText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Error validating zero leave balance: " + e.getMessage();
	        return false;
	    }
	}


 public boolean validateTotals(String empName, String expectedTotal, String expectedDeducted, String expectedAvailable) {
	    try {
	        // 🔹 Dynamic XPath using employee name only
	        String xpath = "//android.view.View[contains(@content-desc,'" + empName + "')]";

	        // 🔹 Wait & find element
	        WebElement block = driver.findElement(By.xpath(xpath));

	        // 🔹 Read content-desc
	        String content = block.getAttribute("content-desc").trim();
	        System.out.println("UI Content:\n" + content);

	        // 🔹 Split by line breaks
	        String[] lines = content.split("\n");

	        String total = "", deducted = "", available = "";

	        // 🔍 Extract values
	        for (int i = 0; i < lines.length; i++) {
	            if (lines[i].contains("Total") && i + 1 < lines.length) {
	                total = lines[i + 1].trim();
	            }
	            if (lines[i].contains("Deducted") && i + 1 < lines.length) {
	                deducted = lines[i + 1].trim();
	            }
	            if (lines[i].contains("Available") && i + 1 < lines.length) {
	                available = lines[i + 1].trim();
	            }
	        }

	        // 🔸 Validation
	        if (!total.contains(expectedTotal)) {
	            exceptionDesc = "Total mismatch. Expected: " + expectedTotal + " but found: " + total;
	            return false;
	        }

	        if (!deducted.contains(expectedDeducted)) {
	            exceptionDesc = "Deducted mismatch. Expected: " + expectedDeducted + " but found: " + deducted;
	            return false;
	        }

	        if (!available.contains(expectedAvailable)) {
	            exceptionDesc = "Available mismatch. Expected: " + expectedAvailable + " but found: " + available;
	            return false;
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error validating totals: " + e.getMessage();
	        return false;
	    }
	}

 
 
 public boolean BalanceTabOnAdmin() {
	    
	    try {
	    	Thread.sleep(1000);
			utils.waitForEle(BalanceTabOnAdmin, "visible", "", 20);
			BalanceTabOnAdmin.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
 
 
 
 
 public boolean clickHolidayCard() {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        // XPath to pick ANY holiday card
	        String xpath = "//android.widget.ImageView[contains(@content-desc, 'Holiday')]";

	        // Scroll until holiday card is visible
	        WebElement holidayCard = driverAndroid.findElement(
	                AppiumBy.androidUIAutomator(
	                        "new UiScrollable(new UiSelector().scrollable(true))" +
	                        ".scrollIntoView(new UiSelector().descriptionContains(\"Holiday\"))"
	                )
	        );

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driverAndroid, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(holidayCard));

	        holidayCard.click();
	        System.out.println("✅ Holiday card clicked successfully.");

	        return true;

	    } catch (Exception e) {
	        System.out.println("❌ Error clicking holiday card: " + e.getMessage());
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

 
 public boolean validateFestivalsPresent() {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        // Scroll entire list once (big scroll)
	        driverAndroid.findElement(
	                AppiumBy.androidUIAutomator(
	                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(5)"
	                )
	        );

	        // Fetch all holiday elements
	        List<WebElement> holidayElements = driverAndroid.findElements(
	                By.xpath("//android.view.View[@content-desc]")
	        );

	        // Extract holiday names from content-desc
	        Set<String> uiHolidayNames = new HashSet<>();

	        for (WebElement ele : holidayElements) {
	            String content = ele.getAttribute("content-desc").trim();

	            if (content.contains("\n")) {
	                String name = content.split("\n")[0].trim();
	                uiHolidayNames.add(name);
	            }
	        }

	        // Festivals to validate
	        String[] festivals = {
	                "RakshaBandhan",
	                "Janamashtami",
	                "Dussehra",
	                "Diwali",
	                "New Year",
	                "Bhai Bij"
	        };

	        // Check missing festivals
	        List<String> missing = new ArrayList<>();

	        for (String fest : festivals) {
	            if (!uiHolidayNames.contains(fest)) {
	                missing.add(fest);
	            }
	        }

	        if (missing.isEmpty()) {
	            System.out.println("✅ All required festivals are present.");
	            return true;
	        } else {
	            System.out.println("❌ Missing Festivals: " + missing);
	            exceptionDesc = "Missing festivals: " + missing;
	            return false;
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Error validating festivals: " + e.getMessage());
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

 
 
    
    
    
    
    public String getExceptionDesc() {
        return exceptionDesc;
    }	
	
    
    
}
