package com.prcmind.view;

public class BaseUserView {

	String accountId;
	String token;
    public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "BaseUserView [accountId=" + accountId + ", token=" + token + "]";
	}
	
   
}
