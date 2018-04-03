<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>App版本管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="App版本管理">
<grid:grid id="appVersionGridId" url="${adminPath}/sys/appversion/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="编码"  name="code" />
    <grid:column label="名称"  name="name" />
    <grid:column label="使用机型"  name="type"  query="true"  queryMode="select"  condition="eq"  dict="appType"/>
    <grid:column label="版本号"  name="version" />
    <grid:column label="是否最新"  name="versionStatus"  query="true" queryMode="select" condition="eq" dict="sf"/>
    <grid:column label="下载地址"  name="downloadUrl" />
    <grid:column label="二维码地址"  name="qrcodeUrl" />
    <grid:column label="描述"  name="description" />
	<grid:toolbar function="create" btnclass="btn-green"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>