<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>设备使用记录</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schEquipmentUserRecordForm">
    <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <form:form id="schEquipmentUserRecordForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备编码</label>
		            </td>
					<td class="width-35">
					    <form:gridselect gridId="schEquipmentFilesGridId" genField="true" title="选择设备" nested="true" path="schEquipmentFilesId" 
					             callback="schEquipmentFilesName"   layerWidth="1200px" layerHeight="800px" multiselect="false" 
					             formField="ddd,schEquipmentFilesName,specification,model,schMeasurementUnitName,equipmentType,equipmentSource,userStatus" 
					             gridField="schEquipmentBaseDataCode,schEquipmentBaseDataName,equipmentSpecification,equipmentModel,schMeasurementUnitName,equipmentType,equipmentSource,userStatus"  
					             gridUrl="${adminPath}/schedulin/schequipmentfiles/chooseEquipmentfiles"   
					             labelName="schEquipmentFilesCode" labelValue="${data.schEquipmentFilesCode}" datatype="*" nullmsg="请选择设备档案管理!"  />
					    <!-- 
						<form:input path="schEquipmentFilesId" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="schEquipmentFilesName" htmlEscape="false" class="form-control"  readonly="true"    />
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
						<form:input path="schMeasurementUnitName" htmlEscape="false" class="form-control"   readonly="true"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备分类:</label>
		            </td>
					<td class="width-35">
					    <form:select  readonly="true" onclick="hehe()" disabled="disabled"
							path="equipmentType" htmlEscape="false" class="form-control"
							dict="equipmentType" datatype="*" nullmsg="请选择设备分类!" 
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="equipmentType" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备来源:</label>
		            </td>
					<td class="width-35">
					    <form:select
							path="equipmentSource" htmlEscape="false" class="form-control"
							dict="equipmentSource" datatype="*" nullmsg="请选择设备分类!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="equipmentSource" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label>初始使用状态:</label>
		            </td>
					<td class="width-35">
					    <form:select
							path="userStatus" htmlEscape="false" class="form-control"
							dict="userStatus" datatype="*" nullmsg="请选择使用状态!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="userStatus" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>现使用状态:</label>
		            </td>
					<td class="width-35" colspan="3">
						 <form:select
							path="status" htmlEscape="false" class="form-control"
							dict="userStatus" datatype="*" nullmsg="请选择使用状态!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>机手:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="machineHand" datatype="n" htmlEscape="false" class="form-control"      />
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
function schEquipmentFilesName(){
	  $("#schEquipmentFilesCode").val($("#ddd").val());  
}

$(function(){
	$("#equipmentType").attr("disabled","true");
	$("#equipmentSource").attr("disabled","true");
	$("#userStatus").attr("disabled","true");
});

</script>
</body>
</html>