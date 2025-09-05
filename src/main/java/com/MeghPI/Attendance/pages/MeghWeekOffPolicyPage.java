package com.MeghPI.Attendance.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghWeekOffPolicyPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String WeekOffPolicyName = "";
	public String WeekOffPolicyNames = "";
	
	
	
	public MeghWeekOffPolicyPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//body[@id='addCustomClassForBody']/section/div/div/div[2]/div[1]/div[1]/a[2]")
	private WebElement WeekOffPolicyButton ; //1stTestCase	
	
	@FindBy(xpath = "(//button[@id='btnAddWeeklyoffPolicy'])[2]")
	private WebElement AddWeekOffPolicyButton ; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtPolicyName']")
	private WebElement WeekOffPolicyNameTextField ; //1stTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[4]/td[2]")
	private WebElement ThursdayButton ; //1stTestCase
	
	@FindBy(xpath = "//li[text()='2nd Half']")
	private WebElement SecondHalf ; //1stTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[5]/td[2]")
	private WebElement FridayButton ; //1stTestCase
	
	@FindBy(xpath = "//li[text()='Full Day']")
	private WebElement FullDayButton ; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnCreatePolicy']")
	private WebElement CreateWeekOffPolicyButton ; //1stTestCase
	
	@FindBy(xpath = "//div[@id='v-pills-grade']/div/div/div[2]/div[3]/div/div/div/p[1]")
	private WebElement WeekOffPolicyNameInEmp ; //1stTestCase
	
	@FindBy(xpath = "//div[@id='dtWeeklyoffPolicy_filter']/label/input")
	private WebElement WeekOffPolicySearchTextField ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtWeeklyoffPolicy']/tbody/tr[1]/td[5]/a/button")
	private WebElement WeekOffEditButton ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[6]/td[3]")
	private WebElement SaturdayButton1stWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[7]/td[3]")
	private WebElement SundayButton1stWeek ; //2ndTestCase
	
	
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[7]/td[6]")
	private WebElement SundayButton4thWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[6]/td[6]")
	private WebElement SaturdayButton4thWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[7]/td[4]")
	private WebElement SundayButton2ndWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[6]/td[4]")
	private WebElement SaturdayButton2ndWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[7]/td[5]")
	private WebElement SundayButton3rdWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[6]/td[5]")
	private WebElement SaturdayButton3rdWeek ; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtWeeklyoffPolicy']/tbody/tr[1]/td[2]")
	private WebElement AfterEditWeekOffData ; //2ndTestCase
	
	@FindBy(xpath = "//select[@id='drpTeam']")
	private WebElement TeamDropDown ; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btnApply']")
	private WebElement ApplyButton ; //3rd TestCase
	
	
	@FindBy(xpath = "//input[@id='chkDefault']")
	private WebElement DefaultCheckBox ; //4th TestCase
	
	@FindBy(xpath = "//table[@id='dtWeeklyoffPolicy']/tbody/tr[1]/td[5]/button")
	private WebElement DeleteButton ; //6th TestCase
	
	@FindBy(xpath = "//table[@id='dtWeeklyoffPolicy']/tbody/tr[1]/td[1]")
	private WebElement WeekOffPolicyFirstRecord ; //6th TestCase
	
	@FindBy(xpath = "//input[@id='chkActive']")
	private WebElement ActiveCheckBox ; //6th TestCase
	
	@FindBy(xpath = "//table[@id='dtWeeklyoffPolicy']/tbody/tr[1]/td[3]/a")
	private WebElement AssignedEmpLinkCount ; //10th TestCase
	
	@FindBy(xpath = "//div[@id='dtEmployeeDetails_filter']/label/input")
	private WebElement AssignedEmpLinkCountSearchTextField ; //10th TestCase
	
	@FindBy(xpath = "//table[@id= 'dtEmployeeDetails']/tbody/tr[1]")
	private WebElement AssignedEmpLinkCountFirstRecord ; //10th TestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpTeam-result')]")
	private WebElement TeamOptionSelected ; //10th TestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[6]/td[2]")
	private WebElement SaturdayOption ; //11th TestCase
	
	@FindBy(xpath = "//li[text()='Full Day']")
	private WebElement FullDaySelected ; //11th TestCase
	
	@FindBy(xpath = "//table[@id='tblWeeklyOffPolicy']/tbody/tr[7]/td[2]")
	private WebElement SundayOption ; //11th TestCase
	
	
	//1st TestCase
		public boolean WeekOffPolicyButton()
		{
			try {
				utils.waitForEle(WeekOffPolicyButton, "visible", "", 10);
				WeekOffPolicyButton.isDisplayed();
				WeekOffPolicyButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AddWeekOffPolicyButton()
		{
			try {
				utils.waitForEle(AddWeekOffPolicyButton, "visible", "", 10);
				AddWeekOffPolicyButton.isDisplayed();
				AddWeekOffPolicyButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean WeekOffPolicyNameTextField(String policyname) {
			try {

				Thread.sleep(4000);
				utils.waitForEle(WeekOffPolicyNameTextField,  "visible", "", 10);
				WeekOffPolicyNameTextField.isDisplayed();
				WeekOffPolicyNameTextField.clear();
				WeekOffPolicyNameTextField.sendKeys(policyname);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean ThursdayButton()
		{
			try {
				utils.waitForEle(ThursdayButton, "visible", "", 10);
				ThursdayButton.isDisplayed();
				ThursdayButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean SecondHalf()
		{
			try {
				utils.waitForEle(SecondHalf, "visible", "", 10);
				SecondHalf.isDisplayed();
				SecondHalf.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean FridayButton()
		{
			try {
				utils.waitForEle(FridayButton, "visible", "", 10);
				FridayButton.isDisplayed();
				FridayButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean FullDayButton()
		{
			try {
				utils.waitForEle(FullDayButton, "visible", "", 10);
				FullDayButton.isDisplayed();
				FullDayButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean CreateWeekOffPolicyButton()
		{
			try {
				utils.waitForEle(CreateWeekOffPolicyButton, "visible", "", 10);
				CreateWeekOffPolicyButton.isDisplayed();
				CreateWeekOffPolicyButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public String getExceptionDesc() {
			return this.exceptionDesc;
		}
		
		public boolean WeekOffPolicyNameInEmp(String policyname) {
		    try {
		    	Thread.sleep(4000);
		    WeekOffPolicyName	= WeekOffPolicyNameInEmp.getText();
		        if (WeekOffPolicyName.equalsIgnoreCase(policyname)) {
		            return true; // Strings match
		        } else {
		            throw new Exception("Assigned Policy Name comparison failed: values do not match." + WeekOffPolicyName + "=" + policyname );
		        }
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}
		
		//2nd TestCase
		public boolean WeekOffPolicySearchTextField(String policyname) {
			try {

				Thread.sleep(4000);
				utils.waitForEle(WeekOffPolicySearchTextField,  "visible", "", 10);
				WeekOffPolicySearchTextField.isDisplayed();
				WeekOffPolicySearchTextField.clear();
				WeekOffPolicySearchTextField.sendKeys(policyname);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean WeekOffEditButton()
		{
			try {
				Thread.sleep(4000);
				utils.waitForEle(WeekOffEditButton, "visible", "", 10);
				WeekOffEditButton.isDisplayed();
				WeekOffEditButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SaturdayButton1stWeek()
		{
			try {
				utils.waitForEle(SaturdayButton1stWeek, "visible", "", 10);
				SaturdayButton1stWeek.isDisplayed();
				SaturdayButton1stWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SundayButton1stWeek()
		{
			try {
				utils.waitForEle(SundayButton1stWeek, "visible", "", 10);
				SundayButton1stWeek.isDisplayed();
				SundayButton1stWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SundayButton4thWeek()
		{
			try {
				utils.waitForEle(SundayButton4thWeek, "visible", "", 10);
				SundayButton4thWeek.isDisplayed();
				SundayButton4thWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SaturdayButton4thWeek()
		{
			try {
				utils.waitForEle(SaturdayButton4thWeek, "visible", "", 10);
				SaturdayButton4thWeek.isDisplayed();
				SaturdayButton4thWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SundayButton2ndWeek()
		{
			try {
				utils.waitForEle(SundayButton2ndWeek, "visible", "", 10);
				SundayButton2ndWeek.isDisplayed();
				SundayButton2ndWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SaturdayButton2ndWeek()
		{
			try {
				utils.waitForEle(SaturdayButton2ndWeek, "visible", "", 10);
				SaturdayButton2ndWeek.isDisplayed();
				SaturdayButton2ndWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SundayButton3rdWeek()
		{
			try {
				utils.waitForEle(SundayButton3rdWeek, "visible", "", 10);
				SundayButton3rdWeek.isDisplayed();
				SundayButton3rdWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SaturdayButton3rdWeek()
		{
			try {
				utils.waitForEle(SaturdayButton3rdWeek, "visible", "", 10);
				SaturdayButton3rdWeek.isDisplayed();
				SaturdayButton3rdWeek.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AfterEditWeekOffData()
		{
			try {
				utils.waitForEle(AfterEditWeekOffData, "visible", "", 10);
				AfterEditWeekOffData.isDisplayed();
			Actions act = new Actions(driver);
			act.moveToElement(AfterEditWeekOffData).perform();
			Thread.sleep(4000);
			
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		//3rd TestCase
		
		public boolean TeamDropDown(String teamname)
		{
			try {
				Thread.sleep(3000);
				utils.waitForEle(TeamDropDown, "visible", "", 10);
				TeamDropDown.isDisplayed();
		Select team = new Select(TeamDropDown);
		team.selectByVisibleText(teamname);
			
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		

		public boolean ApplyButton()
		{
			try {
				utils.waitForEle(ApplyButton, "visible", "", 10);
				ApplyButton.isDisplayed();
				ApplyButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean WeekOffPolicyNameInEmps(String expectedPolicyName) {
		    int attempts = 0;

		    while (attempts < 2) {
		        try {
		            Thread.sleep(3000); // Wait before attempting
		            if (WeekOffPolicyNameInEmp == null) {
		                throw new Exception("WeekOffPolicyNameInEmp element is null.");
		            }

		            String actualPolicyName = WeekOffPolicyNameInEmp.getText().trim();

		            if (actualPolicyName != null && actualPolicyName.toLowerCase().contains(expectedPolicyName.toLowerCase())) {
		                return true;
		            } else {
		                throw new Exception("Assigned Policy Name mismatch: Expected to contain '" 
		                        + expectedPolicyName + "', but found '" + actualPolicyName + "'");
		            }

		        } catch (Exception e) {
		            exceptionDesc = "Attempt " + (attempts + 1) + " failed: " + e.getMessage();
		            attempts++;

		            if (attempts >= 2) {
		                return false; // Return after last failed attempt
		            }
		        }
		    }

		    return false; // Fallback (should not reach)
		}


		
		//4th TestCase
		
		public boolean DefaultCheckBox()
		{
			try {
				utils.waitForEle(DefaultCheckBox, "visible", "", 10);
				DefaultCheckBox.isDisplayed();
				DefaultCheckBox.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		//6th TestCase
		
		public boolean DeleteButton()
		{
			try {
				Thread.sleep(4000);
				utils.waitForEle(DeleteButton, "visible", "", 10);
				DeleteButton.isDisplayed();
				DeleteButton.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean WeekOffPolicyFirstRecord()
		{
			try {
				utils.waitForEle(WeekOffPolicyFirstRecord, "visible", "", 10);
				WeekOffPolicyFirstRecord.isDisplayed();
			
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		
		public boolean ActiveCheckBox()
		{
			try {
				Thread.sleep(4000);
				utils.waitForEle(ActiveCheckBox, "visible", "", 10);
				ActiveCheckBox.isDisplayed();
				ActiveCheckBox.click();
				Thread.sleep(3000);
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		//10th TestCase
		
		public boolean AssignedEmpLinkCount()
		{
			try {
				Thread.sleep(4000);
				utils.waitForEle(AssignedEmpLinkCount, "visible", "", 10);
				AssignedEmpLinkCount.isDisplayed();
				AssignedEmpLinkCount.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AssignedEmpLinkCountSearchTextField(String EmpName) {
			try {

				Thread.sleep(4000);
				utils.waitForEle(AssignedEmpLinkCountSearchTextField,  "visible", "", 10);
				AssignedEmpLinkCountSearchTextField.isDisplayed();
				AssignedEmpLinkCountSearchTextField.clear();
				AssignedEmpLinkCountSearchTextField.sendKeys(EmpName);
				Thread.sleep(4000);
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AssignedEmpLinkCountFirstRecord()
		{
			try {
				Thread.sleep(6000);
				utils.waitForEle(AssignedEmpLinkCountFirstRecord, "visible", "", 10);
				AssignedEmpLinkCountFirstRecord.isDisplayed();
				Thread.sleep(2000);
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		
		public boolean TeamOptionSelected()
		{
			try {
				
				utils.waitForEle(TeamOptionSelected, "visible", "", 10);
				
				TeamOptionSelected.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
	
		
	
		public boolean SaturdayOption()
		{
			try {
				utils.waitForEle(SaturdayOption, "visible", "", 10);
				
				SaturdayOption.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean SundayOption()
		{
			try {
				utils.waitForEle(SundayOption, "visible", "", 10);
				
				SundayOption.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}

		

		public  void setExceptionDesc(String exceptionDesc) {  
			exceptionDesc = exceptionDesc;
		}
	
}
