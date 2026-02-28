package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Pramod {
	
	  private WebDriver driver;
		Utils utils = new Utils(driver);
	 private static String token;

   
    
    public static String generateRandomAlpha(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();
        
    }
    
    public static long generateRandomNumber(int digits) {
        if (digits <= 0 || digits > 18) {
            throw new IllegalArgumentException("Digits must be between 1 and 18");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, digits - 1);
        long max = (long) Math.pow(10, digits) - 1;

        return min + (long)(random.nextDouble() * (max - min + 1));
    }

   
    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(driver1 -> ((JavascriptExecutor) driver1)
                .executeScript("return document.readyState").toString().equals("complete"));
    }


    //get first day of month from excel date
    public static Map<String, String> getMonthDetails(String date) {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate localDate = LocalDate.parse(date);  // input must be yyyy-MM-dd

            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
            String monthName = localDate.getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(localDate.getYear());

            details.put("firstDayOfMonth", firstDayOfMonth);
            details.put("monthName", monthName);
            details.put("year", year);

        } catch (Exception e) {
            System.out.println("Error in getMonthDetails: " + e.getMessage());
        }

        return details;
    }


    
//current month details
    public static Map<String, String> getCurrentDateDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate localDate = LocalDate.parse(currentDate);

            String monthName = localDate.getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = currentDate.split("-")[0];

            details.put("currentDate", currentDate);
            details.put("monthName", monthName);
            details.put("year", year);

        } catch (Exception e) {
            System.out.println("Error in getCurrentDateDetails: " + e.getMessage());
        }

        return details;
    }

 // Get First Working Day of this month
    public static Map<String, String> getMonthFirstWorkingDateDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Adjust if the first date is weekend
            if (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                firstDate = firstDate.plusDays(2);
            } else if (firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Formatter for dd-MM-yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String PunchinDateSearchUI = firstDate.format(formatter);

            // Add details
            String monthFirstWorkingDate = firstDate.toString(); // yyyy-MM-dd
            String firstDayOfMonth = today.withDayOfMonth(1).toString();
            String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(today.getYear());

            details.put("monthFirstWorkingDate", monthFirstWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);
            details.put("monthName", monthName);
            details.put("year", year);
            details.put("PunchinDateSearchUI", PunchinDateSearchUI);

        } catch (Exception e) {
            System.out.println("Error in getMonthFirstWorkingDateDetails: " + e.getMessage());
        }

        return details;
    }

 // Get Previous Working Day (skips Saturday and Sunday)
    public static LocalDate getPreviousWorkingDay() {
        LocalDate today = LocalDate.now();

        // Start from yesterday
        LocalDate prevDate = today.minusDays(1);

        // If previous day is Saturday → go back to Friday
        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(1);
        }
        // If previous day is Sunday → go back to Friday
        else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(2);
        }

        return prevDate;
    }
    
    // Find the first Sunday of this month
    public static Map<String, String> getMonthFirstWeekoffDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // First day of the month
            LocalDate firstDayOfMonth = today.withDayOfMonth(1);

            // Find the first week-off (Saturday) of the month
            LocalDate firstWeekoff = firstDayOfMonth;
            while (firstWeekoff.getDayOfWeek() != DayOfWeek.SATURDAY) {
                firstWeekoff = firstWeekoff.plusDays(1);
            }

            // Formatters
            DateTimeFormatter defaultFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Add to map
            details.put("firstDayOfMonth", firstDayOfMonth.format(defaultFmt));
            details.put("firstWeekoffDate", firstWeekoff.format(defaultFmt));         // yyyy-MM-dd
            details.put("firstWeekoffDateUI", firstWeekoff.format(uiFmt));            // dd-MM-yyyy

        } catch (Exception e) {
            System.out.println("Error in getMonthFirstWeekoffDetails: " + e.getMessage());
        }

        return details;
    }

    
 // Get Second Working Day of the Current Month (skips Saturday and Sunday)
    public static Map<String, String> getMonthSecondWorkingDateDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find first working day
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Move to 2nd working day
            LocalDate workingDate = firstDate;
            int workingDayCount = 1;

            while (workingDayCount < 2) {
                workingDate = workingDate.plusDays(1);
                if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDayCount++;
                }
            }

            String monthSecondWorkingDate = workingDate.toString(); // yyyy-MM-dd

            // Optional UI format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String monthSecondWorkingDateUI = workingDate.format(formatter);

            // Add to map
            details.put("monthSecondWorkingDate", monthSecondWorkingDate);
            details.put("monthSecondWorkingDateUI", monthSecondWorkingDateUI);

        } catch (Exception e) {
            System.out.println("Error in getMonthSecondWorkingDateDetails: " + e.getMessage());
        }

        return details;
    }

 // Get previous working day details (skipping Saturday & Sunday)
    public static Map<String, String> getPreviousWorkingDayDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Find previous working day
            LocalDate prevDate = today.minusDays(1);

            // If it's Saturday → go back to Friday
            if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                prevDate = prevDate.minusDays(1);
            }
            // If it's Sunday → go back to Friday
            else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                prevDate = prevDate.minusDays(2);
            }

            // Prepare details
            String punchinDate = prevDate.toString(); // yyyy-MM-dd
            String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(prevDate.getYear());

            // Optional UI format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String punchinDateUI = prevDate.format(formatter);

            // Put in map
            details.put("punchinDate", punchinDate);
            details.put("punchinDateUI", punchinDateUI);
            details.put("monthName", monthName);
            details.put("year", year);

        } catch (Exception e) {
            System.out.println("Error in getPreviousWorkingDayDetails: " + e.getMessage());
        }

        return details;
    }
 
    //3days before working day
    public static Map<String, String> getDateDetails(String date) {
        Map<String, String> details = new HashMap<>();

        try {
            // Parse the given date (format: yyyy-MM-dd)
            LocalDate localDate = LocalDate.parse(date);

            // Get first day of the same month
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            // Today's date
            LocalDate today = LocalDate.now();

            // Go 3 days back from today
            LocalDate prevDate = today.minusDays(3);

            // If it's Saturday → go back 3 more days
            if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                prevDate = prevDate.minusDays(3);
            }
            // If it's Sunday → go back 4 more days
            else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                prevDate = prevDate.minusDays(4);
            }

            // Final punch-in date
            String punchinDate = prevDate.toString();

            // Month name and year
            String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(prevDate.getYear());

            // Store in map
            details.put("firstDayOfMonth", firstDayOfMonth);
            details.put("punchinDate", punchinDate);
            details.put("monthName", monthName);
            details.put("year", year);

        } catch (Exception e) {
            System.out.println("Error in getDateDetails(): " + e.getMessage());
        }

        return details;
    }

    //4days back working day
    public static Map<String, String> getPreviousWorkingDateDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            // Today's date
            LocalDate today = LocalDate.now();

            // First day of today's month
            String firstDayOfMonth = today.withDayOfMonth(1).toString();

            // Yesterday
            LocalDate prevDate = today.minusDays(1);

            // Skip weekends (Saturday / Sunday)
            while (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                   prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                prevDate = prevDate.minusDays(1);
            }

            // Final working day
            String PunchinDate2 = prevDate.toString();

            // Month and year
            String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(prevDate.getYear());

            // Store in map
            details.put("firstDayOfMonth", firstDayOfMonth);
            details.put("PunchinDate2", PunchinDate2);
            details.put("monthName", monthName);
            details.put("year", year);

        } catch (Exception e) {
            System.out.println("Error in getPreviousWorkingDateDetails(): " + e.getMessage());
        }

        return details;
    }


    //first and 2nd working day
    public static Map<String, String> getFirstAndSecondWorkingDatesOfMonth() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first working day (Mon–Fri)
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Find the second working day
            LocalDate secondDate = firstDate.plusDays(1);
            while (secondDate.getDayOfWeek() == DayOfWeek.SATURDAY || secondDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                secondDate = secondDate.plusDays(1);
            }

            // Convert to String (yyyy-MM-dd format)
            String monthFirstWorkingDate = firstDate.toString();
            String monthSecondWorkingDate = secondDate.toString();

            // Get the first actual day of the same month
            LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            // Store values
            details.put("monthFirstWorkingDate", monthFirstWorkingDate);
            details.put("monthSecondWorkingDate", monthSecondWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getFirstAndSecondWorkingDatesOfMonth(): " + e.getMessage());
        }

        return details;
    }

    
    //first Saturday and sunday
    public static Map<String, Object> getMonthWeekoffDetails() {
        Map<String, Object> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // First day of this month
            LocalDate firstDate = today.withDayOfMonth(1);
            String firstDayOfMonth = firstDate.toString();

            // ----- Find First Saturday -----
            LocalDate firstSaturday = firstDate;
            while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
                firstSaturday = firstSaturday.plusDays(1);
            }
            String monthFirstSaturday = firstSaturday.toString();

            // ----- Find First Sunday -----
            LocalDate firstSunday = firstDate;
            while (firstSunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
                firstSunday = firstSunday.plusDays(1);
            }
            String monthFirstSunday = firstSunday.toString();

            // ----- Count all week-offs (Sat + Sun) from 1st till today -----
            int weekoffCount = 0;
            for (LocalDate date = firstDate; !date.isAfter(today); date = date.plusDays(1)) {
                DayOfWeek day = date.getDayOfWeek();
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    weekoffCount++;
                }
            }

            // Store values
            details.put("firstDayOfMonth", firstDayOfMonth);
            details.put("monthFirstSaturday", monthFirstSaturday);
            details.put("monthFirstSunday", monthFirstSunday);
            details.put("weekoffCount", weekoffCount);

        } catch (Exception e) {
            System.out.println("Error in getMonthWeekoffDetails(): " + e.getMessage());
        }

        return details;
    }


    
    //7th working day
    public static String getSeventhWorkingDayOfMonth() {
        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Move forward to find 7th working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1;

        while (workingDayCount < 7) {
            workingDate = workingDate.plusDays(1);
            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDayCount++;
            }
        }

        return workingDate.toString(); // yyyy-MM-dd
    }

