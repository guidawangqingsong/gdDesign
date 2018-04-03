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
  <form:hidden path="oldStaffId"/>
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
          <form:input path="realname" class="form-control " readonly="true" datatype="*" nullmsg="请输入姓名！"  />
          <label class="Validform_checktip"></label>
        </td>
      </tr>
      <tr>
        <td class="width-15 active text-right">
          <label>
            <font color="red">*</font><spring:message code="sys.user.email" />:</label></td>
        <td class="width-35">
          <form:input path="email" class="form-control" datatype="e" readonly="true"
          	ajaxurl="${adminPath}/sys/user/validate" nullmsg="请输入电子邮箱！"  validErrorMsg="电子邮箱已存在"/>
          <label class="Validform_checktip"></label>
        </td>
        <td class="width-15 active text-right">
          <label>
            <font color="red">*</font><spring:message code="sys.user.phone" />:</label></td>
        <td class="width-35">
          <form:input path="phone" class="form-control" readonly="true" ajaxurl="${adminPath}/sys/user/validate"  validErrorMsg="联系电话已存在"  datatype="m" nullmsg="请输入用户名！" />
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
       <td class="width-15 active"><label class="pull-right"><spring:message code="sys.user.manager" />:</label></td>
		<td  class="width-35">
		   <form:treeselect  title="请选择管理单元" path="morgId"  nested="true"  dataUrl="${adminPath}/sys/organization/treeData?type=0" labelName="morgName" labelValue="${data.morgName}" />	   
		</td>
     </tr>
     <tr>
     <td class="width-15 active"><label class="pull-right"><font color="red">*</font><spring:message code="sys.user.default.org" />:</label></td>
		<td class="width-35">
	   		<form:treeselect  title="请选择默认组织机构" path="orgId"  nested="true" datatype="*"  
	   		dataUrl="${adminPath}/sys/organization/treeData" labelName="orgName" labelValue="${data.orgName}"/>	   
		</td>
		<td id="showStaffId" class="width-15 active"><label class="pull-right"><spring:message code="sys.user.satff" />:</label></td>
		<td id="showStaffName" class="width-35">
		   <form:gridselect gridId="staffGridId" genField="true" title="关联职员" formField="staffId,realname,email,phone,staffName" 
		   gridField="id,name,email,moblie,name" nested="true" path="staffId" gridUrl="${adminPath}/sys/staff/listStaff" 
		   callback="setBackVal"  labelName="staffName" labelValue="${data.staffName}"  />
		</td>
     </tr>
    </tbody>
  </table>
</form:form>
<script type="text/javascript">
function setBackVal(){
	var realName = $("#realname").val();
	$("#staffName").val(realName);
}

$("#type").change(function(){
	if(1==$(this).val()){
	   $("#showStaffId").show();
	   $("#showStaffName").show();
	   $("#realname").attr("readonly",true);
	   $("#email").attr("readonly",true);
	   $("#phone").attr("readonly",true);
	}else{
	   $("#showStaffId").hide();
	   $("#showStaffName").hide();
	   $("#realname").removeAttr("readonly");
	   $("#email").removeAttr("readonly");
	   $("#phone").removeAttr("readonly");
	}
});
</script>
</body>
</html>