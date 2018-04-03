<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>App版本管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="appVersionForm">
    <form:form id="appVersionForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control"  datatype="*"  nullmsg="请填写版本编码" 
							ajaxurl="${adminPath}/sys/appversion/validate" />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*"  nullmsg="请填写版本名称"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>使用机型:</label>
		            </td>
					<td class="width-35">
						<form:select path="type" htmlEscape="false" class="form-control"  dict="appType"  datatype="*"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>是否最新:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="versionStatus" htmlEscape="false" class="form-control"  dict="sf" defaultValue="1"
							datatype="*"    cssClass="i-checks required"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>版本号:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="version" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写版本号"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>下载地址:</label>
		            </td>
					<td class="width-35"  colspan="3">
						<form:input path="downloadUrl" htmlEscape="false" class="form-control"  datatype="*"  
							nullmsg="请填写下载地址" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>二维码地址:</label>
		            </td>
		            
		            
					<td class="width-35"  colspan="3">
						<%-- <form:input path="qrcodeUrl" htmlEscape="false" class="form-control"  datatype="*"  
							nullmsg="请填写二维码地址"  />
						<label class="Validform_checktip"></label> --%>
						<form:fileinput multiple="false" fileInputWidth="100px"  fileInputHeight="100px"  path="qrcodeUrl" 
							datatype="*" nullmsg="请上传二维码" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		      	<tr>
		      		<td  class="width-15 active text-right">	
		              <label>描述:</label>
		            </td>
					<td class="width-35"  colspan="3">
						<form:textarea path="description" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
		      	</tr>
		      	<tr>
		      		<td  class="width-15 active text-right">	
		              <label>app文件:</label>
		            </td>
					<td class="width-35" colspan="3">
						<input id="relateTable" type="hidden" value="sys_app_version"/>
						<input id="relateFeild" type="hidden" value="filePath"/>									
						<form:fileinput buttonText="选择文件" saveType="billId" fileActionSettings="{showRemove: true,showUpload: false}" isFormSubmit="true" uploadExtraFieldData="relateTable,relateFeild"
						showUpload="false" multiple="false" uploadUrl="${adminPath}/sys/appversion/upload" extend="apk" showRemove="false" autoUpload="false" path="filePath" htmlEscape="false" class="form-control"      />
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