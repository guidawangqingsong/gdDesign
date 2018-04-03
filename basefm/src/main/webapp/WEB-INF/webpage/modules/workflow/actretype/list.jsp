<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>流程类型表列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="流程类型表">
<grid:grid id="actReTypeGridId" async="true" treeGrid="true"  expandColumn="name"  url="${adminPath}/workflow/actretype/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="分类名称"   name="name" width="100"/>
	<grid:column label="描述" name="remarks" width="100"/>
	<grid:column label="创建人" name="createBy.realname" width="100"/>
	<grid:column label="创建时间" name="createDate" width="100"/>
	
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>