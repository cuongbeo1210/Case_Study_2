package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPhoneNumber {
    public static final String PHONE_NUMBER_REGEX = "^(84|0[1-9])+([0-9]{8})$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);

    public static boolean validate(String phoneNumber){
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
