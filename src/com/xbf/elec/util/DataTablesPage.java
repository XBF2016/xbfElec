package com.xbf.elec.util;

import java.util.List;

public class DataTablesPage<T> {
	// 氧气
	private String sEcho;
	private int iDisplayStart;
	private int iDisplayLength;
	// 二氧化碳
	private int iTotalRecords;
	private int iTotalDisplayRecords;

	private List<T> data;

	public synchronized List<T> getData() {
		return data;
	}

	public synchronized void setData(List<T> data) {
		this.data = data;
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

	public synchronized int getITotalRecords() {
		return iTotalRecords;
	}

	public synchronized void setITotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public synchronized int getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public synchronized void setITotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

}
