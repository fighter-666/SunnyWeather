package com.example.sunnyweather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilTime {

   /**
    * String 转  date
    *
    * @param strTime    要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    * @param formatType strTime的时间格式必须要与formatType的时间格式相同
    * @return
    * @throws ParseException
    */
   public static Date stringToDate(String strTime, String formatType)
           throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat(formatType);
      Date date = formatter.parse(strTime);
      return date;
   }

   /**
    * 时间合法性验证
    *
    * @param startTime    日期字符串
    * @param endTime    日期字符串
    * @return
    * @throws ParseException
    */
   public static Boolean isAvailableTime(String startTime, String endTime) {
      try {
         if (!UtilText.isEmptyOrNull(startTime) && !UtilText.isEmptyOrNull(endTime)) {
            Date startData = UtilTime.stringToDate(startTime, "HH:mm");
            Date endData = UtilTime.stringToDate(endTime, "HH:mm");

            Calendar start2 = Calendar.getInstance();
            start2.set(Calendar.HOUR_OF_DAY, startData.getHours());
            start2.set(Calendar.MINUTE, startData.getMinutes());

            Calendar end2 = Calendar.getInstance();
            end2.set(Calendar.HOUR_OF_DAY, endData.getHours());
            end2.set(Calendar.MINUTE, endData.getMinutes());

            Calendar nowData = Calendar.getInstance();

            return nowData.after(start2) && nowData.before(end2);
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return false;
   }
}
