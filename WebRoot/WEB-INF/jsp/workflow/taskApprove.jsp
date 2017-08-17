<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/util.js"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.8.3.js"></script>
	</head>
	<script type="text/javascript">
		function approve(isAgree){
			if(!isAgree){
				$("#isAgree").val(false);
			}
			$("#form1").submit();
		}
	</script>

	<body >
	<div align="center" >
	<div style="width:90%;background-color: #f5fafe;">
		<div style="height: 10px;"></div>
		<div style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')" align="center">审批页面</div>
		<div style="height: 10px"></div>
		<div>
			<div align="left">
			说明：<br/>
			1，同意：本次审批通过，流程继续执行。如果所有的环节都通过，则表单的最终状态为：已通过。<br/>
			2，不同意：本次审批未通过，流程结束，不再继续执行。表单的最终状态为：未通过。
			</div>
		</div>
		<br/><br/>
		<form id="form1" action="${pageContext.request.contextPath }/workflow/taskAction_approve.action" method="post">
			<input type="hidden" id="isAgree" name="isAgree" value="true"/>
			<input type="hidden" id="taskId" name="taskId" value="<s:property value="%{#request.taskApply.taskId}"/>"/>
		<table id="table"  style="width: 100%" cellspacing="0"   border="0">
					<tr>
						<td>查看申请文件</td>	
						<td align="left"><a href="${pageContext.request.contextPath }/workflow/taskAction_downloadApplyFile.action?taskId=<s:property value="%{#request.taskApply.taskId}"/>" ><s:property value="%{#request.taskApply.filename}"/></a></td>				
					</tr>
					<tr>
						<td>当前任务名称</td>
						<td><s:property value="%{#request.taskApply.taskName}"/></td>
					</tr>
					<tr>
						<td>选择下一步</td>
						<td align="left">
							<s:select name="outcome" list="%{#request.taskApply.outcomes}"></s:select>
						</td>
					</tr>
					<tr>
						<td>审批意见</td>
						<td align="left">
			        		<textarea name="comment" cols="52" rows="4" ></textarea>
			        	</td>
					</tr>
		</table>
		<hr/>
		<div>
			<input type="button"  value="同意"  onclick="approve(true)" />
			<input type="button"  value="不同意"   onClick="approve(false)" />
		</div>
		</form>
	</div>
	</div>
	<div id="tt"></div>
	</body>
</html>
