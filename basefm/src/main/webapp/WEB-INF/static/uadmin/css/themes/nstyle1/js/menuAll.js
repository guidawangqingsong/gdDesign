;

(function(){
	$.ajax({
		type:"POST",
		url:"system/findTopMenu.do",
		dataType:"json",
		success:function(data){
			var str ='';
			$.each(data,function(i,item){
				str +='<li onclick="getMenuLeft(\''+item.id+'\',this)"><a href="javascript:"><img src="plug-in/jjxx/themes/default/images/searchbox_button.png"/><p>'+item.name+'</p></a></li>'
			});
			$("#main-menuall").html(str);
			initAllAuto();
		}
	});
})()