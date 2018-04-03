package org.framework.customutil.ftp;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.framework.customutil.PropertiesUtil;


/**
 * 此类提供对完调用的方法
 * 
 * */

public class ApacheFtpUtil {
	
	public static String FTP_IP =  "ftp.service.ip";
	public static String FTP_PORT =  "ftp.service.port";
	public static String FTP_USERNAME = "ftp.service.username";
	public static String FTP_PASSWORD =  "ftp.service.password";
	public static String FTP_LOCALTION =  "ftp.service.location";
	
	private static FtpConfig ftp = new FtpConfig();
	
	/**
	 * @Description: 初始化,设置ftp服务器连接信息 .<br>
	 * @param ftpProperties .<br>
	 * @throws SocketException .<br>
	 * @throws IOException .<br>   
	 * @return FtpUtil .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-24 上午11:02:53.<br>
	 */
	public static FtpUtil initFtpConfig(String ftpProperties) throws SocketException, IOException{
		FtpUtil ftpUtil=new FtpUtil();
		setFtpConfig(ftpProperties);
		ftpUtil.connectServer(ftp); 
		return ftpUtil;  
	}
	
	/**
	 * @Description: 初始化，设置ftp连接信息 .<br>
	 * @return void .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-11-24 上午11:02:07.<br>
	 */
	private static void setFtpConfig(String ftpProperties){
		PropertiesUtil pu = new PropertiesUtil(ftpProperties);
		System.out.println(pu.readProperty("ftp.service.id"));
		ftp.setServer(pu.readProperty(FTP_IP));
		ftp.setPort(Integer.valueOf(pu.readProperty(FTP_PORT)));
		ftp.setUsername(pu.readProperty(FTP_USERNAME));
		ftp.setPassword(pu.readProperty(FTP_PASSWORD));
	}
	
	/**
	 * 在ftp服务器上新建文件夹
	 * */
	public static boolean createDirectory(String dirPath){
		FtpUtil ftpUtil = new FtpUtil();  
		try {
			ftpUtil.connectServer(ftp); 
			return ftpUtil.createDirectory(dirPath);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ftpUtil.closeServer();
			} catch (IOException e) {
			}
			ftpUtil = null;
		}
		return false;
	}
	
	/**上传茬点
	 * name 文件的名称 
	 * address 上传的地址
	 * */
	public static boolean uploadAtlas(InputStream iStream ,String uploadPath){
		FtpUtil ftpUtil = new FtpUtil();  
		boolean bool = false;
		 try {
			 ftpUtil.connectServer(ftp); 
			//上传本地D盘文件aaa.txt到服务器，服务器上名称为bbb.txt  
			bool = ftpUtil.uploadAtlas(iStream, uploadPath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ftpUtil.closeServer();
			} catch (IOException e) {
			}
			ftpUtil = null;
		}  
		return bool;
	}
	
	/**获取上传茬点的名称*/
	public static List<String> getAtlasNameList(long atlasId){
		FtpUtil ftpUtil = new FtpUtil(); 
		List<String> atlasNameList = new ArrayList<String>();
		try {
			ftpUtil.connectServer(ftp); 
			atlasNameList = ftpUtil.getFileList("/"+atlasId+"/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atlasNameList;
	}
	
	/**获取茬点图片的流
	 * 这个方法只适合查询少量的图片，少量不能大于ftp服务器连接的的最大值
	 * 这个方法没有关闭ftp连接
	 * */
	public static InputStream downFile(long atlasId,long imageId){
		FtpUtil ftpUtil = new FtpUtil(); 
		InputStream input = null;
		try {
			ftpUtil.connectServer(ftp); 
			//String path = "/"+atlasId+"/"+imageId+".png";
			String path = "/test/timg.jpg";
			System.out.println(path);
			input = ftpUtil.downFile(path);
			return input;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**获取茬点图片的流*/
	public static InputStream downFiles(HttpServletResponse response,long atlasId,long imageId){
		FtpUtil ftpUtil = new FtpUtil(); 
		InputStream input = null;
		ServletOutputStream output = null;		
		try {
			ftpUtil.connectServer(ftp); 
			//String path = "/"+atlasId+"/"+imageId+".png";
			String path = "/test/timg.jpg";
			System.out.println(path);
			input = ftpUtil.downFile(path);
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
        		ftpUtil.closeServer();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        return null;
	}
	
}
