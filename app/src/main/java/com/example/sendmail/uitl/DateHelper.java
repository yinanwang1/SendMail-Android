package com.example.sendmail.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

/**
 * Created by paul on 16/1/13.
 *
 * 日期基础库
 */
public class DateHelper {
    public static final String FORMAT_Y = "yyyy";
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_T_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyydotMMdotdd = "yyyy.MM.dd";
    public static final String MMstr_ddstr = "M月d日";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String MM_dd_HH_mm = "MM-dd HH:mm";

    private DateHelper() {
    }

    public static String convert(long times, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
            return format.format(new Date(times));
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDate(long l) {
        Date date = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(date);
    }

    public static String formatDate(long l, String format) {
        Date date = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 毫秒值转时分秒  格式  HH:mm:ss
     *
     * @return  时间格式字符串
     */
    public static String convertMillis2Time(long millis) {
        return String.format(Locale.CHINA ,"%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    /**
     * 毫秒值转分秒  格式  mm:ss
     *
     * @return 时间格式字符串
     */
    public static String convertMillis2Min(long millis) {
        return String.format(Locale.CHINA, "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis)
                , TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }


    public static String convertToNormal(String serverTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm", Locale.CHINA);
        Date date;
        try {
            date = simpleDateFormat.parse(serverTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.CHINA);

        return simpleDateFormat2.format(date);
    }

    public static String convertToServer(String serverTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
        Date date;
        try {
            date = simpleDateFormat.parse(serverTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmm", Locale.CHINA);
        return simpleDateFormat2.format(date);
    }

    public static String getFormatDateSecond(long longDate, String formatStr) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(longDate * 1000);
        return new SimpleDateFormat(formatStr, Locale.getDefault()).format(gc.getTime());
    }

    public static int calcEnterSchoolYears(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_Y, Locale.CHINA);
        String strCurYears = dateFormat.format(date);

        return Integer.valueOf(strCurYears);
    }

    public static String getFormatDate(long dateLong, String formatString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatString, Locale.CHINA);
            Date date = new Date(dateLong);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 返回 xx月xx日
     *
     * @param dateLong  时间
     *
     * @return 返回时间格式字符串
     */
    public static String getFormatDateByMStrDStr(long dateLong) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(MMstr_ddstr, Locale.CHINA);
            Date date = new Date(dateLong);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getFormatString(Date date, String formatStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr, Locale.CHINA);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDateOfWeekStr(Calendar calendar) {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        switch (week) {
            case Calendar.SUNDAY: {
                weekStr = "星期天";
            }
            break;

            case Calendar.MONDAY: {
                weekStr = "星期一";
            }
            break;

            case Calendar.TUESDAY: {
                weekStr = "星期二";
            }
            break;

            case Calendar.WEDNESDAY: {
                weekStr = "星期三";
            }
            break;

            case Calendar.THURSDAY: {
                weekStr = "星期四";
            }
            break;

            case Calendar.FRIDAY: {
                weekStr = "星期五";
            }
            break;

            case Calendar.SATURDAY: {
                weekStr = "星期六";
            }
            break;
        }

        return weekStr;
    }

    /**
     * 返回消息中心需要显示的时间
     *
     * @param dateLong 单位为秒
     *
     * @return 时间格式字符串
     */
    public static String getMessageTimeType(long dateLong) {
        Date date = new Date(dateLong * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Date curDate = new Date();
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(curDate);

        int year = curCalendar.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int day = curCalendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);

        if (0 >= abs(year)) {
            if (day < 1) { // 小于一天，也就是今天
                return getFormatString(date, "HH:mm");
            } else if (day < 2) {
                return "昨天 " + getFormatString(date, "HH:mm");
            } else if (day < 5) {
                String week = getDateOfWeekStr(calendar);
                String tempTime = getFormatString(date, "HH:mm");
                return week + " " + tempTime;
            } else {
                return getFormatString(date, "MM月dd日 HH:mm");
            }
        } else {
            return getFormatString(date, "yyyy年MM月dd日 HH:mm");
        }
    }

    public static String getTaskTime(long dateLong, boolean isFinish) {

        Date date = new Date(dateLong * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Date curDate = new Date();
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(curDate);
        long diff = curDate.getTime() - date.getTime();//这样得到的差值是微秒级别
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        int year = (curCalendar.get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
        long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//        int day = (curCalendar.get(calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH));
//        int hour = (curCalendar.get(calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY));
        if (0 >= abs(year)) {
            if (hour < 1) { // 小于1小时
                String sStr = s < 10 ? "0" + s : s + "";
                String minStr = min < 10 ? "0" + min : min + "";
                return isFinish ? minStr + "分" : minStr + ":" + sStr;
            } else if (hour == 1 && min == 0 && s == 0) {
                return isFinish ? "1小时" : "1:00:00";
            } else if (hour >= 1 && (min > 0 || s > 0)) {
                String minStr = min < 10 ? "0" + min : min + "";
                String sStr = s < 10 ? "0" + s : s + "";
                return isFinish ? hour + "小时" + minStr + "分" : hour + ":" + minStr + ":" + sStr;
            } else {
                String sStr = s < 10 ? "0" + s : s + "";
                String minStr = min < 10 ? "0" + min : min + "";
                return isFinish ? hour + "小时" + minStr + "分" : hour + ":" + minStr + ":" + sStr;
            }
        } else {
            String minStr = min < 10 ? "0" + min : min + "";
            String sStr = s < 10 ? "0" + s : s + "";
            return isFinish ? hour + "小时" + minStr + "分" : minStr + ":" + minStr + ":" + sStr;
        }
    }


    // 记录错误日志记录
    /** yyyy-MM-dd HH:mm:ss�ַ��� */

    public static String getOtherDay(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, diff);
        return getDateFormat(mCalendar.getTime());
    }

    public static String getDateFormat(Date date) {
        return dateSimpleFormat(date, defaultDateFormat.get());
    }

    public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if (format == null)
            format = defaultDateTimeFormat.get();
        return (date == null ? "" : format.format(date));
    }

    /** yyyy-MM-dd HH:mm:ss��ʽ */
    public static final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(yyyy_MM_dd_T_HH_mm_ss, Locale.CHINA);
        }

    };

    /** yyyy-MM-dd��ʽ */
    public static final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(FORMAT_YMD, Locale.CHINA);
        }

    };



    // 记录错误日志记录  __END__
}
