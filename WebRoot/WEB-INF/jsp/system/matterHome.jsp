<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"  %>

<html>
<head>
<title>待办事宜编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<style>
			form {
				margin: 0;
			}
			textarea {
				display: block;
			}
		</style>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/util.js"></script>
		<script>
			KindEditor.ready(function(K) {
        K.create('#stationRunStatus', {
        	   'items':[
						        'source', '|', 'undo', 'redo', '|', 'preview',  'template', 'cut', 'copy', 'paste',
						        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
						        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
						        'insertfile', 'table', 'hr', 'emoticons', 'map', 'code', 'pagebreak',
						        'link', 'unlink'
		                ],
                uploadJson : '${pageContext.request.contextPath}/KindEditorServlet',
                fileManagerJson : '${pageContext.request.contextPath}/kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
        });
         K.create('#devRunStatus', {
        	    'items':[
						        'source', '|', 'undo', 'redo', '|', 'preview',  'template', 'cut', 'copy', 'paste',
						        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
						        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
						        'insertfile', 'table', 'hr', 'emoticons', 'map', 'code', 'pagebreak',
						        'link', 'unlink'
		                ],
                uploadJson : '${pageContext.request.contextPath}/KindEditorServlet',
                fileManagerJson : '${pageContext.request.contextPath}/kindeditor/jsp/file_manager_json.jsp',
                allowFileManager : true
        });
});

		</script>
</head>

<body>
<form name="Form1" id="Form1" method="post"  onclick="showProc()" action="${pageContext.request.contextPath}/system/matterAction_add.action">

	<table cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>待办事宜编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">站点运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	
			<textarea name="stationRunStatus" id="stationRunStatus"   style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('stationRun');"></textarea>
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">设备运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<textarea name="devRunStatus" id="devRunStatus"  style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('devRun');"></textarea>
			</td>
			
		</tr>
        <tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
			<input type="submit" value="保存" style="font-size:12px; color:black; height=20;width=50">
			</td>
		</tr>
	</table>
	　 
</form>
<form name="Form2" id="Form2"  method="post">
	<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
		<TBODY>
			<TR height=10><td></td></TR>			
			<tr>
			  	<td>
	                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
									<TR>
										<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
										<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">代办事宜列表</TD>
									</TR>
		             </TABLE>
                  </td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>			
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">站点运行情况</td>
								<td align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备运行情况</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">创建日期</td>
							</tr>
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="HEIGHT:22px" align="center" width="40%">
									${request.matter.stationRunStatus}
								</td>
								<td style="HEIGHT:22px" align="center" width="40%">
									${request.matter.devRunStatus}
								</td>
								<td style="HEIGHT:22px" align="center" width="20%">
								<s:property value="#request.matter.createDate"/><br>
								</td>
							</tr>
						</table>
					</td>
				</tr>        
		</TBODY>
	</table>
</form>
</body>
</html>