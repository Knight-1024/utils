package com.chx.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by CHX on 2019/5/20.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(null != str && !"".equals(str)){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNotEmpty(Object str) {
        boolean flag = true;
        if (str != null && !str.equals("")) {
            if (str.toString().length() > 0) {
                flag = true;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     *  最大长度字符显示
     */
    public static String leftString(String str, int len, boolean showdot) {
        if (str == null){
            return "";
        }
        if (showdot){
            return StringUtil.cutstring(str, len, "...");
        }else {
            return StringUtil.cutstring(str, len, "");
        }
    }

    /**
     * 按字节长度截取字符串
     */
    public static String cutstring(String str, int toCount, String more) {
        int reInt = 0;
        String reStr = "";

        if (str == null)
            return "";
        char[] tempChar = str.toCharArray();
        byte[] CharLen = str.getBytes();
        byte[] moreLen = more.getBytes();

        if (CharLen.length > toCount) {
            toCount = toCount - moreLen.length;
        }

        for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            reStr += tempChar[kk];
        }

        if (CharLen.length > toCount){
            reStr += more;
        }
        return reStr;
    }

    /**
     * 把字符串按照一定字符进行分割
     */
    public static String[] splitString(String szSource, String token) {
        if ((szSource == null) || (token == null))
            return null;
        java.util.StringTokenizer st1 = new java.util.StringTokenizer(szSource,
                token);
        String[] d1 = new String[st1.countTokens()];
        for (int x = 0; x < d1.length; x++)
            if (st1.hasMoreTokens())
                d1[x] = st1.nextToken();
        return d1;
    }

    /**
     * 把字符串按照一定字符进行分割 第二种方法
     */
    public static String[] split(String str, String splitsign) {
        int index;
        if (str == null || splitsign == null)
            return null;
        ArrayList<String> al = new ArrayList<String>();
        while ((index = str.indexOf(splitsign)) != -1) {
            al.add(str.substring(0, index));
            str = str.substring(index + splitsign.length());
        }
        al.add(str);
        return al.toArray(new String[0]);
    }

    /**
     * 字符串转为字节
     */
    public static byte[] toByte(String szStr) {
        if (szStr == null)
            return null;
        byte[] tmp = szStr.getBytes();
        return tmp;
    }

    /**
     * 数字数组转换成字符串
     */
    public static String arraytoString(Object[] ids, String separator) {
        String ret = "";
        for (int i = 0; i < ids.length; i++) {
            if (i < ids.length - 1)
                ret += String.valueOf(ids[i]) + separator;
            else
                ret += String.valueOf(ids[i]);
        }
        return ret;
    }

    /**
     * 数字数组转换成字符串
     */
    public static String arrayInttoString(int[] ids, String separator) {
        String ret = "";
        for (int i = 0; i < ids.length; i++) {
            if (i < ids.length - 1)
                ret += String.valueOf(ids[i]) + separator;
            else
                ret += String.valueOf(ids[i]);
        }
        return ret;
    }

    /**
     * 字符数组转换成字符串
     */
    public static String arrayStrtoString(String[] ids, String separator) {
        String ret = "";
        for (int i = 0; i < ids.length; i++) {
            if (i < ids.length - 1)
                ret += String.valueOf(ids[i]) + separator;
            else
                ret += String.valueOf(ids[i]);
        }
        return ret;
    }

    /**
     * 字节数组转为字符串，为null则转为""
     */
    public static String toString(byte[] byBuf) {
        if (byBuf == null)
            return null;
        return new String(byBuf);
    }

    /**
     * 对象转为字符串，为null则转为""
     */
    public static String toString(Object obj) {
        if (obj == null)
            return "";
        return obj.toString().trim();
    }

    /**
     * 字符串为null则以""取代
     */
    public static String toString(String obj) {
        if (obj == null)
            return "";
        return obj;
    }

    /**
     * 日期转为字符串
     */
    public static String toString(Date obj) {
        return toString(obj, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转为字符串
     */
    public static String toString(Date obj, String format) {
        if (obj == null)
            return "";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
        return df.format(obj);
    }

    /**
     * 最大长度字符显示
     */
    public static String subString(String str, int len) {
        if (str == null)
            return "";
        if (str.length() <= len)
            return str;
        return str.substring(0, len);
    }

    /**
     * 字符串截取后拼接
     */
    public static String subString(String str, int len, String expandstr) {
        if (str == null)
            return "";
        if (str.length() <= len)
            return str;
        return str.substring(0, len) + expandstr;
    }

    /**
     * 字符串转为list
     */
    public static List<String> str2List(String urlStr) {
        final String URL_SPLIT_PATTERN = "[, ;\r\n]";// 逗号 空格 分号 换行
        if (urlStr == null) {
            return null;
        }
        String[] urlArray = urlStr.split(URL_SPLIT_PATTERN);
        List<String> urlList = new ArrayList<String>();
        for (String url : urlArray) {
            url = url.trim();
            if (url.length() == 0) {
                continue;
            }
            urlList.add(url);
        }
        return urlList;
    }

    /**
     * list转字符","
     */
    public static String list2Str(List<Integer> args) {
        String str = "";
        if (args != null && args.size() > 0) {
            for (int i : args) {
                str += i + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 数字显示格式的处理
     */
    public static String toPrice(double d) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(d);
    }

    /**
     * 数字显示格式的处理
     */
    public static String toPrice(float d) {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(d);
    }

    /**
     * 数字显示格式的处理
     */
    public static String toPrice_Fin(double d) {
        DecimalFormat df = new DecimalFormat("###0.00");
        return df.format(d);
    }

    /**
     * null 或者 "" 返回默认字符串
     */
    public static String defaultString(String originalString,String defaultString){
        if(null == originalString || "".equals(originalString.trim())){
            return defaultString;
        }
        return originalString;
    }

    /**
     * 返回oriStr中有的，但str上没有的词
     */
    public static String findDiff(String oriStr, String str, String token){
        if(null == token) token = ",";
        String ret = "";
        if(null == oriStr || 0 == oriStr.length()){
            return "";
        }else if(null == str || 0 == str.length()){
            return oriStr;
        }else{
            String[] arr = oriStr.split(token);
            str += token;
            for(String val : arr){
                if(str.indexOf(val + token) == -1){
                    ret += val + ",";
                }
            }
            if(ret.length() > 0)
                ret = ret.substring(0, ret.length() - 1);
            return ret;
        }
    }

    /**
     * 过滤特殊字符
     */
    public static  String StringFilter(String str)throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }

    /**
     * 替换字符串
     */
    public static String replace(String text, String repl, String with) {

        return replace(text, repl, with, -1);
    }

    /**
     * 替换字符串
     */
    public static String replace(String text, String repl, String with, int max) {

        if ((text == null) || (repl == null) || (with == null)
                || (repl.length() == 0) || (max == 0)) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            max--;
            if (max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 过滤怪字符
     */
    public static String filterBadChar(String szText) {
        String tmp = szText;

        String result = "";
        for (int i = 0; i < tmp.length(); i++) {
            char ch = tmp.charAt(i);
            if (ch < ' ' || (ch > '~' && ch < 255))
                continue;
            result += ch;
        }
        return result;
    }

    /**
     * 判断是否为身份证
     */
    public static boolean isCard(String s_aStr) {

        Pattern reg15 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        Matcher mReg15 = reg15.matcher(s_aStr);

        //Pattern reg18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        //兼容带X的身份证号
        Pattern reg18 = Pattern.compile("^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$");

        Matcher mReg18 = reg18.matcher(s_aStr);
        if (mReg15.matches() || mReg18.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为手机号码
     */
    public static boolean isPhone(String phone) {

        /*Pattern reg = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9])|(171)|(147))\\d{8}$");
        Matcher mReg = reg.matcher(phone);
        if (mReg.matches()) {
            return true;
        }
        return false;*/

        String telRegex = "[1][345678]\\d{9}";
        if (StringUtil.isEmpty(phone)) {
            return false;
        }
        return phone.matches(telRegex);
    }

}
