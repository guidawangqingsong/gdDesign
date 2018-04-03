<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>设备使用记录状态</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schEquipmentUserRecordForm"> 
    <form:form id="schEquipmentUserRecordForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody> 
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>现使用状态:</label>
		            </td>
					<td class="width-35" colspan="3">
						 <form:select
							path="status" htmlEscape="false" class="form-control"
							dict="userStatus" datatype="*" nullmsg="请选择使用状态!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>  
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript"  src="${staticPath}/schedulin/js/curdtoos_form_public.js"></script>
<script>
var createName = "${data.createBy.realname}";
var createDate = "${data.createDate}";
/**回写名称*/
function schEquipmentFilesName(){
	  $("#schEquipmentFilesCode").val($("#ddd").val());  
}

$(function(){
	$("#equipmentType").attr("disabled","true");
	$("#equipmentSource").attr("disabled","true");
	$("#userStatus").attr("disabled","true");
});

</script>
</body>
</html>