package com.xbf.elec.web.action;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import utils.Md5Utils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.service.ElecUserService;
import com.xbf.elec.util.DataTablesPage;
import com.xbf.elec.util.Dictionary;
import com.xbf.elec.util.PoiUtil;

public class ElecUserAction extends ActionSupport implements
		ModelDriven<ElecUser> {
	private ElecUserService userService;
	private ElecUser user = new ElecUser();
	private String sEcho;
	private int iDisplayStart;
	private int iDisplayLength;
	private String checkNumber;
	private String rememberMe;
	private boolean isSucc = false;

	// 登录
	public String login() {
		// 验证码的检查
		// 从session中获取到来自checkNumber.jsp的验证码
		/*
		 * String sessionCheckNumber = (String)
		 * ServletActionContext.getRequest()
		 * .getSession().getAttribute("checkNumber"); if
		 * (!sessionCheckNumber.equals(checkNumber)) { addActionError("验证码错误");
		 * return "loginError"; }
		 */
		/*
		 * // 登录前的数据有效性检查 if (user.getAccount() == null ||
		 * user.getAccount().trim().length() == 0) { addActionError("账户不能为空");
		 * return "loginError"; } if (user.getPassword() == null ||
		 * user.getPassword().trim().length() == 0) { addActionError("密码不能为空");
		 * return "loginError"; }
		 * 
		 * // 查询之前保存用户名和密码 String account = user.getAccount(); String password =
		 * user.getPassword();
		 * 
		 * // 添加查询条件 Conditions conditions = new Conditions(); //
		 * 注意，查询的用户必须是未被假删除的 conditions.addCondition("isDelete", false,
		 * Operator.EQUAL); conditions.addCondition("account",
		 * user.getAccount(), Operator.EQUAL);
		 * conditions.addCondition("password", user.getPassword(),
		 * Operator.EQUAL); user = userService.login(conditions); //
		 * 如果查到了先设置标记//就将user放入session中表示登录 if (user != null) { isSucc = true; }
		 * else { //
		 * 查不到有两种情况，要么是用户名或者密码错误，要么是密码对，但是是明文与数据库的密文不匹配，这个时候需要再次查询（查询条件中密码应该是密文）
		 * conditions = new Conditions(); // 注意，查询的用户必须是未被假删除的
		 * conditions.addCondition("isDelete", false, Operator.EQUAL);
		 * conditions.addCondition("account", account, Operator.EQUAL);
		 * conditions.addCondition("password", Md5Utils.Md5(password),
		 * Operator.EQUAL); user = userService.login(conditions); if (user !=
		 * null) { isSucc = true; } }
		 * 
		 * // 根据标志进行登录或者登录失败的跳转 if (isSucc) {
		 * ServletActionContext.getRequest().getSession().setAttribute("user",
		 * user);
		 * 
		 * // 登录成功才能发送cookie到客户端 // 当用户点击记住我才发送cookie if
		 * ("yes".equals(rememberMe)) {
		 * 
		 * Cookie accountCookie = new Cookie("account", user.getAccount()); //
		 * 设置生命周期 accountCookie.setMaxAge(60 * 60 * 24 * 7); // 设置路径
		 * accountCookie.setPath("/"); // 登录成功才能发送cookie到客户端 Cookie
		 * passwordCookie = new Cookie("password", user .getPassword()); //
		 * 设置生命周期 passwordCookie.setMaxAge(60 * 60 * 24 * 7); // 设置路径
		 * passwordCookie.setPath("/"); // 将cookie发送过去
		 * ServletActionContext.getResponse().addCookie(accountCookie);
		 * ServletActionContext.getResponse().addCookie(passwordCookie); }
		 * return "loginSucc"; } else { addActionError("用户名或者密码错误"); return
		 * "loginError"; }
		 */

		return "loginSucc";
	}

	// 保存修改
	public String update() {
		// 还有客户端和服务器端的数据校验
		// 更新的数据中，密码有可能不变，还是原来的加密密码，也有可能变了，变成明文，这个时候就要加密
		// 如何判断变没变？查询
		ElecUser user2 = userService.findById(user.getUserId());
		// 如果密码不匹配才需要加密
		if (!user2.getPassword().equals(user.getPassword())) {
			user.setPassword(Md5Utils.Md5(user.getPassword()));
		}
		userService.update(user);
		return "update";
	}

	// 编辑用户
	public String userEdit() {
		// 覆盖原对象
		user = userService.findById(user.getUserId());
		// 为了便于取值，将user压栈
		ActionContext.getContext().getValueStack().pop();
		ActionContext.getContext().getValueStack().push(user);
		return "userEdit";
	}

	// 删除用户
	public String delete() {
		userService.delete(user);
		return "delete";
	}

	public String page() throws IOException {
		DataTablesPage<ElecUser> page = new DataTablesPage<ElecUser>();
		// 给氧气
		page.setSEcho(sEcho);
		page.setIDisplayStart(iDisplayStart);
		page.setIDisplayLength(iDisplayLength);
		// 条件
		Conditions conditions = new Conditions();
		conditions.addCondition("username", user.getUsername(), Operator.LIKE);
		userService.page(conditions, page);
		// 将page对象生成为json格式的数据
		Gson gson = new Gson();
		String jsonResult = gson.toJson(page);
		// 因为是ajax请求，所以要直接响应，不能使用struts导航
		ServletActionContext.getResponse().getWriter().write(jsonResult);
		System.out.println(jsonResult);
		return null;
	}

	// 跳转到用户管理页面
	public String userHome() {
		return "userHome";
	}

	// 跳转到用户添加页面
	public String userAdd() {
		return "userAdd";
	}

	// 添加用户
	public String add() {
		System.out.println("呵呵");
		// 添加时进行MD5加密
		this.user.setPassword(Md5Utils.Md5(this.user.getPassword()));
		userService.add(this.user);
		return "add";
	}

	// 检查账号唯一性，如果不唯一给出提示
	public String checkAccountUnique() throws IOException {
		boolean unique = userService.checkAccountUnique(user);
		// 当使用ajax进行部分数据校验时，响应在原来的页面上显示，不用转发
		ServletActionContext.getResponse().getWriter().write(unique + "");
		return null;
	}

	// 导出用户报表
	public String exportExcel() throws IOException {
		// 首先要根据用户输入的用户名查询出用户数据
		Conditions conditions = new Conditions();
		conditions.addCondition("username", user.getUsername(), Operator.EQUAL);
		conditions.addCondition("isDelete", false, Operator.EQUAL);
		List<ElecUser> userList = userService.findByConditions(conditions);

		// 总的数据数
		short rowNum = 1;

		if (userList != null) {
			rowNum += userList.size();
		}

		// 数据
		String[][] data = new String[rowNum][3];

		// 第一行的数据
		String[] titleRowData = new String[] { "用户登录账号", "用户名", "性别" };
		data[0] = titleRowData;

		if (userList != null) {
			for (short i = 0; i < userList.size(); i++) {
				ElecUser user = userList.get(i);
				String[] rowData = new String[3];
				rowData[0] = user.getAccount();
				rowData[1] = user.getUsername();
				rowData[2] = Dictionary.getInstance().getItemMap().get(
						user.getGender());
				data[i + 1] = rowData;
			}
		}

		// 创建输出流（解释：由于struts的下载功能需要输入流，而poi的导出需要输出流，故要使用servlet）
		// 设置下载有关的参数
		ServletActionContext.getResponse().setContentType(
				"application/vnd.ms-excel");
		ServletActionContext.getResponse().setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode("用户表.xls", "utf-8"));
		OutputStream outputStream = ServletActionContext.getResponse()
				.getOutputStream();

		// 使用工具类导出报表
		PoiUtil.exportExcel(data, outputStream);

		return null;

	}

	// 统计各单位人数的图表
	public String exportChart() throws IOException {
		// 获取用户数据
		Conditions conditions = new Conditions();
		conditions.addCondition("isDelete", false, Operator.EQUAL);
		List<ElecUser> userList = userService.findByConditions(conditions);

		// 加工数据
		Map<String, Integer> data = new HashMap<String, Integer>();
		if (userList != null) {
			for (ElecUser user : userList) {
				Integer count = data.get(user.getUnits());
				if (count == null) { // 最开始value为null，故要初始化
					count = 0;
				}
				count++;
				data.put(user.getUnits(), count);
			}
		}

		// 准备数据
		DefaultPieDataset dataset = new DefaultPieDataset();
		// 填充数据
		Set<Entry<String, Integer>> entrySet = data.entrySet();
		if (entrySet != null) {
			for (Entry entry : entrySet) {
				String unit = (String) entry.getKey();
				if (unit == null) {
					unit = "??";
				}
				int count = (Integer) entry.getValue();
				dataset.setValue(Dictionary.getInstance().getItemMap()
						.get(unit), count);// 单位的数据字典
			}
		}
		// 图表
		JFreeChart chart = ChartFactory.createPieChart3D("各单位人数饼图", dataset,
				true, true, true);
		// 字体对象
		Font titleFont = new Font("宋体", Font.BOLD, 20);// 标题的字体
		Font labelFont = new Font("宋体", Font.PLAIN, 16);// 各个标签的字体
		// 样式
		chart.getTitle().setFont(titleFont);
		chart.getLegend().setItemFont(labelFont);
		// 绘图区域对象（与柱形图和曲线图的不同，因为它没有子对象x和y轴）
		PiePlot3D piePlot = (PiePlot3D) chart.getPlot();
		// 设置标签显示格式（通过标签生成器）
		piePlot.setLabelFont(labelFont);// 设置标签字体
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} {1}人({2})"));

		// 创建输出流
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);

		return null;
	}

	public synchronized ElecUserService getUserService() {
		return userService;
	}

	public synchronized String getSEcho() {
		return sEcho;
	}

	public synchronized void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public synchronized int getIDisplayStart() {
		return iDisplayStart;
	}

	public synchronized void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public synchronized int getIDisplayLength() {
		return iDisplayLength;
	}

	public synchronized void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public synchronized void setUserService(ElecUserService userService) {
		this.userService = userService;
	}

	public ElecUser getModel() {
		return user;
	}

	public synchronized String getCheckNumber() {
		return checkNumber;
	}

	public synchronized void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public synchronized String getRememberMe() {
		return rememberMe;
	}

	public synchronized void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

}
