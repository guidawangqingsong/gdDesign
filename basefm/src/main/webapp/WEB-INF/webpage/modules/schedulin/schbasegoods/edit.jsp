<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>物资基础资料</title>
<meta name="decorator" content="form" />
<html:css name="bootstrap-fileinput" />
<html:css name="simditor" />
</head>

<body class="white-bg" formid="schBaseGoodsForm" style="margin: 0 30px;">
    <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
	<form:form id="schBaseGoodsForm" modelAttribute="data" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="schmodelorganizationid" />
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>编号:</label></td>
					<td class="width-35"><form:input path="number"
							htmlEscape="false"
							ajaxurl="${adminPath}/schedulin/schbasegoods/validate"
							class="form-control" datatype="*" nullmsg="请输入编码！" /> <label
						class="Validform_checktip"></label></td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>名称:</label></td>
					<td class="width-35"><form:input path="name"
							htmlEscape="false" class="form-control" datatype="*" /> <label
						class="Validform_checktip"></label></td>

				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>计量单位:</label></td>
				    <!-- 
					<td class="width-35">
					    <form:gridselect gridId="fMeasurementUnitGridId" genField="true" title="选择单位" nested="true" path="schMeasurementUnitId" 
					             callback="setSchMeasurementUnitname"   layerWidth="1000px" layerHeight="500px" multiselect="false" formField="ddd" gridField="name"  gridUrl="${adminPath}/schedulin/fmeasurementunit/chooseUnit"   
					              labelName="schMeasurementUnitname" labelValue="${data.schMeasurementUnitName}"  />
					</td>
					 -->
					<!-- 
					<td class="width-35"><form:select path="schmeasurementunitid"
							htmlEscape="false" class="form-control" /> <label
						class="Validform_checktip"></label></td>
				     --> 
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>条形码:</label></td>
					<td class="width-35"><form:input path="barcode"
							htmlEscape="false" class="form-control" datatype="*" /> <label
						class="Validform_checktip"></label></td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>组织单元:</label></td>
					<td class="width-35"><form:treeselect title="组织单元"
							path="sysorganizationid" nested="false"
							dataUrl="${adminPath}/sys/organization/treeData" chkboxType=""
							labelName="parentname" labelValue="${sysorganizationid}"
							multiselect="true" /></td>

					<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label>备注信息:</label></td>
					<td class="width-35"><form:textarea path="remarks"
							htmlEscape="false" class="form-control"></form:textarea> <label
						class="Validform_checktip"></label></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<html:js name="bootstrap-fileinput" />
	<html:js name="simditor" />
	<script type="text/javascript"  src="${staticPath}/schedulin/js/curdtoos_form_public.js"></script>
	<script>
	  /**回写名称*/
	  function setSchMeasurementUnitname(){
		  $("#schMeasurementUnitname").val($("#ddd").val());
		  
	  }
	</script>
</body>
</html>