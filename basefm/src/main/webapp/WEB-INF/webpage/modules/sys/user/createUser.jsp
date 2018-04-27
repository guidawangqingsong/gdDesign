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
				<form  id="userForm"  method="post" action="${adminPath}/sys/user/createUser?type=3&adminType=0">
				<div class="box form">
					<div class="user-in">
						<div class="pwdLogin" >
							<div class="t">用户注册&nbsp;
							<a id="btn-register" href="${adminPath}/admin"><font style="font-size:10px;color:#033661">请登录>></font></a>
							</div>
							<div><input name="id" type="hidden" /></div>
							<div><input name="orgId" type="hidden" value="40288ab85b6080e1015b60996d690005"/></div>
							<div class="in1">
								<input name="username" class="form-control"
								placeholder="<spring:message code="sys.login.username.placeholder"/>" required="">
							</div>
							<div class="in1">
								<input name="realname" class="form-control" placeholder="姓名" required="">
							</div>
							<div class="in1">
								<input name="phone" class="form-control" placeholder="输入联系电话" required="">
							</div>
							<div class="in1">
								<input name="password" type="password" class="form-control" required="" placeholder="<spring:message code="sys.login.password.placeholder"/>" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" >
							</div>
			                <div class="sub1">
								<button class="btn btn-success btn-block" function="checkForm()">注&nbsp;册</button>
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
	<script type="text/javascript">
	var username=$("#username").val();
	var realname=$("#realname").val();
	var realname=$("#phone").val();
	var password=$("#password").val();
	
	function createUser() {
		$.ajax({
			url : "${adminPath}/sys/user/createUser?type=3&adminType=0",
			type : 'post',
			cache : false,
			success : function(d) {
				if (d.ret==0) {
					var msg = d.msg;
				    swal("提示！", msg, "success");
				    window.location.href("${adminPath}/admin");
				}else{
					var msg = d.msg;
				    swal("提示！", msg, "error");
				}
			}
		});
    }
	
	function checkForm(){
		if(username.equals("")&&realname.equals("")){
			alert("用户名或姓名不能为空！")
		}
	}
	
	</script>
	</body>
</html>
