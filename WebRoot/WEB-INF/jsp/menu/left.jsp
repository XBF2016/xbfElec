<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Left</title>
		<style type="text/css">
				body {
					MARGIN: 0px; BACKGROUND-COLOR: #8ba7e3;
				}
				div table {
					border-collapse: 0px;border-spacing: 0px;width: 100%;border:3px;
				}
				th , td ,body{
					COLOR: #000000
				}
				img{
					 width:9px; height:9px;  border:0px ;margin-left: 5px;
				}
				.icon{
				
					background-image:url('${pageContext.request.contextPath }/images/b-info.gif')
				}
		</style>
		<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
		
		<script type="text/javascript"  src="${pageContext.request.contextPath}/script/jquery-1.8.3.js"></script>
		<script language="Javascript" type="text/javascript">
		
		//鼠标滑过变色
		$(document).ready(function(){
			$(".box05").mouseover(function(){
				
				this.style['background-color'] = "white";
				
			}).mouseout(function(){
				
				this.style['background-color'] = "";
				
			});

			//链接点击变色
			$(".cl_01").click(function(){
				$(".cl_01").css('color','');
				this.style.color='red';
			});
       });

		//展开关闭部分导航栏
		function expand(id){
			$("#"+id+"Child").toggle();
		}
</script>
	</head>
	<body class="bodyscroll">
		<table height="100%" cellspacing="0" cellpadding="0" width="143" border="0">
			<tbody>
				<tr>
					<td valign="top" bgColor="#F6F6F6" height="100%">
						<div class="parent" id="KB0Parent">
							<table>
								<tbody>
									<tr height=25 >
										<td class="icon"  >
											 <img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB0"  /> 
											 <a class="cl" onclick="expand('KB0'); return false" href="#"> 技术设施维护管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="child" id="KB0Child">
							<table >
								<tbody>
								   
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/equapment/equapmentIndex.jsp" target="mainFrame">仪器设备管理</a>
										</td>
									</tr>
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/equapment/adjustIndex.jsp" target="mainFrame">设备校准检修</a>
										</td>
									</tr>
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/equapment/planIndex.jsp" target="mainFrame">设备购置计划</a>
										</td>
									</tr>
									
									
								</tbody>
							</table>
						</div>
                       

                      
						<div class="parent" id="KB1Parent">
							<table>
								<tbody>
									<tr height=25>
										<td class="icon" >
											 <img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB1"  />
											  <a class="cl" onclick="expand('KB1'); return false" href="#"> 技术资料图纸管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="child" id="KB1Child">
							<table cellspacing="0" cellpadding="0" width="99%" border="0" >
								<tbody>
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/dataChart/dataChartIndex.jsp" target="mainFrame">资料图纸管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
                      
                      
                      
						<div class="parent" id="KB2Parent">
							<table>
								<tbody>
									<tr height=25>
										<td class="icon" >
											 <img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB2"  />
											  <a class="cl" onclick="expand('KB2'); return false" href="#"> 站点设备运行管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="child" id="KB2Child">
							<table >
								<tbody>
								    
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/siteEquapment/siteInfoIndex.jsp" target="mainFrame">站点基本信息</a>
										</td>
									</tr>
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/siteEquapment/siteRunIndex.jsp" target="mainFrame">运行情况</a>
										</td>
									</tr>
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/siteEquapment/siteMaintainIndex.jsp" target="mainFrame">维护情况</a>
										</td>
									</tr>
								   
								</tbody>
							</table>
						</div>
                       


                      
						<div class="parent" id="KB3Parent">
							<table ><tbody><tr height=25><td class="icon" >
											 <img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB3"  />
											  <a class="cl" onclick="expand('KB3'); return false" href="#"> 监测台建筑管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="child" id="KB3Child">
							<table >
								<tbody>
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/building/buildingIndex.jsp" target="mainFrame">监测台建筑管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
                      
 
                   
						<div class="parent" id="KB4Parent">
							<table>
								<tbody>
									<tr height=25>
										<td class="icon" >
											 <img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB4"  />
											  <a class="cl" onclick="expand('KB4'); return false" href="#"> 系统管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="child" id="KB4Child">
							<table >
								<tbody>
								
								    
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/system/userAction_userHome.action" target="mainFrame">用户管理</a>
										</td>
									</tr>
									
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/system/authAction_roleHome.action" target="mainFrame">角色管理</a>
										</td>
									</tr>
									
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/system/matterAction_home.action" target="mainFrame">待办事宜</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					    <div class="parent" id="KB5Parent">
							<table >
								<tbody>
									<tr height=25>
										<td class="icon" align="left" valign="middle"> 
											<img src="${pageContext.request.contextPath }/images/add.gif" name="imgKB5"/>
											 <a class="cl" onclick="expand('KB5'); return false" href="#"> 工作流程管理</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					    <div class="child" id="KB5Child">
							<table >
								<tbody>
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath}/workflow/processDefinitionAction_home.action" target="mainFrame">审批流程管理</a>
										</td>
									</tr>
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath}/workflow/applyTemplateAction_home.action" target="mainFrame">申请模板管理</a>
										</td>
									</tr>
									
									
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/workflow/applyAction_home.action" target="mainFrame">起草申请</a>
										</td>
									</tr>
									
									<tr>
										<td class="box06"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/workflow/applyAction_applyMy.action" target="mainFrame">我的申请查询</a>
										</td>
									</tr>
									<tr>
										<td class="box05"  >
											<a class="cl_01"  href="${pageContext.request.contextPath }/workflow/taskAction_taskMy.action" target="mainFrame">待我审批</a>
										</td>
									</tr>
									
									
								</tbody>
							</table>
						</div>
			</td>
			</tr>
			</tbody>
			</table>
	</body>