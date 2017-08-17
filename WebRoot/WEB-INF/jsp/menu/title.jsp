<%@ page language="java"  pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<TITLE>Top</TITLE>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/util.js"></script>

<STYLE type=text/css>BODY {
	FONT-SIZE: 12px; MARGIN: 0px; COLOR: #000000; BACKGROUND-COLOR: #ffffff
}
TD {
	FONT-SIZE: 12px; COLOR: #000000
}
TH {
	FONT-SIZE: 12px; COLOR: #000000
}
</STYLE>
<SCRIPT type="text/javascript">
function submitrequest(action){
eval("document.location='"+action+"'");
}

</SCRIPT>
</HEAD>
<BODY>
<FORM id=Form1 name=Form1  method=post>
<table border="0" width="100%" height="9" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" colspan="4" background="${pageContext.request.contextPath }/images/title.jpg" height="58">　</td>
  </tr>
  <tr>
    <td width="100%" height="1" bgcolor="#66C89C" colspan="4"><img border="0" src="${pageContext.request.contextPath }/images/titleline.jpg" width="100%" height="3"></td>
  </tr>
  <tr>
    <td width="20%" height="19" bgcolor="#0965CA">
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15%" align="center"><font color="#FFFFFF"><b>|</b></font></td>
        <td width="70%" align="center">
        <FONT color=#FFFFFF>
            <SCRIPT language=JavaScript>
										document.write(formatDate(new Date(),"yyyy年MM月dd日"));
			</SCRIPT>
            </FONT></td>
        <td width="15%" align="center"><font color="#FFFFFF"><b>|</b></font></td>
      </tr>
    </table>
    
    <td width="16%" height="19" bgcolor="#0965CA">
    <table cellSpacing="2" height="19" cellPadding="0" width="100%" border="0" ><tr><td valign="top">
    <font color="#FFFFFF">欢迎您!&nbsp;&nbsp;&nbsp;${requestScope.user.username}</font>
    </td></tr></table>
    </td>
    <td width="46%" height="19" bgcolor="#0965CA">　</td>
    <td width="18%" height="19" bgcolor="#0965CA" align="center">
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%" align="center">
          <A href="${pageContext.request.contextPath }/menu/menuAction_loading.action" target="mainFrame"><font color="#FFFFFF">返回首页</font></a>
          <font color="#FFFFFF"><b>|</b></font>
          <A href="${pageContext.request.contextPath}/menu/menuAction_index.action"   target="_top"> <font color="#FFFFFF">重新登录</font></A>
          <font color="#FFFFFF"><b>|</b></font>
           <A href="javascript:parent.exitsys()" target="_top"> <font color="#FFFFFF">退出系统</font></A>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</FORM>
</BODY>
</HTML>
