package com.example.sunnyweather.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sunnyweather.R;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @author zhuofq
 * @createTime 2014-12-3 下午11:39:46
 */
public class UtilText {
    private static String SORT_KEY = "sort_key";
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, SORT_KEY};

    /**
     * 去除字符串中所有的空格
     *
     * @param res
     * @return
     */
    public static String trim(String res) {

        if (!TextUtils.isEmpty(res)) {
            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = pattern.matcher(res);
            res = matcher.replaceAll("");
            return res;
        }
        return res;
    }

    /**
     * 禁止EditText输入空格和换行符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpace(EditText editText) {
        InputFilter filter = new InputFilter()  {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6),filter});
    }


    /**
     * 将字符串以一个空格来分割
     *
     * @param str
     * @return String[]
     * @author zhuofq
     */
    public static String[] splitBySpace(String str) {
        return str.split(" ");
    }

    /**
     * 判断一个字符串是否都为字母
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isLetters(String str) {
        return str.matches("^[A-Za-z]+$");
    }

    /**
     * 得到全拼或简拼
     *
     * @param str  字符串
     * @param type 全拼还是简拼
     * @return String
     * @author zhuofq
     */
    public static String getString(String str, int type) {
        str = str.replaceAll("   ", "");
        String[] strs = splitBySpace(str);

        String[] newStrs = new String[strs.length];
        int j = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() > 0) {
                String firstLetter = strs[i].substring(0, 1);
                if (isLetters(firstLetter)) {
                    // type=0 out jp
                    if (type == 0) {
                        newStrs[j] = firstLetter;
                    }
                    // type=1 out qp
                    else {
                        newStrs[j] = strs[i];
                    }
                    j++;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < newStrs.length; k++) {
            if (newStrs[k] != null) {
                sb.append(newStrs[k]);
            }
        }
        return sb.toString();
    }

    /***
     * 测量字符串长度
     * @param textView
     * @param text
     * @return
     */
    public static int measureText(TextView textView, String text) {
        TextPaint paint = textView.getPaint();
        return (int) paint.measureText(text);
    }

    /**
     * 获取截取后的文本
     * @param length 截取的长度
     * @param text
     * @return
     */
    public static String getSubstring(int length, String text) {
        if(!UtilText.isEmptyOrNull(text)) {
            if (text.length() > length) {
                text = text.substring(0, length);
            }
        }
        return text;
    }



    /**
     * 获取当前省市，判断是否“西藏”或"新疆"
     *
     * @param mContext
     * @return boolean
     * @author jiangwenxin
     * @createTime 2014年8月14日 下午4:26:00
     */
    public static boolean isProvinceXJorXZ(Context mContext) {
        // CitysManager mCitysManager = new CitysManager(mContext);
        // String sid = Toolkits.getSID(mContext);
        // if (sid!=null){
        // CityInfo mCityInfo = mCitysManager.getCityBySidCodeCode(sid);
        // if(mCityInfo!=null && ("西藏自治区".equals(mCityInfo.getProvName()) ||
        // "新疆维吾尔自治区".equals(mCityInfo.getProvName()))){
        // return true;
        // }
        // }
        // zhuofq 4.5紧急发布前屏蔽.
        return false;
    }

    /**
     * 去掉小数后面的0
     *
     * @param old
     * @return String
     * @author zhangyi
     * @createTime 2014-12-1 上午9:56:23
     */
    public static String remove0(String old) {
        try {
            double num = Double.parseDouble(old);
            int intnum = (int) num;
            if (num == intnum) {
                return String.valueOf(intnum);
            } else {
                return String.valueOf(num);
            }
        } catch (Exception e) {
            return old;
        }
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断input是否空指针,"", "null"
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmptyOrNull(String str) {
        return TextUtils.isEmpty(str) || str.trim().toLowerCase(Locale.US).equals("null");
    }

    public static boolean isEmptyOrNullString(String str) {
        if (TextUtils.isEmpty(str) || str.trim().toLowerCase(Locale.US).equals("null") || str.trim().length() == 0){
            return true;
        }
        return false;
    }



    /**
     * 设置编辑
     *
     * @param etText
     * @param content
     */
    public static void setEtText(EditText etText, String content) {
        if (!UtilText.isEmptyOrNull(content)) {
            etText.requestFocus();
            etText.setText(content);
            if (etText.getText().toString().length() >= content.length()) {
                etText.setSelection(content.length());
            }
        }
    }

    /**
     * 余额单位转换保留小数点后两位。话费超十万的，显示到个位四舍五入。
     *
     * @param fee 话费
     * @return
     * @author zhangyi
     * @createTime 2014年9月22日 下午3:32:40
     */
    public static String getFee(String fee) {
        String strFee = "";
        Double douFee;
        try {
            douFee = Double.valueOf(fee);
            if ((douFee / 100000) < 1) {// 话费不超过十万，显示接口返回数据
                strFee = String.format("%.2f", douFee);
            } else if ((douFee / 100000) >= 1) {// 话费超十万的，显示到个位
                strFee = String.format("%.0f", douFee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFee;
    }

    /**
     * url 按utf-8进行编码
     *
     * @param str
     * @return String
     * @author zhuofq
     */
    public static String urlDecoder(String str) {
        URLDecoder ud = new URLDecoder();

        try {
            return ud.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * url 按utf-8进行编码
     *
     * @param str
     * @return String
     * @author zhuofq
     */
    public static String urlEncoder(String str) {

        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 高亮显示手机号码的某些部分
     *
     * @param input
     * @param hlstart
     * @param hlend
     * @return SpannableString
     * @author zhuofq
     */
    public static SpannableString splitPhoneNumber(String input, int hlstart,
                                                   int hlend) {
        if (input == null || input.length() != 11) {
            return new SpannableString(input);
        }

        try {
            String splitNumbers = input.substring(0, 3) + " "
                    + input.substring(3, 7) + " " + input.substring(7, 11);
            SpannableString sp = new SpannableString(splitNumbers);
            int highStart = getActualPosition(hlstart);
            int highEnd = getActualPosition(hlend);
            if (highStart == -1 || highEnd == -1 || hlend <= hlstart) {
                return sp;
            }
            sp.setSpan(new ForegroundColorSpan(0xFFF16651), highStart, highEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
            return new SpannableString(input);
        }
    }

    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return 结果SpannableString
     */
    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        SpannableString s = new SpannableString(text);
        keyword = escapeExprSpecialWord(keyword);
        text = escapeExprSpecialWord(text);
        if (text.contains(keyword) && !UtilText.isEmptyOrNull(keyword)) {
            try {
                Pattern p = Pattern.compile(keyword);
                Matcher m = p.matcher(s);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (Exception e) {

            }
        }
        return s;
    }

    /**
     * 验证身份证号是否符合规则
     *
     * 我国当前的身份证号分为三种：
     * 一、15位身份证号
     * 二、18位身份证号（前17位位数字，最后一位为字母x）
     * 三、18为身份证号（18位都是数字）
     * @param text 身份证号
     * @return true 正确身份证
     */
    public static boolean personIdValidation(String text) {
        if (text.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return keyword
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!UtilText.isEmptyOrNull(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    /**
     * 获取实际位置
     *
     * @param position
     * @return
     * @author zhuofq
     */
    private static int getActualPosition(int position) {
        if (position >= 0 && position < 3) {
            return position;
        } else if (position >= 3 && position < 7) {
            return position + 1;
        } else if (position >= 7 && position < 12) {
            return position + 2;
        } else {
            return -1;
        }
    }

    /**
     * 中文字体加粗
     *
     * @param tv
     * @author zhuofq
     */
    public static void setTextBold(TextView tv) {
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 判断号码是否为电信号码
     *
     * @param input
     * @return boolean
     * @author zhuofq
     */
    public static boolean isCtPhoneNum(String input) {
        Pattern pattern = Pattern.compile("^1(33|53|80|81|89)\\d{8}$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 从url中获取文件名
     *
     * @param url
     * @return String
     * @author zhuofq
     */
    public static String parseFileNameFromUrl(String url) {
        if (url == null)
            return null;

        try {
            return url.substring(url.lastIndexOf("/") + 1, url.length());
        } catch (Exception e) {
            return null;
        }
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String TAG = "Toolkits";

    /**
     * 获取十六进制字符串
     *
     * @param b
     * @return String
     * @author zhuofq
     */
    public static String toHexString(byte[] b) {
        // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
//    private final static Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || input.toString().trim().length() == 0)
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否超出最大长度
     *
     * @param str
     * @param minLength
     * @return
     * @作者 Liujun
     * @创建时间 2015-10-26 上午9:53:32
     */
    public static boolean isOutMaxLength(String str, int minLength) {

        if (isEmpty(str) || str.length() < minLength) {
            return true;
        }

        return false;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return boolean
     * @author zhuofq
     */
    public static boolean isEmail(CharSequence email) {
        if (isEmpty(email))
            return false;
        return emailer.matcher(email).matches();
    }

//    /**
//     * 判断是不是一个合法的手机号码
//     */
//    public static boolean isPhone(CharSequence phoneNum) {
//        if (isEmpty(phoneNum))
//            return false;
//        return phone.matcher(phoneNum).matches();
//    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return int
     * @author zhuofq
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return int 转换异常返回 0
     * @author zhuofq
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return long 转换异常返回 0
     * @author zhuofq
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return double 转换异常返回 0
     * @author zhuofq
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return boolean 转换异常返回 false
     * @author zhuofq
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     *
     * @param str
     * @return boolean
     * @author zhuofq
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return String 转换后的结果。
     * @author zhuofq
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     * @author zhuofq
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }


    /**
     * 判断name的长度是否大于maxleng true：则返回 前maxLeng个字符，其他则已...表示
     *
     * @param name    需要判断的字符
     * @param maxLeng 字符显示的个数
     * @return String
     * @作者 Liujun
     * @创建时间 2015-8-4 上午11:02:52
     */
    public static String getShortName(String name, int maxLeng) {
        if (name == null)
            return "";

        String fixName = "";
        if (name.length() > 0 && name.length() <= maxLeng) {
            fixName = name;
        } else if (name.length() > maxLeng) {
            fixName = name.substring(0, maxLeng);
        }

        return fixName;
    }

    /**
     * 字符串替换函数
     *
     * @param string       被替换字符串
     * @param target       要替换的字段
     * @param value        替换的内容
     * @param defaultValue 默认值
     * @return
     * @作者 zhangyi
     * @创建时间 2015-8-27 下午2:58:16
     * @版本
     */
    public static String replaceString(String string, String target, String value, String defaultValue) {
        if (string != null && string.contains(target)) {
            if (UtilText.isEmptyOrNull(value))
                value = defaultValue;
            string = string.replace(target, value);
        }
        return string;
    }

    /**
     * 给字符串添加CDATA的壳防止在通讯的时候被XML转译
     *
     * @return
     * @作者 zhangyi
     * @创建时间 2015-12-10 下午12:40:45
     * @版本
     */
    public static String addCDATA(String string) {
        return "<![CDATA[" + string + "]]>";
    }


    /**
     * 对链接打开掌厅的URL做189.cn的正则匹配，符合条件的才允许打开
     * http://xxx.189.cn:8080/oooo；http://www.xxx.189.cn/aaa.bb.cc
     * http://www.xxx.xxxq.189.cn:8080/aaa.bb.cc；https://xxx.189.cn/oooo
     * 以上几种都是符合要求的
     *
     * @return
     * @作者 zhangyi
     * @创建时间 2016-07-05 下午19:38:45
     * @版本
     */
    public static boolean match189cn(String string) {
        Matcher matcher = Pattern.compile(
                "https?:(.+?)\\.189\\.cn(?::(\\d{2,5}))?/.*",
                Pattern.CASE_INSENSITIVE).matcher(string);
        return matcher.find();
    }

    /**
     * 对链接打开掌厅的URL做h5.bestpay.cn的正则匹配，符合条件的才允许打开
     * http://xxx.h5.bestpay.cn:8080/oooo；http://www.xxx.h5.bestpay.cn/aaa.bb.cc
     * http://www.xxx.xxxq.h5.bestpay.cn:8080/aaa.bb.cc；https://xxx.h5.bestpay.cn/oooo
     * 以上几种都是符合要求的
     *
     * @return
     * @作者 zhangyi
     * @创建时间 2016-07-05 下午19:38:45
     * @版本
     */
    public static boolean matchbestpaycn(String string) {
        Matcher matcher = Pattern.compile(
                "https?:(.+?)\\.h5\\.bestpay\\.cn(?::(\\d{2,5}))?/.*",
                Pattern.CASE_INSENSITIVE).matcher(string);
        return matcher.find();
    }

    /**
     * 格式化号码
     *
     * @param phone
     * @return
     */
    public static String toPhoneNumFormat(String phone) {
        if (UtilText.isEmptyOrNull(phone)) {
            return "";
        } else {
            return phone.replace("+86", "").replace("-", "").replace(" ", "");
        }
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 将字符串全角(处理文字排版问题)
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 保留两位小数
     *
     * @return
     */
    public static String keep2decimal(String num) {
        return String.format("%.2f", Double.parseDouble(num));
    }

    public static String changeF2Y(String amount) {
        try {
            Double lamount = Double.valueOf(amount);
            lamount = lamount / 100;
            return String.format("%.2f", lamount);
        } catch (Exception e) {
            return "0元";
        }

    }

    /**
     * 用指定字符分隔格式化字符串
     * <br/>（如电话号为1391235678 指定startIndex为3，step为4，separator为'-'经过此处理后的结果为 <br/> 139-1234-5678）
     *
     * @param source     需要分隔的字符串
     * @param startIndex 开始分隔的索引号
     * @param step       步长
     * @param separator  指定的分隔符
     * @return 返回分隔格式化处理后的结果字符串
     */
    private static String separateString(String source, int startIndex, int step, char separator) {
        int times = 0;
        StringBuilder tmpBuilder = new StringBuilder(source);
        for (int i = 0; i < tmpBuilder.length(); i++) {
            if (i == startIndex + step * times + times) {//if(i == 3 || i == 8){
                if (separator != tmpBuilder.charAt(i)) {
                    tmpBuilder.insert(i, separator);
                }
                times++;
            } else {
                if (separator == tmpBuilder.charAt(i)) {
                    tmpBuilder.deleteCharAt(i);
                    i = -1;
                    times = 0;
                }
            }
        }
        return tmpBuilder.toString();
    }

    /**
     * 格式化手机号c
     *
     * @param number
     * @return
     */
    public static String getFormatNumber(String number) {
        String formatNumber = "";
        if (!UtilText.isEmptyOrNull(number)) {
            int length = number.length();
            if (length == 11) {//11位手机号
                formatNumber = UtilText.separateString(number, 3, 4, ' ');
            } else {
                formatNumber = number;
            }
        }
        return formatNumber;
    }

    /**
     * 对JSON字符串解析，如果不包含这个key的字段，则返回“”
     *
     * @param object
     * @param key
     * @return
     * @author zhangyi
     * @createTime 2015-3-4 下午2:50:21
     */
    public static String getStringFromJson(JSONObject object, String key) {
        String string = "";
        try {
            if (object.has(key)) {
                string = object.getString(key);
            } else {
                string = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 对JSON字符串解析，如果不包含这个key的字段，则返回99999
     *
     * @param object
     * @param key
     * @return
     * @author zhangyi
     * @createTime 2015-3-4 下午2:50:21
     */
    public static int getIntFromJson(JSONObject object, String key) {
        int value = 99999;
        try {
            if (object.has(key)) {
                value = object.getInt(key);
            } else {
                value = 99999;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /****
     * 手机号码中间4位脱敏
     * @param text 要脱敏的手机号
     * @return 脱敏后的手机号
     */
    public static String getDesensitizationText(String text) {
        String name = "";
        if (!UtilText.isEmptyOrNull(text) && text.length() > 7) {
            name = text.substring(0, 3) + "****" + text.substring(7);
        }
        return name;
    }

    /****
     * 号码脱敏规则
     * 1.账号位数大于8位，显示前3后4，中间固定4个*,比如原来是123123123123123脱敏后是123****3123
     * 2.账户位数大于4位少于等于8位,显示后4位，前面固定4个*，比如原来是123456脱敏后是****3456
     * 3.账号位数少于等于4位账户前面加4个*，比如原来是1234脱敏后是****1234
     */
    public static String getDesensitizationText2(String text) {
        if (UtilText.isEmptyOrNull(text)){
            return "";
        }
        String name;
        if (text.length() <= 4){
            name = "****" + text;
        }else {
            String substring = text.substring(text.length() - 4);
            if(text.length() <= 8){
                name = "****" + substring;
            }else{
                name = text.substring(0, 3) + "****" + substring;
            }
        }
        return name;
    }




    /**
     * 将content 中的网址变色
     *
     * @param content 变量数组
     * @return
     */

    /**
     * 获取所有满足正则表达式的字符串
     *
     * @param str   需要被获取的字符串
     * @param regex 正则表达式
     * @return 所有满足正则表达式的字符串
     */
    public static ArrayList<String> getAllSatisfyStr(String str, String regex) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ArrayList<String> allSatisfyStr = new ArrayList<>();
        if (regex == null || regex.isEmpty()) {
            allSatisfyStr.add(str);
            return allSatisfyStr;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            allSatisfyStr.add(matcher.group());
        }
        return allSatisfyStr;
    }

    /**
     * 说明：获取靓号高亮文本SpannableString
     *
     * @param phoneNumber 号码
     * @param hlStart     高亮开始位置
     * @param hlEnd       高亮结束位置
     * @作者 zhuzr
     * @创建时间 2019/1/3 10:02
     * @版本
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */
 /*   public static SpannableString getHighlightSpan(String phoneNumber, String hlStart, String hlEnd) {
        SpannableString spannableString = new SpannableString("");
        if (!UtilText.isEmptyOrNull(phoneNumber)) {
            int start = UtilOther.parseInt(hlStart);
            int end = UtilOther.parseInt(hlEnd);
            if (start > end) {
                start = 0;
                end = 0;
            }
            if (phoneNumber.length() < end) {
                end = phoneNumber.length();
            }
            if (phoneNumber.length() - 1 < start) {
                start = 0;
                end = 0;
            }
            spannableString = new SpannableString(phoneNumber);
            if (phoneNumber.length() > start && phoneNumber.length() >= end) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Utils.getApp().getResources().getColor(R.color.orange_f79b1c));
                spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }
*/
    /**
     * url处理
     *
     * @return 处理
     */
    @NonNull

    public static SpannableString accountInfoRegex(String tips, String tipsNum, String regex, String color) {
        if (isEmptyOrNull(tips) || isEmptyOrNull(tipsNum)){
            return null;
        }
        SpannableString spannableString = new SpannableString("");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
        if (tips.contains(regex)){
            String string = tips.replace(regex,tipsNum);
            int i = string.indexOf(tipsNum);
            spannableString = new SpannableString(string);
            spannableString.setSpan(colorSpan,i,i+tipsNum.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            return spannableString;
        } else {
            return null;
        }
    }
    /***
     * 在文本的右边增加的图标
     * @param context
     * @param tv
     * @param drawableId
     */



    /**
     * 将 Exception 转化为 String
     */
    public static String getExceptionToString(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static String replaceCityAndProvince(String string) {
        if (UtilText.isEmptyOrNull(string)){
            return "";
        }else{
            if(string.contains("省")){
                string = string.replace("省", "");
            }else if(string.contains("市")){
                string = string.replace("市", "");
            }

            if(string.contains("新疆")){
                string = "新疆";
            }else if(string.contains("广西")){
                string = "广西";
            }else if(string.contains("宁夏")){
                string = "宁夏";
            }else if(string.contains("西藏")){
                string = "西藏";
            }else if(string.contains("内蒙古")){
                string = "内蒙古";
            }
        }
        return string;
    }

    /**
     * 复制内容
     * @param content
     */
  /*  public static void copyContent(String content) {
        ClipboardManager cm;
        ClipData mClipData;
        //获取剪贴板管理器：
        cm = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }*/

    /**
     *
     * @param text  源文本
     * @param elseText 当源文本为空时设置的text
     */
    public static void setText(TextView tv, String text, String elseText) {
        if (UtilText.isEmptyOrNull(text)){
            tv.setText(elseText);
            tv.setContentDescription(elseText);
        } else {
            tv.setText(text);
            tv.setContentDescription(text);
        }
    }

    /**
     * @param unit  单位
     * @param text  源文本
     * @param elseText 当源文本为空时设置的text
     */
    public static void setBalanceText(TextView tv, String text,String unit ,String elseText) {
        if (UtilText.isEmptyOrNull(text)||TextUtils.equals(text,"--")){
            tv.setText(elseText);
        } else {
            tv.setText(text+unit);
        }
    }

    /**
     *
     * @param text 源文本
     * @param view 当源文本为空时设置GONE的VIEW
     */
    public static void setText(TextView tv, String text, View view) {
        if (UtilText.isEmptyOrNull(text)){
            view.setVisibility(View.GONE);
        } else {
            tv.setText(text);
        }
    }

    /**
     *
     * @param text 源文本
     * @param view 当源文本为空时设置GONE的VIEW和文本
     */
    public static void setTextAndView(TextView tv, String text, View view) {
        if (UtilText.isEmptyOrNull(text)){
            view.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void setText(TextView tv, String text) {
        if (UtilText.isEmptyOrNull(text)){
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }
    }

    public static void setTextInvisible(TextView tv, String text) {
        if (UtilText.isEmptyOrNull(text)){
            tv.setVisibility(View.INVISIBLE);
        } else {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }
    }

    public static void setTextColor(TextView textView, String textColor) {
        try {
            textView.setTextColor(Color.parseColor(textColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SpannableStringBuilder getHomePageDataString(String data, String unit) {
        if (UtilText.isEmptyOrNull(data)) {
            return new SpannableStringBuilder("--");
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(data + unit);
        AbsoluteSizeSpan smallSpan = new AbsoluteSizeSpan(12, true);
        AbsoluteSizeSpan middleSpan = new AbsoluteSizeSpan(16, true);
        AbsoluteSizeSpan bigSpan = new AbsoluteSizeSpan(23, true);
        String[] strings;
        if (data.contains(".")) {
            strings = data.split("\\.");
            spannableStringBuilder.setSpan(bigSpan, 0, strings[0].length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableStringBuilder.setSpan(middleSpan, strings[0].length(), data.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableStringBuilder.setSpan(smallSpan, data.length(), spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        } else {
            spannableStringBuilder.setSpan(bigSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableStringBuilder.setSpan(smallSpan, data.length(), spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return spannableStringBuilder;
    }

    public static boolean isListEmptyOrNull(List list) {
        return list == null && list.size()==0;
    }


    public static void setTextViewArrow(TextView textView, int arrowDrawableId, String linkType) {
        if (UtilText.isEmptyOrNull(linkType)) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawableId, 0);
        }
    }
}
