<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>测试流程demo列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="测试流程demo">
<grid:grid id="testFlowGridId" url="${adminPath}/test/testflow/ajaxList" >
	<grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
	<grid:column label="名称"  name="name" width="100"/>
	<grid:column label="编号"  name="number" width="100"/>
	<grid:column label="状态"  name="status" width="100"/>
	<grid:column label="备注"  name="remarks" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	<grid:toolbar btnclass="btn-info"  icon="fa-download"  onclick="submit()" title="提交流程" />	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>

<script type="text/javascript">

/**提交流程测试*/
function submit(){
	var rowsData = $("#testFlowGridIdGrid").jqGrid('getGridParam','selarrrow');
	var id = rowsData[0];
	var formUrl = '${adminPath}/test/testflow/{id}/update';
	formUrl = formUrl.replace("{id}",id);
	var params = {
			basePath:'${adminPath}',
			billId:id,
			key:'Ld201c1479495483cab1865fc57dfaef8',
			formUrl:formUrl,
			tableName:'testFlow',
		}
 	submitFlow(params);
}

</script>
</body>
</html>