//first and 2nd
    public static String getSecondWorkingDayOfMonth() {
        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Now find the second working day
        LocalDate secondWorkingDate = firstDate.plusDays(1);
        while (secondWorkingDate.getDayOfWeek() == DayOfWeek.SATURDAY || secondWorkingDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            secondWorkingDate = secondWorkingDate.plusDays(1);
        }

        return secondWorkingDate.toString(); // yyyy-MM-dd
    }

    
   //3rd working day
    public static Map<String, String> getThirdWorkingDayOfMonthDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first working day (Mon–Fri)
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Move forward to find 3rd working day
            LocalDate workingDate = firstDate;
            int workingDayCount = 1;

            while (workingDayCount < 3) { // loop until we reach 3rd working day
                workingDate = workingDate.plusDays(1);
                if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDayCount++;
                }
            }

            String monthThirdWorkingDate = workingDate.toString();

            LocalDate localDate = LocalDate.parse(monthThirdWorkingDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            details.put("monthThirdWorkingDate", monthThirdWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getThirdWorkingDayOfMonthDetails: " + e.getMessage());
        }

        return details;
    }

   //4th working day
    public static Map<String, String> getFourthWorkingDayOfMonthDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first working day (Mon–Fri)
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Move forward to find 4th working day
            LocalDate workingDate = firstDate;
            int workingDayCount = 1;

            while (workingDayCount < 4) { // loop until we reach 4th working day
                workingDate = workingDate.plusDays(1);
                if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDayCount++;
                }
            }

            String monthFourthWorkingDate = workingDate.toString();

            LocalDate localDate = LocalDate.parse(monthFourthWorkingDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            details.put("monthFourthWorkingDate", monthFourthWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getFourthWorkingDayOfMonthDetails: " + e.getMessage());
        }

        return details;
    }

    //5th Working Day
    public static Map<String, String> getFifthWorkingDayOfMonthDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first working day (Mon–Fri)
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Move forward to find 5th working day
            LocalDate workingDate = firstDate;
            int workingDayCount = 1;

            while (workingDayCount < 5) { // loop until we reach 5th working day
                workingDate = workingDate.plusDays(1);
                if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDayCount++;
                }
            }

            String monthFifthWorkingDate = workingDate.toString();

            LocalDate localDate = LocalDate.parse(monthFifthWorkingDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            details.put("monthFifthWorkingDate", monthFifthWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getFifthWorkingDayOfMonthDetails: " + e.getMessage());
        }

        return details;
    }

    //2nd weekoff saturday
    public static Map<String, String> getSecondSaturdayOfMonthDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first Saturday of this month
            while (firstDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Now find the second Saturday
            LocalDate secondSaturday = firstDate.plusWeeks(1);

            String monthSecondSaturdayDate = secondSaturday.toString();

            LocalDate localDate = LocalDate.parse(monthSecondSaturdayDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            details.put("monthSecondSaturdayDate", monthSecondSaturdayDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getSecondSaturdayOfMonthDetails: " + e.getMessage());
        }

        return details;
    }

    
  //6th working  date
    public static Map<String, String> getSixthWorkingDayOfMonthDetails() {
        Map<String, String> details = new HashMap<>();

        try {
            LocalDate today = LocalDate.now();

            // Get the first day of the current month
            LocalDate firstDate = today.withDayOfMonth(1);

            // Find the first working day (Mon–Fri)
            while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                firstDate = firstDate.plusDays(1);
            }

            // Move forward to find 6th working day
            LocalDate workingDate = firstDate;
            int workingDayCount = 1;

            while (workingDayCount < 6) { // loop until we reach 6th working day
                workingDate = workingDate.plusDays(1);
                if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDayCount++;
                }
            }

            String monthSixthWorkingDate = workingDate.toString();

            LocalDate localDate = LocalDate.parse(monthSixthWorkingDate);
            String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

            details.put("monthSixthWorkingDate", monthSixthWorkingDate);
            details.put("firstDayOfMonth", firstDayOfMonth);

        } catch (Exception e) {
            System.out.println("Error in getSixthWorkingDayOfMonthDetails: " + e.getMessage());
        }

        return details;
    }

    //previous day
    public static Map<String, String> getPunchinDateDetails(String date) {
        Map<String, String> details = new HashMap<>();

        LocalDate localDate = LocalDate.parse(date);
        String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

        LocalDate today = LocalDate.now();
        LocalDate prevDate = today.minusDays(1);

        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(1);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(2);
        }

        String punchinDate = prevDate.toString();
        String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String[] parts = punchinDate.split("-");
        String year = parts[0];

        details.put("firstDayOfMonth", firstDayOfMonth);
        details.put("punchinDate", punchinDate);
        details.put("monthName", monthName);
        details.put("year", year);

        return details;
    }

    //2 days previous working day
    public static String[] getPreviousWorkingDayMinus2Details() {
        LocalDate today = LocalDate.now();

        // Find previous working day (2 days before)
        LocalDate prevDate = today.minusDays(2);

        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(2);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(4);
        }

        String punchinDate2 = prevDate.toString();
        String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(prevDate.getYear());

        LocalDate localDate = LocalDate.parse(punchinDate2);
        String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

        return new String[] { punchinDate2, monthName, year, firstDayOfMonth };
    }
    
    //previous day details
    public static String[] getPreviousWorkingDayMinusTwo() {
        LocalDate today = LocalDate.now();

        // Find previous working day by going 2 days back
        LocalDate prevDate = today.minusDays(2);

        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(2);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(4);
        }

        String regulizationDate = prevDate.toString();
        String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(prevDate.getYear());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedPrevDate = prevDate.format(formatter);

        return new String[] { regulizationDate, monthName, year, formattedPrevDate };
    }

    public static String[] getPreviousWorkingDayMinusThree() {

        LocalDate today = LocalDate.now();

        // Go 3 days back
        LocalDate prevDate = today.minusDays(3);

        // Adjust if weekend
        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(3);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(4);
        }

        String regulizationDate = prevDate.toString();
        String monthName = prevDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(prevDate.getYear());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedPrevDate = prevDate.format(formatter);

        return new String[]{ regulizationDate, monthName, year, formattedPrevDate };
    }
 
    
    public static String[] getPreviousWorkingDayMinus2() {

        LocalDate today = LocalDate.now();

        // Find previous working day (2 days before today)
        LocalDate prevDate = today.minusDays(2);

        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(2);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(4);
        }

        String regulizationDate = prevDate.toString(); // yyyy-MM-dd

        String monthName = prevDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String[] parts = regulizationDate.split("-");
        String year = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedPrevDate = prevDate.format(formatter);

        return new String[]{regulizationDate, monthName, year, formattedPrevDate};
    } 
    
    //4th date
    public static String[] getPreviousWorkingDayMinus4() {

        LocalDate today = LocalDate.now();

        // Previous working day (4 days before)
        LocalDate prevDate = today.minusDays(4);

        if (prevDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            prevDate = prevDate.minusDays(4);
        } else if (prevDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prevDate = prevDate.minusDays(4);
        }

        String regulizationDate = prevDate.toString(); // yyyy-MM-dd

        String monthName = prevDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String[] parts = regulizationDate.split("-");
        String year = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedPrevDate = prevDate.format(formatter);

        return new String[]{regulizationDate, monthName, year, formattedPrevDate};
    }
    
    
    public static String[] convertDateFormats(String date) {
        LocalDate localDate1 = LocalDate.parse(date);

        String monthName = localDate1.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String[] parts = date.split("-");
        String year = parts[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, inputFormat);

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDate.format(outputFormat);

        return new String[]{monthName, year, formattedDate};
    }
    
    public static String[] getMonthYearAndFormattedDate(String date) {

        LocalDate localDate1 = LocalDate.parse(date);
        String monthName = localDate1.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH); 

        String[] parts = date.split("-");
        String year = parts[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, inputFormat);

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDate.format(outputFormat);

        return new String[]{monthName, year, formattedDate};
    }
    
    
    public static String[] getFirstTwoWorkingDaysOfMonth() {
        LocalDate today = LocalDate.now();

        // Get first day of current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY 
                || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Find second working day (Mon–Fri)
        LocalDate secondDate = firstDate.plusDays(1);
        while (secondDate.getDayOfWeek() == DayOfWeek.SATURDAY 
                || secondDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            secondDate = secondDate.plusDays(1);
        }

        String monthFirstWorkingDate = firstDate.toString();   // yyyy-MM-dd
        String monthSecondWorkingDate = secondDate.toString(); // yyyy-MM-dd

        return new String[] { monthFirstWorkingDate, monthSecondWorkingDate };
    }

    public static String[] getThirdWorkingDayAndMonthYear() {

        LocalDate today = LocalDate.now();

        // Get first day of the month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Find the 3rd working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1;

        while (workingDayCount < 3) {
            workingDate = workingDate.plusDays(1);
            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDayCount++;
            }
        }

        String monthThirdWorkingDate = workingDate.toString(); // yyyy-MM-dd format

        String monthName = workingDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String[] parts = monthThirdWorkingDate.split("-");
        String year = parts[0];

        return new String[] { monthThirdWorkingDate, monthName, year };
    }

    
    public static String getFourthWorkingDayOfMonth() {

        LocalDate today = LocalDate.now();

        // Get the first day of the month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Find the 4th working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1;

        while (workingDayCount < 4) {  // until we reach 4th working day
            workingDate = workingDate.plusDays(1);
            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDayCount++;
            }
        }

        return workingDate.toString(); // yyyy-MM-dd
    }

    public static String[] getFourthWorkingDayDetails() {
        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Move forward to find 4th working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1;

        while (workingDayCount < 4) {
            workingDate = workingDate.plusDays(1);
            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) 
            {
                workingDayCount++;
            }
        }

        String fourthWorkingDate = workingDate.toString(); // yyyy-MM-dd format
        String monthName = firstDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = fourthWorkingDate.split("-")[0];

        return new String[] { monthName, year, fourthWorkingDate };
    }

    
    public static String[] getFifthWorkingDayDetails() {
        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Move forward to find 5th working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1;

        while (workingDayCount < 5) {
            workingDate = workingDate.plusDays(1);
            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDayCount++;
            }
        }

        String fifthWorkingDate = workingDate.toString(); // yyyy-MM-dd

        String monthName = workingDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = fifthWorkingDate.split("-")[0]; // YYYY

        return new String[] { monthName, year, fifthWorkingDate };
    }
 
    public static String[] getFirstSaturdayAndMonthDetails() {

        LocalDate today = LocalDate.now();

        // Get the first date of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find first Saturday
        LocalDate firstSaturday = firstDate;
        while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }

        // Format to dd-MM-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String firstSaturdayStr = firstSaturday.format(formatter);

        // Month & Year
        String monthName = firstSaturday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(firstSaturday.getYear());

        return new String[]{monthName, year, firstSaturdayStr};
    }

    
    public static String[] getFirstSaturdayMonthYear() {

        LocalDate today = LocalDate.now();

        // Get first date of month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find first Saturday
        LocalDate firstSaturday = firstDate;
        while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }

        String saturday = firstSaturday.toString();

        String monthName = firstDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String[] parts = saturday.split("-");
        String year = parts[0];

        return new String[]{monthName, year, saturday}; 
    }

    
    public static String getFirstSundayUI() {

        LocalDate today = LocalDate.now();

        // Get first date of month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find first Saturday
        LocalDate firstSaturday = firstDate;
        while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }

        // First Sunday = next day
        LocalDate firstSunday = firstSaturday.plusDays(1);

        // Format for UI
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return firstSunday.format(formatter);
    }

    public static Map<String, String> getFirstSaturdayMondayWorkingDay() {

        LocalDate today = LocalDate.now();
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find first Saturday
        LocalDate firstSaturday = firstDate;
        while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }

        // First Monday after the first Saturday
        LocalDate firstMonday = firstSaturday.plusDays(2);
        if (firstMonday.getDayOfWeek() != DayOfWeek.MONDAY) {
            // adjust if month starts after Saturday/Sunday cases
            firstMonday = firstSaturday.with(java.time.temporal.TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        // Working day UI (skip Sat/Sun)
        LocalDate workingDay = firstDate;
        while (workingDay.getDayOfWeek() == DayOfWeek.SATURDAY || workingDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            workingDay = workingDay.plusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Map<String, String> map = new HashMap<>();
        map.put("firstSaturday", firstSaturday.format(formatter));
        map.put("firstMonday", firstMonday.format(formatter));
        map.put("workingDayUi", workingDay.format(formatter));

        return map;
    }

    
   
    
    public static String getFirstDayOfMonth() {
        LocalDate firstDate = LocalDate.now().withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE - dd MMM yyyy", Locale.ENGLISH);
        return firstDate.format(formatter); // Saturday - 01 Nov 2025
    }

    public boolean tapBackButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement backIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("(//android.widget.ImageView)[1]")
            ));
            
            backIcon.click();  
            return true;
            
        } catch (Exception e) {
            System.out.println("❌ tapBackButton() failed: " + e.getMessage());
            return false;
        }
    }


    public static boolean isElementPresent(WebElement ele) {
        try {
            return ele.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hideKeyboardSmart(WebDriver driver) {
        try {
            // Cast once
            AndroidDriver ad = (AndroidDriver) driver;

            // Small delay so UI reflects true state after sendKeys
            Thread.sleep(300);

            // Check using Appium method
            boolean kbShownByAppium = false;
            try {
                kbShownByAppium = ad.isKeyboardShown();
            } catch (Exception ignored) {}

            // Check using UIAutomator fallback (OnePlus / Android 14-15)
            boolean kbShownByUI = false;
            try {
                kbShownByUI = 
                    ad.findElements(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.inputmethodservice.KeyboardView\")"
                    )).size() > 0;
            } catch (Exception ignored) {}

            // Hide only if keyboard is actually visible
            if (kbShownByAppium || kbShownByUI) {
                try {
                    ad.hideKeyboard();
                } catch (Exception ignored) {}
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void swipe(AndroidDriver driver, int startX, int startY, int endX, int endY, int duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger,1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public static String getFirstDayOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return firstDay.format(formatter);
    }

    
    //more than 31 days
    

   public static String getDateNDaysBack(int daysBack) {
       LocalDate today = LocalDate.now();
       LocalDate targetDate = today.minusDays(daysBack);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
       return targetDate.format(formatter);
   }
    
   
   
   //2ndsaturday
   public static String[] getSecondSaturdayAndNextMonday() {

	    LocalDate today = LocalDate.now();
	    LocalDate firstDate = today.withDayOfMonth(1);

	    // Find first Saturday of the month
	    LocalDate saturday = firstDate;
	    while (saturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
	        saturday = saturday.plusDays(1);
	    }

	    // Get second Saturday
	    LocalDate secondSaturday = saturday.plusDays(7);

	    // Find next Monday
	    LocalDate nextMonday = secondSaturday.plusDays(2); // Saturday + 2 days = Monday

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    String secondSaturdayStr = secondSaturday.format(formatter);
	    String nextMondayStr = nextMonday.format(formatter);

	    String monthName = secondSaturday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	    String year = String.valueOf(secondSaturday.getYear());

	    return new String[]{monthName, year, secondSaturdayStr, nextMondayStr};
	}


// 2nd Sunday + Next Working Day (Tuesday)
public static String[] getSecondSundayDetails() {

    LocalDate today = LocalDate.now();
    LocalDate firstDate = today.withDayOfMonth(1);

    // Find first Sunday
    LocalDate sunday = firstDate;
    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
        sunday = sunday.plusDays(1);
    }

    // Second Sunday
    LocalDate secondSunday = sunday.plusDays(7);

    // Next working day you need = TUESDAY
    LocalDate nextWorkingDay = secondSunday.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    return new String[]{
            secondSunday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), // Month
            String.valueOf(secondSunday.getYear()),                                // Year
            secondSunday.format(formatter),                                        // 2nd Sunday date
            nextWorkingDay.format(formatter)                                       // Tuesday after 2nd Sunday
    };
}

//get firstsundaytopunchin
public static Map<String, String> getFirstSaturdayMondayWorkingDayforweekoffpunch() {

    LocalDate today = LocalDate.now();
    LocalDate firstDate = today.withDayOfMonth(1);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 🔹 First Saturday of the month
    LocalDate firstSaturday = firstDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

    // 🔹 First Monday after the first Saturday
    LocalDate firstMonday = firstSaturday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

    // 🔹 First Working Day (Mon–Fri)
    LocalDate workingDayUi = firstDate;
    while (workingDayUi.getDayOfWeek() == DayOfWeek.SATURDAY ||
           workingDayUi.getDayOfWeek() == DayOfWeek.SUNDAY) {
        workingDayUi = workingDayUi.plusDays(1);
    }

    // 🔹 First Sunday of the month
    LocalDate firstSunday = firstDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

    // Prepare Map
    Map<String, String> map = new HashMap<>();
    map.put("firstDayOfMonth", firstDate.format(formatter));
    map.put("firstSaturday", firstSaturday.format(formatter));
    map.put("firstMonday", firstMonday.format(formatter));
    map.put("workingDayUi", workingDayUi.format(formatter));
    map.put("firstSunday", firstSunday.format(formatter));

    return map;
}


public static Map<String, String> getFourthWorkingDayOfMonthDetailsForRegularization() {
    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate firstDate = today.withDayOfMonth(1);

        // Find the first working day (Mon–Fri)
        while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
               firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            firstDate = firstDate.plusDays(1);
        }

        // Move forward to find 4th working day
        LocalDate workingDate = firstDate;
        int workingDayCount = 1; // first working day found already

        while (workingDayCount < 4) { // loop until 4th working day
            workingDate = workingDate.plusDays(1);

            if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {

                workingDayCount++;
            }
        }

        String fourthWorkingDay = workingDate.toString(); // yyyy-MM-dd
        String firstDayOfMonth = workingDate.withDayOfMonth(1).toString();

        details.put("monthFourthWorkingDate", fourthWorkingDay);
        details.put("firstDayOfMonth", firstDayOfMonth);

    } catch (Exception e) {
        System.out.println("Error in getFourthWorkingDayOfMonthDetails: " + e.getMessage());
    }

    return details;
}

