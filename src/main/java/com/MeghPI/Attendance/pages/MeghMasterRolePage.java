package com.MeghPI.Attendance.pages;


import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghMasterRolePage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String RoleNames = "";
	 

	
	public MeghMasterRolePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//a[@id='tab_Role']")
	private WebElement RoleButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddRole'])[2]")
	private WebElement AddRoleButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtRoleName']")
	private WebElement RoleNameTextField; //1st TestCase
	
	@FindBy(xpath = "//textarea[@id='txtRoleDescription']")
	private WebElement RoleDescriptionField; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnRoleSave']")
	private WebElement RoleSaveButton; //1st TestCase
	
	@FindBy(xpath = "//span[@class='select2-selection select2-selection--multiple']")
	private WebElement ManageProfileRoleTextField; //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpRole-result')])[1]")
	private WebElement ManageProfileRoleSearchResult; //1st TestCase
	
	@FindBy(xpath = "(//div[contains(@id, 'divActionRole')])[1]/div/img")
	private WebElement Role3Dots2ndRow; //2nd TestCase
	
	@FindBy(xpath = "(//a[@data-bs-target='#phRoleEditor'])[1]/..")
	private WebElement RoleEdit2ndRow; //2nd TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[2]")
	private WebElement RoleSearchTextField; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtRole']/tbody/tr[1]/td[1]")
	private WebElement RoleFirstRowName; //2nd TestCase
	
	@FindBy(xpath = "(//input[contains(@id, 'chxdtRole')])[1]")
	private WebElement RoleToggleSwitch; //4th TestCase
	
	@FindBy(xpath = "//table[@id='dtRole']/tbody/tr[1]/td[1]")
	private WebElement RoleRecords; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtRolePermission']/tbody/tr[1]/td[1]")
	private WebElement RolePermissionRecords; //7th TestCase
	
	
	@FindBy(xpath = "//button[@id='tab_RolePermission']")
	private WebElement RolePermissionButton; //7th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemIsActivedtRole']")
	private WebElement StatusCheckbox; //7th TestCase
	
	@FindBy(xpath = "//div[text()='Role Name']")
	private WebElement RoleModulePageLoaded; //7th TestCase
	
	
	//1st TestCase
	public boolean RoleButton()
	{
		try {
			utils.waitForEle(RoleButton, "visible", "", 10);
			RoleButton.isDisplayed();
			RoleButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean AddRoleButton()
	{
		try {
			utils.waitForEle(AddRoleButton, "visible", "", 10);
			AddRoleButton.isDisplayed();
			AddRoleButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleNameTextField(String rolename) {
		try {

			utils.waitForEle(RoleNameTextField,  "visible", "", 10);
			RoleNameTextField.isDisplayed();
			RoleNameTextField.clear();
			RoleNameTextField.sendKeys(rolename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleDescriptionField(String roledescription) {
		try {

			utils.waitForEle(RoleDescriptionField,  "visible", "", 10);
			RoleDescriptionField.isDisplayed();
			RoleDescriptionField.sendKeys(roledescription);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleSaveButton()
	{
		try {
			utils.waitForEle(RoleSaveButton, "visible", "", 10);
			RoleSaveButton.isDisplayed();
			RoleSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileRoleTextDropdown() {
		try {

			utils.waitForEle(ManageProfileRoleTextField, "visible", "", 10);
			ManageProfileRoleTextField.isDisplayed();
			ManageProfileRoleTextField.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileRoleTextField(String rolename) {
		try {
			
			Thread.sleep(10000);

			utils.waitForEle(ManageProfileRoleTextField,  "visible", "", 10);
			ManageProfileRoleTextField.isDisplayed();
			ManageProfileRoleTextField.sendKeys(rolename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	

	public boolean ManageProfileRoleSearchResult()
	{
		
		try {
			Thread.sleep(4000);
			utils.waitForEle(ManageProfileRoleSearchResult, "visible", "", 10);
			ManageProfileRoleSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean Role3Dots2ndRow() {
		try {

			utils.waitForEle(Role3Dots2ndRow, "visible", "", 10);
			Role3Dots2ndRow.isDisplayed();
			Role3Dots2ndRow.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean RoleEdit2ndRow() {
		try {

			utils.waitForEle(RoleEdit2ndRow, "visible", "", 10);
			RoleEdit2ndRow.isDisplayed();
			RoleEdit2ndRow.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleSearchTextField(String rolename) {
		try {

			utils.waitForEle(RoleSearchTextField,  "visible", "", 10);
			RoleSearchTextField.isDisplayed();
			RoleSearchTextField.clear();
			RoleSearchTextField.sendKeys(rolename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleFirstRowName() {
		try {

			utils.waitForEle(RoleFirstRowName, "visible", "", 10);
			RoleFirstRowName.isDisplayed();
		RoleNames =	RoleFirstRowName.getText();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean RoleToggleSwitch() {
		try {

			utils.waitForEle(RoleToggleSwitch, "visible", "", 10);
			RoleToggleSwitch.isDisplayed();
			RoleToggleSwitch.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//7th Record
	
	public boolean RoleRecords() {
		try {

			utils.waitForEle(RoleRecords, "visible", "", 10);
			RoleRecords.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RolePermissionRecords() {
		try {

			utils.waitForEle(RolePermissionRecords, "visible", "", 10);
			RoleRecords.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RolePermissionButton() {
		try {

			utils.waitForEle(RolePermissionButton, "visible", "", 10);
			RolePermissionButton.isDisplayed();
			RolePermissionButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	 public List<String> getRoleRecordsss() throws InterruptedException {
	        List<String> recordList = new ArrayList<>();
	        Thread.sleep(2000);
	       
	        driver.get("http://demo.meghpi.com/Directory/Company");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//a[@id='tab_Role']")).click();
	        Thread.sleep(2000);
	        
	        
	        
List<WebElement> elements = driver.findElements(By.xpath("//table[@id='dtRole']/tbody/tr/td[1]"));
Thread.sleep(2000);
for(WebElement el: elements)
{
	 recordList.add(el.getText().trim());
	
}
return recordList;

	 }
	
	 public List<String> getRolePermissionRecords() throws InterruptedException {
	        List<String> recordList2 = new ArrayList<>();
	        
	        driver.get("http://demo.meghpi.com/Directory/Company");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[@id='tab_RolePermission']")).click();
	        Thread.sleep(4000);
List<WebElement> elements2 = driver.findElements(By.xpath("//table[@id='dtRolePermission']/tbody/tr/td[1]"));
for(WebElement el2: elements2)
{
	 recordList2.add(el2.getText().trim());
	 
}
return recordList2;

	 }
	
	 public boolean Comparision() {
		    try {
		        List<String> moduleRoleList = getRoleRecordsss();
		        List<String> permissionRoleList = getRolePermissionRecords();

		        System.out.println("Module Role List: " + moduleRoleList);
		        System.out.println("Permission Role List: " + permissionRoleList);

		        // First check: size should be equal
		        if (moduleRoleList.size() != permissionRoleList.size()) {
		            System.out.println("Comparison failed: Size mismatch");
		            return false;
		        }

		        // Compare each item case-insensitively
		        for (int i = 0; i < moduleRoleList.size(); i++) {
		            String role = moduleRoleList.get(i).trim();
		            String permission = permissionRoleList.get(i).trim();

		            if (!role.equalsIgnoreCase(permission)) {
		                System.out.println("Comparison failed at index " + i + ": " + role + " vs " + permission);
		                return false;
		            }
		        }

		        System.out.println("All role records matched successfully.");
		        return true;

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}

	 
	 public boolean ManageProfileRoleSearchResultselect() {
			try {

				utils.waitForEle(ManageProfileRoleSearchResult, "visible", "", 10);
				ManageProfileRoleSearchResult.isDisplayed();
				ManageProfileRoleSearchResult.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	
	 
	 public boolean StatusCheckbox() {
			try {

				utils.waitForEle(StatusCheckbox, "visible", "", 10);
				StatusCheckbox.isDisplayed();
				StatusCheckbox.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	 
	 public boolean RoleModulePageLoaded() {
			try {

				utils.waitForEle(RoleModulePageLoaded, "visible", "", 20);
				RoleModulePageLoaded.isDisplayed();
				
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = exceptionDesc;
	}
	
	
}
