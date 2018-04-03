<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>附件信息</title>
  <meta name="decorator" content="grid-select"/>
  <link rel="stylesheet" type="text/css" href="${staticPath}/vendors/terseBanner/terseBanner.css"/>
</head>
<body title="附件信息">
	<div class="main">
		<div class="banner" id="thumbnail">
            <ul>
            	<c:forEach items="${maplist}" var="item" varStatus="i">
                <li style="<c:if test="${imgIndex==i.index}">display:list-item;</c:if>"><img src="${ctx}/${item.path}"></li>
                </c:forEach>
            </ul>
            
        </div>
		</div>
		<script type="text/javascript" src="${staticPath}/vendors/jquery/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${staticPath}/vendors/terseBanner/jquery.terseBanner.min.js" ></script>
		<script type="text/javascript">
		var timer = null;
			$(function(){
				timer = setInterval("init()",500);
			});
			function init(){
				var pageHeight = $(".main .banner").height();
				$(".main .banner").css({
					'width':window.innerWidth +"px",
					'height':window.innerHeight +"px"
				});
				$('#thumbnail').terseBanner({
				    btn: false,
				    thumb: {
				        width: 150,
				        height: 84,
				        gap: 4,
				        visible: 5
				    }
				});
				if(pageHeight == window.innerHeight){
					clearInterval(timer);
				}
			}
		</script>
</body>
</html>