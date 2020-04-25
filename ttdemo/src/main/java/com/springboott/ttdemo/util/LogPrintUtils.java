package com.springboott.ttdemo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 手动添加日志
 * @author Sam
 */
public class LogPrintUtils {

    private final static String PATH = "logs/ttdemo";
    private final static String NAME = "/message.log";

    /**
     * 写日志文件
     *
     * @param str 日志内容
     * @throws IOException
     */
    public static void writeLog(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            File file = new File(PATH + NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file, true);
            StringBuffer sb = new StringBuffer();
            sb.append(sdf.format(date));
            sb.append("\t");
            sb.append(str);
            sb.append("\r\n");
            out.write(sb.toString().getBytes("utf-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写日志文件
     *
     * @param str  日志内容
     * @param path 日志文件路径
     * @throws IOException
     */
    public static void writeLog(String str, String path) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file, true);
            StringBuffer sb = new StringBuffer();
            sb.append(sdf.format(date));
            sb.append("\t");
            sb.append(str);
            sb.append("\r\n");
            out.write(sb.toString().getBytes("utf-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
