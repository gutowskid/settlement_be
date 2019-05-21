package pl.edu.pw.mini.core.tools;

import lombok.extern.log4j.Log4j;

@Log4j
public class MockLogger {
    private static final boolean THROW_EXCEPTION = false;

    public static String getString(String s) {
        if(THROW_EXCEPTION) {
            throw new RuntimeException("MOCKLOGGER!");
        }
        log.warn("MOCKLOGGER");
        return s;
    }

    public static Long getLong(Long l) {
        if(THROW_EXCEPTION) {
            throw new RuntimeException("MOCKLOGGER!");
        }
        log.warn("MOCKLOGGER");
        return l;
    }
}
