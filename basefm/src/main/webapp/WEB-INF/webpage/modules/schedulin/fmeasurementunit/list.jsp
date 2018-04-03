<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>计量单位列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="计量单位">
	<div class="row">
	    <div class="col-sm-6 col-md-2">
		     <!-- 显示组织树列表视图 --> 
		     <!-- 引入公共代码部分  -->
		    <%@include file="/WEB-INF/webpage/modules/schedulin/public/public_tree_view.jsp"%>
			<script type="text/javascript"> 
				function searchData(id, node) { 
					//查询时间
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(id);
					dataSearch('fMeasurementUnitGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-6 col-md-10">
		    <grid:grid id="fMeasurementUnitGridId" url="${adminPath}/schedulin/fmeasurementunit/ajaxList">
				<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
				<grid:column label="编码" name="code"  />
				<grid:column label="名称" name="name" />
				<grid:column label="标准转换系数" name="conversionFactor" />
				<grid:column label="数量精度" name="numSchedule" />
				<grid:query name="orgId" queryMode="hidden" />
				<grid:toolbar title="添加" btnclass="btn-primary" icon="fa-plus" winwidth="700px" winheight="400px" function="createPage"  url="${adminPath}/schedulin/fmeasurementunit/create"/>
				<grid:toolbar title="修改" btnclass="btn-success" icon="fa-file-text-o" winwidth="700px" winheight="400px" function="updatePage"  url="${adminPath}/schedulin/fmeasurementunit/{id}/update"/>
				<grid:toolbar title="删除" btnclass="btn-warning" icon="fa-trash-o" function="batchDeletePage"  url="${adminPath}/schedulin/fmeasurementunit/batch/delete"/>
				<grid:toolbar title="查看" btnclass="btn-info" icon="fa-binoculars" winwidth="700px" winheight="400px"
				    function="detaillook"
					url="${adminPath}/schedulin/fmeasurementunit/{id}/update" />
			    <grid:toolbar title="Excel模板下载" btnclass="btn-success" icon="fa-download" onclick="ExeclExp('${adminPath}/schedulin/fmeasurementunit/excelModelExport','fMeasurementUnitGridId','orgId')"/>
		
			    <grid:toolbar title="数据导入" btnclass="btn-success" icon="fa-upload" winwidth="700px"
			     winheight="400px"  function="imppage"
					url="${adminPath}/import/excel/imp" />
			    
				<grid:toolbar function="search"/>
				<grid:toolbar function="reset"/>
			</grid:grid>
		</div>
    </div>
    <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script> 
</body>
</html>