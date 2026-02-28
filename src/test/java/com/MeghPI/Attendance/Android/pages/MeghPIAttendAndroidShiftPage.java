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

public class MeghPIAttendAndroidShiftPage {

	private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidShiftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((HasSettings) driver).setSetting("enforceXPath1", true);

    }

    
    @FindBy(xpath = "//android.view.View[@content-desc=\"Shift\"]")
    private WebElement ShiftPageLoaded;
	
    @FindBy(xpath = "//android.widget.ScrollView/android.view.View[7]")
    private WebElement SelectShiftPolicyDropDown;
  
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Morning')]")
    private WebElement MorningShiftPolicySelected;

  
    @FindBy(xpath = "//android.widget.ScrollView/android.view.View[8]")
    private WebElement SelectShiftTypeDropDown;
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Morning Shift')]")
    private WebElement MorningShiftTypeSelected;
    
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Shift\"]")
    private WebElement ShiftButtonAdminModule;
  
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Approved')]")
    private WebElement ApprovedCountLabel;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Pending')]")
    private WebElement PendingCountLabel;
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Rejected')]")
    private WebElement RejectedCountLabel;
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Revoked')]")
    private WebElement RevokedCountLabel;
    
  
    @FindBy(xpath = "//android.view.View[@content-desc=\"Shift\"]")
    private WebElement ShiftTextVisible;
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc,'Shift') and contains(@content-desc,'Tab')]")
    private WebElement ShiftTabOnEmp;


    
  public boolean RejectedCountLabel() {
        
        try {
    		utils.waitForEle(RejectedCountLabel, "visible", "", 20);
    		RejectedCountLabel.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }	
  
  public boolean RevokedCountLabel() {
      
      try {
  		utils.waitForEle(RevokedCountLabel, "visible", "", 20);
  		RevokedCountLabel.click();
  	} catch (Exception e) {
  		exceptionDesc=	e.getMessage().toString();
  		return false;
  	}
  	return true;
  }	
    
    
    
    public boolean ShiftTabOnEmp() {
        
        try {
    		utils.waitForEle(ShiftTabOnEmp, "visible", "", 20);
    		ShiftTabOnEmp.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }	
    
    

public boolean ApprovedCountLabel() {
    
    try {
		utils.waitForEle(ApprovedCountLabel, "visible", "", 20);
		ApprovedCountLabel.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}	
    
    
    
	
	
    public boolean ShiftButtonOnEmpDashBoard() {
        try {
            AndroidDriver driverAndroid = (AndroidDriver) driver;

            // Scroll until Shift button is visible
            WebElement shiftCard = driverAndroid.findElement(
                AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().className(\"android.widget.ImageView\")" +
                    ".descriptionContains(\"Shift\"))"
                )
            );

            // Wait until clickable
            WebDriverWait wait = new WebDriverWait(driverAndroid, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(shiftCard));

            shiftCard.click();
            System.out.println("✅ Shift button clicked successfully.");

            // Wait for Shift tab of employee
            utils.waitForEle(ShiftPageLoaded, "visible", "", 30);

            return true;

        } catch (Exception e) {
            System.out.println("❌ Error while clicking Shift button: " + e.getMessage());
            exceptionDesc = e.getMessage();
            return false;
        }
    }

    public boolean clickShiftByDate(String dateFromExcel) {
        try {

            // --- Step 1: Convert yyyy-MM-dd → MMM dd (UI format) ---
            LocalDate date = LocalDate.parse(dateFromExcel);
            DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH);
            String formattedDate = date.format(uiFormatter);   // Example: "Nov 01"

            System.out.println("🔍 Searching Shift Date: " + formattedDate);

            AndroidDriver driverAndroid = (AndroidDriver) driver;

            int maxScrolls = 15;
            Dimension size = driverAndroid.manage().window().getSize();
            int screenHeight = size.height;
            int screenWidth = size.width;

            String lastTopCard = "";  // to detect end of list

            for (int i = 0; i < maxScrolls; i++) {

                // --- Step 2: Find all date cards containing formatted date ---
                List<WebElement> cards = driverAndroid.findElements(
                    By.xpath("//android.view.View[contains(@content-desc,'" + formattedDate + "')]")
                );

                if (!cards.isEmpty()) {

                    // check if list stopped moving
                    String currentTopCard = cards.get(0).getAttribute("content-desc");
                    if (currentTopCard.equals(lastTopCard)) {
                        System.out.println("🛑 No more new items to scroll.");
                        break;
                    }
                    lastTopCard = currentTopCard;

                    // --- Step 3: Click the card ---
                    WebElement card = cards.get(0);  // first match in view

                    WebDriverWait wait = new WebDriverWait(driverAndroid, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.elementToBeClickable(card));

                    card.click();
                    System.out.println("✅ Clicked Shift Date: " + formattedDate);
                    return true;
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
                Thread.sleep(700);
                System.out.println("↕️ Scrolling... Attempt: " + (i + 1));
            }

            System.out.println("❌ Shift date not found: " + formattedDate);
            exceptionDesc = "Shift date not found: " + formattedDate;
            return false;

        } catch (Exception e) {
            System.out.println("❌ Error clicking shift date: " + e.getMessage());
            exceptionDesc = e.getMessage();
            return false;
        }
    }


    public boolean SelectShiftPolicyDropDown() {
        
        try {
    		utils.waitForEle(SelectShiftPolicyDropDown, "visible", "", 20);
    		SelectShiftPolicyDropDown.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }
    
    public boolean ShiftButtonAdminModule() {
        try {
            // Wait until the shift button is visible
            utils.waitForEle(ShiftButtonAdminModule, "visible", "", 20);

            // Click the Shift button
            ShiftButtonAdminModule.click();

            // If expected text is not displayed, refresh the page and click again
            if (!PendingCountLabel.isDisplayed()) {
                System.out.println("Shift text not visible, refreshing the page...");
                driver.navigate().refresh(); // refresh the page
                Thread.sleep(1000); // wait for page to reload

                // Wait and click the Shift button again
                utils.waitForEle(ShiftButtonAdminModule, "visible", "", 20);
                ShiftButtonAdminModule.click();
            }

            return true;

        } catch (Exception e) {
            exceptionDesc = "Error in ShiftButtonAdminModule(): " + e.getMessage();
            return false;
        }
    }


public boolean PendingCountLabel() {
    
    try {
		utils.waitForEle(PendingCountLabel, "visible", "", 20);
		PendingCountLabel.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}	


 public boolean MorningShiftPolicySelected() {
        
        try {
    		utils.waitForEle(MorningShiftPolicySelected, "visible", "", 20);
    		MorningShiftPolicySelected.click();
    	} catch (Exception e) {
    		exceptionDesc=	e.getMessage().toString();
    		return false;
    	}
    	return true;
    }
	
 public boolean SelectShiftTypeDropDown() {
     
     try {
 		utils.waitForEle(SelectShiftTypeDropDown, "visible", "", 20);
 		SelectShiftTypeDropDown.click();
 	} catch (Exception e) {
 		exceptionDesc=	e.getMessage().toString();
 		return false;
 	}
 	return true;
 }
 
public boolean MorningShiftTypeSelected() {
     
     try {
 		utils.waitForEle(MorningShiftTypeSelected, "visible", "", 20);
 		MorningShiftTypeSelected.click();
 	} catch (Exception e) {
 		exceptionDesc=	e.getMessage().toString();
 		return false;
 	}
 	return true;
 }
 



		public boolean navigateToRequiredMonthofshift(String excelDate) {
		    try {
		        LocalDate targetDate = LocalDate.parse(excelDate);
		        String targetMonthYear =
		                targetDate.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));

		        System.out.println("Target Month-Year: " + targetMonthYear);

		        for (int i = 0; i < 12; i++) {

		            // 1️⃣ Get current month header (ONLY month-year)
		            WebElement monthHeader = driver.findElement(
		                    AppiumBy.xpath("//android.view.View[@content-desc and contains(@content-desc,' ')]")
		            );

		            String currentMonthYear = monthHeader.getAttribute("content-desc").trim();
		            System.out.println("Current UI Month-Year: " + currentMonthYear);

		            // 2️⃣ If matched, stop
		            if (currentMonthYear.equalsIgnoreCase(targetMonthYear)) {
		                System.out.println("✅ Month matched successfully");
		                return true;
		            }

		            // 3️⃣ Click BACK button using FULL dynamic XPath
		            WebElement backBtn = driver.findElement(
		                    AppiumBy.xpath("//android.view.View[@content-desc='" 
		                                    + currentMonthYear + 
		                                    "']/android.widget.ImageView[1]")
		            );

		            backBtn.click();
		            System.out.println("⬅ Clicked Previous month... Attempt " + (i + 1));

		            Thread.sleep(1000); // allow UI refresh
		        }

		        exceptionDesc = "Failed to navigate to target month: " + targetMonthYear;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Exception in navigateToRequiredMonth(): " + e.getMessage();
		        return false;
		    }
		}


		public boolean navigateToRequiredMonthOnAdminShift(String excelDate) {
		    try {
		        LocalDate targetDate = LocalDate.parse(excelDate);
		        String targetMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));
		        System.out.println("Target Month-Year: " + targetMonthYear);

		        for (int i = 0; i < 12; i++) {

		            // Directly locate the back button of the currently visible month container using "Total Employees"
		            List<WebElement> backButtons = driver.findElements(
		                AppiumBy.xpath("//android.view.View[contains(@content-desc,'Total Employees')]/android.widget.ImageView[1]")
		            );

		            if (!backButtons.isEmpty()) {
		                WebElement backBtn = backButtons.get(0);

		                // Click the back button to go to the previous month
		                backBtn.click();
		                System.out.println("⬅ Clicked Previous month... Attempt " + (i + 1));
		                Thread.sleep(1000);

		                // Optional: check if target month is now visible
		                List<WebElement> monthContainers = driver.findElements(
		                    AppiumBy.xpath("//android.view.View[contains(@content-desc, '" + targetMonthYear.substring(0, 3) + "')]")
		                );
		                if (!monthContainers.isEmpty()) {
		                    System.out.println("✅ Target month container visible now: " + targetMonthYear);
		                    return true;
		                }

		            } else {
		                System.out.println("Back button not found, retrying...");
		                Thread.sleep(1000);
		            }
		        }

		        exceptionDesc = "Failed to navigate to target month: " + targetMonthYear;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Exception in navigateToRequiredMonthOnAdminShift(): " + e.getMessage();
		        return false;
		    }
		}









		public boolean approveShiftByEmpAndDate(String empId, String excelDate) {
		    try {

		        // 🔹 Convert yyyy-MM-dd → Monday - 03 Nov 2025
		        LocalDate date = LocalDate.parse(excelDate);
		        DateTimeFormatter uiDateFormat =
		                DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
		        String expectedUIDate = date.format(uiDateFormat);

		        System.out.println("Searching Emp: #" + empId + " | Date: " + expectedUIDate);

		        int maxScrolls = 10;
		        AndroidDriver driverAndroid = (AndroidDriver) driver;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // 🔹 Get all visible shift cards
		            List<WebElement> cards = driverAndroid.findElements(
		                    AppiumBy.xpath("//android.view.View[@content-desc and contains(@content-desc,'#Emp')]")
		            );

		            for (WebElement card : cards) {

		                String cardInfo = card.getAttribute("content-desc");

		                boolean empMatch = cardInfo.contains("#" + empId);
		                boolean dateMatch = cardInfo.contains(expectedUIDate);

		                if (empMatch && dateMatch) {

		                    System.out.println("✅ Matching card found:\n" + cardInfo);

		                    // 🔹 Click APPROVE button inside SAME card
		                    WebElement approveBtn = card.findElement(
		                            AppiumBy.xpath(".//android.widget.Button[@content-desc='APPROVE']")
		                    );

		                    approveBtn.click();
		                    return true;
		                }
		            }

		            // 🔹 Scroll up to load next cards
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.7);
		            int endY   = (int) (size.height * 0.3);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
		            Thread.sleep(600);
		        }

		        exceptionDesc = "No matching shift card found for EmpID: #" + empId +
		                        " and Date: " + expectedUIDate;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error while approving shift: " + e.getMessage();
		        return false;
		    }
		}


		public boolean validateShiftCardByEmpAndStatus(String empId, String expectedStatus) {
		    try {
		        AndroidDriver driverAndroid = (AndroidDriver) driver;
		        int maxScrolls = 10;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // 1️⃣ Get all visible shift cards
		            List<WebElement> cards = driverAndroid.findElements(
		                AppiumBy.xpath("//android.view.View[contains(@content-desc,'#Emp')]")
		            );

		            for (WebElement card : cards) {
		                String cardText = card.getAttribute("content-desc");

		                // 2️⃣ Check if card contains expected empId and status
		                boolean empMatch = cardText.contains("#" + empId);
		                boolean statusMatch = cardText.toLowerCase().contains(expectedStatus.toLowerCase());

		                if (empMatch && statusMatch) {
		                    System.out.println("✅ Found matching card:\n" + cardText);
		                    return true; // Match found, exit
		                }
		            }

		            // 3️⃣ Scroll up to load next cards using Pramod.swipe()
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.7);
		            int endY   = (int) (size.height * 0.3);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
		            Thread.sleep(800);
		        }

		        exceptionDesc = "No shift card found for EmpID: " + empId + " with Status: " + expectedStatus;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Exception in validateShiftCardByEmpAndStatus(): " + e.getMessage();
		        return false;
		    }
		}



		public boolean validateShiftCardByDateAndStatus(String excelDate, String expectedStatus) {
		    try {
		        AndroidDriver driverAndroid = (AndroidDriver) driver;

		        // Convert yyyy-MM-dd → "Monday - 03 Nov 2025"
		        LocalDate date = LocalDate.parse(excelDate);
		        DateTimeFormatter uiDateFormat =
		                DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
		        String expectedUIDate = date.format(uiDateFormat);

		        System.out.println("Searching for card with Date: " + expectedUIDate + " | Status: " + expectedStatus);

		        int maxScrolls = 10;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // Get all visible cards
		            List<WebElement> cards = driverAndroid.findElements(
		                    AppiumBy.xpath("//android.view.View[@content-desc and contains(@content-desc,'" + expectedUIDate + "')]")
		            );

		            for (WebElement card : cards) {
		                String cardText = card.getAttribute("content-desc");

		                boolean dateMatch = cardText.contains(expectedUIDate);
		                boolean statusMatch = cardText.toLowerCase().contains(expectedStatus.toLowerCase());

		                if (dateMatch && statusMatch) {
		                    System.out.println("✅ Matching card found:\n" + cardText);
		                    return true; // Match found
		                }
		            }

		            // Scroll up to load more cards
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.7);
		            int endY   = (int) (size.height * 0.3);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
		            Thread.sleep(800);
		        }

		        exceptionDesc = "No shift card found for Date: " + expectedUIDate + " with Status: " + expectedStatus;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error while validating shift card: " + e.getMessage();
		        return false;
		    }
		}



		public boolean navigateToExpectedMonth(String excelDate) {
		    try {
		        // yyyy-MM-dd → Dec 2025
		        LocalDate date = LocalDate.parse(excelDate);
		        String expectedMonthYear =
		                date.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));

		        System.out.println("Expected Month-Year: " + expectedMonthYear);

		        int maxAttempts = 12;

		        for (int i = 0; i < maxAttempts; i++) {

		            // 1️⃣ Read current month text
		            WebElement monthHeader = driver.findElement(
		                AppiumBy.xpath("//android.view.View[@content-desc and contains(@content-desc,'202')]")
		            );

		            String currentMonth = monthHeader.getAttribute("content-desc").trim();
		            System.out.println("Current UI Month-Year: " + currentMonth);

		            // 2️⃣ If matched → return true
		            if (currentMonth.equalsIgnoreCase(expectedMonthYear)) {
		                System.out.println("✅ Month matched");
		                return true;
		            }

		            // 3️⃣ If not matched → click NEXT arrow
		            WebElement nextBtn = driver.findElement(
		                AppiumBy.xpath("//android.widget.ScrollView/android.widget.ImageView[2]")
		            );

		            nextBtn.click();
		            Thread.sleep(800);
		        }

		        exceptionDesc = "Target month not found: " + expectedMonthYear;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error in navigateToExpectedMonth(): " + e.getMessage();
		        return false;
		    }
		}


		public boolean clickCalendarDate(String excelDate) {
		    try {
		        // yyyy-MM-dd → extract day (NO leading zero)
		        LocalDate date = LocalDate.parse(excelDate);
		        int day = date.getDayOfMonth(); // 3 (not 03)

		        System.out.println("Clicking calendar day: " + day);

		        AndroidDriver driverAndroid = (AndroidDriver) driver;
		        int maxScrolls = 6;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // 🔹 Find all visible calendar cells
		            List<WebElement> days = driverAndroid.findElements(
		                AppiumBy.xpath("//android.view.View[@content-desc]")
		            );

		            for (WebElement dayCell : days) {

		                String cellText = dayCell.getAttribute("content-desc");

		                // Example: "3\nP"
		                if (cellText.startsWith(day + "\n")) {
		                    System.out.println("✅ Found day cell: " + cellText);
		                    dayCell.click();
		                    return true;
		                }
		            }

		            // 🔹 Scroll calendar if not found
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.65);
		            int endY   = (int) (size.height * 0.35);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 500);
		            Thread.sleep(500);
		        }

		        exceptionDesc = "Calendar date not found: " + day;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error clicking calendar date: " + e.getMessage();
		        return false;
		    }
		}


		public boolean validateShiftNameInCard(String expectedShiftName) {
		    try {

		        AndroidDriver driverAndroid = (AndroidDriver) driver;
		        int maxScrolls = 8;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // 🔹 Get all visible cards
		            List<WebElement> cards = driverAndroid.findElements(
		                AppiumBy.xpath("//android.view.View[@content-desc]")
		            );

		            for (WebElement card : cards) {

		                String cardText = card.getAttribute("content-desc");

		                // 🔹 Check only shift name (ignore time)
		                if (cardText.contains(expectedShiftName)) {

		                    System.out.println("✅ Shift matched: " + expectedShiftName);
		                    System.out.println("Card content:\n" + cardText);
		                    return true;
		                }
		            }

		            // 🔹 Scroll if not found
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.7);
		            int endY   = (int) (size.height * 0.3);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
		            Thread.sleep(600);
		        }

		        exceptionDesc = "Shift not found in any card. Expected: " + expectedShiftName;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error while validating shift name: " + e.getMessage();
		        return false;
		    }
		}


		public boolean rejectShiftByEmpAndDate(String empId, String excelDate) {
		    try {

		        // 🔹 Convert yyyy-MM-dd → Tuesday - 04 Nov 2025
		        LocalDate date = LocalDate.parse(excelDate);
		        DateTimeFormatter uiDateFormat =
		                DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
		        String expectedUIDate = date.format(uiDateFormat);

		        System.out.println("Searching Emp: #" + empId + " | Date: " + expectedUIDate);

		        int maxScrolls = 10;
		        AndroidDriver driverAndroid = (AndroidDriver) driver;

		        for (int scroll = 0; scroll < maxScrolls; scroll++) {

		            // 🔹 Get all visible shift cards
		            List<WebElement> cards = driverAndroid.findElements(
		                AppiumBy.xpath("//android.view.View[@content-desc and contains(@content-desc,'#Emp')]")
		            );

		            for (WebElement card : cards) {

		                String cardInfo = card.getAttribute("content-desc");

		                boolean empMatch  = cardInfo.contains("#" + empId);
		                boolean dateMatch = cardInfo.contains(expectedUIDate);

		                if (empMatch && dateMatch) {

		                    System.out.println("✅ Matching card found:\n" + cardInfo);

		                    // 🔹 Click REJECT button inside SAME card
		                    WebElement rejectBtn = card.findElement(
		                        AppiumBy.xpath(".//android.widget.Button[@content-desc='REJECT']")
		                    );

		                    rejectBtn.click();
		                    return true;
		                }
		            }

		            // 🔹 Scroll up to load next cards
		            Dimension size = driverAndroid.manage().window().getSize();
		            int startY = (int) (size.height * 0.7);
		            int endY   = (int) (size.height * 0.3);
		            int x      = size.width / 2;

		            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
		            Thread.sleep(600);
		        }

		        exceptionDesc = "No matching shift card found for EmpID: #" + empId +
		                        " and Date: " + expectedUIDate;
		        return false;

		    } catch (Exception e) {
		        exceptionDesc = "Error while rejecting shift: " + e.getMessage();
		        return false;
		    }
		}



		public boolean validateStatusCount(String expectedStatus, String expectedCount) {
		    try {

		        // 🔹 Locate the status card dynamically
		        WebElement statusCard = driver.findElement(
		            AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + expectedStatus + "')]")
		        );

		        String cardText = statusCard.getAttribute("content-desc");
		        System.out.println("UI Card Text:\n" + cardText);

		        // 🔹 content-desc format:
		        // "1\nRevoked"
		        String[] parts = cardText.split("\\n");

		        String uiCount  = parts[0].trim();
		        String uiStatus = parts[1].trim();

		        // 🔹 Validate count
		        if (!uiCount.equals(expectedCount)) {
		            exceptionDesc = "Count mismatch! Expected: " + expectedCount +
		                            " | UI: " + uiCount;
		            return false;
		        }

		        // 🔹 Validate status
		        if (!uiStatus.equalsIgnoreCase(expectedStatus)) {
		            exceptionDesc = "Status mismatch! Expected: " + expectedStatus +
		                            " | UI: " + uiStatus;
		            return false;
		        }

		        System.out.println("✅ Status & Count matched successfully");
		        return true;

		    } catch (Exception e) {
		        exceptionDesc = "Error while validating status count: " + e.getMessage();
		        return false;
		    }
		}

 
 
 
 
 
 
 
 
	
    public String getExceptionDesc() {
        return exceptionDesc;
    }
	
	
}
