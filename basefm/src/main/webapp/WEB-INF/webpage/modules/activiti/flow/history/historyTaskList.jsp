<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.task.history.title" /></title>
  <meta name="decorator" content="list"/>
  <script type="text/javascript" src="${staticPath }/uadmin/js/public-add.js"></script>
</head>
<body title="<spring:message code="activiti.task.history.title" />">
<grid:grid id="activitiModelId" sortname="t.id_" url="${adminPath}/activiti/flow/history/ajaxList" gridSetting="{loadComplete:onloadFun}">
	<grid:column label="activiti.task.id" hidden="true"   name="id" width="100"/>
	<grid:column label="activiti.execution.id"  name="executionId" hidden="true" />
	<grid:column label="activiti.procinst.id"  name="procInstId" query="true"  condition="like" />
	<grid:column label="activiti.procdef.id"  name="procDefId" />
	<grid:column label="activiti.task.process.name"  name="processName" />
	<grid:column label="activiti.task.activiti.name"  name="name" />
    <grid:column label="activiti.task.taskdefkey"  name="taskDefKey" />
    <grid:column label="activiti.task.assignee"  name="assigneeName" query="true"  condition="like"/>
    <grid:column label="activiti.task.starttime"  name="startTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.endtime"  name="endTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.duedate"  name="dueDate" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.claimtime"  name="claimTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.duration"  name="duration" />
    <grid:column label="activiti.task.history.formkey"  name="formKey"/>
    <grid:column label="activiti.task.description"  name="description"  />
	<grid:toolbar  function="search"  />
</grid:grid>