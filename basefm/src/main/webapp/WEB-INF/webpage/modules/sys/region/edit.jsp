<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>区域管理</title>
    <meta name="decorator" content="form"/> 
    
</head>

<body class="white-bg"  formid="regionForm">
    <form:form id="regionForm"  modelAttribute="data"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			  <tr>
		         <td class="width-15 active text-right">
		         	<label><font color="red">*</font>编码:</label>
		         </td>
		          <td class="width-35">   
				  	<form:input path="code"  class="form-control " ajaxurl="${adminPath}/sys/region/validate" datatype="*" nullmsg="请输入区域编码！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
				  </td>
		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>名称:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="name" class="form-control " datatype="*" nullmsg="请输入区域名称！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">上级区域:</label></td>
			        <td class="width-35">
			            <form:treeselect title="请选择上级管理" path="parentId" dataUrl="${adminPath}/sys/region/treeData?async=true" labelName="parentname" labelValue="${data.parent.name}" />	   
					</td>
					<td  class="width-15 active text-right">	
		              <label>排序:</label>
		            </td>
					<td class="width-35">
						<form:input path="regionOrder" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>英文名:</label>
		            </td>
					<td class="width-35">
						<form:input path="regionNameEn" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>简称:</label>
		            </td>
					<td class="width-35">
						<form:input path="regionSshortnameEn" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		      
		   </tbody>
		</table>   
	</form:form>
</body>
</html>