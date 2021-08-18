package com.mall.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String getDate(String format) {
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern(format));
    }

}
