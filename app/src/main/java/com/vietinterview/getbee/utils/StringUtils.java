package com.vietinterview.getbee.utils;

/**
 * Created by hiepnguyennghia on 10/10/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */

import android.content.Context;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.model.StatusCV;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * is null or its length is 0 or it is made by space
     *
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return true, else return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     *
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * compare two string
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */
    public static boolean isEquals(String actual, String expected) {
        return ObjectUtils.isEquals(actual, expected);
    }

    /**
     * get length of CharSequence
     *
     * <pre>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
     * </pre>
     *
     * @param str
     * @return if str is null or empty, return 0, else return {@link CharSequence#length()}.
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * null Object to empty string
     *
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * capitalize first letter
     *
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(str.length())
                .append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     *
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException if an error occurs
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     *
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     *
     * @param href
     * @return <ul>
     * <li>if href is null, return ""</li>
     * <li>if not match regx, return source</li>
     * <li>return the last string that match regx</li>
     * </ul>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * process special char in html
     *
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * transform half width char to full width char
     *
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    public static void setColorForPath(Spannable spannable, String[] paths, int color) {
        for (int i = 0; i < paths.length; i++) {
            int indexOfPath = spannable.toString().indexOf(paths[i]);
            if (indexOfPath == -1) {
                return;
            }
            spannable.setSpan(new ForegroundColorSpan(color), indexOfPath,
                    indexOfPath + paths[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * transform full width char to half width char
     *
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String filterCurrencyString(long number) {
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,###");
        return df.format(number);
    }

    public static boolean checkFunction(String code) {
        boolean isHave = false;
        for (int i = 0; i < AccountManager.getUserInfoBean().getLstFunctionAuthority().size(); i++) {
            if (AccountManager.getUserInfoBean().getLstFunctionAuthority().get(i).getCode().equalsIgnoreCase(code)) {
                isHave = true;
                break;
            } else {
                isHave = false;
            }
        }
        return isHave;
    }

    public static String genStringLan(int value, Context context) {
        String valueString;
        switch (value) {
            case 1:
                valueString = context.getResources().getString(R.string.good);
                break;
            case 2:
                valueString = context.getResources().getString(R.string.rather);
                break;
            case 3:
                valueString = context.getResources().getString(R.string.medium);
                break;
            case 4:
                valueString = context.getResources().getString(R.string.least);
                break;

            default:
                valueString = "";
                break;
        }
        return valueString;
    }

    public static String genStringCurrency(int value) {
        String valueString;
        switch (value) {
            case 1:
                valueString = "VND";
                break;
            case 2:
                valueString = "USD";
                break;
            case 3:
                valueString = "JPY";
                break;
            default:
                valueString = "VND";
                break;
        }
        return valueString;
    }

    public static String genString(int value, Context context) {
        String valueString;
        switch (value) {
            case 1:
                valueString = context.getResources().getString(R.string.below_50);
                break;
            case 2:
                valueString = context.getResources().getString(R.string.from_50_to_100);
                break;
            case 3:
                valueString = context.getResources().getString(R.string.above_100);
                break;
            case 4:
                valueString = context.getResources().getString(R.string.above_500);
                break;
            case 5:
                valueString = context.getResources().getString(R.string.average);
                break;
            default:
                valueString = "";
                break;
        }
        return valueString;
    }

    public static String genStringSex(int valueSex, Context context) {
        String Sex = "";
        switch (valueSex) {
            case 0:
                Sex = "Không yêu cầu";
                break;
            case 1:
                Sex = context.getResources().getString(R.string.male);
                break;
            case 2:
                Sex = context.getResources().getString(R.string.female);
                break;
            default:
                Sex = "";
                break;
        }
        return Sex;
    }

    public static String genStringAge(int valueAge) {
        String age = "";
        switch (valueAge) {
            case 1:
                age = "Không giới hạn tuổi";
                break;
            case 2:
                age = "Dưới 25 tuổi";
                break;
            case 3:
                age = "Từ 25 - 30 tuổi";
                break;
            case 4:
                age = "Trên 30 tuổi";
                break;
            default:
                age = "";
                break;
        }
        return age;
    }

    public static String genStringExperience(String valueExperience) {
        String Experience = "";
        switch (valueExperience) {
            case "1":
                Experience = "Không yêu cầu";
                break;
            case "2":
                Experience = "Dưới 1 năm";
                break;
            case "3":
                Experience = "Từ 1 - 3 năm";
                break;
            case "4":
                Experience = "Trên 3 năm";
                break;
            default:
                Experience = "";
                break;
        }
        return Experience;
    }

    public static StatusCV genStringStatus(int status, Context context) {
        StatusCV statusCV = null;
        switch (status) {
            case -1:
                statusCV = new StatusCV(context.getResources().getString(R.string.draft), context.getResources().getDrawable(R.drawable.border_gray), context.getColor(R.color.background_icon_not_focus));
                break;
            case 0:
                statusCV = new StatusCV(context.getResources().getString(R.string.not_send), context.getResources().getDrawable(R.drawable.border_gray), context.getColor(R.color.background_icon_not_focus));
                break;
            case 1:
                statusCV = new StatusCV(context.getResources().getString(R.string.sent), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 2:
                statusCV = new StatusCV(context.getResources().getString(R.string.approved), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 3:
                statusCV = new StatusCV(context.getResources().getString(R.string.seen), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 4:
                statusCV = new StatusCV(context.getResources().getString(R.string.not_accept), context.getResources().getDrawable(R.drawable.border_gray), context.getColor(R.color.background_icon_not_focus));
                break;
            case 5:
                statusCV = new StatusCV(context.getResources().getString(R.string.invite_interview), context.getResources().getDrawable(R.drawable.border_red), context.getColor(R.color.red));
                break;
            case 6:
                statusCV = new StatusCV(context.getResources().getString(R.string.interviewed), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 7:
                statusCV = new StatusCV(context.getResources().getString(R.string.offered), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 8:
                statusCV = new StatusCV(context.getResources().getString(R.string.go_to_work), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 9:
                statusCV = new StatusCV(context.getResources().getString(R.string.contract), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 10:
                statusCV = new StatusCV(context.getResources().getString(R.string.done), context.getResources().getDrawable(R.drawable.border_gray), context.getColor(R.color.background_icon_not_focus));
                break;
        }
        return statusCV;
    }

    public static StatusCV genStringStatusCVNTD(int status, Context context) {
        StatusCV statusCV = null;
        switch (status) {
            case -1:
                statusCV = new StatusCV(context.getResources().getString(R.string.draft), context.getResources().getDrawable(R.drawable.border_gray_12), context.getColor(R.color.background_icon_not_focus));
                break;
            case 0:
                statusCV = new StatusCV(context.getResources().getString(R.string.not_send), context.getResources().getDrawable(R.drawable.border_gray_12), context.getColor(R.color.background_icon_not_focus));
                break;
            case 1:
                statusCV = new StatusCV(context.getResources().getString(R.string.sent), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 2:
                statusCV = new StatusCV(context.getResources().getString(R.string.not_seen), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 3:
                statusCV = new StatusCV(context.getResources().getString(R.string.seen), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
            case 4:
                statusCV = new StatusCV(context.getResources().getString(R.string.not_accept), context.getResources().getDrawable(R.drawable.border_gray_12), context.getColor(R.color.background_icon_not_focus));
                break;
            case 5:
                statusCV = new StatusCV(context.getResources().getString(R.string.invite_interview), context.getResources().getDrawable(R.drawable.border_red), context.getColor(R.color.red));
                break;
            case 6:
                statusCV = new StatusCV(context.getResources().getString(R.string.interviewed), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 7:
                statusCV = new StatusCV(context.getResources().getString(R.string.offered), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 8:
                statusCV = new StatusCV(context.getResources().getString(R.string.go_to_work), context.getResources().getDrawable(R.drawable.border_green), context.getColor(R.color.green));
                break;
            case 9:
                statusCV = new StatusCV(context.getResources().getString(R.string.contract), context.getResources().getDrawable(R.drawable.border_yellow), context.getColor(R.color.yellow_highlight));
                break;
        }
        return statusCV;
    }
}

