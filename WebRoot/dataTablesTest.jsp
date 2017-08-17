<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/script/dataTables/css/jquery.dataTables.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/dataTables/js/jquery.dataTables.js"></script>

</head>

<script type="text/javascript">
        $(document).ready(function(){
			$("#table").dataTable({
				bFilter:false,
				bSort :false,
				bLengthChange:false,
				oLanguage: {
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
			
			
				bServerSide:true ,  //开启服务器支持
				sAjaxSource:"${pageContext.request.contextPath }/system/userAction_page.action?timestamp="+new Date().getTime(),//指定请求路径
				fnServerParams : function ( aoData ) {
					//...其他的js代码确定value的值
					aoData.push( { 
					      "name": "username",
					      "value": "a"
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
				},
				  "columns": [
				            { "data": "account" },
				            { "data": "username" },
				            { "data": "isDuty" }
				        ]
			});
	});
</script>

<body>
<table id="table" border="1px">
<thead><tr><td>账户</td><td>用户名</td><td>是否在职</td></thead>
</table>
</body>
</html>