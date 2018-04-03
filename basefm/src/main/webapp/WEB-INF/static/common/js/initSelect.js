
/**
 * @function 根据url绑定下拉数据
 * @param id 控件Id
 * @param url 访问地址
 * @param value 控件的值
 * @param idFeild id字段
 * @param textFeild 显示字段
 */
function initSelectByUrl(id,url,value,idFeild,textFeild){
	$.ajax({
		   type:"post",
		   url:url,
		   dataType:'json',
		   data:{id:value},
		   success:function(data){
			   $("#"+id).empty();			
			   $("#"+id).append("<option value='' selected='true'>请选择</option>");			
			   $(data).each(function(index,item){
				   if(item.id==value){
					   $("#"+id).append("<option value='"+item[idFeild]+"' selected='true'>"+item[textFeild]+"</option>");	
				   }else{
					   $("#"+id).append("<option value='"+item[idFeild]+"'>"+item[textFeild]+"</option>");	
				   }				   
			   });
		   }
		});
}

/**
 * @function 根据数据源绑定下拉数据
 * @param id 控件Id
 * @param data 数据源（json数据）
 * @param value 控件的值
 * @param idFeild id字段
 * @param textFeild 显示字段
 */
function initSelectByData(id,data,value,idFeild,textFeild){
	$(data).each(function(index,item){
		if(typeof(value)=='number'&&item.id==value){
		    $("#"+id).append("<option value='"+item[idFeild]+"' selected='true'>"+item[textFeild]+"</option>");	
	    }else{
	    	$("#"+id).append("<option value='"+item[idFeild]+"'>"+item[textFeild]+"</option>");	
	    }	    
	});
}


/**
 * @function 级联菜单，获取下拉数据
 * @param id 控件Id
 * @param url 访问地址
 * @param value 控件的值
 * @param pValue 父级的值
 * @param idFeild id字段
 * @param textFeild 显示字段
 */
function initCascadingSelect(id,url,value,pValue,idFeild,textFeild){
	$.ajax({
		   type:"post",
		   url:url,
		   dataType:'json',
		   data:{id:value,pId:pValue},
		   success:function(data){
			   $("#"+id).empty();			
			   $("#"+id).append("<option value='' selected='selected'>请选择</option>");			
			   $(data).each(function(index,item){
				   if(item.id==value){
					   $("#"+id).append("<option value='"+item[idFeild]+"' selected='selected'>"+item[textFeild]+"</option>");	
				   }else{
					   $("#"+id).append("<option value='"+item[idFeild]+"'>"+item[textFeild]+"</option>");	
				   }				   
			   });
		   }
		});
}