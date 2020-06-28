package com.springboott.ttdemo.util;

import com.springboott.ttdemo.config.enums.ErrorCodeEnum;
import com.springboott.ttdemo.config.exception.ApiAssert;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

    /**
     * 将文字转为汉语拼音
     *
     * @param chineseStr 要转成拼音的中文
     */
    public static String getPinyinString(String chineseStr) {
        char[] cl_chars = chineseStr.trim().toCharArray();
        String convert = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    convert += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    convert += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("字符不能转成汉语拼音"), false);
        }
        return convert;
    }

    /**
     * 转换拼音字符串中第一个为大写
     *
     * @param chineseStr
     * @return
     */
    public static String getFirstLettersUp(String chineseStr) {
        return getFirstLetters(chineseStr, HanyuPinyinCaseType.UPPERCASE);
    }

    /**
     * 转换拼音字符串第一个为小写
     *
     * @param chineseStr
     * @return
     */
    public static String getFirstLettersLo(String chineseStr) {
        return getFirstLetters(chineseStr, HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * 获取第一个位置
     *
     * @param chineseStr
     * @param caseType
     * @return
     */
    public static String getFirstLetters(String chineseStr, HanyuPinyinCaseType caseType) {
        char[] cl_chars = chineseStr.trim().toCharArray();
        String convert = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    convert += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    convert += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    convert += cl_chars[i];
                } else {// 否则不转换
                    convert += cl_chars[i];//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("字符不能转成汉语拼音"), false);
        }
        return convert;
    }


    /**
     * 取第一个汉字的第一个字符
     *
     * @param chineseStr
     * @return
     */
    public static String getFirstLetter(String chineseStr) {
        char[] cl_chars = chineseStr.trim().toCharArray();
        String convert = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                convert = PinyinHelper.toHanyuPinyinStringArray(
                        cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                convert += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                convert += cl_chars[0];
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("字符不能转成汉语拼音"), false);
        }
        return convert;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param chineseStr
     * @return String
     */
    public static String getPinYinHeadChar(String chineseStr) {
        String convert = "";
        for (int j = 0; j < chineseStr.length(); j++) {
            char word = chineseStr.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff) + " ");
        }
        return strBuf.toString();
    }

}
