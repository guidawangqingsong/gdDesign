<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>act_id_group列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="act_id_group">
<grid:grid id="actIdGroupGridId" sortname="t.id_" url="${adminPath}/activiti/deploy/group/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="ID_"  name="id" />
    <grid:column label="REV_"  name="rev" />
    <grid:column label="NAME_"  name="name" />
    <grid:column label="TYPE_"  name="type" />
	<grid:toolbar function="create" url="${adminPath}/activiti/deploy"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>