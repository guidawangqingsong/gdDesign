package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.FileUtil;
import cn.jjxx.core.utils.IpUtils;
import cn.jjxx.core.utils.PropertiesUtil;
import cn.jjxx.core.utils.ServletUtils;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.core.utils.upload.FileUploadUtils;
import cn.jjxx.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jjxx.core.utils.upload.exception.InvalidExtensionException;
import cn.jjxx.modules.sys.entity.Attachment;
import cn.jjxx.modules.sys.mapper.AttachmentMapper;
import cn.jjxx.modules.sys.service.IAttachmentService;
import cn.jjxx.modules.sys.utils.UserUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("attachmentService")
public class AttachmentServiceImpl extends CommonServiceImpl<AttachmentMapper, Attachment>
		implements IAttachmentService {
	public static final String DEFAULT_CONFIG_FILE = "upload.properties";
	protected String configname = DEFAULT_CONFIG_FILE;
	private long maxSize = 0;
	private boolean needDatePathAndRandomName = true;
	private String[] allowedExtension;
	private String baseDir;

	@Override
	public Page<Attachment> selectPage(Page<Attachment> page, Wrapper<Attachment> wrapper) {
		Page<Attachment> pageInfo = new Page<Attachment>();
		wrapper.eq("1", "1");
		int total = baseMapper.selectCount(wrapper);
		List<Attachment> records = baseMapper.selectAttachmentPage(page, wrapper);
		pageInfo.setTotal(total);
		pageInfo.setRecords(records);
		return pageInfo;
	}

	@PostConstruct
	public void initAttachement() {
		PropertiesUtil propertiesUtil = new PropertiesUtil(configname);
		maxSize = propertiesUtil.getLong("upload.max.size");
		baseDir = propertiesUtil.getString("upload.base.dir");
		String extension = propertiesUtil.getString("upload.allowed.extension");
		allowedExtension = extension.split(",");
	}

	@Override
	public Attachment upload(HttpServletRequest request, MultipartFile file) throws FileSizeLimitExceededException,
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException {
		//上传表的信息
		String billId = request.getParameter("billId");
		String relateTable = request.getParameter("relateTable");
		String relateFeild = request.getParameter("relateFeild");
		return uploadAndSave(request, file, billId, relateTable, relateFeild,null);
	}
	
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
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException  {
		if(StringUtils.isNotEmpty(basePath)){
			baseDir = basePath;
		}
		String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize,
				needDatePathAndRandomName);
		String filename = file.getOriginalFilename();
		long size = file.getSize();
		String realFileName = StringUtils.getFileNameNoEx(filename);
		String fileext = StringUtils.getExtensionName(filename);
		// 保存上传的信息
		Attachment attachment = new Attachment();
		attachment.setFileext(fileext);
		attachment.setFilename(realFileName);
		attachment.setFilepath(url);
		attachment.setFilesize(size);
		attachment.setStatus("1");
		attachment.setUploadip(IpUtils.getIpAddr(request));
		attachment.setUploadtime(new Date());
		attachment.setUser(UserUtils.getUser());
		attachment.setBillid(billId);
		attachment.setRelateTable(relateTable);
		attachment.setRelateFeild(relateFeild);
		insert(attachment);
		return attachment;
	}
	
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		for (Object object : idList) {
			deleteById((Serializable) object);
		}
		return true;
	}

	@Override
	public boolean deleteById(Serializable id) {
		Attachment attachment = selectById(id);
		String basePath = ServletUtils.getRequest().getServletContext().getRealPath("/");
		String filePath = attachment.getFilepath();
		FileUtil.delFile(basePath + filePath);
		return super.deleteById(id);
	}

	@Override
	public String selectFileNameByModel(String billId, String relateTable,
			String relateFeild) {
		EntityWrapper<Attachment> attachmentWrapper = new EntityWrapper<Attachment>(Attachment.class);
		attachmentWrapper.eq("billId", billId);
		attachmentWrapper.eq("relateTable",relateTable);
		attachmentWrapper.eq("relateFeild",relateFeild);
		List<Attachment> attachmentList = selectList(attachmentWrapper);
		StringBuffer filePath = new StringBuffer();
		for (int i=0;i<attachmentList.size();i++) {
			if(i==attachmentList.size()-1){
				filePath.append(attachmentList.get(i).getFilepath());
			}else{
				filePath.append(attachmentList.get(i).getFilepath()+",");
			}			
		}
		return filePath.toString();
	}

	@Override
	public List<Attachment> selectAttachmentByModel(String billId,
			String relateTable, String relateFeild) {
		EntityWrapper<Attachment> attachmentWrapper = new EntityWrapper<Attachment>(Attachment.class);
		attachmentWrapper.eq("billId", billId);
		attachmentWrapper.eq("relateTable",relateTable);
		attachmentWrapper.eq("relateFeild",relateFeild);
		List<Attachment> attachmentList = selectList(attachmentWrapper);
		return attachmentList;
	}

}
