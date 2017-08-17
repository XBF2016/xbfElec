<%@ page language="java" pageEncoding="UTF-8"%>


<html>
<head>
<title>国家电力监测中心首页</title>
<link href="${pageContext.request.contextPath }/css/Font.css" type="text/css" rel="stylesheet">

<style>body {
	SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-BASE-COLOR: #dee3f7
}
</style>

<script type="text/javascript">
function submitrequest(action){
eval("document.location='"+action+"'");
}

function exitsys(){
    window.close();   
 }
</script>
</head>

<frameset border=0 frameSpacing=0 rows=82,* frameBorder="no" >
	<frame  src="${pageContext.request.contextPath }/menu/menuAction_title.action" noResize scrolling=no   />
	
	<frameset  border=0 frameSpacing=0 frameBorder="no"   cols=143,* >
		<frame  name="left" src="${pageContext.request.contextPath }/menu/menuAction_left.action" />
		<frame name="mainFrame" src="${pageContext.request.contextPath }/menu/menuAction_loading.action"  />
	</frameset>
</frameset>


</html>
