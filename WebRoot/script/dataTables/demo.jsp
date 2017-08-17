<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/script/dataTables/css/jquery.dataTables.css">
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/js/jquery.dataTables.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/fnReloadAjax.js"></script>
	</head>
<script type="text/javascript">
//配置参数
var options = {
		"bPaginate": true, //翻页功能
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"bInfo": true,//页脚信息
		"bSort": false, //排序功能
 		"bStateSave": true,//状态保存开关,使用cookie记录当前页码,即是页面关闭,重新打开时还是上次的页码
		//ajax 相关
        "bProcessing": true,  //进度条
 		"bServerSide": true,  //开启服务器支持
		"sAjaxSource": "xxx.action",  //ajax请求的url
		//向服务器发送请求时调用,用来添加额外的请求参数
		"fnServerParams": function ( aoData ) {
				var username=$("#username").val();
				if(username){
					aoData.push( { "name": "username", "value": username} );	
				}
		},
		//需要从服务器请求数据时调用
		"fnServerData": function ( sSource, aoData, fnCallback ) {
 					$.ajax( {
 							"dataType": 'json',
 							"type": "POST",
 							"url": sSource,
 							"data": aoData,
 							"success": fnCallback
 					} );
 		 },
 		 //定义表格的每列如何从json数据对象中取得数据
        "columns": [
            { "data": "account" },//第1列为 json对象的account属性
            { "data": "username" },
            { "data": "gender" },
            { "data": "contactTel" },
            { "data": "isDuty" },
            { "data": "account" }
        ],
 		//国际化信息
		"oLanguage":{
     		  "sLengthMenu": "每页显示 _MENU_条",
    		  "sZeroRecords": "没有找到符合条件的数据",
  		      "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
   			  "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
    		  "sInfoEmpty": "木有记录",
   		      "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
  		      "sSearch": "搜索：",
   		      "oPaginate": {
              		"sFirst": "首页",
              		"sPrevious": "前一页",
              		"sNext": "后一页",
              		"sLast": "尾页"
       		  }
		},
		//创建一个表格行时调用,可以修改追加表格行的数据
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
				//把此行最后一列的数据修改为
				$(':last-child', nRow).html('<a href="xxx">删除</a>');
				
				//为此行添加数据经过事件
				nRow.onmouseover=function(){
					$('td' , this).css('background-color', 'white');
				}
				nRow.onmouseout=function(){
					$('td' , this).css('background-color','#F5FAFE');	
				}
				return nRow;
		}
}

var pageTables;
$(document).ready(function() {
   pageTables =  $('#table').dataTable( options );
});

function query(){
	pageTables.fnReloadAjax();	
}
</script>
		
<body >
		<table id="table"  class="tableClass"   >
				<thead style="width: 100%;">
					<tr>
							<td>账号</td>
							<td>用户姓名</td>
							<td>性别</td>
							<td>联系电话</td>
							<td>是否在职</td>
							<td>操作</td>
					</tr>
				</thead>
		</table>
</body>
</html>
