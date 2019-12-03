package com.skycong.sfs.util;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author ruanmingcong 2019.12.3 13:05
 */
public class Util {

    /**
     * 获取文件的md5值
     *
     * @param inputStream file
     * @return file的md5
     */
    public static String getFileMD5(InputStream inputStream) {
        try {
            byte[] digest = digest(inputStream);
            return new String(bytes2HexChars(digest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 将byte 数组转换为十六进制 char 数组
     *
     * @param bytes byte data
     * @return hex char
     */
    public static char[] bytes2HexChars(byte[] bytes) {
        final int l = bytes.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = HEX_CHARS[(0xF0 & bytes[i]) >>> 4];
            out[j++] = HEX_CHARS[0x0F & bytes[i]];
        }
        return out;
    }

    private static byte[] digest(InputStream inputStream) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            messageDigest.update(buffer, 0, bytesRead);
        }
        return messageDigest.digest();
    }
}
