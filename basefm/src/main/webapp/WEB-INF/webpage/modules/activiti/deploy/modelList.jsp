<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.model.title" /></title>
  <meta name="decorator" content="list"/>
  <script type="text/javascript" src="${staticPath }/uadmin/js/public-add.js"></script>
</head>
<body title="<spring:message code="activiti.model.title" />">
<grid:grid id="activitiModelId" url="${adminPath}/activiti/deploy/model/ajaxList" gridSetting="{loadComplete:onloadFun}">
	<grid:column label="activiti.model.id" hidden="true"   name="id" width="100"/>
    <grid:column label="activiti.model.name"  name="name"  query="true"  queryModel="input" width="100"   condition="like" />
    <grid:column label="activiti.model.key"  name="key" />
    <grid:column label="activiti.model.discription"  name="discription"  />
    <grid:column label="activiti.model.type"  name="category" />
    <grid:column label="activiti.model.createtime"  name="createTime" formatter="date" dateformat="yyyy-MM-dd"/>
    <grid:column label="activiti.model.release.status"  name="status" dict="fb" />
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="120"/>
	<grid:button groupname="opt" function="rowConfirm" title="activiti.model.release" tipMsg="你确定要发布该流程模型吗?" outclass="btn-green" innerclass="fa-hourglass-start" url="${adminPath}/activiti/deploy/deployModel?modelId={id}"/>
	<grid:button groupname="opt" exp="row.status==1" function="rowDialogDetailRefresh" title="activiti.model.version.monitor" outclass="btn-blue"  innerclass="fa-plus" url="${adminPath}/activiti/deploy/predef/list?modelId=\"+row.id+\"" winwidth="60%" />
	<grid:button groupname="opt" exp="row.status==0" function="delete" url="${adminPath}/activiti/deploy/delModel?modelId={id}"/>
	<grid:toolbar  function="create" title="activiti.model.add" btnclass="btn-green" url="${adminPath}/activiti/deploy/createModel"/>
	<grid:toolbar  function="update" title="activiti.model.edit"  url="${adminPath}/activiti/deploy/modeler?modelId={id}" winwidth="100%" winheight="100%"/>
	<grid:toolbar  function="search"  />
</grid:grid>

<script type="text/javascript">

</script>