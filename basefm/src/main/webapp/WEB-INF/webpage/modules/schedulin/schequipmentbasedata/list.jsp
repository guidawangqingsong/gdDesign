<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>设备基础资料列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="设备基础资料">
<div class="row">
     <div class="col-sm-3 col-md-2">
	     <!-- 显示组织树列表视图 --> 
	     <!-- 引入公共代码部分  -->
	    <%@include file="/WEB-INF/webpage/modules/schedulin/public/public_tree_view.jsp"%>
		<script type="text/javascript"> 
			function searchData(id, node) { 
				//查询时间
				//gridquery隐藏 查询标签概念，query,单独的query
				$("input[name='orgId']").val(id);
				dataSearch('schEquipmentBaseDataGridIdGrid');
			}
		    
		</script>
	</div>
	<div class="col-sm-9 col-md-10">
	     <grid:grid id="schEquipmentBaseDataGridId" url="${adminPath}/schedulin/schequipmentbasedata/ajaxList">
				<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			    <grid:column label="编码"  name="code" />
			    <grid:column label="名称"  name="name" query="true" />
			    <grid:column label="规格"  name="specification" />
			    <grid:column label="型号"  name="model" />
			    <grid:column label="计量单位"  name="schMeasurementUnitName" />
			    <grid:column label="描述"  name="remarks" />
			    <grid:column label="定额号"  name="topNumber" />
			    <grid:column label="机械规格名称"  name="mechanicalName" />
			    <grid:query name="orgId" queryMode="hidden" />
			 <%--    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType"  /> --%>
			    <grid:column label="计量组织"  name="sysOrganizationName" />
			    <grid:column label="创建者"  name="createBy.realname" />
			    <grid:column label="创建时间"  name="createDate" formatter="date"  dateformat="yyyy-MM-dd"  />
				
				<grid:toolbar title="添加" btnclass="btn-primary" icon="fa-plus" function="createPage"  url="${adminPath}/schedulin/schequipmentbasedata/create"/>
				<grid:toolbar title="修改" btnclass="btn-success" icon="fa-file-text-o" function="updatePage"  url="${adminPath}/schedulin/schequipmentbasedata/{id}/update"/>
				<grid:toolbar title="删除" btnclass="btn-warning" icon="fa-trash-o" function="batchDeletePage"  url="${adminPath}/schedulin/schequipmentbasedata/batch/delete"/>
				<grid:toolbar title="查看" btnclass="btn-info" icon="fa-binoculars"
				    function="detaillook"
					url="${adminPath}/schedulin/schequipmentbasedata/{id}/update" />
				<%-- <grid:toolbar function="search"/> --%>
				<grid:toolbar title="Excel导出" btnclass="btn-primary" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schequipmentbasedata/excelExport','schEquipmentBaseDataGridId','orgId')"/>
				<grid:toolbar title="Excel模板下载" btnclass="btn-success" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schequipmentbasedata/excelModelExport','schEquipmentBaseDataGridId','orgId')"/>
				   <grid:toolbar title="数据导入" btnclass="btn-success" icon="fa-upload" winwidth="700px"
			     winheight="400px"  function="imppage"
					url="${adminPath}/import/excel/schequipmentimp" />
			    
				<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" function="searchsuper('schEquipmentBaseDataGridIdGrid','orgId')"  />
				<grid:toolbar function="reset"/>
			</grid:grid>
	</div>
</div>
   <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>