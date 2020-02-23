package com.hhr.lazyread.util;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Harry
 * @Date: 2019-10-13 17:31
 * @Version 1.0
 */

@Component
public class DateUtil {

    public static final long ONE_DAY = 24 * 60 * 60 * 1000;

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String LDATETIME_FORMAT = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String LLDATETIME_FORMAT = "yyyyMMddHHmmssSSS";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * yyyyMMdd
     */
    public static final String LDATE_FORMAT = "yyyyMMdd";
    /**
     * HHmmss
     */
    public static final String LSHORTTIME_FORMAT = "HHmmss";
    /**
     * HH:mm:ss
     */
    public static final String SHORTTIME_FORMAT = "HH:mm:ss";
    public static final String YEAR_MONTH_FORMAT = "yyyyMM";
    private static final ThreadLocal<SimpleDateFormat> dateTimeThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DATETIME_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> lDateTimeThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(LDATETIME_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> llDateTimeThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(LLDATETIME_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> dateThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DATE_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> lDateThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(LDATE_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> lShortTimeThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(LSHORTTIME_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> shortTimeThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(SHORTTIME_FORMAT);
                }
            };
    private static final ThreadLocal<SimpleDateFormat> yearMonthFormatThreadLocal =
            new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(YEAR_MONTH_FORMAT);
                }
            };

    public  SimpleDateFormat yesygetYearMonthDate() {
        return yearMonthFormatThreadLocal.get();
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public int getLdate(Date date) {
        return Integer.parseInt(formatLShortDate(date));
    }

    /**
     * @param date
     * @return HHmmss
     */
    public int getLShortTime(Date date) {
        return Integer.parseInt(formatLShortTime(date));
    }

    /**
     * @param date
     * @return yyyyMMddHHmmss
     */
    public long getLDateTime(Date date) {
        return Long.parseLong(formatLDateTime(date));
    }

    public boolean isBefore(Date date1, Date date2) {
        return date2.getTime() - date1.getTime() > 0;
    }

    private Date add(Date date, int zoom, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (amount == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(zoom, amount);
        return cal.getTime();
    }

    public Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    public Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public  Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    private  Date paser(DateFormat format, String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误:" + dateString);
        }
    }

    public  String format(DateFormat format, Date date) {
        return format.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public  Date parseDateTime(String date) {
        return paser(dateTimeThreadLocal.get(), date);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public  Date parseDate(String date) {
        return paser(dateThreadLocal.get(), date);
    }

    public  Date parseDate(String date, String format) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return paser(new SimpleDateFormat(format), date);
    }

    /**
     * @param date
     * @return HH:mm:ss
     */
    public  String formatShortTime(Date date) {
        return format(shortTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public  String formatDateTime(Date date) {
        return format(dateTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd
     */
    public  String formatDate(Date date) {
        return format(dateThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return format
     */
    public  String formatDate(Date date, String format) {
        return format(new SimpleDateFormat(format), date);
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public  String formatLDate(Date date) {
        return format(lDateThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyyMMddHHmmss
     */
    public  String formatLDateTime(Date date) {
        return format(lDateTimeThreadLocal.get(), date);
    }


    /**
     * @param date
     * @return yyyyMMddHHmmssSSS
     */
    public  String formatLLDateTime(Date date) {
        return format(llDateTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return HHmmss
     */
    public  String formatLShortTime(Date date) {
        return format(lShortTimeThreadLocal.get(), date);
    }

    /**
     * @param date
     * @return yyyyMMdd
     */
    public  String formatLShortDate(Date date) {
        return format(lDateThreadLocal.get(), date);
    }

    public  Date yesterdayStart() {
        return new Date(todayStart().getTime() - ONE_DAY);
    }

    public  Date yesterdayEnd() {
        return new Date(todayStart().getTime() - 1);
    }

    public  boolean isYesterday(Date startDay) {
        boolean isYesterday = false;
        Date yesterdayStart = yesterdayStart();
        Date yesterdayEnd = yesterdayEnd();
        if (yesterdayStart.getTime() <= startDay.getTime()
                && yesterdayEnd.getTime() >= startDay.getTime()) {
            isYesterday = true;
        }
        return isYesterday;
    }

    /**
     * The very beginning of today.
     *
     * @return
     */
    public  Date todayStart() {
        return dayStart(new Date());
    }

    public  Date todayEnd() {
        return dayEnd(new Date());
    }

    public  Date dayStart(Date someday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(someday);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 时间间隔(天数),date1-date2
     *
     * @param date1
     * @param date2
     * @return 负数表示date1在date2前
     */
    public  int intervalDays(Date date1, Date date2) {
        Calendar cNow = DateUtils.toCalendar(dayStart(date1));
        Calendar cReturnDate = DateUtils.toCalendar(dayStart(date2));
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / ONE_DAY);
    }

    public  Date dayEnd(Date someday) {
        return new Date(dayStart(someday).getTime() + ONE_DAY - 1);
    }

    public  Date dayEnd2(Date someday) {
        Calendar calendar = DateUtils.toCalendar(someday);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    public  Date zero() {
        return new Date(0);
    }

    public  Date firstDayOfMonth(Date date) {
        Calendar calendar = DateUtils.toCalendar(dayStart(date));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public  boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public  Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public  Date yesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public Date getNow(){
        return new Date(System.currentTimeMillis());
    }
}