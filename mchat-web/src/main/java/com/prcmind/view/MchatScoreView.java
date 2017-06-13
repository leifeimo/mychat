package com.prcmind.view;

import java.util.Date;

import com.prcmind.facade.scale.mchat.entity.MchatScore;
import com.prcmind.utils.DateUtil;

public class MchatScoreView {

	/**
	 * 1：初筛，2：回访
	 */
	private Integer level;
	
	/**
	 * 此报告的UUID
	 */
	private String scoreNo;
	/**
	 * R/F对应的ID
	 */
	private String rfScoreNo;
	/**
	 * 被试者姓名
	 */
	private String testeeNo;
	/**
	 * 报告编号
	 */
	private String reportNo;
	private String cardNo;
	private Integer cardType;
	private String testeeName;
	private Integer sex;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthToday;
	private Integer testYear;
	private Integer testMonth;
	private Integer testToday;
	private Integer lifeMonth;
	private Integer lifeDay;
	/**
	 * 出生情况
	 */
	private String births;
	private Integer birthWeight;
	/**
	 * 孕周
	 */
	private Integer gestationalWeeks;
	/**
	 * 孕周的日：1-6
	 */
	private Integer gestationalDays;
	private String father;
	private String mother;
	private Integer motherCareer;
	private Integer fatherCareer;
	/**
	 * 职业类型
	 */
	private Integer motherCareerCategory;
	private Integer fatherCareerCategory;
	/**
	 * 职业类型
	 */
	private String fatherBirthday;
	private String motherBirthday;
	private Integer motherCultureDegree;//文化程度
	private Integer fatherCultureDegree;//文化程度
	private String patronnInfo;//协助填写问卷人关系或者职业
	private Integer maritalStatus;//婚姻状态
	private Integer nation;
	private String address;
	private String province;
	private String city;
	private String town;
	private String tel;
	private String email;
	private String caregiversCultureDegree;
	private String medicName;
	private String medicNo;
	private String enterpriseNo;
	private String enterpriseName;
	private String remark;
	/**
	 * 报告总分
	 */
	private Integer score;

