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
	line-height: 5px;
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
					申请模板管理
				</div>
				<div style="height: 10px"></div>
				<div>
					<div
						style="float: left;width:130px;background-image: url('${pageContext.request.contextPath }/images/cotNavGround.gif')">
						<img src="${pageContext.request.contextPath }/images/yin.gif"
							width="15" />
						模板列表
					</div>
					<div align="right" style="float: right;">
						<input type="button" value="刷新" onclick="location.reload()" />
						&nbsp;&nbsp;
						<input type="button" value="上传申请模板"
							onclick="openWindow('${pageContext.request.contextPath }/workflow/applyTemplateAction_addPage.action','700','400')" />
					</div>
					<div style="clear: both;"></div>
					<div align="left">
						说明：
						<br />
						1，删除时，相应的文件也被删除。
						<br />
						2，下载时，文件名默认为：{表单模板名称_account}.doc。
						<br />
					</div>
				</div>
				<table id="table" style="width: 100%" cellspacing="0" border="0">
					<thead style="height: 20px;">
						<tr>
							<td>
								模板名称
							</td>
							<td>
								所属流程定义
							</td>
							<td>
								操作
							</td>
						</tr>

					</thead>
					<s:iterator value="%{#request.applyTemplateList}"
						var="applyTemplate">
						<tr>
							<td>
								<s:property value="%{#applyTemplate.filename}" />
							</td>
							<td>
								<s:property value="%{#applyTemplate.processDefinitionKey}" />
							</td>
							<td>
								<a
									href="${pageContext.request.contextPath}/workflow/applyTemplateAction_download.action?templateId=<s:property value="%{#applyTemplate.templateId}"/>">下载</a>
								<a
									href="${pageContext.request.contextPath}/workflow/applyTemplateAction_delete.action?templateId=<s:property value="%{#applyTemplate.templateId}"/>"
									onclick="return confirm('你确定要删除 <s:property value="%{#applyTemplate.filename}" />？')"> <img
										src="${pageContext.request.contextPath }/images/delete.gif" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</table>

			</div>
		</div>
	</body>
</html>
