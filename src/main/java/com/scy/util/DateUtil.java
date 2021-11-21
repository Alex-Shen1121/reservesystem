package com.scy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 根据日期获取 星期 （2019-05-06 ——> 星期一）
     * @param datetime
     * @return
     */
    public static int  dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        int[] weekDays = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 获取日期间日期
     * 获取当前月份每日的日期返回一个月内日期集合对象
     */
    public  static List<Date> getBetweenDates()  {
        List<Date> list = new ArrayList<Date>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = String.valueOf(year)+"-"+month+"-"+i;
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(aDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(date);
        }
        return list;
    }

}
