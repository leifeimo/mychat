package com.prcmind.view.req;

public class FollowReq {
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

}
