package com.MeghPI.Attendance.pages;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Pramod;
import utils.Utils;
public class MeghPIAttendanceReportsPage {


	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
	
	
	
	
	public MeghPIAttendanceReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//input[@aria-controls='dtPunchReport']")
	private WebElement PunchReportSearchTextField; //1stTestCase
	
	@FindBy(xpath = "(//label[text()='Punch Time'])[1]")
	private WebElement PunchTimeCheckbox; //1stTestCase
	
	@FindBy(xpath = "(//label[text()='Punch Mode'])[1]")
	private WebElement PunchModeCheckbox; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpReportFilterCompanyLocation-container']")
	private WebElement LocationDropdown; //1stTestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpReportFilterCompanyLocation-results']")
	private WebElement LocationDropdownSearchTextField; //1stTestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpReportFilterCompanyLocation-result')])[1]")
	private WebElement LocationDropdownSearchResult; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[2]")
	private WebElement FirstRecordDateAndTime; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[3]/span")
	private WebElement PunchModeInFirstRecord; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement MailReportButton; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtEmailSubject']")
	private WebElement SubjectTextField; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtEmailCC']")
	private WebElement CCTextField; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtEmailBCC']")
	private WebElement BCCTextField; //2nd TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFrequency']")
	private WebElement FrequencySelection; //2nd TestCase
	
	@FindBy(xpath = "//button[@id='btnSaveScheduleReport']")
	private WebElement MailSendSaveButton; //2nd TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterDepartment']")
	private WebElement DepartmentDropdown; //3rd TestCase
	
	@FindBy(xpath = "(//button[@id='btnFilterPunchReport'])[2]")
	private WebElement FilterButton; //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterTeam']")
	private WebElement TeamDropDown; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btnFilterReport']")
	private WebElement FilterSaveButton; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement FirstRecordResult; //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterDesignation']")
	private WebElement DesignationDropDown; //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterEntityType']")
	private WebElement EntityTypeDropDown; //5th TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterYear']")
	private WebElement YearDropDown; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterMonth']")
	private WebElement MonthDropDown; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpReportFilterWeekly']")
	private WebElement WeeklyDropDown; //7th TestCase
	
	@FindBy(xpath = "//input[@id='drpReportFilterFromDate']/../input[2]")
	private WebElement FromDateDropDown; //7th TestCase
	
	@FindBy(xpath = "//input[@id='drpReportFilterToDate']/../input[2]")
	private WebElement ToDateDropDown; //7th TestCase
	
	@FindBy(xpath = "//input[@id='drpReportFilterFromTime']/../input[2]")
	private WebElement FromTimeTextField; //7th TestCase
	
	@FindBy(xpath = "//input[@id='drpReportFilterToTime']/../input[2]")
	private WebElement ToTimeTextField; //7th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-EmployeeIddtPunchReport']")
	private WebElement EmployeeIDCheckBox; //8th TestCase
	
	//inout report
	@FindBy(xpath = "//input[@id='chkdivDrpItemPunchTimedtInOutReport']")
	private WebElement PunchTimeCheckBoxInOut; //1st TestCase inout report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemModedtInOutReport']")
	private WebElement PunchModeInOut; //1st TestCase inout report
	
	@FindBy(xpath = "//input[@aria-controls='dtInOutReport']")
	private WebElement SearchTextFieldInOut; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[2]")
	private WebElement PunchTimeFirstRowinout; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[3]/span")
	private WebElement PunchModeFirstRowInout; //1st TestCase inout report
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement InoutReportMailButton; //2nd TestCase inout report
	
	@FindBy(xpath = "(//button[@id='btnFilterInOutReport'])[2]")
	private WebElement FilterButtonInOut; //3rd TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement FirstRecordResultInout; //3rd TestCase inout report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-EmployeeIddtInOutReport']")
	private WebElement EmpIDCheckboxInout; //12th TestCase inout report
	
	@FindBy(xpath = "//a[text()='Attendance Report']")
	private WebElement AttendanceReportButton; //13th TestCase inout report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemInTimedtAttendanceReport']")
	private WebElement PunchInTimeCheckboxAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemOutTimedtAttendanceReport']")
	private WebElement PunchOutTimeCheckboxAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemShift-NamedtAttendanceReport']")
	private WebElement ShiftCheckboxAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//input[@aria-controls= 'dtAttendanceReport']")
	private WebElement SearchTextFieldAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemAttnDatedtAttendanceReport']")
	private WebElement AttenDateCheckboxAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemAttnFinalStatus-StatusDescdtAttendanceReport']")
	private WebElement AttenStatusCheckboxAttendancerpt; //17th TestCase Attendance report
	
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[3]")
	private WebElement PunchinTimeRecordAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[4]")
	private WebElement PunchOutTimeRecordAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[5]")
	private WebElement ShiftRecordAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[8]")
	private WebElement AttendanceStatusAttendancerpt; //17th TestCase Attendance report
	
	@FindBy(xpath = "(//button[@id='btnFilterAttendanceReport'])[2]")
	private WebElement FilterButtonAttendancerpt; //18th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement FirstRecordAttendancerpt; //18th TestCase Attendance report
	
	@FindBy(xpath = "(//button[@id='btnRptColumnsAttendanceReport'])[2]")
	private WebElement EditColumnButton; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Shift In Time']")
	private WebElement ShiftInTimeCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Shift Out Time']")
	private WebElement ShiftOutTimeCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Department']")
	private WebElement DepartmentCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Designation']")
	private WebElement DesignationCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Role']")
	private WebElement RoleCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Team']")
	private WebElement TeamCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Working Hour']")
	private WebElement WorkingHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Effective Working Hour']")
	private WebElement EffectiveHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Early In Hour']")
	private WebElement EarlyInHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Early Out Hour']")
	private WebElement EarlyOutHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Late In Hour']")
	private WebElement LateInHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Late Out Hour']")
	private WebElement LateOutHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Over Time Hour']")
	private WebElement OTHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Lunch In Time 1']")
	private WebElement LunchInTimeCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Lunch Out Time 1']")
	private WebElement LunchOutTimeCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Device In']")
	private WebElement DeviceInCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Break Hour']")
	private WebElement BreakHourCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Leave Type']")
	private WebElement LeaveTypeCheckbox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//button[@id='btnRptColumnSave']")
	private WebElement SaveChangesButton; //19th TestCase Attendance report
	
	@FindBy(xpath = "//label[text()='Over Stay Hour']")
	private WebElement OverStayCheckBox; //19th TestCase Attendance report
	
