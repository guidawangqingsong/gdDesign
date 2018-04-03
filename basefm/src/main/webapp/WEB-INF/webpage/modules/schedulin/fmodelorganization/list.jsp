<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>模块组织树列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="模块组织树">
<grid:grid id="fModelOrganizationGridId" async="true" treeGrid="true"  expandColumn="name"  url="${adminPath}/schedulin/fmodelorganization/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="编码"  name="code" />
    <grid:column label="名称"  name="name" />
    <grid:column label="父级id"  name="parent_id" />
    <grid:column label="上级所有id路径"  name="parent_ids" />
    <grid:column label="模块类别"  name="category" />
    <grid:column label="类型"  name="type" />
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete"/>
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>