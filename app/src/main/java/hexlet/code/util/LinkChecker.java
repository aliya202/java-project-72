package hexlet.code.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkChecker {

    public static boolean isLinkValid(String text) throws Exception {
        Pattern pattern = Pattern.compile(
                "^(https?://)" +
                        "(?:" +
                        "(localhost(?::\\d{1,5})?)" +
                        "|" +
                        "((?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+" +
                        "(?:ru|com|org|net|edu|gov|io)" +
                        "(?::\\d{1,5})?" +
                        ")" +
                        ")" +
                        "(/.*)?$");
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            throw new Exception("Некорректный URL");
        }

        return true;
    }
}
