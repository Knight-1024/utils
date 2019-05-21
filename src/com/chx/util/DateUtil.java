package com.chx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CHX on 2019/5/21.
 */
public class DateUtil {

    /**
     * 判断连续的几个日期是否为连续的
     * @param dates 必须是从小到大排序的Date数组
     * @param intervalMills 间隔的时间标准，以毫秒为单位
     * @return
     */
    public static boolean isContinuous(long intervalMills, Date... dates) {
        if (dates == null || dates.length < 2) {
            return false;
        }

        for (int i = 0; i < dates.length - 1; i++) {
            if (dates[i] == null) {
                return false;
            }
            Date adate = dates[i];
            Date bdate = dates[i+1];
            long atime = adate.getTime();
            long btime = bdate.getTime();

            if (btime - atime > intervalMills) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断连续的几个日期是否为连续的日期，标准为连续的天
     * @param dates
     * @return
     */
    public static boolean isContinuousDay(Date... dates) {
        if (dates == null || dates.length < 2) {
            return false;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < dates.length - 1; i++) {
            Date adate = dates[i];
            Date bdate = dates[i+1];

            Calendar c1 = GregorianCalendar.getInstance();
            c1.setTime(adate);
            c1.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH) + 1);

            Calendar c2 = GregorianCalendar.getInstance();
            c2.setTime(bdate);

            Date d1 = c1.getTime();
            Date d2 = c2.getTime();

            if (!format.format(d1).equals(format.format(d2))) {
                return false;
            }
        }
        return true;
    }

    public static Date add(Date date, int field, int value) {
        if (date == null) {
            return null;
        }

        Calendar c1 = GregorianCalendar.getInstance();
        c1.setTime(date);
        c1.add(field, value);

        return c1.getTime();
    }

    /**
     * 获取系统当前年
     * @return
     */
    public static String getDateYear() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.YEAR) + "";
    }

    /**
     * 获取系统当前月
     * @return
     */
    public static String getDateMonth() {
        Calendar calendar = GregorianCalendar.getInstance();
        return (calendar.get(Calendar.MONTH) + 1) + "";
    }

    /**
     * 获取系统当前日
     * @return
     */
    public static String getDateDay() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "";
    }


    /**
     * 获取当前时间
     * @param type 格式,例如：yyyyMMddHHmmss，不传参数默认为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateTimeStr(String... type) {
        String ss = "yyyy-MM-dd HH:mm:ss";
        if(type.length > 0) ss = type[0];
        return new SimpleDateFormat(ss).format(new Date());
    }

    /**
     * 获取当前时间
     * @param type 格式,例如：yyyyMMddHHmmss，不传参数默认为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateTimeStr(Long time) {
        String ss = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(ss).format(new Date(time));
    }

    /**
     * 计算两时间之间的周间隔，
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getWeekInterval(Date startDate, Date endDate) {
        int betweenWeeks = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(startDate);
        c2.setTime(endDate);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(startDate);
        }

        int y1 = c1.get(Calendar.YEAR);
        int y2 = c2.get(Calendar.YEAR);

        int w1 = c1.get(Calendar.WEEK_OF_YEAR);
        int w2 = c2.get(Calendar.WEEK_OF_YEAR);

        betweenWeeks += (w2 - w1);
        int betweenYears = y2 - y1;
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenWeeks += c1.getMaximum(Calendar.WEEK_OF_YEAR);
        }

        return betweenWeeks;
    }

    /*****
     * 两日期相减(最好用)
     * @param begin_date
     * @param end_date
     * @return
     */
    public static long getTwoDay(Date begin_date, Date end_date) {
        long day = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String sdate = format.format(Calendar.getInstance().getTime());

            if (begin_date == null) {
                begin_date = format.parse(sdate);
            }
            if (end_date == null) {
                end_date = format.parse(sdate);
            }
            day = (end_date.getTime() - begin_date.getTime())
                    / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return day;
    }

    /**
     * 格式化时间
     * @param date
     * @param type
     * @return
     */
    public static String format(Date date, String type){
        if(StringUtil.isEmpty(type)) type = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
    
    /**
     * 格式化时间
     * @param date
     * @param type
     * @return
     */
    public static Date format(String date, String type) throws Exception{
        if(StringUtil.isEmpty(type)) type = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.parse(date);
    }

    /**
     * 获取当前时间
     * @param type 格式,例如：yyyyMMddHHmmss，不传参数默认为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getDateTime(String type)  {
        try {
            String ss = "yyyy-MM-dd";
            SimpleDateFormat sf = new SimpleDateFormat(ss);
            return sf.parse(type);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 获得当月的第一天
     * 默认(yyyy-MM-dd)
     * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
     */
    public static String getFirstDayOfMonth(String formatStr){
        if(StringUtil.isEmpty(formatStr)){
            formatStr = "yyyy-MM-dd";
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
        String str=sdf.format(cal.getTime());
        return str;
    }

}
