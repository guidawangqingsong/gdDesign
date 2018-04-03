<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>物资基础资料列表</title>
<meta name="decorator" content="list" />
<html:component name="bootstrap-treeview" />
</head>
<body title="物资基础资料">
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
					dataSearch('schBaseGoodsGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-9 col-md-10">
			<grid:grid id="schBaseGoodsGridId"
				url="${adminPath}/schedulin/schbasegoods/ajaxList">
				<grid:column label="sys.common.key" hidden="true" name="id"
					width="100" />  
			    <grid:column label="基本计量单位" name="orgId" />
				<grid:column label="编码" name="number" />
				<grid:column label="名称" name="name" query="true" />
				<grid:column label="规格型号" name="spec" query="true" />
				<grid:column label="基本计量单位" name="schMeasurementUnitId" />
				<grid:column label="基本计量单位" name="schMeasurementUnitName" />
				<grid:column label="条形码" name="code" />
				<grid:column label="物料状态" name="audstate" dict="audstate" />
				<grid:column label="管理单元" name="orginame" />
				<grid:query name="orgid" queryMode="hidden" />
				<grid:column label="创建者"  name="createBy.realname" />
				<grid:column label="创建日期" name="createDate"  formatter="yyyy-MM-dd HH:mm:ss" />

				<grid:column label="sys.common.opt" name="opt" formatter="button"
					width="100" />
				<grid:button groupname="opt" function="delete" />
				<grid:toolbar title="添加" btnclass="btn-primary" icon="fa-plus"
					 function="createPage"
					url="${adminPath}/schedulin/schbasegoods/create" />

				<grid:toolbar function="update" />
				<grid:toolbar function="delete" />

				<grid:toolbar function="search" />
				<grid:toolbar function="reset" />
			</grid:grid>
		</div>
	</div>
	<script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
	<html:js name="simditor" /> 
</body>
</html>