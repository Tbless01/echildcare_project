package semicolon.africa.echildcarebackend.services.parent.helperClasses;

import java.util.Scanner;

public  class CardValidation {

    public static boolean isDebitCardValid(String debitCardNumber) {

        if (!debitCardNumber.matches("\\d+")) {
            return false;
        }
        int sum = 0;
        boolean result = false;
        for (int i = debitCardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(debitCardNumber.charAt(i));
            if (result) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            result = true;
        }
        return sum % 10 == 0;
    }
}
