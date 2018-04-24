<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 		
  		<div class="absolute left">
			<div class="uinfo">
				<img src="${appPath}/${fns:getUser().portrait}" style="height:143px" class="head" onload="this.style.left=-(this.width-145)/2+'px'" onerror="this.src='${staticPath}/uadmin/images/gallery/adminFlower.jpg'"/>
				<p class="name">${fns:getUser().username}</p>
				<p class="job">${fns:getUser().realname}</p>
			</div>
			<div class="tip">
				<div class="am-u-sm-4">
					<p class="n mess">0</p>
					<p>消息</p>
				</div>
				<div class="am-u-sm-4">
					<p class="n flow">0</p>
					<p>流程</p>
				</div>
				<div class="am-u-sm-4">
					<p class="n alt">0</p>
					<p>提醒</p>
				</div>
			</div>
			<!-- <div class="edit">
				<a href="javascript:">编辑</a>
			</div> -->
		</div>
		<div class="absolute right">
			<ul class="tabs-t">
				<li class="active"><div id="echarts-title">组织预测百分比</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<div id="echarts" style="width:70%;height:400px;"></div>
					<div id="echarts-list">
						<table border="1">
							<tr>
								<th>组织</th>
								<th>总预测数</th>
							</tr>
						</table>
					</div>
				</li>
			</ul>
		</div>
		<div class="absolute bottom">
			<ul class="tabs-t">
				<li class="active"><div id="echarts2-title">医用预测统计图</div></li>
			</ul>
			<ul class="tabs-c">
				<li class="active">
					<div id="echarts2" style="width:100%;min-height:300px;"></div>
				</li>
			</ul>
		</div>
		<hr>