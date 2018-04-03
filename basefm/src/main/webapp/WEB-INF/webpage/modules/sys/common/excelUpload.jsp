<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.skill.name" /></title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="uploadExcel">
    <form:form id="uploadExcel" modelAttribute="data" method="post" action="null" class="form-horizontal" >
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		  		<tr>
					<td class="width-35">
						<input id="controllerName" type="hidden" value="${param.controllerName}"/>
						<input id="nodeId" type="hidden" value="${param.nodeId}"/>						
						<form:fileinput buttonText="选择文件" extend="xlsx" saveType="billId" uploadUrl="${param.controllerName}?nodeId=${param.nodeId}"  fileActionSettings="{showRemove: true,showUpload: false}" isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
						showUpload="false" showRemove="false" autoUpload="false" nested="false" fileInputWidth="100px"  fileInputHeight="100px"  path="remarks" htmlEscape="false" class="form-control"  multiple="false"    />
					</td>
				</tr>		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
<!-- 全局js -->
<html:js name="iCheck,Validform,markdown,syntaxhighlighter" />
<!-- 自定义js -->
<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
</html>