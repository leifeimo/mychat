package com.prcmind.view.req;

public class RecordReq {

	int pageNum;
	int numPerPage;
	String testeeName;
	String reportNo;
	String cardNo;
	int birthYear;
	int birthMonth;
	int birthToday;
	int deleted;
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	public int getBirthToday() {
		return birthToday;
	}
	public void setBirthToday(int birthToday) {
		this.birthToday = birthToday;
	}
	
}
