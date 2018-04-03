package cn.jjxx.modules.sys.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.sys.mapper.AppVersionMapper;
import cn.jjxx.modules.sys.entity.AppVersion;
import cn.jjxx.modules.sys.service.IAppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: App版本管理
 * @Description: App版本管理
 * @author jjxx
 * @date 2018-01-12 23:40:56
 * @version V1.0   
 *
 */
@Transactional
@Service("appVersionService")
public class AppVersionServiceImpl  extends CommonServiceImpl<AppVersionMapper,AppVersion> implements  IAppVersionService {

}
