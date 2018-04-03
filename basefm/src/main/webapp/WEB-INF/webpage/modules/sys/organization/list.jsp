<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.organization.title" /></title>
   <meta name="decorator" content="list"/>
  <script type="text/javascript" src="${staticPath }/uadmin/js/public-add.js"></script>
    <script type="text/javascript">
  function onloadFun(){
	  var winHeight = $("#page-wrapper").height()-50;
		
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
  </script>
 
</head>
<body title="<spring:message code="sys.organization.title" />">
<grid:grid id="organizationGridId" async="true" treeGrid="true" expandColumn="name" gridSetting="{loadComplete:onloadFun}"  url="${adminPath}/sys/organization/ajaxOrgTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.organization.code"  name="code"  width="30"/>
	<grid:column label="sys.organization.name"  name="name"  width="120"/>
	<grid:column label="sys.organization.type"  name="type"  dict="orggroup"   width="30"/>
	<grid:column label="sys.organization.orgType"  name="orgTypeName"    width="80"/>
	<grid:column label="sys.organization.remarks"  name="remarks"  width="60"/>
    <%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button   groupname="opt" function="delete" url="${adminPath}/sys/organization/{id}/deleteOrg"/> --%>
    
    <grid:toolbar  function="create" btnclass="btn-green" url="${adminPath}/sys/organization/createOrg"/>
	<grid:toolbar  function="update" icon="fa-file-text-o" url="${adminPath}/sys/organization/{id}/updateOrg"/>
	<grid:toolbar  function="delete" url="${adminPath}/sys/organization/batch/deleteOrg"/>
	<%-- <grid:toolbar  function="search" />
	<grid:toolbar  function="reset"/> --%>
</grid:grid>
</body>
</html>