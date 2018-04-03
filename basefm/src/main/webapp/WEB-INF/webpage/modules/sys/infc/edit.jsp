<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>接口列表</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="infcForm">
    <form:form id="infcForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>接口编号:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写接口编号"
							ajaxurl=""   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>接口名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>请求类型:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="reqType" htmlEscape="false" class="form-control"  dict="reqType" 
							defaultValue="0" datatype="*" delimiter="&nbsp;&nbsp;" cssClass="i-checks required"/>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>操作类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="operType" htmlEscape="false" class="form-control"  dict="operType"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>使用设备:</label>
		            </td>
					<td class="width-35">
						<form:checkboxes path="useEqp" delimiter="&nbsp;&nbsp;" htmlEscape="false" 
							class="form-control"  dict="useEqp"  datatype="*" cssClass="i-checks required"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>接口类型:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="supportType" htmlEscape="false" class="form-control"  dict="supportType" onchange="changeSupport(this.value)"
							defaultValue="1" datatype="*" delimiter="&nbsp;&nbsp;" cssClass="i-checks required"/>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr id="interfaceUrlTr">
					<td  class="width-15 active text-right">	
		              <label>接口地址:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="interfaceUrl" htmlEscape="false" class="form-control" datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
				
				</tr>
				<tr id="definedSqlTr" hidden="hidden">
					<td  class="width-15 active text-right">	
		              <label>定义sql:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="definedSql" htmlEscape="false" class="form-control"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>请求body:</label>
		            </td>
					<td class="width-35"  colspan="3">
						<form:textarea path="reqBody" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>成功时返回消息:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="sucMsg" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>失败时返回消息:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="failMsg" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>备注:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="remark" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript">

	/**
	 * @param description .<br>
	 * @author zcg .<br>
	 * @date 2017/1/19 .<br>
	 */
	function changeSupport(value){
		if(value==1){
			$('#interfaceUrl').attr("ignore",false);
			$('#definedSql').attr("ignore",true);
			$('#interfaceUrl').attr("datatype","*");
			$('#definedSqlTr').removeAttr("datatype","*");
			$('#definedSqlTr').hide();
			$('#interfaceUrlTr').show();
		}else{
			$('#interfaceUrl').attr("ignore",true);
			$('#definedSql').attr("ignore",false);
			$('#definedSqlTr').attr("datatype","*");
			$('#interfaceUrl').removeAttr("datatype","*");
			$('#definedSqlTr').show();
			$('#interfaceUrlTr').hide();
		}
	}
</script>
</body>
</html>