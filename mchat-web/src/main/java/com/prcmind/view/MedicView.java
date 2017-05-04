package com.prcmind.view;

public class MedicView {
	String medicNo;
	String loginName;
	String mobileNo;
	String realName;
	int status;
	int isChangedPwd;
	int pwdErrorTimes;
	int deleted;
	int type;
	int oauthStatus;
	String lastLoginTime;
	String pwdErrorLastTime;
	String lastAlertPwdTime;
	MedicInfoView medicInfo;
	EnterpriseOperatorView enterpriseOperator;
	public String getMedicNo() {
		return medicNo;
	}
	public void setMedicNo(String medicNo) {
		this.medicNo = medicNo;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsChangedPwd() {
		return isChangedPwd;
	}
	public void setIsChangedPwd(int isChangedPwd) {
		this.isChangedPwd = isChangedPwd;
	}
	public int getPwdErrorTimes() {
		return pwdErrorTimes;
	}
	public void setPwdErrorTimes(int pwdErrorTimes) {
		this.pwdErrorTimes = pwdErrorTimes;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getOauthStatus() {
		return oauthStatus;
	}
	public void setOauthStatus(int oauthStatus) {
		this.oauthStatus = oauthStatus;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getPwdErrorLastTime() {
		return pwdErrorLastTime;
	}
	public void setPwdErrorLastTime(String pwdErrorLastTime) {
		this.pwdErrorLastTime = pwdErrorLastTime;
	}
	public String getLastAlertPwdTime() {
		return lastAlertPwdTime;
	}
	public void setLastAlertPwdTime(String lastAlertPwdTime) {
		this.lastAlertPwdTime = lastAlertPwdTime;
	}
	public MedicInfoView getMedicInfo() {
		return medicInfo;
	}
	public void setMedicInfo(MedicInfoView medicInfo) {
		this.medicInfo = medicInfo;
	}
	public EnterpriseOperatorView getEnterpriseOperator() {
		return enterpriseOperator;
	}
	public void setEnterpriseOperator(EnterpriseOperatorView enterpriseOperator) {
		this.enterpriseOperator = enterpriseOperator;
	}
	@Override
	public String toString() {
		return "MedicView [medicNo=" + medicNo + ", loginName=" + loginName + ", mobileNo=" + mobileNo + ", realName="
				+ realName + ", status=" + status + ", isChangedPwd=" + isChangedPwd + ", pwdErrorTimes="
				+ pwdErrorTimes + ", deleted=" + deleted + ", type=" + type + ", oauthStatus=" + oauthStatus
				+ ", lastLoginTime=" + lastLoginTime + ", pwdErrorLastTime=" + pwdErrorLastTime + ", lastAlertPwdTime="
				+ lastAlertPwdTime + ", medicInfo=" + medicInfo + ", enterpriseOperator=" + enterpriseOperator + "]";
	}
	
	
}