	@FindBy(xpath = "//a[@id='MissingPunchReport_Tab']")
	private WebElement MissingPunchTab; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//input[@aria-controls='dtMissingPunchReport']")
	private WebElement MissingPunchSearchField; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemShift-NamedtMissingPunchReport']")
	private WebElement ShiftCheckBoxMissingPunchRpt; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemAttnFinalStatus-StatusDescdtMissingPunchReport']")
	private WebElement AttenFinalStatusCheckBoxMissingPunchrpt; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[5]")
	private WebElement ShiftRecordInMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[8]")
	private WebElement AttendanceFinalStatusRecordInMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "(//button[@id='btnReportEmailButton'])[2]")
	private WebElement MailButtonMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "(//button[@id='btnFilterMissingPunchReport'])[2]")
	private WebElement FilterButtonMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[1]/div/div/p")
	private WebElement FirstRecordMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[4]")
	private WebElement OutTimeRecordMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "(//button[@id='btnRptColumnsMissingPunchReport'])[2]")
	private WebElement EditColumnButtonMissingPunch; //01st TestCase MissingPunch report
	
	@FindBy(xpath = "//button[@id='btngrdCheckIn']")
	private WebElement CheckInButton; // TestCase MissingPunch report
	
	@FindBy(xpath = "//label[text()='In Punch From']")
	private WebElement InPunchFromCheckBox; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr/td[text()='No matching records found']")
	private WebElement NoRecordFoundMsg; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/thead/tr/th/div[text()='Device In']")
	private WebElement DeviceInScroll; // TestCase MissingPunch report
	
	@FindBy(xpath = "//span[@id='select2-drpReportFilterEntityType-container']")
	private WebElement EmpTypeDrpDownClick; // TestCase MissingPunch report
	
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/thead/tr/th/div[text()='Device In']")
	private WebElement MissingPunchDeviceInScroll; // TestCase MissingPunch report
	
	
	@FindBy(xpath = "//label[text()='Device Name']")
	private WebElement DeviceNameCheckBox; // TestCase MissingPunch report
	
	@FindBy(xpath = "//label[text()='Company Location']")
	private WebElement CompanyLocationNameCheckBox; // TestCase MissingPunch report
	
	@FindBy(xpath = "//label[text()='Entity Type']")
	private WebElement EntityTypeNameCheckBox; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[5]")
	private WebElement DeviceNameRow; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[6]")
	private WebElement CompanyLocationNameRow; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[7]")
	private WebElement EntityTypeNameRow; // TestCase MissingPunch report
	
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[5]")
	private WebElement DeviceNamrFirstRowInout; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[6]")
	private WebElement LocationNamrFirstRowInout; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[7]")
	private WebElement EntityTypeNamrFirstRowInout; //1st TestCase inout report
	
	@FindBy(xpath = "//label[text()='Attn Date']")
	private WebElement AttendanceDateCheckBox; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[2]")
	private WebElement AttendanceDateRow; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[3]")
	private WebElement MissPunchEmpAttendanceTime; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtMissingPunchReport']/tbody/tr[1]/td[2]")
	private WebElement MissPunchEmpAttendanceDate; //17th TestCase Attendance report
	
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[1]")
	private WebElement FirstRecordDateAndTimeOnEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[2]/span")
	private WebElement PunchModeInFirstRecordOnEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[4]")
	private WebElement DeviceNameRowOnEmp; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[5]")
	private WebElement CompanyLocationNameRowOnEmp; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[6]")
	private WebElement EntityTypeNameRowOnEmp; // TestCase MissingPunch report
	
	@FindBy(xpath = "//table[@id='dtPunchReport']/tbody/tr[1]/td[1]")
	private WebElement FirstRecordResultOnEmp; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[1]")
	private WebElement PunchTimeFirstRowinoutOnEmp; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[2]/span")
	private WebElement PunchModeFirstRowInoutOnEmp; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[4]")
	private WebElement DeviceNamrFirstRowInoutOnEmp; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[5]")
	private WebElement LocationNamrFirstRowInoutOnEmp; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtInOutReport']/tbody/tr[1]/td[6]")
	private WebElement EntityTypeNamrFirstRowInoutOnEmp; //1st TestCase inout report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[2]")
	private WebElement PunchinTimeRecordAttendancerptOnEmp; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[3]")
	private WebElement PunchOutTimeRecordAttendancerptOnEmp; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[4]")
	private WebElement ShiftRecordAttendancerptOnEmp; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[7]")
	private WebElement AttendanceStatusAttendancerptOnEmp; //17th TestCase Attendance report
	
	@FindBy(xpath = "//table[@id='dtAttendanceReport']/tbody/tr[1]/td[1]")
	private WebElement AttendanceDateRowOnEmp; //17th TestCase Attendance report
	
	public boolean AttendanceDateRowOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(AttendanceDateRowOnEmp, "visible", "", 15);

	        String actualText = AttendanceDateRowOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchinTimeRecordAttendancerptOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchinTimeRecordAttendancerptOnEmp, "visible", "", 15);

	        String actualText = PunchinTimeRecordAttendancerptOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchOutTimeRecordAttendancerptOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchOutTimeRecordAttendancerptOnEmp, "visible", "", 15);

	        String actualText = PunchOutTimeRecordAttendancerptOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ShiftRecordAttendancerptOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(ShiftRecordAttendancerptOnEmp, "visible", "", 15);

	        String actualText = ShiftRecordAttendancerptOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AttendanceStatusAttendancerptOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(AttendanceStatusAttendancerptOnEmp, "visible", "", 15);

	        String actualText = AttendanceStatusAttendancerptOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean DeviceNamrFirstRowInoutOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(DeviceNamrFirstRowInoutOnEmp, "visible", "", 15);

	        String actualText = DeviceNamrFirstRowInoutOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LocationNamrFirstRowInoutOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(LocationNamrFirstRowInoutOnEmp, "visible", "", 15);

	        String actualText = LocationNamrFirstRowInoutOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EntityTypeNamrFirstRowInoutOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(EntityTypeNamrFirstRowInoutOnEmp, "visible", "", 15);

	        String actualText = EntityTypeNamrFirstRowInoutOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	public boolean PunchTimeFirstRowinoutOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchTimeFirstRowinoutOnEmp, "visible", "", 15);

	        String actualText = PunchTimeFirstRowinoutOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchModeFirstRowInoutOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchModeFirstRowInoutOnEmp, "visible", "", 15);

	        String actualText = PunchModeFirstRowInoutOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	

	public boolean DeviceNameRowOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(DeviceNameRowOnEmp, "visible", "", 15);

	        String actualText = DeviceNameRowOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean CompanyLocationNameRowOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(CompanyLocationNameRowOnEmp, "visible", "", 15);

	        String actualText = CompanyLocationNameRowOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EntityTypeNameRowOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(EntityTypeNameRowOnEmp, "visible", "", 15);

	        String actualText = EntityTypeNameRowOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean FirstRecordDateAndTimeOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(FirstRecordDateAndTimeOnEmp, "visible", "", 15);

	        String actualText = FirstRecordDateAndTimeOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchModeInFirstRecordOnEmp(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchModeInFirstRecordOnEmp, "visible", "", 15);

	        String actualText = PunchModeInFirstRecordOnEmp.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean AttendanceDateRow(String status) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(AttendanceDateRow, "visible", "", 15);

	        String actualText = AttendanceDateRow.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(status)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + status + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	public boolean  AttendanceDateCheckBox()
	{
		try {
			utils.waitForEle(AttendanceDateCheckBox, "visible", "", 15);
			AttendanceDateCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	public boolean  PunchReportSearchTextField(String punchtime)
	{
		try {
			utils.waitForEle(PunchReportSearchTextField, "visible", "", 15);
			PunchReportSearchTextField.clear();
			PunchReportSearchTextField.sendKeys(punchtime);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  PunchTimeCheckbox()
	{
		try {
			utils.waitForEle(PunchTimeCheckbox, "visible", "", 15);
			PunchTimeCheckbox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  PunchModeCheckbox()
	{
		try {
			utils.waitForEle(PunchModeCheckbox, "visible", "", 15);
			PunchModeCheckbox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LocationDropdown() {
	    try {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	        // Wait for dropdown to be visible & clickable
	        WebElement dropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("//span[@id='select2-drpReportFilterCompanyLocation-container']")
	                )
	        );

	        // First click
	        dropdown.click();

	        // Check search field visibility, else retry click
	        try {
	            wait.until(ExpectedConditions.visibilityOf(LocationDropdownSearchTextField));
	        } catch (TimeoutException te) {
	            dropdown.click(); // retry click
	            wait.until(ExpectedConditions.visibilityOf(LocationDropdownSearchTextField));
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "LocationDropdown error: " + e.getMessage();
	        return false;
	    }
	}


	
	public boolean  LocationDropdownSearchTextField(String locationName)
	{
		try {
			utils.waitForEle(LocationDropdownSearchTextField, "visible", "", 15);
			LocationDropdownSearchTextField.clear();
			LocationDropdownSearchTextField.sendKeys(locationName);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LocationDropdownSearchResult()
	{
		try {
			utils.waitForEle(LocationDropdownSearchResult, "visible", "", 15);
			LocationDropdownSearchResult.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	public boolean FirstRecordDateAndTime(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(FirstRecordDateAndTime, "visible", "", 15);

	        String actualText = FirstRecordDateAndTime.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchModeInFirstRecord(String punchmode) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchModeInFirstRecord, "visible", "", 15);

	        String actualText = PunchModeInFirstRecord.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(punchmode)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + punchmode + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  MailReportButton()
	{
		try {
			utils.waitForEle(MailReportButton, "visible", "", 15);
			MailReportButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  SubjectTextField(String Subject)
	{
		try {
			utils.waitForEle(SubjectTextField, "visible", "", 15);
			SubjectTextField.clear();
			SubjectTextField.sendKeys(Subject);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  CCTextField(String CCMail)
	{
		try {
			utils.waitForEle(CCTextField, "visible", "", 15);
			CCTextField.clear();
			CCTextField.sendKeys(CCMail);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  BCCTextField(String BCCMail)
	{
		try {
			utils.waitForEle(BCCTextField, "visible", "", 15);
			BCCTextField.clear();
			BCCTextField.sendKeys(BCCMail);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FrequencySelection()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(FrequencySelection, "visible", "", 15);
			Select sel = new Select(FrequencySelection);
			sel.selectByIndex(1);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  MailSendSaveButton()
	{
		try {
			
			utils.waitForEle(MailSendSaveButton, "visible", "", 15);
			MailSendSaveButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean  DepartmentDropdown(String DeptName)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DepartmentDropdown, "visible", "", 15);
			Select sel = new Select(DepartmentDropdown);
			sel.selectByVisibleText(DeptName);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FilterButton()
	{
		try {
			
			utils.waitForEle(FilterButton, "visible", "", 15);
			FilterButton.click();
	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FilterSaveButton()
	{
		try {
			
			utils.waitForEle(FilterSaveButton, "visible", "", 15);
			FilterSaveButton.click();
	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  TeamDropDown(String TeamName)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(TeamDropDown, "visible", "", 15);
			Select sel = new Select(TeamDropDown);
			sel.selectByVisibleText(TeamName);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DesignationDropDown(String DesignationName)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DesignationDropDown, "visible", "", 15);
			Select sel = new Select(DesignationDropDown);
			sel.selectByVisibleText(DesignationName);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FirstRecordResultOnEmp()
	{
		try {
		
			utils.waitForEle(FirstRecordResultOnEmp, "visible", "", 15);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  FirstRecordResult()
	{
		try {
		
			utils.waitForEle(FirstRecordResult, "visible", "", 15);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EntityTypeDropDown(String EntityName)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EntityTypeDropDown, "visible", "", 15);
			Select sel = new Select(EntityTypeDropDown);
			sel.selectByVisibleText(EntityName);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	
	public boolean  YearDropDown(String Year)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(YearDropDown, "visible", "", 30);
			Select sel = new Select(YearDropDown);
			sel.selectByVisibleText(Year);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  MonthDropDown(String Month)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(MonthDropDown, "visible", "", 20);
			Select sel = new Select(MonthDropDown);
			sel.selectByVisibleText(Month);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	public boolean  WeeklyDropDown()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(WeeklyDropDown, "visible", "", 15);
			Select sel = new Select(WeeklyDropDown);
			sel.selectByIndex(1);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FromDateDropDown()
	{
		try {
			utils.waitForEle(FromDateDropDown, "visible", "", 10);
		
			FromDateDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean FromDateSelected(String FromDate) {
		 try {
			 Thread.sleep(2000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('drpReportFilterFromDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('drpReportFilterFromDate')._flatpickr) {" +
		            "  document.getElementById('drpReportFilterFromDate')._flatpickr.setDate('" + FromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on drpReportFilterFromDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean  ToDateDropDown()
	{
		try {
			utils.waitForEle(ToDateDropDown, "visible", "", 10);
		
			ToDateDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean ToDateSelected(String ToDate) {
		 try {
			 Thread.sleep(2000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('drpReportFilterToDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('drpReportFilterToDate')._flatpickr) {" +
		            "  document.getElementById('drpReportFilterToDate')._flatpickr.setDate('" + ToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on drpReportFilterToDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean  FromTimeTextField()
	{
		try {
			utils.waitForEle(FromTimeTextField, "visible", "", 10);
		
			FromTimeTextField.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean FromTimeSelected(String FromTime) {
		 try {
			 Thread.sleep(2000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('drpReportFilterFromTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('drpReportFilterFromTime')._flatpickr) {" +
		            "  document.getElementById('drpReportFilterFromTime')._flatpickr.setDate('" + FromTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on drpReportFilterFromTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean  ToTimeTextField()
	{
		try {
			utils.waitForEle(ToTimeTextField, "visible", "", 10);
		
			ToTimeTextField.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean ToTimeSelected(String ToTime) {
		 try {
			 Thread.sleep(2000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('drpReportFilterToTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('drpReportFilterToTime')._flatpickr) {" +
		            "  document.getElementById('drpReportFilterToTime')._flatpickr.setDate('" + ToTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on drpReportFilterToTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	public boolean  EmployeeIDCheckBox()
	{
		try {
			utils.waitForEle(EmployeeIDCheckBox, "visible", "", 10);
		
			EmployeeIDCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	

	public boolean validatePunchRow(String expectedEmpId, String excelDate, String expectedTime, String expectedMode) {
	    try {
	        // convert Excel date (yyyy-MM-dd) to UI format (dd-MM-yyyy)
	        LocalDate ld = LocalDate.parse(excelDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String expectedDate = ld.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // if caller passed without #, normalise by adding it
	        String normalisedExpectedEmpId = expectedEmpId.startsWith("#")
	                                         ? expectedEmpId.trim()
	                                         : "#" + expectedEmpId.trim();

	      
	        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            // get employee id from row
	            String empId = row.findElement(
	                    By.cssSelector("td div.user_details p.user_email_id"))
	                    .getText().trim();

	            if (empId.equalsIgnoreCase(normalisedExpectedEmpId)) {
	                // get the date/time cell
	                String dateTimeText = row.findElement(By.cssSelector("td:nth-of-type(2)")).getText().trim();
	                String[] parts = dateTimeText.split(" ");
	                String actualDate = parts[0].trim(); // "02-09-2025"
	                String actualTime = parts[1].trim(); // "19:05:20"

	                // get mode (IN/OUT)
	                String actualMode = row.findElement(By.cssSelector("td:nth-of-type(3) span"))
	                                       .getText().trim();

	                if (actualDate.equals(expectedDate) &&
	                    actualTime.equals(expectedTime) &&
	                    actualMode.equalsIgnoreCase(expectedMode)) {
	                    return true; // validation success
	                } else {
	                    exceptionDesc = "Mismatch: Expected Date=" + expectedDate +
	                                    ", Actual Date=" + actualDate +
	                                    ", Expected Time=" + expectedTime +
	                                    ", Actual Time=" + actualTime +
	                                    ", Expected Mode=" + expectedMode +
	                                    ", Actual Mode=" + actualMode;
	                    return false;
	                }
	            }
	        }

	        exceptionDesc = "Employee ID " + normalisedExpectedEmpId + " not found in table.";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
//inout
	public boolean  PunchTimeCheckBoxInOut()
	{
		try {
			utils.waitForEle(PunchTimeCheckBoxInOut, "visible", "", 10);
		
			PunchTimeCheckBoxInOut.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  PunchModeInOut()
	{
		try {
			utils.waitForEle(PunchModeInOut, "visible", "", 10);
		
			PunchModeInOut.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  SearchTextFieldInOut(String punchtime)
	{
		try {
			utils.waitForEle(SearchTextFieldInOut, "visible", "", 15);
			SearchTextFieldInOut.clear();
			SearchTextFieldInOut.sendKeys(punchtime);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean PunchTimeFirstRowinout(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchTimeFirstRowinout, "visible", "", 15);

	        String actualText = PunchTimeFirstRowinout.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchModeFirstRowInout(String punchmode) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchModeFirstRowInout, "visible", "", 15);

	        String actualText = PunchModeFirstRowInout.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(punchmode)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + punchmode + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  InoutReportMailButton()
	{
		try {
			utils.waitForEle(InoutReportMailButton, "visible", "", 15);
			InoutReportMailButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean  FilterButtonInOut()
	{
		try {
			utils.waitForEle(FilterButtonInOut, "visible", "", 15);
			FilterButtonInOut.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FirstRecordResultInout()
	{
		try {
		
			utils.waitForEle(FirstRecordResultInout, "visible", "", 15);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean validateInOutTimes(String empId,
            String inOutTime1, String mode1,
            String inOutTime2, String mode2,
            String inOutTime3, String mode3,
            String inOutTime4, String mode4) {
try {


// Get all rows
List<WebElement> rows = driver.findElements(By.cssSelector("#dtInOutReport tbody tr"));

// Collect all rows matching empId
List<WebElement> empRows = new ArrayList<>();
for (WebElement row : rows) {
String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();
if (rowEmpId.replace("#","").equalsIgnoreCase(empId)) {
    empRows.add(row);
}
}

// Build arrays from parameters
String[] expTimes = {inOutTime1, inOutTime2, inOutTime3, inOutTime4};
String[] expModes = {mode1, mode2, mode3, mode4};

// Loop through only the rows where expectedTime is not null/empty
for (int i = 0; i < expTimes.length; i++) {
if (expTimes[i] == null || expTimes[i].isEmpty()) continue; // skip empty parameters

if (i >= empRows.size()) {
System.out.println("Row missing for employee: expected row " + (i+1));
return false;
}

WebElement row = empRows.get(i);

// Extract date/time text
String actualDateTime = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
// Take only time part
String actualTime = actualDateTime.split(" ")[1].trim();

// Extract mode text
String actualMode = row.findElement(By.cssSelector("td:nth-child(3) span")).getText().trim();

// Compare
if (!actualTime.contains(expTimes[i]) || !actualMode.contains(expModes[i])) {
System.out.println("Mismatch at row " + (i+1) +
": Expected Time=" + expTimes[i] + ", Actual Time=" + actualTime +
", Expected Mode=" + expModes[i] + ", Actual Mode=" + actualMode);
return false;
}
}
} catch (Exception e) {
exceptionDesc = e.getMessage();
return false;
}
return true;
}

	public boolean  EmpIDCheckboxInout()
	{
		try {
			utils.waitForEle(EmpIDCheckboxInout, "visible", "", 10);
		
			EmpIDCheckboxInout.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	public boolean  AttendanceReportButton()
	{
		try {
			utils.waitForEle(AttendanceReportButton, "visible", "", 10);
		
			AttendanceReportButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
//Attendance Report
	public boolean  PunchInTimeCheckboxAttendancerpt()
	{
		try {
			utils.waitForEle(PunchInTimeCheckboxAttendancerpt, "visible", "", 10);
		
			PunchInTimeCheckboxAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  PunchOutTimeCheckboxAttendancerpt()
	{
		try {
			utils.waitForEle(PunchOutTimeCheckboxAttendancerpt, "visible", "", 10);
		
			PunchOutTimeCheckboxAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ShiftCheckboxAttendancerpt()
	{
		try {
			utils.waitForEle(ShiftCheckboxAttendancerpt, "visible", "", 10);
		
			ShiftCheckboxAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  SearchTextFieldAttendancerpt(String punchintime)
	{
		try {
			utils.waitForEle(SearchTextFieldAttendancerpt, "visible", "", 10);
		
			SearchTextFieldAttendancerpt.clear();
			SearchTextFieldAttendancerpt.sendKeys(punchintime);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AttenDateCheckboxAttendancerpt()
	{
		try {
			utils.waitForEle(AttenDateCheckboxAttendancerpt, "visible", "", 10);
		
			AttenDateCheckboxAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	public boolean  AttenStatusCheckboxAttendancerpt()
	{
		try {
			utils.waitForEle(AttenStatusCheckboxAttendancerpt, "visible", "", 10);
		
			AttenStatusCheckboxAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean PunchinTimeRecordAttendancerpt(String inpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchinTimeRecordAttendancerpt, "visible", "", 15);

	        String actualText = PunchinTimeRecordAttendancerpt.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(inpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + inpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean PunchOutTimeRecordAttendancerpt(String Outpunch) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(PunchOutTimeRecordAttendancerpt, "visible", "", 15);

	        String actualText = PunchOutTimeRecordAttendancerpt.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(Outpunch)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + Outpunch + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ShiftRecordAttendancerpt(String shift) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(ShiftRecordAttendancerpt, "visible", "", 15);

	        String actualText = ShiftRecordAttendancerpt.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + shift + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AttendanceStatusAttendancerpt(String status) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(AttendanceStatusAttendancerpt, "visible", "", 15);

	        String actualText = AttendanceStatusAttendancerpt.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(status)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + status + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean  FilterButtonAttendancerpt()
	{
		try {
			utils.waitForEle(FilterButtonAttendancerpt, "visible", "", 10);
		
			FilterButtonAttendancerpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FirstRecordAttendancerpt()
	{
		try {
		
			utils.waitForEle(FirstRecordAttendancerpt, "visible", "", 15);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//Edit column
	
	public boolean  EditColumnButton()
	{
		try {
		
			utils.waitForEle(EditColumnButton, "visible", "", 15);
			EditColumnButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  RoleCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(RoleCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, RoleCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  ShiftInTimeCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(ShiftInTimeCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, ShiftInTimeCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  ShiftOutTimeCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(ShiftOutTimeCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, ShiftOutTimeCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  DepartmentCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(DepartmentCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, DepartmentCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  DesignationCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(DesignationCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, DesignationCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  TeamCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(TeamCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, TeamCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  WorkingHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(WorkingHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, WorkingHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  EffectiveHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(EffectiveHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, EffectiveHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  EarlyInHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(EarlyInHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, EarlyInHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  EarlyOutHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(EarlyOutHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, EarlyOutHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LateInHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LateInHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, LateInHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LateOutHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LateOutHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, LateOutHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  OTHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(OTHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, OTHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LunchInTimeCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LunchInTimeCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, LunchInTimeCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LunchOutTimeCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LunchOutTimeCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, LunchOutTimeCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  DeviceInCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(DeviceInCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, DeviceInCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  BreakHourCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(BreakHourCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, BreakHourCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LeaveTypeCheckbox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LeaveTypeCheckbox, "visible", "", 15);
			return Utils.safeClick(driver, LeaveTypeCheckbox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	public boolean  OverStayCheckBox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(OverStayCheckBox, "visible", "", 15);
			return Utils.safeClick(driver, OverStayCheckBox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	
	
	public boolean  SaveChangesButton()
	{
		try {
		
			utils.waitForEle(SaveChangesButton, "visible", "", 15);
			SaveChangesButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean validateRoleColumn(String empId, String expectedRole) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Role")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Role column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(expectedRole)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Role=" + expectedRole + ", Actual Role=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateDesignationColumn(String empId, String expectedDesignation) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Designation")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Designation column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(expectedDesignation)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Designation=" + expectedDesignation + ", Actual Designation=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateDepartmentColumn(String empId, String expectedDepartment) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Department")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Department column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(expectedDepartment)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Department=" + expectedDepartment + ", Actual Department=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateTeamColumn(String empId, String expectedTeam) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Team")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Team column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(expectedTeam)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Team=" + expectedTeam + ", Actual Team=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateWorkingHourColumn(String empId, String WorkingHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Working Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Working Hour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(WorkingHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected WorkingHour=" + WorkingHour + ", Actual WorkingHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateEffectiveHourColumn(String empId, String EffectiveHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Effective Working Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("EffectiveHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(EffectiveHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected EffectiveHour=" + EffectiveHour + ", Actual EffectiveHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateEarlyInHourColumn(String empId, String EarlyInHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Early In Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println(" EarlyInHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(EarlyInHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected EarlyInHour=" + EarlyInHour + ", Actual EarlyInHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateEarlyOutHourColumn(String empId, String EarlyOutHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Early Out Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("EarlyOutHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(EarlyOutHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected EarlyOutHour=" + EarlyOutHour + ", Actual EarlyOutHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateLateInHourrColumn(String empId, String LateInHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Late In Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("LateInHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(LateInHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected LateInHour=" + LateInHour + ", Actual LateInHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateLateOutHourColumn(String empId, String LateOutHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Late Out Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("LateOutHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(LateOutHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected LateOutHour=" + LateOutHour + ", Actual LateOutHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validatelunchintimeColumn(String empId, String lunchintime) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Lunch In Time 1")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("lunchintime column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(lunchintime)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected lunchintime=" + lunchintime + ", Actual lunchintime=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validatelunchouttimeColumn(String empId, String lunchouttime) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Lunch Out Time 1")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("lunchouttime column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(lunchouttime)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected lunchouttime=" + lunchouttime + ", Actual lunchouttime=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateOverStayHourColumn(String empId, String OverStayHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Over Stay Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("OverStayHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(OverStayHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected OverStayHour=" + OverStayHour + ", Actual OverStayHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateOverTimeHourColumn(String empId, String OverTimeHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Over Time Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("OverTimeHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(OverTimeHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected OverTimeHour=" + OverTimeHour + ", Actual OverTimeHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateFinalStatusColumn(String empId, String FinalStatus) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Final Status")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("FinalStatus column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(FinalStatus)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected FinalStatus=" + FinalStatus + ", Actual FinalStatus=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateBreakHourColumn(String empId, String BreakHour) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Break Hour")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("BreakHour column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(BreakHour)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected BreakHour=" + BreakHour + ", Actual BreakHour=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean validateDeviceInColumn(String empId, String expectedDeviceIn) {
	    try {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.id("dtAttendanceReport")
	        ));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int deviceInColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Device In")) {
	                deviceInColIndex = i + 1;   // nth-child starts from 1
	                break;
	            }
	        }

	        if (deviceInColIndex == -1) {
	            exceptionDesc = "Device In column not found in Attendance Report table.";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in attendance report.";
	            return false;
	        }

	        // Normalize empId (# handling)
	        String cleanEmpId = empId.replace("#", "").trim();

	        // 3️⃣ Iterate through rows
	        for (WebElement row : rows) {

	            WebElement empIdElement = row.findElement(By.cssSelector(".user_email_id"));
	            String rowEmpId = empIdElement.getText().trim().replace("#", "");

	            if (rowEmpId.contains(cleanEmpId)) {

	                // Scroll row into view (horizontal + vertical)
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row);

	                // 4️⃣ Read the Device In column
	                WebElement deviceCell = row.findElement(By.cssSelector("td:nth-child(" + deviceInColIndex + ")"));

	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deviceCell);

	                String actualDeviceIn = deviceCell.getText().trim();

	                if (actualDeviceIn.contains(expectedDeviceIn)) {
	                    return true;
	                } else {
	                    exceptionDesc = "Device In Mismatch. Expected: " + expectedDeviceIn + ", Actual: " + actualDeviceIn;
	                    return false;
	                }
	            }
	        }

	        exceptionDesc = "Employee ID not found in table: " + empId;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Device In column: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateLeaveTypeColumn(String empId, String LeaveType) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Leave Type")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("LeaveType column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(LeaveType)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected LeaveType=" + LeaveType + ", Actual LeaveType=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateShiftColumn(String empId, String Shift) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Shift")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("Shift column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(Shift)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Shift=" + Shift + ", Actual Shift=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateShiftInTimeColumn(String empId, String ShiftInTime) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Shift In Time")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("ShiftInTime column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(ShiftInTime)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected ShiftInTime=" + ShiftInTime + ", Actual ShiftInTime=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateShiftOutTimeColumn(String empId, String ShiftOutTime) {
	    try {
	       

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Shift Out Time")) {
	                roleColIndex = i + 1; // +1 because nth-child starts at 1
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            System.out.println("ShiftOutTime column not found in the Attendance Report table");
	            return false;
	        }

	        // 2. Find the row with this employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // Compare ignoring or including # as needed
	            if (rowEmpId.replace("#","").equalsIgnoreCase(empId.replace("#",""))) {

	                // 3. Get the cell at the Role column for this row
	                WebElement roleCell = row.findElement(By.cssSelector("td:nth-child(" + roleColIndex + ")"));
	                String actualRole = roleCell.getText().trim();

	                if (actualRole.equalsIgnoreCase(ShiftOutTime)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected ShiftOutTime=" + ShiftOutTime + ", Actual ShiftOutTime=" + actualRole);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  MissingPunchTab()
	{
		try {
			utils.waitForEle(MissingPunchTab, "visible", "", 10);
		
			MissingPunchTab.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  MissingPunchSearchField(String shift)
	{
		try {
			utils.waitForEle(MissingPunchSearchField, "visible", "", 10);
		
			MissingPunchSearchField.clear();
			MissingPunchSearchField.sendKeys(shift);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ShiftCheckBoxMissingPunchRpt()
	{
		try {
			utils.waitForEle(ShiftCheckBoxMissingPunchRpt, "visible", "", 10);
		
			ShiftCheckBoxMissingPunchRpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AttenFinalStatusCheckBoxMissingPunchrpt()
	{
		try {
			utils.waitForEle(AttenFinalStatusCheckBoxMissingPunchrpt, "visible", "", 10);
		
			AttenFinalStatusCheckBoxMissingPunchrpt.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftRecordInMissingPunch(String shift) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(ShiftRecordInMissingPunch, "visible", "", 15);

	        String actualText = ShiftRecordInMissingPunch.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(shift)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + shift + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AttendanceFinalStatusRecordInMissingPunch(String status) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(AttendanceFinalStatusRecordInMissingPunch, "visible", "", 15);

	        String actualText = AttendanceFinalStatusRecordInMissingPunch.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(status)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + status + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean  MailButtonMissingPunch()
	{
		try {
			utils.waitForEle(MailButtonMissingPunch, "visible", "", 10);
		
			MailButtonMissingPunch.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FilterButtonMissingPunch()
	{
		try {
			utils.waitForEle(FilterButtonMissingPunch, "visible", "", 10);
		
			FilterButtonMissingPunch.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FirstRecordMissingPunch()
	{
		try {
		
			utils.waitForEle(FirstRecordMissingPunch, "visible", "", 15);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean validateOutTimeRowInMissingPunchReport(String empId, String OutTime) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Out Time")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("OutTime column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(OutTime)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected OutTime=" + OutTime +
	                            ", Actual OutTime=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	public boolean validateFinalStatusRowInMissingPunchReport(String empId, String FinalStatus) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Final Status")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("FinalStatus column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(FinalStatus)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected FinalStatus=" + FinalStatus +
	                            ", Actual FinalStatus=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  EditColumnButtonMissingPunch()
	{
		try {
		
			utils.waitForEle(EditColumnButtonMissingPunch, "visible", "", 15);
			EditColumnButtonMissingPunch.click();
			;
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean validateDeviceInRowInMissingPunchReport(String empId, String DeviceIn) {
	    try {
	        // locate the Missing Punch Report table
	    	Thread.sleep(3000);
	    	JavascriptExecutor jse = (JavascriptExecutor) driver;
	    	jse.executeScript("arguments[0].scrollIntoView(true);", MissingPunchDeviceInScroll);
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.contains("Device In")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Device In column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(DeviceIn)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected DeviceIn=" + DeviceIn +
	                            ", Actual DeviceIn=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateShiftRowInMissingPunchReport(String empId, String Shift) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Shift")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Shift  column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(Shift)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Shift=" + Shift +
	                            ", Actual Shift=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateAttnDateRowInMissingPunchReport(String empId, String attnDate) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int attnDateColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Attn Date")) {
	                attnDateColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (attnDateColIndex == -1) {
	            System.out.println("AttnDate column not found in the Missing Punch Report table");
	            return false;
	        }

	        // Convert passed date (yyyy-MM-dd) to dd-MM-yyyy
	        String expectedAttnDate;
	        try {
	            LocalDate date = LocalDate.parse(attnDate); // parses yyyy-MM-dd
	            expectedAttnDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        } catch (Exception ex) {
	            // If parse fails, fallback to the passed string
	            expectedAttnDate = attnDate;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Attn Date column
	                WebElement attnDateCell = row.findElement(By.cssSelector("td:nth-child(" + attnDateColIndex + ")"));
	                String actualAttnDate = attnDateCell.getText().trim();

	                if (actualAttnDate.equalsIgnoreCase(expectedAttnDate)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected AttnDate=" + expectedAttnDate +
	                            ", Actual AttnDate=" + actualAttnDate);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateDepartmentRowInMissingPunchReport(String empId, String Department) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Department")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Department column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(Department)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Department=" + Department +
	                            ", Actual Department=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateDesignationRowInMissingPunchReport(String empId, String Designation) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Designation")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Designation column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(Designation)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Designation=" + Designation +
	                            ", Actual Designation=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateRoleRowInMissingPunchReport(String empId, String Role) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Role")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Role column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(Role)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Role=" + Role +
	                            ", Actual Role=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean validateTeamRowInMissingPunchReport(String empId, String Team) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("Team")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Team column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(Team)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected Team=" + Team +
	                            ", Actual Team=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean CheckInButton()
	{
		try {
			utils.waitForEle(CheckInButton, "visible", "", 10);
			CheckInButton.click();
		Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}


	public boolean  InPunchFromCheckBox()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(InPunchFromCheckBox, "visible", "", 15);
			return Utils.safeClick(driver, InPunchFromCheckBox);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean validateInPunchFromRowInMissingPunchReport(String empId, String InPunchFrom) {
	    try {
	        // locate the Missing Punch Report table
	        WebElement table = driver.findElement(By.id("dtMissingPunchReport"));

	        // 1. Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase("In Punch From")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            System.out.println("Team column not found in the Missing Punch Report table");
	            return false;
	        }

	        // 2. Loop rows to find the given employee ID
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        for (WebElement row : rows) {
	            String rowEmpId = row.findElement(By.cssSelector(".user_email_id")).getText().trim();

	            // normalize both sides (handle # prefix)
	            if (rowEmpId.replace("#", "").equalsIgnoreCase(empId.replace("#", ""))) {

	                // 3. Get the cell at Final Status column
	                WebElement finalStatusCell = row.findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"));
	                String actualFinalStatus = finalStatusCell.getText().trim();

	                if (actualFinalStatus.equalsIgnoreCase(InPunchFrom)) {
	                    return true;
	                } else {
	                    System.out.println("Mismatch: Expected InPunchFrom=" + InPunchFrom +
	                            ", Actual InPunchFrom=" + actualFinalStatus);
	                    return false;
	                }
	            }
	        }

	        System.out.println("No row found for Employee ID: " + empId);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean NoRecordFoundMsg()
	{
		try {
			utils.waitForEle(NoRecordFoundMsg, "visible", "", 10);
			
		Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
		
	public boolean EmpTypeDrpDownClick()
	{
		try {
			utils.waitForEle(EmpTypeDrpDownClick, "visible", "", 20);
			EmpTypeDrpDownClick.click();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeviceNameRow(String devicename) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(DeviceNameRow, "visible", "", 15);

	        String actualText = DeviceNameRow.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(devicename)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + devicename + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean  DeviceNameCheckBox()
	{
		try {
			utils.waitForEle(DeviceNameCheckBox, "visible", "", 15);
			DeviceNameCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  CompanyLocationNameCheckBox()
	{
		try {
			utils.waitForEle(CompanyLocationNameCheckBox, "visible", "", 15);
			CompanyLocationNameCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EntityTypeNameCheckBox()
	{
		try {
			utils.waitForEle(EntityTypeNameCheckBox, "visible", "", 15);
			EntityTypeNameCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyLocationNameRow(String companylocationname) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(CompanyLocationNameRow, "visible", "", 15);

	        String actualText = CompanyLocationNameRow.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(companylocationname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + companylocationname + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EntityTypeNameRow(String entitytypename) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(EntityTypeNameRow, "visible", "", 15);

	        String actualText = EntityTypeNameRow.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(entitytypename)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + entitytypename + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public static int lastMonthWeekNumber = -1;  // store result globally

	public boolean getLastMonthWeekNumber(String dateKey) {
	    try {
	        Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

	        String dateStr = details.get(dateKey);
	        if (dateStr == null) {
	            exceptionDesc = "No date found for key: " + dateKey;
	            return false;
	        }

	        LocalDate date = LocalDate.parse(dateStr);

	        LocalDate today = LocalDate.now();
	        LocalDate lastMonth = today.minusMonths(1);

	        if (date.getMonthValue() != lastMonth.getMonthValue()) {
	            exceptionDesc = "Provided date does not belong to last month!";
	            return false;
	        }

	        // ⭐ Correct week of month
	        int weekNumber = date.get(WeekFields.of(Locale.ENGLISH).weekOfMonth());

	        // store result so your test can use it
	        lastMonthWeekNumber = weekNumber;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }

	    return true;
	}

	
	public boolean WeeklyDropDownSelected(String weekcount) {
	    try {
	        Thread.sleep(3000);
	        utils.waitForEle(WeeklyDropDown, "visible", "", 15);

	        Select sel = new Select(WeeklyDropDown);

	        // Convert string to int (e.g., "2", "3", etc.)
	        int week = Integer.parseInt(weekcount);

	        // Select week dropdown by value
	        sel.selectByValue(String.valueOf(week));

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	

	
	
	public boolean DeviceNamrFirstRowInout(String companylocationname) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(DeviceNamrFirstRowInout, "visible", "", 15);

	        String actualText = DeviceNamrFirstRowInout.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(companylocationname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + companylocationname + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean LocationNamrFirstRowInout(String companylocationname) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(LocationNamrFirstRowInout, "visible", "", 15);

	        String actualText = LocationNamrFirstRowInout.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(companylocationname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + companylocationname + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EntityTypeNamrFirstRowInout(String companylocationname) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(EntityTypeNamrFirstRowInout, "visible", "", 15);

	        String actualText = EntityTypeNamrFirstRowInout.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(companylocationname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + companylocationname + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean MissPunchEmpAttendanceTime(String status) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(MissPunchEmpAttendanceTime, "visible", "", 15);

	        String actualText = MissPunchEmpAttendanceTime.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(status)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + status + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	public boolean MissPunchEmpAttendanceDate(String status) {
	    try {
	        // Wait until element visible
	        utils.waitForEle(MissPunchEmpAttendanceDate, "visible", "", 15);

	        String actualText = MissPunchEmpAttendanceDate.getText().trim();
	        // Check whether actual text contains the expected input punch
	        if (actualText.contains(status)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected text to contain '" + status + "' but was '" + actualText + "'";
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public static int previous12WorkingDateWeekNumber = -1;
	public boolean getPrevious12WorkingDateWeekNumber(String dateKey) {
	    try {
	        Map<String, String> details = Pramod.getPrevious12WorkingDates();

	        String dateStr = details.get(dateKey);
	        if (dateStr == null) {
	            exceptionDesc = "No date found for key: " + dateKey;
	            return false;
	        }

	        LocalDate date = LocalDate.parse(dateStr);

	        // Ensure date is before today
	        if (!date.isBefore(LocalDate.now())) {
	            exceptionDesc = "Provided date must be a past working day. Found: " + date;
	            return false;
	        }

	        // Week of month using ISO week rules
	        int weekNumber = date.get(WeekFields.of(Locale.ENGLISH).weekOfMonth());

	        // Store globally for use in test class
	        previous12WorkingDateWeekNumber = weekNumber;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	
	public boolean validateRoleColumnOnEmp(String expectedRole) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Role column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int roleColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Role")) {
	                roleColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (roleColIndex == -1) {
	            exceptionDesc = "Role column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Role value for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            WebElement roleCell = row.findElement(
	                    By.cssSelector("td:nth-child(" + roleColIndex + ")"));

	            String actualRole = roleCell.getText().trim();

	            if (!actualRole.equalsIgnoreCase(expectedRole)) {
	                exceptionDesc = "Role mismatch. Expected: " + expectedRole +
	                        ", Found: " + actualRole;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateDesignationColumnOnEmp(String expectedDesignation) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Designation column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int designationColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Designation")) {
	                designationColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (designationColIndex == -1) {
	            exceptionDesc = "Designation column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate designation for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualDesignation = row
	                    .findElement(By.cssSelector("td:nth-child(" + designationColIndex + ")"))
	                    .getText().trim();

	            if (!actualDesignation.equalsIgnoreCase(expectedDesignation)) {
	                exceptionDesc = "Designation mismatch. Expected: " + expectedDesignation +
	                        ", Found: " + actualDesignation;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateDepartmentColumnOnEmp(String expectedDepartment) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Department column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int departmentColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Department")) {
	                departmentColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (departmentColIndex == -1) {
	            exceptionDesc = "Department column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Department value for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualDepartment = row
	                    .findElement(By.cssSelector("td:nth-child(" + departmentColIndex + ")"))
	                    .getText().trim();

	            if (!actualDepartment.equalsIgnoreCase(expectedDepartment)) {
	                exceptionDesc = "Department mismatch. Expected: " + expectedDepartment +
	                        ", Found: " + actualDepartment;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateTeamColumnOnEmp(String expectedTeam) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Team column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int teamColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Team")) {
	                teamColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (teamColIndex == -1) {
	            exceptionDesc = "Team column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Team value for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualTeam = row
	                    .findElement(By.cssSelector("td:nth-child(" + teamColIndex + ")"))
	                    .getText().trim();

	            if (!actualTeam.equalsIgnoreCase(expectedTeam)) {
	                exceptionDesc = "Team mismatch. Expected: " + expectedTeam +
	                        ", Found: " + actualTeam;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateEffectiveHourColumnOnEmp(String expectedEffectiveHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Effective Working Hour column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int effectiveHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim()
	                    .equalsIgnoreCase("Effective Working Hour")) {
	                effectiveHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (effectiveHourColIndex == -1) {
	            exceptionDesc = "Effective Working Hour column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Effective Working Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualEffectiveHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + effectiveHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualEffectiveHour.equalsIgnoreCase(expectedEffectiveHour)) {
	                exceptionDesc = "Effective Hour mismatch. Expected: "
	                        + expectedEffectiveHour + ", Found: " + actualEffectiveHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateWorkingHourColumnOnEmp(String expectedWorkingHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Working Hour column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int workingHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Working Hour")) {
	                workingHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (workingHourColIndex == -1) {
	            exceptionDesc = "Working Hour column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Working Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualWorkingHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + workingHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualWorkingHour.equalsIgnoreCase(expectedWorkingHour)) {
	                exceptionDesc = "Working Hour mismatch. Expected: "
	                        + expectedWorkingHour + ", Found: " + actualWorkingHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateEarlyInHourColumnOnEmp(String expectedEarlyInHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Find Early In Hour column index dynamically
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int earlyInHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Early In Hour")) {
	                earlyInHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (earlyInHourColIndex == -1) {
	            exceptionDesc = "Early In Hour column not found in table";
	            return false;
	        }

	        // 2️⃣ Validate Early In Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualEarlyInHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + earlyInHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualEarlyInHour.equalsIgnoreCase(expectedEarlyInHour)) {
	                exceptionDesc = "Early In Hour mismatch. Expected: "
	                        + expectedEarlyInHour + ", Found: " + actualEarlyInHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateEarlyOutHourColumnOnEmp(String expectedEarlyOutHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Early Out Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int earlyOutHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Early Out Hour")) {
	                earlyOutHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (earlyOutHourColIndex == -1) {
	            exceptionDesc = "Early Out Hour column not found in table";
	            return false;
	        }

	        // Validate Early Out Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualEarlyOutHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + earlyOutHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualEarlyOutHour.equalsIgnoreCase(expectedEarlyOutHour)) {
	                exceptionDesc = "Early Out Hour mismatch. Expected: "
	                        + expectedEarlyOutHour + ", Found: " + actualEarlyOutHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateLateInHourColumnOnEmp(String expectedLateInHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Late In Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int lateInHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Late In Hour")) {
	                lateInHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (lateInHourColIndex == -1) {
	            exceptionDesc = "Late In Hour column not found in table";
	            return false;
	        }

	        // Validate Late In Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualLateInHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + lateInHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualLateInHour.equalsIgnoreCase(expectedLateInHour)) {
	                exceptionDesc = "Late In Hour mismatch. Expected: "
	                        + expectedLateInHour + ", Found: " + actualLateInHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateLateOutHourColumnOnEmp(String expectedLateOutHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Late Out Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int lateOutHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Late Out Hour")) {
	                lateOutHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (lateOutHourColIndex == -1) {
	            exceptionDesc = "Late Out Hour column not found in table";
	            return false;
	        }

	        // Validate Late Out Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualLateOutHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + lateOutHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualLateOutHour.equalsIgnoreCase(expectedLateOutHour)) {
	                exceptionDesc = "Late Out Hour mismatch. Expected: "
	                        + expectedLateOutHour + ", Found: " + actualLateOutHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateLunchInTimeColumnOnEmp(String expectedLunchInTime) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Lunch In Time 1 column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int lunchInTimeColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Lunch In Time 1")) {
	                lunchInTimeColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (lunchInTimeColIndex == -1) {
	            exceptionDesc = "Lunch In Time column not found in table";
	            return false;
	        }

	        // Validate Lunch In Time for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualLunchInTime = row
	                    .findElement(By.cssSelector("td:nth-child(" + lunchInTimeColIndex + ")"))
	                    .getText().trim();

	            if (!actualLunchInTime.equalsIgnoreCase(expectedLunchInTime)) {
	                exceptionDesc = "Lunch In Time mismatch. Expected: "
	                        + expectedLunchInTime + ", Found: " + actualLunchInTime;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateLunchOutTimeColumnOnEmp(String expectedLunchOutTime) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Lunch Out Time 1 column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int lunchOutTimeColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Lunch Out Time 1")) {
	                lunchOutTimeColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (lunchOutTimeColIndex == -1) {
	            exceptionDesc = "Lunch Out Time column not found in table";
	            return false;
	        }

	        // Validate Lunch Out Time for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualLunchOutTime = row
	                    .findElement(By.cssSelector("td:nth-child(" + lunchOutTimeColIndex + ")"))
	                    .getText().trim();

	            if (!actualLunchOutTime.equalsIgnoreCase(expectedLunchOutTime)) {
	                exceptionDesc = "Lunch Out Time mismatch. Expected: "
	                        + expectedLunchOutTime + ", Found: " + actualLunchOutTime;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateOverStayHourColumnOnEmp(String expectedOverStayHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Over Stay Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int overStayHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Over Stay Hour")) {
	                overStayHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (overStayHourColIndex == -1) {
	            exceptionDesc = "Over Stay Hour column not found in Attendance Report table";
	            return false;
	        }

	        // Validate Over Stay Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualOverStayHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + overStayHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualOverStayHour.equalsIgnoreCase(expectedOverStayHour)) {
	                exceptionDesc = "Over Stay Hour mismatch. Expected: "
	                        + expectedOverStayHour + ", Found: " + actualOverStayHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateOverTimeHourColumnOnEmp(String expectedOverTimeHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Over Time Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int overTimeHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Over Time Hour")) {
	                overTimeHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (overTimeHourColIndex == -1) {
	            exceptionDesc = "Over Time Hour column not found in Attendance Report table";
	            return false;
	        }

	        // Validate Over Time Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualOverTimeHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + overTimeHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualOverTimeHour.equalsIgnoreCase(expectedOverTimeHour)) {
	                exceptionDesc = "Over Time Hour mismatch. Expected: "
	                        + expectedOverTimeHour + ", Found: " + actualOverTimeHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateFinalStatusColumnOnEmp(String expectedFinalStatus) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Final Status column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int finalStatusColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Final Status")) {
	                finalStatusColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (finalStatusColIndex == -1) {
	            exceptionDesc = "Final Status column not found in Attendance Report table";
	            return false;
	        }

	        // Validate Final Status for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualFinalStatus = row
	                    .findElement(By.cssSelector("td:nth-child(" + finalStatusColIndex + ")"))
	                    .getText().trim();

	            if (!actualFinalStatus.equalsIgnoreCase(expectedFinalStatus)) {
	                exceptionDesc = "Final Status mismatch. Expected: "
	                        + expectedFinalStatus + ", Found: " + actualFinalStatus;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean validateBreakHourColumnOnEmp(String expectedBreakHour) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // Find Break Hour column index
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int breakHourColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Break Hour")) {
	                breakHourColIndex = i + 1; // nth-child is 1-based
	                break;
	            }
	        }

	        if (breakHourColIndex == -1) {
	            exceptionDesc = "Break Hour column not found in Attendance Report table";
	            return false;
	        }

	        // Validate Break Hour for all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

	        for (WebElement row : rows) {
	            String actualBreakHour = row
	                    .findElement(By.cssSelector("td:nth-child(" + breakHourColIndex + ")"))
	                    .getText().trim();

	            if (!actualBreakHour.equalsIgnoreCase(expectedBreakHour)) {
	                exceptionDesc = "Break Hour mismatch. Expected: "
	                        + expectedBreakHour + ", Found: " + actualBreakHour;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean validateDeviceInColumnOnEmp(String expectedDeviceIn) {
	    try {

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.id("dtAttendanceReport")
	        ));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int deviceInColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Device In")) {
	                deviceInColIndex = i + 1; // nth-child starts at 1
	                break;
	            }
	        }

	        if (deviceInColIndex == -1) {
	            exceptionDesc = "Device In column not found in Attendance Report table.";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in attendance report.";
	            return false;
	        }

	        // 3️⃣ Iterate through rows and validate Device In
	        for (WebElement row : rows) {
	            WebElement deviceCell = row.findElement(By.cssSelector("td:nth-child(" + deviceInColIndex + ")"));
	            String actualDeviceIn = deviceCell.getText().trim();

	            if (!actualDeviceIn.equalsIgnoreCase(expectedDeviceIn)) {
	                exceptionDesc = "Device In mismatch. Expected: " + expectedDeviceIn + ", Actual: " + actualDeviceIn;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Device In column: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateLeaveTypeColumnOnEmp(String expectedLeaveType) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int leaveTypeColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Leave Type")) {
	                leaveTypeColIndex = i + 1; // nth-child starts at 1
	                break;
	            }
	        }

	        if (leaveTypeColIndex == -1) {
	            exceptionDesc = "Leave Type column not found in the Attendance Report table";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in Attendance Report table";
	            return false;
	        }

	        // 3️⃣ Validate Leave Type for all rows
	        for (WebElement row : rows) {
	            WebElement leaveCell = row.findElement(By.cssSelector("td:nth-child(" + leaveTypeColIndex + ")"));
	            String actualLeaveType = leaveCell.getText().trim();

	            if (!actualLeaveType.equalsIgnoreCase(expectedLeaveType)) {
	                exceptionDesc = "Leave Type mismatch. Expected: " + expectedLeaveType + ", Found: " + actualLeaveType;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Leave Type column: " + e.getMessage();
	        return false;
	    }
	}

	
	public boolean validateShiftColumnOnEmp(String expectedShift) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int shiftColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Shift")) {
	                shiftColIndex = i + 1; // nth-child starts at 1
	                break;
	            }
	        }

	        if (shiftColIndex == -1) {
	            exceptionDesc = "Shift column not found in the Attendance Report table";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in Attendance Report table";
	            return false;
	        }

	        // 3️⃣ Validate Shift for all rows
	        for (WebElement row : rows) {
	            WebElement shiftCell = row.findElement(By.cssSelector("td:nth-child(" + shiftColIndex + ")"));
	            String actualShift = shiftCell.getText().trim();

	            if (!actualShift.equalsIgnoreCase(expectedShift)) {
	                exceptionDesc = "Shift mismatch. Expected: " + expectedShift + ", Found: " + actualShift;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Shift column: " + e.getMessage();
	        return false;
	    }
	}

	
	
	
	public boolean validateShiftOutTimeColumnOnEmp(String expectedShiftOutTime) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int shiftOutColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Shift Out Time")) {
	                shiftOutColIndex = i + 1; // nth-child starts at 1
	                break;
	            }
	        }

	        if (shiftOutColIndex == -1) {
	            exceptionDesc = "Shift Out Time column not found in the Attendance Report table";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in Attendance Report table";
	            return false;
	        }

	        // 3️⃣ Validate Shift Out Time for all rows
	        for (WebElement row : rows) {
	            WebElement shiftOutCell = row.findElement(By.cssSelector("td:nth-child(" + shiftOutColIndex + ")"));
	            String actualShiftOutTime = shiftOutCell.getText().trim();

	            if (!actualShiftOutTime.equalsIgnoreCase(expectedShiftOutTime)) {
	                exceptionDesc = "Shift Out Time mismatch. Expected: " + expectedShiftOutTime + ", Found: " + actualShiftOutTime;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Shift Out Time column: " + e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean validateShiftInTimeColumnOnEmp(String expectedShiftInTime) {
	    try {

	        WebElement table = driver.findElement(By.id("dtAttendanceReport"));

	        // 1️⃣ Get all headers
	        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
	        int shiftInColIndex = -1;

	        for (int i = 0; i < headers.size(); i++) {
	            if (headers.get(i).getText().trim().equalsIgnoreCase("Shift In Time")) {
	                shiftInColIndex = i + 1; // nth-child starts at 1
	                break;
	            }
	        }

	        if (shiftInColIndex == -1) {
	            exceptionDesc = "Shift In Time column not found in the Attendance Report table";
	            return false;
	        }

	        // 2️⃣ Get all rows
	        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
	        if (rows.isEmpty()) {
	            exceptionDesc = "No rows found in Attendance Report table";
	            return false;
	        }

	        // 3️⃣ Validate Shift In Time for all rows
	        for (WebElement row : rows) {
	            WebElement shiftInCell = row.findElement(By.cssSelector("td:nth-child(" + shiftInColIndex + ")"));
	            String actualShiftInTime = shiftInCell.getText().trim();

	            if (!actualShiftInTime.equalsIgnoreCase(expectedShiftInTime)) {
	                exceptionDesc = "Shift In Time mismatch. Expected: " + expectedShiftInTime + ", Found: " + actualShiftInTime;
	                return false;
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while validating Shift In Time column: " + e.getMessage();
	        return false;
	    }
	}

	
	
	
	
	
	
	
	

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	
}
