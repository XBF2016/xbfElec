package com.xbf.elec.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xbf.elec.service.ElecJBPMService;

public class ElecProcessDefinitionAction extends ActionSupport {
	// JBPMService
	ElecJBPMService jbpmService;

	// 上传的文件
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	// 流程定义id
	private String id;

	// 文件流
	private InputStream inputStream;

	// 显示流程定义图片
	public String showProcessImg() {
		// 根据流程id去得到图片的输入流
		inputStream = jbpmService.showProcessImg(id);
		return "showProcessImg";
	}

	// 根据流程的部署id删除
	public String delete() {
		jbpmService.deleteByProcessDefinitionId(id);
		return "delete";
	}

	// 部署流程定义
	public String deployment() {
		jbpmService.depolyment(upload);
		return "deployment";
	}

	// 打开添加页面
	public String add() {
		return "add";
	}

	// 流程管理首页
	public String home() {
		// 显示各个流程的最新版本
		List newestProcessDefinitionList = jbpmService
				.listNewestProcessDefinitions();

		// 放数据到前端
		ServletActionContext.getRequest().setAttribute("processDefinitionList",
				newestProcessDefinitionList);
		return "home";
	}

	public synchronized ElecJBPMService getJbpmService() {
		return jbpmService;
	}

	public synchronized void setJbpmService(ElecJBPMService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public synchronized File getUpload() {
		return upload;
	}

	public synchronized void setUpload(File upload) {
		this.upload = upload;
	}

	public synchronized String getUploadContentType() {
		return uploadContentType;
	}

	public synchronized void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public synchronized String getUploadFileName() {
		return uploadFileName;
	}

	public synchronized void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public synchronized String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		// 对付get乱码 改字典即可
		try {
			id = new String(id.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		this.id = id;
	}

	public synchronized InputStream getInputStream() {
		return inputStream;
	}

	public synchronized void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
