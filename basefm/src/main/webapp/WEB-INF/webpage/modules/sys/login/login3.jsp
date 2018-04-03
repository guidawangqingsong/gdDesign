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
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
    <html:css  name="favicon,bootstrap,font-awesome,animate,pace,iCheck,toastr,bootstrapvalidator"/>
    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/style-responsive.css"> 
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/nstyle1/css/login.css"> 
    <style type="text/css">
    	.form-control,.btn{height:40px;}
    </style>
</head>

<body id="signin-page">
    <div class="page-form" id="form">
    	<div class="logo">
				<img src="${staticPath}/uadmin/css/themes/nstyle1/img/logo.png" />
				<p><spring:message code="sys.project.name"/></p>
			</div>
        <form  id="loginform"  method="post" class="form">
            <div class="header-content">
                <h1></h1>
            </div>
            <div class="body-content">
                <div class="form-group">
                    <div class="input-icon left"><i class="fa fa-user"></i>
                        <input name="username" class="form-control" placeholder="<spring:message code="sys.login.username.placeholder"/>" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon left"><i class="fa fa-key"></i>
                        <input name="password" type="password" class="form-control" placeholder="<spring:message code="sys.login.password.placeholder"/>" required="">
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
                <%-- <div class="form-group" style="margin-top:10px;">
                	<div class="pull-left">
	                    <div class="checkbox-list" style="color:red">${error}</div>
	                </div>
	                <div class="pull-right">
	                    <button type="submit" class="btn btn-success btn-block"><spring:message code="sys.login.submit.label"/> &nbsp;
	                        <i class="fa fa-chevron-circle-right"></i>
	                    </button>
	                </div>
	            </div> --%>
	             <div class="form-group"> <font color="red">${error}</font>
	             	<button type="submit" class="btn btn-success btn-block"><spring:message code="sys.login.submit.label"/> &nbsp;
                        <i class="fa fa-chevron-circle-right"></i>
                    </button></div>
                <div class="clearfix"></div>
            </div>
        </form>
    </div>
    <div id="top">
		<div class="bg b1"></div>
		<div class="bg b2"></div>
		<div class="bg b3"></div>
		<div class="bg w"></div>
	</div>
	<div id="bottom"></div>
	
    <html:js  name="jquery,bootstrap,iCheck,bootstrap-hover-dropdown,toastr,bootstrapvalidator"/>
    <script src="${staticPath}/uadmin/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery-ui.js"></script>
    <script src="${staticPath}/uadmin/js/html5shiv.js"></script>
    <script src="${staticPath}/uadmin/js/respond.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery.menu.js"></script>
    <script>
        //BEGIN CHECKBOX & RADIO
        $('input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_minimal-grey',
            increaseArea: '20%' // optional
        });
        $('input[type="radio"]').iCheck({
            radioClass: 'iradio_minimal-grey',
            increaseArea: '20%' // optional
        });
        //END CHECKBOX & RADIO
        function changeJcaptchaSrc(){  
            document.getElementById("img_jcaptcha").src='${appPath}/jcaptcha.jpg?_='+(new Date()).getTime();  
        } 
        function initTb() {
			var winH = $(window).height();
			$("#top").height(winH * 0.6);
			$("#bottom").height(winH * 0.4);
			$("#form").height($("#loginform").height());
		}
		$(window).resize(function() {
			initTb();
		});
    </script>
     <script type="text/javascript">
		$(document).ready(function() {
			initTb();
		     $("#loginform").bootstrapValidator();
		     var error="${error}";
		});
		
	</script>
</body>

</html>