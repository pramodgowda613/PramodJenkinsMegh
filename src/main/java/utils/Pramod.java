package utils;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Pramod {
	
	 private static String token;

    private boolean selectDropdowns(WebElement dropdownElement, String selectionType, String value) {
        try {
            Select dropdown = new Select(dropdownElement);

            switch (selectionType.toLowerCase()) {
                case "visibletext":
                    dropdown.selectByVisibleText(value);
                    break;
                case "value":
                    dropdown.selectByValue(value);
                    break;
                case "index":
                    int index = Integer.parseInt(value);
                    dropdown.selectByIndex(index);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid selection type: " + selectionType);
            }

            return true;

        } catch (Exception e) {
       //     MeghLoginPage.setExceptionDesc("Dropdown selection failed. Type: " + selectionType +
         //           ", Value: " + value + " | Error: " + e.getMessage());
            return false;
        }
    }
    
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

   
  

   
       

        public static String getToken() {
            return token;
        }

        public static void setToken(String newToken) {
            token = newToken;
        }
    

    
    
}
