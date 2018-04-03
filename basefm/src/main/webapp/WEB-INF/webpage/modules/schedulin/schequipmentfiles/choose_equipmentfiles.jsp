<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>设备档案列表</title>
  <meta name="decorator" content="grid-select"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="设备档案">
<div class="row">
     <div class="col-sm-3 col-md-2" style="width: 30%;float: left;margin-top: 75px;">
			<view:treeview id="organizationTreeview"
				dataUrl="${adminPath}/sys/organization/bootstrapTreeData"
				onNodeSelected="organizationOnclick"></view:treeview>
			<script type="text/javascript">
			    var orgId = "";//所属组织id
				function organizationOnclick(event, node) {
					orgId = node.href;
					//查询时间
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(orgId);
					dataSearch('schEquipmentFilesGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-9 col-md-10" style="width: 68%;float: left;">
		      <grid:grid id="schEquipmentFilesGridId" url="${adminPath}/schedulin/schequipmentfiles/ajaxList">
					<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
					<grid:query name="orgId" queryMode="hidden" />
				    <grid:column label="设备来源"  name="equipmentSource" dict="equipmentSource" />
				    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType"/>
				    <grid:column label="设备编码"  name="schEquipmentBaseDataCode" />
				    <grid:column label="设备名称"  name="schEquipmentBaseDataName" query="true" />
				    <grid:column label="设备规格"  name="equipmentSpecification" />
				    <grid:column label="设备型号"  name="equipmentModel" />
				    <grid:column label="计量单位"  name="schMeasurementUnitName" />
				    <grid:column label="管理单位"  name="managementUnitName" />
				    <grid:column label="管理单位"  name="userUnitName" />
				    <grid:column label="现使用状态"  name="userStatus" dict="userStatus"  query="true" queryMode="select" condition="eq"/>
					<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search"
					 function="searchsuper('schEquipmentFilesGridIdGrid','userStatus,orgId')"  />
			<grid:toolbar function="reset"/>
				</grid:grid>
		 </div>
   </div>
   <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>