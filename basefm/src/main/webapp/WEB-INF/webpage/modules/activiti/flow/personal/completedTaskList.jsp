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
<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>
<grid:grid id="activitiModelId" sortname="t.id_" url="${adminPath}/activiti/flow/personal/history/ajaxList" gridSetting="{loadComplete:onloadFun}">
	<grid:column label="activiti.task.id" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="80"/>
	<grid:column label="activiti.task.type" name="flowType"/>
	<grid:column label="activiti.task.process.name"  name="processName" />
	<grid:column label="activiti.task.activiti.name"  name="name" />
    <grid:column label="activiti.task.taskdefkey"  name="taskDefKey" />
    <grid:column label="activiti.task.startor"  name="startor"/>
    <grid:column label="activiti.task.starttime"  name="startTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.endtime"  name="endTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.duedate"  name="dueDate" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.claimtime"  name="claimTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.history.duration"  name="duration" />
    <grid:column label="activiti.task.description"  name="description"  />
    <grid:button groupname="opt" onclick="showflow('${adminPath}',row.id)" title="activiti.task.flow.chart" outclass="btn-info"  innerclass="fa-video-camera" />	
	<%-- <grid:toolbar  function="search"  /> --%>
</grid:grid>
