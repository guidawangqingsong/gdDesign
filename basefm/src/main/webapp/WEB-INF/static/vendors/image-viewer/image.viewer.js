window.onload =  function(){
	var windowHeight = window.innerHeight;
	var windowWidth =  window.innerWidth;
	var imgArr = new Array();
	var image_viewer_index =0;//当前下标
	var shadebox = document.getElementById("image_viewer_shadebox");
	var slideArr = document.getElementsByClassName("image_viewer");
	//debugger;
	for(var i=0;i<slideArr.length;i++){
		var liarr = slideArr[i].getElementsByTagName("li");
		for(var j =0;j<liarr.length;j++){
			liarr[j].index = j;
			liarr[j].onclick = function(){
				image_viewer_index = this.index;
				loadSlide(this.parentNode);
				
			}
		}
		
	}
	function loadSlide(imgBox){
		imgArr = new Array();
		var liarr = imgBox.getElementsByTagName("li");
		for(var i=0;i<liarr.length;i++){
			imgArr.push(liarr[i].getElementsByTagName("img")[0]);
		}
		if(!shadebox){
			var div = document.createElement("div");
			div.id="image_viewer_shadebox";
			div.style.height = window.innerHeight+"px";
			div.innerHTML = '<a href="javascript:" class="image_viewer_prev"></a>'+
					'<a href="javascript:" class="image_viewer_next"></a>'+
					'<a href="javascript:" class="image_viewer_close"></a>'+
					'<img src="img/1.jpg" style="max-height:100%"/>';
			document.body.appendChild(div);
			shadebox = div;
		}
		shadebox.style.display = "block";
		shadebox.style.height = window.innerHeight+"px";
		shadebox.getElementsByTagName("img")[0].style.height = window.innerHeight+"px";
		shadebox.getElementsByTagName("img")[0].src = imgArr[image_viewer_index].src;
		shadebox.getElementsByClassName("image_viewer_prev")[0].onclick = image_viewer_prev;
		shadebox.getElementsByClassName("image_viewer_next")[0].onclick = image_viewer_next;
		shadebox.getElementsByClassName("image_viewer_close")[0].onclick = image_viewer_close;
	}
	
	/*上一张*/
	function image_viewer_prev(){
		if(image_viewer_index < 1) return;
		image_viewer_index--;
		shadebox.getElementsByTagName("img")[0].src = imgArr[image_viewer_index].src;
	}
	/*下一张*/
	function image_viewer_next(){
		if(image_viewer_index > imgArr.length-2) return;
		image_viewer_index++;
		shadebox.getElementsByTagName("img")[0].src = imgArr[image_viewer_index].src;
	}
	function image_viewer_close(){
		shadebox.style.display ="none";
	}
};
	