public static Map<String, String> getMonthWorkingDatesDetails() {
    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();
        LocalDate date = today.withDayOfMonth(1);

        // ---- Collect First 7 Working Days ----
        List<LocalDate> workingDays = new ArrayList<>();

        while (workingDays.size() < 7) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY &&
                date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays.add(date);
            }
            date = date.plusDays(1);
        }

        // Formatters
        DateTimeFormatter apiFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter uiFmt  = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // 🟢 2nd Working Day
        LocalDate second = workingDays.get(1);
        details.put("secondWorkingDate", second.format(apiFmt));
        details.put("secondWorkingDateUI", second.format(uiFmt));

        // 🟢 3rd Working Day
        LocalDate third = workingDays.get(2);
        details.put("thirdWorkingDate", third.format(apiFmt));
        details.put("thirdWorkingDateUI", third.format(uiFmt));

        // 🟢 4th Working Day
        LocalDate fourth = workingDays.get(3);
        details.put("fourthWorkingDate", fourth.format(apiFmt));
        details.put("fourthWorkingDateUI", fourth.format(uiFmt));

        // 🟢 5th Working Day
        LocalDate fifth = workingDays.get(4);
        details.put("fifthWorkingDate", fifth.format(apiFmt));
        details.put("fifthWorkingDateUI", fifth.format(uiFmt));

        // 🟢 6th Working Day
        LocalDate sixth = workingDays.get(5);
        details.put("sixthWorkingDate", sixth.format(apiFmt));
        details.put("sixthWorkingDateUI", sixth.format(uiFmt));

        // 🟢 7th Working Day
        LocalDate seventh = workingDays.get(6);
        details.put("seventhWorkingDate", seventh.format(apiFmt));
        details.put("seventhWorkingDateUI", seventh.format(uiFmt));

    } catch (Exception e) {
        System.out.println("Error in getMonthWorkingDatesDetails: " + e.getMessage());
    }

    return details;
}

