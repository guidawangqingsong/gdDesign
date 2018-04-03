<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>基础材料列表</title>
  <meta name="decorator" content="grid-select"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="基础材料">
<div class="row">
    <div class="col-sm-3 col-md-2" style="width: 30%;float: left;margin-top: 75px;">
	     <!-- 显示组织树列表视图 --> 
	      <!-- 显示组织树列表视图 --> 
	     <view:treeview id="organizationTreeview"
			dataUrl="${adminPath}/schedulin/fmodelorganization/bootstrapTreeData?category=2"
			onNodeSelected="organizationOnclick"></view:treeview>
		<script type="text/javascript">
		    var orgId = "";//所属组织id
			function organizationOnclick(event, node) {
				orgId = node.href;
				//查询时间
				//gridquery隐藏 查询标签概念，query,单独的query
				$("input[name='orgId']").val(orgId);
				dataSearch('schBasisMaterialGridIdGrid');
			}
		</script>
	</div>
	<div class="col-sm-9 col-md-10" style="width: 68%;float: left;">
		<grid:grid id="schBasisMaterialGridId"  url="${adminPath}/schedulin/schbasismaterial/ajaxList">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		    <grid:column label="编码"  name="code" />
		    <grid:column label="名称"  name="name" query="true" />
		    <grid:query name="orgId" queryMode="hidden" />
		    <grid:column label="周材分类"  name="orgName" />
		    <grid:column label="规格"  name="specification" />
		    <grid:column label="型号"  name="model" />
		    <grid:column label="计量单位"  name="schMeasurementUnitName"/>
		    <grid:column label="创建者"  name="createBy.realname" />
		    <grid:column label="创建时间"  name="createDate" formatter="date"  dateformat="yyyy-MM-dd"  />
		    
			<grid:toolbar title="搜索" btnclass="btn-info" layout="right"  icon="fa fa-search" function="searchsuper('schBasisMaterialGridIdGrid','orgId')"  />
			<grid:toolbar function="reset"/>
		</grid:grid>
    </div>
</div>
   <!-- 引入js工具文件 -->
   <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
</body>
</html>