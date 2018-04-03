<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.task.title" /></title>
  <meta name="decorator" content="grad-select"/>
</head>
<body title="<spring:message code="activiti.task.title" />">

<body class="gray-bg">
    <img alt="流程图" src="${adminPath}/activiti/flow/showFlow?taskId=${param.taskId}">
</body>
</html>