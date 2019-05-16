package pl.edu.pw.mini.core.tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static LocalDate toLocalDate(Date input) {
        return input.toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
    }

    public static Date toDate(LocalDate input) {
        return Date.from(input.atStartOfDay(ZoneId.of("GMT")).toInstant());
    }
}
