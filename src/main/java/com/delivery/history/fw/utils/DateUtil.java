package com.delivery.history.fw.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * DATE Util 함수
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public class DateUtil {

    /**
     * LocalDateTime -> String으로 형변환
     *
     * @param {LocalDateTime} date
     * @return String
     */
    public static String formatDateTime(LocalDateTime date) {

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * String -> LocalDateTime 형변환
     *
     * @param {String} date
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String date) {

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * String 형식이 시간이 없는 날짜 형식일 시,
     * String -> LocalDateTime 으로 형변환 및 시간 세팅
     *
     * @param {String} date
     * @return LocalDateTime
     */
    public static LocalDateTime getDateTime(String date) {

        return LocalDate
                .parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
                .atStartOfDay();
    }

}
