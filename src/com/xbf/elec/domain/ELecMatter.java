package com.xbf.elec.domain;

import java.io.Serializable;
import java.util.Date;

//#ElecMatter 代办事宜
//create table elecMatter(
//       matterId varchar(100) primary key,
//       stationRunStatus text,
//       devRunStatus text,
//       createDate date
//)
public class ELecMatter implements Serializable {

	private String matterId;
	private String stationRunStatus;
	private String devRunStatus;
	private Date createDate;

	public synchronized String getMatterId() {
		return matterId;
	}

	public synchronized void setMatterId(String matterId) {
		this.matterId = matterId;
	}

	public synchronized String getStationRunStatus() {
		return stationRunStatus;
	}

	public synchronized void setStationRunStatus(String stationRunStatus) {
		this.stationRunStatus = stationRunStatus;
	}

	public synchronized String getDevRunStatus() {
		return devRunStatus;
	}

	public synchronized void setDevRunStatus(String devRunStatus) {
		this.devRunStatus = devRunStatus;
	}

	public synchronized Date getCreateDate() {
		return createDate;
	}

	public synchronized void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
