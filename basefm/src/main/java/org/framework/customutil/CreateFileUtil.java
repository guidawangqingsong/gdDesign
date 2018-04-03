package org.framework.customutil;

import java.io.File;  
import java.io.IOException;  

  
public class CreateFileUtil {  
	
    /**
     * @功能 创建文件.<br> 
     * @param destFileName 目标文件名.<br>
     * @return
     */
    public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
            return false;  
        }  
        //判断目标文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
                return false;  
            }  
        }  
        //创建目标文件  
        try {  
            if (file.createNewFile()) {  
                System.out.println("创建单个文件" + destFileName + "成功！");  
                return true;  
            } else {  
                System.out.println("创建单个文件" + destFileName + "失败！");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
            return false;  
        }  
    }  
     
    /**
     * @功能 创建目录文件夹.<br>
     * @param destDirName 文件夹路径.<br>
     * @return
     */
    public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }  
     
    /**
     * @功能 创建临时文件 .<br>
     * @param prefix 文件前缀名.<br>
     * @param suffix 文件后缀名.<br>
     * @param dirName 文件路径.<br>
     * @return 临时文件目录.<br>
     */
    public static String createTempFile(String prefix, String suffix, String dirName) {  
        File tempFile = null;  
        if (dirName == null) {  
            try{  
                //在默认文件夹下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix);  
                //返回临时文件的路径  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        } else {  
            File dir = new File(dirName);  
            //如果临时文件所在目录不存在，首先创建  
            if (!dir.exists()) {  
                if (!CreateFileUtil.createDir(dirName)) {  
                    System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");  
                    return null;  
                }  
            }  
            try {  
                //在指定目录下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix, dir);  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        }  
    } 
    
    /**
     * @功能 创建文件覆盖原来的文件 .<br>
     * @param file 文件.<br>
     * @return
     */
    public static boolean createNewFile(File file){
    	try {
    		if(file.exists()) { 
        		file.delete(); 
        	} 
        	file.createNewFile(); 
        	System.out.println("文件创建成果");
        	return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件创建失败");
			return false;
		}
    	
    }
     
    public static void main(String[] args) {  
        //创建目录  
        String dirName = "D:/work/temp/temp0/temp1/";  
        //CreateFileUtil.createDir(dirName);  
        //创建文件  
        String fileName = dirName + "/temp2/tempFile.txt";  
       boolean flag = CreateFileUtil.createFile(fileName); 
       if(!flag){
    	   createNewFile(new File(fileName));
       }
        /*//创建临时文件  
        String prefix = "temp";  
        String suffix = ".txt";  
        for (int i = 0; i < 2; i++) {  
            System.out.println("创建了临时文件："  
                    + CreateFileUtil.createTempFile(prefix, suffix, dirName));  
        }  
        //在默认目录下创建临时文件  
        for (int i = 0; i < 2; i++) {  
            System.out.println("在默认目录下创建了临时文件："  
                    + CreateFileUtil.createTempFile(prefix, suffix, null));  
        } */ 
    }  
  
}  
