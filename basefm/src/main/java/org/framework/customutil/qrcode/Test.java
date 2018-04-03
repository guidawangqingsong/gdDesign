package org.framework.customutil.qrcode;

import org.framework.customutil.qrcode.entity.QRCodeParams;
import org.framework.customutil.qrcode.exception.QRParamsException;
import org.framework.customutil.qrcode.handler.QRCodeEncoderHandler;

public class Test {

	public static void main(String[] args) {
		createQrcode();
		//QRCodeDecoderHandler.decoderQRCode("d://core//test.png");
	}
	
	private static void createQrcode(){
		QRCodeParams params = new QRCodeParams();
		params.setTxt("http://www.w3school.com.cn/");
		params.setQrCodeUrl("http://www.w3school.com.cn/");
		params.setLogoPath("d:/logo.png");
		params.setFilePath("d:/core");
		params.setFileName("test.png");
		params.setOnColor(0x99999900);
		params.setOffColor(0x44552200);
		try {
			QRCodeEncoderHandler.generateQRImage(params);
		} catch (QRParamsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
