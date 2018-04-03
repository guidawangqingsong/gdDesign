
/**
 * @description .<br>
 * @param title 弹出框的标题(必填).<br>
 * @param url 跳转的地址(必填).<br>
 * @param gridid jqgrid的Id(必填).<br>
 * @param width 宽度.<br>
 * @param height 高度.<br>
 * @param type layer的弹出类型.<br>
 * @param callBack 回调函数.<br>
 * @param sucText 确定按钮的文字.<br>
 * @param errText 关闭按钮的文本.<br>
 * @param isShowSuc 是否显示成功按钮.<br>
 * @param isShowErr 是否显示关闭按钮.<br>
 * @param fileForm 是否是文件表单.<br>
 * @date 2017/12/13.<br>
 * @author zcg.<br>
 */
(function(){	
	var callBack;//成功回调事件	
	document.window = function(configure){
		var config = {};
		if(typeof (configure) == "object" && Object.prototype.toString.call(configure).toLowerCase() == "[object object]" && !configure.length){
			config = configure;
		}
		//初始化配置项
		if(!config.width)config.width = '800px';
		if(!config.height)config.height = '500px';
		if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
			config.width='auto';
			config.height='auto';
		}else{//如果是PC端，根据用户设置的width和height显示。
			if(config.width==='auto')config.width =document.body.clientWidth+'px';
			if(config.height==='auto')config.height =document.body.clientHeight+'px';
			if(config.width==='100%')config.width =(document.body.clientWidth*config.width)+'px';
			if(config.height==='100%')config.height =(document.body.clientHeight*config.width)+'px';
		}
		//layer弹框配置项
		if(typeof(config.isShowSuc)=='undefined'){config.isShowSuc = true};
		if(typeof(config.isShowErr)=='undefined'){config.isShowErr = true};
		if(typeof(config.fileForm)=='undefined'){config.fileForm = true};
		if(!config.type)config.type = 2;
		if(!config.sucText)config.sucText = '确定';
		if(!config.errText)config.errText = '关闭';
		
		//回调
		if(config.callBack)callBack = config.callBack;
		if(config.callBack)delete config.callBack;
		//打开弹出框
		openLayerWindow(config);
	}
	
	/**
	 * @description 描述表单提交,不带文件上传的表单提交.<br>
	 * @param config 配置信息.<br> 
	 * @param 弹出界面的Iframe.<br> 
	 */
	function openLayerWindow(config){
		var btn = [];
		if(config.isShowSuc)btn.push(config.sucText);
		if(config.isShowErr)btn.push(config.errText);
		top.layer.open({
		    type: config.type,  
		    area: [config.width, config.height],
		    title: config.title,
	        maxmin: true, //开启最大化最小化按钮
		    content: config.url ,
		    btn: btn,
		    yes: function(index, layero){
		    	if(!config.isShowSuc){
		    		if(callBack){callBack()};
		    		top.layer.close(index);
		    		return;
		    	}
		    	 var body = top.layer.getChildFrame('body', index);
		    	//得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         var iframeWin = layero.find('iframe')[0]; 
		         if(config.fileForm){
		        	 fileFormSubmit(config,iframeWin,index);
		         }else{
		        	 formSubmit(config,iframeWin,index);
		         }
			  },
			  cancel: function(index){ 
				  
			  }
		}); 
	}
	
	/**
	 * @description 描述表单提交,带文件上传的表单提交.<br>
	 * @param config 配置信息.<br> 
	 * @param 弹出界面的Iframe.<br> 
	 * @date 2017/12/13 .<br>
	 * @author zcg .<br>
	 */
	function fileFormSubmit(config,iframeWin,index){
		iframeWin.contentWindow.doSubmit(function(results){
       	//上传文件
       	var isFormSubmit = null;
       	try {
       		isFormSubmit = iframeWin.contentWindow.isFormSubmit();
	        	if(isFormSubmit)iframeWin.contentWindow.uploadFile(results.data.id);
			} catch (e) {}
			
			if(callBack){
				 callBack(results)
			}else{
				 tipInfo(results);
				 //判断逻辑并关闭
	   	         setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
	        	 //刷新表单
	   	         if(config.gridId){refreshTable(config.gridId)};
	        	 window.location.reload();
			};
       });
	}
	
	/**
	 * @description 描述表单提交,不带文件上传的表单提交.<br>
	 * @param config 配置信息.<br> 
	 * @param 弹出界面的Iframe.<br> 
	 * @date 2017/12/13 .<br>
	 * @author zcg .<br>
	 */
	function formSubmit(config,iframeWin,index){
		iframeWin.contentWindow.doSubmit(function(results){
			 if(callBack){
				 callBack(results)
			 }else{
				 tipInfo(results);
				 //判断逻辑并关闭
	   	         setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
	        	 //刷新表单
	   	         if(config.gridId){refreshTable(config.gridId)};
	        	 window.location.reload();
			 };
         });
	}
	
	/**
	 * @description 弹出提示框.<br>
	 * @param result 返回的结果.<br>
	 */
	function tipInfo(result){	
		if(result.ret==0){
			top.layer.alert(result.msg, {icon: 0, title:'提示'});
		}else{
			top.layer.alert(result.msg, {icon: 0, title:'警告'});
		}
	}
	
	/**
	 * @description 刷新界面.<br>
	 * @date 2017/12/13 .<br>
	 * @author zcg .<br>
	 */
	function refreshTab(gridId){
		search(gridId);
	}
	
	/**
	 *搜索
	 * @param gridId
	 */
	function search(gridId) {
	    var queryParams = {};
	    var queryFields=$('#queryFields').val();
	    queryParams['queryFields'] = queryFields;
	    //普通的查询
	    $('#' + gridId + "Query").find(":input").each(function() {
			var val = $(this).val();
			if (queryParams[$(this).attr('name')]) {
				val = queryParams[$(this).attr('name')] + "," + $(this).val();
			}
			queryParams[$(this).attr('name')] = val;
		});

		// 普通的查询
		$('#' + gridId + "Query").find(":input").each(function() {
			var condition = $(this).attr('condition');
			if (!condition) {
				condition = "";
			}
			var key = "query." + $(this).attr('name') + "||" + condition;
			queryParams[key] = queryParams[$(this).attr('name')];
		});
	    //刷新
	    //传入查询条件参数  
	    $("#"+gridId).jqGrid('setGridParam',{  
	        datatype:'json',  
	        postData:queryParams, //发送数据  
	        page:1  
	    }).trigger("reloadGrid"); //重新载入    
	}
	
	if(!$.window){
		$.window = document.window;
	}
	if(!document.window){
		document.window = $.window;
	}
})();