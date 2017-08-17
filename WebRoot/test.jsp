
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

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
	  },100);
	  
   }

</script>
</head>
<body>
<button onclick="showProc();return" >点击显示进度条</button>
</body>
</html>