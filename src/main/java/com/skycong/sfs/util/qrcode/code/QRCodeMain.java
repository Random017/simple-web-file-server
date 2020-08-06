package com.skycong.sfs.util.qrcode.code;

import java.io.File;

public class QRCodeMain {

    public static String encode(String content, String savePath, String desc) {
        CodeCreator creator = new CodeCreator();
        CodeModel info = new CodeModel();
        info.setWidth(350);
        info.setHeight(350);
        info.setFontSize(18);
        info.setContents(content);
        String file = QRCodeMain.class.getResource("/static/img/bug.jpg").getFile();
        info.setLogoFile(new File(file));
        info.setDesc(desc);
        String filename = savePath + File.separator + System.currentTimeMillis() + "." + info.getFormat();
        creator.createCodeImage(info, filename);
        return filename;
    }

    public static void main(String[] args) throws Exception {
        encode("https://www.cnblogs.com/huanzi-qch/p/10097791.html", "C:\\Users\\mc\\Desktop", "aaaaa");
    }
}