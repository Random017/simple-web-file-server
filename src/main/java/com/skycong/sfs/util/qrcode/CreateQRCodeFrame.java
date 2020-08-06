// package com.skycong.sfs.util.qrcode;
//
// import java.awt.GridLayout;
// import java.io.File;
// import java.nio.file.Path;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.HashMap;
//
// import javax.swing.JButton;
// import javax.swing.JComboBox;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
// import com.google.zxing.BarcodeFormat;
// import com.google.zxing.EncodeHintType;
// import com.google.zxing.MultiFormatWriter;
// import com.google.zxing.common.BitMatrix;
// import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
// /**
//  * 创建时间：2017年7月14日 下午1:40:37 项目名称：MyJavaLearning
//  *
//  * @author Random
//  * @version 1.0
//  * @since JDK 1.8 文件名称：CreateQRCodeFrame.java 类说明： 生成二维码的界面
//  */
// public class CreateQRCodeFrame extends JFrame {
//
// 	private static final long serialVersionUID = -7882691383139441965L;
// 	private static final String CHARSET = "UTF8";
// 	private static final String FORMART = "png";
// 	private String qrcodePath;
// 	private int qrSize;
// 	private String qrContent;
//
// 	public CreateQRCodeFrame() {
// 		JLabel qrSize = new JLabel("二维码大小:");
// 		JComboBox<String> qrSizeSelect = new JComboBox<>();
// 		qrSizeSelect.addItem("100 x 100");
// 		qrSizeSelect.addItem("200 x 200");
// 		qrSizeSelect.addItem("300 x 300");
// 		qrSizeSelect.addItem("400 x 400");
// 		qrSizeSelect.addItem("500 x 500");
// 		add(qrSize);
// 		add(qrSizeSelect);
// 		JLabel savePath = new JLabel("保存路径:");
// 		JTextField filePth = new JTextField(30);
// 		JButton select = new JButton("选择目录");
// 		select.addActionListener((e) -> {
// 			JFileChooser fileDir = new JFileChooser();
// 			fileDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
// 			fileDir.showSaveDialog(null);
// 			File saveQRCode = fileDir.getSelectedFile();
// 			//设置路径
// 			this.qrcodePath = saveQRCode.getAbsolutePath();
// 			System.out.println(qrcodePath);
// 			filePth.setText(this.qrcodePath);
// 		});
// 		add(savePath);
// 		add(filePth);
// 		add(select);
// 		JLabel qrContent = new JLabel("二维码内容");
// 		JTextArea content = new JTextArea(2, 30);
// 		add(qrContent);
// 		add(content);
// 		JButton savaQRCode = new JButton("保存二维码");
// 		savaQRCode.addActionListener((e) -> {
// 			//设置大小
// 			String key = qrSizeSelect.getSelectedItem().toString();
// 			System.out.println(key);
// 			switch (key) {
// 			case "100 x 100":
// 				this.qrSize = 100;
// 				break;
// 			case "200 x 200":
// 				this.qrSize = 200;
// 				break;
// 			case "400 x 400":
// 				this.qrSize = 400;
// 				break;
// 			case "500 x 500":
// 				this.qrSize = 500;
// 				break;
// 			default:
// 				this.qrSize = 300;
// 				break;
// 			}
// 			//设置内容
// 			this.qrContent=content.getText().toString().trim();
// 			System.out.println(this.qrContent);
// 			//生成二维码
// 			createQRCode();
// 		});
// 		add(savaQRCode);
// 		setLayout(new GridLayout(8, 1));
// 		setResizable(false);
// 		setBounds(400, 200, 500, 320);
// 		setVisible(true);
// 		pack();
// 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
// 	}
//
// 	private void createQRCode() {
// 		try {
// 			HashMap<Object, Object> map = new HashMap<>();
// 			//设置编码格式
// 			map.put(EncodeHintType.CHARACTER_SET, CHARSET);
// 			//设置二维码内容大小量
// 			map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
// 			//设置二维码的白边大小
// 			map.put(EncodeHintType.MARGIN, 2);
//
// 			String content = new String(this.qrContent.getBytes("UTF-8"), "ISO-8859-1");
// 			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, this.qrSize, this.qrSize);
// 			this.qrcodePath += "/"+createFilenameByTime()+"."+FORMART;
// 			Path path = new File(this.qrcodePath).toPath();
// 			System.out.println(path);
// 			// MatrixToImageWriter.writeToPath(bitMatrix, FORMART, path);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
//
// 	private String createFilenameByTime(){
// 		//格式化时间
// 		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
// 		return sf.format(new Date());
// 	}
//
// 	public static void main(String[] args) {
// 		new CreateQRCodeFrame();
// 	}
//
// }
