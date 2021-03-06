<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>医用系统民主测评列表</title>
  <meta name="decorator" content="list"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body title="医用系统民主测评">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <%-- 左布局 --%>
    <div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<%-- 中心布局 --%>
	<div data-options="region:'center'">
		 <grid:grid id="medicalEvaluationGridId" url="${adminPath}/medicalevaluation/medicalevaluation/ajaxList">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
			<grid:button groupname="opt" title="附件" url="${adminPath}/medicalevaluation/medicalevaluation/{id}/file"  
			 winwidth="800px" function="rowDialogDetailRefresh" outclass="btn-info" innerclass="fa-file"/>
			<grid:button groupname="opt" function="delete" />
			
			<grid:column label="医护编号" name="staffId"/> <!-- 自动获取员工编号,不能手工添加 -->
			<grid:column label="创建人"  name="createByName" query="true" queryMode="input" condition="like"/>
			<grid:column label="创建时间"  name="createDate" query="true" queryMode="date" condition="between" />
		    <grid:column label="医用预测功能"  name="sysPredict" query="true" queryMode="input" condition="like"/> 
		    <grid:column label="组织评定"  name="originEva" />
		    <grid:column label= "系统前端界面"  name="sysFrontUI" dict="sysEvaType"/>
		    <grid:column label= "系统后台设计"  name="sysBackstage" dict="sysEvaType"/>
		    <!-- 实现字典框查询  -->
		    <grid:column label="系统设置"  name="sysConfig" dict="sysEvaType" query="true" queryMode="select"/>
		    <grid:column label="附件"  name="evaAttach" formatter="true"/>
		    
			<grid:toolbar function="update" winwidth="800px" winheight="600px"/>
			<grid:toolbar title="删除" btnclass="btn-primary" function="batchDeleteLog" url="${adminPath}/medicalevaluation/medicalevaluation/batch/delete"/>	
			<grid:toolbar title="添加" function="createPage" btnclass="btn-primary" winwidth="800px" winheight="600px" icon="fa-plus" url="${adminPath}/medicalevaluation/medicalevaluation/create"/>
			<grid:toolbar title="查看" function="detail"  url="${adminPath}/medicalevaluation/medicalevaluation/{id}/update"  btnclass="btn btn-sm btn-success" 
			winwidth="800px" winheight="600px" icon="fa-search"/>
			
			
			<%-- <grid:toolbar title="搜索" function="search"/> --%>
			<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" function="dataSearch"  />
			<grid:toolbar function="reset"/>
		</grid:grid>
	</div>
</div>


<script type="text/javascript">
    var orgId ='';
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
	
	//单击组织树触发事件，并获取orgId的值
	function onClick(event, treeId, treeNode, clickFlag) {
		var gridId = 'medicalEvaluationGridIdGrid';
	    orgId = treeNode.id; 		 //获取组织的节点id，是变量生命期短很安全。
	    $("input[name='orgId']").val(treeNode.id);   // 选择属性name=orgId的input元素进行赋值操作
	
		$("#"+gridId).jqGrid('setGridParam',{
		    datatype:'json',  
		    postData:{"orgId":orgId}, //发送数据  
		    page:1  
		 }).trigger("reloadGrid"); //重新载入 
		search('medicalEvaluationGridIdGrid');
		reset('medicalEvaluationGridIdGrid');
	} 
	
	$(document).ready(function(){
		//初始化树数据
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
	
	/**
	 * 新增事件打开窗口(是否包含组织id，有的话组织id为：orgId)
	 * 需要声明全局变量 orgId
	 * @param title 编辑框标题
	 * @param addurl//目标页面地址
	 */
	function createPage(title,url,gridid,width,height) { 
		if(!orgId){//新增时需要选择组织id
			top.layer.alert('请选择左边组织!', {icon: 0, title:'警告'});
			return;
		}else{
			url += "?orgId="+orgId;//路径传参
		}
		create(title,url,gridid,width,height);
	}
	
	/**
	 *搜索(数据搜索查询,过滤后台组装查询条件，将由查询人员自动组织查询条件，目前该方法只限于“orgId”字段)
	 * @param gridId 列表id
	 * 注：调用此方法；所有查询条件都需自己在后台设置
	 */
	function dataSearch() {
		var gridId = 'medicalEvaluationGridIdGrid';
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
	
	/**
	 * 多记录刪除請求
	 * @param title
	 * @param url
	 * @param gname
	 * @return
	 */
	function batchDeleteLog(title,url,gridId) {
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
	
</script>

</body>
</html>