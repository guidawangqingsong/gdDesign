<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>样本信息列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="costSample">
<grid:grid id="medicalSampleGridId" url="${adminPath}/ms/medicalsample/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
	
	<grid:column label="样本编号" name="id" query="true" queryMode="input"/>
	<grid:column label="挂号费" name="registerFee" query="true" queryMode="input"/>
	<grid:column label="医药费" name="medicalFee" query="true" queryMode="input"/>
	<grid:column label="治疗费" name="treatmentFee" query="true" queryMode="input"/>
	<grid:column label="住院费" name="hospitalFee" query="true" queryMode="input"/>
	<grid:column label="其他" name="other" query="true" queryMode="input"/>
	<grid:column label="人均医用（样本）" name="personalFee" query="true" queryMode="input"/>
	
	<grid:toolbar function="update"/>
	<grid:toolbar title="删除" btnclass="btn-primary" function="batchDeleteLog" url="${adminPath}/ms/medicalsample/batchDelete"/>	
	<grid:toolbar title="添加" btnclass="btn-primary" winwidth="600px" winheight="600px" icon="fa-plus" function="createPage"  url="${adminPath}/ms/medicalsample/create"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>