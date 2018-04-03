<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.predef.title" /></title>
  <meta name="decorator" content="grid-select"/>
</head>
<body title="<spring:message code="activiti.predef.title" />">
<grid:grid id="activitiModelId" multiselect="false" url="${adminPath}/activiti/deploy/predef/ajaxList?modelId=${param.modelId}">
	<grid:column label="activiti.predef.id" hidden="true" name="id" width="100" />
    <grid:column label="activiti.predef.name"  name="name" />
    <grid:column label="activiti.predef.key"  name="key" />
    <grid:column label="activiti.predef.version"  name="version" />
    <grid:column label="activiti.predef.deployid"  name="deploymentId" />
    <grid:column label="activiti.predef.resource.name"  name="resourceName" />
    <grid:column label="activiti.predef.resource.img.name"  name="diagramResourceName" />
    <grid:column label="activiti.predef.discription"  name="description"  />
    <grid:column label="activiti.predef.suspension.state"  name="suspensionState" dict="lcgq" />
    <%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="120"/>
	<grid:button groupname="opt" exp="row.suspensionState==1" function="rowDialogDetailRefresh" title="activiti.predef.hangup" tipMsg="你确定要挂起吗?" outclass="btn-primary" innerclass="fa-hourglass-start" url="${adminPath}/activiti/deployModel?modelId={id}&status="/>
	<grid:button groupname="opt" exp="row.suspensionState==2" function="rowDialogDetailRefresh" title="activiti.predef.activate" tipMsg="你确定要激活吗?" outclass="btn-primary"  innerclass="fa-hourglass-start" url="${adminPath}/activiti/deployModel?modelId={id}" /> --%>
</grid:grid>