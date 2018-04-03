var _staticPath = null,_option=null;
var _timer = null;

function lrBtn(tag){
	var $li = parent.getIndexByImg(tag);
	var op = {
			num:$li.find("p").html(),
			name:$li.find("p").html(),
	}
	zoomInit($li.find("img").attr("src"),op,_staticPath);
}
function zoomInit(imgSrc,option,path){
	_staticPath = path;
	_option = option;
	option.num = option.num===undefined?'无':option.num;
	option.name = option.name===undefined?'无':option.name;
	option.detail = option.detail===undefined?'无':option.detail;
	var str = '<a href="javascript:"  class="cutLR-btn left" onclick="lrBtn(0)"></a><a href="javascript:" onclick="lrBtn(1)" class="cutLR-btn right"></a><div id="pageContent">'+
'			<div id="imgContainer">'+
'				<img id="imageFullScreen" src="'+imgSrc+'" />'+
'			</div>'+
'			<div id="positionButtonDiv">'+
'				'+
'				<p>'+
'					<span class="positionButtonSpan">'+
'					<map name="positionMap" class="positionMapClass">'+
'						<area id="topPositionMap" shape="rect" coords="20,0,40,20" title="move up" alt="move up"/>'+
'		                <area id="leftPositionMap" shape="rect" coords="0,20,20,40" title="move left" alt="move left"/>'+
'						<area id="rightPositionMap" shape="rect" coords="40,20,60,40" title="move right" alt="move right"/>'+
'						<area id="bottomPositionMap" shape="rect" coords="20,40,40,60" title="move bottom" alt="move bottom"/>'+
'					</map>'+
'					<img src="'+_staticPath+'/vendors/zoom-jquery/images/position2.png" usemap="#positionMap" />'+
'		         </span>'+
'				</p>'+
'				<p>'+
'					<span> '+
'						<span id="zoomInButton" class="zoomButton max icon-amplification_icon" title="放大"></span> '+
'						<span id="zoomOutButton" class="zoomButton min icon-suoxiao" title="缩小" ></span>'+
'						<span  class="zoomButton2 info icon-prompt">'+
'							<span class="img-info">'+
'								<span>图号：'+_option.num+'</span>'+
'								<span>图名：'+_option.name+'</span>'+
'								<span>描述：'+_option.detail+'</span>'+
'							</span>'+
'						</span> '+
'						<a href="'+imgSrc+'" download class="zoomButton2 download icon-xiazai"'+
						//'onclick="DownLoadIMG(\''+_option.downUrl+'\')"'+
						'title="下载"></a>'+
'						<span  class="zoomButton3 init icon-quanping"  title="自适应大小" onclick="newInit()"></span>'+
'					</span> '+
'				</p>'+
'			</div>'+
'		</div>';
	$("body").html(str);
	$("#pageContent,#imgContainer").height($(window).height()-50);
	//载入插件
	$('#imageFullScreen').smartZoom({
		'containerClass': 'zoomableContainer'
	});
	$('#topPositionMap,#leftPositionMap,#rightPositionMap,#bottomPositionMap').bind("click", moveButtonClickHandler);
	$('#zoomInButton,#zoomOutButton').bind("click", zoomButtonClickHandler);

	function zoomButtonClickHandler(e) {
		var scaleToAdd = 0.8;
		if(e.target.id == 'zoomOutButton')
			scaleToAdd = -scaleToAdd;
		$('#imageFullScreen').smartZoom('zoom', scaleToAdd);
	}

	function moveButtonClickHandler(e) {
		var pixelsToMoveOnX = 0;
		var pixelsToMoveOnY = 0;

		switch(e.target.id) {
			case "leftPositionMap":
				pixelsToMoveOnX = 50;
				break;
			case "rightPositionMap":
				pixelsToMoveOnX = -50;
				break;
			case "topPositionMap":
				pixelsToMoveOnY = 50;
				break;
			case "bottomPositionMap":
				pixelsToMoveOnY = -50;
				break;
		}
		$('#imageFullScreen').smartZoom('pan', pixelsToMoveOnX, pixelsToMoveOnY);
	}
	clearInterval(_timer);
	_timer = setInterval("setIntervals()",500);
}
function setIntervals(){
	var pageHeight = $("#pageContent").height()+50;
	var winHeight = window.innerHeight;
	if(pageHeight === winHeight){
		clearInterval(_timer);
		return;
	}
	newInit();
}
function newInit(){
	zoomInit($("#imageFullScreen").attr("src"),_option,_staticPath);
}

//下载图片
function DownLoadIMG(imgPathURL) {
  $.ajax({
	  type:"POST",
	  url:_option.downLoader+"/wbs/taskmain/downImg?path="+_option.downUrl,
	  success:function(data){
		  alert(data);
	  }
  });
}

