<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>设备档案</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="schEquipmentFilesForm">
    <input type="hidden" name="ddd" id="ddd" readonly="readonly" >
    <input type="hidden" name="EquipmentName" id="EquipmentName" readonly="readonly">
    <form:form id="schEquipmentFilesForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
				   <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>编码:</label>
		            </td>
					<td class="width-35" >
						<form:input path="code" htmlEscape="false" datatype="*" class="form-control"
						            ajaxurl="${adminPath}/schedulin/schequipmentfiles/validate" />
						<label class="Validform_checktip"></label>
					</td>	
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备分类:</label>
		            </td>
					<td class="width-35">
					    <form:select
							path="equipmentType" htmlEscape="false" class="form-control"
							dict="equipmentType" datatype="*" nullmsg="请选择设备分类!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="equipmentType" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备名称:</label>
		            </td>
					<td class="width-35">
					    <form:gridselect gridId="schEquipmentBaseDataGridId" genField="true" title="选择设备" nested="true" path="schEquipmentBaseDataId" 
					             callback="setEquipmentName"   layerWidth="1200px" layerHeight="800px" multiselect="false" 
					             formField="EquipmentName,schEquipmentBaseDataCode,equipmentSpecification,equipmentModel,schMeasurementUnitName" gridField="name,code,specification,model,schMeasurementUnitName"  
					             gridUrl="${adminPath}/schedulin/schequipmentbasedata/chooseEquipment"   
					             labelName="schEquipmentBaseDataName" labelValue="${data.schMeasurementUnitName}" datatype="*" nullmsg="请选择设备!"  />
					    <!-- 
						<form:input path="schEquipmentBaseDataId" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
					 <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="schEquipmentBaseDataCode" htmlEscape="false" class="form-control"  placeholder="选择设备时自动带入"  readonly="true"  />
					</td>
				</tr>
				<tr>
				     <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>规格:</label>
		            </td>
					<td class="width-35">
						<form:input path="equipmentSpecification" htmlEscape="false" class="form-control"   readonly="true" placeholder="选择设备时自动带入"  />
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>型号:</label>
		            </td>
					<td class="width-35">
						<form:input path="equipmentModel" htmlEscape="false" class="form-control"  readonly="true" placeholder="选择设备时自动带入"   />
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>计量单位:</label>
		            </td>
					<td class="width-35">
					     <!--
					    <form:gridselect gridId="fMeasurementUnitGridId" genField="true" title="选择单位" nested="true" path="schMeasurementUnitId" 
					             callback="setSchMeasurementUnitname"   layerWidth="1200px" layerHeight="800px" multiselect="false" formField="ddd" gridField="name"  gridUrl="${adminPath}/schedulin/fmeasurementunit/chooseUnit"   
					              labelName="schMeasurementUnitname" labelValue="${data.schMeasurementUnitName}" datatype="*" nullmsg="请选择计量单位!"  />
					       -->
						<form:input path="schMeasurementUnitName" htmlEscape="false" class="form-control" readonly="true"   placeholder="选择设备时自动带入"  /> 
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>品牌名:</label>
		            </td>
					<td class="width-35">
						<form:input path="brandName" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>生产厂家:</label>
		            </td>
					<td class="width-35">
						<form:input path="produceFactory" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>出厂日期:</label>
		            </td>
					<td class="width-35">
					    <form:input path="factoryDate" readonly="true" datefmt="yyyy-MM-dd" cssStyle="width:100%"
							htmlEscape="false" class="form-control layer-date"
							placeholder="YYYY-MM-DD"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
						    nullmsg="请选择开始招标日期" />
					    <!--  
						<form:input path="factoryDate" htmlEscape="false" class="form-control"      />-->
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>出厂编码:</label>
		            </td>
					<td class="width-35">
						<form:input path="factoryCode" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>车架号:</label>
		            </td>
					<td class="width-35">
						<form:input path="frameNumber" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>发动机号:</label>
		            </td>
					<td class="width-35">
						<form:input path="engineNumber" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>车牌号:</label>
		            </td>
					<td class="width-35">
						<form:input path="licenseNumber" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>管理单位:</label>
		            </td>
					<td class="width-35">
					     <form:treeselect title="请选择组织机构" path="managementUnitId" nested="true" datatype="*" nullmsg="请选择组织机构!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					          labelName="parentname" labelValue="${data.managementUnitName}" />
					    <!-- 
						<form:input path="managementUnitId" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>使用单位:</label>
		            </td>
					<td class="width-35">
					     <form:treeselect title="请选择组织机构" path="userUnitId" nested="true" datatype="*" nullmsg="请选择组织机构!"
					          dataUrl="${adminPath}/sys/organization/treeData"  chkboxType="" 
					          labelName="userUnitName" labelValue="${data.userUnitName}" />
					    <!-- 
						<form:input path="managementUnitId" htmlEscape="false" class="form-control"      />
						 -->
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				     <td  class="width-15 active text-right">	
		              <label>购买时间:</label>
		            </td>
					<td class="width-35">
					    <form:input path="buyDate" readonly="true" datefmt="yyyy-MM-dd"
							htmlEscape="false" class="form-control layer-date"
							placeholder="YYYY-MM-DD"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
						    nullmsg="请选择开始招标日期" />
					   <!-- 
						<form:input path="buyDate" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>使用状态:</label>
		            </td>
					<td class="width-35">
					    <form:select
							path="userStatus" htmlEscape="false" class="form-control"
							dict="userStatus" datatype="*" nullmsg="请选择使用状态!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="userStatus" htmlEscape="false" class="form-control"      /> -->
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>存放地点:</label>
		            </td>
					<td class="width-35" >
						<form:input path="storeAddres" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>	
					
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>设备来源:</label>
		            </td>
					<td class="width-35">
					    <form:select
							path="equipmentSource" htmlEscape="false" class="form-control"
							dict="equipmentSource" datatype="*" nullmsg="请选择设备分类!"
							cssClass="i-checks required" cssStyle="width:100%"/> 
					    <!-- 
						<form:input path="equipmentSource" htmlEscape="false" class="form-control"      /> -->
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
  /**回写名称*/
  function setEquipmentName(){
	  $("#schEquipmentBaseDataName").val($("#EquipmentName").val());  
  }
  
</script>
</body>
</html>