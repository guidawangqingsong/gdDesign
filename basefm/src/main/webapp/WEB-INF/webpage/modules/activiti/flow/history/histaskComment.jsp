<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.task.history.title" /></title>
  <meta name="decorator" content="grid-select"/>
  <style type="text/css">
  	.page-content{padding:0;}
  </style>
</head>
<body title="<spring:message code="activiti.task.history.title" />">
<grid:grid id="activitiModelId" sortname="t.id_" url="${adminPath}/activiti/flow/history/histaskComment?taskId=${param.taskId}">
	<grid:column label="activiti.task.id" hidden="true"   name="id" width="100"/>
	<grid:column label="activiti.task.activiti.name"  name="name" />
    <grid:column label="activiti.task.assignee"  name="assigneeName"/>
    <grid:column label="activiti.task.history.duration"  name="type" />
    <grid:column label="activiti.task.history.formkey"  name="message"/>
    <grid:column label="activiti.task.history.endtime"  name="endTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.description"  name="attachName"  />
</grid:grid>