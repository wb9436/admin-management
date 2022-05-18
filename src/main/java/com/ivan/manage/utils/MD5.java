package com.ivan.manage.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5 {

    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_GBK = "GBK";

    public static String encode(String str,String charset) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(str.getBytes(charset));
            byte bytes[] = m.digest();
            return HexBin.encode(bytes).toLowerCase();
        } catch (Exception e) {
        }
        return "";
    }


    public static String encodeUTF8(String str) {
        return encode(str, CHARSET_UTF8);
    }

    public static String encodeGBK(String str) {
        return encode(str, CHARSET_GBK);
    }

    public static String md5File(String file) {
        FileInputStream fis = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int l = 0;
            while ((l = fis.read(buf)) > 0) {
                digest.update(buf, 0, l);
            }
            byte[] binaryData = digest.digest();
            return HexBin.encode(binaryData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
       System.out.println( MD5.encodeUTF8("123456"));
    }
}
