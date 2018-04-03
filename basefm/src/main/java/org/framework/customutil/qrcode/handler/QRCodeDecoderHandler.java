package org.framework.customutil.qrcode.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

public class QRCodeDecoderHandler {

	/**
	 * @Description: 二维码解码 .<br>
	 * @param @param imgPath 图片路径.<br>
	 * @return String 返回解析类容.<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-8-31 下午5:10:02.<br>
	 */
    public static String decoderQRCode(String imgPath) {  
  
    	// QRCode 二维码图片的文件  
        File imageFile = new File(imgPath);  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(imageFile);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new J2SEImageGucas(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }  
    
}
