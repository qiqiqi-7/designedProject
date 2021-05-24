package cn.edu.hbpu.education.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private  DateFormatUtil(){
    }
    private static final SimpleDateFormat sdf = new SimpleDateFormat();
    public static String dateToString(Date date,String pattern){
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }
}
