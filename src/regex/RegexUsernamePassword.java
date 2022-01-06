package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUsernamePassword {
    public static final String USERNAME_PASSWORD_REGEX = "^[a-z0-9]{8,16}$";
    private static final Pattern pattern = Pattern.compile(USERNAME_PASSWORD_REGEX);

    public static boolean validateUserName(String userName){
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
    public static boolean validatePassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
