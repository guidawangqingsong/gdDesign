<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<link rel="stylesheet" type="text/css"
	href="${staticPath}/schedulin/css/metroStyle/metroStyle.css">
<script type="text/javascript"
	src="${staticPath}/vendors/jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${staticPath}/vendors/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${staticPath}/vendors/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>

<script type="text/javascript">
	var orgid = "";
	var orgId = "";
	var zTreeObj;
	$(function() {
		var setting = {
			check : {
				enable : false,
			},
			view : {
				selectedMulti : false,
				showIcon : true, //设置是否显示节点图标  
				showLine : true, //设置是否显示节点与节点之间的连线   
			},
			async : {
				enable : true, // 设置 zTree是否开启异步加载模式  加载全部信息  
				url : "", // Ajax 获取数据的 URL 地址    
				autoParam : [ "id" ], // 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1     
			},
			callback : {
				onClick : zTreeOnOnClick
			//点击事件  
			},
			data : { // 必须使用data    
				simpleData : {
					enable : true,
					idKey : "id", // id编号命名 默认    
					pIdKey : "pId", // 父id编号命名 默认    
					rootPId : 0
				// 用于修正根节点父节点数据，即 pIdKey 指定的属性值    
				}
			}
		};

		init();
		$.fn.zTree.init($("#treeDemo"), setting);
		//显示区域树  
		$.ajax({
			type : "POST",
			url : "${adminPath}/ztree/bindinfo/treeData",
			data : {},
			dataType : "json",
			success : function(data) {  
				zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data.data);
				try {
					var node = zTreeObj.getNodes()[0];
					zTreeObj.selectNode(node);
					setting.callback.onClick(null, zTreeObj.setting.treeId,
							node);
				} catch (e) {
				}
			}
		});

		function zTreeOnOnClick(event, treeId, treeNode) {
			orgid = treeNode.id;
			orgId = treeNode.id;
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var node = treeObj.getNodeByTId(treeNode.tId);
			//没有子节点才去查询  
			if (node.children == null || node.children == "undefined") {
				$.ajax({
					type : "POST",
					async : false,
					url : "${adminPath}/ztree/bindinfo/treeData",
					data : {
						pid : treeNode.id
					},
					dataType : "json",
					success : function(data) {
						if (data.data != null && data.data != "") {
							//添加新节点  
							newNode = treeObj.addNodes(node, data.data);
						}
					},
					error : function(event, XMLHttpRequest, ajaxOptions,
							thrownError) {
						result = true;
						alert("请求失败!");
					}
				});
			}
			try {
				searchData(treeNode.id, treeNode);
			} catch (e) {
			}
		}
	});
	function init() {
		var _height = $("body").height() - 160;//获取当前窗口的高度 
		$('#treeDemo').css('height', _height + 'px');//调整列表的宽高
	}
</script>
<ul id="treeDemo" class="ztree"	style="margin-left: 0; width: 98%; 
background-color:#FDFBFB;  position: absolute;overflow:auto">uoi
</ul>
<!-- <ul id="treeObj" class="ztree "
	style="margin-left: 0; width: 98%; background-color:	#FDFBFB;  position: absolute;overflow:auto">
</ul> -->
