<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title>角色权限管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.8.3.js"></script>
		<script type="text/javascript">
		function selectRole(){
		var selectValue = $("#roles").val();
		if(!selectValue){
			//拿到所有权限复选框,全部取消选中
			$("[name='functions']").each(function(index , domElement){
				$(domElement).attr('checked',null);
			});
		}else{
			//根据roleId请求该角色的所有权限
			$.post("${pageContext.request.contextPath }/system/authAction_findFunctionsByRoleId.action",{'roleId':selectValue,'timestamp':new Date().getTime()},function(data){
				//拿到所有复选框，判断其是否在data中
				$("[name='functions']").each(function(index , domElement){
					//拿到复选框的value
					var value=$(domElement).val();
					//如果在data中置为选中，否则置为未选中
					if(data.indexOf(value)>-1){        
				       $(domElement).attr('checked','checked');
				    }else{
				    	$(domElement).attr('checked',null);
				    }
			});
			});
		}
	}
			
	function updateRole(){
		//拿到roleId和所有被选中的复选框的id（functions）
		var roleId=$('#roles').val();
		//拼接functionId
		var functions="";
		
		$("[name='functions']").each(function(index , domElement){
				if($(domElement).attr('checked')){
					functions +=   $(domElement).val()+",";
				}
		});
		
		$.post("${pageContext.request.contextPath }/system/authAction_updateRole.action",{'roleId':roleId,'functions':functions,'timestamp':new Date().getTime()},function(data){
			alert(data);
		});
	}
	
	//获取所有角色的json数据
	var roleListJson=<s:property value="%{#request.roleListJson}" escapeHtml="false"/>;
	
	//根据用户名模糊查询用户(ajax)
	function queryUser(){
		var username=$('#username').val();//用户名
		if(!username){
			return;
		}
		$.post("${pageContext.request.contextPath }/system/authAction_queryUser.action",{'username':username,'timestamp':new Date().getTime()},function(data){
			initTable(data);
		});
	}
	

	
	function initTable(userListJson){
		//先清空旧数据
		$("#userRoleTable").empty();
		for(var i in userListJson){
			var user = userListJson[i];
			var html = "";
			html += '<tr><td>'+user.username+':</td><td>';
			html += '<form method="post" id="form'+i+'">';
			html += '	<input type="hidden" name="userId" value="'+user.userId+'" />';
			for(var j in roleListJson){
				var role = roleListJson[j];
				//标记,表示当前用户是否拥有当前角色
				var mark = false;
				//当前用户所拥有的角色
				var userRoleArray = user.roles;
				if(userRoleArray){
					for(var m in userRoleArray){
						var userRole = userRoleArray[m];
						if(userRole.roleId == role.roleId){
							mark = true;
							break;
						}
					}
				}
				if(mark){
					html += ' <input type="checkbox" name="roles" value="'+role.roleId+'" checked="checked" />'+role.roleName;
				}else{
					html += ' <input type="checkbox" name="roles" value="'+role.roleId+'"  />'+role.roleName;	
				}
			}
			html += ' </form>';
			html += '	</td><td><button onclick="updateUserRole('+i+')">保存修改</button></td></tr>';
			
			$("#userRoleTable").append(html);
		}
	}
	
	//接下来提交被点击保存的表单的数据，使用ajax获取form表单的数据提交
	function updateUserRole(i){ //i用于确定是第几个表单
		//获取表单的数据
		var queryString = $("#form"+i).serialize();
	    alert(queryString);
		$.post("${pageContext.request.contextPath }/system/authAction_updateUserRole.action?"+queryString,{'timestamp':new Date().getTime()},function(data){
		});
	}
	
	
	
	
	
   </script>
	
</head>
	<body>
 	<div align="center" >
 <div  align="center"  style="width: 90%;background-color: #f5fafe;height: 100%" >

   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;color:#023726;font-size: 12px;min-height: 40%">
   	<legend align="left">
   			权限分配
   			<s:select id="roles" list="%{#request.roleList}"  listKey="roleId" listValue="roleName" emptyOption="true" onChange="selectRole();"></s:select>
   			<!-- 
			<select name="" id="selectRole" style="width:100px" onchange="selectRole()">
    <option value=""></option>
    <option value="005">超级管理员</option>
    <option value="004">系统管理员</option>
    <option value="003">经理</option>
    <option value="002">项目组长</option>
    <option value="001">普通员工</option>
</select>
 -->

	</legend>
	<legend align="right">
			<button onclick="updateRole();">保存修改</button>
	</legend>
 
     <table cellSpacing="0" cellPadding="0"border="0">
     
     		<s:iterator value="%{#request.groupsMap}" var="groupsEntry">
     			<tr>
     				<td class="ta_01"  align="right" width="10%"  style="font-weight: bold">
     					<s:property  value="%{#groupsEntry.key}" />
     				</td>
     				<td class="ta_01" align="left" width="90%"  >
     				<s:checkboxlist name="functions"  id="functions" list="%{#groupsEntry.value}"  listKey="functionId" listValue="functionName"></s:checkboxlist>
     				</td>
     			</tr>
 </s:iterator>    		
		 </table>	
        </fieldset>
        
        <br/>
        
         <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;color:#023726;font-size: 12px;min-height: 40%">
			   	<legend align="left">
			   			角色分配
						<input id="username" type="text"  size="15" name="username" />
						<button onclick="queryUser()">查询用户名</button>
				</legend>
			 
			     <table id="userRoleTable" cellSpacing="0" cellPadding="0"border="0">
			     
				 </table>	
		 </fieldset>
</div>
</div>		    				    
	</body>
</HTML>
