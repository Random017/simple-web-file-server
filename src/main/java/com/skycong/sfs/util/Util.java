package com.skycong.sfs.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.List;

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

    public static String checkAndGenerateFilename(List<String> directoryFileList, String originFileName) {
        return checkAndGenerateFilename(directoryFileList, originFileName, 1);
    }

    /**
     * 检查并生成新文件名
     * eg： aaa-->aaa(1)-->aaa(2)<br/>
     * aaa.jpg-->aaa(1).jpg--->aaa(2).jpg<br/>
     * aaa(1)bbb.jpg-->aaa(1)bbb(1).jpg
     *
     * @param directoryFileList 同目录下文件名列表
     * @param originFileName    源文件名
     * @param order             生成序号
     * @return
     */
    public static String checkAndGenerateFilename(List<String> directoryFileList, String originFileName, int order) {
        if (directoryFileList == null || directoryFileList.isEmpty()) return originFileName;
        if (directoryFileList.contains(originFileName)) {
            String prefix, suffix;
            int index = originFileName.indexOf(POINT);
            if (index < 0) {
                prefix = originFileName;
                suffix = "";
            } else {
                prefix = originFileName.substring(0, index);
                suffix = originFileName.substring(index);
            }
            //重命名操作
            if (prefix.endsWith(")")) {
                int sindex = prefix.lastIndexOf("(");
                int eindex = prefix.lastIndexOf(")");
                //满足条件
                if (sindex >= 0 && eindex > sindex + 1) {
                    String orderStr = prefix.substring(sindex + 1, eindex);
                    try {
                        Integer.valueOf(orderStr);
                        order++;
                        prefix = prefix.substring(0, sindex);
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
            originFileName = prefix + "(" + order + ")" + suffix;
            return checkAndGenerateFilename(directoryFileList, originFileName, order);
        }
        return originFileName;
    }

    public static final String POINT = ".";
}
