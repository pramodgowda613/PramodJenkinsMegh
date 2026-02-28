package com.MeghPI.Attendance.Android.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Pramod;
import utils.Utils;


public class MeghPIAttendAndroidLoginPage {

    private WebDriver driver;
    private String exceptionDesc;
	Utils utils = new Utils(driver);
	
    public MeghPIAttendAndroidLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//android.widget.EditText")
    private WebElement companyCodeTextField;

    @FindBy(xpath = "//android.widget.Button[@content-desc='Login with Password']")
    private WebElement loginWithPasswordButton;
  //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
    private WebElement UserNameTextField;
                     

    
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
    private WebElement PasswordTextField;
    
                     


    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Login\"]")
    private WebElement LoginButton;
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[1]")
    private WebElement ManageButton;
    
  



    
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Logout\"]")
    private WebElement LogoutButton;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    private WebElement LogoutConfirmButton;
    
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Inbox\"]")
    private WebElement DashBoardPageLoaded;


    @FindBy(xpath = "//android.view.View[@content-desc=\"Login with OTP\"]")
    private WebElement LoginWithOTPClicked;
  
  
    @FindBy(xpath = "//android.widget.EditText")
    private WebElement MailIDForOTPLogin;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Request OTP\"]")
    private WebElement RequestOTPButton;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[2]")
    private WebElement QuickActionForValidation;
  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public boolean enterCompanyCode(String cmpcode) {
        try {
            utils.waitForEle(companyCodeTextField, "visible", "", 30);
            companyCodeTextField.click();
            companyCodeTextField.sendKeys(cmpcode);

            // ✅ Safe keyboard hide (no fail even if keyboard isn't open)
            Pramod.hideKeyboardSmart(driver);
            
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
        return true;
    }

    
    
    

    public boolean clickLoginWithPassword() {
      
            try {
    			utils.waitForEle(loginWithPasswordButton, "visible", "", 30);
    			loginWithPasswordButton.click();
    		} catch (Exception e) {
    			exceptionDesc=	e.getMessage().toString();
    			return false;
    		}
    		return true;
    	}
    
    public boolean UserNameTextField(String username) {
        try {
            utils.waitForEle(UserNameTextField, "visible", "", 20);
            UserNameTextField.click();
            UserNameTextField.sendKeys(username);
            
            // ✅ Hide keyboard AFTER typing
            Pramod.hideKeyboardSmart(driver);
            
        
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
        return true;
    }

    
    public boolean PasswordTextField(String password) {
        try {
     
            utils.waitForEle(PasswordTextField, "visible", "", 30);
            PasswordTextField.click();
            
            PasswordTextField.sendKeys(password);
            Pramod.hideKeyboardSmart(driver);
           
            
        
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
        return true;
    }

    
    public boolean LoginButton() {
        int maxRetries = 3;

        try {

            utils.waitForEle(LoginButton, "visible", "", 30);

            for (int attempt = 1; attempt <= maxRetries; attempt++) {

                // Click Login button
                LoginButton.click();

                // Wait for validation element
                if (utils.waitForEle(QuickActionForValidation, "visible", "", 20)) {
                    return true; // success
                }

                // Retry if not visible
                if (attempt < maxRetries) {
                    System.out.println("QuickAction not visible. Retrying Login click. Attempt: " + attempt);
                    Thread.sleep(1500); // small wait before retry
                }
            }

            exceptionDesc = "QuickActionForValidation not visible after " + maxRetries + " login attempts.";
            return false;

        } catch (Exception e) {
            exceptionDesc = "Exception in LoginButton(): " + e.getMessage();
            return false;
        }
    }


    public boolean ManageButton() {
        int maxRetries = 8;
        int retry = 0;

        try {
            while (retry < maxRetries) {

                System.out.println("🔁 Attempt " + (retry + 1) + " to open Manage screen");

                utils.waitForEle(ManageButton, "enable", "", 50);
                ManageButton.click();
                Thread.sleep(1500);

                // ✅ Check if Manage screen opened
                if (Pramod.isElementPresent(LogoutButton)) {
                    System.out.println("✅ Manage screen opened successfully on attempt " + (retry + 1));
                    return true;
                }

                System.out.println("⚠ Manage not opened, retrying...");
                retry++;

                // ⏪ Go back & retry only if not the last attempt
                if (retry < maxRetries) {
                    driver.navigate().back();
                    Thread.sleep(2000);
                }
            }

            // ❌ If loop completes without return, Manage failed
            exceptionDesc = "❌ Failed to open Manage screen after " + maxRetries + " attempts";
            return false;

        } catch (Exception e) {
            exceptionDesc = "❌ Exception in ManageButton(): " + e.getMessage();
            return false;
        }
    }




    
public boolean LogoutButton() {
        
        try {
			utils.waitForEle(LogoutButton, "visible", "", 20);
			LogoutButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

public boolean LogoutConfirmButton() {
    
    try {
		utils.waitForEle(LogoutConfirmButton, "visible", "", 30);
		LogoutConfirmButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}



public boolean DashBoardPageLoaded() {
    
    try {
    	Thread.sleep(4000);	
    	utils.waitForEle(DashBoardPageLoaded, "visible", "", 30);
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


public boolean LoginWithOTPClicked() {
    
    try {
    	
		utils.waitForEle(LoginWithOTPClicked, "visible", "", 20);
		LoginWithOTPClicked.click();
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean MailIDForOTPLogin(String Email) {
    try {
        utils.waitForEle(MailIDForOTPLogin, "visible", "", 30);
        MailIDForOTPLogin.click();
        MailIDForOTPLogin.sendKeys(Email);

        Pramod.hideKeyboardSmart(driver); // ✅ smart keyboard hide call

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
    return true;
}


public boolean RequestOTPButton() {
    
    try {
    	
		utils.waitForEle(RequestOTPButton, "visible", "", 20);
		RequestOTPButton.click();
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
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
