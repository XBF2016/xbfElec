package com.xbf.elec.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xbf.elec.domain.ELecMatter;
import com.xbf.elec.service.ElecMatterService;

public class ElecMatterAction extends ActionSupport implements
		ModelDriven<ELecMatter> {
	private ElecMatterService matterService;
	private ELecMatter matter = new ELecMatter();

	// /代办事宜主页，转发同时查询数据以供显示
	public String home() {
		ELecMatter matter = matterService.findOne();
		// 将查询到的数据放入web域中
		ServletActionContext.getRequest().setAttribute("matter", matter);
		return "home";
	}

	// 查询数据
	public String alermSB() {
		ELecMatter matter = matterService.findOne();
		// 将查询到的数据放入web域中
		ServletActionContext.getRequest().setAttribute("matter", matter);
		return "alermSB";

	}

	// 查询数据
	public String alermZD() {
		ELecMatter matter = matterService.findOne();
		// 将查询到的数据放入web域中
		ServletActionContext.getRequest().setAttribute("matter", matter);
		return "alermZD";

	}

	// 添加事宜
	public String add() {
		matter.setCreateDate(new Date());
		matterService.addAfterDelete(matter);
		return "add";
	}

	public synchronized ELecMatter getMatter() {
		return matter;
	}

	public synchronized void setMatter(ELecMatter matter) {
		this.matter = matter;
	}

	public synchronized ElecMatterService getMatterService() {
		return matterService;
	}

	public synchronized void setMatterService(ElecMatterService matterService) {
		this.matterService = matterService;
	}

	public ELecMatter getModel() {
		return matter;
	}
}
