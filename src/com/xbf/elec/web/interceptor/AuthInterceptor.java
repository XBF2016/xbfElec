package com.xbf.elec.web.interceptor;

import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xbf.elec.domain.ElecFunction;
import com.xbf.elec.domain.ElecRole;
import com.xbf.elec.domain.ElecUser;

public class AuthInterceptor extends AbstractInterceptor {
	// 设置白名单
	private Set<String> whitePath = new HashSet<String>();

	@Override
	// 初始化白名单
	public void init() {
		whitePath.add("userAction_login");
		whitePath.add("userAction_logout");
		whitePath.add("menuAction_left");
		whitePath.add("menuAction_title");
		whitePath.add("menuAction_loading");
		whitePath.add("menuAction_alermXZ");
		whitePath.add("menuAction_alermJX");
		whitePath.add("menuAction_alermYS");
		whitePath.add("matterAction_alermSB");
		whitePath.add("matterAction_alermZD");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		boolean mark = true;
		if (mark) {
			return actionInvocation.invoke();
		}
		ActionContext actionContext = actionInvocation.getInvocationContext();
		// 获取请求路径
		String actionName = actionContext.getName();

		// 白名单放行
		if (whitePath.contains(actionName)) {
			return actionInvocation.invoke();
		}

		// 获取用户
		ElecUser user = (ElecUser) actionContext.getSession().get("user");

		// 如果不在白名单中又未登录，拦截
		if (user == null) {
			return "authError";
		}
		// 获取角色
		Set<ElecRole> roles = user.getRoles();
		if (roles != null) {
			for (ElecRole elecRole : roles) {
				// 获取权限
				Set<ElecFunction> functions = elecRole.getFunctions();
				if (functions != null) {
					for (ElecFunction elecFunction : functions) {
						// 如果请求的路径匹配就放行
						if (actionName.equals(elecFunction.getPath())) {
							return actionInvocation.invoke();
						}
					}
				}
			}
		}

		return "authError";
	}
}
