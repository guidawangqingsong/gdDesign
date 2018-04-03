<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>流程监听列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="流程监听">
<grid:grid id="actListenGridId" url="${adminPath}/workflow/actlisten/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="code"  name="code" />
    <grid:column label="name"  name="name" />
    <grid:column label="监听类型"  name="listenType"  query="true"  queryMode="select"  condition="eq"  dict="listenType"/>
    <grid:column label="事件类型"  name="eventType"  query="true"  queryMode="select"  condition="eq"  dict="eventType"/>
    <grid:column label="值类型"  name="valType"  dict="valType"/>
    <grid:column label="类路径/表达式"  name="listenValue" />
    <grid:column label="描述"  name="description" />
    <grid:column label="状态（0：启用；1：禁用）"  name="status"  dict="sf"/>
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>