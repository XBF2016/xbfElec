<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/util.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.8.3.js"></script>
	</head>
	<style type="text/css">
	 tr{
		text-align: center;
	}
	td{
		border-bottom:1 gray solid;
		background-color: #f5fafe;
		
	}
	thead tr td{
		font-weight:bold;
		background-image: url('${pageContext.request.contextPath }/images/tablehead.jpg');
	}

	
</style>

	<body >
	<div align="center" >
	<div style="width:90%;background-color: #f5fafe;">
		<form action="${pageContext.request.contextPath}/workflow/elecTaskAction_myTask.action" >
		<div style="height: 10px;"></div>
		<div style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')" align="center">流程记录详情</div>
		<div align="left">
		</div>
		<div style="height: 10px"></div>
		</form>
		<div>
			<div style="float: left;width:130px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
				<img src="${pageContext.request.contextPath }/images/yin.gif" width="15" />审批处理列表
			</div>
			<div style="clear: both;"></div>
		</div>
		<table   style="width: 100%" cellspacing="0"  border="0">
			<thead style="height: 20px;">
					<tr>
								<td>任务名称</td>
								<td>审批人</td>
								<td>审批意见</td>
					</tr>
					
			</thead>
			<s:iterator  value="%{#request.approveInfos}"  var="approveInfo">
						<tr>
							<td><s:property value="%{#approveInfo.taskName}" /></td>
							<td><s:property value="%{#approveInfo.username}" /></td>
							<td><s:property value="%{#approveInfo.comment}" /></td>
						</tr>
				</s:iterator>
		</table>
		
	</div>
	</div>
	</body>
</html>
