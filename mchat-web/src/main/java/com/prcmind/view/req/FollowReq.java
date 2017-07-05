package com.prcmind.view.req;

import java.io.Serializable;

public class FollowReq implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7897148489665050954L;
	int pageNum;
	int numPerPage;
	String testeeName;
	String reportNo;
	String testeeNo;
	String scoreNo;
	String parentNo;
	int deleted;
	String birth;
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	String testDate;
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getTesteeName() {
		return testeeName;
	}

	public void setTesteeName(String testeeName) {
		this.testeeName = testeeName;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getTesteeNo() {
		return testeeNo;
	}

	public void setTesteeNo(String testeeNo) {
		this.testeeNo = testeeNo;
	}

	public String getScoreNo() {
		return scoreNo;
	}

	public void setScoreNo(String scoreNo) {
		this.scoreNo = scoreNo;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "FollowReq [pageNum=" + pageNum + ", numPerPage=" + numPerPage + ", testeeName=" + testeeName
				+ ", reportNo=" + reportNo + ", testeeNo=" + testeeNo + ", scoreNo=" + scoreNo + ", parentNo="
				+ parentNo + ", deleted=" + deleted + ", birth=" + birth + ", testDate=" + testDate + "]";
	}

}
