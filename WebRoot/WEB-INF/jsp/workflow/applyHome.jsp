<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<link href="${pageContext.request.contextPath }/css/Style.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript" language="javascript"
			src="${pageContext.request.contextPath }/script/util.js">
</script>
		<script type="text/javascript" language="javascript"
			src="${pageContext.request.contextPath }/script/jquery-1.8.3.js">
</script>
	</head>
	<style type="text/css">
tr {
	text-align: center;
}

td {
	border-bottom: 1 gray solid;
	background-color: #f5fafe;
}

thead tr td {
	font-weight: bold;
	background-image:
		url('${pageContext.request.contextPath }/images/tablehead.jpg');
}
</style>

	<body>
		<div align="center">
			<div style="width: 90%; background-color: #f5fafe;">
				<div style="height: 10px;"></div>
				<div
					style="font: bold 12pt 宋体 ;background-image:url('${pageContext.request.contextPath}/images/b-info.gif')"
					align="center">
					流程启动页面
				</div>
				<div style="height: 10px"></div>
				<div>
					<div
						style="float: left;width:150px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
						<img src="${pageContext.request.contextPath }/images/yin.gif"
							width="15" />
						流程定义及模板文件列表
					</div>
					<div align="right" style="float: right;">
						<input type="button" value="刷新" onclick="location.reload()" />
					</div>
					<div style="clear: both;"></div>
					<div align="left">
						说明：
						<br />
						1，选择相应的工作流程对应的申请文件模板,下载并填写后再上传提交即可启动该流程
						<br />
					</div>
				</div>
				<table id="table" style="width: 100%" cellspacing="0" border="0">
					<thead style="height: 20px;">
						<tr>
							<td>
								工作流程名称
							</td>
							<td>
								查看流程图
							</td>
							<td>
								对应申请文件模板
							</td>
							<td>
								上传申请文件并启动流程
							</td>
						</tr>
					</thead>
					<s:iterator value="%{#request.pdatList}" var="pdat">
						<tr>
							<td>
								<s:property value="%{#pdat.processDefinitionKey}" />
							</td>
							<td>
								<a href="javascript:void(0)"
									onclick="openWindow(' ${pageContext.request.contextPath}/workflow/processDefinitionAction_showProcessImg.action?id=<s:property value="%{#pdat.processDefinitionId}"/>',800,500);">查看流程图</a>
							</td>
							<td>
								<a
									href="${pageContext.request.contextPath}/workflow/applyTemplateAction_download.action?templateId=<s:property  value="%{#pdat.templateId}"/>"><s:property
										value="%{#pdat.filename}" />
								</a>
							</td>
							<td>
								<form
									action="${pageContext.request.contextPath}/workflow/applyAction_start.action"
									method="post" enctype="multipart/form-data">
									<input type="file" name="upload" />
									<input type="hidden" name="processDefinitionId"
										value="<s:property value="%{#pdat.processDefinitionId}"/>"  />
									<input type="hidden" name="processDefinitionKey"
										value="<s:property value="%{#pdat.processDefinitionKey}"/>" />
									<input type="submit" value="启动" />
								</form>
							</td>
						</tr>
					</s:iterator>
				</table>

			</div>
		</div>
		<div id="tt"></div>
	</body>
</html>
