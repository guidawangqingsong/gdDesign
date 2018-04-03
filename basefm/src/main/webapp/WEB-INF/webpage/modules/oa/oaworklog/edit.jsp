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

<body class="white-bg"  formid="oaWorkLogForm">
    <form:form id="oaWorkLogForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="staffId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>医护编号:</label>	<!-- 应该关联sys_staff表查询员工id -->
		            </td>
					<td class="width-35">
						<form:input path="staffNumber" htmlEscape="false" class="form-control" disabled="true" datatype="*"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>日志日期:</label>
		            </td>
		            <td class="width-45" colspan="3">
						<form:input path="logTime" htmlEscape="false" class="form-control layer-date"  datefmt="yyyy-MM-dd"
						onclick="laydate({istime: true, istoday:true, format: 'YYYY-MM-DD',min:laydate.now(-3),max:laydate.now(),
						ready: function(){ins22.hint('日期可选值设定在 <br> #min 到  laydate.now()'); }})"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              	<label>日志主题:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:input path="logTheme" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>日志状态:</label>
		            </td>
		            <td class="width-35">
					    <form:select  readonly="true" onclick="hehe()" disabled="disabled"
							path="logState" htmlEscape="false" class="form-control"
							dict="logState" datatype="*" nullmsg="请选择日志状态!" 
							cssClass="i-checks required" cssStyle="width:100%"/>   
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>日志类型:</label>
		            </td>
					<td class="width-35">
					    <form:select  readonly="true" onclick="hehe()" disabled="disabled"
							path="logType" htmlEscape="false" class="form-control"
							dict="logType" datatype="*" nullmsg="请选择设备分类!" 
							cssClass="i-checks required" cssStyle="width:100%"/>   
						<label class="Validform_checktip"></label>
					</td>			
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>日志内容:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="logContent" rows="4" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>医用计划:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="nextPlan" rows="4" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>遗留问题:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="unfinished" rows="4" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>未完成的原因:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="reason" rows="4" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>		
				</tr>  
				<tr>
					<td class="width-15 active text-right">	
		             	 <label>附件上传:</label>
		            </td>
					<td class="width-35" colspan="3">
						<input id="relateTable" type="hidden" value="oa_work_log"/>
						<input id="relateFeild" type="hidden" value="logAttach"/>						
						<form:fileinput buttonText="选择文件" saveType="billId" 
						fileActionSettings="{showRemove: true,showUpload: true}" isFormSubmit="true" 
						uploadExtraFieldData="relateTable,relateFeild"
						showUpload="false" showRemove="false" autoUpload="false" fileInputWidth="100px"  
						fileInputHeight="100px"  path="logAttach" htmlEscape="false" class="form-control" />
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>