//8th working day
public static String getEighthWorkingDayOfMonth() {
 LocalDate today = LocalDate.now();

 // Get the first day of the current month
 LocalDate firstDate = today.withDayOfMonth(1);

 // Find the first working day (Mon–Fri)
 while (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY || firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
     firstDate = firstDate.plusDays(1);
 }

 // Move forward to find 8th working day
 LocalDate workingDate = firstDate;
 int workingDayCount = 1;

 while (workingDayCount < 8) {
     workingDate = workingDate.plusDays(1);
     if (workingDate.getDayOfWeek() != DayOfWeek.SATURDAY && workingDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
         workingDayCount++;
     }
 }

 return workingDate.toString(); // yyyy-MM-dd
}


public static Map<String, String> getLastMonthWorkingDays() {

    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // Last Month
        LocalDate lastMonth = today.minusMonths(1);

        LocalDate firstDate = lastMonth.withDayOfMonth(1);
        LocalDate lastDate  = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());

        int workingDays = 0;

        // Loop entire last month
        LocalDate date = firstDate;

        while (!date.isAfter(lastDate)) {

            // Sunday = week off
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays++;
            }

            date = date.plusDays(1);
        }

        // Fill response map
        details.put("lastMonthFirstDate", firstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        details.put("lastMonthLastDate", lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        details.put("monthName", lastMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        details.put("year", String.valueOf(lastMonth.getYear()));
        details.put("workingDays", String.valueOf(workingDays));

    } catch (Exception e) {
        System.out.println("Error in getLastMonthWorkingDays: " + e.getMessage());
    }

    return details;
}

public static int getAbsentCount(int workingDaysTillToday, int presentCountFromExcel, int holidayCount) {
    return workingDaysTillToday - presentCountFromExcel - holidayCount;
}


//lastmonthsunday
public static Map<String, String> getLastMonthLastSundayDetails() {
    Map<String, String> details = new HashMap<>();

    try {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Go to previous month
        LocalDate lastMonth = today.minusMonths(1).withDayOfMonth(1);

        // Move to end of last month
        LocalDate lastDay = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());

        // Find the last Sunday
        while (lastDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastDay = lastDay.minusDays(1);
        }

        // API format (yyyy-MM-dd)
        String lastSunday = lastDay.toString();

        // UI format (dd-MM-yyyy)
        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String lastSundayUI = lastDay.format(uiFormat);

        // Month name (Full)
        String monthName = lastDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        // Year
        String year = String.valueOf(lastDay.getYear());

        // Store in map
        details.put("lastMonthLastSunday", lastSunday);
        details.put("lastMonthLastSundayUI", lastSundayUI);
        details.put("monthName", monthName);
        details.put("year", year);

    } catch (Exception e) {
        System.out.println("Error in getLastMonthLastSundayDetails: " + e.getMessage());
    }

    return details;
}

