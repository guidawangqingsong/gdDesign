<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>流程转办</title>
    <meta name="decorator" content="form"/> 
    
</head>

<body class="white-bg"  formid="actReTypeForm">
    <form:form id="actReTypeForm"  modelAttribute="data" method="post" action="${adminPath}/activiti/flow/transferAssignee" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="taskId" defaultValue="${param.taskId}"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			  <tr>
		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>转办人员:</label>
		         </td>
		         <td class="width-35" >
		             	<form:gridselect gridId="userGridId" genField="true" title="选择人员" labelName="realname"
		    				nested="true" path="assignee" gridUrl="${adminPath}/activiti/flow/selectUser" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>转办原因:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="reason" htmlEscape="false" class="form-control" style="height: 80px"     />
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>		      
		   </tbody>
		</table>   
	</form:form>
</body>
</html>