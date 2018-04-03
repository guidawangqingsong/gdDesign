<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>设备档案列表</title>
  <meta name="decorator" content="list"/> 
   <html:component name="bootstrap-treeview" />
</head> 
<body title="设备档案">  
 <div class="row">
     <div class="col-xs-6 col-sm-2">
            <%@include file="/WEB-INF/webpage/modules/schedulin/public/orgztree.jsp"%> 
			<%-- <view:treeview id="organizationTreeview"
				dataUrl="${adminPath}/sys/organization/bootstrapTreeData"
				onNodeSelected="organizationOnclick"></view:treeview> --%>
			  <script type="text/javascript">
			   var downloadZipUrl = "${staticPath}";
			   /*  var orgId = "";//所属组织id
				function organizationOnclick(event, node) {
					orgId = node.href;
					//查询时间
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(orgId);
					dataSearch('schEquipmentFilesGridIdGrid');
				}  */
			 	function searchData(id,node){ 
					$("input[name='orgId']").val(id);
					dataSearch('schEquipmentFilesGridIdGrid');
				}
			</script>  
		</div>
		<div class="col-xs-6 col-sm-10">
		      <grid:grid id="schEquipmentFilesGridId" url="${adminPath}/schedulin/schequipmentfiles/ajaxList">
					<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
					<grid:query name="orgId" queryMode="hidden" />
					<grid:column label="编码"  name="code" />
				    <grid:column label="设备来源"  name="equipmentSource" dict="equipmentSource" />
				    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType"/>
					<grid:query name="orgId" queryMode="hidden" /> 
				    <grid:column label="设备编码"  name="schEquipmentBaseDataCode" />
				    <grid:column label="设备名称"  name="schEquipmentBaseDataName" query="true" />
				    <grid:column label="设备规格"  name="equipmentSpecification" />
				    <grid:column label="设备型号"  name="equipmentModel" />
				    <grid:column label="计量单位"  name="schMeasurementUnitName" />
				    <grid:column label="设备来源"  name="equipmentSource" dict="equipmentSource" query="true" queryMode="select" condition="eq"/>
				    <grid:column label="设备分类"  name="equipmentType" dict="equipmentType" query="true" queryMode="select" condition="eq"/>
				    <grid:column label="品牌名"  name="brandName" />
				    <grid:column label="生产厂家"  name="produceFactory" />
				    <grid:column label="出厂日期"  name="factoryDate" formatter="date"  dateformat="yyyy-MM-dd"/>
				    <grid:column label="出厂编码"  name="factoryCode" />
				    <grid:column label="车架号"  name="frameNumber" />
				    <grid:column label="发动机号"  name="engineNumber" />
				    <grid:column label="车牌号"  name="licenseNumber" />
				    <grid:column label="管理单位"  name="managementUnitName" />
				    <grid:column label="管理单位"  name="userUnitName" />
				    <grid:column label="购买日期"  name="buyDate" formatter="date"  dateformat="yyyy-MM-dd" />
				    <grid:column label="存放地点"  name="storeAddres" />
				    <grid:column label="使用状态"  name="userStatus" dict="userStatus"  query="true" queryMode="select" condition="eq"/>
				    
				    <grid:toolbar title="添加" btnclass="btn-primary" winwidth="900px" winheight="600px" icon="fa-plus" function="createPage"  url="${adminPath}/schedulin/schequipmentfiles/create"/>
				    <grid:toolbar title="修改" btnclass="btn-success" winwidth="900px" winheight="600px" icon="fa-plus" function="updatePage"  url="${adminPath}/schedulin/schequipmentfiles/{id}/update"/>
				    <grid:toolbar title="删除" btnclass="btn-warning" icon="fa-plus" function="batchDeletePage"  url="${adminPath}/schedulin/schequipmentfiles/batch/delete"/>
				    <%--
				    <grid:toolbar title="查看" btnclass="btn-info" winwidth="900px" winheight="600px" icon="fa-arrows-h" function="detaillook" url="${adminPath}/schedulin/schequipmentfiles/{id}/update" />
					 --%>
					<grid:toolbar title="查看二维码" btnclass="btn-info" winwidth="900px" winheight="700px" icon="fa-arrows-h" function="detaillook" url="${adminPath}/schedulin/schequipmentfiles/{id}/getQRCode" />
					<grid:toolbar title="导出二维码" btnclass="btn-warning" icon="fa-plus" function="batchExportQRCode"  url="${adminPath}/schedulin/schequipmentfiles/batch/batchExportQRCode"/>
                    <grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" 
                      function="searchsuper('schEquipmentFilesGridIdGrid','userStatus,equipmentSource,orgId')"  />
				<grid:toolbar function="reset"/>
					
				</grid:grid>
		 </div>
   </div>
   <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>