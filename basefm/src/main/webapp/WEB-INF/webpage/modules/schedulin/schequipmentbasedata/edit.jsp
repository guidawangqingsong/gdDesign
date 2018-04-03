<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>设备基础资料</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schEquipmentBaseDataForm">
     <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <form:form id="schEquipmentBaseDataForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>	
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control" datatype="*" nullmsg="请填写编码!" 
						   ajaxurl="${adminPath}/schedulin/schequipmentbasedata/validate"    />
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写名称!"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>规格:</label>
		            </td>
					<td class="width-35">
						<form:input path="specification" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写规格!"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>型号:</label>
		            </td>
					<td class="width-35">
						<form:input path="model" htmlEscape="false" class="form-control"  nullmsg="请填写型号!"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>	
					<td  class="width-15 active text-right">	
		              <label>定额号:</label>
		            </td>
					<td class="width-35">
						<form:input path="topNumber" htmlEscape="false" class="form-control"  nullmsg="请填写定额号!"    />
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label>机械规格名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="mechanicalName" htmlEscape="false" class="form-control" nullmsg="请填写机械规格名称!"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备分类:</label>
		            </td>
					<td class="width-35">
					      <form:select
							path="equipmentType" htmlEscape="false" class="form-control"
							dict="equipmentType" datatype="*" nullmsg="请选择设备分类!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
						<!-- 
						<form:input path="equipmentType" htmlEscape="false" class="form-control"/> -->
						<label class="Validform_checktip"></label>
					</td>
					
					  <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>计量单位:</label>
		            </td>
					<td class="width-35">
					    <form:gridselect gridId="fMeasurementUnitGridId" genField="true" title="选择单位" nested="true" path="schMeasurementUnitId" 
					             callback="setSchMeasurementUnitname"   layerWidth="1200px" layerHeight="800px" multiselect="false" formField="ddd" gridField="name"  gridUrl="${adminPath}/schedulin/fmeasurementunit/chooseUnit"   
					              labelName="schMeasurementUnitname" labelValue="${data.schMeasurementUnitName}" datatype="*" nullmsg="请选择计量单位!"  />
					    <label class="Validform_checktip"></label>
					    <!--         
						<form:input path="schMeasurementUnitId" htmlEscape="false" class="form-control"      />
						 -->  
					</td>
				</tr>
				<tr> 
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>所属组织:</label>
		            </td>
					<td class="width-35">
					     <form:treeselect title="请选择组织机构" path="sysOrganizationId" nested="true" datatype="*" nullmsg="请选择组织机构!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					          labelName="parentname" labelValue="${data.sysOrganizationName}" />
					     <label class="Validform_checktip"></label>
					    <!-- 
						<form:input path="sysOrganizationId" htmlEscape="false" class="form-control"      />		
						 -->
					</td>
					
				</tr>
				<tr>
				   <td  class="width-15 active text-right">	
		              <label>描述:</label>
		            </td>
					<td class="width-35" colspan="3">
						<form:textarea path="remarks" htmlEscape="false" class="form-control"  cssStyle="height:100px"    />
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
  function setSchMeasurementUnitname(){
	  $("#schMeasurementUnitname").val($("#ddd").val());
  }
</script>
</body>
</html>