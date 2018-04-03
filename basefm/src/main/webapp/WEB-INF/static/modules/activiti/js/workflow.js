
/**
 * @discription 提交流程.<br>
 * @param billId 单据Id(必填).<br>
 * @param statusFeild 状态字段默认可以不填（默认为status）.<br>
 * @param key 启动流程的key(必填).<br>
 * @param formUrl 表单显示地址(必填).<br>
 * @param billName 单据的中文名(选填).<br>
 * @param idFeild 单据主键字段,默认可以不填（默认为Id）.<br>
 * @param tableName 表的名称（必填）,如act_ru_task,则传入actRuTask.<br>
 * @param callback 回调 .<br>
 * @param basePath 单据Id(必填).<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function submitFlow(paramInfo,basePath){
	var params = {};
	var callBack = null;
	var url = paramInfo.basePath+'/activiti/flow/submitFlow';
	if (typeof (paramInfo) == "object" && Object.prototype.toString.call(paramInfo).toLowerCase() == "[object object]" && !paramInfo.length) {
		params = paramInfo;
		if(paramInfo.callback)callBack = callback;
		if(paramInfo.callback)delete paramInfo.callback;
	}
	ajaxPost(url, params, callBack);
}


/**
 * @discription 审批任务.<br>
 * @param taskId 当前任务Id.<br>
 * @param nextAuditor 下一步审核人.<br>
 * @param policy 审核决策（对应实体类OperateBean的policy属性）.<br>
 * @param opinion 审批意见（对应实体类OperateBean的opinion属性）.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function auditeBill(basePath,taskId,nextAuditor,policy,opinion,callBack){
	var url = basePath+'/activiti/flow/completeTask';
	var params = {taskId:taskId,customAuditor:nextAuditor,policy:policy,opinion:opinion};
	ajaxPost(url, params, callBack);
}


/**
 * @discription 转办任务.<br>
 * @param taskId 当前任务Id.<br>
 * @param callBack 回调.<br> 
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function transferTask(basePath,taskId,callBack){
	var url = basePath+'/activiti/flow/transactTask?taskId='+taskId;
	$.window({
		title:'转办任务',
		url:url,
		width:'30%',
		height:'40%',
		sucText:'转办',
		callBack:callBack,
		fileForm:false
	});
}

/**
 * @discription 驳回流程.<br>
 * @param taskId 当前任务Id.<br>
 * @param activityId 驳回的节点Id.<br>
 * @param params 其他参数.<br> 
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function backProcessTask(basePath,taskId,activityId){
	var url = basePath+'/activiti/flow/backProcess';
	var params = {taskId:taskId,activityId:activityId};
	ajaxPost(url, params, null);
}

/**
 * @discription 驳回流程.<br>
 * @param taskId 当前任务Id.<br>
 * @param assignees 处理人（多人用逗号分隔）.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function joinPassTask(basePath,taskId,assignees){
	var url = basePath+'/activiti/flow/joinPass';
	var params = {taskId:taskId,assignee:assignee};
	ajaxPost(url, params, null);
}

/**
 * @discription 设置下一步审批人.<br>
 * @param nextAuditorId 下一步审批人.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function nextAuditor(basePath,nextAuditorId){
	var url = basePath+'/baseframe/admin/activiti/flow/';
	$.window({
		title:'测试',
		url:'www.baidu.com',
		gridid:'wbsMainGridIdGrid',
		width:'60%',
		height:'60%'
	});
}

/**
 * @discription 显示审批信息.<br>
 * @param taskId 当前任务Id.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function showAuditeInfo(basePath,taskId){
	var url = basePath+'/activiti/flow/histaskComment?taskId='+taskId;
	$.window({
		title:'审批信息',
		url:url,
		gridid:'wbsMainGridIdGrid',
		width:'90%',
		height:'90%',
	});
}

/**
 * @discription 显示单据信息.<br>
 * @param url 单据访问地址.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br> 
 */
function showBillInfo(url){
	$.window({
		title:'单据信息',
		url:url,
		width:'60%',
		height:'60%',
		isShowSuc:false,
		isShowErr:false
	});
}

/**
 * @discription 显示流程图.<br>
 * @param taskId 当前任务Id.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function showflow(basePath,taskId){
	var url = basePath+'/activiti/flow/showFlow?taskId='+taskId;
	$.window({
		title:'流程图',
		url:url,
		width:'60%',
		height:'60%',
		isShowSuc:false,
		isShowErr:false
	});
}

/**
 * @discription 显示审批页面.<br>
 * @param basePath 基础路径.<br>
 * @param taskId 当前任务Id.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function showAudite(basePath,taskId){
	var url = basePath+'/activiti/flow/flowAudit?taskId='+taskId;
	$.window({
		title:'审批',
		url:url,
		gridId:'',
		width:'95%',
		height:'95%',
		isShowSuc:false,
		isShowErr:false
	});
}


/**
 * @discription 通过ajax的post提交方式，处理相关数据.<br>
 * @param url 请求的地址.<br>
 * @param params 参数集合.<br>
 * @param callBack 回调函数.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function ajaxPost(url,params,callBack){
	if(!params)params = {};
	$.ajax({
		type:"post",
		url:url,
		data:params,
		dataType:'json',
		success:function(data){
			if(callBack){
				//处理成功后
				callBack(data);
			}else{
				tip(data);
			}
			
		}
	});
}

/**
 * @discription 提示.<br>
 * @param result 返回的结果.<br>
 * @author zcg .<br>
 * @date 2017/12/12 .<br>
 */
function tip(result){
	if(result.ret==0){
	     top.layer.alert(result.msg, {icon: 0, title:'提示'});
    }else{
		 top.layer.alert(result.msg, {icon: 0, title:'警告'});
	 }
}
