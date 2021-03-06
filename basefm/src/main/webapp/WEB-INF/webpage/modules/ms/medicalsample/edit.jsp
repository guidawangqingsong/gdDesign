<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>costSample</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="medicalSampleForm">
    <form:form id="medicalSampleForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
					<td  class="width-15 active text-right">	
		              <label>挂号费:</label>
		            </td>
		            <td class="width-35">
						<form:input path="registerFee" htmlEscape="false" class="form-control" datatype="dot" />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>医药费:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="medicalFee" htmlEscape="false" class="form-control" datatype="dot"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>治疗费:</label>
		            </td>
		            <td class="width-35">
						<form:input path="treatmentFee" htmlEscape="false" class="form-control" datatype="dot"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>住院费:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="hospitalFee" htmlEscape="false" class="form-control" datatype="dot"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>其他:</label>
		            </td>
		            <td class="width-35">
						<form:input path="other" htmlEscape="false" class="form-control" datatype="dot"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>人均费用（样本）:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="personalFee" htmlEscape="false" class="form-control" datatype="dot"/>
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