<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.role.edittitle" /></title>
    <meta name="decorator" content="form"/> 
    
</head>

<body class="white-bg"  formid="modelForm">
    <form:form id="modelForm" modelAttribute="data"  method="post" class="form-horizontal">
		<%-- <form:hidden path="id"/> --%>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		       <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>模型名称:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="modelName" class="form-control " datatype="*" nullmsg="请填写模型名称！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		         <td  class="width-15 active text-right">	
		          	<label><font color="red">*</font>模型类型:</label>
		         </td>
		         <td  class="width-35" >
		             <form:treeselect title="请选择上级流程类型表" path="modelType" datatype="*" nullmsg="请选择模型类型！"
		             dataUrl="${adminPath}/workflow/actretype/treeData" labelName="parentname" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
			  <tr>
		         <td  class="width-15 active text-right">	
		              <label>备注:</label>
		         </td>
		         <td class="width-35" colspan="3" >
		              <form:textarea path="discription" htmlEscape="false" rows="3" maxlength="200" class="form-control "/>
		         </td>
		      </tr>
		   </tbody>
		</table>   
	</form:form>
</body>
</html>