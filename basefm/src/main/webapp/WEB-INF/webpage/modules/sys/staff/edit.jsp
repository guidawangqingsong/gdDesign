<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.staff" /></title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
    <html:css  name="iCheck,Validform"/>
</head>

<body class="white-bg"  formid="staffForm">
    <form:form id="staffForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="userId"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
		  			<td  class="width-10 active text-right" rowspan="6">	
		              <label>头像:</label>
		            </td>
					<td class="width-20" rowspan="6">
						<form:fileinput showType="avatar" fileInputWidth="100px"  fileInputHeight="100px"  path="imageUrl" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					  <td  class="width-10 active text-right">	
		              <label><font color="red">*</font>员工编号:</label>
		            </td>
					<td class="width-20">
						<form:input path="code" htmlEscape="false" ajaxurl="${adminPath}/sys/staff/validate" class="form-control" datatype="*"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-10 active text-right">	
		              <label><font color="red">*</font>姓名:</label>
		            </td>
					<td class="width-20">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>性别:</label>
		            </td>
					<td class="width-20">
						<form:radiobuttons path="sex" class="width-35" 
						 dict="sex" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false"  defaultValue="1"
						cssClass="i-checks required" />  
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-10 active text-right">	
		              <label><!-- <font color="red">*</font> -->身份证号码:</label>
		            </td>
					<td class="width-20">
						<form:input path="idcard" htmlEscape="false" class="form-control"  ajaxurl="${adminPath}/sys/staff/validate" datatype="idcard"  ignore="ignore"  validErrorMsg="身份证号已存在"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label><!-- <font color="red">*</font> -->出生日期	:</label>
		            </td>
					<td class="width-20">
						<form:input path="birthday" htmlEscape="false" class="form-control layer-date"  onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-10 active text-right">	
		              <label><font color="red">*</font>电子邮箱:</label>
		            </td>
					<td class="width-20">
						<form:input path="email" htmlEscape="false" class="form-control"  datatype="e"    ajaxurl="${adminPath}/sys/staff/validate" validErrorMsg="电子邮箱已存在"  />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label><font color="red">*</font>手机号码:</label>
		            </td>
					<td class="width-20">
						<form:input path="moblie" htmlEscape="false" class="form-control"  datatype="m"   ajaxurl="${adminPath}/sys/staff/validate"  validErrorMsg="手机号已存在"  />
						<label class="Validform_checktip"></label>
					</td>
						<td  class="width-10 active text-right">	
		              <label>办公室电话:</label>
		            </td>
					<td class="width-20">
						<form:input path="officetel" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>民族:</label>
		            </td>
					<td class="width-20">
						<form:input path="ethnicities" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
		          	 <td  class="width-10 active text-right">	
		              <label>原工作单位:</label>
		            </td>
					<td class="width-20">
						<form:input path="preCompany" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					  <td  class="width-10 active text-right">	
		              <label>学位:</label>
		            </td>
					<td class="width-20">
					<form:select path="degree" class="width-35" 
						 dict="degree" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" 
						 cssClass="i-checks required" /> 
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-10 active text-right">	
		              <label>职称:</label>
		            </td>
					<td class="width-20">
						<form:input path="protitle" htmlEscape="false" class="form-control"      /> 
						<label class="Validform_checktip"></label>
					</td>			
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>职员状态:</label>
		            </td>
					<td class="width-20">
						<form:select path="status" class="width-35" 
						 dict="staffStatus" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" 
						cssClass="i-checks required" />  
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-10 active text-right">	
		              <label>职员类型:</label>
		            </td>
					<td class="width-20">
						<form:select path="type" class="width-35" 
						 dict="staffType" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" 
						cssClass="i-checks required" />   
						<label class="Validform_checktip"></label>
					</td>
					<td class="width-10 active"><label class="pull-right"><font color="red">*</font>所属组织:</label></td>
					<td class="width-20">
					   <form:treeselect  title="请选择默认组织机构" path="orgId"  nested="true" datatype="*" nullmsg="请选择所属组织"
					   		dataUrl="${adminPath}/sys/organization/treeData" labelName="orgName" labelValue="${data.orgName}" />	
					   <label class="Validform_checktip"></label>   
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>岗位:</label>
		            </td>
					<td class="width-20">
						<form:select path="position" class="width-35" 
						 dict="staffJob" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" 
						cssClass="i-checks required" />  
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>上级岗位:</label>
		            </td>
					<td class="width-20">
						<form:select path="parentPosition" class="width-35" 
						 dict="staffJob" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" 
						cssClass="i-checks required" /> 
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>是否负责人：</label>
		            </td>
					<td class="width-20">
						<label class="Validform_checktip"></label>
						<form:radiobuttons path="lead" class="width-35" 
						 dict="sf" delimiter="&nbsp;&nbsp;"
						 htmlEscape="false" defaultValue="0"
						cssClass="i-checks required" />   
					</td>					
				</tr>
				<tr>
					<td  class="width-10 active text-right" colspan="1">	
		              <label>通信地址:</label>
		            </td>
					<td class="width-20" colspan="7">
						<form:input path="addr" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-10 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-20" colspan="5">
						<form:textarea path="remarks" htmlEscape="false" class="form-control"      />
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
