<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>职员管理列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="<spring:message code="sys.staff" />">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
	<div  data-options="region:'west',split:true" style="width:20%;" >
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<div   data-options="region:'center'">
	<grid:grid id="staffGridId" url="${adminPath}/sys/staff/ajaxList" gridSetting="{loadComplete:onloadFun2}">
		<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
		<grid:button groupname="opt" function="delete" /> --%>
	    <grid:column label="员工编号"  name="code"  query="true"  condition="like"/>
	    <grid:column label="姓名"    name="name"   query="true"  condition="like"/>
	    <grid:column label="性别" dict="sex"  name="sex" />
	    <grid:column label="手机号"  name="moblie"  query="true"  condition="like"/>
	    <grid:column label="职员状态" dict="staffStatus" query="true" queryMode="select" condition="eq"  name="status" />
	    <grid:column label="职员类型" dict="staffType"   query="true" queryMode="select" condition="eq"  name="type" />
	    <grid:column label="岗位"    dict="staffJob"    query="true" queryMode="select" condition="eq"  name="position" />
	    <grid:column label="所属组织" name="orgName" />
		<grid:query name="orgId" condition="eq"  queryMode="hidden" />
		<grid:toolbar function="create"  winwidth="1000px" winheight="700px;"/>
		<grid:toolbar function="update"  winwidth="1000px" winheight="700px;"/>
		<grid:toolbar function="delete"  />
		
		<grid:toolbar function="search"/>
		<grid:toolbar function="reset"/>
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
	function onloadFun2(){
		var winHeight = $("#page-wrapper").height()-100;
		
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-200+"px");
		}catch(e){}
	}
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	function onClick(event, treeId, treeNode, clickFlag) {
		 $("input[name='orgId']").val(treeNode.id);
	     search('staffGridIdGrid');
	}
	
	$(document).ready(function(){
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	
</script>
</body>
</html>