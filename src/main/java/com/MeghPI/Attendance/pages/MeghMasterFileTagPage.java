package com.MeghPI.Attendance.pages;


import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghMasterFileTagPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String createdtag = "";
	
	
	public MeghMasterFileTagPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(xpath = "//button[@id='fileTag_tab']")
	private WebElement FileTagButton;  //1st TestCase	
	
	@FindBy(xpath = "(//button[@data-bs-target='#fileTagCreateModal'])[1]")
	private WebElement CreateTagButton;  //1st TestCase
	
	@FindBy(xpath = "(//input[@id='txtFileTagName'])[1]")
	private WebElement TagNameTextField;  //1st TestCase
	
	@FindBy(xpath = "//input[@id='fileTagTypeEmpFiles']")
	private WebElement EmployeeFilesRadioButton;  //1st TestCase
	
	@FindBy(xpath = "//div[@class='tag_box box_color_green']")
	private WebElement ColorPicker;  //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnFileTagSave']")
	private WebElement AddTagSaveButton;  //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddEmployeeFiles'])[1]")
	private WebElement UploadFileButton;  //1st TestCase
	
	@FindBy(xpath = "//input[@id='fileInput']")
	private WebElement UploadFileFromStorage;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpFileTag1-container']")
	private WebElement FileTagDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpFileTag1-results']")
	private WebElement FileTagSearchDropDown;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpFileTag1-result')])[1]")
	private WebElement FileTagSearchResult;  //1st TestCase
	
	@FindBy(xpath = "//input[@id='fileTagTypeCompanyFiles']")
	private WebElement CompanyFileRadioButton;  //2nd TestCase
	
	@FindBy(xpath = "//p[text()='Available In: Company Files']/../p[1]")
	private List<WebElement> CompanyFiles;  //2nd TestCase
	
	@FindBy(xpath = "(//button[@data-bs-target='#fileTagCreateModal'])[5]")
	private WebElement FileTagEditIcon4thRecord;  //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpSectionType']")
	private WebElement SelectSectionTypeDropDown;  //4th TestCase
	
	@FindBy(xpath = "//p[text()='Available In: Company Files']/../p[2]")
	private List<WebElement> OnlyCompanyFiles;  //4th TestCase
	
	@FindBy(xpath = "//select[@id='drpFileTag1']")
	private WebElement FileTagSelectDropDown;  //5th TestCase
	
	
	@FindBy(xpath = "//button[@id='btnEmployeeFilesSave']")
	private WebElement UploadFilesSaveButton;  //5th TestCase
	
	@FindBy(xpath = "//button[@id='move-file-action']")
	private WebElement MoveFileDropDown;  //5th TestCase
	
	@FindBy(xpath = "//select[@id='drpFileTagMoveFrom']")
	private WebElement FromDropDown;  //5th TestCase
	
	@FindBy(xpath = "//select[@id='drpFileTagMoveTo']")
	private WebElement ToDropDown;  //5th TestCase
	
	@FindBy(xpath = "//button[@id='btnMoveFile']")
	private WebElement MoveSaveButton;  //5th TestCase
	
	@FindBy(xpath = "(//p[@class='file-box-title'])[1]/../p[3]")
	private WebElement MovedValidation;  //5th TestCase
	
	@FindBy(xpath = "//input[@id='fileValidUntil1']/../input[2]")
	private WebElement Date;  //5th TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddEmployeeFiles'])[2]")
	private WebElement UploadButton2ndRow;  //5th TestCase
	
	@FindBy(xpath = "//select[@id='drpFileTag2']")
	private WebElement Select2ndFileTag;  //5th TestCase
	
	@FindBy(xpath = "//input[@id='fileValidUntil2']/../input[2]")
	private WebElement Date2;  //5th TestCase
	
	@FindBy(xpath = "//div[@id='fileTagDiv']/div[1]")
	private WebElement FileTagPageLoaded;  //5th TestCase
	
	@FindBy(xpath = "(//select[@aria-label='Month'])[14]")
	private WebElement MonthSelection;  //5th TestCase
	
	@FindBy(xpath = "//span[@aria-label='December 1, 2025']")
	private WebElement DaySelection;  //5th TestCase
	
	@FindBy(xpath = "//input[@id='fileValidUntil1']/../input[2]")
	private WebElement DatePicker;  //5th TestCase
	
	@FindBy(xpath = "//select[contains(@class,'flatpickr-monthDropdown-months')]/../../../../../div[25]/div[1]/div/div/select")
	private WebElement monthDropdown;  //5th TestCase
	
	// (//select[contains(@class,'flatpickr-monthDropdown-months')])[15]
	
	//1st TestCase
	public boolean  FileTagButton()
	{
		try {
			utils.waitForEle(FileTagButton, "visible", "", 10);
			FileTagButton.isDisplayed();
			
			FileTagButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean  CreateTagButton()
	{
		try {
			utils.waitForEle(CreateTagButton, "visible", "", 10);
			CreateTagButton.isDisplayed();
			
			CreateTagButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean TagNameTextField(String tagname) {
		try {

			utils.waitForEle(TagNameTextField,  "visible", "", 10);
			TagNameTextField.isDisplayed();
			TagNameTextField.clear();
			TagNameTextField.sendKeys(tagname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  EmployeeFilesRadioButton()
	{
		try {
			utils.waitForEle(EmployeeFilesRadioButton, "visible", "", 10);
			EmployeeFilesRadioButton.isDisplayed();
			EmployeeFilesRadioButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  ColorPicker()
	{
		try {
			utils.waitForEle(ColorPicker, "visible", "", 10);
			ColorPicker.isDisplayed();
			ColorPicker.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddTagSaveButton()
	{
		try {
			utils.waitForEle(AddTagSaveButton, "visible", "", 10);
			AddTagSaveButton.isDisplayed();
			AddTagSaveButton.click();
			Thread.sleep(7000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  UploadFileButton()
	{
		try {

			utils.waitForEle(UploadFileButton, "visible", "", 30);
			UploadFileButton.isDisplayed();
			UploadFileButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean UploadFileFromStorage(String Fileupload) {
	    try {
	    	Thread.sleep(2000);
	        // Only set file detector if it's a real RemoteWebDriver instance
	        if (driver instanceof RemoteWebDriver &&
	            !(driver.getClass().getName().contains("ChromeDriver") || driver.getClass().getName().contains("ChromiumDriver"))) {
	            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	        }

	        File file = new File(Fileupload);
	        String canonicalPath = file.getCanonicalPath();

	        utils.waitForEle(UploadFileFromStorage, "visible", Fileupload);
	        UploadFileFromStorage.sendKeys(canonicalPath);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean  FileTagDropdown()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(FileTagDropdown, "visible", "", 10);
			FileTagDropdown.isDisplayed();
			FileTagDropdown.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean FileTagSearchDropDown(String tagname) {
		try {

			utils.waitForEle(FileTagSearchDropDown,  "visible", "", 10);
			FileTagSearchDropDown.isDisplayed();
			FileTagSearchDropDown.sendKeys(tagname);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  FileTagSearchResult()
	{
		try {
			utils.waitForEle(FileTagSearchResult, "visible", "", 10);
			FileTagSearchResult.isDisplayed();
		createdtag =	FileTagSearchResult.getText();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	
	public boolean  CompanyFileRadioButton()
	{
		try {
			utils.waitForEle(CompanyFileRadioButton, "visible", "", 10);
			CompanyFileRadioButton.isDisplayed();
			CompanyFileRadioButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyFiless(String tagname) {
	    try {
	    	Thread.sleep(3000);
	        boolean found = false;

	        for (WebElement cmpfiletag : CompanyFiles) {
	            if (cmpfiletag.getText().trim().equalsIgnoreCase(tagname)) {
	            	 System.out.println(tagname + "tagname is");
	                found = true;
	                break;
	               
	            }
	        }

	        if (!found) {
	            exceptionDesc = "Tag '" + tagname + "' not found in Company Files.";
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false; // Return false only if an exception occurs
	    }

	    return true; // Always return true if no exception, as per your request
	}

	
	
	//3rd TestCase
	
	public boolean  FileTagEditIcon4thRecord()
	{
		try {
			utils.waitForEle(FileTagEditIcon4thRecord, "visible", "", 10);
			FileTagEditIcon4thRecord.isDisplayed();
			FileTagEditIcon4thRecord.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean SelectSectionTypeDropDown(String tagtype) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(SelectSectionTypeDropDown,  "visible", "", 10);
			SelectSectionTypeDropDown.isDisplayed();
	Select type = new Select(SelectSectionTypeDropDown);
	type.selectByVisibleText(tagtype);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OnlyCompanyFiles() {
	    try {
	    	Thread.sleep(3000);
	        for (WebElement eachCmpFile : OnlyCompanyFiles) {
	        	System.out.println("all are" + eachCmpFile.getText());
	        	
	            if (!eachCmpFile.getText().trim().equalsIgnoreCase("Available In: Company Files")) {
	            	
	            	
	            	
	                exceptionDesc = "Unexpected value found: " + eachCmpFile.getText();
	                return false;
	            }
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	//5th TestCase
	public boolean FileTagSelectDropDown(String fromtagname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(FileTagSelectDropDown,  "visible", "", 10);
			FileTagSelectDropDown.isDisplayed();
	Select type = new Select(FileTagSelectDropDown);
	type.selectByVisibleText(fromtagname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  UploadFilesSaveButton()
	{
		try {
			utils.waitForEle(UploadFilesSaveButton, "visible", "", 10);
			
			UploadFilesSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	

	public boolean  MoveFileDropDown()
	{
		try {
			utils.waitForEle(MoveFileDropDown, "visible", "", 10);
			MoveFileDropDown.isDisplayed();
			MoveFileDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDropDown(String fromtagname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(FromDropDown,  "visible", "", 10);
			FromDropDown.isDisplayed();
	Select type = new Select(FromDropDown);
	type.selectByVisibleText(fromtagname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDropDown(String totagname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(ToDropDown,  "visible", "", 10);
			ToDropDown.isDisplayed();
	Select type = new Select(ToDropDown);
	type.selectByVisibleText(totagname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  MoveSaveButton()
	{
		try {
			utils.waitForEle(MoveSaveButton, "visible", "", 10);
			MoveSaveButton.isDisplayed();
			MoveSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	public boolean MovedValidation() {
	    try {
	    	Thread.sleep(4000);
	        if (MovedValidation.getText().trim().equalsIgnoreCase("Files: 0")) {
	            System.out.println(MovedValidation.getText() + " movedResult");
	        } else {
	            exceptionDesc = "Expected 'Files: 0' but found: " + MovedValidation.getText();
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean Date() {
		try {

			utils.waitForEle(Date,  "visible", "", 10);
			
		Date.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	  

	public boolean DateEntry(String date) {
	    try {
	        Thread.sleep(3000);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Use JS to get the visible flatpickr input next to hidden input and set the date
	        js.executeScript(
	            "var hiddenInput = document.getElementById('fileValidUntil1');" +
	            "var flatpickrInput = hiddenInput.nextElementSibling;" +
	            "if (flatpickrInput && flatpickrInput._flatpickr) {" +
	            "    flatpickrInput._flatpickr.setDate('" + date + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on visible input'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	
	public boolean  UploadButton2ndRow()
	{
		try {
			driver.navigate().refresh();
			Thread.sleep(4000);
			utils.waitForEle(UploadButton2ndRow, "visible", "", 30);
			
			UploadButton2ndRow.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	
	public boolean Select2ndFileTag(String totagname) {
		try {
			Thread.sleep(2000);
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", Select2ndFileTag);
			Thread.sleep(4000);

			utils.waitForEle(Select2ndFileTag,  "visible", "", 10);
			Select2ndFileTag.isDisplayed();
	Select type = new Select(Select2ndFileTag);
	type.selectByVisibleText(totagname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean Date2() {
		try {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", Date2);

			utils.waitForEle(Date2, "visible", "", 20);
			Date2.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean Date2Selected(String date) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fileValidUntil2').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fileValidUntil2')._flatpickr) {" +
		            "  document.getElementById('fileValidUntil2')._flatpickr.setDate('" + date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fileValidUntil2'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}  
	
	
	
	public boolean  FileTagPageLoaded()
	{
		try {
			utils.waitForEle(FileTagPageLoaded, "visible", "", 20);
			FileTagPageLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean MonthSelection(String monthName) {
	    try {
	    	
	    	Thread.sleep(4000);
	        // Step 1: Click on the date picker input to open the calendar
	      //  WebElement dateInput = driver.findElement(By.xpath("(//select[contains(@class,'flatpickr-monthDropdown-months')])[15]"));
	     //   dateInput.click();

	      //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Step 2: Wait for month dropdown to become visible & clickable
	       
	        Thread.sleep(4000);

	        // Step 3: Now use Select to choose the month
	        Select sel = new Select(monthDropdown);
	        sel.selectByVisibleText(monthName);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error While Selecting Month: " + e.getMessage();
	        return false;
	    }
	}


	

	public boolean DaySelection(String dayLabel) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // ✅ Find the visible calendar (with "open" class)
	        WebElement visibleCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]")
	        ));

	        // ✅ Inside that visible calendar, locate the desired day by aria-label
	        WebElement dayElement = visibleCalendar.findElement(By.xpath(".//span[@aria-label='" + dayLabel + "']"));

	        wait.until(ExpectedConditions.elementToBeClickable(dayElement));
	        dayElement.click();

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error While Selecting Day: " + e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean  DatePickerClick()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DatePicker, "visible", "", 20);
			DatePicker.click();
			Thread.sleep(3000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DatePicker(String Date) {
		
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fileValidUntil1').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fileValidUntil1')._flatpickr) {" +
		            "  document.getElementById('fileValidUntil1')._flatpickr.setDate('" + Date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fileValidUntil1'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
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
