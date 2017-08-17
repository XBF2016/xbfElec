<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet" />
		<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

body,td,th {
	color: #000000;
}
-->
</style>
		<style>
BODY {
	SCROLLBAR-FACE-COLOR: #cccccc;
	SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF;
	SCROLLBAR-SHADOW-COLOR: #ffffff;
	SCROLLBAR-3DLIGHT-COLOR: #cccccc;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-TRACK-COLOR: #ffffFF;
	SCROLLBAR-DARKSHADOW-COLOR: #cccccc;
}
</style>
		<link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet" type="text/css">
	</head>

	<body>

		<form name="Form1" method="post" action="name.aspx" id="Form1">

			<table width="100%" border="0" height="88" border="1"
				background=" ${pageContext.request.contextPath }/images/back1.jpg">
				<tr>
					<td colspan=3 class="ta_01" align="center"
						background="${pageContext.request.contextPath }/images/b-info.gif">
						<strong>系统首页</strong>
					</td>
				</tr>

				<tr>
					<td width="30%" height="84" align="left" valign="top">
						<fieldset
							style="width: 259px; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG">
							<legend>
								<font color="#0000FF"> <img border="0"
										src="${pageContext.request.contextPath }/images/zoom.gif"
										width="14" height="14"> 校准提醒</font>
							</legend>
							<IFRAME
								src="${pageContext.request.contextPath }/menu/menuAction_alermXZ.action"
								name="alarmx" id="alarmx" frameBorder=0 width=270 scrolling=auto
								height=170></IFRAME>
						</fieldset>
					</td>

					<td width="30%" height="84" align="left" valign="top" >
						<fieldset
							style="width: 259px; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG">
							<legend>
								<font color="#0000FF"> <img border="0"
										src="${pageContext.request.contextPath }/images/zoom.gif"
										width="14" height="14"> 检修提醒</font>
							</legend>
							<IFRAME
								src="${pageContext.request.contextPath }/menu/menuAction_alermJX.action"
								name="alarmj" id="alarmj" frameBorder=0 width=270 scrolling=auto
								height=170></IFRAME>
						</fieldset>
					</td>

					<td width="30%" align="left" valign="top" rowspan="2">
						<fieldset style="width: 275px; height: 340px; padding: 1">
							<legend>
								<font color="#0000FF"> <img border="0"
										src="${pageContext.request.contextPath }/images/zoom.gif"
										width="14" height="14"> 验收提醒</font>
							</legend>
							<IFRAME 
								src="${pageContext.request.contextPath }/menu/menuAction_alermYS.action"
								name="project" id="project" frameBorder=0 width=270
								scrolling=auto height=340></IFRAME>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td width="30%" height="84" align="left" valign="top">
						<fieldset
							style="width: 259px; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG">
							<legend>
								<font color="#0000FF"> <img border="0"
										src="${pageContext.request.contextPath }/images/zoom.gif"
										width="14" height="14"> 站点运行情况</font>
							</legend>

							<IFRAME
								src="${pageContext.request.contextPath }/system/matterAction_alermZD.action"
								name="station" id="station" frameBorder=0 width=270
								scrolling=auto height=170></IFRAME>

						</fieldset>


					</td>
					<td width="30%" height="84" align="left" valign="top">
						<fieldset
							style="width: 259px; height: 200px; padding: 1 background:${pageContext.request.contextPath }/images/back1.JPG">
							<legend>
								<font color="#0000FF"> <img border="0"
										src="${pageContext.request.contextPath }/images/zoom.gif"
										width="14" height="14"> 设备运行情况</font>
							</legend>
							<IFRAME
								src="${pageContext.request.contextPath }/system/matterAction_alermSB.action"
								name="dev" id="dev" frameBorder=0 width=270 scrolling=auto
								height=170></IFRAME>
						</fieldset>
					</td>
					</tr>
					
				<tr>
				
					<td height=2></td>
				</tr>

			</table>

		</form>

	</body>

</html>
