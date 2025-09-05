package androidtest;

import org.testng.annotations.Test;
import androidpage.LoginBaseClass;
import androidpage.MeghPILoginPage;

public class MeghPILoginTest extends LoginBaseClass {

    @Test
    public void testLogin() throws InterruptedException {
        MeghPILoginPage loginPage = new MeghPILoginPage(driver); // Driver from LoginBaseClass

        // Step 1: Enter Company Code
        driver.activateApp("com.mantra.praveshsaas");
        Thread.sleep(2000);
        if (!loginPage.enterCompanyCode()) {
            System.out.println("Company code entry failed: " + loginPage.getExceptionDesc());
        } else {
            System.out.println("Company code entered successfully.");
        }

        // Step 2: Click Login with Password
        if (!loginPage.clickLoginWithPassword()) {
            System.out.println("Login With Password Button  click failed: " + loginPage.getExceptionDesc());
        } else {
            System.out.println("Login With Password button clicked successfully.");
        }
        
     
        
        if (!loginPage.UserNameTextField()) {
            System.out.println("UserName Inputting failed: " + loginPage.getExceptionDesc());
        } else {
            System.out.println("User Name entered successfully.");
        }
        
        if (!loginPage.PasswordTextField()) {
            System.out.println("Password entry failed: " + loginPage.getExceptionDesc());
        } else {
            System.out.println("Password entered successfully.");
        }
        
        if (!loginPage.LoginButton()) {
            System.out.println("Login Button Click failed: " + loginPage.getExceptionDesc());
        } else {
            System.out.println("Login Button Clicked successfully.");
        }
        
        
        
        
        
        
        
    }
}
