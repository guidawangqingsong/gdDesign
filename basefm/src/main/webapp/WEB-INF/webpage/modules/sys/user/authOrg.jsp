<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.org.name" /></title>
    <meta name="decorator" content="form"/> 
    <html:css name="jquery-ztree"/>
    <html:js  name="jquery-ztree"/>
    <style>
	    .ibox-content {
	   	  padding: 0px 0px 0px;
	   	}
	</style>
</head>

<body class="white-bg"  formid="orgForm">
    <form:form id="orgForm" modelAttribute="data" action="${adminPath}/sys/user/doAuthOrg" method="post" class="form-horizontal">
		<form:hidden nested="true" path="id"/>
		<form:hidden nested="false" path="selectOrgs"/>
		<ul id="treeObjOrg" class="ztree"></ul>
		 
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

		var menusNodes =${orgs};
		
		
		// 默认选择节点
		var selectMenus = ${selectOrgs};
		
		$(document).ready(function(){
			treeObj=$.fn.zTree.init($("#treeObjOrg"), setting, menusNodes);			
			// 默认展开一级节点
	        var nodes = treeObj.getNodesByParam("level", 0);
	        for (var i = 0; i < nodes.length; i++) {
	        	treeObj.expandNode(nodes[i], true, false, false);
	        }
			// 不选择父节点
			treeObj.setting.check.chkboxType = { "Y" : "", "N" : "" };
			for(var i=0; i<selectMenus.length; i++) {
				var nodeid=selectMenus[i].id;
				var node = treeObj.getNodeByParam("id", nodeid);
				treeObj.expandNode(node, true, true, true);//默认展开选中的组织节点
				try{treeObj.checkNode(node, true, false);}catch(e){}
			}
			//treeObj.expandAll(true);
		});
		
		function beforeSubmit(){
			var nodes = treeObj.getCheckedNodes(true);
			var selectids=[];
			for(var i=0; i<nodes.length; i++) {
				selectids[i]=nodes[i].id;
			}
			$("#selectOrgs").val(selectids);
			return true;
		}
		
	</SCRIPT>
</body>
</html>