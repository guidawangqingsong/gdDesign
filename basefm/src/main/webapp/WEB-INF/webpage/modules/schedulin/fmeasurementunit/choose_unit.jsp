<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>计量单位列表</title>
  <meta name="decorator" content="grid-select"/>
  <html:component name="bootstrap-treeview" />
</head>
<body title="计量单位" style="width: 100%;">
	<div class="row">
	    <div class="col-sm-3 col-md-2" style="width: 30%;float: left;margin-top: 75px;">
		     <!-- 显示组织树列表视图 --> 
		     <view:treeview id="organizationTreeview"
				dataUrl="${adminPath}/schedulin/fmodelorganization/bootstrapTreeData?category=1"
				onNodeSelected="organizationOnclick"></view:treeview>
			<script type="text/javascript">
			    var orgId = "";//所属组织id
				function organizationOnclick(event, node) {
					orgId = node.href;
					//查询时间
					//gridquery隐藏 查询标签概念，query,单独的query
					$("input[name='orgId']").val(orgId);
					dataSearch('fMeasurementUnitGridIdGrid');
				}
			</script>
		</div>
		<div class="col-sm-9 col-md-10" style="width: 68%;float: left;">
		    <grid:grid id="fMeasurementUnitGridId" multiselect="false" url="${adminPath}/schedulin/fmeasurementunit/ajaxList">
				<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
				<grid:column label="编码" name="code" />
				<grid:column label="名称" name="name" query="true"  condition="eq"/>
				<grid:query name="orgId" queryMode="hidden" />
				
				<grid:toolbar function="search"/>
				<grid:toolbar function="reset"/>
			</grid:grid>
		</div>
    </div>
    <script type="text/javascript"  src="${staticPath}/front/js/curdtools_jqgrid_public.js"></script>
    <html:js name="simditor" />
</body>
</html>