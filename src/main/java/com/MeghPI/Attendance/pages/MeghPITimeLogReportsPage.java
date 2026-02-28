package com.MeghPI.Attendance.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghPITimeLogReportsPage {

	

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
	
	
	
	
	public MeghPITimeLogReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Time Log - Early and Late In/Out']")
	private WebElement TimeLogReportButton; //1stTestCase

	@FindBy(xpath = "//a[@id='PunchStatisticsReport_Tab']")
	private WebElement PunchStatisticsTab; //1stTestCase

	@FindBy(xpath = "//input[@aria-controls='dtPunchStatisticsReport']")
	private WebElement PunchStatisticsSearchField; //1stTestCase

	@FindBy(xpath = "//input[@id='chkdivDrpItemEmployeeIddtPunchStatisticsReport']")
	private WebElement EmpIDCheckBoxStatistics; //1stTestCase

	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIDRecordInResult; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement EmailButtonPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "(//button[@id='btnFilterPunchStatisticsReport'])[2]")
	private WebElement FilterButtonPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement FirstRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[4]")
	private WebElement EarlyInRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[5]")
	private WebElement EarlyOutRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[2]")
	private WebElement LateInRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[3]")
	private WebElement LateOutRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchStatisticsReport']/tbody/tr[1]/td[6]")
	private WebElement OTCountRecordPunchStatistics; //2ndTestCase
	
	@FindBy(xpath = "//a[@id='LateInReport_Tab']")
	private WebElement LateInTab; //1st TestCase Late IN
	
	@FindBy(xpath = "//input[@aria-controls='dtLateInReport']")
	private WebElement LateInTabSearchField; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='Date']")
	private WebElement LateInDateCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='In Time']")
	private WebElement LateInTabInTimeCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='Out Time']")
	private WebElement LateInTabOutTimeCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='Total Hours']")
	private WebElement LateInTabTotalHourCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='Late In Hours']")
	private WebElement LateInTabLateInHoursCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//label[text()='Shift']")
	private WebElement LateInTabShiftCheckBox; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[2]")
	private WebElement LateInTabDateSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[3]")
	private WebElement LateInTabInTimeSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[5]")
	private WebElement LateInTabTotalHoursSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[6]")
	private WebElement LateInTabLateInHoursSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[7]")
	private WebElement LateInTabShiftSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement LateInTabMailButton; //1st TestCase Late IN
	
	@FindBy(xpath = "(//button[@id='btnFilterLateInReport'])[2]")
	private WebElement LateInTabFilterButton; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[9]")
	private WebElement LateInTabTeamSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[11]")
	private WebElement LateInTabDesignationSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]")
	private WebElement LateInTabSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[2]")
	private WebElement LateInTabAttenDateSearchResult; //1st TestCase Late IN
	
	@FindBy(xpath = "//a[@id='LateOutReport_Tab']")
	private WebElement LateOutTab; //1st TestCase Late IN
	
	@FindBy(xpath = "//input[@aria-controls='dtLateOutReport']")
	private WebElement LateOutTabSearchTextfield; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[2]")
	private WebElement LateOutTabDateSearchResult; //1st TestCase Late out
	

	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[4]")
	private WebElement LateOutTabOutTimeSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//label[text()='Late Out Hours']")
	private WebElement LateOutTabLatehoursCheckbox; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[5]")
	private WebElement LateOutTabTotalhoursSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[6]")
	private WebElement LateOutTabLateOuthoursSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[7]")
	private WebElement LateOutTabShiftSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement LateOutTabEmailButton; //1st TestCase Late out
	
	@FindBy(xpath = "(//button[@id='btnFilterLateOutReport'])[2]")
	private WebElement LateOutTabFilterButton; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[9]")
	private WebElement LateOutTabTeamSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[11]")
	private WebElement LateOutTabDesignationSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]")
	private WebElement LateOutTabSearchResult; //1st TestCase Late out
	
	@FindBy(xpath = "//a[@id='EarlyInReport_Tab']")
	private WebElement EarlyInTab; //1st TestCase Early In
	
	@FindBy(xpath = "//input[@aria-controls='dtEarlyInReport']")
	private WebElement EarlyInTabSearchTextField; //1st TestCase Early In
	
	@FindBy(xpath = "//label[text()='Early In Hours']")
	private WebElement EarlyInTabEarlyInHoursCheckBox; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[2]")
	private WebElement EarlyInTabDateSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[3]")
	private WebElement EarlyInTabInTImeSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[5]")
	private WebElement EarlyInTabTotalHoursSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[6]")
	private WebElement EarlyInTabEarlyInHoursSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[7]")
	private WebElement EarlyInTabShiftSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement EarlyInTabEmailButton; //1st TestCase Early In
	
	@FindBy(xpath = "(//button[@id='btnFilterEarlyInReport'])[2]")
	private WebElement EarlyInTabFilterButton; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[9]")
	private WebElement EarlyInTabTeamSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[11]")
	private WebElement EarlyInTabDesignationSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]")
	private WebElement EarlyInTabSearchResult; //1st TestCase Early In
	
	@FindBy(xpath = "//a[@id='EarlyOutReport_Tab']")
	private WebElement EarlyOutTab; //1st TestCase Early Out
	
	@FindBy(xpath = "//input[@aria-controls='dtEarlyOutReport']")
	private WebElement EarlyOutTabSearchTextField; //1st TestCase Early Out
	
	@FindBy(xpath = "//label[text()='Early Out Hours']")
	private WebElement EarlyOutTabEarlyOutHoursCheckBox; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[2]")
	private WebElement EarlyOutTabDateSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[4]")
	private WebElement EarlyOutTabOutTimeSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[5]")
	private WebElement EarlyOutTabTotalHoursSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[6]")
	private WebElement EarlyOutTabEarlyOutHoursSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[7]")
	private WebElement EarlyOutTabShiftSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement EarlyOutTabEmailButton; //1st TestCase Early Out
	
	@FindBy(xpath = "(//button[@id='btnFilterEarlyOutReport'])[2]")
	private WebElement EarlyOutTabFilterButton; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[9]")
	private WebElement EarlyOutTabTeamSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[11]")
	private WebElement EarlyOutTabDesignationSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]")
	private WebElement EarlyOutTabSearchResult; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[1]")
	private WebElement LateInTabDateSearchResultOnEmp; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[2]")
	private WebElement LateInTabInTimeSearchResultOnEmp; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[4]")
	private WebElement LateInTabTotalHoursSearchResultOnEmp; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[5]")
	private WebElement LateInTabLateInHoursSearchResultOnEmp; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateInReport']/tbody/tr[1]/td[6]")
	private WebElement LateInTabShiftSearchResultOnEmp; //1st TestCase Late IN
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[1]")
	private WebElement LateOutTabDateSearchResultOnEmp; //1st TestCase Late out
	

	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[3]")
	private WebElement LateOutTabOutTimeSearchResultOnEmp; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[4]")
	private WebElement LateOutTabTotalhoursSearchResultOnEmp; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[5]")
	private WebElement LateOutTabLateOuthoursSearchResultOnEmp; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr[1]/td[6]")
	private WebElement LateOutTabShiftSearchResultOnEmp; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[1]")
	private WebElement EarlyInTabDateSearchResultOnEmp; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[2]")
	private WebElement EarlyInTabInTImeSearchResultOnEmp; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[4]")
	private WebElement EarlyInTabTotalHoursSearchResultOnEmp; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[5]")
	private WebElement EarlyInTabEarlyInHoursSearchResultOnEmp; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr[1]/td[6]")
	private WebElement EarlyInTabShiftSearchResultOnEmp; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[1]")
	private WebElement EarlyOutTabDateSearchResultOnEmp; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[3]")
	private WebElement EarlyOutTabOutTimeSearchResultOnEmp; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[4]")
	private WebElement EarlyOutTabTotalHoursSearchResultOnEmp; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[5]")
	private WebElement EarlyOutTabEarlyOutHoursSearchResultOnEmp; //1st TestCase Early Out
	
	@FindBy(xpath = "//table[@id='dtEarlyOutReport']/tbody/tr[1]/td[6]")
	private WebElement EarlyOutTabShiftSearchResultOnEmp; //1st TestCase Early Out
	
	
	@FindBy(xpath = "//table[@id='dtLateOutReport']/tbody/tr/td[6]")
	private List<WebElement> LateOutTabLateOuthoursSearchResultList; //1st TestCase Late out
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr/td[5]")
	private List<WebElement> EarlyInTabTotalHoursSearchResultList; //1st TestCase Early In
	
	@FindBy(xpath = "//table[@id='dtEarlyInReport']/tbody/tr/td[3]")
	private List<WebElement> EarlyInTabInTImeSearchResultList; //1st TestCase Early In
	
	public boolean  TimeLogReportButton()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(TimeLogReportButton, "visible", "", 15);
			TimeLogReportButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  PunchStatisticsTab()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(PunchStatisticsTab, "visible", "", 15);
			PunchStatisticsTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  PunchStatisticsSearchField(String empid)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(PunchStatisticsSearchField, "visible", "", 15);
			PunchStatisticsSearchField.clear();
			PunchStatisticsSearchField.sendKeys(empid);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EmpIDCheckBoxStatistics()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EmpIDCheckBoxStatistics, "visible", "", 15);
			EmpIDCheckBoxStatistics.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpIDRecordInResult(String empid) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EmpIDRecordInResult, "visible", "", 15);

	        // Get the actual text
	        String actualText = EmpIDRecordInResult.getText().trim();

	        // Normalize both sides (handle # prefix differences)
	        String expected = empid.replace("#", "");
	        String actual = actualText.replace("#", "");

	        if (actual.contains(expected)) {
	            return true;
	        } else {
	            System.out.println("Employee ID mismatch: Expected contains=" + empid + ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean  EmailButtonPunchStatistics()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EmailButtonPunchStatistics, "visible", "", 15);
			EmailButtonPunchStatistics.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  FilterButtonPunchStatistics()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(FilterButtonPunchStatistics, "visible", "", 15);
			FilterButtonPunchStatistics.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  FirstRecordPunchStatistics()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(FirstRecordPunchStatistics, "visible", "", 15);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyInRecordPunchStatistics(String earlyincount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EarlyInRecordPunchStatistics, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInRecordPunchStatistics.getText().trim();

	        // Compare the value
	        if (actualText.contains(earlyincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early In Count=" + earlyincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}

	public boolean EarlyOutRecordPunchStatistics(String earlyoutcount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(EarlyOutRecordPunchStatistics, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutRecordPunchStatistics.getText().trim();

	        // Compare the value
	        if (actualText.contains(earlyoutcount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early OUt Count=" + earlyoutcount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateInRecordPunchStatistics(String lateincount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(LateInRecordPunchStatistics, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInRecordPunchStatistics.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected latein Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateOutRecordPunchStatistics(String lateoutcount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(LateOutRecordPunchStatistics, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutRecordPunchStatistics.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateoutcount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late Out Count=" + lateoutcount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean OTCountRecordPunchStatistics(String OTcount) {
	    try {
	        Thread.sleep(4000);
	        utils.waitForEle(OTCountRecordPunchStatistics, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = OTCountRecordPunchStatistics.getText().trim();

	        // Compare the value
	        if (actualText.contains(OTcount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected OTcount Count=" + OTcount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	//late in 
	public boolean  LateInTab()
	{
		try {
		
			utils.waitForEle(LateInTab, "visible", "", 15);
			LateInTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInDateCheckBox()
	{
		try {
		
			utils.waitForEle(LateInDateCheckBox, "visible", "", 15);
			LateInDateCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabInTimeCheckBox()
	{
		try {
		
			utils.waitForEle(LateInTabInTimeCheckBox, "visible", "", 15);
			LateInTabInTimeCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabOutTimeCheckBox()
	{
		try {
		
			utils.waitForEle(LateInTabOutTimeCheckBox, "visible", "", 15);
			LateInTabOutTimeCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabTotalHourCheckBox()
	{
		try {
		
			utils.waitForEle(LateInTabTotalHourCheckBox, "visible", "", 15);
			LateInTabTotalHourCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabLateInHoursCheckBox()
	{
		try {
		
			utils.waitForEle(LateInTabLateInHoursCheckBox, "visible", "", 15);
			LateInTabLateInHoursCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabShiftCheckBox()
	{
		try {
		
			utils.waitForEle(LateInTabShiftCheckBox, "visible", "", 15);
			LateInTabShiftCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateInTabSearchField(String searchinput)
	{
		try {
		
			utils.waitForEle(LateInTabSearchField, "visible", "", 15);
			LateInTabSearchField.clear();
			LateInTabSearchField.sendKeys(searchinput);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateInTabDateSearchResult(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(LateInTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = LateInTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean LateInTabInTimeSearchResult(String lateincount) {
	    try {
	       
	        utils.waitForEle(LateInTabInTimeSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabInTimeSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean LateInTabTotalHoursSearchResult(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(LateInTabTotalHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabTotalHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateInTabLateInHoursSearchResult(String LateInhourscount) {
	    try {
	       
	        utils.waitForEle(LateInTabLateInHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabLateInHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(LateInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Hours Count=" + LateInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateInTabShiftSearchResult(String shift) {
	    try {
	       
	        utils.waitForEle(LateInTabShiftSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabShiftSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	public boolean  LateInTabMailButton()
	{
		try {
		
			utils.waitForEle(LateInTabMailButton, "visible", "", 15);
			LateInTabMailButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  LateInTabFilterButton()
	{
		try {
		
			utils.waitForEle(LateInTabFilterButton, "visible", "", 15);
			LateInTabFilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LateInTabTeamSearchResult(String team) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(LateInTabTeamSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = LateInTabTeamSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(team)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected team contains '" + team +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean LateInTabDesignationSearchResult(String designation) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(LateInTabDesignationSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = LateInTabDesignationSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(designation)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected designation contains '" + designation +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	public boolean  LateInTabSearchResult()
	{
		try {
		
			utils.waitForEle(LateInTabSearchResult, "visible", "", 15);
			LateInTabSearchResult.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateInTabAttenDateSearchResult(String expectedMonthName, String expectedYear) {
	    try {
	        // wait for the element
	        utils.waitForEle(LateInTabAttenDateSearchResult, "visible", "", 15);

	        // get date string from element e.g. "02-09-2025"
	        String dateText = LateInTabAttenDateSearchResult.getText().trim();

	        // parse the dd-MM-yyyy string
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate attnDate = LocalDate.parse(dateText, inputFormat);

	        // extract month name (full) and year
	        String actualMonthName = attnDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // "September"
	        String actualYear = String.valueOf(attnDate.getYear()); // "2025"

	        // compare ignoring case for month
	        if (actualMonthName.equalsIgnoreCase(expectedMonthName) && actualYear.equals(expectedYear)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Month=" + expectedMonthName +
	                    ", Actual Month=" + actualMonthName +
	                    ", Expected Year=" + expectedYear +
	                    ", Actual Year=" + actualYear);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  LateOutTab()
	{
		try {
		
			utils.waitForEle(LateOutTab, "visible", "", 15);
			LateOutTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  LateOutTabSearchTextfield(String Empid)
	{
		try {
		
			utils.waitForEle(LateOutTabSearchTextfield, "visible", "", 15);
			LateOutTabSearchTextfield.clear();
			LateOutTabSearchTextfield.sendKeys(Empid);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateOutTabDateSearchResult(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(LateOutTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = LateOutTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LateOutTabOutTimeSearchResult(String lateincount) {
	    try {
	       
	        utils.waitForEle(LateOutTabOutTimeSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabOutTimeSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean  LateOutTabLatehoursCheckbox()
	{
		try {
		
			utils.waitForEle(LateOutTabLatehoursCheckbox, "visible", "", 15);
			LateOutTabLatehoursCheckbox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateOutTabTotalhoursSearchResult(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(LateOutTabTotalhoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabTotalhoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateOutTabLateOuthoursSearchResult(String expectedLateOutHoursCount) {
	    try {

	        utils.waitForEle(LateOutTabLateOuthoursSearchResult, "visible", "", 15);

	       

	        for (WebElement actualEle : LateOutTabLateOuthoursSearchResultList) {

	            String actualText = actualEle.getText().trim();

	            // PASS if any one row matches
	            if (actualText.contains(expectedLateOutHoursCount)) {
	                return true;
	            }
	        }

	        // FAIL only if no row matched
	        exceptionDesc = "Expected Late Out Hours Count '" + expectedLateOutHoursCount +
	                        "' not found in any result row";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Error in LateOutTabLateOuthoursSearchResult: " + e.getMessage();
	        return false;
	    }
	}



	
	public boolean LateOutTabShiftSearchResult(String shift) {
	    try {
	       
	        utils.waitForEle(LateOutTabShiftSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabShiftSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean  LateOutTabEmailButton()
	{
		try {
		
			utils.waitForEle(LateOutTabEmailButton, "visible", "", 15);
			LateOutTabEmailButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LateOutTabFilterButton()
	{
		try {
		
			utils.waitForEle(LateOutTabFilterButton, "visible", "", 15);
			LateOutTabFilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateOutTabTeamSearchResult(String team) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(LateOutTabTeamSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = LateOutTabTeamSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(team)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected team contains '" + team +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LateOutTabDesignationSearchResult(String designation) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(LateOutTabDesignationSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = LateOutTabDesignationSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(designation)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected designation contains '" + designation +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  LateOutTabSearchResult()
	{
		try {
		
			utils.waitForEle(LateOutTabSearchResult, "visible", "", 15);
			LateOutTabSearchResult.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateOutTabAttenDateSearchResult(String expectedMonthName, String expectedYear) {
	    try {
	        // wait for the element
	        utils.waitForEle(LateOutTabDateSearchResult, "visible", "", 15);

	        // get date string from element e.g. "02-09-2025"
	        String dateText = LateOutTabDateSearchResult.getText().trim();

	        // parse the dd-MM-yyyy string
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate attnDate = LocalDate.parse(dateText, inputFormat);

	        // extract month name (full) and year
	        String actualMonthName = attnDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // "September"
	        String actualYear = String.valueOf(attnDate.getYear()); // "2025"

	        // compare ignoring case for month
	        if (actualMonthName.equalsIgnoreCase(expectedMonthName) && actualYear.equals(expectedYear)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Month=" + expectedMonthName +
	                    ", Actual Month=" + actualMonthName +
	                    ", Expected Year=" + expectedYear +
	                    ", Actual Year=" + actualYear);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LateOutTabDateSearchResultValidation(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(LateOutTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = LateOutTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LateOutTabLateOuthoursSearchResultValidation(String LateInhourscount) {
	    try {
	       
	        utils.waitForEle(LateOutTabLateOuthoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabLateOuthoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(LateInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Hours Count=" + LateInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean  EarlyInTab()
	{
		try {
		
			utils.waitForEle(EarlyInTab, "visible", "", 15);
			EarlyInTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EarlyInTabSearchTextField(String date)
	{
		try {
		
			utils.waitForEle(EarlyInTabSearchTextField, "visible", "", 15);
			EarlyInTabSearchTextField.clear();
			EarlyInTabSearchTextField.sendKeys(date);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EarlyInTabEarlyInHoursCheckBox()
	{
		try {
		
			utils.waitForEle(EarlyInTabEarlyInHoursCheckBox, "visible", "", 15);
			EarlyInTabEarlyInHoursCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyInTabDateSearchResult(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyInTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyInTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyInTabInTImeSearchResult(String expectedLateInCount) {
	    try {

	        utils.waitForEle(EarlyInTabInTImeSearchResult, "visible", "", 15);

	       

	        for (WebElement actualEle : EarlyInTabInTImeSearchResultList) {

	            String actualText = actualEle.getText().trim();

	            // PASS if any one row matches
	            if (actualText.contains(expectedLateInCount)) {
	                return true;
	            }
	        }

	        // FAIL only if no row matched
	        exceptionDesc = "Expected Late In Count '" + expectedLateInCount +
	                        "' not found in Early In tab In Time results";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Error in EarlyInTabInTImeSearchResult: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean EarlyInTabTotalHoursSearchResult(String expectedTotalHoursCount) {
	    try {

	        utils.waitForEle(EarlyInTabTotalHoursSearchResult, "visible", "", 15);

	       

	        for (WebElement actualEle : EarlyInTabTotalHoursSearchResultList) {

	            String actualText = actualEle.getText().trim();

	            // PASS if any one row matches
	            if (actualText.contains(expectedTotalHoursCount)) {
	                return true;
	            }
	        }

	        // FAIL only if no row matched
	        exceptionDesc = "Expected Total Hours Count '" + expectedTotalHoursCount +
	                        "' not found in Early In tab results";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Error in EarlyInTabTotalHoursSearchResult: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean EarlyInTabEarlyInHoursSearchResult(String EarlyInhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyInTabEarlyInHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabEarlyInHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early In Hours Count=" + EarlyInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyInTabShiftSearchResult(String shift) {
	    try {
	       
	        utils.waitForEle(EarlyInTabShiftSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabShiftSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean  EarlyInTabEmailButton()
	{
		try {
		
			utils.waitForEle(EarlyInTabEmailButton, "visible", "", 15);
			EarlyInTabEmailButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  EarlyInTabFilterButton()
	{
		try {
		
			utils.waitForEle(EarlyInTabFilterButton, "visible", "", 15);
			EarlyInTabFilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyInTabTeamSearchResult(String team) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(EarlyInTabTeamSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = EarlyInTabTeamSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(team)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected team contains '" + team +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyInTabDesignationSearchResult(String designation) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(EarlyInTabDesignationSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = EarlyInTabDesignationSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(designation)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected designation contains '" + designation +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  EarlyInTabSearchResult()
	{
		try {
		
			utils.waitForEle(EarlyInTabSearchResult, "visible", "", 15);
			EarlyInTabSearchResult.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyInTabAttenDateSearchResult(String expectedMonthName, String expectedYear) {
	    try {
	        // wait for the element
	        utils.waitForEle(EarlyInTabDateSearchResult, "visible", "", 15);

	        // get date string from element e.g. "02-09-2025"
	        String dateText = EarlyInTabDateSearchResult.getText().trim();

	        // parse the dd-MM-yyyy string
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate attnDate = LocalDate.parse(dateText, inputFormat);

	        // extract month name (full) and year
	        String actualMonthName = attnDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // "September"
	        String actualYear = String.valueOf(attnDate.getYear()); // "2025"

	        // compare ignoring case for month
	        if (actualMonthName.equalsIgnoreCase(expectedMonthName) && actualYear.equals(expectedYear)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Month=" + expectedMonthName +
	                    ", Actual Month=" + actualMonthName +
	                    ", Expected Year=" + expectedYear +
	                    ", Actual Year=" + actualYear);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyInTabDateSearchResultValidation(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyInTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyInTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyInTabEarlyInhoursSearchResultValidation(String LateInhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyInTabEarlyInHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabEarlyInHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(LateInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Hours Count=" + LateInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	//Early Out
	public boolean  EarlyOutTab()
	{
		try {
		
			utils.waitForEle(EarlyOutTab, "visible", "", 15);
			EarlyOutTab.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EarlyOutTabSearchTextField(String date)
	{
		try {
		
			utils.waitForEle(EarlyOutTabSearchTextField, "visible", "", 15);
			EarlyOutTabSearchTextField.clear();
			EarlyOutTabSearchTextField.sendKeys(date);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EarlyOutTabEarlyOutHoursCheckBox()
	{
		try {
		
			utils.waitForEle(EarlyOutTabEarlyOutHoursCheckBox, "visible", "", 15);
			EarlyOutTabEarlyOutHoursCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyOutTabDateSearchResult(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyOutTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyOutTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean EarlyOutTabOutTimeSearchResult(String EarlyOutcount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabOutTimeSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabOutTimeSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyOutcount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early Out Count=" + EarlyOutcount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabTotalHoursSearchResult(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabTotalHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabTotalHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}	
	
	public boolean EarlyOutTabEarlyOutHoursSearchResult(String EarlyOuthourscount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabEarlyOutHoursSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabEarlyOutHoursSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyOuthourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early Out Hours Count=" + EarlyOuthourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabShiftSearchResult(String shift) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabShiftSearchResult, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabShiftSearchResult.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean  EarlyOutTabEmailButton()
	{
		try {
		
			utils.waitForEle(EarlyOutTabEmailButton, "visible", "", 15);
			EarlyOutTabEmailButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  EarlyOutTabFilterButton()
	{
		try {
		
			utils.waitForEle(EarlyOutTabFilterButton, "visible", "", 15);
			EarlyOutTabFilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EarlyOutTabTeamSearchResult(String team) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(EarlyOutTabTeamSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = EarlyOutTabTeamSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(team)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected team contains '" + team +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabDesignationSearchResult(String designation) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(EarlyOutTabDesignationSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = EarlyOutTabDesignationSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(designation)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected designation contains '" + designation +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  EarlyOutTabSearchResult()
	{
		try {
		
			utils.waitForEle(EarlyOutTabSearchResult, "visible", "", 15);
			EarlyOutTabSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateOutTabAttenDateSearchResultValidation(String expectedMonthName, String expectedYear) {
	    try {
	        // wait for the element
	        utils.waitForEle(EarlyOutTabDateSearchResult, "visible", "", 15);

	        // get date string from element e.g. "02-09-2025"
	        String dateText = EarlyOutTabDateSearchResult.getText().trim();

	        // parse the dd-MM-yyyy string
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate attnDate = LocalDate.parse(dateText, inputFormat);

	        // extract month name (full) and year
	        String actualMonthName = attnDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // "September"
	        String actualYear = String.valueOf(attnDate.getYear()); // "2025"

	        // compare ignoring case for month
	        if (actualMonthName.equalsIgnoreCase(expectedMonthName) && actualYear.equals(expectedYear)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Month=" + expectedMonthName +
	                    ", Actual Month=" + actualMonthName +
	                    ", Expected Year=" + expectedYear +
	                    ", Actual Year=" + actualYear);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabDateSearchResultFilterValidation(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyOutTabDateSearchResult, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyOutTabDateSearchResult.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean LateInTabDateSearchResultOnEmp(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(LateInTabDateSearchResultOnEmp, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = LateInTabDateSearchResultOnEmp.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean LateInTabInTimeSearchResultOnEmp(String lateincount) {
	    try {
	       
	        utils.waitForEle(LateInTabInTimeSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabInTimeSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean LateInTabTotalHoursSearchResultOnEmp(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(LateInTabTotalHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabTotalHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateInTabLateInHoursSearchResultOnEmp(String LateInhourscount) {
	    try {
	       
	        utils.waitForEle(LateInTabLateInHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabLateInHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(LateInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Hours Count=" + LateInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateInTabShiftSearchResultOnEmp(String shift) {
	    try {
	       
	        utils.waitForEle(LateInTabShiftSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateInTabShiftSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateOutTabDateSearchResultOnEmp(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(LateOutTabDateSearchResultOnEmp, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = LateOutTabDateSearchResultOnEmp.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LateOutTabOutTimeSearchResultOnEmp(String lateincount) {
	    try {
	       
	        utils.waitForEle(LateOutTabOutTimeSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabOutTimeSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}

	
	public boolean LateOutTabTotalhoursSearchResultOnEmp(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(LateOutTabTotalhoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabTotalhoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateOutTabLateOuthoursSearchResultOnEmp(String LateOuthourscount) {
	    try {
	       
	        utils.waitForEle(LateOutTabLateOuthoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabLateOuthoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(LateOuthourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late Out Hours Count=" + LateOuthourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateOutTabShiftSearchResultOnEmp(String shift) {
	    try {
	       
	        utils.waitForEle(LateOutTabShiftSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = LateOutTabShiftSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean EarlyInTabDateSearchResultOnEmp(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyInTabDateSearchResultOnEmp, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyInTabDateSearchResultOnEmp.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EarlyInTabInTImeSearchResultOnEmp(String lateincount) {
	    try {
	       
	        utils.waitForEle(EarlyInTabInTImeSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabInTImeSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(lateincount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Late In Count=" + lateincount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyInTabTotalHoursSearchResultOnEmp(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyInTabTotalHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabTotalHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyInTabEarlyInHoursSearchResultOnEmp(String EarlyInhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyInTabEarlyInHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabEarlyInHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyInhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early In Hours Count=" + EarlyInhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyInTabShiftSearchResultOnEmp(String shift) {
	    try {
	       
	        utils.waitForEle(EarlyInTabShiftSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyInTabShiftSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabDateSearchResultOnEmp(String date) {
	    try {
	        // Wait for element to be visible
	        utils.waitForEle(EarlyOutTabDateSearchResultOnEmp, "visible", "", 15);

	        // UI shows dd-MM-yyyy but test passes yyyy-MM-dd
	        DateTimeFormatter expectedFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Convert expected to UI format
	        LocalDate parsedDate = LocalDate.parse(date, expectedFmt);
	        String expectedUiDate = parsedDate.format(uiFmt);  // e.g. "10-09-2025"

	        // Get actual text from UI
	        String actualText = EarlyOutTabDateSearchResultOnEmp.getText().trim();

	        // Compare
	        if (actualText.contains(expectedUiDate)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected date=" + expectedUiDate + ", Actual text=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean EarlyOutTabOutTimeSearchResultOnEmp(String EarlyOutcount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabOutTimeSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabOutTimeSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyOutcount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early Out Count=" + EarlyOutcount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabTotalHoursSearchResultOnEmp(String Totalhourscount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabTotalHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabTotalHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(Totalhourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Total Hours Count=" + Totalhourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}	
	
	public boolean EarlyOutTabEarlyOutHoursSearchResultOnEmp(String EarlyOuthourscount) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabEarlyOutHoursSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabEarlyOutHoursSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(EarlyOuthourscount)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Early Out Hours Count=" + EarlyOuthourscount + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EarlyOutTabShiftSearchResultOnEmp(String shift) {
	    try {
	       
	        utils.waitForEle(EarlyOutTabShiftSearchResultOnEmp, "visible", "", 15);

	        // Get the actual text of the element
	        String actualText = EarlyOutTabShiftSearchResultOnEmp.getText().trim();

	        // Compare the value
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Shift =" + shift + 
	                               ", Actual=" + actualText);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}}
