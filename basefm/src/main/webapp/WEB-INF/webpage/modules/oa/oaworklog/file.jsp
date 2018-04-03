<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>员工日志管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

				<%-- otherActionButtons = <button type="button" class="kv-file-down btn btn-xs btn-default" {dataKey} title="下载附件">
				<i class="fa fa-cloud-download"></i></button> --%>
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