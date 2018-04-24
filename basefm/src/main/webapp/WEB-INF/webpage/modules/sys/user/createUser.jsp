<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<<html>

<head>
    <title><spring:message code="sys.site.title" arguments="${platformName}"/></title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
    <meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <html:css  name="iCheck,Validform"/>
    <html:js  name="iCheck,Validform"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/uadmin/css/login.css"/>
    
</head>

<body formid="userForm">
<script src="${staticPath}/uadmin/js/jquery-1.10.2.min.js"></script>
		<div id="main-center">
			<div class="w-1200">
				<div class="box">
					<div class="logo">
						<img src="${staticPath}/uadmin/images/login/logo.png"/>
						<span class="logo-text"><spring:message code="sys.project.name"/></span>
					</div>
					<div class="img">
						<img src="${staticPath}/uadmin/images/login/img.png"/>
					</div>
				</div>
				<div class="box form">
					<div class="user-in">
						<div class="pwdLogin" >
							<div class="t">用户注册&nbsp;<a id="btn-register" href="${adminPath}/admin"><font style="font-size:10px;color:#033661">请登录>></font></a></div>
							<%-- <div class="in">
								<input name="username" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  
								placeholder="<spring:message code="sys.login.username.placeholder"/>" validErrorMsg="用户名重复"  htmlEscape="false"  datatype="*"  nullmsg="请输入用户名！">
								<label class="Validform_checktip"></label>
							</div>
							<div class="in">
								<input name="password" type="password" class="form-control" datatype="*6-16" 
								placeholder="<spring:message code="sys.login.password.placeholder"/>" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" >
								<label class="Validform_checktip"></label>
							</div>
							<div class="in">
								<input name="userpassword2" type="password" class="form-control" datatype="*" recheck="password" 
								placeholder="确认密码" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！">
								<label class="Validform_checktip"></label>
							</div> --%>
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
							      </tr>
							      <tr>
							         <td  class="width-15 active text-right">	
							              <label><font color="red">*</font><spring:message code="sys.user.pwd" />:</label>
							         </td>
							         <td class="width-35" >
							             <input type="password" value="" name="password"  class="form-control" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" />
							             <label class="Validform_checktip"></label>
							         </td>
							      </tr>
							      <tr>
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
							      </tr>
							       <tr>
					     		    <td class="width-15 active"><label class="pull-right"><font color="red">*</font><spring:message code="sys.user.default.org" />:</label></td>
									<td class="width-35">
									   <form:treeselect  title="请选择默认组织机构" path="orgId"   datatype="*"  dataUrl="${adminPath}/sys/organization/treeData" labelName="orgName" labelValue="${data.orgName}" />	   
									   <label class="Validform_checktip"></label>
									</td>
							      </tr>
							      <tr>
							      	<td id="showStaffId" class="width-15 active"><label class="pull-right"><spring:message code="sys.user.satff" />:</label></td>
									<td id="showStaffName" class="width-35">
									   <form:gridselect gridId="staffGridId" genField="true" title="关联职员" formField="staffId,realname,email,phone,staffName" gridField="id,name,email,moblie,name"  path="staffId" gridUrl="${adminPath}/sys/staff/listStaff" callback="setBackVal"  labelName="staffName" labelValue="无"  />
									</td>
							      </tr>
							   </tbody>
							   </table>
						    </form:form>
						</div>
						<div class="sub1">
							<button type="submit" class="btn btn-success btn-block">注&nbsp;册</button>
	                    </div>
					</div>
				</div>
				<div id="light3"></div>
			</div>
			<div id="footer"></div>
		</div>
		<div id="light"></div>
		<div id="light2"></div>
	
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
