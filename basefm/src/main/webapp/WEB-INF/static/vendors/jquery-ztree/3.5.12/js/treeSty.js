(function(){
	var nod = document.createElement('style'),   
     str = '.col-sm-9{position: relative;}'+
     '  	.col-sm-9:BEFORE{'+
     '  		content:"";'+
     '  		width:10px;'+
     '  		height:110%;'+
     '  		background-color: #F0F2F5;'+
     '  		position: absolute;'+
     '  		top:-25px;'+
     '  		left:-10px;'+
     '  	}'; 
     nod.type='text/css';  
     if (nod.styleSheet) { //ie下  
         nod.styleSheet.cssText = str;
     } else {
         nod.innerHTML = str; //或者写成 nod.appendChild(document.createTextNode(str))  
     }
     document.getElementsByTagName('head')[0].appendChild(nod); 
})();