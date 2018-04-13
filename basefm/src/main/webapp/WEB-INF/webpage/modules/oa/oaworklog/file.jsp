<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>医用日志管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="oaWorkLogForm">
 <div class="portlet-body">
    <input id="id" type="hidden" value="${param.id}"/>
    <html:display>

    </html:display>
    <form:fileinput saveType="billId" nested="false"
                    fileInputSetting="{showRemove : false, showBrowse : false, layoutTemplates : {actionDelete:''}}"
                    showUpload="false" showRemove="false" autoUpload="false"
                    path="infoid" buttonText="选择文件"/>
</div>

<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter"/>
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
</body>
</html>