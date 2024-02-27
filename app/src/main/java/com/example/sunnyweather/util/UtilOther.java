package com.example.sunnyweather.util;

public class UtilOther {

   /**
    * 强制转换为int
    * @param number
    * @return
    */
   public static int parseInt(String number){
      int num;
      try {
         num = Integer.parseInt(number);
      } catch (Exception e) {
         e.printStackTrace();
         return 0;
      }
      return num;
   }

   public static Integer parseInteger(String number){
      int num;
      try {
         num = Integer.parseInt(number);
      } catch (Exception e) {
         e.printStackTrace();
         return 0;
      }
      return num;
   }
}