//Last Month All saturdays and sunday as weekoff
public static Map<String, Object> getLastMonthWeekendAndWorkingDetails() {
    Map<String, Object> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // --- Last Month ---
        LocalDate firstDayLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayLastMonth = today.minusMonths(1).withDayOfMonth(today.minusMonths(1).lengthOfMonth());

        // Store
        details.put("firstDayLastMonth", firstDayLastMonth.toString());
        details.put("lastDayLastMonth", lastDayLastMonth.toString());

        List<LocalDate> saturdays = new ArrayList<>();
        List<LocalDate> sundays = new ArrayList<>();

        // --- Collect all Saturdays & Sundays of last month ---
        LocalDate loop = firstDayLastMonth;
        while (!loop.isAfter(lastDayLastMonth)) {
            if (loop.getDayOfWeek() == DayOfWeek.SATURDAY) {
                saturdays.add(loop);
            }
            if (loop.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundays.add(loop);
            }
            loop = loop.plusDays(1);
        }

        details.put("allSaturdays", saturdays);
        details.put("allSundays", sundays);

        // --- 1st, 2nd, 3rd, 4th Saturday ---
        details.put("1stSaturday", saturdays.size() >= 1 ? saturdays.get(0).toString() : null);
        details.put("2ndSaturday", saturdays.size() >= 2 ? saturdays.get(1).toString() : null);
        details.put("3rdSaturday", saturdays.size() >= 3 ? saturdays.get(2).toString() : null);
        details.put("4thSaturday", saturdays.size() >= 4 ? saturdays.get(3).toString() : null);

        // --- 1st, 2nd, 3rd, 4th Sunday ---
        details.put("1stSunday", sundays.size() >= 1 ? sundays.get(0).toString() : null);
        details.put("2ndSunday", sundays.size() >= 2 ? sundays.get(1).toString() : null);
        details.put("3rdSunday", sundays.size() >= 3 ? sundays.get(2).toString() : null);
        details.put("4thSunday", sundays.size() >= 4 ? sundays.get(3).toString() : null);

        // --- Next working day ---
        // For 1st & 2nd Saturday → Monday next
        if (saturdays.size() >= 1) {
            details.put("workingAfter1stSaturday", saturdays.get(0).plusDays(2).toString());
        }
        if (saturdays.size() >= 2) {
            details.put("workingAfter2ndSaturday", saturdays.get(1).plusDays(2).toString());
        }

        // For 1st & 2nd Sunday → Tuesday next
        if (sundays.size() >= 1) {
            details.put("workingAfter1stSunday", sundays.get(0).plusDays(2).toString());
        }
        if (sundays.size() >= 2) {
            details.put("workingAfter2ndSunday", sundays.get(1).plusDays(2).toString());
        }

    } catch (Exception e) {
        System.out.println("Error in getLastMonthWeekendAndWorkingDetails: " + e.getMessage());
    }

    return details;
}


