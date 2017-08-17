<%@ page language="java" pageEncoding="utf-8"%>


<html>
	<head>
		<title>部署流程定义</title>

		<link href="${pageContext.request.contextPath }/css/Style.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/script/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function deployProcess() {
	$("#form1").submit();
	alert("上传成功");
}
</script>
	</head>
	<body>
		<form id="form1" method="post" enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/workflow/processDefinitionAction_deployment.action">

			<div style="margin-top: 50px">
				<div align="center"
					style="background-image: url('${pageContext.request.contextPath }/images/b-info.gif')">
					<h2>
						部署流程定义
					</h2>
				</div>
				<div>
					请选择流程定义文档 :
					<input type="file" name="upload" />
					( 只接受zip文件 )
				</div>
				<div align="center" style="margin-top: 50px;">
					<input type="button" value="上传并部署" onclick="deployProcess()">
					<input type="button" value="关闭" onclick=" window.close();" />
				</div>
			</div>
		</form>

	</body>
</html>
