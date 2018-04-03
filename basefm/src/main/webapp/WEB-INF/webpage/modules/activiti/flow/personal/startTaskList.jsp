<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.procinst.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="activiti.procinst.title" />">
<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>
<grid:grid id="actHiProcinstGridId" sortname="t.id_" url="${adminPath}/activiti/flow/personal/start/ajaxList" >
	<grid:column label="sys.common.key" hidden="true"  name="id" width="100"/>
	<grid:column label="任务Id"  name="taskId" hidden="true"/> 
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="120"/>
	<grid:button groupname="opt" onclick="showflow('${adminPath}',row.id)" title="activiti.task.flow.chart" outclass="btn-info"  innerclass="fa-video-camera" />	
	<grid:button groupname="opt" function="rowConfirm" exp="row.taskId!=null" title="activiti.closed" tipMsg="你确定要关闭该任务吗?" 
		outclass="btn-primary" innerclass="fa-times" url="${adminPath}/activiti/flow/endProcess?taskId={id}"/>	
	<grid:column label="流程类型"  name="flowType" />
	<grid:column label="单据编号"  name="businessKey" />
    <grid:column label="流程名称"  name="flowName" />    
    <grid:column label="开始时间"  name="startTime"/>
    <grid:column label="结束时间"  name="endTime" />
    <grid:column label="时长(ms)"  name="duration" />
    <grid:column label="版本号"  name="version" />
    <grid:column label="启动人"  name="startor" />
    <grid:column label="启动实例Id"  name="startActId" />
    <grid:column label="结束实例Id"  name="endActId" />
    <grid:toolbar  function="search"  />	
</grid:grid>
</body>
</html>