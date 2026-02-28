package com.MeghPI.Attendance.Android.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import utils.Pramod;
import java.time.Duration;
import utils.Utils;
public class MeghPIAttendAndroidAttendancePage {

	
	

    private WebDriver driver;
    WebDriverWait wait;

    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidAttendancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc, 'Attendance')]")
    private WebElement AttendanceButtonOnEmpDashBoard;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"REGULARIZATION\"]")
    private WebElement RegulirizationButtonOnEmpAttendance;
                      
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
    private WebElement RegulirizationFromDate;
    
  
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[1]")
    private WebElement RegulirizationFromDateVersion15;
  

    
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    private WebElement RegulirizationFromDateSelected;
    
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
    private WebElement RegulirizationFromTimeTextField;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")
    private WebElement RegulirizationFromTimeTextFieldVersion15;

  

    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[1]")
    private WebElement RegulirizationFromTimeKeyboardEnable;

    
    @FindBy(xpath = "//android.view.View[@content-desc='Enter time']//following::android.widget.EditText[1]")
    private WebElement RegulirizationFromTimeHoursClicked;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    private WebElement RegulirizationFromTimeHoursSelected;
  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[3]")
    private WebElement RegulirizationToDateClicked;
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[3]")
    private WebElement RegulirizationToDateClickedVersion15;
  


  

  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    private WebElement RegulirizationToDateSelected;
    
  
    @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[4]")
    private WebElement RegulirizationToTimeClicked;
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[4]")
    private WebElement RegulirizationToTimeClickedVersion15;
  


    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[1]")
    private WebElement RegulirizationToTimeKeyBoardEnabled;
  
  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    private WebElement RegulirizationToTimeSelected;
    
    @FindBy(xpath = "//android.widget.ScrollView/android.view.View[3]")
    private WebElement RegularizationTypeClicked;
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]")
    private WebElement RegularizationTypeClickedVersion15;
  


    
    
    @FindBy(xpath = "//android.view.View[@content-desc=\"Miss Punch\"]")
    private WebElement RegulirizationTypeSelected;

    @FindBy(xpath = "//android.widget.EditText")
    private WebElement RegulirizationReasonTextField;
  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Apply\"]")
    private WebElement RegulirizationApplyButton;

    By hourField = AppiumBy.xpath("//android.view.View[@content-desc='Enter time']/following-sibling::android.widget.EditText[1]");
    By minField  = AppiumBy.xpath("//android.view.View[@content-desc='Enter time']/following-sibling::android.widget.EditText[2]");

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Attendance\"]")
    private WebElement AttendanceModuleInAdmin; //2nd TC
    
    @FindBy(xpath = "//android.view.View[contains(@content-desc, 'Regularization')]")
    private WebElement RegulirizationTabInAdmin; //2nd TC
    
    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'#')]")
    private List<WebElement> requestCards; //2nd TC

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"YES\"]")
    private WebElement RegularizationConfirmButton; //2nd TC

  
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"APPROVE\"]")
    private WebElement RegularizationApproveButton; //2nd TC
  


 
   @FindBy(xpath = "//android.view.View[@content-desc=\"Attendance Summary\"]")
   private WebElement EmpAccountLoaded; //2nd TC

   @FindBy(xpath = "//android.view.View[contains(@content-desc,'Attendance') and contains(@content-desc,'Tab')]")
   public WebElement AttendanceTabOfEmp;
 
   @FindBy(xpath = "//android.widget.Button[@content-desc=\"Apply\"]")
   private WebElement LeaveApplyConfirmButton; //3rd TC
   
   @FindBy(xpath = "//android.widget.ScrollView/android.view.View[1]")
   private WebElement LeaveTypeDropDown; //3rd TC
   
   @FindBy(xpath = "//android.view.View[contains(@content-desc,'Sick Leave')]")
   private WebElement SickLeaveTypeSelected;  //3rd TC
   
   @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[1]")
   private WebElement FromDateClicked;  //3rd TC
 
   @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]")
   private WebElement DurationOne;  //3rd TC
 
   @FindBy(xpath = "//android.view.View[@content-desc=\"Full Day\"]")
   private WebElement FullDaySelected;  //3rd TC

   @FindBy(xpath = "//android.widget.EditText")
   private WebElement ReasonTextField;  //3rd TC

   @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Leave\"]")
   private WebElement LeaveModuleInAdmin;  //4th TC
   
   @FindBy(xpath = "//android.view.View[@content-desc=\"0 Cancelled\"]")
   private WebElement LeavePageLoaded;  //4th TC
 
   @FindBy(xpath = "//android.widget.Button[@content-desc=\"APPROVE\"]")
   private WebElement LeaveApproveButton;  //4th TC
 //android.widget.Button[@content-desc="APPROVE"]

   
   
   @FindBy(xpath = "//android.view.View[contains(@content-desc,'From') and contains(@content-desc,'To')]")
   private WebElement leaveRecordCard; //4th TC

   @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[1]")
   private WebElement BackButton;  //4th TC
 
   @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Leave')]")
   private WebElement leaveBalanceCardInAdmin;  //4th TC
   
   @FindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
   private WebElement BackNavigation;  //4th TC
   
   @FindBy(xpath = "//android.view.View[contains(@content-desc, 'Day Type')]")
   private WebElement dayTypeRow;


 


   
   
   
   
   
   
   
   
   

	
	
   public boolean AttendanceButtonOnEmpDashBoard() {
	    try {
	        AndroidDriver driverAndroid = (AndroidDriver) driver;

	        // Scroll until Attendance button is visible
	        WebElement attendanceCard = driverAndroid.findElement(
	            AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true))" +
	                ".scrollIntoView(new UiSelector().className(\"android.widget.ImageView\")" +
	                ".descriptionContains(\"Attendance\"))"
	            )
	        );

	        // Wait until clickable and click
	        WebDriverWait wait = new WebDriverWait(driverAndroid, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(attendanceCard));

	        attendanceCard.click();
	        System.out.println("✅ Attendance button clicked successfully.");

	        // Wait for Attendance tab to appear
	        utils.waitForEle(AttendanceTabOfEmp, "visible", "", 30);

	        return true;

	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking Attendance button: " + e.getMessage());
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}






	    
	    



    
    public boolean RegulirizationButtonOnEmpAttendance() {
        
        try {
			utils.waitForEle(RegulirizationButtonOnEmpAttendance, "visible", "", 30);
			RegulirizationButtonOnEmpAttendance.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
    
    private boolean isElementVisible(WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
    
    
    public boolean RegulirizationFromDate() {
        try {

            // 🔹 First try default locator
            if (isElementVisible(RegulirizationFromDate, 5)) {
                RegulirizationFromDate.click();
                return true;
            }

            // 🔹 Fallback for Android 15 layout
            if (isElementVisible(RegulirizationFromDateVersion15, 5)) {
                RegulirizationFromDateVersion15.click();
                return true;
            }

            // 🔹 If both not found
            throw new Exception("Regularization From Date icon not found in any supported UI version");

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }

 
    public boolean firstDate() {
        try {
            // Get current month and year
            LocalDate today = LocalDate.now();
            String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = today.getYear();

            // Day of week for 1st day of this month
            LocalDate firstDay = LocalDate.of(year, today.getMonth(), 1);
            String dayOfWeek = firstDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            // Build content-desc
            String contentDesc = String.format("1, %s, %s 1, %d", dayOfWeek, month, year);

            // Find and click element
            WebElement firstDate = driver.findElement(AppiumBy.accessibilityId(contentDesc));
            utils.waitForEle(firstDate, "visible", "", 30);
            firstDate.click();

        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
        return true;
    }
    
public boolean RegulirizationFromDateSelected() {
        
        try {
			utils.waitForEle(RegulirizationFromDateSelected, "visible", "", 30);
			RegulirizationFromDateSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
    



public boolean RegulirizationFromTimeTextField() {
    try {

        // 🔹 First try default locator
        if (isElementVisible(RegulirizationFromTimeTextField, 5)) {
        	RegulirizationFromTimeTextField.click();
            return true;
        }

        // 🔹 Fallback for Android 15 layout
        if (isElementVisible(RegulirizationFromTimeTextFieldVersion15, 5)) {
        	RegulirizationFromTimeTextFieldVersion15.click();
            return true;
        }

        // 🔹 If both not found
        throw new Exception("Regularization From Time icon not found in any supported UI version");

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}






    
public boolean RegulirizationFromTimeKeyboardEnable() {
    
    try {
		utils.waitForEle(RegulirizationFromTimeKeyboardEnable, "visible", "", 30);
		RegulirizationFromTimeKeyboardEnable.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}
    
public boolean clickTime(String hour, String min) {
    try {
        WebElement hourEle = driver.findElement(hourField);
        hourEle.click();
        Thread.sleep(300);
        hourEle.clear();
        hourEle.sendKeys(hour);

        // Automatically cursor jumps to minute, but still target min field too
        WebElement minEle = driver.findElement(minField);
        Thread.sleep(300);
        minEle.clear();
        minEle.sendKeys(min);

      
        return true;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}



public boolean RegulirizationFromTimeHoursSelected() {
    
    try {
		utils.waitForEle(RegulirizationFromTimeHoursSelected, "visible", "", 30);
		RegulirizationFromTimeHoursSelected.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}
	
	



public boolean RegulirizationToDateClicked() {
    try {

        // 🔹 First try default locator
        if (isElementVisible(RegulirizationToDateClicked, 5)) {
        	RegulirizationToDateClicked.click();
            return true;
        }

        // 🔹 Fallback for Android 15 layout
        if (isElementVisible(RegulirizationToDateClickedVersion15, 5)) {
        	RegulirizationToDateClickedVersion15.click();
            return true;
        }

        // 🔹 If both not found
        throw new Exception("Regularization To Date icon not found in any supported UI version");

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}







	
	
public boolean selectFirstDateOfMonth() {
    try {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);

        int day = firstDay.getDayOfMonth(); // 1
        String dayOfWeek = firstDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String month = firstDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = firstDay.getYear();

        // Correct content-desc
        String contentDesc = String.format("%d, %s, %s %d, %d", day, dayOfWeek, month, day, year);
        System.out.println("Looking for button with content-desc: " + contentDesc);

        WebElement firstDateBtn = ((AndroidDriver) driver).findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))"
            )
        );

        utils.waitForEle(firstDateBtn, "visible", "", 30);
        firstDateBtn.click();

    } catch (Exception e) {
        exceptionDesc = "Failed to click first date: " + e.getMessage();
        return false;
    }
    return true;
}









public boolean RegulirizationToDateSelected() {
    
    try {
		utils.waitForEle(RegulirizationToDateSelected, "visible", "", 30);
		RegulirizationToDateSelected.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}	



public boolean RegulirizationToTimeClicked() {
    try {

        // 🔹 First try default locator
        if (isElementVisible(RegulirizationToTimeClicked, 5)) {
        	RegulirizationToTimeClicked.click();
            return true;
        }

        // 🔹 Fallback for Android 15 layout
        if (isElementVisible(RegulirizationToTimeClickedVersion15, 5)) {
        	RegulirizationToTimeClickedVersion15.click();
            return true;
        }

        // 🔹 If both not found
        throw new Exception("Regularization To Time icon not found in any supported UI version");

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}





public boolean RegulirizationToTimeKeyBoardEnabled() {
    
    try {
		utils.waitForEle(RegulirizationToTimeKeyBoardEnabled, "visible", "", 30);
		RegulirizationToTimeKeyBoardEnabled.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


public boolean clickToTime(String hour, String min) {
    try {
        // Scroll into view the "Enter time" label
        WebElement enterTimeView = ((AndroidDriver) driver)
            .findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().descriptionContains(\"Enter time\"))"));

        utils.waitForEle(enterTimeView, "visible", "", 30);

        // Hour and Minute fields are siblings of Enter time view
        WebElement hourEle = driver.findElement(AppiumBy.xpath(
            "//android.view.View[@content-desc='Enter time']/following-sibling::android.widget.EditText[1]"));
        WebElement minEle = driver.findElement(AppiumBy.xpath(
            "//android.view.View[@content-desc='Enter time']/following-sibling::android.widget.EditText[2]"));

        // Input hour
        hourEle.click();
        Thread.sleep(300);
        hourEle.clear();
        hourEle.sendKeys(hour);

        // Input minute
        Thread.sleep(300);
        minEle.clear();
        minEle.sendKeys(min);

     

        return true;

    } catch (Exception e) {
        exceptionDesc = "Failed to input time: " + e.getMessage();
        return false;
    }
}





public boolean RegulirizationToTimeSelected() {
    
    try {
		utils.waitForEle(RegulirizationToTimeSelected, "visible", "", 30);
		RegulirizationToTimeSelected.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}
	


public boolean RegularizationTypeClicked() {
    try {

        // 🔹 First try default locator
        if (isElementVisible(RegularizationTypeClicked, 5)) {
        	RegularizationTypeClicked.click();
            return true;
        }

        // 🔹 Fallback for Android 15 layout
        if (isElementVisible(RegularizationTypeClickedVersion15, 5)) {
        	RegularizationTypeClickedVersion15.click();
            return true;
        }

        // 🔹 If both not found
        throw new Exception("Regularization Type icon not found in any supported UI version");

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
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
    
public boolean RegulirizationReasonTextField(String reason) {
    
    try {
		utils.waitForEle(RegulirizationReasonTextField, "visible", "", 30);
		RegulirizationReasonTextField.click();
		RegulirizationReasonTextField.sendKeys(reason);
		Pramod.hideKeyboardSmart(driver);
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}  

public boolean RegulirizationApplyButton() {
    
    try {
		utils.waitForEle(RegulirizationApplyButton, "visible", "", 30);
		RegulirizationApplyButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}
    
    
//2nd TC
public boolean RegulirizationTabInAdmin() {
    
    try {
    	Thread.sleep(2000);
		utils.waitForEle(RegulirizationTabInAdmin, "visible", "", 20);
		RegulirizationTabInAdmin.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


//2nd TestCase
public boolean scrollToEmpId(String empId) {
    try {
        String searchId = "#" + empId; // example: #Emp021
        
        // Build UIAutomator scroll code
        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"" 
                        + searchId + "\"));";

        System.out.println("Scrolling to employee: " + searchId);

        // Scroll on screen until element becomes visible
        WebElement empCard = ((AndroidDriver) driver).findElement(
                AppiumBy.androidUIAutomator(uiScroll)
        );

        // ✅ Found employee card — return true
        if (empCard != null) {
            return true;
        }
        
        return false; 

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        System.out.println("Error scrolling for EmpId: " + empId + " --> " + exceptionDesc);
        return false;
    }
}

public boolean isFirstDayOfMonthVisible() {
    try {
        // Get first date text like "Saturday - 01 Nov 2025"
        String firstDateText = Pramod.getFirstDayOfMonth();  
        System.out.println("Checking for first date: " + firstDateText);

        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().className(\"android.view.View\").descriptionContains(\"" 
                + firstDateText + "\"));";

        WebElement firstDateEle = ((AndroidDriver) driver).findElement(
                AppiumBy.androidUIAutomator(uiScroll)
        );

        utils.waitForEle(firstDateEle, "visible", "", 30);
        return true;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}

public boolean validateFirstDayRecord(String expectedStatus) {
    try {
        String firstDateText = Pramod.getFirstDayOfMonth(); // Saturday - 01 Nov 2025
        System.out.println("Checking for first date: " + firstDateText);

        // Scroll to first date card
        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"" + firstDateText + "\"));";

        WebElement firstDateEle = ((AndroidDriver) driver)
                .findElement(AppiumBy.androidUIAutomator(uiScroll));

        String info = firstDateEle.getAttribute("content-desc");
        System.out.println("UI Entry:\n" + info);

        // ✅ Validate Date + Status
        if (info.contains(firstDateText) &&
            info.toLowerCase().contains(expectedStatus.toLowerCase())) {
            return true;
        }

        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " but UI shows:\n" + info;
        return false;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}










public boolean AttendanceModuleInAdmin() {
    
    try {
    	Thread.sleep(2000);
		utils.waitForEle(AttendanceModuleInAdmin, "visible", "", 50);
		AttendanceModuleInAdmin.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


public boolean RegularizationConfirmButton() {
    
    try {
    	Thread.sleep(3000);
		utils.waitForEle(RegularizationConfirmButton, "visible", "", 20);
		RegularizationConfirmButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean RegularizationApproveButton(String empId, String dateFromExcel) {
    try {
    	Thread.sleep(2000);        AndroidDriver driverAndroid = (AndroidDriver) driver;

        // Convert date to UI format (e.g., "03 Nov 2025")
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        String formattedDate = date.format(formatter);

        System.out.println("🔍 Searching for PENDING card with EmpID: " + empId + " and Date: " + formattedDate);

        boolean found = false;
        int maxScrolls = 8;

        for (int i = 0; i < maxScrolls; i++) {
            // Find all employee cards visible on screen
            List<WebElement> allCards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + empId + "')]")
            );

            for (WebElement card : allCards) {
                String cardDesc = card.getAttribute("contentDescription");

                // Match EmpID + Date + ensure status is Pending
                if (cardDesc.contains(empId) && cardDesc.contains(formattedDate) && cardDesc.contains("Pending")) {
                    System.out.println("✅ Found Pending card:\n" + cardDesc);

                    // Scroll card into view smoothly
                    ((JavascriptExecutor) driverAndroid).executeScript("mobile: scrollGesture", Map.of(
                            "elementId", ((RemoteWebElement) card).getId(),
                            "direction", "down",
                            "percent", 0.3
                    ));

                    // Locate APPROVE button on visible screen
                    List<WebElement> approveBtns = driverAndroid.findElements(
                            AppiumBy.xpath("//android.widget.Button[@content-desc='APPROVE']")
                    );

                    if (!approveBtns.isEmpty()) {
                        WebElement approveBtn = approveBtns.get(0); // first approve visible
                        utils.waitForEle(approveBtn, "visible", "", 30);

                        // Scroll directly to the button before clicking
                        ((JavascriptExecutor) driverAndroid).executeScript("mobile: scrollGesture", Map.of(
                                "elementId", ((RemoteWebElement) approveBtn).getId(),
                                "direction", "down",
                                "percent", 0.1
                        ));

                        try {
                            approveBtn.click();
                            System.out.println("👍 Clicked APPROVE for EmpID: " + empId + " on Date: " + formattedDate);
                            Thread.sleep(1500); // wait briefly for UI refresh
                        } catch (StaleElementReferenceException stale) {
                            System.out.println("⚠️ APPROVE button became stale after click (expected — UI refreshed). Click was successful.");
                        }
                        found = true;
                        break;
                    } else {
                        System.out.println("⚠️ APPROVE button not visible — card may already be processed.");
                    }
                }
            }

            if (found) break;

            // Scroll for next batch of cards
            System.out.println("↕️ Scrolling... Attempt " + (i + 1));
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int centerX = size.width / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence scroll = new Sequence(finger, 1);
            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driverAndroid.perform(Arrays.asList(scroll));
        }

        if (!found) {
            exceptionDesc = "❌ No Pending card found for EmpID: " + empId + " and Date: " + formattedDate;
            System.out.println(exceptionDesc);
            return false;
        }

        return true;

    } catch (Exception e) {
        exceptionDesc = "❌ Error while approving regularization request: " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}






//3rd TC

public boolean navigateToRequiredMonth(String excelDate) {
    try {
        LocalDate targetDate = LocalDate.parse(excelDate);
        String targetMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));

        System.out.println("Target Month-Year: " + targetMonthYear);

        // Loop max 12 times
        for (int i = 0; i < 12; i++) {

            // ✅ Click Previous Month FIRST
            try {
                WebElement backBtn = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'Present')]/android.widget.ImageView[1]"));
                backBtn.click();
                System.out.println("⬅ Clicked Previous month... Attempt " + (i+1));
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("❌ Back button not found/click failed: " + e.getMessage());
            }

            // ✅ Now read the month and validate
            try {
                WebElement monthHeader = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'Present')]"));
                String currentMonthYear = monthHeader.getAttribute("content-desc").split("\n")[0].trim();

                System.out.println("Current UI Month-Year: " + currentMonthYear);

                if (currentMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    System.out.println("✅ Month matched successfully!");
                    return true;
                }

            } catch (Exception e) {
                System.out.println("⚠ Month header not found yet, retrying...");
            }
        }

        exceptionDesc = "❌ Failed to navigate to target month: " + targetMonthYear;
        return false;

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in navigateToRequiredMonth(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}






public boolean validateAttendanceByDate(String dateFromExcel, String expectedStatus) {
    try {
    	utils.waitForEle(dayTypeRow , "visible", "", 300);
        // Convert YYYY-MM-DD to UI format: "24 Jul 2025"
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);           // "24 Jul 2025"
        String dayName = date.format(dayFormatter);               // "Thursday"

        String searchText = dayName + " - " + formattedDate;      // e.g. "Thursday - 24 Jul 2025"

        System.out.println("Searching attendance card: " + searchText);

        // UiScrollable scroll until card found
        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"" + searchText + "\"));";

        WebElement dayCard = ((AndroidDriver) driver)
                .findElement(AppiumBy.androidUIAutomator(uiScroll));
        utils.waitForEle(dayCard , "visible", "", 50);

        String cardInfo = dayCard.getAttribute("content-desc");
        System.out.println("Found record:\n" + cardInfo);

        // Validate Status
        if (cardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
            return true;
        }

        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + ", UI shows:\n" + cardInfo;
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error while validating attendance: " + e.getMessage();
        return false;
    }
}
public boolean validateAttendanceByDateweekoff(String dateFromExcel, String expectedStatus) {
    try {
        LocalDate date = LocalDate.parse(dateFromExcel);

        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
        String dayName = date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));

        String searchText = dayName + " - " + formattedDate;

        System.out.println("Searching attendance card: " + searchText);

        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"" + searchText + "\"));";

        WebElement dayCard = ((AndroidDriver) driver)
                .findElement(AppiumBy.androidUIAutomator(uiScroll));

        String cardInfo = dayCard.getAttribute("content-desc");
        System.out.println("Found record:\n" + cardInfo);

        // Convert to lowercase for safe matching
        String lowerInfo = cardInfo.toLowerCase();
        String lowerExpected = expectedStatus.toLowerCase();

        // ✅ Special handling for Week Off
        if (lowerExpected.contains("week") && lowerExpected.contains("off")) {
            if (lowerInfo.contains("week off")) {
                return true;
            } else {
                exceptionDesc = "❌ Expected Week Off but UI shows:\n" + cardInfo;
                return false;
            }
        }

        // ✅ Normal status match (Present / Absent / Leave etc.)
        if (lowerInfo.contains(lowerExpected)) {
            return true;
        }

        exceptionDesc = "❌ Status mismatch! Expected: " + expectedStatus + ", UI shows:\n" + cardInfo;
        return false;

    } catch (Exception e) {
        exceptionDesc = "❌ Error while validating attendance: " + e.getMessage();
        return false;
    }
}

public boolean validateAttendanceByDateondaytype(String dateFromExcel, String expectedStatus, String expectedDayType) {
    try {

        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);

        String searchText = dayName + " - " + formattedDate;

        System.out.println("Searching card: " + searchText);

        int maxScrolls = 10;
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {

            // 🔹 Get all attendance cards currently visible
            List<WebElement> cards =
                    driverAndroid.findElements(AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]"));

            for (WebElement card : cards) {
            	

                String cardInfo = card.getAttribute("content-desc");

                if (cardInfo.startsWith(searchText)) {
                    System.out.println("✅ Found record:\n" + cardInfo);

                    // ✅ Validate status
                    if (!cardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
                        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " | UI: " + cardInfo;
                        return false;
                    }

                    // ✅ Extract Day Type
                    String dayType = "";
                    String[] lines = cardInfo.split("\n");
                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].trim().equalsIgnoreCase("Day Type") && i + 1 < lines.length) {
                            dayType = lines[i + 1].trim();
                            break;
                        }
                    }

                    // ✅ Validate DayType
                    if (!dayType.equalsIgnoreCase(expectedDayType)) {
                        exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + dayType;
                        return false;
                    }

                    return true;
                }
            }

            // 🔹 Scroll up to load older dates
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int x = size.width / 2;

            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);

            
            Thread.sleep(600);
        }

        exceptionDesc = "No record found for date: " + searchText;
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error while validating attendance: " + e.getMessage();
        return false;
    }
}

public boolean validateAttendanceByDateondaytypeAndTotalHr(String dateFromExcel, String expectedStatus, String expectedDayType, String expectedTotalHr) {
    try {

        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);

        String searchText = dayName + " - " + formattedDate;
        System.out.println("Searching card: " + searchText);

        int maxScrolls = 10;
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {

            List<WebElement> cards =
                driverAndroid.findElements(AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]"));

            for (WebElement card : cards) {

                String cardInfo = card.getAttribute("content-desc");

                if (cardInfo.startsWith(searchText)) {
                    System.out.println("✅ Found record:\n" + cardInfo);

                    // ✅ Validate Status
                    if (!cardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
                        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " | UI: " + cardInfo;
                        return false;
                    }

                    // ✅ Extract & Validate Day Type
                    String[] lines = cardInfo.split("\n");
                    String dayType = "", totalHR = "";

                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].trim().equalsIgnoreCase("Day Type") && i + 1 < lines.length)
                            dayType = lines[i + 1].trim();

                        if (lines[i].trim().equalsIgnoreCase("Total HR") && i + 1 < lines.length)
                            totalHR = lines[i + 1].trim();
                    }

                    if (!dayType.equalsIgnoreCase(expectedDayType)) {
                        exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + dayType;
                        return false;
                    }

                    // ✅ Validate Total HR
                    if (!totalHR.contains(expectedTotalHr)) {
                        exceptionDesc = "Total HR mismatch! Expected: " + expectedTotalHr + " | UI: " + totalHR;
                        return false;
                    }

                    return true;
                }
            }

            // 🔹 Scroll to load more records
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int x = size.width / 2;

            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
            Thread.sleep(600);
        }

        exceptionDesc = "No record found for date: " + searchText;
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error while validating attendance: " + e.getMessage();
        return false;
    }
}



public boolean EmpAccountLoaded() {
    
    try {
		utils.waitForEle(EmpAccountLoaded, "visible", "", 50);
		EmpAccountLoaded.isDisplayed();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


//3rd TC

public boolean clickApplyLeaveForDate(String dateFromExcel) {
    try {
        // Convert dd-MM-yyyy → EEEE - dd MMM yyyy
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(dateFromExcel, inputFormat);
        String targetText = date.format(uiFormat).trim();

        System.out.println("Looking for UI card: " + targetText);

        AndroidDriver driverAndroid = (AndroidDriver) driver;
        int maxScrolls = 10;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {

            // Get all cards with content-desc
            List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]")
            );

            for (WebElement card : cards) {
                String text = card.getAttribute("content-desc");

                if (text.startsWith(targetText)) {

                    System.out.println("✅ Found date card: " + text);

                    try {
                        // Find APPLY LEAVE relative to this card only
                        WebElement applyLeaveBtn = card.findElement(
                                AppiumBy.xpath(".//following-sibling::android.widget.Button[contains(@content-desc,'APPLY LEAVE')]")
                        );
                        applyLeaveBtn.click();
                        return true;

                    } catch (Exception e) {
                        exceptionDesc = "Apply Leave button not found under card: " + text;
                        return false;
                    }
                }
            }

            // Scroll down
            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.75);
            int endY = (int) (size.height * 0.35);
            int x = size.width / 2;

            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
            Thread.sleep(600);
        }

        exceptionDesc = "Date not found in list: " + targetText;
        return false;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}

public boolean LeaveApplyConfirmDisplayed() {
    
    try {
		utils.waitForEle(LeaveApplyConfirmButton, "visible", "", 50);
		LeaveApplyConfirmButton.isDisplayed();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean LeaveTypeDropDown() {
    
    try {
		utils.waitForEle(LeaveTypeDropDown, "visible", "", 20);
		LeaveTypeDropDown.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean SickLeaveTypeSelected() {
    
    try {
		utils.waitForEle(SickLeaveTypeSelected, "visible", "", 20);
		SickLeaveTypeSelected.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean clickDateInCalendar(String dateFromExcel) {
    try {
        // Input format dd-MM-yyyy → UI format "d, EEEE, MMMM d, yyyy"
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("d, EEEE, MMMM d, yyyy", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(dateFromExcel, inputFormat);
        String expectedText = date.format(uiFormat);

        System.out.println("Target Date: " + expectedText);

        AndroidDriver driverAndroid = (AndroidDriver) driver;
        int maxScrolls = 8;

        for (int i = 0; i < maxScrolls; i++) {

            List<WebElement> buttons = driverAndroid.findElements(
                    AppiumBy.xpath("//android.widget.Button[@content-desc]")
            );

            for (WebElement btn : buttons) {
                String text = btn.getAttribute("content-desc");

                if (text.startsWith(expectedText)) {
                    System.out.println("✅ Date Found → " + text);
                    btn.click();
                    return true;
                }
            }

            // scroll if not found yet
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.70);
            int endY  = (int) (size.height * 0.30);
            int x = size.width / 2;

            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
            Thread.sleep(600);
        }

        exceptionDesc = "Date not visible in calendar: " + expectedText;
        return false;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}

public boolean selectToDate(String dateFromExcel) {
    try {
        // Convert dd-MM-yyyy → d, EEEE, MMMM d, yyyy
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("d, EEEE, MMMM d, yyyy", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(dateFromExcel, inputFormat);
        String expectedText = date.format(uiFormat);

        System.out.println("Target To-Date: " + expectedText);

        AndroidDriver driverAndroid = (AndroidDriver) driver;
        int maxScrolls = 8;

        for (int i = 0; i < maxScrolls; i++) {

            List<WebElement> buttons = driverAndroid.findElements(
                AppiumBy.xpath("//android.widget.Button[@content-desc]")
            );

            for (WebElement btn : buttons) {
                String text = btn.getAttribute("content-desc").trim();

                if (text.startsWith(expectedText)) {
                    System.out.println("✅ To-Date Found → " + text);
                    btn.click();
                    return true;
                }
            }

            // Scroll down to find date
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.70);
            int endY = (int) (size.height * 0.30);
            int x = size.width / 2;

            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
            Thread.sleep(500);
        }

        exceptionDesc = "To-Date not found: " + expectedText;
        return false;

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
}

public boolean DurationOne() {
    
    try {
		utils.waitForEle(DurationOne, "visible", "", 20);
		DurationOne.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean FullDaySelected() {
    
    try {
		utils.waitForEle(FullDaySelected, "visible", "", 20);
		FullDaySelected.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


public boolean LeaveApplyConfirmButton() {
    
    try {
		utils.waitForEle(LeaveApplyConfirmButton, "visible", "", 20);
		LeaveApplyConfirmButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean LeaveModuleInAdmin() {
    
    try {
		utils.waitForEle(LeaveModuleInAdmin, "visible", "", 30);
		LeaveModuleInAdmin.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

//4th TestCase
public boolean validateLeaveAndApprove(String empId, String date) {
    try {
        // Format EmpID → add # prefix for UI
        String uiEmpId = "#" + empId;

        // Convert dd-MM-yyyy → dd MMM, yyyy
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

        LocalDate parsedDate = LocalDate.parse(date, inputFormat);
        String formattedDate = parsedDate.format(uiFormat);

        // Dynamic XPath for the leave card
        String cardXpath =
                "//android.view.View[contains(@content-desc,'" + uiEmpId + "') " +
                "and contains(@content-desc,'From') " +
                "and contains(@content-desc,'" + formattedDate + "') " +
                "and contains(@content-desc,'To')]";

        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // single wait object
		WebElement leaveCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cardXpath)));

        if (leaveCard.isDisplayed()) {
            WebElement approveBtn = leaveCard.findElement(By.xpath(".//android.widget.Button[@content-desc='APPROVE']"));
            approveBtn.click();
            return true;
        }

        return false;

    } catch (Exception e) {
        System.out.println("Error validating leave & clicking APPROVE: " + e.getMessage());
        return false;
    }
}


public boolean LeavePageLoaded() {
    
    try {
		utils.waitForEle(leaveRecordCard, "visible", "", 20);
		leaveRecordCard.isDisplayed();
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean LeaveApproveButton() {
    
    try {
    	Thread.sleep(2000);
		utils.waitForEle(LeaveApproveButton, "visible", "", 30);
		LeaveApproveButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}
public boolean BackButton() {
    
    try {
		utils.waitForEle(BackButton, "visible", "", 20);
		BackButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean clickLeaveBalanceCard() {
    try {
    	utils.waitForEle(leaveBalanceCardInAdmin, "visible", "", 30);
        AndroidDriver androidDriver = (AndroidDriver) driver;
        int maxScroll = 8;

        for (int i = 0; i < maxScroll; i++) {
            try {
                // Check visible & click
                if (leaveBalanceCardInAdmin.isDisplayed()) {
                    leaveBalanceCardInAdmin.click();
                    return true; // ✅ success return
                }
            } catch (Exception ignore) {
                // Element not found on screen yet → scroll next
            }

            // ✅ Scroll/swipe up
            Dimension size = androidDriver.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int x = size.width / 2;

            Pramod.swipe(androidDriver, x, startY, x, endY, 600);
            Thread.sleep(600);
        }

        exceptionDesc = "Leave balance card not found after scrolling";
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error in clickLeaveBalanceCard(): " + e.getMessage();
        return false;
    }
}

public boolean validateLeaveRecord(String leaveType, String status, String fromDate, String toDate) {
    try {
        // Convert dd-MM-yyyy → dd MMM, yyyy (UI format)
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH);

        String fromDateUI = LocalDate.parse(fromDate, inputFormat).format(uiFormat);
        String toDateUI = LocalDate.parse(toDate, inputFormat).format(uiFormat);

        // Build Dynamic XPath
        String xpath = "//android.view.View[contains(@content-desc,'" + leaveType + "') " +
                       "and contains(@content-desc,'" + status + "') " +
                       "and contains(@content-desc,'" + fromDateUI + "') " +
                       "and contains(@content-desc,'" + toDateUI + "')]";

        // Step 1: Locate element first
        WebElement leaveCard = driver.findElement(By.xpath(xpath));

        // Step 2: Wait for element to be visible
        if (!utils.waitForEle(leaveCard, "visible", "", 30)) {
            exceptionDesc = "Leave record not visible within timeout";
            return false;
        }

        // Step 3: Validate displayed
        return leaveCard.isDisplayed();

    } catch (Exception e) {
        exceptionDesc = "Leave record not found: " + e.getMessage();
        return false;
    }
}

public boolean validateAttendanceByDateAndOvertime(String dateFromExcel, String expectedStatus, String expectedDayType, String expectedOverTime) {
    try {
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);

        String searchText = dayName + " - " + formattedDate;
        System.out.println("Searching card: " + searchText);

        int maxScrolls = 10;
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {

            // 🔹 Step 1: Small wait before checking visible cards (allow load)
            Thread.sleep(1200);

            List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]"));

            if (cards.isEmpty()) {
                System.out.println("⚠️ No cards found in current view, waiting before next scroll...");
                Thread.sleep(1000);
                continue;
            }

            boolean recordFoundInVisibleCards = false;

            System.out.println("🔹 Checking " + cards.size() + " visible cards...");

            for (WebElement card : cards) {
                String cardInfo = card.getAttribute("content-desc");
                String normalizedCardInfo = cardInfo.replace("\n", " ").trim(); // ✅ Normalize newlines

                // Log first few characters for clarity
                System.out.println("🔍 Card Text: " + normalizedCardInfo.substring(0, Math.min(60, normalizedCardInfo.length())) + "...");

                // 🔹 Step 2: Check if this card matches the searched date
                if (normalizedCardInfo.startsWith(searchText)) {
                    recordFoundInVisibleCards = true;
                    System.out.println("✅ Found record:\n" + normalizedCardInfo);

                    // ✅ Validate Status
                    if (!normalizedCardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
                        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " | UI: " + normalizedCardInfo;
                        return false;
                    }

                    // ✅ Validate Day Type
                    if (!normalizedCardInfo.toLowerCase().contains(("Day Type " + expectedDayType).toLowerCase())) {
                        exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + normalizedCardInfo;
                        return false;
                    }

                    // ✅ Validate Over Time
                    if (!normalizedCardInfo.contains("Over Time " + expectedOverTime)) {
                        exceptionDesc = "Over Time mismatch! Expected: " + expectedOverTime + " | UI: " + normalizedCardInfo;
                        return false;
                    }

                    // ✅ All checks passed
                    return true;
                }
            }

            // 🔹 Step 3: If no record found in current view, scroll and wait
            if (!recordFoundInVisibleCards) {
                System.out.println("Record not found in current view → scrolling (" + (scroll + 1) + "/" + maxScrolls + ")");
                Dimension size = driverAndroid.manage().window().getSize();
                int startY = (int) (size.height * 0.7);
                int endY = (int) (size.height * 0.3);
                int x = size.width / 2;

                Pramod.swipe(driverAndroid, x, startY, x, endY, 600);

                // Wait after scroll for UI to refresh
                Thread.sleep(1500);
            } else {
                break; // Found record, no need to continue
            }
        }

        exceptionDesc = "No record found for date: " + searchText;
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error while validating attendance: " + e.getMessage();
        return false;
    }
}
public boolean validateAttendanceByDateAndMultipleShifts(
        String dateFromExcel,
        String expectedStatus,
        String expectedDayType,
        String expectedInTimeMorning,
        String expectedOutTimeMorning,
        String expectedInTimeNoon,
        String expectedOutTimeNoon) {

    try {
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);
        String searchText = dayName + " - " + formattedDate;

        System.out.println("🔍 Searching for cards with date: " + searchText);

        AndroidDriver driverAndroid = (AndroidDriver) driver;
        boolean morningFound = false;
        boolean noonFound = false;

        // ✅ Fetch visible attendance cards
        List<WebElement> cards = driverAndroid.findElements(
                AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]")
        );

        for (WebElement card : cards) {
            String cardInfo = card.getAttribute("content-desc").trim();

            // ✅ Check only matching date cards
            if (cardInfo.startsWith(searchText)) {
                System.out.println("\n✅ Found record:\n" + cardInfo);

                // Extract details
                String[] lines = cardInfo.split("\n");
                String dayType = "", inTime = "", outTime = "";

                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].trim().equalsIgnoreCase("Day Type") && i + 1 < lines.length)
                        dayType = lines[i + 1].trim();
                    else if (lines[i].trim().equalsIgnoreCase("In Time") && i + 1 < lines.length)
                        inTime = lines[i + 1].trim();
                    else if (lines[i].trim().equalsIgnoreCase("Out Time") && i + 1 < lines.length)
                        outTime = lines[i + 1].trim();
                }

                // ✅ Validate Status & Day Type
                if (!cardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
                    exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " | UI: " + cardInfo;
                    return false;
                }
                if (!dayType.equalsIgnoreCase(expectedDayType)) {
                    exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + dayType;
                    return false;
                }

                // ✅ Normalize and log comparisons
                String uiIn = inTime.replaceAll("\\s+", "").toLowerCase();
                String uiOut = outTime.replaceAll("\\s+", "").toLowerCase();

                String expInMorning = expectedInTimeMorning.replaceAll("\\s+", "").toLowerCase();
                String expOutMorning = expectedOutTimeMorning.replaceAll("\\s+", "").toLowerCase();
                String expInNoon = expectedInTimeNoon.replaceAll("\\s+", "").toLowerCase();
                String expOutNoon = expectedOutTimeNoon.replaceAll("\\s+", "").toLowerCase();

                System.out.println("🕒 Expected Morning Shift: " + expectedInTimeMorning + " - " + expectedOutTimeMorning);
                System.out.println("🕒 Expected Noon Shift: " + expectedInTimeNoon + " - " + expectedOutTimeNoon);
                System.out.println("🕓 UI In/Out: " + inTime + " - " + outTime);

                // ✅ Compare with AM/PM included
                if (uiIn.equals(expInMorning) && uiOut.equals(expOutMorning)) {
                    System.out.println("🌅 Morning shift validated successfully: " + inTime + " - " + outTime);
                    morningFound = true;
                } else if (uiIn.equals(expInNoon) && uiOut.equals(expOutNoon)) {
                    System.out.println("🌇 Noon shift validated successfully: " + inTime + " - " + outTime);
                    noonFound = true;
                } else {
                    System.out.println("⚠️ Card found but In/Out Time didn't match expected shifts.");
                }

                // ✅ If both found, stop loop
                if (morningFound && noonFound) {
                    System.out.println("✅ Both morning and noon shift records validated successfully.");
                    return true;
                }
            }
        }

        // ✅ Final check
        if (!morningFound || !noonFound) {
            exceptionDesc = "Could not validate both Morning and Noon records for date: " + searchText;
            return false;
        }

        return true;

    } catch (Exception e) {
        exceptionDesc = "Error while validating multiple shift attendance: " + e.getMessage();
        return false;
    }
}

public boolean validateAttendanceByDateWhenMultipleShiftNo(
        String dateFromExcel,
        String expectedStatus,
        String expectedDayType,
        String expectedOverTime,
        String expectedInTime,
        String expectedOutTime) {

    try {
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);
        String searchText = dayName + " - " + formattedDate;

        System.out.println("🔍 Searching card: " + searchText);

        int maxScrolls = 10;
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {
            Thread.sleep(1200); // Allow load

            List<WebElement> cards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]")
            );

            if (cards.isEmpty()) {
                System.out.println("⚠️ No cards found in current view, waiting before next scroll...");
                Thread.sleep(1000);
                continue;
            }

            System.out.println("🔹 Checking " + cards.size() + " visible cards...");

            for (WebElement card : cards) {
                String cardInfo = card.getAttribute("content-desc");
                if (cardInfo == null || cardInfo.isEmpty()) continue;

                String normalizedCardInfo = cardInfo.replace("\n", " ").trim();

                // Print preview
                System.out.println("🔍 Card Text: " + normalizedCardInfo.substring(0, Math.min(80, normalizedCardInfo.length())) + "...");

                // 🔹 Match date
                if (normalizedCardInfo.startsWith(searchText)) {
                    System.out.println("✅ Found record:\n" + normalizedCardInfo);

                    // Extract fields
                    String inTime = "", outTime = "", overTime = "", dayType = "";
                    String[] lines = cardInfo.split("\n");

                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].trim().equalsIgnoreCase("In Time") && i + 1 < lines.length)
                            inTime = lines[i + 1].trim();
                        else if (lines[i].trim().equalsIgnoreCase("Out Time") && i + 1 < lines.length)
                            outTime = lines[i + 1].trim();
                        else if (lines[i].trim().equalsIgnoreCase("Day Type") && i + 1 < lines.length)
                            dayType = lines[i + 1].trim();
                        else if (lines[i].trim().equalsIgnoreCase("Over Time") && i + 1 < lines.length)
                            overTime = lines[i + 1].trim();
                    }

                    // Log expected vs actual
                    System.out.println("🕒 Expected In Time: " + expectedInTime + " | UI: " + inTime);
                    System.out.println("🕒 Expected Out Time: " + expectedOutTime + " | UI: " + outTime);
                    System.out.println("📅 Expected Day Type: " + expectedDayType + " | UI: " + dayType);
                    System.out.println("⏱ Expected Over Time: " + expectedOverTime + " | UI: " + overTime);
                    System.out.println("📋 Expected Status: " + expectedStatus);

                    // ✅ Validate Status
                    if (!normalizedCardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
                        exceptionDesc = "Status mismatch! Expected: " + expectedStatus + " | UI: " + normalizedCardInfo;
                        return false;
                    }

                    // ✅ Validate Day Type
                    if (!dayType.equalsIgnoreCase(expectedDayType)) {
                        exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + dayType;
                        return false;
                    }

                    // ✅ Validate In Time
                    if (!inTime.equalsIgnoreCase(expectedInTime)) {
                        exceptionDesc = "In Time mismatch! Expected: " + expectedInTime + " | UI: " + inTime;
                        return false;
                    }

                    // ✅ Validate Out Time
                    if (!outTime.equalsIgnoreCase(expectedOutTime)) {
                        exceptionDesc = "Out Time mismatch! Expected: " + expectedOutTime + " | UI: " + outTime;
                        return false;
                    }

                    // ✅ Validate Over Time (handle null, empty, "-" etc.)
                    if (expectedOverTime == null || expectedOverTime.trim().isEmpty()) {
                        if (overTime.equalsIgnoreCase("-") || overTime.equalsIgnoreCase("0:00 Hrs") || overTime.trim().isEmpty()) {
                            System.out.println("⏱ No overtime expected and none found in UI ✅");
                        } else {
                            exceptionDesc = "Over Time mismatch! Expected: None | UI: " + overTime;
                            return false;
                        }
                    } else {
                        if (!overTime.equalsIgnoreCase(expectedOverTime)) {
                            exceptionDesc = "Over Time mismatch! Expected: " + expectedOverTime + " | UI: " + overTime;
                            return false;
                        }
                    }

                    System.out.println("✅ All validations passed for " + searchText);
                    return true;
                }
            }

            // Scroll if no record for this date
            System.out.println("Record not found yet → scrolling (" + (scroll + 1) + "/" + maxScrolls + ")");
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int x = size.width / 2;
            Pramod.swipe(driverAndroid, x, startY, x, endY, 600);
        }

        exceptionDesc = "Record not found after maximum scrolls for date: " + searchText;
        return false;

    } catch (Exception e) {
        exceptionDesc = "Error while validating attendance and overtime: " + e.getMessage();
        return false;
    }
}

public boolean validateAttendanceByDateAndMultipleShiftsStatus(
        String dateFromExcel,
        String expectedStatusMorning,
        String expectedStatusNoon,
        String expectedDayType,
        String expectedInTimeMorning,
        String expectedOutTimeMorning,
        String expectedInTimeNoon,
        String expectedOutTimeNoon) {

    try {
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        String formattedDate = date.format(uiFormatter);
        String dayName = date.format(dayFormatter);
        String searchText = dayName + " - " + formattedDate;

        System.out.println("🔍 Searching for cards with date: " + searchText);

        AndroidDriver driverAndroid = (AndroidDriver) driver;
        boolean morningFound = false;
        boolean noonFound = false;

        // ✅ Fetch visible attendance cards
        List<WebElement> cards = driverAndroid.findElements(
                AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc]")
        );

        for (WebElement card : cards) {
            String cardInfo = card.getAttribute("content-desc").trim();

            // ✅ Process only cards matching the searched date
            if (cardInfo.startsWith(searchText)) {
                System.out.println("\n✅ Found record:\n" + cardInfo);

                // Extract details
                String[] lines = cardInfo.split("\n");
                String dayType = "", inTime = "", outTime = "", uiStatus = "";

                // Extract relevant info from content-desc
                if (lines.length > 1) uiStatus = lines[1].trim(); // usually 2nd line after date
                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].trim().equalsIgnoreCase("Day Type") && i + 1 < lines.length)
                        dayType = lines[i + 1].trim();
                    else if (lines[i].trim().equalsIgnoreCase("In Time") && i + 1 < lines.length)
                        inTime = lines[i + 1].trim();
                    else if (lines[i].trim().equalsIgnoreCase("Out Time") && i + 1 < lines.length)
                        outTime = lines[i + 1].trim();
                }

                // ✅ Normalize times for comparison
                String uiIn = inTime.replaceAll("\\s+", "").toLowerCase();
                String uiOut = outTime.replaceAll("\\s+", "").toLowerCase();

                String expInMorning = expectedInTimeMorning.replaceAll("\\s+", "").toLowerCase();
                String expOutMorning = expectedOutTimeMorning.replaceAll("\\s+", "").toLowerCase();
                String expInNoon = expectedInTimeNoon.replaceAll("\\s+", "").toLowerCase();
                String expOutNoon = expectedOutTimeNoon.replaceAll("\\s+", "").toLowerCase();

                System.out.println("📅 Day Type (UI): " + dayType);
                System.out.println("📋 Status (UI): " + uiStatus);
                System.out.println("🕓 UI In/Out: " + inTime + " - " + outTime);
                System.out.println("🌅 Expected Morning Shift: " + expectedInTimeMorning + " - " + expectedOutTimeMorning + " | Status: " + expectedStatusMorning);
                System.out.println("🌇 Expected Noon Shift: " + expectedInTimeNoon + " - " + expectedOutTimeNoon + " | Status: " + expectedStatusNoon);

                // ✅ Validate Day Type
                if (!dayType.equalsIgnoreCase(expectedDayType)) {
                    exceptionDesc = "Day Type mismatch! Expected: " + expectedDayType + " | UI: " + dayType;
                    return false;
                }

                // ✅ Morning shift validation
                if (uiIn.equals(expInMorning) && uiOut.equals(expOutMorning)) {
                    if (!uiStatus.equalsIgnoreCase(expectedStatusMorning)) {
                        exceptionDesc = "Morning shift status mismatch! Expected: " + expectedStatusMorning + " | UI: " + uiStatus;
                        return false;
                    }
                    System.out.println("🌅 Morning shift validated successfully: " + inTime + " - " + outTime + " (" + uiStatus + ")");
                    morningFound = true;
                }
                // ✅ Noon shift validation
                else if (uiIn.equals(expInNoon) && uiOut.equals(expOutNoon)) {
                    if (!uiStatus.equalsIgnoreCase(expectedStatusNoon)) {
                        exceptionDesc = "Noon shift status mismatch! Expected: " + expectedStatusNoon + " | UI: " + uiStatus;
                        return false;
                    }
                    System.out.println("🌇 Noon shift validated successfully: " + inTime + " - " + outTime + " (" + uiStatus + ")");
                    noonFound = true;
                } else {
                    System.out.println("⚠️ Card found but In/Out Time didn't match either shift pattern.");
                }

                // ✅ If both found, stop loop
                if (morningFound && noonFound) {
                    System.out.println("✅ Both morning and noon shift records validated successfully.");
                    return true;
                }
            }
        }

        // ✅ Final check
        if (!morningFound || !noonFound) {
            exceptionDesc = "Could not validate both Morning and Noon records for date: " + searchText;
            return false;
        }

        return true;

    } catch (Exception e) {
        exceptionDesc = "Error while validating multiple shift attendance: " + e.getMessage();
        return false;
    }
}


public boolean validateAttendanceForTwoConsecutiveDates(
        String firstDateFromExcel,
        String firstExpectedStatus,
        String secondDateFromExcel,
        String secondExpectedStatus) {
    try {
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        // ✅ Prepare formatted date strings
        LocalDate date1 = LocalDate.parse(firstDateFromExcel);
        LocalDate date2 = LocalDate.parse(secondDateFromExcel);

        String searchText1 = date1.format(dayFormatter) + " - " + date1.format(uiFormatter);
        String searchText2 = date2.format(dayFormatter) + " - " + date2.format(uiFormatter);

        System.out.println("🔍 Searching first date card: " + searchText1);
        System.out.println("🔍 Searching second date card: " + searchText2);

        // ✅ Step 1: Locate first date card (scroll if needed)
        WebElement firstCard = findAttendanceCardWithScroll(driverAndroid, searchText1);
        if (firstCard == null) {
            exceptionDesc = "❌ Could not locate first card for date: " + searchText1;
            return false;
        }

        String firstCardInfo = firstCard.getAttribute("content-desc");
        System.out.println("\n📅 Found record for " + searchText1 + ":\n" + firstCardInfo);
        boolean firstMatch = validateCardStatus(firstCardInfo, firstExpectedStatus, searchText1);

        // ✅ Step 2: Locate second date card (scroll if needed)
        WebElement secondCard = findAttendanceCardWithScroll(driverAndroid, searchText2);
        if (secondCard == null) {
            exceptionDesc = "❌ Could not locate second card for date: " + searchText2;
            return false;
        }

        String secondCardInfo = secondCard.getAttribute("content-desc");
        System.out.println("\n📅 Found record for " + searchText2 + ":\n" + secondCardInfo);
        boolean secondMatch = validateCardStatus(secondCardInfo, secondExpectedStatus, searchText2);

        // ✅ Final validation
        if (firstMatch && secondMatch) {
            System.out.println("\n✅ Both consecutive date statuses validated successfully!");
            return true;
        } else {
            System.out.println("\n❌ One or both validations failed!");
            return false;
        }

    } catch (Exception e) {
        exceptionDesc = "❌ Error while validating consecutive attendance: " + e.getMessage();
        return false;
    }
}

/**
 * Scrolls inside the ScrollView until the target date element is found.
 */
private WebElement findAttendanceCardWithScroll(AndroidDriver driver, String searchText) {
    try {
        // ✅ Locate the main ScrollView container
        WebElement scrollContainer = driver.findElement(AppiumBy.className("android.widget.ScrollView"));

        int maxScrolls = 10;
        for (int i = 0; i < maxScrolls; i++) {
            List<WebElement> cards = scrollContainer.findElements(
                    AppiumBy.xpath(".//android.view.View[contains(@content-desc,'" + searchText + "')]")
            );
            if (!cards.isEmpty()) {
                return cards.get(0);
            }

            // 🟡 Perform controlled scroll gesture *within* the ScrollView
            Map<String, Object> scrollParams = new HashMap<>();
            scrollParams.put("elementId", ((RemoteWebElement) scrollContainer).getId());
            scrollParams.put("direction", "down");
            scrollParams.put("percent", 0.8);
            driver.executeScript("mobile: scrollGesture", scrollParams);

            System.out.println("🔄 Scrolling ScrollView to find: " + searchText + " (Attempt " + (i + 1) + "/" + maxScrolls + ")");
        }
    } catch (Exception e) {
        System.out.println("⚠️ Scroll failed: " + e.getMessage());
    }
    return null;
}

/**
 * Validates that cardInfo contains the expected attendance status.
 */
private boolean validateCardStatus(String cardInfo, String expectedStatus, String dateLabel) {
    if (cardInfo == null || cardInfo.isEmpty()) return false;

    String lowerInfo = cardInfo.toLowerCase();
    String lowerExpected = expectedStatus.toLowerCase();

    if (lowerExpected.contains("week") && lowerExpected.contains("off")) {
        if (lowerInfo.contains("week off")) {
            System.out.println("🟢 " + dateLabel + " → Week Off validated successfully!");
            return true;
        } else {
            exceptionDesc = "❌ Expected Week Off but UI shows:\n" + cardInfo;
            return false;
        }
    }

    if (lowerInfo.contains(lowerExpected)) {
        System.out.println("🟢 " + dateLabel + " → Status validated successfully: " + expectedStatus);
        return true;
    } else {
        exceptionDesc = "❌ Status mismatch for " + dateLabel +
                " | Expected: " + expectedStatus + " | UI: " + cardInfo;
        return false;
    }
}


public boolean validateAttendanceByDateleave(String dateFromExcel, String expectedStatus) {
    try {
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        // Convert YYYY-MM-DD → "07 Jul 2025" and get day name
        LocalDate date = LocalDate.parse(dateFromExcel);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
        String dayName = date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH));

        String searchText = dayName + " - " + formattedDate;
        System.out.println("🔍 Searching attendance card: " + searchText);

        WebElement dayCard = findAttendanceCardWithScroll(driverAndroid, searchText);
        utils.waitForEle(dayCard , "visible", "", 50);
        if (dayCard == null) {
            exceptionDesc = "❌ Could not locate attendance card for: " + searchText;
            return false;
        }

        String cardInfo = dayCard.getAttribute("content-desc");
        System.out.println("✅ Found record:\n" + cardInfo);

        if (cardInfo.toLowerCase().contains(expectedStatus.toLowerCase())) {
            return true;
        }

        exceptionDesc = "❌ Status mismatch! Expected: " + expectedStatus + ", UI shows:\n" + cardInfo;
        return false;

    } catch (Exception e) {
        exceptionDesc = "❌ Error while validating attendance: " + e.getMessage();
        return false;
    }
}

/**
 * Scrolls until a matching attendance card is found.
 */


public boolean navigateToRequiredMonthAdmin(String excelDate) {
    try {
        // ✅ Input validation
        if (excelDate == null || excelDate.trim().isEmpty()) {
            exceptionDesc = "❌ Provided date from Excel is null or empty!";
            System.out.println(exceptionDesc);
            return false;
        }

        // ✅ Parse date
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(excelDate.trim());
        } catch (Exception e) {
            exceptionDesc = "❌ Invalid date format! Expected yyyy-MM-dd but got: " + excelDate;
            System.out.println(exceptionDesc);
            return false;
        }

        String targetMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));
        System.out.println("🎯 Target Month-Year: " + targetMonthYear);

        // ✅ Loop max 12 times
        for (int i = 0; i < 12; i++) {
            try {
                // ✅ Find visible month header (pattern like "Jul 2025")
                List<WebElement> headers = driver.findElements(AppiumBy.xpath("//android.view.View[matches(@content-desc,'^[A-Za-z]{3} [0-9]{4}$')]"));
                if (headers.isEmpty()) {
                    System.out.println("⚠ Month header not found on UI, retrying...");
                    Thread.sleep(1000);
                    continue;
                }

                WebElement monthHeader = headers.get(0);
                String currentMonthYear = monthHeader.getAttribute("content-desc").trim();
                System.out.println("📅 Current UI Month-Year: " + currentMonthYear);

                // ✅ Compare
                if (currentMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    System.out.println("✅ Month matched successfully!");
                    return true;
                }

                // ✅ Click "previous month" button inside same view
                try {
                    WebElement backBtn = driver.findElement(
                            AppiumBy.xpath("//android.view.View[@content-desc='" + currentMonthYear + "']/android.widget.ImageView[1]")
                    );
                    backBtn.click();
                    System.out.println("⬅ Clicked Previous month... Attempt " + (i + 1));
                    Thread.sleep(1200);
                } catch (Exception e1) {
                    System.out.println("⚠ Could not click back button for " + currentMonthYear + ": " + e1.getMessage());
                }

            } catch (Exception e) {
                System.out.println("⚠ Retry needed (loop " + (i + 1) + "): " + e.getMessage());
            }
        }

        exceptionDesc = "❌ Failed to navigate to target month: " + targetMonthYear;
        return false;

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in navigateToRequiredMonthAdmin(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}


public boolean clickOnEmployeeCard(String empIdOrName) {
    try {
        AndroidDriver driverAndroid = (AndroidDriver) driver;
        int maxScrolls = 10; // limit to avoid infinite loop
        boolean found = false;

        for (int i = 0; i < maxScrolls; i++) {
            // Locate any matching card in current viewport
            List<WebElement> empCards = driverAndroid.findElements(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + empIdOrName + "')]")
            );

            if (!empCards.isEmpty()) {
                WebElement empCard = empCards.get(0);
                System.out.println("✅ Found employee card for: " + empIdOrName);
                utils.waitForEle(empCard, "visible", "", 30);
                empCard.click();
                found = true;
                break;
            }

            // Scroll down if not found
            System.out.println("🔍 Employee card not visible yet, scrolling... Attempt " + (i + 1));
            Map<String, Object> scrollParams = new HashMap<>();
            scrollParams.put("direction", "down");
            scrollParams.put("percent", 0.8);
            driverAndroid.executeScript("mobile: scrollGesture", scrollParams);
        }

        if (!found) {
            exceptionDesc = "❌ Could not locate employee card for: " + empIdOrName;
            return false;
        }

        return true;

    } catch (Exception e) {
        exceptionDesc = "❌ Error while locating employee card: " + e.getMessage();
        return false;
    }
}


public boolean validateAttendanceStatusByDate(String dateFromExcel, String expectedStatus) {
    try {
        // ✅ Parse date from Excel (format: yyyy-MM-dd)
        LocalDate date = LocalDate.parse(dateFromExcel);
        int day = date.getDayOfMonth();
        String dayStr = String.valueOf(day);

        // ✅ XPath: match start of content-desc with day number (ignores line break issue)
        String xpath = "//android.view.View[starts-with(@content-desc,'" + dayStr + "')]";

        List<WebElement> dayCells = driver.findElements(AppiumBy.xpath(xpath));
        if (dayCells.isEmpty()) {
            exceptionDesc = "❌ Could not find day cell for date: " + dateFromExcel;
            System.out.println(exceptionDesc);
            return false;
        }

        // ✅ Pick the first match (usually unique)
        String dayInfo = dayCells.get(0).getAttribute("content-desc").trim();
        System.out.println("📅 Found day cell: " + dayInfo);

        // ✅ Extract status (after line break if present)
        String status = dayInfo.contains("\n")
                ? dayInfo.split("\n")[1].trim()
                : dayInfo.replaceAll("\\d+", "").trim(); // fallback if no newline

        // ✅ Compare
        if (status.equalsIgnoreCase(expectedStatus)) {
            System.out.println("✅ Attendance matched for " + dateFromExcel + " → " + expectedStatus);
            return true;
        } else {
            exceptionDesc = "❌ Mismatch for " + dateFromExcel + " | Expected: " + expectedStatus + " | Found: " + status;
            System.out.println(exceptionDesc);
            return false;
        }

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in validateAttendanceStatusByDate(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}


public boolean validateEmployeeAttendanceSummary(String empId, String expectedPresent, String expectedAbsent, String expectedLeave, String expectedOTHours) {
    try {
        // ✅ Scroll until employee card with given empId is found
        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"" + empId + "\"));";

        WebElement empCard = ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(uiScroll));
        String desc = empCard.getAttribute("content-desc").trim();

        System.out.println("📋 Employee Card Found for " + empId + ":\n" + desc);

        // ✅ Split by newlines
        String[] parts = desc.split("\\n+");
        if (parts.length < 10) {
            exceptionDesc = "❌ Invalid card structure for empId: " + empId + " | Content: " + desc;
            System.out.println(exceptionDesc);
            return false;
        }

        // ✅ Extract values based on structure
        String presentCount = parts[2].trim();  // 2
        String absentCount = parts[4].trim();   // 19
        String leaveCount = parts[6].trim();    // 1
        String otHours = parts[8].trim();       // 1:50

        System.out.println("🧾 Extracted Values → Present: " + presentCount + ", Absent: " + absentCount +
                ", Leave: " + leaveCount + ", OT Hours: " + otHours);

        // ✅ Compare all values
        if (!presentCount.equals(expectedPresent)) {
            exceptionDesc = "❌ Present count mismatch. Expected: " + expectedPresent + " | Found: " + presentCount;
            return false;
        }
        if (!absentCount.equals(expectedAbsent)) {
            exceptionDesc = "❌ Absent count mismatch. Expected: " + expectedAbsent + " | Found: " + absentCount;
            return false;
        }
        if (!leaveCount.equals(expectedLeave)) {
            exceptionDesc = "❌ Leave count mismatch. Expected: " + expectedLeave + " | Found: " + leaveCount;
            return false;
        }
        if (!otHours.equalsIgnoreCase(expectedOTHours)) {
            exceptionDesc = "❌ OT Hours mismatch. Expected: " + expectedOTHours + " | Found: " + otHours;
            return false;
        }

        System.out.println("✅ All summary values matched successfully for employee: " + empId);
        return true;

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in validateEmployeeAttendanceSummary(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}


public boolean validateEmployeeAttendanceSummaryOnEmp(String expectedPresent, String expectedAbsent, String expectedLeave, String expectedOTHours) {
    try {
        // ✅ Scroll until we find a summary card containing "Total Overtime"
        String uiScroll = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"Total Overtime\"));";

        WebElement empSummaryCard = ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(uiScroll));
        String desc = empSummaryCard.getAttribute("content-desc").trim();

        System.out.println("📋 Found Employee Summary Card:\n" + desc);

        // ✅ Split by newline and parse
        String[] parts = desc.split("\\n+");

        String presentCount = "N/A", absentCount = "N/A", leaveCount = "N/A", otHours = "N/A";

        for (int i = 0; i < parts.length; i++) {
            String line = parts[i].trim();

            if (line.equalsIgnoreCase("Present") && i > 0) {
                presentCount = parts[i - 1].trim();
            } else if (line.equalsIgnoreCase("Absent") && i > 0) {
                absentCount = parts[i - 1].trim();
            } else if (line.equalsIgnoreCase("Total Overtime") && i > 0) {
                otHours = parts[i - 1].trim().replace("Hrs", "").trim();
            } else if (line.equalsIgnoreCase("Leave") && i > 0) {
                leaveCount = parts[i - 1].trim(); // the number above "Leave" is the leave count
            }
        }

        System.out.println("🧾 Extracted → Present: " + presentCount +
                ", Absent: " + absentCount +
                ", Leave: " + leaveCount +
                ", OT Hours: " + otHours);

        // ✅ Compare extracted vs expected
        if (!presentCount.equals(expectedPresent)) {
            exceptionDesc = "❌ Present mismatch → Expected: " + expectedPresent + " | Found: " + presentCount;
            return false;
        }
        if (!absentCount.equals(expectedAbsent)) {
            exceptionDesc = "❌ Absent mismatch → Expected: " + expectedAbsent + " | Found: " + absentCount;
            return false;
        }
        if (!leaveCount.equals(expectedLeave)) {
            exceptionDesc = "❌ Leave mismatch → Expected: " + expectedLeave + " | Found: " + leaveCount;
            return false;
        }
        if (!otHours.equalsIgnoreCase(expectedOTHours)) {
            exceptionDesc = "❌ OT Hours mismatch → Expected: " + expectedOTHours + " | Found: " + otHours;
            return false;
        }

        System.out.println("✅ All attendance summary values matched successfully!");
        return true;

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in validateEmployeeAttendanceSummaryOnEmp(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}

public boolean navigateToRequiredMonthemps(WebDriver driver, String dateFromExcel) {
    try {
        // Convert date (yyyy-MM-dd) → MMM yyyy
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
        String expectedMonthYear = date.format(uiFormatter);

        System.out.println("🗓 Expected Month-Year (from date " + dateFromExcel + "): " + expectedMonthYear);

        // Locator for month header (e.g., Nov 2025)
        By monthHeader = By.xpath("//android.view.View[contains(@content-desc,'" + expectedMonthYear + "')]");
        // Locator for back button *after* the month header
       // By backButton = By.xpath("//android.view.View[contains(@content-desc,'202')]/following-sibling::android.widget.ImageView[1]");

        int retries = 0;
        int maxRetries = 12;

        while (retries < maxRetries) {
            try {
                WebElement header = driver.findElement(monthHeader);
                String currentMonth = header.getAttribute("content-desc").trim();

                if (currentMonth.equalsIgnoreCase(expectedMonthYear)) {
                    System.out.println("✅ Month-Year matched: " + currentMonth);
                    return true;
                } else {
                    System.out.println("⚠️ Month-Year not matched → Expected: " + expectedMonthYear + " | Found: " + currentMonth);
                    clickBackButton(driver);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Month header not found yet, retrying...");
                clickBackButton(driver);
            }

            Thread.sleep(1500);
            retries++;
        }

        System.out.println("❌ Month-Year mismatch → Expected: " + expectedMonthYear + " | Navigated back.");
        return false;

    } catch (Exception e) {
        System.out.println("❌ Exception in navigateToRequiredMonth(): " + e.getMessage());
        return false;
    }
}

// ✅ Helper method to safely click back button near month header
private void clickBackButton(WebDriver driver) {
    try {
        WebElement backBtn = driver.findElement(
                By.xpath("//android.view.View[contains(@content-desc,'202')]/following-sibling::android.widget.ImageView[1]"));
        backBtn.click();
        System.out.println("↩️ Clicked back navigation button");
    } catch (Exception e) {
        System.out.println("❌ Back button not found/click failed: " + e.getMessage());
    }
}





public boolean validateEmployeeAttendanceStatusByDateOnEMp(String dateFromExcel, String expectedStatus) {
    try {
        // ✅ Parse the date (format: yyyy-MM-dd)
        LocalDate date = LocalDate.parse(dateFromExcel);
        int day = date.getDayOfMonth();
        String dayStr = String.valueOf(day);

        System.out.println("🔎 Checking attendance for date: " + dateFromExcel + " (Day: " + dayStr + ")");

        // ✅ Locate day cell (e.g., "22\nP", "9\nL")
        String xpath = "//android.view.View[starts-with(@content-desc,'" + dayStr + "')]";
        List<WebElement> dayCells = driver.findElements(AppiumBy.xpath(xpath));

        if (dayCells.isEmpty()) {
            exceptionDesc = "❌ Could not find any day cell for date: " + dateFromExcel + " (Day: " + dayStr + ")";
            System.out.println(exceptionDesc);
            return false;
        }

        // ✅ Pick first match
        String dayInfo = dayCells.get(0).getAttribute("content-desc").trim();
        System.out.println("📅 Found day info: " + dayInfo);

        // ✅ Extract status (after newline)
        String status = dayInfo.contains("\n")
                ? dayInfo.split("\n")[1].trim()
                : dayInfo.replaceAll("\\d+", "").trim();

        // ✅ Defensive cleaning (e.g., if status includes extra text)
        status = status.replaceAll("[^A-Za-z]", "").trim();

        // ✅ Compare expected vs actual
        if (status.equalsIgnoreCase(expectedStatus)) {
            System.out.println("✅ Matched: " + dateFromExcel + " → " + expectedStatus);
            return true;
        } else {
            exceptionDesc = "❌ Mismatch for " + dateFromExcel + " | Expected: " + expectedStatus + " | Found: " + status;
            System.out.println(exceptionDesc);
            return false;
        }

    } catch (Exception e) {
        exceptionDesc = "❌ Exception in validateEmployeeAttendanceStatusByDate(): " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}


public boolean RegularizationRejectButton(String empId, String dateFromExcel) {
    try {
        AndroidDriver driverAndroid = (AndroidDriver) driver;

        // Convert date to UI format (e.g., "04 Nov 2025")
        LocalDate date = LocalDate.parse(dateFromExcel);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        String formattedDate = date.format(formatter);

        System.out.println("🔍 Searching for PENDING card with EmpID: " + empId + " and Date: " + formattedDate);

        boolean found = false;
        int maxScrolls = 6;

        for (int i = 0; i < maxScrolls; i++) {

            // Get all visible employee cards
            List<WebElement> allCards = driverAndroid.findElements(
                AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + empId + "')]")
            );

            for (WebElement card : allCards) {
                String cardDesc = card.getAttribute("contentDescription");

                // Check both EmpID + Date + ensure it's still Pending
                if (cardDesc.contains(empId) && cardDesc.contains(formattedDate) && cardDesc.contains("Pending")) {
                    System.out.println("✅ Found Pending card:\n" + cardDesc);

                    // Scroll this card slightly into view
                    ((JavascriptExecutor) driverAndroid).executeScript("mobile: scrollGesture", Map.of(
                        "elementId", ((RemoteWebElement) card).getId(),
                        "direction", "down",
                        "percent", 0.3
                    ));

                    // Try to locate REJECT button near this card
                    List<WebElement> rejectBtns = driverAndroid.findElements(
                        AppiumBy.xpath("//android.widget.Button[@content-desc='REJECT']")
                    );

                    if (!rejectBtns.isEmpty()) {
                        WebElement rejectBtn = rejectBtns.get(0); // assume first REJECT belongs to this card
                        utils.waitForEle(rejectBtn, "visible", "", 30);
                        rejectBtn.click();

                        System.out.println("👎 Clicked REJECT for EmpID: " + empId + " on Date: " + formattedDate);
                        found = true;
                        break;
                    } else {
                        System.out.println("⚠️ No REJECT button visible — card might already be acted upon.");
                    }
                }
            }

            if (found) break;

            // Scroll if not found
            System.out.println("↕️ Scrolling... Attempt " + (i + 1));
            Dimension size = driverAndroid.manage().window().getSize();
            int startY = (int) (size.height * 0.7);
            int endY = (int) (size.height * 0.3);
            int centerX = size.width / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence scroll = new Sequence(finger, 1);
            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driverAndroid.perform(Arrays.asList(scroll));
        }

        if (!found) {
            exceptionDesc = "❌ No Pending card found for EmpID: " + empId + " and Date: " + formattedDate;
            System.out.println(exceptionDesc);
            return false;
        }

        return true;

    } catch (Exception e) {
        exceptionDesc = "❌ Error while rejecting regularization request: " + e.getMessage();
        System.out.println(exceptionDesc);
        return false;
    }
}






















public String getExceptionDesc() {
    return exceptionDesc;
}}


