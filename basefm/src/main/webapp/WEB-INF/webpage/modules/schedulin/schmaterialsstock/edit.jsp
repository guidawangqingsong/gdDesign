<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>物资库存量概况</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schMaterialsStockForm">
    <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <form:form id="schMaterialsStockForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>物资名称:</label>
		            </td>
					<td class="width-35">
					     <form:gridselect gridId="schMaterialsBaseDataGridId" genField="true" title="选择设备" nested="true" path="schMaterialsBaseDataId" 
					             callback="setMaterialsBaseDataName"   layerWidth="1200px" layerHeight="800px" multiselect="false" 
					             formField="ddd,schMaterialsBaseDataCode,baseDataSpecification,schMeasurementUnitName,brandName,setSchMaterialsBaseDataOgrName" gridField="name,code,specification,schMeasurementUnitName,brandName,modelOrgName"  
					             gridUrl="${adminPath}/schedulin/schmaterialsbasedata/chooseMaterialsbasedata"   
					             labelName="schMaterialsBaseDataName" labelValue="${data.schMaterialsBaseDataName}" datatype="*" nullmsg="请选择物资基础资料!"  />
					  
					    <!-- 
						<form:input path="schMaterialsBaseDataId" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>物料编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="schMaterialsBaseDataCode" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr> 
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>规格:</label>
		            </td>
					<td class="width-35">
						<form:input path="baseDataSpecification" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>计量单位:</label>
		            </td>
					<td class="width-35">
						<form:input path="schMeasurementUnitName" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr> 
				<tr>
				   <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>品牌名:</label>
		            </td>
					<td class="width-35">
						<form:input path="brandName" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>物资类别:</label>
		            </td>
					<td class="width-35">
						<form:input path="setSchMaterialsBaseDataOgrName" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>仓库名称:</label>
		            </td>
					<td class="width-35">
						<%-- <form:input path="warehouse" htmlEscape="false" class="form-control"      />
						 --%>
						 <form:treeselect title="请选仓库名称" path="warehouse" nested="true" datatype="*"
						  nullmsg="请选择仓库名称!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					       labelName="parentname" labelValue="${data.warehouseName}" />
						
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>库存数:</label>
		            </td>
					<td class="width-35">
						<form:input path="stock" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		       <tr>
		           <td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" class="form-control"  cssStyle="height:100px"    />
						<label class="Validform_checktip"></label>
					</td>
		       </tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript"  src="${staticPath}/schedulin/js/curdtoos_form_public.js"></script>
<script>
var createName = "${data.createBy.realname}";
var createDate = "${data.createDate}";
/**回写名称*/
function setMaterialsBaseDataName(){
	  $("#schMaterialsBaseDataName").val($("#ddd").val());  
}
</script>
</body>
</html>