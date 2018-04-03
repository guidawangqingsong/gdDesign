<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>接口列表列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="接口列表">
<grid:grid id="infcGridId" url="${adminPath}/sys/infc/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="接口编号"  name="code" />
    <grid:column label="接口名称"  name="name" />
    <grid:column label="请求类型"  name="reqType"  query="true"  queryMode="select"  condition="eq"  dict="reqType"/>
    <grid:column label="操作类型"  name="operType"  query="true"  queryMode="select"  condition="eq"  dict="operType"/>
    <grid:column label="使用设备"  name="useEqp"  dict="useEqp"/>
    <grid:column label="使用设备"  name="supportType"  dict="supportType"/>
    <grid:column label="使用设备"  name="interfaceUrl"/>
    <grid:column label="定义sql"  name="definedSql" />
    <grid:column label="请求body"  name="reqBody" />
    <grid:column label="成功时返回消息"  name="sucMsg" />
    <grid:column label="失败时返回消息"  name="failMsg" />
    <grid:column label="备注"  name="remark" />
	<grid:toolbar function="create" winwidth="60%" winheight="70%"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>