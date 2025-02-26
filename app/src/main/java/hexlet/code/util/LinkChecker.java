package hexlet.code.util;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkChecker {

    private static final Set<String> VALID_TLDS = Set.of(
            "com", "org", "net", "edu", "gov", "mil", "int", "ru", "us", "uk", "fr", "de", "cn",
            "br", "in", "jp", "kr", "ca", "au", "it", "es", "nl", "se", "no", "fi", "dk", "io"
    );

    public static boolean isLinkValid(String text) throws Exception {

        Pattern pattern = Pattern.compile(
                "^(https?://(?:[\\w-]+\\.)+([a-zA-Z]{2,6})|localhost)(?::\\d{1,5})?(?:/.*)?$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            throw new Exception("Некорректный URL");
        }

        String tld = matcher.group(2);
        if (tld == null || !VALID_TLDS.contains(tld.toLowerCase())) {
            throw new Exception("Некорректный домен");
        }

        return true;
    }
}