//sat and sunday
public static Map<String, Object> getLastMonthWeekendAndWorkingDetailsfoentiremonth() {
    Map<String, Object> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        LocalDate firstDayLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayLastMonth = today.minusMonths(1)
                .withDayOfMonth(today.minusMonths(1).lengthOfMonth());

        details.put("firstDayLastMonth", firstDayLastMonth.toString());
        details.put("lastDayLastMonth", lastDayLastMonth.toString());

        List<LocalDate> saturdays = new ArrayList<>();
        List<LocalDate> sundays = new ArrayList<>();

        LocalDate loop = firstDayLastMonth;
        while (!loop.isAfter(lastDayLastMonth)) {
            if (loop.getDayOfWeek() == DayOfWeek.SATURDAY) saturdays.add(loop);
            if (loop.getDayOfWeek() == DayOfWeek.SUNDAY) sundays.add(loop);
            loop = loop.plusDays(1);
        }

        // Correct ordinal suffix function
        String[] ord = {"1st", "2nd", "3rd", "4th"};

        // Store weekends with correct names
        for (int i = 0; i < 4; i++) {
            details.put(ord[i] + "Saturday", saturdays.size() > i ? saturdays.get(i).toString() : null);
            details.put(ord[i] + "Sunday", sundays.size() > i ? sundays.get(i).toString() : null);
        }

        // Working days after each weekend
        for (int i = 0; i < 4; i++) {

            LocalDate sat = saturdays.size() > i ? saturdays.get(i) : null;
            LocalDate sun = sundays.size() > i ? sundays.get(i) : null;

            LocalDate weekoffEnd = (sun != null ? sun : sat);
            if (weekoffEnd == null) continue;

            List<String> nextWorking = new ArrayList<>();
            LocalDate next = weekoffEnd.plusDays(1);

            while (nextWorking.size() < 3) {
                if (next.getDayOfWeek() != DayOfWeek.SATURDAY &&
                        next.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    nextWorking.add(next.toString());
                }
                next = next.plusDays(1);
            }

            details.put("workingAfter" + (i + 1) + "Weekoff", nextWorking);
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    return details;
}




/*

  Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();

// Month boundaries
String firstDayLastMonth = (String) details.get("firstDayLastMonth");
String lastDayLastMonth  = (String) details.get("lastDayLastMonth");

// Week 1
String week1Saturday = (String) details.get("1stSaturday");
String week1Sunday   = (String) details.get("1stSunday");
List<String> week1WorkingDays = (List<String>) details.get("workingAfter1Weekoff");

// Week 2
String week2Saturday = (String) details.get("2ndSaturday");
String week2Sunday   = (String) details.get("2ndSunday");
List<String> week2WorkingDays = (List<String>) details.get("workingAfter2Weekoff");

// Week 3
String week3Saturday = (String) details.get("3rdSaturday");
String week3Sunday   = (String) details.get("3rdSunday");
List<String> week3WorkingDays = (List<String>) details.get("workingAfter3Weekoff");

// Week 4
String week4Saturday = (String) details.get("4thSaturday");
String week4Sunday   = (String) details.get("4thSunday");
List<String> week4WorkingDays = (List<String>) details.get("workingAfter4Weekoff");

// Example usage
String punchInDate     = week1WorkingDays.get(0);
String punchOutDate    = week1WorkingDays.get(1);
String validationDate  = week1WorkingDays.get(2);

// Week 2 example
String week2Monday    = week2WorkingDays.get(0);
String week2Tuesday   = week2WorkingDays.get(1);
String week2Wednesday = week2WorkingDays.get(2);

// Old & new weekoff verification
String oldWeekoffDate = week2Saturday;
String newWeekoffDate = week2Sunday;

// Collect all Mondays (optional)
List<String> allMondays = Arrays.asList(
    week1WorkingDays.get(0),
    week2WorkingDays.get(0),
    week3WorkingDays.get(0),
    week4WorkingDays.get(0)
);


*/

public static Map<String, Object> getLast2ndMonthWeekendAndWorkingDetails() {
    Map<String, Object> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // --- Last 2nd Month ---
        LocalDate firstDay = today.minusMonths(2).withDayOfMonth(1);
        LocalDate lastDay = today.minusMonths(2).withDayOfMonth(today.minusMonths(2).lengthOfMonth());

        details.put("firstDayLast2ndMonth", firstDay.toString());
        details.put("lastDayLast2ndMonth", lastDay.toString());

        List<LocalDate> saturdays = new ArrayList<>();
        List<LocalDate> sundays = new ArrayList<>();

        LocalDate loop = firstDay;

        // --- Collect all Saturdays & Sundays of last 2nd month ---
        while (!loop.isAfter(lastDay)) {

            if (loop.getDayOfWeek() == DayOfWeek.SATURDAY)
                saturdays.add(loop);

            if (loop.getDayOfWeek() == DayOfWeek.SUNDAY)
                sundays.add(loop);

            loop = loop.plusDays(1);
        }

        // Store lists
        details.put("allSaturdays", saturdays);
        details.put("allSundays", sundays);

        // --- Saturday indexes ---
        details.put("1stSaturday", saturdays.size() >= 1 ? saturdays.get(0) : null);
        details.put("2ndSaturday", saturdays.size() >= 2 ? saturdays.get(1) : null);
        details.put("3rdSaturday", saturdays.size() >= 3 ? saturdays.get(2) : null);
        details.put("4thSaturday", saturdays.size() >= 4 ? saturdays.get(3) : null);

        // --- Sunday indexes ---
        details.put("1stSunday", sundays.size() >= 1 ? sundays.get(0) : null);
        details.put("2ndSunday", sundays.size() >= 2 ? sundays.get(1) : null);
        details.put("3rdSunday", sundays.size() >= 3 ? sundays.get(2) : null);
        details.put("4thSunday", sundays.size() >= 4 ? sundays.get(3) : null);

        // --- Working days (Mon for Sat, Tue for Sun) ---
        // Saturday +2 = Monday
        if (saturdays.size() >= 1) details.put("workingAfter1stSaturday", saturdays.get(0).plusDays(2));
        if (saturdays.size() >= 2) details.put("workingAfter2ndSaturday", saturdays.get(1).plusDays(2));
        if (saturdays.size() >= 3) details.put("workingAfter3rdSaturday", saturdays.get(2).plusDays(2));
        if (saturdays.size() >= 4) details.put("workingAfter4thSaturday", saturdays.get(3).plusDays(2));

        // Sunday +2 = Tuesday
        if (sundays.size() >= 1) details.put("workingAfter1stSunday", sundays.get(0).plusDays(2));
        if (sundays.size() >= 2) details.put("workingAfter2ndSunday", sundays.get(1).plusDays(2));
        if (sundays.size() >= 3) details.put("workingAfter3rdSunday", sundays.get(2).plusDays(2));
        if (sundays.size() >= 4) details.put("workingAfter4thSunday", sundays.get(3).plusDays(2));

    } catch (Exception e) {
        System.out.println("Error in getLast2ndMonthWeekendAndWorkingDetails: " + e.getMessage());
    }

    return details;
}


