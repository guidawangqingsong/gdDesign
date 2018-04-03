<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="activiti.task.title" /></title>
  <meta name="decorator" content="grad-select"/>
  <html:css  name="iCheck,Validform,jquery-ztree,amaze-ui"/>
  <link type="text/css" rel="stylesheet" href="${staticPath}/modules/activiti/css/style.css">
  <html:js  name="iCheck,Validform,jquery-ztree,jquery"/>
</head>
<script type="text/javascript" src="${staticPath}/modules/activiti/js/workflow.js"></script>
<script type="text/javascript" src="${staticPath}/common/js/window.js"></script>

<body title="<spring:message code="activiti.task.title" />">
	<input id="nextAuditor" name="nextAuditor" type="hidden"> 
    	<div id="flow-audit">
			<div class="panel tools">
				<button type="button" onclick="audite()" class="am-btn am-btn-success"><i class="am-icon-gavel"></i>审批</button>
				<button type="button" onclick="transferTask('${adminPath}',${param.taskId},callBack)" class="am-btn am-btn-primary"><i class="am-icon-undo"></i>转办</button>	
				<button type="button" onclick="backProcessTask('${adminPath}',${param.taskId})" class="am-btn am-btn-danger"><i class="am-icon-reply-all"></i>驳回</button>
				<button type="button" onclick="joinPassTask('${adminPath}',${param.taskId})" class="am-btn am-btn-success"><i class="am-icon-edit"></i>会签</button>
				<button type="button" onclick="nextAuditor('${adminPath}',${param.taskId})" class="am-btn am-btn-primary"><i class="am-icon-share-square-o"></i>下一步参与人</button>	
				<button type="button" onclick="showAuditeInfo('${adminPath}',${param.taskId})" class="am-btn am-btn-danger"><i class="am-icon-twitch"></i>审批信息</button>
				<button type="button" onclick="showBillInfo('${formUrl}')" class="am-btn am-btn-primary"><i class="am-icon-search"></i>查看单据</button>	
				<button type="button" onclick="showflow('${adminPath}',${param.taskId})" class="am-btn am-btn-danger"><i class="am-icon-object-group"></i>流程图</button>
			</div>
			<div class="panel top">
				<iframe src="${formUrl}" width="100%" height="100%"></iframe>
			</div>
			<div class="panel f-left">
				<div class="manage">
					<div class="t">审核处理</div>
					<input type="radio" name="policy" id="policy" value="0" onclick="changeOpinion(this.value)" checked="checked"/>同意
					<input type="radio" name="policy" id="policy" value="1" onclick="changeOpinion(this.value)"/>不同意
				</div>
				<div class="idea">
					<div class="t">审核意见</div>
					<textarea id="opinion" name="opinion">审批通过</textarea>
				</div>
			</div>
			<div class="panel f-right" style="width: 100%">
				<grid:grid>
					<grid:column label="activiti.task.activiti.name"  name="name" />
				    <grid:column label="activiti.task.assignee"  name="assigneeName"/>
				</grid:grid>
				<iframe src="${adminPath}/activiti/flow/histaskComment?taskId=${param.taskId}" width="100%"></iframe>
			</div>
		</div>
		
<script type="text/javascript">

	/**
	 * @discription rediogroups点击事件，修改审批意见的内容.<br>
	 * @author zcg .<br>
	 * @date 2017/12/26 .<br>
	 */
	function changeOpinion(value){
		if(value==1){
			$('#opinion').val('打回修改');
		}else{
			$('#opinion').val('审批通过');
		}
	}

	/**
	 * @discription 审批任务.<br>
	 * @author zcg .<br>
	 * @date 2017/12/15 .<br>
	 */
	function audite(){
		var basePath = '${adminPath}';
		var taskId = '${param.taskId}';
		var nextAuditor = $('#nextAuditor').val();
		var policy = $("input[name='policy']:checked").val();
		var opinion = $('#opinion').val();
		auditeBill(basePath,taskId,nextAuditor,policy,opinion,callBack);
	}
	
	 /**
	  * @discription 操作成功之后回调.<br>
	  * @author zcg .<br>
	  * @date 2017/12/15 .<br>
	  */
	function callBack(result){
		 if(result.ret==0){
       	    top.layer.alert(result.msg, {icon: 0, title:'提示'});
       	 	//var index = parent.layer.getFrameIndex(window.name);
       	 	setTimeout(function(){top.layer.closeAll()}, 100);
       	 	parent.window.location.reload();
		 }else{
			top.layer.alert(result.msg, {icon: 0, title:'提示'});
		 }
	 }

</script>
<script type="text/javascript">
	(function(){
		function panelInit(){
			var winHeight = window.innerHeight;
			var panelTop = document.getElementsByClassName("top")[0];
			var panelLeft = document.getElementsByClassName("f-left")[0];
			var panelRight = document.getElementsByClassName("f-right")[0];
			
			panelTop.style.cssText = "height:"+(winHeight*0.7-50)+"px;top:50px";
			panelLeft.style.cssText = "height:"+winHeight*0.3+"px;top:"+winHeight*0.7+"px";
			panelRight.style.cssText = "height:"+winHeight*0.3+"px;top:"+winHeight*0.7+"px;left:30%";
			
			panelRight.getElementsByTagName("iframe")[0].style.height = winHeight*0.3+"px";
		}
		panelInit();
		window.onresize = panelInit;
	})();
</script>
</body>
</html>