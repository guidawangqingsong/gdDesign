<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>医用系统民主测评</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="medicalEvaluationForm">
    <form:form id="medicalEvaluationForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="staffId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
		   			<td  class="width-15 active text-right">	
		              <label>医护编号:</label>	<!-- 应该关联sys_staff表查询员工id -->
		            </td>
					<td class="width-35">
						<form:input path="staffNumber" htmlEscape="false" class="form-control" disabled="true"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>组织评定:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="originEva" htmlEscape="false" class="form-control" />
						<label class="Validform_checktip"></label>
					</td>
		   		</tr>
		      	<tr>
					<td  class="width-15 active text-right">	
		              <label>系统前端界面:</label>
		            </td>
					<td class="width-35">
					    <form:select  readonly="true" disabled="disabled"
							path="sysFrontUI" htmlEscape="false" class="form-control"
							dict="sysEvaType" nullmsg="请选择评价等级!" 
							cssClass="i-checks required" cssStyle="width:100%"/>   
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>系统后台设计:</label>
		            </td>
					<td class="width-45" colspan="3">
					    <form:select  readonly="true" disabled="disabled"
							path="sysBackstage" htmlEscape="false" class="form-control"
							dict="sysEvaType" nullmsg="请选择评价等级!" 
							cssClass="i-checks required" cssStyle="width:100%"/>   
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>系统主要功能:</label>
		            </td>
		            <td class="width-35">
						<form:input path="sysPredict" htmlEscape="false" class="form-control" datatype="dot"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>系统设置:</label>
		            </td>
					<td class="width-45" colspan="3">
					    <form:select  readonly="true" disabled="disabled"
							path="sysConfig" htmlEscape="false" class="form-control"
							dict="sysEvaType" nullmsg="请选择评价等级!" 
							cssClass="i-checks required" cssStyle="width:100%"/>   
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
		            <td class="width-35" colspan="3">
						<form:textarea path="remarks" rows="2" htmlEscape="false" class="form-control"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">	
		             	 <label>测评附件:</label>
		            </td>
					<td class="width-35" colspan="3">
						<input id="relateTable" type="hidden" value="medical_evaluation"/>
						<input id="relateFeild" type="hidden" value="evaAttach"/>						
						<form:fileinput buttonText="选择文件" saveType="billId" 
						fileActionSettings="{showRemove: true,showUpload: true}" isFormSubmit="true" 
						uploadExtraFieldData="relateTable,relateFeild"
						showUpload="false" showRemove="false" autoUpload="false" fileInputWidth="100px"  
						fileInputHeight="100px"  path="evaAttach" htmlEscape="false" class="form-control" />
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>