package org.framework.customutil.qrcode.handler;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class J2SEImageGucas implements QRCodeImage{

	BufferedImage image;  
    
    public J2SEImageGucas(BufferedImage image) {  
        this.image = image;  
    }  
  
    public int getWidth() {  
        return image.getWidth();  
    }  
  
    public int getHeight() {  
        return image.getHeight();  
    }  
  
    public int getPixel(int x, int y) {  
        return image.getRGB(x, y);  
    }  
}
