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
		<div style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')" align="center">流程定义管理</div>
		<div style="height: 10px"></div>
		<div>
			<div style="float: left;width:130px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
				<img src="${pageContext.request.contextPath }/images/yin.gif" width="15" />已部署的流程定义
			</div>
			<div align="right" style="float: right;">
				<input  type="button" value="刷新"  onclick="location.reload()" />&nbsp;&nbsp;
			    <input  type="button" value="部署流程定义文档"  onclick="openWindow('${pageContext.request.contextPath }/workflow/processDefinitionAction_add.action','700','400')" />
			</div>
			<div style="clear: both;"></div>
			<div align="left">
				 说明：1，列表显示的是所有流程定义（不同Key）的最新版本。<br>
					       &nbsp;&nbsp;&nbsp;&nbsp;2，删除流程定义时，此Key的所有版本的流程定义都会被删除。<br />
					       &nbsp;&nbsp;&nbsp;&nbsp;3，查看流程图时显示的这个最新版本的流程定义的图片。
			</div>
		</div>
		<table id="table"  style="width: 100%" cellspacing="0"  border="0">
			<thead style="height: 20px;">
					<tr>
								<td>流程名称</td>
								<td>最新版本</td>
								<td>说明</td>
								<td>操作</td>
					</tr>
					
			</thead>
			<s:iterator value="%{#request.processDefinitionList}" var="processDefinition">
						<tr>
							<td><s:property value="%{#processDefinition.name}"/></td>
							<td><s:property value="%{#processDefinition.version}"/></td>
							<td><s:property value="%{#processDefinition.description}"/> </td>
							<td>
								<a href="javascript:void(0)" onclick="openWindow(' ${pageContext.request.contextPath}/workflow/processDefinitionAction_showProcessImg.action?id=<s:property value="%{#processDefinition.id}"/>',800,500);">查看流程图</a>
								<a href="${pageContext.request.contextPath}/workflow/processDefinitionAction_delete.action?id=<s:property value="%{#processDefinition.id}"/>" onclick="return confirm('你确定要删除 <s:property value="%{#processDefinition.name}"/>？')">
										<img src="${pageContext.request.contextPath }/images/delete.gif" />
								</a>
							</td>
						</tr>
			</s:iterator>
		</table>
		
	</div>
	</div>
	</body>
</html>
