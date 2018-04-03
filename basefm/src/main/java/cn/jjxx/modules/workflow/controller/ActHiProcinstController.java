package cn.jjxx.modules.workflow.controller;

import cn.jjxx.core.common.data.DuplicateValid;
import cn.jjxx.core.model.AjaxJson;
import cn.jjxx.core.model.PageJson;
import cn.jjxx.core.model.ValidJson;
import cn.jjxx.core.query.annotation.PageableDefaults;
import cn.jjxx.core.query.data.PropertyPreFilterable;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.utils.QueryableConvertUtils;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import cn.jjxx.core.common.controller.BaseBeanController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import cn.jjxx.modules.workflow.entity.ActHiProcinst;
import cn.jjxx.modules.workflow.service.IActHiProcinstService;

/**   
 * @Title: 流程实例
 * @Description: 流程实例
 * @author jjxx
 * @date 2017-12-22 14:55:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/workflow/acthiprocinst")
@RequiresPathPermission("workflow:acthiprocinst")
public class ActHiProcinstController extends BaseBeanController<ActHiProcinst> {

    @Autowired
    protected IActHiProcinstService actHiProcinstService;

    public ActHiProcinst get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return actHiProcinstService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<ActHiProcinst> entityWrapper = new EntityWrapper<ActHiProcinst>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<ActHiProcinst> pagejson = new PageJson<ActHiProcinst>(actHiProcinstService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
     * @Description: 获取发起的任务 .<br>
     * @param queryable 查询条件.<br>
     * @author 郑成功 .<br>
     * @date 2017-12-22 下午3:33:50.<br>
     */
    public String getAjaxListString(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
            HttpServletResponse response){
    	EntityWrapper<ActHiProcinst> entityWrapper = new EntityWrapper<ActHiProcinst>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<ActHiProcinst> pagejson = new PageJson<ActHiProcinst>(actHiProcinstService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        return content;
    }
    
}
