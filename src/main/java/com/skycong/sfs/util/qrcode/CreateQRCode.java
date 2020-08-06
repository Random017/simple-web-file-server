// package com.skycong.sfs.util.qrcode;
//
// import java.io.File;
// import java.nio.file.Path;
// import java.util.HashMap;
//
// import com.google.zxing.BarcodeFormat;
// import com.google.zxing.EncodeHintType;
// import com.google.zxing.MultiFormatWriter;
// import com.google.zxing.common.BitMatrix;
// import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
// public class CreateQRCode {
//
// 	static final private int width = 300;
// 	static final private int height = 300;
// 	static final private String pictureFormat = "png";
// 	static  private String pictureContent="恭喜你中奖了,晏尼玛会在一分钟之内转你100块大洋!!!";
//
//
//
// 	public static void main(String[] args) {
// 		createQRCode();
// 	}
// 	static void createQRCode(){
//
// 		HashMap<Object, Object> map = new HashMap<>();
// 		map.put(EncodeHintType.CHARACTER_SET, "utf-8");
// 		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
// 		map.put(EncodeHintType.MARGIN, 2);
//
// 		try {
// 			String content = new String(pictureContent.getBytes("UTF-8"), "ISO-8859-1");
// 			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
// 			Path path = new File("D:/qr.png").toPath();
// 			System.out.println(path);
// 			// MatrixToImageWriter.writeToPath(bitMatrix, pictureFormat, path);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
// }
