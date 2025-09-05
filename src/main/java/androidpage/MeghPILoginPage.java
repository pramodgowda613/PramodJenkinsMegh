package androidpage;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumBy;



import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;


public class MeghPILoginPage {

    private AndroidDriver driver;
    private String exceptionDesc;

    public MeghPILoginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   
    
    @FindBy(xpath = "//android.widget.EditText")
    private WebElement companyCodeTextField;



    @FindBy(xpath = "//android.widget.Button[@content-desc='Login with Password']")
    private WebElement loginWithPasswordButton;
  //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]

    @FindBy(xpath = "  //android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")
    private WebElement UserNameTextField;
    
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
    private WebElement PasswordTextField;
    
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Login\"]")
    private WebElement LoginButton;

    public boolean enterCompanyCode() {
        try {
        	
        	
        	WebElement companyCodeTextField = driver.findElement(
        	        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").enabled(true)")
        	);        	
        	companyCodeTextField.click();
        	Thread.sleep(3000);
        	companyCodeTextField.sendKeys("MAN0");
            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }
    
    

    public boolean clickLoginWithPassword() {
        try {
            loginWithPasswordButton.click();
            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }
    
    public boolean UserNameTextField() {
        try {
        	Thread.sleep(3000);
        	UserNameTextField.click();
        	UserNameTextField.sendKeys("pramod.c@mantratec.com");
            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }
    
    public boolean PasswordTextField() {
        try {
        	Thread.sleep(3000);
        	PasswordTextField.click();
        	PasswordTextField.sendKeys("Pramod@1234");
            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }
    
    public boolean LoginButton() {
        try {
        	driver.navigate().back();
        	Thread.sleep(2000);
        	LoginButton.click();
            return true;
        } catch (Exception e) {
            exceptionDesc = e.getMessage();
            return false;
        }
    }
    
    

    public String getExceptionDesc() {
        return exceptionDesc;
    }
}
