<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>物资基础资料列表</title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="物资基础资料">
<div class="row">
    <div class="col-sm-3 col-md-2">
	     <!-- 显示组织树列表视图 --> 
	     <!-- 引入公共代码部分  -->
	    <%@include file="/WEB-INF/webpage/modules/schedulin/public/public_tree_view.jsp"%>
		<script type="text/javascript">
		    /**
		    var orgId = "";//所属组织id
			function organizationOnclick(event, node) {
				orgId = node.href;
				//查询时间
				//gridquery隐藏 查询标签概念，query,单独的query
				$("input[name='orgId']").val(orgId);
				dataSearch('schMaterialsBaseDataGridIdGrid');
			}
			*/
			function searchData(id, node) { 
				//查询时间
				//gridquery隐藏 查询标签概念，query,单独的query
				$("input[name='orgId']").val(id);
				dataSearch('schMaterialsBaseDataGridIdGrid');
			}
		</script>
	</div>
	<div class="col-sm-9 col-md-10">
		   <grid:grid id="schMaterialsBaseDataGridId" url="${adminPath}/schedulin/schmaterialsbasedata/ajaxList">
				<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			    <grid:column label="编码"  name="code" />
			    <grid:column label="名称"  name="name"  query="true"/>
			    <grid:column label="品牌名"  name="brandName" />
			    <grid:column label="规格型号"  name="specification" />
			    <grid:column label="计量单位"  name="schMeasurementUnitName" />
			    <grid:column label="条形码"  name="barcode" />
			    <grid:query name="orgId" queryMode="hidden" />
			       <grid:column label="更新日期"  name="updateDate" />
			    <grid:column label="物料状态"  name="materialsStatus" dict="materialsStatus"/>
			    <grid:column label="所属组织"  name="sysOrganizationName" />
			    <grid:column label="创建者"  name="createBy.realname" />
			    <grid:column label="创建时间"  name="createDate" formatter="date"  dateformat="yyyy-MM-dd"  />
				
				<grid:toolbar title="添加" btnclass="btn-primary" icon="fa-plus" function="createPage"  url="${adminPath}/schedulin/schmaterialsbasedata/create"/>
				<grid:toolbar title="修改" btnclass="btn-success" icon="fa-file-text-o" function="updatePage"  url="${adminPath}/schedulin/schmaterialsbasedata/{id}/update"/>
				<grid:toolbar title="删除" btnclass="btn-warning" icon="fa-trash-o" function="batchDeletePage"  url="${adminPath}/schedulin/schmaterialsbasedata/batch/delete"/>
				<grid:toolbar title="查看" btnclass="btn-info" icon="fa-binoculars"
				    function="detaillook"
					url="${adminPath}/schedulin/schmaterialsbasedata/{id}/update" />
				<grid:toolbar title="Excel导出" btnclass="btn-primary" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schmaterialsbasedata/excelExport','schMaterialsBaseDataGridId','orgId')"/>
				<grid:toolbar title="Excel模版下载" btnclass="btn-success" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/schmaterialsbasedata/excelModelExport','schMaterialsBaseDataGridId','orgId')"/>
					
				  <grid:toolbar title="数据导入" btnclass="btn-success" icon="fa-upload" winwidth="700px"
			     winheight="400px"  function="imppage"
					url="${adminPath}/import/excel/schmaterialsimp" />
			    	
					
				<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" function="searchsuper('schMaterialsBaseDataGridIdGrid','orgId')"  />
				
					<grid:toolbar function="reset"/>
			</grid:grid>
	</div>
</div>
   <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>