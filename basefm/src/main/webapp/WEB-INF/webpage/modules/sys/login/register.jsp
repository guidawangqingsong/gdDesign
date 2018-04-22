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
    
    <html:css  name="iCheck,Validform"/>
    <html:js  name="iCheck,Validform"/>
    
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
						<a href="javascript:" class="QR-icon"></a>
						<div class="QR" style="display: none;">
							<div class="qr-t">
								APP下载
								<p>扫描下面二维码下载APP</p>
							</div>
							<div class="qr-img">
								<div class="imgbox">
									<img id="ios-img" src=""  onerror="this.src=''"/>
									<p>苹果版 v1.0</p>
								</div>
								<div class="imgbox">
									<img id="android-img" src="" onerror="this.src=''"/>
									<p>安卓版 v1.0</p>
								</div>
							</div>
						</div>
						<div class="pwdRegister" >
							<div class="t"><spring:message code="sys.user.createuser"/></div>
							<div class="in">
								<form:input path="phone" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  
		             	              readonly="true" htmlEscape="false"  datatype="m"  nullmsg="请输入联系电话！"/>
		             				  <label class="Validform_checktip"></label>
							</div>
							<div class="in">
								<input type="password" value="" name="password"  class="form-control" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" />
		                        <label class="Validform_checktip"></label>
							</div>
							<div class="in">
								<input type="password" value="" name="userpassword2" class="form-control" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" />
		             			<label class="Validform_checktip"></label>
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
								<button type="submit" class="btn btn-success btn-block">创&nbsp;&nbsp;建</button>
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
		 function changeJcaptchaSrc(){  
	            document.getElementById("img_jcaptcha").src='${appPath}/jcaptcha.jpg?_='+(new Date()).getTime();  
	        } 
		 window.onload = function(){
			 getIOSandAND();
			 registerUser();
				var qr = document.getElementsByClassName("QR")[0];
				var pwdLogin = document.getElementsByClassName("pwdRegister")[0];
				var qrClick =  document.getElementsByClassName("QR-icon")[0];
				qrClick.onclick = function(){
					if(qr.style.display === "none"){
						//切换APP下载页面
						this.className +=" active";
						pwdRegister.style.display = "none";
						qr.style.display = "block";
					}else{
						//切换登录页面
						this.className = "QR-icon";
						pwdRegister.style.display = "block";
						qr.style.display = "none";
					}
				}
			}
		    function getIOSandAND(){
				 $.ajax({
					type:"POST",
					url:"${adminPath}/sys/appversion/findAppVersion",
					async:false,
					success:function(data){
						data = data.data;
						$.each(data,function(i,item){
							if(item.type==2){
								$("img#ios-img").attr("src","${appPath}/"+item.qrcodeUrl);
							}else{
								$("img#android-img").attr("src","${appPath}/"+item.qrcodeUrl);
							}
						});
						
					}
				 });
			 }
		    function registerUser(){
				 $.ajax({
					type:"POST",
					url:"${adminPath}/sys/user/createUser",
					async:false,
					success:function(data){
						data = data.data;
						top.layer.alert('创建成功!', {icon: 0, title:'success'});
						window.location.href("${adminPath}/sys/login/login.jsp");
					}
				 });
			 }
		</script>
	</body>
</html>
