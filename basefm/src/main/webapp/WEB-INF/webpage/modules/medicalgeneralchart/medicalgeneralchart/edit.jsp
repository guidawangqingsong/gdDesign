<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>综合分析</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="medicalGeneralChartForm">
    <form:form id="medicalGeneralChartForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="staffId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
		   			<td  class="width-15 active text-right">	
		              <label>好评数量:</label>	<!-- 应该关联sys_staff表查询员工id -->
		            </td>
					<td class="width-35">
						<form:input path="highOpinon" htmlEscape="false" class="form-control" disabled="true"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>差评数量:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="lowOpinon" htmlEscape="false" class="form-control" disabled="true"/>
						<label class="Validform_checktip"></label>
					</td>
		   		</tr>
		      	<tr>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
		            <td class="width-35" colspan="3">
						<form:textarea path="remarks" rows="2" htmlEscape="false" class="form-control"/>
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