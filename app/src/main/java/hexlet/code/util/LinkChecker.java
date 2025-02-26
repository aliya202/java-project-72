package hexlet.code.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkChecker {

    public static boolean isLinkValid(String text) throws Exception {

        Pattern pattern = Pattern.compile(
                "^(https?://(?:(?:[\\w-]+\\.)+[a-zA-Z]{2,6}|localhost)(?::\\d{1,5})?(?:/.*)?)$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return true;
        } else {
            throw new Exception();
        }
    }
}
