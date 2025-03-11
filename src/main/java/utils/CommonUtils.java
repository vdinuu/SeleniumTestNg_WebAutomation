package utils;/*
 * @created 11/03/2025 - 2:34 PM
 * @author Dinu
 */

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CommonUtils {
    /**
     * Generates a random number within a specified size range.
     */
    public static String getRandomNumber(int size){
        Random random = new Random();
        List<Integer> randomSingleDigits = Stream.generate(() -> random.nextInt(10))
                .limit(size)
                .toList();
        StringBuilder result = new StringBuilder();
        randomSingleDigits.forEach(result::append);
        return result.toString();
    }

    /** Generates a random string of alphabets and number (uppercase or lowercase) of a specified size. */
    public static String getRandomAlphaNumeric(int size, boolean isLower){
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"+"1234567890";
        StringBuffer stringBuffer = new StringBuffer(size);
        for(int y=0; y<size; y++){
            int index = (int) (alphaString.length()*Math.random());
            stringBuffer.append(alphaString.charAt(index));
        }
        return isLower? stringBuffer.toString().toLowerCase(): stringBuffer.toString().toUpperCase();
    }

    /** Generates a random email address. */
    public static String generateEmailId(){
        return getRandomAlphaNumeric(10, true)+"@gmail.com";
    }
}
