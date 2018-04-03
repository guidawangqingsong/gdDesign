<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>流程实例列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="流程实例">
<grid:grid id="actHiProcinstGridId" url="${adminPath}/workflow/acthiprocinst/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="ID_"  name="id" />
    <grid:column label="PROC_INST_ID_"  name="procInstId" />
    <grid:column label="BUSINESS_KEY_"  name="businessKey" />
    <grid:column label="PROC_DEF_ID_"  name="procDefId" />
    <grid:column label="START_TIME_"  name="startTime" />
    <grid:column label="END_TIME_"  name="endTime" />
    <grid:column label="DURATION_"  name="duration" />
    <grid:column label="START_USER_ID_"  name="startUserId" />
    <grid:column label="START_ACT_ID_"  name="startActId" />
    <grid:column label="END_ACT_ID_"  name="endActId" />
    <grid:column label="SUPER_PROCESS_INSTANCE_ID_"  name="superProcessInstanceId" />
    <grid:column label="DELETE_REASON_"  name="deleteReason" />
    <grid:column label="TENANT_ID_"  name="tenantId" />
    <grid:column label="NAME_"  name="name" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>