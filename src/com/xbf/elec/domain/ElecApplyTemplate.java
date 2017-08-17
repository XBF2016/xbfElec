package com.xbf.elec.domain;

/*
 #申请模板文件表
 create table elecApplyTemplate(
 templateId varchar(100) primary key,#模板ID
 filename varchar(100),#文件名
 path text,#文件路径
 processDefinitionKey varchar(100) #对应的流程定义的名称
 );
 */
public class ElecApplyTemplate {
	private String templateId;
	private String filename;
	private String path;
	private String processDefinitionKey;

	public synchronized String getTemplateId() {
		return templateId;
	}

	public synchronized void setTemplateId(String templateId) {
		this.templateId = templateId;
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

	public synchronized String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public synchronized void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

}