	/**
	 * 报告地址
	 */
	private String  reportPath;
	private String ip;
	/**
	 * IP对应的物理地址
	 */
	private String ipLocation;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getScoreNo() {
		return scoreNo;
	}
	public void setScoreNo(String scoreNo) {
		this.scoreNo = scoreNo;
	}
	public String getRfScoreNo() {
		return rfScoreNo;
	}
	public void setRfScoreNo(String rfScoreNo) {
		this.rfScoreNo = rfScoreNo;
	}
	public String getTesteeNo() {
		return testeeNo;
	}
	public void setTesteeNo(String testeeNo) {
		this.testeeNo = testeeNo;
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
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getTesteeName() {
		return testeeName;
	}
	public void setTesteeName(String testeeName) {
		this.testeeName = testeeName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	public Integer getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}
	public Integer getBirthToday() {
		return birthToday;
	}
	public void setBirthToday(Integer birthToday) {
		this.birthToday = birthToday;
	}
	public Integer getTestYear() {
		return testYear;
	}
	public void setTestYear(Integer testYear) {
		this.testYear = testYear;
	}
	public Integer getTestMonth() {
		return testMonth;
	}
	public void setTestMonth(Integer testMonth) {
		this.testMonth = testMonth;
	}
	public Integer getTestToday() {
		return testToday;
	}
	public void setTestToday(Integer testToday) {
		this.testToday = testToday;
	}
	public Integer getLifeMonth() {
		return lifeMonth;
	}
	public void setLifeMonth(Integer lifeMonth) {
		this.lifeMonth = lifeMonth;
	}
	public Integer getLifeDay() {
		return lifeDay;
	}
	public void setLifeDay(Integer lifeDay) {
		this.lifeDay = lifeDay;
	}
	public String getBirths() {
		return births;
	}
	public void setBirths(String births) {
		this.births = births;
	}
	public Integer getBirthWeight() {
		return birthWeight;
	}
	public void setBirthWeight(Integer birthWeight) {
		this.birthWeight = birthWeight;
	}
	public Integer getGestationalWeeks() {
		return gestationalWeeks;
	}
	public void setGestationalWeeks(Integer gestationalWeeks) {
		this.gestationalWeeks = gestationalWeeks;
	}
	public Integer getGestationalDays() {
		return gestationalDays;
	}
	public void setGestationalDays(Integer gestationalDays) {
		this.gestationalDays = gestationalDays;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public Integer getMotherCareer() {
		return motherCareer;
	}
	public void setMotherCareer(Integer motherCareer) {
		this.motherCareer = motherCareer;
	}
	public Integer getFatherCareer() {
		return fatherCareer;
	}
	public void setFatherCareer(Integer fatherCareer) {
		this.fatherCareer = fatherCareer;
	}
	public Integer getMotherCareerCategory() {
		return motherCareerCategory;
	}
	public void setMotherCareerCategory(Integer motherCareerCategory) {
		this.motherCareerCategory = motherCareerCategory;
	}
	public Integer getFatherCareerCategory() {
		return fatherCareerCategory;
	}
	public void setFatherCareerCategory(Integer fatherCareerCategory) {
		this.fatherCareerCategory = fatherCareerCategory;
	}
	public String getFatherBirthday() {
		return fatherBirthday;
	}
	public void setFatherBirthday(String fatherBirthday) {
		this.fatherBirthday = fatherBirthday;
	}
	public String getMotherBirthday() {
		return motherBirthday;
	}
	public void setMotherBirthday(String motherBirthday) {
		this.motherBirthday = motherBirthday;
	}
	public Integer getMotherCultureDegree() {
		return motherCultureDegree;
	}
	public void setMotherCultureDegree(Integer motherCultureDegree) {
		this.motherCultureDegree = motherCultureDegree;
	}
	public Integer getFatherCultureDegree() {
		return fatherCultureDegree;
	}
	public void setFatherCultureDegree(Integer fatherCultureDegree) {
		this.fatherCultureDegree = fatherCultureDegree;
	}
	public String getPatronnInfo() {
		return patronnInfo;
	}
	public void setPatronnInfo(String patronnInfo) {
		this.patronnInfo = patronnInfo;
	}
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Integer getNation() {
		return nation;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCaregiversCultureDegree() {
		return caregiversCultureDegree;
	}
	public void setCaregiversCultureDegree(String caregiversCultureDegree) {
		this.caregiversCultureDegree = caregiversCultureDegree;
	}
	public String getMedicName() {
		return medicName;
	}
	public void setMedicName(String medicName) {
		this.medicName = medicName;
	}
	public String getMedicNo() {
		return medicNo;
	}
	public void setMedicNo(String medicNo) {
		this.medicNo = medicNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpLocation() {
		return ipLocation;
	}
	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}
	public static MchatScore toMchatScore(MchatScoreView mchatScoreView) {
		if(mchatScoreView !=null){
			MchatScore mchatScore = new MchatScore();
			mchatScore.setAddress(mchatScoreView.getAddress());
			mchatScore.setBirthMonth(mchatScoreView.getBirthMonth());
			mchatScore.setBirths(mchatScoreView.getBirths());
			mchatScore.setBirthToday(mchatScoreView.getBirthToday());
			mchatScore.setBirthWeight(mchatScoreView.getBirthWeight());
			mchatScore.setBirthYear(mchatScoreView.getBirthYear());
			mchatScore.setCardNo(mchatScoreView.getCardNo());
			mchatScore.setCardType(mchatScoreView.getCardType());
			mchatScore.setCaregiversCultureDegree(mchatScoreView.getCaregiversCultureDegree());
			mchatScore.setCity(mchatScoreView.getCity());
			mchatScore.setCreateTime(new Date(System.currentTimeMillis()));
			mchatScore.setEmail(mchatScoreView.getEmail());
			mchatScore.setEnterpriseName(mchatScoreView.getEnterpriseName());
			mchatScore.setEnterpriseNo(mchatScoreView.getEnterpriseNo());
			mchatScore.setTel(mchatScoreView.getTel());
			mchatScore.setTesteeName(mchatScoreView.getTesteeName());
			mchatScore.setTesteeNo(mchatScoreView.getTesteeNo());
			mchatScore.setTestMonth(mchatScoreView.getTestMonth());
			mchatScore.setTestToday(mchatScoreView.getTestToday());
			mchatScore.setTestYear(mchatScoreView.getTestYear());
			mchatScore.setTown(mchatScoreView.getTown());
			mchatScore.setFather(mchatScoreView.getFather());
			if(mchatScoreView.getFatherBirthday()!=null && mchatScoreView.getFatherBirthday().length() >1){
				mchatScore.setFatherBirthday(DateUtil.StrToDate(mchatScoreView.getFatherBirthday(), "yyyy-MM-dd"));
			}
			
			mchatScore.setFatherCareer(mchatScoreView.getFatherCareer());
			mchatScore.setFatherCareerCategory(mchatScoreView.getFatherCareerCategory());
			mchatScore.setFatherCultureDegree(mchatScoreView.getFatherCultureDegree());
			mchatScore.setGestationalDays(mchatScoreView.getGestationalDays());
			mchatScore.setGestationalWeeks(mchatScoreView.getGestationalWeeks());
			mchatScore.setLevel(mchatScoreView.getLevel());
			mchatScore.setMaritalStatus(mchatScoreView.getMaritalStatus());
			mchatScore.setMedicName(mchatScoreView.getMedicName());
			mchatScore.setMother(mchatScoreView.getMother());
			mchatScore.setMedicNo(mchatScoreView.getMedicNo());
			if(mchatScoreView.getMotherBirthday()!=null && mchatScoreView.getMotherBirthday().length() >1){
				mchatScore.setMotherBirthday(DateUtil.StrToDate(mchatScoreView.getMotherBirthday(), "yyyy-MM-dd"));
			}
			mchatScore.setMotherCareerCategory(mchatScoreView.getMotherCareerCategory());
			mchatScore.setMotherCareer(mchatScoreView.getMotherCareer());
			mchatScore.setMotherCultureDegree(mchatScoreView.getMotherCultureDegree());
			mchatScore.setNation(mchatScoreView.getNation());
			mchatScore.setPatronnInfo(mchatScoreView.getPatronnInfo());
			mchatScore.setReportNo(mchatScoreView.getReportNo());
			mchatScore.setRfScoreNo(mchatScoreView.getRfScoreNo());
			mchatScore.setScoreNo(mchatScoreView.getScoreNo());
			mchatScore.setSex(mchatScoreView.getSex());
			return mchatScore;
			
			
		}
		return null;
	}
	
	
	
}
