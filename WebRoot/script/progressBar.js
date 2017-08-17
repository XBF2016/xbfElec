//显示进度条函数
function showProgressBar(){
	
	//预定义颜色	
	var colors = ["#5B00AE","#28FF28","#FF8000","#00DB00","#408080","#79FF79"];
	
	//背景层,平铺,灰色,0.33透明度
	var div2 = document.createElement("div2");
	div2.style.position="absolute";
	div2.style.top = "0";
	div2.style .left="0";
	div2.style.width = "100%";
	div2.style.height = "100%";
	div2.style['background-color']="gray";
	div2.style.opacity = "0.33";
    document.body.appendChild(div2);

    //进度条浮动层,居中
	var div = document.createElement("div");
	div.style.position="absolute";
	div.style.top = "50%";
	div.style .left="50%";
	div.style.width = "33%";
	div.style.margin="-20% 0 0 -20%";
	div.innerHTML = '处理中...<div style="background-color: gray;width: 100%;height: 10px;"><div id="progressBar" style="background-color: '+colors[0]+';width: 1%;height: 10px;"></div></div>';
	document.body.appendChild(div);
	
	//进度条div dom对象
	var progressBar = document.getElementById("progressBar");
	
	var percent = 1;//控制进度条百分比
	var count = 0;//控制颜色切换
	
	setInterval(function(){
			//如果满了重新开始并改变颜色
			if(percent>=100){
				percent=1;
				count++;
				progressBar.style['background-color']= colors[count%colors.length];//每次走满一条,切换颜色
			}
			progressBar.style.width=percent+"%";
			percent+=2;
   },200);//200毫秒刷新一次
}
