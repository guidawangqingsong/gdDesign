<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>综合分析列表</title>
  <meta name="decorator" content="list"/>
  <link type="text/css" rel="stylesheet" href="${staticPath}/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
  <html:css  name="iCheck,Validform,jquery-ztree,easy-ui,favicon,font-awesome,animate,pace"/>
  <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/datachart.css">
  <!-- costom css  -->
  
  <html:js  name="iCheck,Validform,jquery-ztree,easy-ui,bootstrap,public-js,bootstrap-hover-dropdown,echarts"/>
  <style type="text/css">.row{margin:0;}</style>
</head>

<body title="综合分析">
<div class="easyui-layout" fit="true" id="cc" style="width:100%;">
	<%-- 左布局 --%>
    <div data-options="region:'west',split:true" style="width:20%;">
		 <div class="zTreeDemoBackground left">
			<ul id="treeObj" class="ztree"></ul>
		</div>
	</div>
	<%-- 中心布局 --%>
	<div data-options="region:'center'">
		 <grid:grid id="medicalGeneralChartGridId" url="${adminPath}/medicalgeneralchart/medicalgeneralchart/ajaxList">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
			<grid:button groupname="opt" function="delete" />
			
			<grid:column label="医护编号" name="staffId"/> <!-- 自动获取员工编号,不能手工添加 -->
			<grid:column label="创建人"  name="createByName" query="true" queryMode="input"/>
			<grid:column label="创建时间"  name="createDate" query="true" queryMode="date" condition="between"/>
			<grid:column label="好评数量"  name="highOpinon"/>
			<grid:column label="差评数量"  name="lowOpinon"/>
			
			<grid:toolbar title="删除" btnclass="btn-primary" function="batchDeleteInfo" url="${adminPath}/medicalgeneralchart/medicalgeneralchart/batch/delete" icon="fa-trash"/>	
			<grid:toolbar title="查看" function="detail"  url="${adminPath}/medicalgeneralchart/medicalgeneralchart/{id}/update"  btnclass="btn btn-sm btn-success" 
			winwidth="600px" winheight="400px" icon="fa-search"/>
			<grid:toolbar title="添加" btnclass="btn-primary" winwidth="800px" winheight="600px" icon="fa-plus" function="createPage"  url="${adminPath}/medicalgeneralchart/medicalgeneralchart/create"/>
			
			<grid:toolbar title="搜索" function="dataSearch" btnclass="btn-info" layout="right" icon="fa fa-search" />
			<grid:toolbar function="reset"/>
		</grid:grid>
	</div>
	<%-- 底部布局 --%>
	<div data-options="region:'south',split:true" style="width:100%;height:90%">
		<div class="absolute left">
			<ul class="tabs-t">
				<li class="active"><div id="echarts-title">好评百分比</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<div id="echarts1" style="width:87%;height:300px;"></div>
				</li>
			</ul>
		</div>
		<div class="absolute right">
			<ul class="tabs-t">
				<li class="active"><div id="echarts2-title">预测高分百分比</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<div id="echarts2" style="width:87%;height:300px;left:-8%"></div>
				</li>
			</ul>
		</div>
		<div class="absolute south">
			<ul class="tabs-t">
				<li class="active"><div id="echarts3-title">综合气泡分布图</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<div id="echarts3" style="width:100%;height:300px;"></div>
				</li>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
    var orgId ='';
	var treeObj;
	var setting = {
			async: {
				enable: true,
				url:"${adminPath}/sys/organization/orgTree",
				autoParam:["id=nodeid"],
				dataFilter: filter
			},
			callback: {
				onClick: onClick
			}
		};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	//单击组织树触发事件，并获取orgId的值
	function onClick(event, treeId, treeNode, clickFlag) {
		 var gridId = 'medicalGeneralChartGridIdGrid';
		 orgId = treeNode.id; 		 //获取组织的节点id，是变量生命期短很安全。
		 $("input[name='orgId']").val(treeNode.id);   // 选择属性name=orgId的input元素进行赋值操作
		 
 		 $("#"+gridId).jqGrid('setGridParam',{  
 		        datatype:'json',  
		        postData:{"orgId":orgId}, //发送数据  
 		        page:1  
 		  }).trigger("reloadGrid"); //重新载入   
 		 search('medicalGeneralChartGridIdGrid');
		 reset('medicalGeneralChartGridIdGrid');
	}
	
	$(document).ready(function(){
		//初始化树数据
		treeObj=$.fn.zTree.init($("#treeObj"), setting);
	});
	
	function onloadFun(){
		var winHeight = $("#page-wrapper").height()-100;
		try{
			$(".portlet-body,.portlet-body>div").css("height",winHeight+"px");
			$(".ui-jqgrid-view").css("height",winHeight-150+"px")
		}catch(e){}
	}
	
	/**
	 * 新增事件打开窗口(是否包含组织id，有的话组织id为：orgId)
	 * 需要声明全局变量 orgId
	 * @param title 编辑框标题
	 * @param addurl//目标页面地址
	 */
	function createPage(title,url,gridid,width,height) { 
		if(!orgId){//新增时需要选择组织id
			top.layer.alert('请选择左边组织!', {icon: 0, title:'警告'});
			return;
		}else{
			url += "?orgId="+orgId;//路径传参
		}
		openDialog1(title,url,gridid,width,height);
	}
	
	/**
	 *搜索(数据搜索查询,过滤后台组装查询条件，将由查询人员自动组织查询条件，目前该方法只限于“orgId”字段)
	 * @param gridId 列表id
	 * 注：调用此方法；所有查询条件都需自己在后台设置
	 */
	function dataSearch() {
		var gridId = 'medicalGeneralChartGridIdGrid';
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
		//传入查询条件参数  
		$("#"+gridId).jqGrid('setGridParam',{  
			datatype:'json',  
			postData:queryParams, //发送数据  
			page:1  
		}).trigger("reloadGrid"); //重新载入    
	}
	
	//打开对话框(添加或修改的弹框)
	function openDialog1(title,url,gridId,width,height){
			if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
				width='auto';
				height='auto';
			}else{//如果是PC端，根据用户设置的width和height显示。
		}
		top.layer.open({
			type: 2,  
			area: [width, height],
			title: title,
			maxmin: true, //开启最大化最小化按钮
			content: url ,
			btn: ['确定', '关闭'],
			yes: function(index, layero){
				var body = top.layer.getChildFrame('body', index);
				var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				//文档地址
				//http://www.layui.com/doc/modules/layer.html#use	         
				iframeWin.contentWindow.doSubmit(function(results){ 
					tipInfo(results);
					//判断逻辑并关闭
					setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
					//刷新表单
					refreshTable(gridId); 
				});
			},
			cancel: function(index){}
		}); 	 
	} 
	
	/**方法操作成功后刷新表单*/
	function refreshTable(gridId){
		dataSearch(gridId);
	}
	
	
	/**
	 * 多记录刪除請求
	 * @param title
	 * @param url
	 * @param gname
	 * @return
	 */
	function batchDeleteInfo(title,url,gridId) {
		var ids = [];
		var rows =$("#"+gridId).jqGrid('getGridParam','selarrrow');
		var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
		var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
		if(!multiselect)
		{
			if(rowData)
			{
				rows[0]=rowData;
			}
		}
	    if (rows.length > 0) {
	    	  layer.confirm("您确定要删除这些信息么?请谨慎操作！", {
	   		  title:title+"提示",
	   		  icon:3,
	   		  btn: ['确定','取消'] //按钮
	   		}, function(){
	   		   //确定
	   			for ( var i = 0; i < rows.length; i++) {
	           		ids.push(rows[i]);
	   			}
	   			$.ajax({
	   				url : url,
	   				type : 'get',
	   				data : {
	   					ids : ids.join(',')
	   				},
	   				cache : false,
	   				success : function(d) {
	   					if (d.ret==0) {
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon:1});
	   					    //刷新表单
	   			            refreshTable1(gridId);
	   					}else{
	   						var msg = d.msg;
	   					    layer.msg(msg, {icon: 3,shade:0.3});
	   					}
	   				}
	   			});
	   		}, function(){
	   		  //取消
	   		});
			return;
		}else{
		    top.layer.alert('请选择需要删除的数据!', {icon: 0, title:'警告'});
		    return;
		}
	}
