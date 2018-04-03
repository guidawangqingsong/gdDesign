package cn.jjxx.modules.codegen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jjxx.core.common.controller.BaseCRUDController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.modules.codegen.entity.Column;
@Controller
@RequestMapping("${admin.url.prefix}/codegen/column")
@RequiresPathPermission("codegen:column")
public class ColumnController extends BaseCRUDController<Column, String> {
 
}
