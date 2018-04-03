<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.task.title" /></title>
  <meta name="decorator" content="list"/>
  <script type="text/javascript" src="${staticPath }/uadmin/js/public-add.js"></script>
</head>
<body title="<spring:message code="activiti.task.title" />">
<grid:grid id="activitiModelId" sortname="t.id_" url="${adminPath}/activiti/flow/personal/run/ajaxList" gridSetting="{loadComplete:onloadFun}">
	<grid:column label="activiti.task.id" hidden="true" name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="40"/>
	<grid:column label="activiti.task.type" name="flowType"/>
	<grid:column label="activiti.task.process.name" name="flowName" />
	<grid:column label="activiti.task.activiti.name"  name="name" />
    <grid:column label="activiti.task.taskdefkey"  name="taskDefKey" />
    <grid:column label="activiti.task.description"  name="description"  />	
    <grid:column label="activiti.task.startor" width="80" name="startor" />
    <grid:column label="activiti.task.starttime" width="80" name="createTime" formatter="date" dateformat="yyyy-MM-dd hh:mm:ss"/>
    <grid:column label="activiti.task.duedate" width="80" name="dueDate" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.suspension.state" width="60" name="suspensionState" dict="lcgq"/>
	<grid:button groupname="opt" onclick="showAuditing(row.id)" title="activiti.task.handle" outclass="btn-info"  innerclass="fa-video-camera" />	
	<%-- <grid:toolbar  function="search"  /> --%>
</grid:grid>

<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>

<script type="text/javascript">

function showAuditing(id){
	showAudite('${adminPath}',id);	
}

</script>
