<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.user.updateuser" /></title>
    <meta name="decorator" content="form"/> 
    <html:css  name="jquery-ztree"/>
    <html:js  name="jquery-ztree"/>
    <html:component name="bootstrap-fileinput" />
</head>
<body class="white-bg"  formid="userForm">
<form:form id="userForm" modelAttribute="data" action="${adminPath}/sys/user/changeOrg" method="post" class="form-horizontal">
	<input type="hidden" id="orgId" name="orgId"/>
	<input type="hidden" id="orgName" name="orgName"/>
    <div class="list-group list-group-success" id="cut-org-box-list">
	    <ul id="treeObj" class="ztree"></ul>
	</div>
</form:form>
<script type="text/javascript">
	var treeObj;
	var setting = {
			async: {
				enable: true,
				url:"${adminPath}/sys/user/userOrgTree",
				autoParam:["id=nodeid", "type=type", "iswbs=iswbs","isParent=isParent"],
				dataFilter: filter
			},
			data:{key:{title:"title"}},
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
		clearAllVal();//每次点击之前清理之前设置的所有值
		$("#orgId").val(treeNode.id);
		$("#orgName").val(treeNode.name);
	}
	
	function clearAllVal(){
		$("#orgId").val("");
		$("#orgName").val("");
	}
	
	$(document).ready(function(){
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
</script>
</body>
</html>