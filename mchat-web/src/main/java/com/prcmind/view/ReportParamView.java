package com.prcmind.view;

import java.io.Serializable;

public class ReportParamView implements Serializable{
	
	private static final long serialVersionUID = 1857581288851781834L;

	private String testName;//施测者姓名
	
	private String testeeName;//儿童姓名
	
	private String cardType;//身份信息卡类型
	
	private String cardNo;//身份证号码
	
	private String sex;//性别
	
	private String birthDay;//出生日期
	
	private String testDay;//完成问卷日期
	
	private String gestationalWeeks;//孕周
	
	private String births;//出生时情况
	
	private String consignorName;//完成问卷人姓名
	
	private String consignorType;//与儿童关系
	
	private String weight;//体重
	
	private String address;//通讯地址
	
	private String zip;//邮政编码
	
	private String email;//电子邮箱
	
	private String tel;//联系电话
	
	private String remarks;//备注
	
	private String motherCultureDegree;//母亲文化程度
	
	private String motherCareerCategory;//母亲职业大类
	
	private String motherCareer;//母亲职业
	
	private String fatherCultureDegree;//父亲文化程度
	
	private String fatherCareerCategory;//父亲职业大类
	
	private String fatherCareer;//父亲职业
	
	private String caregiversCultureDegree;//主要照顾人文化程度
	
	private String motherBirthDay;//母亲生日
	
	private String fatherBirthDay;//父亲生日
	
	private String maritalStatus;//婚姻状况

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTesteeName() {
		return testeeName;
	}

	public void setTesteeName(String testeeName) {
		this.testeeName = testeeName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getTestDay() {
		return testDay;
	}

	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}

	public String getGestationalWeeks() {
		return gestationalWeeks;
	}

	public void setGestationalWeeks(String gestationalWeeks) {
		this.gestationalWeeks = gestationalWeeks;
	}

	public String getBirths() {
		return births;
	}

	public void setBirths(String births) {
		this.births = births;
	}

	public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	public String getConsignorType() {
		return consignorType;
	}

	public void setConsignorType(String consignorType) {
		this.consignorType = consignorType;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMotherCultureDegree() {
		return motherCultureDegree;
	}

	public void setMotherCultureDegree(String motherCultureDegree) {
		this.motherCultureDegree = motherCultureDegree;
	}

	public String getMotherCareerCategory() {
		return motherCareerCategory;
	}

	public void setMotherCareerCategory(String motherCareerCategory) {
		this.motherCareerCategory = motherCareerCategory;
	}

	public String getMotherCareer() {
		return motherCareer;
	}

	public void setMotherCareer(String motherCareer) {
		this.motherCareer = motherCareer;
	}

	public String getFatherCultureDegree() {
		return fatherCultureDegree;
	}

	public void setFatherCultureDegree(String fatherCultureDegree) {
		this.fatherCultureDegree = fatherCultureDegree;
	}

	public String getFatherCareerCategory() {
		return fatherCareerCategory;
	}

	public void setFatherCareerCategory(String fatherCareerCategory) {
		this.fatherCareerCategory = fatherCareerCategory;
	}

	public String getFatherCareer() {
		return fatherCareer;
	}

	public void setFatherCareer(String fatherCareer) {
		this.fatherCareer = fatherCareer;
	}

	public String getCaregiversCultureDegree() {
		return caregiversCultureDegree;
	}

	public void setCaregiversCultureDegree(String caregiversCultureDegree) {
		this.caregiversCultureDegree = caregiversCultureDegree;
	}

	public String getMotherBirthDay() {
		return motherBirthDay;
	}

	public void setMotherBirthDay(String motherBirthDay) {
		this.motherBirthDay = motherBirthDay;
	}

	public String getFatherBirthDay() {
		return fatherBirthDay;
	}

	public void setFatherBirthDay(String fatherBirthDay) {
		this.fatherBirthDay = fatherBirthDay;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public String toString() {
		return "ReportParamView [testName=" + testName + ", testeeName="
				+ testeeName + ", cardType=" + cardType + ", cardNo=" + cardNo
				+ ", sex=" + sex + ", birthDay=" + birthDay + ", testDay="
				+ testDay + ", gestationalWeeks=" + gestationalWeeks
				+ ", births=" + births + ", consignorName=" + consignorName
				+ ", consignorType=" + consignorType + ", weight=" + weight
				+ ", address=" + address + ", zip=" + zip + ", email=" + email
				+ ", tel=" + tel + ", remarks=" + remarks
				+ ", motherCultureDegree=" + motherCultureDegree
				+ ", motherCareerCategory=" + motherCareerCategory
				+ ", motherCareer=" + motherCareer + ", fatherCultureDegree="
				+ fatherCultureDegree + ", fatherCareerCategory="
				+ fatherCareerCategory + ", fatherCareer=" + fatherCareer
				+ ", caregiversCultureDegree=" + caregiversCultureDegree
				+ ", motherBirthDay=" + motherBirthDay + ", fatherBirthDay="
				+ fatherBirthDay + ", maritalStatus=" + maritalStatus + "]";
	}
	
}
