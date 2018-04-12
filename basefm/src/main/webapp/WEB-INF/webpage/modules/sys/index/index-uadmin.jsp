<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en" style="overflow: hidden;">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    
    <title><spring:message code="sys.site.title" arguments="${platformName}"/></title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
    <meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">
     
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
    <html:css  name="favicon,bootstrap,font-awesome,animate,pace,Validform"/>

    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/style-responsive.css"> 
    <!-- costom css  -->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/index.css"> 
  
</head>

<body class="sidebar-default " >
    <div>
        <!--BEGIN BACK TO TOP--><a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <%@include file="../../../decorators/uadmin/topbar.jsp"%>
        <div id="wrapper">
            <%@include file="../../../decorators/uadmin/left.jsp"%>
            <!--BEGIN PAGE WRAPPER-->
            <div id="page-wrapper">
                <!--BEGIN CONTENT-->
                <div class="page-content" style="overflow: auto;">
                     <%@include file="../../../decorators/uadmin/main.jsp"%>
			    </div>
			    <!--END CONTENT-->
    </div>
    <!--BEGIN FOOTER-->
   <%--  <div id="footer">
        <div class="copyright"><spring:message code="sys.site.bottom.copyright" /></div>
    </div> --%>
    <!--END FOOTER-->
    <!--END PAGE WRAPPER-->
    </div>
    </div>
    <html:js  name="jquery,bootstrap,metisMenu,slimscroll,layer,pace,bootstrap-hover-dropdown,jquery-cookie,Validform,echarts"/>
    <script src="${staticPath}/uadmin/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery-ui.js"></script>
    <!--loading bootstrap js-->
    <script src="${staticPath}/uadmin/js/html5shiv.js"></script>
    <script src="${staticPath}/uadmin/js/respond.min.js"></script>
    <script src="${staticPath}/uadmin/js/jquery.menu.js"></script>
    <script src="${staticPath}/vendors/slimscroll/jquery.slimscroll.js"></script>
    <!--CORE JAVASCRIPT-->
    <script src="${staticPath}/uadmin/js/main.js"></script>
    <script>

		$(document).ready(function(){
			var list_theme = $('.dropdown-theme-setting > li > select#list_theme');
		    list_theme.on('change', function(){
		    	var theme=$(this).val();
		    	$.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   
		    		//window.location.reload();
		    		window.top.location.href="${adminPath}";
		    	});
		    	//$.get('${adminPath}/theme/'+theme+'?url='+window.top.location.href,function(result){   window.location.reload();});
		    });
		});
		
		var changePasswordForm;
		function changePassword(){
			changePasswordForm.ajaxPost();
		}
		
		$(document).ready(function() {
			
			setTimeout(function(){},100);
			    changePasswordForm=$("#changePasswordForm").Validform({
				tiptype:function(msg,o,cssctl){
					if(!o.obj.is("form")){
						var objtip=o.obj.siblings(".Validform_checktip");
						cssctl(objtip,o.type);
						objtip.text(msg);
					}
				},beforeSubmit:function(curform){
					try{
						var beforeFunc=beforeSubmit;
						if(beforeFunc&&typeof(beforeFunc)=="function"){
							return beforeFunc(curform); 
						}
					}catch(err){
						
					}
					return true;	
				},callback:function(result){
					if(result.ret==0)
	              	{
	              	    top.layer.alert(result.msg, {icon: 0, title:'提示'});
	              	    //运行回调
	          	        callFunc(result);
	              	}else
	              	{
	              		top.layer.alert(result.msg, {icon: 0, title:'警告'});
	              	}
				}
			});
		});
		
	</script>
	<script type="text/javascript">
	 var myChart1 = echarts.init(document.getElementById('echarts'));
	 var myChart2 = echarts.init(document.getElementById('echarts2'));
	var _pieData={ld:[],vd:[]};
	var _barData={ld:[],vd:[],xd:[]};
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
				    tooltip : { trigger: 'axis'  },
				  	//颜色标记
				    //legend: { data:data.ld },
				    color:['#A4D3EE'],
				    xAxis : [
				        {
				            type : 'category',
				            data:data.xd
				        }
				    ],
				    yAxis : [ { type : 'value' }],
				    grid: {
				        left: '3%',
				        right: '3%',
				        bottom: '10%',
				        containLabel: true
				    },
				    series : [
				        {
				            type:'bar',
				            barWidth:'50px',
				            data:data.vd,
				            markPoint: {  
	                            data: [  
	                                {type: 'max', name: '最大值'},  
	                                {type: 'min', name: '最小值'}  
	                            ]  
	                        }
				    	}
				    ] 
				    //series:data.vd
				};

	        // 使用刚指定的配置项和数据显示图表。
	        myChart2.setOption(option);
	        window.onresize = myChart2.resize; 
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
			$.ajax({
				type:"POST",
				url:"${adminPath}/medicalhomechart/medicalhomechart/findHomeCharts",
				dataType:"json",
				success:function(data){
					var pielist = data.extend.pieList;
					var barlist = data.extend.barList;
					$("#echarts-title").html("组织预测百分比")
					$("#echarts2-title").html("医用预测统计图")
					
					//饼图设置
					$.each(pielist,function(i,item){
						_pieData.ld.push(item.name);
						temp = {value:item.predictCount,name:item.name};
						_pieData.vd.push(temp);
					});
					
					var d=[];
					var tablelist = '';
					
					//列表展示
					 $.each(barlist,function(i,item){
						 _barData.ld=[item.name];
						 d.push(item.predictCount);
						_barData.xd.push(item.name==undefined?'-':item.name);
						if(i<=8) tablelist += '<tr><td>'+item.name+'</td><td>'+item.predictCount+'</td><td>'
					});
					 tablelist = tablelist.replace(/undefined/g,'-');
					 $("#echarts-list>table").append(tablelist);
					 
					//条形图设置
					_barData.vd=d;
					 
				},
				complete:function(){
					myChart1.hideLoading();
					Echarts1(_pieData);
					myChart2.hideLoading();
					Echarts2(_barData);
				}
			});
		}
		$(function() {
			initEchartsData();
			//Echarts1();
			//Echarts2();
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