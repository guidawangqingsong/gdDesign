package org.framework.customutil.ftp;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.SocketException;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.commons.net.ftp.FTP;  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPFile;  

/**
 * @Title: FtpUtil.java .<br>
 * @Package org.framework.customutil.ftp .<br>
 * @Description: ftp文件服务器工具类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-11-24 上午10:41:44.<br>
 * @version V1.0.<br>
 */
public class FtpUtil {    
    private FTPClient ftpClient;    
    public static final int BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;    
    public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;    
        
    /** 
     * @discription 利用FtpConfig进行服务器连接  .<br>
     * @param ftpConfig 参数配置Bean类 .<br> 
     * @throws SocketException  .<br>
     * @throws IOException .<br> 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>  
     */  
    public void connectServer(FtpConfig ftpConfig) throws SocketException,    
            IOException {    
        String server = ftpConfig.getServer();    
        int port = ftpConfig.getPort();    
        String user = ftpConfig.getUsername();    
        String password = ftpConfig.getPassword();    
        String location = ftpConfig.getLocation();    
        connectServer(server, port, user, password, location);    
    }    
        
    /** 
     * @discription 使用详细信息进行服务器连接 .<br>
     * @param server：服务器地址名称 .<br>
     * @param port：端口号 .<br>
     * @param user：用户名 .<br>
     * @param password：用户密码 .<br>
     * @param path：转移到FTP服务器目录  .<br>
     * @throws SocketException .<br>
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>  
     */  
    public void connectServer(String server, int port, String user,    
            String password, String path) throws SocketException, IOException {    
        ftpClient = new FTPClient();    
        ftpClient.connect(server, port);    
        System.out.println("Connected to " + server + ".");    
        //连接成功后的回应码  
        System.out.println(ftpClient.getReplyCode());    
        ftpClient.login(user, password);    
        if (path!=null&&path.length() != 0) {    
            ftpClient.changeWorkingDirectory(path);    
        }    
        ftpClient.setBufferSize(1024);//设置上传缓存大小  
        ftpClient.setControlEncoding("UTF-8");//设置编码  
        ftpClient.setFileType(BINARY_FILE_TYPE);//设置文件类型  
    }    
      
    /** 
     * @Description 设置传输文件类型:FTP.BINARY_FILE_TYPE | FTP.ASCII_FILE_TYPE 二进制文件或文本文件  .<br>  
     * @param fileType 文件类型 .<br>
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:42:49.<br>
     */  
    public void setFileType(int fileType) throws IOException {    
        ftpClient.setFileType(fileType);    
    }    
    
    /**
     * @Description: 关闭连接  .<br>
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:42:49.<br>
     */
    public void closeServer() throws IOException {    
        if (ftpClient!=null&&ftpClient.isConnected()) {    
            ftpClient.logout();//退出FTP服务器   
            ftpClient.disconnect();//关闭FTP连接   
        }    
    }  
      
    /** 
     * @Description 转移到FTP服务器工作目录 .<br>  
     * @param path 路径 .<br>  
     * @return boolean 是否转移成功.<br>  
     * @throws IOException.<br> 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>  
     */  
    public boolean changeDirectory(String path) throws IOException {    
        return ftpClient.changeWorkingDirectory(path);    
    }    
      
    /** 
     * @Description 在服务器上创建目录 .<br> 
     * @param pathName 文件名称  .<br> 
     * @return boolean 是否创建成功 .<br> 
     * @throws IOException 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>
     */  
    public boolean createDirectory(String pathName) throws IOException {    
        return ftpClient.makeDirectory(pathName);    
    }    
      
    /** 
     * @Description 在服务器上删除目录 .<br>
     * @param path 文件目录 .<br>
     * @return boolean 是否删除成功 .<br>
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>
     */  
    public boolean removeDirectory(String path) throws IOException {    
        return ftpClient.removeDirectory(path);    
    }    
        
