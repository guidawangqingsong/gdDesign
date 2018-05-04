<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="sys.site.title" arguments="${platformName}"/></title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
    <meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <link rel="stylesheet" type="text/css" href="${staticPath}/uadmin/css/login.css"/>
    <html:css  name="iCheck,Validform"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body>
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
				<form  id="userForm"  method="post">
				<div class="box form">
					<div class="user-in">
						<div class="pwdLogin" >
							<div class="t">用户注册&nbsp;
							<a id="btn-register" href="${adminPath}/admin"><font style="font-size:10px;color:#033661">请登录>></font></a>
							</div>
							<div><input name="id" id="id" type="hidden" /></div>
							<div><input name="orgId" id="orgId" type="hidden" value="40288ab85b6080e1015b60996d690005"/></div>
							<div class="in1">
								<input name="username" id="username" class="form-control"
								placeholder="<spring:message code="sys.login.username.placeholder"/>" required="">
							</div>
							<div class="in1">
								<input name="realname" id="realname" class="form-control" placeholder="姓名" required="">
							</div>
							<div class="in1">
								<input name="phone" id="phone" class="form-control" placeholder="输入联系电话" datatype="m" required="">
							</div>
							<div class="in1">
								<input name="password" type="password" id="password" class="form-control" required="" placeholder="<spring:message code="sys.login.password.placeholder"/>" errormsg="密码范围在6~16位之间！" >
							</div>
			                <div class="sub1">
								<button id="checkButton" class="btn btn-success btn-block" >注&nbsp;册</button>
		                    </div>
		                    <div class="error">${error}</div> 
						</div>
					</div>
				</div>
				</form>
				<div id="light3"></div>
			</div>
			<div id="footer"></div>
		</div>
		<div id="light"></div>
		<div id="light2"></div>
	<script src="${staticPath}/uadmin/js/jquery-1.10.2.min.js"></script>
	<html:js  name="iCheck,Validform"/>
	<html:js name="bootstrap-fileinput" />
	<html:js name="simditor" />
	<script type="text/javascript">
	$("#checkButton").attr('disabled',true);
	
	$("#username").blur(function (){
		var username=$("#username").val();
		if(username!=''){
			$("#checkButton").attr('disabled',false);
		}
		$.ajax({
			url : "${adminPath}/sys/user/validate",
			type : "post",
			data : {
				"username" : username
			},
			success : function(d){
				if (d.status=='y') {
					console.log(d.info);
				}else{
					alert(d.info);
					window.location.href=("${adminPath}/sys/user/createUser?type=3&adminType=0");
				}
			}
		});
	});
	
	$("#realname").blur(function (){
		var realname=$("#realname").val();
		if(realname==null||realname==''){
			this.value="默认姓名";
		}
	});
	
	$("#checkButton").click(function (){
		var orgId=$("#orgId").val();
		var username=$("#username").val();
		var realname=$("#realname").val();
		var phone=$("#phone").val();
		var password=$("#password").val();
		
		$.ajax({
			url : "${adminPath}/sys/user/createUser?type=3&adminType=0",
			type : 'post',
			data:{
				"orgId" : orgId,
				"username" : username,
				"realname" : realname,
				"phone" : phone,
				"password" : password
			},
			success : function(d) {
				if (d.ret==0) {
					console.log(d);
				    alert("注册成功！请登录");
				    window.location.href=("${adminPath}/admin");
				}else{
					console.log(d);
				    alert("注册失败！");
				    window.location.href=("${adminPath}/sys/user/createUser?type=3&adminType=0");
				}
			}
		});
	});
	</script>
	</body>
</html>
