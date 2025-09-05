package com.MeghPI.Attendance.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;


public class MeghShiftPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String RoleNames = "";
	public String firstrow = "";
	 

	
	public MeghShiftPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id= 'policyMasteraccordionExample']/div[3]/div/div/ul/li[1]")
	private WebElement ShiftButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddShift'])[2]")
	private WebElement AddShiftButton; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnAddBreak']")
	private WebElement AddBreakButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtBreakName1']")
	private WebElement BreakNameTextField; //1st TestCase
	
	@FindBy(xpath = "//input[@id='breakStartTime1']/../input[2]")
	private WebElement BreakStartTime; //1st TestCase
	
	@FindBy(xpath = "//input[@id='breakEndTime1']/../input[2]")
	private WebElement BreakEndTime; //1st TestCase
	
	@FindBy(xpath = "//input[@id='breakBuffering1']/../input[2]")
	private WebElement BreakBufferTime; //1st TestCase
	
	@FindBy(xpath = "//div[@id='nav-tab']/a[2]")
	private WebElement ShiftPolicyTab; //1st TestCase
	
	@FindBy(xpath = "//div[@id='divShift']/div/div/label")
	private List<WebElement> AllShiftNames; //1st TestCase
	
	@FindBy(xpath = "(//body[@id='addCustomClassForBody']/section/div/div/div[2]/div[1]/div/div[1]/div[2]/div[1]/div[1]/div/div[1]/div/div[1])[1]")
	private WebElement loginValidate;
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[5]/a/button/img")
	private WebElement ShiftEditButton; //2nd TestCase
	
	@FindBy(xpath = "//div[@id='dtShift_filter']/label/input")
	private WebElement ShiftSearchTextField; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[1]")
	private WebElement ShiftFirstRowName; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[3]")
	private WebElement ShiftFirstRowStartTime; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[4]")
	private WebElement ShiftFirstRowEndTime; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[5]/button/img")
	private WebElement ShiftDeleteIcon; //3rd TestCase
	
	
	@FindBy(xpath = "//div[@id='addCustomPolicyClass']/div[1]/div[1]/a[1]")
	private WebElement ShiftTab; //4th TestCase
	
	 @FindBy(xpath = "//div[@class='toast-message']")
	    private WebElement ErrorMessage; //4th TestCase 
	
	
	 @FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[2]")
	    private WebElement ShiftCode; //5th TestCase 
	
	
	 @FindBy(xpath = "//div[@id='element_dataTables_tbl_header']/div[1]/div[1]/div[2]/button")
	    private WebElement ShiftSearchDropDwon; //5th TestCase 
	
	 @FindBy(xpath = "//input[@id='chkdivDrpItemShiftCodedtShift']")
	    private WebElement ShiftCodeCheckBox; //5th TestCase 
	
	 @FindBy(xpath = "//div[text()='Shift Code']")
	    private WebElement ShiftPageLoaded; //5th TestCase 
	 
	
	//1st TestCase
	public boolean ShiftButton() {
		try {

			utils.waitForEle(ShiftButton, "visible", "", 10);
			ShiftButton.isDisplayed();
			ShiftButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddShiftButton() {
		try {

			utils.waitForEle(AddShiftButton, "visible", "", 10);
			AddShiftButton.isDisplayed();
			AddShiftButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddBreakButton() {
		try {

			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", AddBreakButton);

			
			utils.waitForEle(AddBreakButton, "visible", "", 10);
			AddBreakButton.isDisplayed();
			AddBreakButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BreakNameTextField(String breakname) {
		try {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", BreakNameTextField);

			utils.waitForEle(BreakNameTextField,  "visible", "", 10);
			BreakNameTextField.isDisplayed();
			BreakNameTextField.clear();
			BreakNameTextField.sendKeys(breakname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BreakStartTimeClick() {
		try {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", BreakStartTime);

			utils.waitForEle(BreakStartTime, "visible", "", 10);
			BreakStartTime.isDisplayed();
			BreakStartTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BreakStartTime(String breakStartTime) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('breakStartTime1').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('breakStartTime1')._flatpickr) {" +
		            "  document.getElementById('breakStartTime1')._flatpickr.setDate('" + breakStartTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on breakStartTime1'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
		

	public boolean BreakEndTimeClick() {
		try {

			utils.waitForEle(BreakEndTime, "visible", "", 10);
			BreakEndTime.isDisplayed();
			BreakEndTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BreakEndTime(String breakEndTime) {
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('breakEndTime1').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('breakEndTime1')._flatpickr) {" +
		            "  document.getElementById('breakEndTime1')._flatpickr.setDate('" + breakEndTime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on breakEndTime1'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean BreakBufferTimeClick() {
		try {
Thread.sleep(4000);
			utils.waitForEle(BreakBufferTime, "visible", "", 10);
			
			BreakBufferTime.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BreakBufferTime(String breakBuffering) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('breakBuffering1').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('breakBuffering1')._flatpickr) {" +
		            "  document.getElementById('breakBuffering1')._flatpickr.setDate('" + breakBuffering + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on breakBuffering1'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	public boolean ShiftPolicyTab() {
		try {

			utils.waitForEle(ShiftPolicyTab, "visible", "", 10);
			ShiftPolicyTab.isDisplayed();
			ShiftPolicyTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean AllShiftNames(String shiftname) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement element : AllShiftNames) {
	            if (element.getText().trim().equalsIgnoreCase(shiftname)) {
	                return true;
	            }
	        }
	        // If no match found after checking all elements
	        throw new Exception("Shift name not found: " + shiftname);
	        
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean loginValidate() throws InterruptedException {
	    int attempts = 0;

	    while (attempts < 2) {
	        try {
	            // Wait for login validation element
	            utils.waitForEle(loginValidate, "visible", "", 20);

	            if (loginValidate.isDisplayed()) {
	                return true; // Success
	            } else {
	                throw new Exception("Login validation element is not displayed.");
	            }

	        } catch (Exception e) {
	            exceptionDesc = "Login validation attempt " + (attempts + 1) + " failed: " + e.getMessage();
	            attempts++;

	            if (attempts < 2) {
	                driver.navigate().refresh();
	                Thread.sleep(4000); // Wait after refresh
	            } else {
	                return false; // Fail after 2 attempts
	            }
	        }
	    }
	    return false; // Fallback (should not reach here)
	}



	

	
	//2nd TestCase
	public boolean ShiftEditButton() {
		try {

			utils.waitForEle(ShiftEditButton, "visible", "", 10);
			ShiftEditButton.isDisplayed();
			ShiftEditButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftSearchTextField(String shiftname) {
		try {

			utils.waitForEle(ShiftSearchTextField,  "visible", "", 10);
			ShiftSearchTextField.isDisplayed();
			ShiftSearchTextField.clear();
			ShiftSearchTextField.sendKeys(shiftname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftFirstRowName(String shiftname) {
	    try {
	        utils.waitForEle(ShiftFirstRowName, "visible", "", 10);
	        String actualName = ShiftFirstRowName.getText().trim();
	        if (actualName.equalsIgnoreCase(shiftname)) {
	            return true;
	        } else {
	            throw new Exception("Shift name mismatch: Expected '" + shiftname + "', but found '" + actualName + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean ShiftFirstRowStartTime(String shiftstarttime) {
	    try {
	        utils.waitForEle(ShiftFirstRowStartTime, "visible", "", 10);
	        String actualStartTime = ShiftFirstRowStartTime.getText().trim();
	        if (actualStartTime.toLowerCase().contains(shiftstarttime.toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Shift start time mismatch: Expected to contain '" + shiftstarttime + "', but found '" + actualStartTime + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	public boolean ShiftFirstRowEndTime(String shiftendtime) {
	    try {
	        utils.waitForEle(ShiftFirstRowEndTime, "visible", "", 10);
	        String actualEndTime = ShiftFirstRowEndTime.getText().trim();
	        if (actualEndTime.toLowerCase().contains(shiftendtime.toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Shift end time mismatch: Expected to contain '" + shiftendtime + "', but found '" + actualEndTime + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	//3rd TestCase
	
	public boolean ShiftDeleteIcon() {
		try {

			utils.waitForEle(ShiftDeleteIcon, "visible", "", 10);
			ShiftDeleteIcon.isDisplayed();
			ShiftDeleteIcon.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftNotFound(String shiftname) {
	    try {
	        for (WebElement element : AllShiftNames) {
	            if (element.getText().trim().equalsIgnoreCase(shiftname)) {
	                // If match found, shift is present — so return false
	                throw new Exception("Shift name found: " + shiftname);
	            }
	        }
	        // If no element matches, return true — shift is not found
	        return true;
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	//4th TestCase
	public boolean CreatedShiftClick(String shiftname) {
	    try {
	    	Thread.sleep(4000);
	        boolean found = false;

	        for (WebElement element : AllShiftNames) {
	            if (element.getText().trim().equalsIgnoreCase(shiftname)) {
	                // Scroll element into view
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	                js.executeScript("arguments[0].scrollIntoView(true);", element);
	                Thread.sleep(1000); // Small pause after scroll

	                int retries = 3;
	                while (retries > 0) {
	                    try {
	                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                        wait.until(ExpectedConditions.elementToBeClickable(element));
	                        element.click();
	                        found = true;
	                        Thread.sleep(1000); // Wait after click
	                        break; // Exit retry loop after successful click
	                    } catch (Exception clickEx) {
	                        retries--;
	                        if (retries == 0) {
	                            throw new Exception("Failed to click element after retries: " + clickEx.getMessage());
	                        }
	                        Thread.sleep(1000); // Wait before retry
	                    }
	                }

	                // If only one match is needed, uncomment the following:
	                // break;
	            }
	        }

	        return found;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean ShiftTab() {
		try {

			utils.waitForEle(ShiftTab, "visible", "", 10);
			ShiftTab.isDisplayed();
			ShiftTab.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ErrorMsg() {
		try {

			utils.waitForEle(ErrorMessage, "visible", "", 10);
			ErrorMessage.isDisplayed();
			ErrorMessage.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//5th TestCase
	
	
	public boolean ShiftFirstRowNameFetch() {
		try {

			utils.waitForEle(ShiftCode, "visible", "", 10);
			ShiftCode.isDisplayed();
		firstrow =	ShiftCode.getText();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftSearchDropDwon() {
		try {

			utils.waitForEle(ShiftSearchDropDwon, "visible", "", 10);
			ShiftSearchDropDwon.isDisplayed();
			ShiftSearchDropDwon.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftCodeCheckBox() {
		try {

			utils.waitForEle(ShiftCodeCheckBox, "visible", "", 10);
			ShiftCodeCheckBox.isDisplayed();
			ShiftCodeCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftCodeValidation(String shiftcode) {
	    try {
	        String actualCode = ShiftCode.getText();
	        if (actualCode.equalsIgnoreCase(shiftcode)) {
	            return true;
	        } else {
	            exceptionDesc = "Shift code mismatch. Expected: " + shiftcode + ", Actual: " + actualCode;
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = "Error validating shift code: " + e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean ShiftPageLoaded() {
		try {

			utils.waitForEle(ShiftPageLoaded, "visible", "", 10);
			ShiftPageLoaded.isDisplayed();
			
			
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
