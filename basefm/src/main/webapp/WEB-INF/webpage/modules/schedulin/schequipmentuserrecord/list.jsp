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
		    <grid:grid id="schEquipmentUserRecordGridId" url="${adminPath}/schedulin/schequipmentuserrecord/ajaxList">
				<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			    <grid:query name="orgId" queryMode="hidden" />
			    <grid:column label="设备编码"  name="schEquipmentFilesCode" />
			    <grid:column label="设备名称"  name="schEquipmentFilesName" query="true"  />
			    <grid:column label="规格"  name="specification" />
			    <grid:column label="型号"  name="model" />
			    <grid:column label="计量单位"  name="schMeasurementUnitName" />
			    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType" query="true" queryMode="select"/>
			    <grid:column label="机手"  name="machineHand" />
			    <grid:column label="更新日期"  name="updateDate" />
			    <grid:column label="设备来源"  name="equipmentSource" dict="equipmentSource" query="true" queryMode="select" condition="eq"/>
			    <grid:column label="现使用状态"  name="status" dict="userStatus" query="true" queryMode="select" condition="eq"/>
				<grid:column label="备注信息"  name="remarks" />
				
				<grid:toolbar title="添加" btnclass="btn-primary"  icon="fa-plus" function="createPage"  url="${adminPath}/schedulin/schequipmentuserrecord/create"/>
			    <grid:toolbar title="修改" btnclass="btn-success" winwidth="900px" winheight="600px" icon="fa-file-text-o" function="updatePage"  url="${adminPath}/schedulin/schequipmentuserrecord/{id}/update"/>
			    <grid:toolbar title="删除" btnclass="btn-warning" icon="fa-trash-o" function="batchDeletePage"  url="${adminPath}/schedulin/schequipmentuserrecord/batch/delete"/>
			    <grid:toolbar title="查看" btnclass="btn-info" winwidth="900px" winheight="600px" icon="fa-binoculars" function="detaillook" url="${adminPath}/schedulin/schequipmentuserrecord/{id}/update" />
			    <grid:toolbar title="Excel导出" btnclass="btn-primary" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schequipmentuserrecord/excelExport','schEquipmentUserRecordGridId','orgId')"/>
				<grid:toolbar title="Excel模板下载" btnclass="btn-success" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schequipmentuserrecord/excelModelExport','schEquipmentUserRecordGridId','orgId')"/>
				<grid:toolbar title="状态" btnclass="btn-info" winwidth="400px" winheight="300px" icon="fa-gg-circle" function="updatestate" url="${adminPath}/schedulin/schequipmentuserrecord/{id}/updatestate" />
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