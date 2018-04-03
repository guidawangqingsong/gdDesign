<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>计量单位</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>
<body class="white-bg"  formid="fMeasurementUnitForm">
    <form:form id="fMeasurementUnitForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请输入编码!"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请输入名称!"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>标准转换系数:</label>
		            </td>
					<td class="width-35">
						<form:input path="conversionFactor" htmlEscape="false" class="form-control" datatype="*" nullmsg="请输入标准转换系数!"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label>数量精度:</label>
		            </td>
					<td class="width-35">
						<form:input path="numSchedule" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
	<!-- 通过js控制是否显示创建人及创建时间 -->
	<!-- 
	<div style="margin-top: -18px;">
	      <div style="float: left;margin-left: 3px;">创建人：${data.name}</div>
	      <div style="float: right;margin-right: 3px;">创建时间：2017-12-22</div>
	    </div>
	 -->
	
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript"  src="${staticPath}/schedulin/js/curdtoos_form_public.js"></script>
<script>
   var createName = "${data.createBy.realname}";
   var createDate = "${data.createDate}";
</script>
</body>
</html>