//last month
public static Map<String, String> getLastMonthWorkingDatesDetails() {

    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // --- LAST MONTH INFO ---
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate firstDay = lastMonth.withDayOfMonth(1);
        LocalDate lastDay = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());

        // UI Month Name (e.g., "December")
        String monthNameUI = firstDay.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        details.put("firstDayOfMonth", firstDay.toString());
        details.put("monthName", monthNameUI);
        details.put("year", String.valueOf(firstDay.getYear()));

        // --- Collect all working days ---
        List<LocalDate> workingDays = new ArrayList<>();

        LocalDate loop = firstDay;
        while (!loop.isAfter(lastDay)) {
            DayOfWeek dow = loop.getDayOfWeek();
            if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) {
                workingDays.add(loop);
            }
            loop = loop.plusDays(1);
        }

        // --- Store first 20 working dates ---
        for (int i = 0; i < 20; i++) {
            details.put("month" + (i + 1) + "WorkingDate",
                    i < workingDays.size() ? workingDays.get(i).toString() : null);
        }

    } catch (Exception e) {
        System.out.println("Error in getLastMonthWorkingDatesDetails: " + e.getMessage());
    }

    return details;
}




/*
Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

String month1WorkingDate  = dateDetails.get("month1WorkingDate");
String month2WorkingDate  = dateDetails.get("month2WorkingDate");
String month3WorkingDate  = dateDetails.get("month3WorkingDate");
String month4WorkingDate  = dateDetails.get("month4WorkingDate");
String month5WorkingDate  = dateDetails.get("month5WorkingDate");
String month6WorkingDate  = dateDetails.get("month6WorkingDate");
String month7WorkingDate  = dateDetails.get("month7WorkingDate");
String month8WorkingDate  = dateDetails.get("month8WorkingDate");
String month9WorkingDate  = dateDetails.get("month9WorkingDate");
String month10WorkingDate = dateDetails.get("month10WorkingDate");
String month11WorkingDate = dateDetails.get("month11WorkingDate");
String month12WorkingDate = dateDetails.get("month12WorkingDate");

*/


