<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>医用日志管理列表</title>
  <meta name="decorator" content="list"/>
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui"/>
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,public-js"/>
  <style type="text/css">.row{margin:0;}</style>
</head>
<body class="fa fa-book fa-fw" title="医用日志管理">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
    <%-- 左布局 --%>
    <div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<%-- 中心布局 --%>
	<div data-options="region:'center'">
		 <grid:grid id="oaWorkLogGridId" url="${adminPath}/oa/oaworklog/ajaxList" >
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			
			<grid:column label="医护编号" name="staffId"/> <!-- 自动获取员工编号,不能手工添加 -->
			<grid:column label="创建人"  name="createByName" query="true" queryMode="input"/>
			<grid:column label="更新时间"  name="updateDate" />
		    <grid:column label="日志主题"  name="logTheme" query="true"  queryMode="input"/>
		    <grid:column label="医用计划"  name="nextPlan" />
		    <grid:column label="日志类型"  name="logType"  dict="logType" query="true" queryMode="select" /><!--实现字典框查询  -->
		    <grid:column label="遗留问题"  name="unfinished" />
		    <grid:column label= "未完成的原因"  name="reason" />
		    <grid:query name="staffId" queryMode="hidden" /> 
			<grid:column label="日志时间"  name="logTime" query="true" queryMode="date" condition="between" />
			<grid:column label="附件"  name="logAttach" formatter="true"/>
			<label class="Validform_checktip"></label>
			<grid:toolbar function="update" winwidth="800px" winheight="600px"/>
			<grid:toolbar title="删除" btnclass="btn-primary" function="batchDeleteLog" url="${adminPath}/oa/oaworklog/batchDelete"/>	
			<grid:toolbar title="添加" btnclass="btn-primary" winwidth="800px" winheight="600px" icon="fa-plus" function="createPage"  url="${adminPath}/oa/oaworklog/create"/>
			<grid:toolbar title="查看" function="detail"  url="${adminPath}/oa/oaworklog/{id}/update"  btnclass="btn btn-sm btn-success" 
			winwidth="800px" winheight="600px" icon="fa-search"/>
			
			<%-- <grid:toolbar title="搜索" function="search"/> --%>
			<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" 
						function="dataSearch1"  />
			
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
		var gridId = 'oaWorkLogGridIdGrid';
	    orgId = treeNode.id; 		 //获取组织的节点id，是变量生命期短很安全。
	    $("input[name='orgId']").val(treeNode.id);   // 选择属性name=orgId的input元素进行赋值操作
	
		$("#"+gridId).jqGrid('setGridParam',{
		    datatype:'json',  
		    postData:{"orgId":orgId}, //发送数据  
		    page:1  
		 }).trigger("reloadGrid"); //重新载入 
		search('oaWorkLogGridIdGrid');
		reset('oaWorkLogGridIdGrid');
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
	function dataSearch1() {
		var gridId = 'oaWorkLogGridIdGrid';
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
	function refreshTable1(gridId){
		dataSearch1(gridId);
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
	   			            refreshTable1(gridId);
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
	
	
	/**附件的超链接*/
	function oaWorkLogGridIdLogAttachFormatter(value, options, row){
		/* debugger; */
		var str = "";
		if(value.length!=0){
			str = "<a  onclick=file('"+row.id+"')>"+value+"</a>";
		}
		return str;
	}
	
	/**打开附件窗口*/
	function file(id){
		var url = "${adminPath}/oa/oaworklog/{id}/file";//{}占位符
		url = url.replace("{id}", id);
		openDialogView("附件详情", url + "?id=" + id, "800px", "500px");		
	}
	
	// 打开对话框(查看)
	function openDialogView(title, url, width, height) {
		if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {// 如果是移动端，就使用自适应大小弹窗
			width = 'auto';
			height = 'auto';
		} 
		else {// 如果是PC端，根据用户设置的width和height显示。
			
		}
		top.layer.open({
			type : 2,
			area : [ width, height ],
			title : title,
			maxmin : true, // 开启最大化最小化按钮
			content : url,
			btn : [ '关闭' ],
			cancel : function(index) {
			}
		});
	}

	
	
</script>
</body>
</html>