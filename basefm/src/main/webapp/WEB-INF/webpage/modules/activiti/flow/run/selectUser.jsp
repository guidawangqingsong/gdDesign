<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="grid-select"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="<spring:message code="sys.user.title" />">
<div class="easyui-layout" id="cc" style="width:100%;">
	<div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center'">
		<grid:grid id="userGridId" url="${adminPath}/sys/user/ajaxListUser" multiselect="false">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>			
			<grid:query name="orgId"  queryMode="hidden" />
		    <grid:column label="sys.user.realname"  name="realname"  query="true" condition="like" width="60" />
		    <grid:column label="sys.user.username"  name="username"  query="true" condition="like"  width="60" />
		    <grid:column label="sys.user.organization.name"  name="orgName"/>
		    <grid:column label="sys.user.email"  name="email"  width="60"/>
		    <grid:column label="sys.user.phone"  name="phone" width="60"/>
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
	
</script>
</body>
</html>