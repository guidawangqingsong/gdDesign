<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>模块组织树</title>
<meta name="decorator" content="form" />

</head>

<body class="white-bg" formid="fModelOrganizationForm">
	<form:form id="fModelOrganizationForm" modelAttribute="data"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" name="category" value="${param.category}">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
					<tr>
						<td class="width-15 active text-right"><label><font
								color="red">*</font>编码:</label></td>
						<td class="width-35"><form:input path="code"
								htmlEscape="false" class="form-control" datatype="*"
								ajaxurl="${adminPath}/schedulin/fmodelorganization/validate?category=${param.category}"
								nullmsg="请输入组织编码！" /> <label class="Validform_checktip"></label>
						</td>
					</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">请选择上级:</label></td>
					<td class="width-35"><form:treeselect title="请选择上级"
							path="parentId"
							dataUrl="${adminPath}/schedulin/fmodelorganization/treeData?category=${param.category}"
							labelName="parentname" labelValue="${parentname}" /></td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>组织名称:</label></td>
					<td class="width-35"><form:input path="name"
							class="form-control " datatype="*" nullmsg="请输入组织树名称！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>