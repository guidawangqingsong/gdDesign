<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>职员管理列表</title>
  <meta name="decorator" content="grid-select"/> 
  <html:css  name="iCheck,Validform"/>
  <html:js  name="iCheck,Validform"/>
</head>
<body title="<spring:message code="sys.staff" />">
<grid:grid id="staffGridId" url="${adminPath}/sys/staff/ajaxList" multiselect="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="员工编号"  name="code"  query="true"  condition="like"/>
    <grid:column label="姓名"    name="name"   query="true"  condition="like"/>
    <grid:column label="手机号"  name="moblie"  query="true"  condition="like"/>
    <grid:column label="岗位"    dict="staffJob"   name="position" />
	<grid:column label="电子邮箱 "  hidden="true"   name="email" />
	<grid:column label="默认组织 "  name="orgName" />
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>
