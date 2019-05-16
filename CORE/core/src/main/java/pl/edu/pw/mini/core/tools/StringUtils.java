package pl.edu.pw.mini.core.tools;

public class StringUtils extends org.springframework.util.StringUtils {

    public static boolean notEmpty (String value) {
        return ! org.springframework.util.StringUtils.isEmpty(value);
    }
    public static boolean anyEquals(String s1, String... s2) {
        for(String str: s2) {
            if(s1.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
