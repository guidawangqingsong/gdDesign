<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="<spring:message code="sys.user.title" />">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
		<grid:grid id="userGridId" url="${adminPath}/sys/user/ajaxListUser" gridSetting="{loadComplete:onloadFun}">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			<grid:column label="sys.common.opt"  name="opt" formatter="button" width="40"/>
			<grid:button title="sys.user.org"  groupname="opt" function="rowDialogDetailRefresh" outclass="btn-info"  innerclass="fa-sitemap" url="${adminPath}/sys/user/configOrg?gid=\"+row.id+\"" />
			<%-- <grid:button groupname="opt" function="delete" url="${adminPath}/sys/user/{id}/deleteUser" /> --%>
			<grid:query name="orgId"  queryMode="hidden" />
		    <grid:column label="sys.user.realname"  name="realname"  query="true"  condition="like" width="60" />
		    <grid:column label="sys.user.username"  name="username"  query="true" condition="like"  width="60" />
		    <grid:column label="sys.user.organization.name"  name="orgName"/>
		    <grid:column label="sys.user.email"  name="email"  width="60"/>
		    <grid:column label="sys.user.phone"  name="phone"  query="true"  condition="like"  width="60"/>
			<grid:toolbar title="sys.user.createuser" function="create" btnclass="btn-green" icon="fa-user-plus"   url="${adminPath}/sys/user/createUser"/>
		 	<grid:toolbar title="sys.user.updateuser" function="update" icon="fa-user"  url="${adminPath}/sys/user/{id}/updateUser"/>
			<grid:toolbar title="sys.user.modifypwd" icon="fa-unlock-alt"  function="updateDialog" url="${adminPath}/sys/user/{id}/changePassword"  />
			<grid:toolbar function="delete"  url="${adminPath}/sys/user/batch/deleteUser"/>
			<grid:toolbar  function="search"/>
			<grid:toolbar  function="reset"/>
		</grid:grid>
	</div>
</div>
<script type="text/javascript">
	var treeObj;
	var setting = {
			async: {
				enable: true,
				url:"${adminPath}/sys/organization/orgTree",
				autoParam:["id=nodeid"],
				dataFilter: filter
			},
			callback: {
				onClick: onClick
			}
		};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	function onClick(event, treeId, treeNode, clickFlag) {
		 $("input[name='orgId']").val(treeNode.id);
	     search('userGridIdGrid');
	}
	
	$(document).ready(function(){
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
</script>
</body>
</html>