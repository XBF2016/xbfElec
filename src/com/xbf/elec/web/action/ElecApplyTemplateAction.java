package com.xbf.elec.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xbf.elec.domain.ElecApplyTemplate;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.service.ElecJBPMService;

public class ElecApplyTemplateAction extends ActionSupport implements
		ModelDriven<ElecApplyTemplate> {
	// 模板
	private ElecApplyTemplate applyTemplate = new ElecApplyTemplate();
	// service
	private ElecJBPMService jbpmService;

	// 上传三要素
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	// 文件下载需要的输入流、
	private InputStream inputStream;

	// 导航到申请模板首页
	public String home() {
		// 查询出所有流程定义对应的申请模板并带过去
		List<ElecApplyTemplate> applyTemplateList = jbpmService
				.listApplyTemplate();
		ServletActionContext.getRequest().setAttribute("applyTemplateList",
				applyTemplateList);
		return "home";
	}

	// 添加模板页面
	public String addPage() {
		// 将所有流程定义的key查询出来带过去
		List<ProcessDefinition> processDefinitionList = jbpmService
				.listNewestProcessDefinitions();// 最新的流程定义中就有需要的数据
		ServletActionContext.getRequest().setAttribute("processDefinitionList",
				processDefinitionList);
		return "addPage";
	}

	// 添加模板
	public String upload() {
		// 上传到哪里？
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload/applyTemplate");
		// 确定该路径是否存在
		File file = new File(path);
		// 如果不存在就创建此路径
		if (!file.exists()) {
			file.mkdirs();
		}

		// 确定最终的位置（路径+唯一标识+文件名）
		path += ("/" + UUID.randomUUID() + uploadFileName);
		// 复制（新方式：重命名就可）
		upload.renameTo(new File(path));

		// 封装数据
		applyTemplate.setFilename(uploadFileName);
		applyTemplate.setPath(path);

		// 持久化到数据库（更新或保存，因为要保证最新的流程定义对应着最新的申请模板）
		jbpmService.addOrUpdateApplyTemplate(applyTemplate);

		return null;
	}

	public String download() {
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("user");

		// struts的下载不是通过servlet了，而是通过配置文件中的result来配置，action中唯一必须的东西是inputStream，它是要下载的文件的输入流
		// 首先根据模板的id查询模板
		applyTemplate = jbpmService.getApplyTemplateById(applyTemplate
				.getTemplateId());
		String filename = applyTemplate.getFilename();
		int index = filename.indexOf(".");
		// 给下载的文件名插入_acctunt
		filename = filename.substring(0, index) + "_" + user.getAccount()
				+ filename.substring(index);
		try {
			// 输入流连到文件所在的位置
			inputStream = new FileInputStream(applyTemplate.getPath());
			// 拿到mime类型（在struts-workflow.xml中使用）
			String contentType = ServletActionContext.getServletContext()
					.getMimeType(applyTemplate.getFilename());
			ServletActionContext.getRequest().setAttribute("contentType",
					contentType);
			// 拿到用户名（在struts-workflow.xml中使用）
			ServletActionContext.getRequest().setAttribute("filename",
					URLEncoder.encode(filename, "utf-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "download";
	}

	// 删除模板
	public String delete() {
		jbpmService.deleteApplyTemplateById(applyTemplate.getTemplateId());
		return "delete";
	}

	public ElecApplyTemplate getModel() {
		return this.applyTemplate;
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

	public synchronized InputStream getInputStream() {
		return inputStream;
	}

	public synchronized void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