</script>

<!--图表js  -->
<script type="text/javascript">
	var myChart1 = echarts.init(document.getElementById('echarts1'));
	var myChart2 = echarts.init(document.getElementById('echarts2'));
	var myChart3 = echarts.init(document.getElementById('echarts3'));
	var _pieData1={ld:[],vd:[]};
	var _pieData2={ld:[],vd:[]};
	var _bubbleData1={vd:[],xd:[]};
	var _bubbleData2={vd:[],xd:[]};
	function Echarts1(data){
		 var option = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    //颜色标记
				    color:['#A4D3EE','#4A708B','#C67171','#C5C1AA','#4876FF'],
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:data.ld
				    },
		            itemStyle: {  
		                narmal: {  
		                    labelLine:{length:20}
		                }  
		            } ,
				    series: [
				        {
				            name:'占比',
				            type:'pie',
				            radius: ['40%', '70%'],
				            avoidLabelOverlap: false,
				            //悬浮提示框
				            data:data.vd
				        }
				    ]
				};

	        // 使用刚指定的配置项和数据显示图表。
	        myChart1.setOption(option);
	        window.onresize = myChart1.resize; 
	}
	function Echarts2(data){
		 var option = {
				 tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    //颜色标记
				    color:['#A4D3EE','#4A708B','#C67171','#C5C1AA','#4876FF'],
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:data.ld
				    },
		            itemStyle: {  
		                narmal: {  
		                    labelLine:{length:20}
		                }  
		            } ,
				    series: [
				        {
				            name:'占比',
				            type:'pie',
				            radius: ['40%', '70%'],
				            avoidLabelOverlap: false,
				            //悬浮提示框
				            data:data.vd
				        }
				    ]
				};

	        // 使用刚指定的配置项和数据显示图表。
	        myChart2.setOption(option);
	        window.onresize = myChart2.resize; 
	}
	
	function Echarts3(data1,data2){
		//console.info("data1.vd=="+JSON.stringify(data1)+"\n  data2.vd="+JSON.stringify(data2))
		var option = {
			    backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
			        offset: 0,
			        color: '#f7f8fa'
			    }, {
			        offset: 1,
			        color: '#cdd0d5'
			    }]),
			    title: {
			        text: '综合分布统计'
			    },
			    legend: {
			        center:0,
			        data: ['系统设置好评', '预测高分']
			    },
			    xAxis:[
			           {
			               type: 'category',
			               position: 'top',
			               data:data1.xd,
			               axisLine: {
			                   lineStyle: {
			                       color: 'red'
			                   }
			               }
			           },
			           {
			               type: 'category',
			               position: 'bottom',
			               data:data2.xd,
			               axisLine: {
			                   lineStyle: {
			                       color: 'rgb(50, 227, 238)'
			                   }
			               }
			           }
			   ],
			    yAxis: {
			    	type:'value',
			        splitLine: {
			            lineStyle: {
			                type: 'dashed'
			            }
			        },
			        scale: true
			    },
			    series: [{
			        name: '系统设置好评',
			        data: data1.vd,
			        type: 'scatter',
			        label: {
			            emphasis: {
			                show: true,
			                position: 'top'
			            }
			        },
			        itemStyle: {
			            normal: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(120, 36, 50, 0.5)',
			                shadowOffsetY: 10,
			                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(251, 118, 123)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(204, 46, 72)'
			                }])
			            }
			        }
			    }, {
			        name: '预测高分',
			        data: data2.vd,
			        type: 'scatter',
			        label: {
			            emphasis: {
			                show: true,
			                formatter: function (param) {
			                    return param.data2.vd;
			                },
			                position: 'top'
			            }
			        },
			        itemStyle: {
			            normal: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(25, 100, 150, 0.5)',
			                shadowOffsetY: 10,
			                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(129, 227, 238)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(25, 183, 207)'
			                }])
			            }
			        }
			    }]
			};
		 // 使用刚指定的配置项和数据显示图表。
        myChart3.setOption(option);
        window.onresize = myChart3.resize; 
	}
		function init() {
			var winW = $(".page-content").width();
			$(".right").css({'width': winW - 415 + "px"});
			$(".page-content").height($(window).height()-50);
			var pageH = $(".page-content").height();
			$(".bottom").css({'width': winW + "px"});
			$("#echarts2").css('height',pageH-530+"px");
		}
		function initEchartsData(){
			myChart1.showLoading();
			myChart2.showLoading();
			myChart3.showLoading();
			$.ajax({
				type:"POST",
				url:"${adminPath}/medicalgeneralchart/medicalgeneralchart/findGeneralCharts",
				dataType:"json",
				success:function(data){
					var hgradeList = data.extend.hgradeList;
					//var lgradearList = data.extend.lgradearList;
					var hpredictList = data.extend.hpredictList;
					//var lpredictList = data.extend.lpredictList;
					$("#echarts-title").html("好评百分比")
					$("#echarts2-title").html("预测高分百分比")
					
					var d1=[];
					var d2=[];
					
					//饼图设置
					$.each(hgradeList,function(i,item){
						_pieData1.ld.push(item.name);
						temp = {value:item.ACount,name:item.name};
						_pieData1.vd.push(temp);
						_bubbleData1.xd.push(item.name==undefined?'-':item.name);
						d1.push(item.ACount);
					});
					$.each(hpredictList,function(i,item){
						_pieData2.ld.push(item.name);
						temp = {value:item.hpCount,name:item.name};
						_pieData2.vd.push(temp);
						_bubbleData2.xd.push(item.name==undefined?'-':item.name);
						d2.push(item.hpCount);
					});
					
					//气泡图设置
					_bubbleData1.vd=d1;
					_bubbleData2.vd=d2;
				},
				complete:function(){
					myChart1.hideLoading();
					Echarts1(_pieData1);
					myChart2.hideLoading();
					Echarts2(_pieData2);
					myChart3.hideLoading();
					Echarts3(_bubbleData1,_bubbleData2);
				}
			});
		}
		
		$(function() {
			initEchartsData();
			init();
			$(".right .tabs-t>li").click(function(){
				$(".right .tabs-c>li").eq($(this).index()).addClass("active").siblings("li").removeClass("active");
				$(this).addClass("active").siblings("li").removeClass("active");
			});
			$(".left>.tip>.am-u-sm-4").click(function(){
				var url = $(this).attr("data-href");
				if(url) location.href=url;
			});
		});
		$(window).resize(init);
		
	</script>

</body>
</html>