<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.user.updateuser" /></title>
    <meta name="decorator" content="form"/> 
    <html:component name="bootstrap-fileinput" />
</head>
<body class="white-bg"  formid="userForm">
<form:form id="userForm" modelAttribute="data" method="post" class="form-horizontal">
  <form:hidden path="id" />
  <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
    <tbody>
      <tr>
        <td class="width-15 active text-right">
          <label><spring:message code="sys.user.username" />:</label></td>
        <td class="width-35">${data.username}</td>
        <td class="width-15 active text-right">
          <label>
            <font color="red">*</font><spring:message code="sys.user.realname" />:</label></td>
        <td class="width-35">
          <form:input path="realname" class="form-control " datatype="*" nullmsg="请输入姓名！" htmlEscape="false" />
          <label class="Validform_checktip"></label>
        </td>
      </tr>
      <tr>
        <td class="width-15 active text-right">
          <label>
            <font color="red">*</font><spring:message code="sys.user.email" />:</label></td>
        <td class="width-35">
          <form:input path="email" class="form-control" datatype="e" nullmsg="请输入姓名！" htmlEscape="false" />
          <label class="Validform_checktip"></label>
        </td>
        <td class="width-15 active text-right">
          <label>
            <font color="red">*</font><spring:message code="sys.user.phone" />:</label></td>
        <td class="width-35">
          <form:input path="phone" class="form-control" htmlEscape="false" datatype="m" nullmsg="请输入用户名！" />
          <label class="Validform_checktip"></label>
        </td>
      </tr>
       <tr>
        <td class="active"><label class="pull-right"><font color="red">*</font><spring:message code="sys.user.type" />:</label></td>
        <td>
        	<form:select path="type" class="width-35" 
					 dict="userType" delimiter="&nbsp;&nbsp;"
					 htmlEscape="false" defaultValue="1"
					cssClass="i-checks required" />        
        </td>
        <td class="width-15 active"><label class="pull-right"><spring:message code="sys.user.satff" />:</label></td>
		<td colspan="3">
	   		<form:treeselect  title="请选择默认组织机构" path="orgId"  nested="false"  dataUrl="${adminPath}/sys/organization/treeData" labelName="parentname" labelValue="${data.orgName}"/>	   
		</td>
     </tr>
    </tbody>
  </table>
</form:form>
</body>
</html>