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
		line-height: 5px;	
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
		<div style="height: 10px;"></div>
		<div style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')" align="center">待我审批页面</div>
		<div style="height: 10px"></div>
		<div>
			<div style="float: left;width:130px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
				<img src="${pageContext.request.contextPath }/images/yin.gif" width="15" />待我审批的任务列表
			</div>
			<div align="right" style="float: right;">
				<input  type="button" value="刷新"  onclick="location.reload()" />
			</div>
			<div style="clear: both;"></div>
			<div align="left">
			</div>
		</div>
		<table id="table"    style="width: 100%" cellspacing="0"  border="0">
			<thead style="height: 20px;">
					<tr>
								<td>工作流程名称</td>
								<td>当前任务名称</td>				
								<td>查看流程图</td>
								<td>申请人</td>
								<td>查看流程信息</td>
								<td>操作</td>
					</tr>
					
			</thead>
			<s:iterator value="%{#request.taskApplyList}" var="taskApply">
						<tr>
							<td><s:property value="%{#taskApply.processDefinitionKey}"/></td>
							<td><s:property value="%{#taskApply.taskName}"/></td>
							<td><a href="javascript:void(0)" onclick="openWindow(' ${pageContext.request.contextPath}/workflow/processDefinitionAction_showProcessImg.action?id=<s:property value="%{#taskApply.processDefinitionId}"/>',800,500);">查看流程图</a></td>
							<td><s:property value="%{#taskApply.username}"/></td>
							<td><a href="${pageContext.request.contextPath }/workflow/taskAction_approveInfo.action?taskId=<s:property value="%{#taskApply.taskId}"/>">查看流程记录</a></td>
							<td><a href="${pageContext.request.contextPath}/workflow/taskAction_taskApprove.action?taskId=<s:property value="%{#taskApply.taskId}"/>">审批</a></td>
						</tr>
			</s:iterator>
		</table>
		
	</div>
	</div>
	<div id="tt"></div>
	</body>
</html>
