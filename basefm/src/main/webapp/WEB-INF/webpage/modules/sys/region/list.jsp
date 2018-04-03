<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>区域管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="区域管理">
<grid:grid id="regionGridId" async="true" treeGrid="true"  expandColumn="name"  url="${adminPath}/sys/region/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="编码"  name="code" />
    <grid:column label="名称"  name="name" />
    <grid:column label="上级"  name="parentName" />
    <grid:column label="排序"  name="regionOrder" />
    <grid:column label="英文名"  name="regionNameEn" />
    <grid:column label="简称"  name="regionSshortnameEn" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>