    /** 
     * @Description 删除所有文件和目录 .<br>   
     * @param path 目录 .<br>   
     * @param isAll true:删除所有文件和目录 
     * @return boolean .<br>   
     * @throws IOException .<br>    
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>
     */  
    public boolean removeDirectory(String path, boolean isAll) throws IOException {               
        if (!isAll) {    
            return removeDirectory(path);    
        }    
    
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);    
        if (ftpFileArr == null || ftpFileArr.length == 0) {    
            return removeDirectory(path);    
        }    
        for (FTPFile ftpFile : ftpFileArr) {    
            String name = ftpFile.getName();    
            if (ftpFile.isDirectory()) {    
                System.out.println("* [sD]Delete subPath ["+path + "/" + name+"]");                 
                removeDirectory(path + "/" + name, true);    
            } else if (ftpFile.isFile()) {    
                System.out.println("* [sF]Delete file ["+path + "/" + name+"]");                            
                deleteFile(path + "/" + name);    
            } else if (ftpFile.isSymbolicLink()) {    
    
            } else if (ftpFile.isUnknown()) {    
    
            }    
        }    
        return ftpClient.removeDirectory(path);    
    }    
      
    /** 
     * @Description: 检查目录在服务器上是否存在 true：存在  false：不存在 .<br>  
     * @param path 目录或文件路径.<br>  
     * @return boolean .<br>  
     * @throws IOException 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>
     */  
    public boolean existDirectory(String path) throws IOException {    
        boolean flag = false;    
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);    
        for (FTPFile ftpFile : ftpFileArr) {    
            if (ftpFile.isDirectory()    
                    && ftpFile.getName().equalsIgnoreCase(path)) {    
                flag = true;    
                break;    
            }    
        }    
        return flag;    
    }    
     
    /**
     * @Description: 得到文件列表,listFiles返回包含目录和文件，它返回的是一个FTPFile数组,
     * 				 listNames()：只包含目录的字符串数组,String[] fileNameArr = ftpClient.listNames(path); .<br>
     * @param path:服务器上的文件目录:/DF4 .<br>
     * @return List<String> .<br> 
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:32:20.<br>
     */
    public List<String> getFileList(String path) throws IOException {    
        FTPFile[] ftpFiles= ftpClient.listFiles(path);    
        //通过FTPFileFilter遍历只获得文件  
/*      FTPFile[] ftpFiles2= ftpClient.listFiles(path,new FTPFileFilter() { 
            @Override 
            public boolean accept(FTPFile ftpFile) { 
                return ftpFile.isFile(); 
            } 
        });  */  
        List<String> retList = new ArrayList<String>();    
        if (ftpFiles == null || ftpFiles.length == 0) {    
            return retList;    
        }    
        for (FTPFile ftpFile : ftpFiles) {    
            if (ftpFile.isFile()) {    
                retList.add(ftpFile.getName());    
            }    
        }    
        return retList;    
    }    
    
    /**
     * @Description: 删除服务器上的文件  .<br>
     * @param @param pathName 文件路径名 .<br>
     * @return boolean .<br> 
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:29:09.<br>
     */
    public boolean deleteFile(String pathName) throws IOException {    
        return ftpClient.deleteFile(pathName);    
    }    
    
    /**
     * @description: 上传文件到ftp服务器,在进行上传和下载文件的时候，
     * 				  设置文件的类型最好是：ftpUtil.setFileType(FtpUtil.BINARY_FILE_TYPE)  .<br>
     * @param remoteFileName 本地文件路径和名称 .<br>
     * @return boolean 服务器文件名称  .<br> 
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:27:03.<br>
     */
    public boolean uploadFile(String localFilePath, String remoteFileName)    
            throws IOException {    
        boolean flag = false;    
        InputStream iStream = null;    
        try {    
            iStream = new FileInputStream(localFilePath);    
            //我们可以使用BufferedInputStream进行封装  
            //BufferedInputStream bis=new BufferedInputStream(iStream);  
            //flag = ftpClient.storeFile(remoteFileName, bis);   
            flag = ftpClient.storeFile(remoteFileName, iStream);    
        } catch (IOException e) {    
            flag = false;    
            return flag;    
        } finally {    
            if (iStream != null) {    
                iStream.close();    
            }    
        }    
        return flag;    
    }
    
    /**
     * @description: 上传文件到ftp服务器,在进行上传和下载文件的时候，
     * 				  设置文件的类型最好是：ftpUtil.setFileType(FtpUtil.BINARY_FILE_TYPE)  .<br>
     * @param iStream 输入流.<br>
     * @param remoteFileName .<br>
     * @return boolean 服务器文件名称  .<br> 
     * @throws IOException .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:27:03.<br>
     */
    public boolean uploadAtlas(InputStream iStream, String remoteFileName)    
            throws IOException {    
        boolean flag = false;     
        try {    
            flag = ftpClient.storeFile(remoteFileName, iStream);    
        } catch (IOException e) {    
            flag = false;    
            return flag;    
        } finally {    
            if (iStream != null) {    
                iStream.close();    
            }    
        }    
        return flag;    
    } 
     
    /**
     * @Description: 上传文件到ftp服务器，上传新的文件名称和原名称一样  .<br>
     * @param fileName 文件名称  .<br>
     * @return boolean .<br> 
     * @throws IOException.<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:24:51.<br>
     */
    public boolean uploadFile(String fileName) throws IOException {    
        return uploadFile(fileName, fileName);    
    }    
    
    /**
     * @Description: 上传文件到ftp服务器 .<br>
     * @param iStream 输入流  .<br>
     * @param newName 新文件名称 .<br>
     * @throws IOException .<br>   
     * @return boolean .<br> 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:23:55.<br>
     */
    public boolean uploadFile(InputStream iStream, String newName)    
            throws IOException {    
        boolean flag = false;    
        try {    
            flag = ftpClient.storeFile(newName, iStream);    
        } catch (IOException e) {    
            flag = false;    
            return flag;    
        } finally {    
            if (iStream != null) {    
                iStream.close();    
            }    
        }    
        return flag;    
    }    

    /**
     * @description: 从ftp服务器上下载文件到本地 .<br>
     * @param remoteFileName ftp服务器上文件名称 .<br>
     * @param localFileName 本地文件名称  .<br>
     * @return boolean .<br> 
     * @throws IOException .<br> 
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:22:34.<br>
     */
    public boolean download(String remoteFileName, String localFileName)    
            throws IOException {    
        boolean flag = false;    
        File outfile = new File(localFileName);    
        OutputStream oStream = null;    
        try {    
            oStream = new FileOutputStream(outfile);    
            //我们可以使用BufferedOutputStream进行封装  
            //BufferedOutputStream bos=new BufferedOutputStream(oStream);  
            //flag = ftpClient.retrieveFile(remoteFileName, bos);   
            flag = ftpClient.retrieveFile(remoteFileName, oStream);    
        } catch (IOException e) {    
            flag = false;    
            return flag;    
        } finally {    
            oStream.close();    
        }    
        return flag;    
    }    
        
    /**
     * @discription 从ftp服务器上下载文件到本地 .<br>
     * @param sourceFileName：服务器资源文件名称  .<br>
     * @return InputStream 输入流  .<br>
     * @throws IOException  .<br>
     * @author 郑成功 .<br>
     * @date 2017-11-24 上午10:22:12.<br>
     */
    public InputStream downFile(String sourceFileName) throws IOException {   
    	return ftpClient.retrieveFileStream(sourceFileName);  
    }    
      
}  
