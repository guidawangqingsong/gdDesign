package org.framework.customutil.ftp;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FtpTest {

	private long imageId ; //图片的id
	private long folderId;//文件夹名
	
	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}
	
	public String viewImages(HttpServletResponse response){
        ApacheFtpUtil.downFiles(response,folderId,imageId);
        return null;
	}
	//这个方法只适合查询少量的图片，少量不能大于ftp服务器连接的的最大值
	public String test(HttpServletResponse response){
        ServletOutputStream output = null;
        InputStream input = null;
        try {
            input = ApacheFtpUtil.downFile(folderId,imageId);
            output = response.getOutputStream();
            response.setContentType("image/jpeg");
            byte imageArray[] = new byte[4064];
			int len = 0;
			while((len = input.read(imageArray)) != -1){
				output.write(imageArray, 0, len);
			}
			output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (input != null) {
        			input.close();
        		}
        		if (output != null) {
                	output.close();
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        return null;
	}
	
	/**查询自制关卡*/
	/*public String showUpload(HttpServletResponse response,int id){
		ServletOutputStream output = null;
        try {
        	HttpServletResponse response = ServletActionContext.getResponse();
            output = response.getOutputStream();
            response.setContentType("image/jpeg");
            byte [] uploadImageArray = UploadRedisManager.getUpLoadIMG(id);
			output.write(uploadImageArray);
			output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (output != null) {
                	output.close();
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
		return null;
	}*/
	
	public static void main(String[] args) {
		try {
			FtpUtil ftpUtil = ApacheFtpUtil.initFtpConfig("ftp.properties");		
		    
		  //testUpload();  
		    //testDownload();	    	    
		    /*ftpip =  pu.readProperty("FTP_IP");
		    ftpPort =  Integer.valueOf(pu.readProperty("FTP_PORT"));
		    ftpname = pu.readProperty("FTP_NAME");
		    ftppassword =  pu.readProperty("FTP_PASSWORD");*/
		    //获得ftp服务器上目录名称为DF4下的所有文件名称  
		    List<String> list=ftpUtil.getFileList("/test/");  
		    System.out.println("文件名称列表为:"+list);  
		    //上传本地D盘文件aaa.txt到服务器，服务器上名称为bbb.txt  
//		    boolean back = ftpUtil.uploadFile("d:" + File.separator + "zbs.txt", "/DF4/zbshh.txt");
//		    System.out.println("上传是否成功="+back);
		    //从服务器上下载文件bbb.txt到本地d盘名称为ccc.txt  
		    
		    
//		    
//		    boolean back = ftpUtil.download("eee.txt", "d:" + File.separator + "fff.txt");  
//		    System.out.println("上传是否成功="+back);
//		    
		    
		    
		    //删除ftp服务器上文件:bbb.txt  
		    //ftpUtil.deleteFile("bbb.txt");
		    
		    
//		    boolean back = ftpUtil.createDirectory("20170327");
//		    System.out.println("创建目录成功="+back);
		    
//		    InputStream in = ApacheFtpUtil.downFile(1,1);
//		    System.out.println(in);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
