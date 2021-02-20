package com.gcnbl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

@Deprecated
public class DateUtils {
    //时间格式
    public  static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /***
     * 时间格式转换
     * @param date
     * @return
     */
    public static String dateToStr1(Date date){
        return simpleDateFormat1.format(date);
    }
}