public static String convertToUIFormat(String date) {
    try {
        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dt = LocalDate.parse(date, inputFmt);
        return dt.format(uiFmt);

    } catch (Exception e) {
        System.out.println("Error converting date: " + date + " => " + e.getMessage());
        return null;
    }
}


//previous working days
public static Map<String, String> getPrevious12WorkingDates() {

    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();
        List<LocalDate> prevWorkingDays = new ArrayList<>();

        // Correct formatted month name (first letter capital)
        LocalDate firstDay = today.withDayOfMonth(1);
        String monthNameUI = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        LocalDate date = today.minusDays(1); // Start from yesterday

        // Collect previous 12 working days (Mon–Fri)
        while (prevWorkingDays.size() < 12) {
            DayOfWeek dow = date.getDayOfWeek();

            if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) {
                prevWorkingDays.add(date);
            }

            date = date.minusDays(1);
        }

        // Store in map: month1WorkingDate ... month12WorkingDate
        for (int i = 0; i < 12; i++) {
            details.put("month" + (i + 1) + "WorkingDate", prevWorkingDays.get(i).toString());
        }

        // Extra fields
        details.put("today", today.toString());
        details.put("firstDayOfMonth", firstDay.toString());
        details.put("monthName", monthNameUI);  
        details.put("year", String.valueOf(firstDay.getYear()));

    } catch (Exception e) {
        System.out.println("Error in getPrevious12WorkingDates: " + e.getMessage());
    }

    return details;
}




/*
Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();

String day1 = dateDetails.get("month1WorkingDate");
String day2 = dateDetails.get("month2WorkingDate");
String day3 = dateDetails.get("month3WorkingDate");
String day4 = dateDetails.get("month4WorkingDate");
String day5 = dateDetails.get("month5WorkingDate");
String day6 = dateDetails.get("month6WorkingDate");
String day7 = dateDetails.get("month7WorkingDate");
String day8 = dateDetails.get("month8WorkingDate");
String day9 = dateDetails.get("month9WorkingDate");
String day10 = dateDetails.get("month10WorkingDate");
String day11 = dateDetails.get("month11WorkingDate");
String day12 = dateDetails.get("month12WorkingDate");


*/


//last month mon to friday
public static Map<String, String> getLastMonthMonToFriWeek() {

    Map<String, String> details = new HashMap<>();

    try {
        LocalDate today = LocalDate.now();

        // LAST MONTH
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate firstDay = lastMonth.withDayOfMonth(1);
        LocalDate lastDay = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());

        // --- Find first Monday of last month ---
        LocalDate firstMonday = firstDay;
        while (firstMonday.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMonday = firstMonday.plusDays(1);
        }

        // Friday = Monday + 4 days
        LocalDate correspondingFriday = firstMonday.plusDays(4);

        // Store values
        details.put("mondayDate", firstMonday.toString());
        details.put("fridayDate", correspondingFriday.toString());
        details.put("monthName", firstDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        details.put("year", String.valueOf(firstDay.getYear()));

    } catch (Exception e) {
        System.out.println("Error in getLastMonthMonToFriWeek: " + e.getMessage());
    }

    return details;
}


//get weekcount
public static int getLastMonthWeekNumber(String dateKey) {

    try {
        // Get all last month working dates using your existing method
        Map<String, String> details = getLastMonthWorkingDatesDetails();

        // Get date from map using the passed key
        String dateStr = details.get(dateKey);

        if (dateStr == null) {
            System.out.println("❌ No date found for key: " + dateKey);
            return -1;
        }

        LocalDate date = LocalDate.parse(dateStr);

        // Ensure it belongs to LAST MONTH only
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);

        if (date.getMonthValue() != lastMonth.getMonthValue()) {
            System.out.println("❌ Provided date does not belong to last month!");
            return -1;
        }

        // Calculate week number inside LAST MONTH
        LocalDate firstDayOfLastMonth = lastMonth.withDayOfMonth(1);

        int weekNumber = ((date.getDayOfMonth() - 1) / 7) + 1;

        return weekNumber;

    } catch (Exception e) {
        System.out.println("Error in getLastMonthWeekNumber: " + e.getMessage());
        return -1;
    }
}









    
    

        public static String getToken() {
            return token;
        }

        public static void setToken(String newToken) {
            token = newToken;
        }
    

    
    
}
