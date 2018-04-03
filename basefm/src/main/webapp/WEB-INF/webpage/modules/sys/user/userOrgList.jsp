<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.dict.title" /></title>
  <meta name="decorator" content="grid-select"/>
</head>
<body title="<spring:message code="sys.dict.title" />">
<grid:grid id="groupGridId" url="${adminPath}/sys/user/ajaxListConfig?gid=${user.id}" >
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.key" hidden="true"   name="organization.id" width="100"/>
	<grid:column label="sys.common.key" hidden="true"   name="user.id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.user.role"  groupname="opt" function="updateObj" outclass="btn-primary" winwidth="300px"  innerclass="fa-user" url="${adminPath}/sys/user/authRole" />
	<grid:button title="sys.user.per"   groupname="opt" function="updateObj" outclass="btn-info"    winwidth="300px"  innerclass="fa-key" url="${adminPath}/sys/user/authMenu" />
	<grid:button   groupname="opt" function="delete"  url="${adminPath}/sys/user/{id}/deleteOrg"/>
	<grid:column label="sys.user.realname"  name="user.realname"  width="50"/>
   	<grid:column label="sys.organization.code"  name="organization.code" width="50"/>
	<grid:column label="sys.organization.name"  name="organization.name" width="120"/>
    <grid:column label="sys.organization.type"  name="organization.type"  dict="orggroup" width="50"/>
	<grid:toolbar  function="updateObj" outclass="btn-primary" winwidth="300px" icon="fa-plus" title="添加组织" url="${adminPath}/sys/user/authOrg?gid=${user.id}"  />
	<grid:toolbar  function="delete"   url="${adminPath}/sys/user/batch/deleteOrg" />
	<%-- <grid:toolbar  function="search"  />
	<grid:toolbar  function="reset"  /> --%>

</grid:grid>
</body>
</html>