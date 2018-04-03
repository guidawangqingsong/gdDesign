<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>物资基础资料</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schMaterialsBaseDataForm" style="margin: 0 30px;">
    <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <form:form id="schMaterialsBaseDataForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="code" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写编码!"
						    ajaxurl="${adminPath}/schedulin/schmaterialsbasedata/validate"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control"  datatype="*" nullmsg="请填写名称!"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				    <td  class="width-15 active text-right">	
		              <label>品牌名:</label>
		            </td>
					<td class="width-35">
						<form:input path="brandName" htmlEscape="false" class="form-control"  nullmsg="请填写品牌!"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>规格型号:</label>
		            </td>
					<td class="width-35">
						<form:input path="specification" htmlEscape="false" class="form-control"   datatype="*" nullmsg="请填写规格型号!"    />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr> 
				<tr>
				     <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>计量单位:</label>
		            </td>
					<td class="width-35">
					    <form:gridselect gridId="fMeasurementUnitGridId" genField="true" title="选择单位" nested="true" path="schMeasurementUnitId" 
					             callback="setSchMeasurementUnitname"   layerWidth="1200px" layerHeight="800px" multiselect="false" formField="ddd" gridField="name"  gridUrl="${adminPath}/schedulin/fmeasurementunit/chooseUnit"   
					              labelName="schMeasurementUnitname" labelValue="${data.schMeasurementUnitName}" datatype="*" nullmsg="请选择计量单位!"  />
					    <!--      
						<form:input path="schMeasurementUnitId" htmlEscape="false" class="form-control"      /> -->     
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>条形码:</label>
		            </td>
					<td class="width-35">
						<form:input path="barcode" htmlEscape="false" class="form-control"   nullmsg="请填写条形码!"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>所属组织:</label>
		            </td>
					<td class="width-35">
					    <form:treeselect title="请选择组织机构" path="sysOrganizationId" datatype="*" nullmsg="请选择组织机构!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					          labelName="parentname" labelValue="${data.sysOrganizationName}" />
					    <!-- 
						<form:input path="sysOrganizationId" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" /><%-- 
<script type="text/javascript"  src="${staticPath}/schedulin/js/curdtoos_form_public.js"></script> --%>
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