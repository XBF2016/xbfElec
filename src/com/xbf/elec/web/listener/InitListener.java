package com.xbf.elec.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xbf.elec.util.Dictionary;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		// 当项目初始化时就实例化字典对象并同时调用它的init方法读取字典数据
		Dictionary dict = Dictionary.getInstance();
		// 将字典对象放入web域中以供访问
		arg0.getServletContext().setAttribute("dict", dict);

	}

}
