<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>设备使用记录列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="设备使用记录">
<div class="row">
     <div class="col-sm-3 col-md-2">
            <%@include file="/WEB-INF/webpage/modules/schedulin/public/orgztree.jsp"%>
            <!-- 
			<view:treeview id="organizationTreeview"
				dataUrl="${adminPath}/sys/organization/bootstrapTreeData"
				onNodeSelected="organizationOnclick"></view:treeview>
		     -->
			<script type="text/javascript">
			    /**
			    var orgId = "";//所属组织id
			    //树的单击事件
				function organizationOnclick(event, node) {
					orgId = node.href;
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(orgId);
					dataSearch('schEquipmentUserRecordGridIdGrid');
				}*/
				function searchData(id,node){ 
					$("input[name='orgId']").val(id);
					dataSearch('schEquipmentUserRecordGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-9 col-md-10">
		    <grid:grid id="schEquipmentUserRecordGridId" multiselect="false"
		        url="${adminPath}/schedulin/schequipmentuserrecord/ajaxList" sortable="true">
				<grid:column label="sys.common.key" hidden="true" name="id" width="100"/>
			    <grid:query name="orgId" queryMode="hidden" />
			    <grid:column label="组织名称"  name="orgname" />
			    <grid:column label="设备编码"  name="schEquipmentFilesCode" />
			    <grid:column label="设备名称"  name="schEquipmentFilesName" query="true"  />
			    <grid:column label="规格"  name="specification" />
			    <grid:column label="型号"  name="model" />
			    <grid:column label="计量单位"  name="schMeasurementUnitName" />
			    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType" query="true" queryMode="select"/>
			    <grid:column label="机手"  name="machineHand" />
			    <grid:column label="日期"  name="updateDate" />
			    <grid:column label="设备来源"  name="equipmentSource" dict="equipmentSource" query="true" queryMode="select" condition="eq"/>
			    <grid:column label="现使用状态"  name="status" dict="userStatus" query="true" queryMode="select" condition="eq"/>
				<grid:column label="备注信息"  name="remarks" />
				 
				<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" 
						function="searchsuper('schEquipmentUserRecordGridIdGrid','orgId')"  />
				<grid:toolbar function="reset"/>
			</grid:grid>
		</div>
</div>
 <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>