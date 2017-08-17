package com.xbf.elec.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ElecUser implements Serializable {
	private String userId;// ID
	private String username;// 用户名
	private String password;// 密码
	private String account;// 账户
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String address;// 地址
	private String homeTel;// 家庭电话
	private String email;// 邮箱
	private String phone;// 手机号
	private String units;// 所属单位
	private String isDuty;// 是否入职
	private Date onDutyDate;// 入职日期
	private Date offDutyDate;// 离职日期
	private String comment;// 备注

	// 特殊数据，主要是为了实现问责机制，记录操作者
	private String createUser;// 创建者的ID
	private Date createDate;// 创建日期
	private String lastUpdateUser;// 最后一次修改者，其他的修改者放在另外的历史表或者修改日志中
	private Date lastUpdateDate;// 最后一次修改日期

	// 假删除，删除时并不真的删除，仅仅设置该条数据，查询时条件中就会加上该条件，如果已被设置为已删除就读取记录
	private boolean isDelete;

	// 多端：角色
	private Set<ElecRole> roles;

	public synchronized Set<ElecRole> getRoles() {
		return roles;
	}

	public synchronized void setRoles(Set<ElecRole> roles) {
		this.roles = roles;
	}

	public synchronized String getUserId() {
		return userId;
	}

	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getPassword() {
		return password;
	}

	public synchronized void setPassword(String password) {
		this.password = password;
	}

	public synchronized String getAccount() {
		return account;
	}

	public synchronized void setAccount(String account) {
		this.account = account;
	}

	public synchronized String getGender() {
		return gender;
	}

	public synchronized void setGender(String gender) {
		this.gender = gender;
	}

	public synchronized Date getBirthday() {
		return birthday;
	}

	public synchronized void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public synchronized String getAddress() {
		return address;
	}

	public synchronized void setAddress(String address) {
		this.address = address;
	}

	public synchronized String getHomeTel() {
		return homeTel;
	}

	public synchronized void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public synchronized String getEmail() {
		return email;
	}

	public synchronized void setEmail(String email) {
		this.email = email;
	}

	public synchronized String getPhone() {
		return phone;
	}

	public synchronized void setPhone(String phone) {
		this.phone = phone;
	}

	public synchronized String getUnits() {
		return units;
	}

	public synchronized void setUnits(String units) {
		this.units = units;
	}

	public synchronized String getIsDuty() {
		return isDuty;
	}

	public synchronized void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}

	public synchronized Date getOnDutyDate() {
		return onDutyDate;
	}

	public synchronized void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public synchronized Date getOffDutyDate() {
		return offDutyDate;
	}

	public synchronized void setOffDutyDate(Date offDutyDate) {
		this.offDutyDate = offDutyDate;
	}

	public synchronized String getComment() {
		return comment;
	}

	public synchronized void setComment(String comment) {
		this.comment = comment;
	}

	public synchronized String getCreateUser() {
		return createUser;
	}

	public synchronized void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public synchronized Date getCreateDate() {
		return createDate;
	}

	public synchronized void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public synchronized String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public synchronized void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public synchronized Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public synchronized void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public synchronized boolean getIsDelete() {
		return isDelete;
	}

	public synchronized void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
