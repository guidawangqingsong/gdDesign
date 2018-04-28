<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


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
				 <form  id="loginform"  method="post">
				<div class="box form">
					<div class="user-in">
						<div class="pwdLogin" >
							<div class="t"><spring:message code="sys.login.title"/></div>
							<div class="in"><input name="username" class="form-control" placeholder="<spring:message code="sys.login.username.placeholder"/>" required=""></div>
							<div class="in">
								<input name="password" type="password" class="form-control" placeholder="<spring:message code="sys.login.password.placeholder"/>" required="">
								<div class="auto">
									<input id="rememberMe"  type="checkbox" name="rememberMe" value="1" class="i-checks">
									<spring:message code="sys.login.rememberMe"/>
								</div>
							</div>
							 <c:if test="${showCaptcha eq 1}">
				                <div class="form-group">
				               	   <div class="pull-left">
				                     <input   name="jcaptchaCode" class="form-control" placeholder="<spring:message code="sys.login.captcha.placeholder"/>" required="">
				                   </div>
				                   <div class="pull-right">
				                     <img id="img_jcaptcha"  src="${appPath}/jcaptcha.jpg" width="100" height="35" onclick="changeJcaptchaSrc();" />
				                   </div>   
				                </div>
				                <div class="clearfix"></div>
			                </c:if>
							<div class="sub">
								<button type="submit" class="btn btn-success btn-block"><spring:message code="sys.login.submit.label"/> </button>
		                    </div>
		                    <div class="error">${error}</div>
		                    <p><font style="font-size:10px">如果您没有帐号? </font>
                    		<a id="btn-register" href="${adminPath}/sys/user/createUser?type=3&adminType=0"><font style="font-size:10px;color:#033661">请注册>></font></a> 
               	 			</p>
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
		 function changeJcaptchaSrc(){  
	            document.getElementById("img_jcaptcha").src='${appPath}/jcaptcha.jpg?_='+(new Date()).getTime();  
	        } 
		 window.onload = function(){
				var pwdLogin = document.getElementsByClassName("pwdLogin")[0];
			}
		</script>
	</body>
</html>
