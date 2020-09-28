package com.springboott.ttdemo.util;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Md5加密方法
 *
 * @author ruoyi
 */
public class Md5Utils {
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    @Test
    public void a1() {
        String fileName = "呆两年卡.png" + System.nanoTime();
        fileName = Md5Utils.hash(fileName);
        System.out.println("fileName = " + fileName);

        fileName = "呆两年卡.png" + System.nanoTime();
        fileName = Md5Crypt.md5Crypt(fileName.getBytes());
        System.out.println("fileName = " + fileName);

        fileName = "呆两年卡.png" + System.nanoTime();
        fileName = ApiUtils.toMD5(fileName, 32);
        System.out.println("fileName = " + fileName);
    }
}
