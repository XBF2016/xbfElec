package com.xbf.elec.domain;

import java.io.Serializable;
import java.util.Date;

public class ElecTest implements Serializable {
	private String testId;
	private String testName;
	private Date testDate;
	private String testComment;

	public synchronized String getTestId() {
		return testId;
	}

	@Override
	public String toString() {
		return "ElecTest [testComment=" + testComment + ", testName="
				+ testName + "]";
	}

	public synchronized void setTestId(String testId) {
		this.testId = testId;
	}

	public synchronized String getTestName() {
		return testName;
	}

	public synchronized void setTestName(String testName) {
		this.testName = testName;
	}

	public synchronized Date getTestDate() {
		return testDate;
	}

	public synchronized void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public synchronized String getTestComment() {
		return testComment;
	}

	public synchronized void setTestComment(String testComment) {
		this.testComment = testComment;
	}

}
