<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title>新增申请模板</title>
		<link href="${pageContext.request.contextPath }/css/Style.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript"
			src="${pageContext.request.contextPath }/script/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function uploadTemplate() {
	$("#form1").submit();
	alert("请求已发送");
	opener.location.reload();
	window.close();
}
</script>
	</head>
	<body>
		<form id="form1"
			action="${pageContext.request.contextPath }/workflow/applyTemplateAction_upload.action"
			method="post" enctype="multipart/form-data">
			<br>
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif"
						colspan="4">
						<font face="宋体" size="2"><strong>文档模板信息</strong> </font>
					</td>
				</tr>

				<tr height=10>
					<td colspan=4></td>
				</tr>

				<tr>
					<td width="25%" align="center" bgColor="#f5fafe" class="ta_01">
						所用流程：
						<font color="#FF0000">*</font>
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
                   <s:select  name="processDefinitionKey" list="%{#request.processDefinitionList}" listKey="key" listValue="key"></s:select>
						<!-- 
			         	<select name="processDefinitionKey" id="processDefinitionKey" style="width:155px">
						    <option>员工请假流程</option>
						    <option>设备购置计划流程</option>
						    <option>设备费用报销流程</option>
						</select>
					 -->
					</td>
				</tr>

				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						请选择模板文件(doc格式)：
						<font color="#FF0000">*</font>
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="file" name="upload" style="width: 450px;" />
					</td>
				</tr>
				<tr height=50>
					<td colspan=4></td>
				</tr>
				<tr height=2>
					<td colspan=4
						background="${pageContext.request.contextPath }/images/b-info.gif"></td>
				</tr>
				<tr height=10>
					<td colspan=4></td>
				</tr>
				<tr>
					<td align="center" colspan=4>
						<input type="button" value="上传"
							style="font-size: 12px; color: black;" onclick="uploadTemplate()" />
						<input type="button" value="关闭" onclick=" window.close();"
							style="width: 60px; font-size: 12px; color: black;" />
					</td>
				</tr>
			</table>
		</form>

	</body>
</html>
