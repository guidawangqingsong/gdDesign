<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="sys.user.createuser" /></title>
    <meta name="decorator" content="form"/>
    <html:css  name="iCheck,Validform"/>
    <html:js  name="iCheck,Validform"/>
</head>

<body class="white-bg"  formid="userForm">
     <form:form id="userForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		       <tr>
		         <td  class="width-15 active text-right">	<label><font color="red">*</font><spring:message code="sys.user.username" />:</label></td>
		         <td  class="width-35" >
		             <form:input path="username" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  validErrorMsg="用户名重复"  htmlEscape="false"  datatype="*"  nullmsg="请输入用户名！"/>
		             <label class="Validform_checktip"></label>
		         </td>
		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.user.realname" />:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="realname" class="form-control " readonly="true" datatype="*" nullmsg="请输入姓名！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		      <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.user.email" />:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="email" class="form-control" ajaxurl="${adminPath}/sys/user/validate"   
		             	readonly="true" datatype="e" nullmsg="请输入邮箱！"  htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		         <td  class="width-15 active text-right">	
		           	 <label><font color="red">*</font><spring:message code="sys.user.phone" />:</label>
		         </td>
		         <td  class="width-35" >
		             <form:input path="phone" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  
		             	readonly="true" htmlEscape="false"  datatype="m"  nullmsg="请输入联系电话！"/>
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		      <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font><spring:message code="sys.user.pwd" />:</label>
		         </td>
		         <td class="width-35" >
		             <input type="password" value="" name="password"  class="form-control" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" />
		             <label class="Validform_checktip"></label>
		         </td>
		         <td  class="width-15 active text-right">	<label><font color="red">*</font><spring:message code="sys.user.confirm.pwd" /></label></td>
		         <td  class="width-35" >
		             <input type="password" value="" name="userpassword2" class="form-control" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" />
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
					<label class="Validform_checktip"></label>     
		         </td>
				<td class="width-15 active"><label class="pull-right"><spring:message code="sys.user.manager" />:</label></td>
				<td class="width-35">
				   <form:treeselect  title="请选择管理单元" path="morgId"    dataUrl="${adminPath}/sys/organization/treeData?type=0" labelName="morgName" labelValue="${data.morgName}" />	   
				   <label class="Validform_checktip"></label>
				</td>
		      </tr>
		       <tr>
     		    <td class="width-15 active"><label class="pull-right"><font color="red">*</font><spring:message code="sys.user.default.org" />:</label></td>
				<td class="width-35">
				   <form:treeselect  title="请选择默认组织机构" path="orgId"   datatype="*"  dataUrl="${adminPath}/sys/organization/treeData" labelName="orgName" labelValue="${data.orgName}" />	   
				   <label class="Validform_checktip"></label>
				</td>
				<td id="showStaffId" class="width-15 active"><label class="pull-right"><spring:message code="sys.user.satff" />:</label></td>
				<td id="showStaffName" class="width-35">
				   <form:gridselect gridId="staffGridId" genField="true" title="关联职员" formField="staffId,realname,email,phone,staffName" gridField="id,name,email,moblie,name"  path="staffId" gridUrl="${adminPath}/sys/staff/listStaff" callback="setBackVal"  labelName="staffName" labelValue="无"  />
				</td>				
		      </tr>
		   </tbody>
		   </table>   
	</form:form>
<script type="text/javascript">
function setBackVal(data){
	var userId = $("#id").val();
	if(data.length>0&&!userId){
		var orgId = data[0].orgId;
		var orgName = data[0].orgName;
		$("#orgId").val(orgId);
		$("#orgName").val(orgName);
	}
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
