package com.example.sunnyweather.base;

import com.example.sunnyweather.util.UtilText;

public class UtilEncryption {
   /**
    * 位运算加密
    * @param string
    * @return
    */
   public static String encrypt(String string) {
      if(!UtilText.isEmptyOrNull(string)){
         char[] chars = string.toCharArray();
         char ch;
         StringBuilder result = new StringBuilder();
         for (char aChar : chars) {
            ch = aChar;
            result.append((char) (ch + 2));
         }
         return result.toString();
      }else {
         return "";
      }
   }

   /**
    * DES 加密
    * 注意，其中key的长度为32位
    * @author zhuofq
    * @param para
    * @return	String
    * @throws Exception
    */
   public static String encode(String para) throws Exception {
      return encode(Variable.mSK, para);
   }

   /**
    * 对字符串进行解密
    *
    * @author zhuofq
    * @param para
    * @return	String
    * @throws Exception
    */
   public static String decode(String para) throws Exception {
      return decode(Variable.mSK, para);
   }
}
