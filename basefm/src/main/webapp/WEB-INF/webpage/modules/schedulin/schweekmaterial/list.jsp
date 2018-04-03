<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>周材资产列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="周材资产">
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
				function organizationOnclick(event, node) {
					orgId = node.href;
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(orgId);
					dataSearch('schWeekMaterialGridIdGrid');
				}
				*/
				function searchData(id,node){ 
					$("input[name='orgId']").val(id);
					dataSearch('schWeekMaterialGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-9 col-md-10">
		     <grid:grid id="schWeekMaterialGridId" url="${adminPath}/schedulin/schweekmaterial/ajaxList">
					<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
				    <grid:query name="orgId" queryMode="hidden" />
				     <grid:column label="仓库名称"  name="warehouseName" />
				    <grid:column label="库存数"  name="stockNumber" />
				    <grid:column label="周材名称"  name="schBasisMaterialName" query="true"/>
				    <grid:column label="周材编码"  name="schBasisMaterialCode" />
				    <grid:column label="规格"  name="specification" />
				    <grid:column label="型号"  name="model" />
				    <grid:column label="计量单位"  name="schMeasurementUnitName" />
				    <grid:column label="周材分类"  name="schBasisMaterialOgrName" />
				    <grid:column label="更新日期"  name="updateDate" />
				   
				    <grid:column label="备注信息"  name="remarks" />
					<grid:toolbar title="添加" btnclass="btn-primary"  icon="fa-plus" function="createPage"  url="${adminPath}/schedulin/schweekmaterial/create"/>
				    <grid:toolbar title="修改" btnclass="btn-success" winwidth="900px" winheight="600px" icon="fa-plus" function="updatePage"  url="${adminPath}/schedulin/schweekmaterial/{id}/update"/>
				    <grid:toolbar title="删除" btnclass="btn-warning" icon="fa-plus" function="batchDeletePage"  url="${adminPath}/schedulin/schweekmaterial/batch/delete"/>
				    <grid:toolbar title="查看" btnclass="btn-info" winwidth="900px" winheight="600px" icon="fa-arrows-h" function="detaillook" url="${adminPath}/schedulin/schweekmaterial/{id}/update" />
					
					<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search"
					 function="searchsuper('schWeekMaterialGridIdGrid','orgId')"  />
				<grid:toolbar function="reset"/> 	
				</grid:grid>
		</div>
</div>
<!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>