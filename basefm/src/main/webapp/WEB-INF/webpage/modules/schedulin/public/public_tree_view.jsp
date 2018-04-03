<!-- 
  discript:组织树显示及操作公共代码
 -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>



<link rel="stylesheet" type="text/css"
	href="${staticPath}/schedulin/css/metroStyle/metroStyle.css">
<script type="text/javascript"
	src="${staticPath}/vendors/jquery/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${staticPath}/vendors/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${staticPath}/vendors/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>

<div>
	<div style="margin-top: 0px;margin-bottom: 8px;">
		<button class="btn btn-sm btn-primary" style="padding: 5px 6px"
			onclick="createOrg('添加','/baseframe/admin/schedulin/fmodelorganization/create?category=${param.category}','fModelOrganizationGridId','600px','400px')">
			<i class="fa fa-plus"></i>添加
		</button>
		<button class="btn btn-sm btn-success" style="padding: 5px 6px"
			onclick="updateOrg('修改','/baseframe/admin/schedulin/fmodelorganization/{id}/update?category=${param.category}','fModelOrganizationGridId','600px','400px')">
			<i class="fa fa-file-text-o"></i> 修改
		</button>
		<button class="btn btn-sm btn-warning" style="padding: 5px 6px"
			onclick="deleteOrg('删除','/baseframe/admin/schedulin/fmodelorganization/{id}/deletestate','fModelOrganizationGridId')">
			<i class="fa fa-trash-o"></i> 删除
		</button>
		<button class="btn btn-sm btn-success" style="padding: 5px 6px"
			onclick="ExeclExp('/baseframe/admin/schedulin/fmodelorganization/excelModelExport?category=${param.category}','fModelOrganizationGridId','orgId')">
			<i class="fa fa-download"></i>模板下载
		</button>
	</div>
	<%-- <view:treeview id="organizationTreeview"
		dataUrl="${adminPath}/schedulin/fmodelorganization/bootstrapTreeData?category=${param.category}"
		onNodeSelected="organizationOnclick"></view:treeview> --%>

	<ul id="treeDemo" class="ztree"
		style="margin-left: 0; width: 98%;background-color:	#FDFBFB; position: absolute;overflow:auto"></ul>

	<script type="text/javascript">
		var orgid="";
		var orgId="";
		var zTreeObj;
		var category = "${param.category}";
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
					}
				}
			}; 
			init();
			$.fn.zTree.init($("#treeDemo"), setting);
			//显示区域树  
			$
					.ajax({
						type : "POST",
						url : "${adminPath}/schedulin/fmodelorganization/basetreeData",
						data : {category:"${param.category}"},
						dataType : "json",
						success : function(data) {
							zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,
									data.data);
							try {
								var node = zTreeObj.getNodes()[0];
								zTreeObj.selectNode(node);
								setting.callback.onClick(null,
										zTreeObj.setting.treeId, node);
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
					$
							.ajax({
								type : "POST",
								async : false,
								url : "${adminPath}/schedulin/fmodelorganization/basetreeData",
								data : {
									pid : treeNode.id,
									category:"${param.category}"
								},
								dataType : "json",
								success : function(data) {
									if (data.data != null && data.data != "") {
										//添加新节点  
										newNode = treeObj.addNodes(node,
												data.data);
									}
								},
								error : function(event, XMLHttpRequest,
										ajaxOptions, thrownError) {
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
		
		function init(){
			var _height=$("body").height()-160;//获取当前窗口的高度 
			$('#treeDemo').css('height',_height+'px');//调整列表的宽高
		}
		
		/**
		 * 新增事件打开窗口
		 * @param title 编辑框标题
		 * @param addurl//目标页面地址
		 */
		function createOrg(title, url, gridid, width, height) {
			openDialogOrg(title, url, gridid, width, height);
		}
		/**
		 * 修改组织树
		 * @param title 编辑框标题
		 * @param addurl//目标页面地址
		 * @param id//主键字段
		 */
		function updateOrg(title, url, gridId, width, height) {
			if (!orgId) {//未选择组织机构，无法获取组织机构id
				top.layer.alert('请选择要修改的组织机构!', {
					icon : 0,
					title : '警告'
				});
				return;
			}
			url = url.replace("{id}", orgId); 
			openDialogOrg(title, url, gridId, width, height);

		}

		//打开对话框(添加修改)
		function openDialogOrg(title, url, gridId, width, height) {
			width = width ? width : '800px';
			height = height ? height : '500px';
			if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，就使用自适应大小弹窗
				width = 'auto';
				height = 'auto';
			} else {//如果是PC端，根据用户设置的width和height显示。

			}
			top.layer.open({
				type : 2,
				area : [ width, height ],
				title : title,
				maxmin : true, //开启最大化最小化按钮
				content : url,
				btn : [ '确定', '关闭' ],
				yes : function(index, layero) {
					var body = top.layer.getChildFrame('body', index);
					var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					//文档地址
					//http://www.layui.com/doc/modules/layer.html#use
					iframeWin.contentWindow.doSubmit(function() {
						//判断逻辑并关闭
						setTimeout(function() {
							top.layer.close(index)
						}, 100);//延时0.1秒，对应360 7.1版本bug
						//修改成功之后刷新当前页面
						window.location.reload();
					});

				},
				cancel : function(index) {

				}
			});

		}

		/**
		 * 单条记录删除
		 * @param title
		 * @param url
		 * @param gname
		 * @return
		 */
		function deleteOrg(title, url, infoid, gridId, tipMsg) {
			url = url.replace("{id}", orgId);
			if (tipMsg == undefined || tipMsg == '') {
				msg = "您确定要删除该组织吗，请谨慎操作！";
			}
			swal({
				title : "提示",
				text : msg,
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确定",
				closeOnConfirm : false,
				cancelButtonText : "取消",
			}, function() {
				$.ajax({
					url : url,
					type : 'post',
					data : {
						id : infoid
					},
					cache : false,
					success : function(d) {
						if (d.ret == 0) {
							var msg = d.msg;
							swal("提示！", msg, "success");
							//修改成功之后刷新当前页面
							window.location.reload();
						} else {
							var msg = d.msg;
							swal("提示！", msg, "error");
						}
					}
				});
			});
		}
	</script>
</div>

