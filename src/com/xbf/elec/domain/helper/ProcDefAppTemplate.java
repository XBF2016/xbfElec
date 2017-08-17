package com.xbf.elec.domain.helper;

//辅助类
public class ProcDefAppTemplate {
	private String processDefinitionKey;
	private String processDefinitionId;
	private String filename;
	private String path;
	private String templateId;

	public synchronized String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public synchronized void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public synchronized String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public synchronized void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public synchronized String getFilename() {
		return filename;
	}

	public synchronized void setFilename(String filename) {
		this.filename = filename;
	}

	public synchronized String getPath() {
		return path;
	}

	public synchronized void setPath(String path) {
		this.path = path;
	}

	public synchronized String getTemplateId() {
		return templateId;
	}

	public synchronized void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
