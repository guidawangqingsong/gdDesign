<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>样本信息列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="costSample">
<grid:grid id="medicalSampleGridId" url="${adminPath}/ms/medicalsample/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
	
	<grid:column label="样本编号" name="id" query="true" queryMode="input"/>
	<grid:column label="挂号费" name="registerFee" query="true" queryMode="input"/>
	<grid:column label="医药费" name="medicalFee" query="true" queryMode="input"/>
	<grid:column label="治疗费" name="treatmentFee" query="true" queryMode="input"/>
	<grid:column label="住院费" name="hospitalFee" query="true" queryMode="input"/>
	<grid:column label="其他" name="other" query="true" queryMode="input"/>
	<grid:column label="人均医用（样本）" name="personalFee" query="true" queryMode="input"/>
	
	<grid:toolbar function="update"/>
	<grid:toolbar title="删除" btnclass="btn-primary" function="batchDeleteSample" url="${adminPath}/ms/medicalsample/batch/delete" icon="fa-trash"/>	
	<grid:toolbar title="添加" function="create" btnclass="btn-primary" winwidth="600px" winheight="400px" icon="fa-plus"/>
	<grid:toolbar title="查看" btnclass="btn btn-sm btn-success" winwidth="600px" winheight="400px" 
	function="detail" url="${adminPath}/ms/medicalsample/" icon="fa-search"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
/**
 * 多记录刪除請求
 * @param title
 * @param url
 * @param gname
 * @return
 */
function batchDeleteSample(title,url,gridId) {
	/* debugger; */
	var ids = [];
	var rows =$("#"+gridId).jqGrid('getGridParam','selarrrow');
	var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
	var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
	if(!multiselect)
	{
		if(rowData)
		{
			rows[0]=rowData;
		}
	}
    if (rows.length > 0) {
    	  layer.confirm("您确定要删除这些信息么?请谨慎操作！", {
   		  title:title+"提示",
   		  icon:3,
   		  btn: ['确定','取消'] //按钮
   		}, function(){
   		   //确定
   			for ( var i = 0; i < rows.length; i++) {
           		ids.push(rows[i]);
   			}
   			$.ajax({
   				url : url,
   				type : 'get',
   				data : {
   					ids : ids.join(',')
   				},
   				cache : false,
   				success : function(d) {
   					if (d.ret==0) {
   						var msg = d.msg;
   					    layer.msg(msg, {icon:1});
   					    //刷新表单
   			            refreshTable(gridId);
   					}else{
   						var msg = d.msg;
   					    layer.msg(msg, {icon: 3,shade:0.3});
   					}
   				}
   			});
   		}, function(){
   		  //取消
   		});
		return;
	}else{
	    top.layer.alert('请选择需要删除的数据!', {icon: 0, title:'警告'});
	    return;
	}
}

/**
 *搜索(数据搜索查询,过滤后台组装查询条件，将由查询人员自动组织查询条件，目前该方法只限于“orgId”字段)
 * @param gridId 列表id
 * 注：调用此方法；所有查询条件都需自己在后台设置
 */
function dataSearch(gridId) {
	var queryParams = {};
	var queryFields=$('#queryFields').val();
	queryParams['queryFields'] = queryFields;
	//普通的查询
	$('#' + gridId + "Query").find(":input").each(function() {
		var val = $(this).val();
		if (queryParams[$(this).attr('name')]) {
			val = queryParams[$(this).attr('name')] + "," + $(this).val();
		}
		queryParams[$(this).attr('name')] = val;
	});
	

	//刷新
	//传入查询条件参数  
	$("#"+gridId).jqGrid('setGridParam',{  
		datatype:'json',  
		postData:queryParams, //发送数据  
		page:1  
	}).trigger("reloadGrid"); //重新载入    
}	

/**方法操作成功后刷新表单*/
function refreshTable(gridId){
	dataSearch(gridId);
}

</script>

</body>
</html>