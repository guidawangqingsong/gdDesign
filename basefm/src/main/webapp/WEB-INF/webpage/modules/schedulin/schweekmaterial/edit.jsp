<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>周材资产</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schWeekMaterialForm">
     <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <form:form id="schWeekMaterialForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>周材名称:</label>
		            </td>
					<td class="width-35">
					     <form:gridselect gridId="schBasisMaterialGridId" genField="true" title="选择设备" nested="true" path="schBasisMaterialId" 
					             callback="setMaterialsBaseDataName"   layerWidth="1200px" layerHeight="800px" multiselect="false" 
					             formField="ddd,schBasisMaterialCode,specification,model,schMeasurementUnitName,schBasisMaterialOgrName" gridField="name,code,specification,model,schMeasurementUnitName,orgName"  
					             gridUrl="${adminPath}/schedulin/schbasismaterial/basesmaterial"   
					             labelName="schMaterialsBaseDataName" labelValue="${data.schBasisMaterialName}" datatype="*" nullmsg="请选择周材资料!"  />
					    <!-- 
						<form:input path="schBasisMaterialId" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>周材编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="schBasisMaterialCode" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>规格:</label>
		            </td>
					<td class="width-35">
						<form:input path="specification" htmlEscape="false" class="form-control"  readonly="true"    />
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>型号:</label>
		            </td>
					<td class="width-35">
						<form:input path="model" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>计量单位:</label>
		            </td>
					<td class="width-35">
						<form:input path="schMeasurementUnitName" htmlEscape="false" class="form-control"  readonly="true"    />
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>周材分类:</label>
		            </td>
					<td class="width-35">
						<form:input path="schBasisMaterialOgrName" htmlEscape="false" class="form-control"    readonly="true"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>周材仓库:</label>
		            </td>
					<td class="width-35">
						<%-- <form:input path="warehouse" htmlEscape="false" class="form-control"      /> --%>
						
						 <form:treeselect title="请选仓库名称" path="warehouse" nested="true" datatype="*"
						  nullmsg="请选择仓库名称!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					       labelName="parentname" labelValue="${data.warehouseName}" />
						
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>库存数:</label>
		            </td>
					<td class="width-35">
						<form:input path="stockNumber" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				   <td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" class="form-control"   cssStyle="height:100px"   />
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