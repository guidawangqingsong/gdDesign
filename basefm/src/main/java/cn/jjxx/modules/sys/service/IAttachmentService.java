package cn.jjxx.modules.sys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jjxx.core.utils.upload.exception.InvalidExtensionException;
import cn.jjxx.modules.sys.entity.Attachment;

public interface IAttachmentService extends ICommonService<Attachment> {
	/**
	 * 
	 * @title: upload
	 * @description: 文件上传
	 * @param request
	 * @param file
	 * @return
	 * @return: Attachment
	 */
	public Attachment upload(HttpServletRequest request, MultipartFile file) throws FileSizeLimitExceededException,
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException;
	
	/**
	 * @Description: 上传保存 .<br>
	 * @param file 文件.<br>
	 * @param billId 单据Id.<br>
	 * @param relateTable 关联表.<br>
	 * @param relateFeild 关联字段.<br>
	 * @throws FileSizeLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileNameLengthLimitExceededException
	 * @throws IOException .<br>   
	 * @return Attachment .<br> 
	 * @author 郑成功 .<br>
	 * @date 2017-12-12 下午2:33:54.<br>
	 */
	public Attachment uploadAndSave(HttpServletRequest request, MultipartFile file,
			String billId,String relateTable,String relateFeild,String basePath) throws FileSizeLimitExceededException, 
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException;
	
	/**
	 * 
	 * @Description: 根据关联ID与关联表和关联字段信息 查询所有附件 并且以url,url的方式拼接返回所有文件地址 
	 * @param billId 上传附件实体的ID  比如user上传的附件 这里为userId
	 * @param relateTable 上传附件实体表名  比如user上传的附件 这里为sys_user，主要用来区分附件类型
	 * @param relateFeild 上传附件实体属性名   比如user上传的附件 这里为file_path ，主要用来区分附件类型 
	 * @return .  以url,url的方式拼接返回所有文件地址 
	 * @return String .
	 * @author 周恺 
	 * @date 2017年12月6日 下午5:08:31
	 */
	String selectFileNameByModel(String billId,String relateTable,String relateFeild);
	
	/**
	 * 
	 * @Description: 根据关联ID与关联表和关联字段信息 查询所有附件 并且以url,url的方式拼接返回所有文件地址 
	 * @param billId 上传附件实体的ID  比如user上传的附件 这里为userId
	 * @param relateTable 上传附件实体表名  比如user上传的附件 这里为sys_user，主要用来区分附件类型
	 * @param relateFeild 上传附件实体属性名   比如user上传的附件 这里为file_path ，主要用来区分附件类型 
	 * @return 返回所有附件对象.  
	 * @return String .
	 * @author 周恺 
	 * @date 2017年12月6日 下午5:08:31
	 */
	List<Attachment> selectAttachmentByModel(String billId,String relateTable,String relateFeild);
}
