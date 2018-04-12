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
    <form:form id="oaWorkLogForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="staffId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
	       	<input id="relateTable" type="hidden" value="oa_work_log" />
			<input id="relateFeild" type="hidden" value="logAttach" />						
			<form:fileinput saveType="billId" 
			fileActionSettings="{showRemove: false,showUpload: false}" 
			isFormSubmit="true"  uploadExtraFieldData="relateTable,relateFeild"
			showUpload="false" showRemove="false" autoUpload="false" fileInputWidth="100px"  
			fileInputHeight="100px"  path="logAttach" htmlEscape="false" class="form-control" />
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>