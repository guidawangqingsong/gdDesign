<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.organization.edittitle" /></title>
    <meta name="decorator" content="form"/> 

</head>
<body class="white-bg"  formid="organizationForm">
     <form:form id="organizationForm" modelAttribute="data"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="oldType" name="oldType" value="${oldType}">
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		       <tr>
   		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.organization.code" />:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="code" class="form-control " datatype="*"  nullmsg="请输入组织编码" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>	
		        <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.organization.name" />:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="name" class="form-control " datatype="*" nullmsg="请输入组织名称！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
        
		      </tr>
		      <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.organization.type" />:</label>
		         </td>
		         <td class="width-35" >
		             <form:radiobuttons path="type" class="width-35"
								 dict="orggroup" delimiter="&nbsp;&nbsp;"
								 htmlEscape="false" defaultValue="1"
								cssClass="i-checks required" />
		              <label class="Validform_checktip"></label>
		         </td>
	           <td class="width-15 active"><label class="pull-right"><spring:message code="sys.organization.pre" />:</label></td>
		         <td class="width-35">
		            <form:treeselect title="请选择上级机构" async="false" path="parentId" dataUrl="${adminPath}/sys/organization/treeData" callback="setBackVal" labelName="parentname" labelValue="${data.parent.name}" />	   
				  </td>    
		      </tr>
		      <tr>
		        <td class="width-15 active"><label class="pull-right"><spring:message code="sys.organization.orgType" />:</label></td>
		          <td class="width-80" colspan="4" >
	              <form:checkboxes path="orgType"  class="width-35"
							 dict="contrlType" delimiter="&nbsp;&nbsp;"
							 htmlEscape="false"	showValue="${orgTypeIds}"
							 cssClass="i-checks required" />
	              <label class="Validform_checktip"></label>
	              </td>
		      </tr>
		       <tr>
		         <td  class="width-15 active text-right">	
		              <label><spring:message code="sys.organization.remarks" />:</label>
		         </td>
		         <td class="width-35" colspan="3" >
		              <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control "/>
		         </td>
		      </tr>
		  
		   </tbody>
		   </table>   
	</form:form>
	<script type="text/javascript" src="${staticPath}/vendors/layer/layer.min.js"></script>
	<script type="text/javascript">
		/**
		 * @function 设置选择上级结构的回调.<br> 
		 * @date 2018/1/3.<br> 
		 * @author zcg .<br> 
		 */
		function setBackVal(data){
			 var id = $('#id').val();
			 if(data.length>0&data[0].id==id){
				 $('#parentId').val('');
				 $('#parentname').val('无');
				 layer.alert('上级不能是本级', {icon: 0, title:'提示'});
			 }
		}
	</script>
</body>

</html>