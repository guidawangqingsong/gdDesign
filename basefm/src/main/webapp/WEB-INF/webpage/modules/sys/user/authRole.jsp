<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.user.authrole" /></title>
    <meta name="decorator" content="form"/> 
    <html:css name="jquery-ztree"/>
    <html:js  name="jquery-ztree"/>
    <style>
	    .ibox-content {
	   	  padding: 0px 0px 0px;
	   	}
	</style>
</head>

<body class="white-bg"  formid="roleForm">
    <form:form id="roleForm" modelAttribute="data" action="${adminPath}/sys/user/doRoleAuthMenu" method="post" class="form-horizontal">
		<form:hidden nested="true" path="id"/>
		<form:hidden nested="false" path="selectRoles"/>
		<ul id="treeObj" class="ztree"></ul>		 
	</form:form>
	<script type="text/javascript">
        var treeObj;
		var setting = {
			check: {
				enable: true
			},
			data: {
				key: {
					url: "notarget",
					icon: "notarget"
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: 0
				}
			}
		};

		var menusNodes =${roles};
		
		
		// 默认选择节点
		var selectMenus = ${selectRoles};
		
		$(document).ready(function(){
			treeObj=$.fn.zTree.init($("#treeObj"), setting, menusNodes);
			// 不选择父节点
			//treeObj.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			for(var i=0; i<selectMenus.length; i++) {
				var nodeid=selectMenus[i].id;
				var node = treeObj.getNodeByParam("id", nodeid);
				try{treeObj.checkNode(node, true, false);}catch(e){}
			}
			treeObj.expandAll(true);
		});
		
		function beforeSubmit(){
			var nodes = treeObj.getCheckedNodes(true);
			var selectids=[];
			for(var i=0; i<nodes.length; i++) {
				selectids[i]=nodes[i].id;
			}
			$("#selectRoles").val(selectids);
			return true;
		}
		
	</SCRIPT>
</body>
</html>