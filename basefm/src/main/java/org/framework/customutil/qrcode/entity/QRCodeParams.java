package org.framework.customutil.qrcode.entity;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

/**
 * @Title: QRCodeParams.java .<br>
 * @Package org.framework.customutil.qrcode.entity .<br>
 * @Description: 二维码参数信息类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-8-31 下午3:29:59.<br>
 * @version V1.0.<br>
 */
public class QRCodeParams { 
	/** 二维码内容  */
    private String txt; 
    /** 二维码网络路径  */
    private String qrCodeUrl;
    /** 二维码生成物理路径 */
    private String filePath;     
    /** 二维码生成图片名称（包含后缀名） */
    private String fileName;  
    /** logo图片 */
    private String logoPath;             
    /** 二维码宽度 */
    private Integer width = 300;             
    /** 二维码高度 */
    private Integer height = 300;
    /**前景色 */
    private Integer onColor = 0xFF000000;
    /**背景色*/
    private Integer offColor = 0xFFFFFFFF;  
    /**白边大小，取值范围0~4*/
    private Integer margin = 1;             
    /**二维码容错率  */
    private ErrorCorrectionLevel level = ErrorCorrectionLevel.L;
    
    
    public String getTxt() {  
        return txt;  
    }  
    public void setTxt(String txt) {  
        this.txt = txt;  
    }  
    public String getFilePath() {  
        return filePath;  
    }  
    public void setFilePath(String filePath) {  
        this.filePath = filePath;  
    }  
    public String getFileName() {  
        return fileName;  
    }  
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    }  
    public Integer getWidth() {  
        return width;  
    }  
    public void setWidth(Integer width) {  
        this.width = width;  
    }  
    public Integer getHeight() {  
        return height;  
    }  
    public void setHeight(Integer height) {  
        this.height = height;  
    }  
    public String getQrCodeUrl() {  
        return qrCodeUrl;  
    }  
    public void setQrCodeUrl(String qrCodeUrl) {  
        this.qrCodeUrl = qrCodeUrl;  
    }  
    public String getLogoPath() {  
        return logoPath;  
    }  
    public void setLogoPath(String logoPath) {  
        this.logoPath = logoPath;  
    }  
    public Integer getOnColor() {  
        return onColor;  
    }  
    public void setOnColor(Integer onColor) {  
        this.onColor = onColor;  
    }  
    public Integer getOffColor() {  
        return offColor;  
    }  
    public void setOffColor(Integer offColor) {  
        this.offColor = offColor;  
    }  
    public Integer getMargin() {  
        return margin;  
    }  
    public void setMargin(Integer margin) {  
        this.margin = margin;  
    }
    public ErrorCorrectionLevel getLevel() {  
        return level;  
    }  
    public void setLevel(ErrorCorrectionLevel level) {  
        this.level = level;  
    }  
  
    /** 
     * 返回文件后缀名 
     * @return 
     */  
    public String getSuffixName(){  
        String imgName = this.getFileName();  
        if(imgName != null && !"".equals(imgName)){  
            String suffix=fileName.substring(fileName.lastIndexOf(".")+1);  
            return suffix;  
        }  
        return "";  
    }  
}  