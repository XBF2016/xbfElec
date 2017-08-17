<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>用户管理</title>		
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/script/dataTables/css/jquery.dataTables.css">
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/js/jquery.dataTables.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/util.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/dataTables/fnReloadAjax.js"></script>
	</head>
	<style type="text/css">
	.tableClass tr{
		text-align: center;
    
	}
	.tableClass tr td{
		line-height: 5px;	
		background-color: #f5fafe;
		border-top:gray 1px solid;
		border-left:gray 1px solid;
	}
	.tableClass thead tr td{
		font-weight:bold;
		line-height:0px;
		border-bottom:none;
		background-image: url('${pageContext.request.contextPath }/images/tablehead.jpg');
	}
	.tableClass  img{
		margin-top: -6px;
		margin-bottom: -6px;
	}

	
</style>
<script type="text/javascript">
var options = {
		"oLanguage": {
			       "sLengthMenu": "每页显示 _MENU_条",
			       "sZeroRecords": "没有找到符合条件的数据",
			       "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
			       "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			       "sInfoEmpty": "有木记录",
			       "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
			       "sSearch": "搜索：",
			       "oPaginate": {
			              "sFirst": "首页",
			              "sPrevious": "前一页",
			              "sNext": "后一页",
			              "sLast": "尾页"
			       }
				},
        "bProcessing": true,
		
        "columns": [
            { "data": "account" },
            { "data": "username" },
            { "data": "gender" },
            { "data": "contactTel" },
            { "data": "isDuty" },
            { "data": "isDuty" }
        ],
        "bPaginate": true, //翻页功能
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"bInfo": true,//页脚信息
		"bSort": false, //排序功能
 		"bStateSave": true,//状态保存开关,使用cookie记录当前页码,即是页面关闭,重新打开时还是上次的页码
 		
 		//ajax服务器分页逻辑
 		bServerSide:true ,  //开启服务器支持
		sAjaxSource:"${pageContext.request.contextPath }/system/userAction_page.action?timestamp="+new Date().getTime(),//指定请求路径
		fnRowCallback: function( nRow, aData, iDisplayIndex ) {
				var deleteUrl = "${pageContext.request.contextPath }/system/userAction_delete.action";
				var updateUrl = "${pageContext.request.contextPath }/system/userAction_userEdit.action";
				var content = "<a href='"+deleteUrl+"?userId="+aData.userId+"'>删除</a>   <a href='"+updateUrl+"?userId="+aData.userId+"'>修改</a>"
				//把一行的最后一个单元格里的内容替换为删除超链接 和 修改超链接
				$(":last-child",nRow).html(content);
				
			
				nRow.onmouseover=function(){
					$('td' , this).css('background-color', 'white');
				}
				nRow.onmouseout=function(){
					$('td' , this).css('background-color','#F5FAFE');	
				}
				return nRow;
		},
		fnServerParams : function ( aoData ) {
					var value = $("#username").val();
					//...其他的js代码确定value的值
					aoData.push( { 
					      "name": "username",
					      "value": value
					 });
				},
				//需要从服务器请求数据时调用(如何使用ajax进行请求)
				fnServerData: function ( sSource, aoData,fnCallback, oSettings ) { 
				      oSettings.jqXHR = $.ajax( {
				      		"dataType": 'json', 
							"type": "POST", 
							"url": sSource, 
				      		"data": aoData, 
				     		"success": fnCallback 
				     } );
				}
	}
var pageTables;
$(document).ready(function() {
   pageTables =  $('#table').dataTable( options );
});

function query(){
	pageTables.fnReloadAjax();
}

function exportExcel(){
	$("#exportForm").attr("action","${pageContext.request.contextPath}/system/userAction_exportExcel.action") ;
	$("#exportForm").submit();
}

function exportChart(){
	$("#exportForm").attr("action","${pageContext.request.contextPath}/system/userAction_exportChart.action") ;
	$("#exportForm").submit();
}

</script>
		
	<body >
	<div align="center" >
	<div style="width:90%;background-color: #f5fafe;">
		<div style="height: 10px;"></div>
		<div style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')" align="center">用户信息管理</div>
		<div align="left">
			<form id="exportForm"  method="post">
				姓名<input id="username" name="username" type="text" id="username" />
			</form>
		</div>
		<div style="height: 10px"></div>
		<div>
			<div style="float: left;width:80px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
				<img src="${pageContext.request.contextPath }/images/yin.gif" width="15" />用户列表
			</div>
			<div align="right" style="float: right;">
				<button  onclick="query()" >查询</button>
				<button  onclick="exportExcel()" >导出报表</button>
				<button  onclick="exportChart()" >统计各单位人数</button>
				
				<button onclick="openWindow('${pageContext.request.contextPath }/system/userAction_userAdd.action','800','400')">添加用户</button>
			</div>
			<div style="clear: both;"></div>
		</div>
		<table id="table"  class="tableClass"   >
				<thead style="width: 100%;">
					<tr>
								<td>Name</td>
								<td>用户姓名</td>
								<td>性别</td>
								<td>联系电话</td>
								<td>是否在职</td>
								<td>操作</td>
					</tr>
				</thead>
		</table>
		
	</div>
	</div>
	</body>
</html>
