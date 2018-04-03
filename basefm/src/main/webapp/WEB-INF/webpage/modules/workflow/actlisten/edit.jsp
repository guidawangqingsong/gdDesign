<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>流程监听</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="actListenForm">
    <form:form id="actListenForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>监听编号:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写监听编号" 
						  	ajaxurl="${adminPath}/workflow/actlisten/validate"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>监听名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*"  nullmsg="请填写监听名称"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>监听类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="listenType" htmlEscape="false" class="form-control"  dict="listenType"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>事件类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="eventType" htmlEscape="false" class="form-control"  dict="eventType"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>值类型:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="valType" htmlEscape="false" class="form-control"  dict="valType"  datatype="*"  defaultValue="0"  cssClass="i-checks required"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>是否启用:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="status" htmlEscape="false" class="form-control"  dict="sf"  defaultValue="1"    cssClass="i-checks required"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>类路径/表达式:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="listenValue" htmlEscape="false" class="form-control"  datatype="*"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>描述:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="description" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>