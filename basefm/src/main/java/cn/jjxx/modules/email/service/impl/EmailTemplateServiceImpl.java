package cn.jjxx.modules.email.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.email.entity.EmailTemplate;
import cn.jjxx.modules.email.mapper.EmailTemplateMapper;
import cn.jjxx.modules.email.service.IEmailTemplateService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 邮件发送模板
 * @Description: 邮件发送模板
 * @author jeeweb
 * @date 2017-06-10 07:46:14
 * @version V1.0   
 *
 */
@Transactional
@Service("emailTemplateService")
public class EmailTemplateServiceImpl  extends CommonServiceImpl<EmailTemplateMapper,EmailTemplate> implements  IEmailTemplateService {

}
