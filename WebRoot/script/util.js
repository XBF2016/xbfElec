function openWindowWithName(url,width,height,name) {
  var left=(screen.availWidth-width)/2;
  var top=(screen.availHeight-height)/2;
  var ref="";
  ref += "width="+width+"px,height="+height+"px,";
  ref += "left="+left+"px,top="+top+"px,";
  ref += "resizable=yes,scrollbars=yes,status=yes,toolbar=no,systemmenu=no,location=no,borderSize=thin";//channelmode,fullscreen
  var childWindow = window.open(url,name,ref,false);
  childWindow.focus();
}

function  openWindow(url,width,height){
  openWindowWithName(url,width,height,'newwindow');
}




//格式化日期函数
function formatDate(date ,pattern){
    if(!pattern){
	    pattern="yyyy-MM-dd";
    }
	var o = {
		'M+' : date.getMonth() + 1, //月份\n"
		'd+' : date.getDate(), //日 \n"
		'h+' : date.getHours(), //小时\n"
		'm+' : date.getMinutes(), //分 \n"
		's+' : date.getSeconds(), //秒 \n"
		'S' : date.getMilliseconds()
	};
	//替换填充年份
	if (/(y+)/.test(pattern)) {
		pattern = pattern.replace(RegExp.$1, (date.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	//填充替换剩余的时间元素
	for ( var key in o){
		if (new RegExp('(' + key + ')').test(pattern)){
			pattern = pattern.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[key])
					: (('00' + o[key]).substr(('' + o[key]).length)));
		}
	}
	//返回格式化结果
    return pattern;	
}

 //点击显示进度条的函数
      function showProc(){
	  //预定义的颜色
	  var colors=['red','orange','yellow','green','blue','indigo','purple'];
	  
	  //1.灰色的全屏平铺层
	  var div1=document.createElement("div");
	  //设置属性：绝对定位
	  div1.style.position="absolute";
	  div1.style.top=0;
	  div1.style.left=0;
	  div1.style.height="100%";
	  div1.style.width="100%";
	  div1.style['background-color']='gray';
	  //透明度
	  div1.style.opacity='0.3';
	  //添加到文档的body中
	  document.body.appendChild(div1);
	  
	  //2.进度条底层
	  var div2=document.createElement("div");
	   //设置属性：绝对定位
	  div2.style.position="absolute";
	   //位置
	  div2.style.top='40%';
	  div2.style.left='30%';
	  //大小 
	  div2.style.height="1.5%";
	  div2.style.width="40%";
	  div2.style['background-color']='gray';
	    //添加到文档的body中
	   document.body.appendChild(div2);
	    
	   //3.进度条：随时间前进
	  var div3=document.createElement("div");
	   //div3添加到div2中，位置不用设置了
	  div2.appendChild(div3);
	   //设置属性：绝对定位
	  div3.style.position="absolute";
	  div3.style.height="100%";
	  div3.style['background-color']=colors[0];
	  //时间函数：控制进度条的长度变化
	  var count=1;
	  var color=1;
	  setInterval(function(){
		  div3.style.width=count+'%';
		  count++;
		  if(count==100){
			  count=1;
			   //满一百颜色改变
			  div3.style['background-color']=colors[color];
			   color++
			   if(color==colors.length){
				  color=0;
			  }
		  }
	  },100);}