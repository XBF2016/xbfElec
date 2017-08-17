package com.xbf.elec.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecFunction;
import com.xbf.elec.domain.ElecRole;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.service.ElecFunctionService;
import com.xbf.elec.service.ElecRoleService;
import com.xbf.elec.service.ElecUserService;

//在权限管理机制中并不需要单独的角色action和权限action，因为角色和权限一旦设定好，日后的操作就几乎只要查找了，没必要专门给两个action
public class ElecAuthAction extends ActionSupport {
	// 需要三个service
	private ElecUserService userService;
	private ElecRoleService roleService;
	private ElecFunctionService functionService;
	private String roleId;
	private String functions;
	private String username;
	private String userId;
	private String roles;

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	// 保存角色分配
	public String updateUserRole() throws IOException {
		// 首先创建出所有roleId的对象，并放在一个Set集合中
		Set<ElecRole> roleSet = new HashSet<ElecRole>();
		String[] roleArray = roles.split(",");
		// 遍历数组
		for (String string : roleArray) {
			// 如果是roleId才创建对象
			if (string != null || string.trim().length() != 0) {
				ElecRole role = new ElecRole();
				role.setRoleId(string.trim());
				// 添加
				roleSet.add(role);
			}
		}
		// 更新:将userId和角色对象的集合传过去即可
		userService.update(userId, roleSet);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print("保存修改成功！");
		return null;// ajax
	}

	// 查询用户(ajax)
	public String queryUser() throws IOException {
		Conditions conditions = new Conditions();
		conditions.addCondition("isDelete", false, Operator.EQUAL);
		conditions.addCondition("username", username, Operator.LIKE);
		List<ElecUser> userList = userService.findByConditions(conditions);
		// 因为是ajax请求，
		Gson gson = new Gson();
		String userListJson = gson.toJson(userList);
		ServletActionContext.getResponse().setContentType(
				"text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(userListJson);
		return null;
	}

	// 保存用户权限的修改
	public String updateRole() throws IOException {
		// 首先创建出所有functionId的对象，并放在一个Set集合中
		Set<ElecFunction> functionSet = new HashSet<ElecFunction>();
		String[] funtionArray = functions.split(",");
		// 遍历数组
		for (String string : funtionArray) {
			// 如果是functionId才创建对象
			if (string != null || string.trim().length() != 0) {
				ElecFunction function = new ElecFunction();
				function.setFunctionId(string.trim());
				// 添加
				functionSet.add(function);
			}
		}

		// 更新:将roleId和权限对象的集合传过去即可
		roleService.update(roleId, functionSet);
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print("保存修改成功！");
		return null;// ajax
	}

	// 根据客户端传来的roleId查询其所有权限并将其id以字符串的形式发送过去
	public String findFunctionsByRoleId() throws IOException {
		// 查询：查到的是一个role，但是hibernate已经自动查询了中间表，并将所有权限放入了Set中
		ElecRole role = roleService.findFunctionsByRoleId(roleId);
		Set<ElecFunction> functions = role.getFunctions();
		if (functions != null) {
			StringBuilder stringBuilder = new StringBuilder(",");
			// 遍历
			for (ElecFunction function : functions) {
				// 拼接
				stringBuilder.append(function.getFunctionId()).append(",");
			}

			// 发送
			ServletActionContext.getResponse().getWriter().write(
					stringBuilder.toString());
		}
		return null;// ajax
	}

	// 提取数据用于访问roleHome页面时显示
	public String roleHome() {

		// 所有角色
		List<ElecRole> roleList = roleService.list();

		// 所有权限，按照存放
		Map<String, List<ElecFunction>> groupsMap = new HashMap<String, List<ElecFunction>>();
		// 首先查询所有权限
		List<ElecFunction> functions = functionService.list();
		// 对functions进行迭代（迭代要操作functions所以要判断非空），实现分组
		if (functions != null) {
			for (ElecFunction elecFunction : functions) {
				// 进行分组
				String groups = elecFunction.getGroups();
				// 拿出当前权限所在分组对应的List对象
				List<ElecFunction> tempList = groupsMap.get(groups);
				// 如果还没有就创建
				if (tempList == null) {
					tempList = new ArrayList<ElecFunction>();
				}
				// 添加到list里面
				tempList.add(elecFunction);
				// 更新
				groupsMap.put(groups, tempList);
			}
		}

		// 将所有角色数据转换json格式用于显示角色复选框
		Gson gson = new Gson();
		String roleListJson = gson.toJson(roleList);

		ServletActionContext.getRequest().setAttribute("roleList", roleList);
		ServletActionContext.getRequest().setAttribute("groupsMap", groupsMap);
		ServletActionContext.getRequest().setAttribute("roleListJson",
				roleListJson);
		return "roleHome";
	}

	public synchronized ElecUserService getUserService() {
		return userService;
	}

	public synchronized void setUserService(ElecUserService userService) {
		this.userService = userService;
	}

	public synchronized ElecRoleService getRoleService() {
		return roleService;
	}

	public synchronized void setRoleService(ElecRoleService roleService) {
		this.roleService = roleService;
	}

	public synchronized ElecFunctionService getFunctionService() {
		return functionService;
	}

	public synchronized void setFunctionService(
			ElecFunctionService functionService) {
		this.functionService = functionService;
	}

	public synchronized String getRoleId() {
		return roleId;
	}

	public synchronized void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public synchronized String getFunctions() {
		return functions;
	}

	public synchronized void setFunctions(String functions) {
		this.functions = functions;
	}

	public synchronized String getUserId() {
		return userId;
	}

	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}

	public synchronized String getRoles() {
		return roles;
	}

	public synchronized void setRoles(String roles) {
		this.roles = roles;
	}

}
