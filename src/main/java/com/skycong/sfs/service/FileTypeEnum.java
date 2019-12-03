package com.skycong.sfs.service;

import java.util.regex.Pattern;

/**
 * @author ruanmingcong 2018/9/17 14:27
 */
public enum FileTypeEnum {

    IMAGE(1, ".+(.jpeg|.jpg|.png|.gif|.bmp|.pcx|.ico|.tiff|.pict)$"),
    AUDIO(2, ".+(.cd|.mp3|.wav|.ape|.rm|.vqf|.ogg)$"),
    VIDEO(3, ".+(.avi|.wmv|.mpeg|.mp4|.mkv|.flv|.rmvb|.3gp)$"),
    TEXT(4, ".+(.ini|.txt|.html|.css|.js|.json|.xml|.sh|.sql|.conf)$"),
    APPLICATION(5, "");

    private Integer code;
    private String pattern;


    FileTypeEnum(Integer code, String pattern) {
        this.code = code;
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public Integer getCode() {
        return code;
    }

    public static final Pattern IMAGE_PAT = Pattern.compile(IMAGE.getPattern());
    public static final Pattern AUDIO_PAT = Pattern.compile(AUDIO.getPattern());
    public static final Pattern VIDEO_PAT = Pattern.compile(VIDEO.getPattern());
    public static final Pattern TEXT_PAT = Pattern.compile(TEXT.getPattern());

    /**
     * 根据文件名判断文件类型
     *
     * @param filename filename
     * @return file type string
     */
    public static String getFileTypeStr(String filename) {
        String s = filename.toLowerCase();
        if (IMAGE_PAT.matcher(s).find()) return IMAGE.name();
        if (AUDIO_PAT.matcher(s).find()) return AUDIO.name();
        if (VIDEO_PAT.matcher(s).find()) return VIDEO.name();
        if (TEXT_PAT.matcher(s).find()) return TEXT.name();
        return APPLICATION.name();
    }
}
