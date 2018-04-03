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
<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>
<grid:grid id="activitiModelId" sortname="t.id_" url="${adminPath}/activiti/flow/task/ajaxList" gridSetting="{loadComplete:onloadFun}">
	<grid:column label="activiti.task.id" hidden="true" name="id" width="100"/>
	<grid:column label="activiti.execution.id" hidden="true" name="executionId" />
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="220"/>
	<grid:column label="activiti.procinst.id" width="60" name="procInstId" />
	<grid:column label="activiti.procdef.id"  name="procDefId" />
	<grid:column label="activiti.task.process.name" name="flowName" />
	<grid:column label="activiti.task.activiti.name"  name="name" />
    <grid:column label="activiti.task.taskdefkey"  name="taskDefKey" />
    <grid:column label="activiti.task.assignee" width="80" name="assigneeName" />
    <grid:column label="activiti.task.starttime" width="80" name="createTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.duedate" width="80" name="dueDate" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.task.suspension.state" width="60" name="suspensionState" dict="lcgq"/>
    <grid:column label="activiti.task.description"  name="description"  />
	<grid:toolbar title="转办" onclick="transTask()" btnclass="btn-green"/>
	<grid:button groupname="opt" function="rowDialogDetailRefresh" title="activiti.task.flow.chart" outclass="btn-blue"  innerclass="fa-eye" url="${adminPath}/activiti/flow/flowchart?taskId=\"+row.id+\"" winwidth="60%" winheight="800" />
	<grid:button groupname="opt" function="rowDialogDetailRefresh" title="activiti.task.approval.record" outclass="btn-info"  innerclass="fa-video-camera" url="${adminPath}/activiti/flow/histaskComment?taskId=\"+row.id+\"" winwidth="60%" />
	<grid:button groupname="opt" exp="row.suspensionState==1" function="rowConfirm" title="activiti.hangup" tipMsg="你确定要挂起该任务吗?" outclass="btn-yellow" innerclass="fa-ban" url="${adminPath}/activiti/flow/suspendProcessInstance?taskId={id}"/>
	<grid:button groupname="opt" exp="row.suspensionState==2" function="rowConfirm" title="activiti.activate" tipMsg="你确定要激活该任务吗?" outclass="btn-primary" innerclass="fa-hourglass-start" url="${adminPath}/activiti/flow/activateProcessInstance?taskId={id}"/>
	<grid:button groupname="opt" function="rowConfirm" title="activiti.closed" tipMsg="你确定要关闭该任务吗?" outclass="btn-primary" innerclass="fa-times" url="${adminPath}/activiti/flow/endProcess?taskId={id}"/>
	<grid:toolbar  function="search"  />
</grid:grid>

<script type="text/javascript">

/**
 * @description 转办任务.<br> 
 * @date 2017/12/26 .<br>
 * @author zcg .<br>
 */
function transTask(){
	var basePath = '${adminPath}';
	var gridId = 'activitiModelIdGrid';
	var rows =$("#"+gridId).jqGrid('getGridParam','selarrrow');
	var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
	if(rows.length<2&&rowData){
		transferTask(basePath,rowData);
	}else{
		layer.alert('请选择一条数据', {icon: 0, title:'警告'});
	}	
}


</script>
