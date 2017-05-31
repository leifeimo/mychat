<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>监护人</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<script src="../../mobile/js/layer_mobile/layer.js"></script>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/util.js"></script>
<link rel="Stylesheet" type="text/css" href="../../mobile/css/style.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/m_1.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/mobile.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/payment.css" />
<style>
.gif{
		width:90%;
		height:90%;
	}
	@media only screen and (min-width:320px){
		.layui-m-layercont{
			padding:0px 30px;
		}
	}
	@media only screen and (min-width:500px){
		.layui-m-layercont{
			padding:0px 30px;
		}
	}
	@media only screen and (min-width:700px){
		.layui-m-layercont{
			padding:0px 100px;
		}
	}
	@media only screen and (min-width:1000px){
		.layui-m-layercont{
			padding:0px 160px;
		}
	}
	input{background-color:transparent;border-style:none;font-size:1.5rem;border:0; text-align:right; margin-right:10px; width:80%; color:#999}
	.divhide{
		display: none;
	}
</style>


</head>

<body>
	<di>
	<form action="" id="reportForm" accept-charset="UTF-8" method="post">
    <div id="m_guardian">
    <p class="m_faces"><img src="../images/faces.png" /></p>
    <p class="m_guardian_name f_white">监护人</p>
    <p class="m_guardian_read"><span>使用说明</span></p>
    </div>
    
    <div id="m_test_name" class="m_list_1 b_solid">
    <p>施测者：<span id="medicName">${report.medicName}</span>
    	<input type="hidden" name="medicNo" value="${report.medicNo}">
    	<input type="hidden" name="medicName" value="${report.medicName}">
    </p>
    
    <p>
			<select name="testNameChange" id="changeTestName" class="fs">
			    <option value="">切换</option>
			</select>
	</p>
    
   
    </div>



    <div class="m_mid">
      <div class="m_list b_solid ${report.testeeNameShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_1.png" /></span></p>
        <p>儿童姓名*</p>
        <p><span>&nbsp;</span><input type="text" value="${report.testeeName}" name="testeeName" id="testeeName" ${empty report.testeeName ? "" : "disabled"}/></p>
      </div>
      
      
      <div class="m_list b_solid ${report.cardTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>身份信息*</p>
        <p>
			<select name="cardType" id="cardType" class="fs" ${ empty report.cardType ? "" : "disabled"}>
				<option value="" ${empty report.cardType ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="0" ${report.cardType eq '0' ? "selected = 'selected'" : ""}>儿童身份证</option>
				<option value="1" ${report.cardType eq '1' ? "selected = 'selected'" : ""}>父亲身份证</option>
				<option value="2" ${report.cardType eq '2' ? "selected = 'selected'" : ""}>母亲身份证</option>
				<option value="3" ${report.cardType eq '3' ? "selected = 'selected'" : ""}>诊疗卡号</option>
				<option value="9" ${report.cardType eq '9' ? "selected = 'selected'" : ""}>其他</option>
			</select>
		</p>
      </div>


      <div class="m_list b_solid ${report.cardNoShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_3.png" /></span></p>
        <p>身份证号码*</p>
        <p><span>&nbsp;</span><input type="text" value="${report.cardNo}" id="cardNo" name="cardNo" ${empty report.cardNo ? "" : "disabled"}/></p>
      </div>      


      <div class="m_list_1 b_solid m_top_20 ${report.sexShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_4.png" /></span></p>
        <p>性别*</p>
        <p>
			<select name="sex" id="sex" class="fs" ${ empty report.sex ? "" : "disabled"}>
				<option value="" ${empty report.sex ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="0" ${report.sex eq '0' ? "selected = 'selected'" : ""}>男</option>
				<option value="1" ${report.sex eq '1' ? "selected = 'selected'" : ""}>女</option>
			</select>
		</p>
      </div>  
      
      
      <div class="m_list_1 b_solid ${report.birthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>出生日期*</p>
        <p>
        	<input type="date" name="birthDay" class="fs1" value="${report.birthDay}" ${empty report.birthDay ? "" : "disabled"}/>
        </p>
      </div>  
      
      <div class="m_list_1 b_solid ${report.testDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>完成问卷日期*</p>
        <p>
        	<input type="date" name="testDay" class="fs1" value="${report.testDay}" ${empty report.testDay ? "" : "disabled"}/>
        </p>
      </div>  
      
      <div class="m_list_1 b_solid" style="display:none">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>儿童的生活龄</p>
        <p>计算年龄<img src="../images/tip2.jpg" align="absmiddle" /></p>
      </div>  


	  
      <div class="m_list_1 b_solid m_top_20 ${report.gestationalWeeksDaysShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_8.png" /></span></p>
        <p>宝宝的孕周*</p> 
        <p>
		    <select name="gestationalWeeks" id="gestationalWeeks" class="fs" ${ empty report.gestationalWeeks ? "" : "disabled"}>
			    <option value="" ${empty report.gestationalWeeks ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.gestationalWeeks eq '1' ? "selected = 'selected'" : ""}>1周</option>
				<option value="2" ${report.gestationalWeeks eq '2' ? "selected = 'selected'" : ""}>2周</option>
				<option value="3" ${report.gestationalWeeks eq '3' ? "selected = 'selected'" : ""}>3周</option>
				<option value="4" ${report.gestationalWeeks eq '4' ? "selected = 'selected'" : ""}>4周</option>
				<option value="5" ${report.gestationalWeeks eq '5' ? "selected = 'selected'" : ""}>5周</option>
				<option value="6" ${report.gestationalWeeks eq '6' ? "selected = 'selected'" : ""}>6周</option>
				<option value="7" ${report.gestationalWeeks eq '7' ? "selected = 'selected'" : ""}>7周</option>
				<option value="8" ${report.gestationalWeeks eq '8' ? "selected = 'selected'" : ""}>8周</option>
				<option value="9" ${report.gestationalWeeks eq '9' ? "selected = 'selected'" : ""}>9周</option>
				<option value="10" ${report.gestationalWeeks eq '10' ? "selected = 'selected'" : ""}>10周</option>
				<option value="11" ${report.gestationalWeeks eq '11' ? "selected = 'selected'" : ""}>11周</option>
				<option value="12" ${report.gestationalWeeks eq '12' ? "selected = 'selected'" : ""}>12周</option>
				<option value="13" ${report.gestationalWeeks eq '13' ? "selected = 'selected'" : ""}>13周</option>
				<option value="14" ${report.gestationalWeeks eq '14' ? "selected = 'selected'" : ""}>14周</option>
				<option value="15" ${report.gestationalWeeks eq '15' ? "selected = 'selected'" : ""}>15周</option>
				<option value="16" ${report.gestationalWeeks eq '16' ? "selected = 'selected'" : ""}>16周</option>
				<option value="17" ${report.gestationalWeeks eq '17' ? "selected = 'selected'" : ""}>17周</option>
				<option value="18" ${report.gestationalWeeks eq '18' ? "selected = 'selected'" : ""}>18周</option>
				<option value="19" ${report.gestationalWeeks eq '19' ? "selected = 'selected'" : ""}>19周</option>
				<option value="20" ${report.gestationalWeeks eq '20' ? "selected = 'selected'" : ""}>20周</option>
			</select>
		</p>
      </div>  
	   
	   
	   
      <div class="m_list_1 b_solid ${report.birthsShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>出生时情况(多选)</p>
        <p class="payment_situation_title">
		   	 <input type="hidden" value="${report.birthsResult}" name="birthsResult"/>
		   	 <select name="births" id="births"  multiple="multiple" class="fs">
			    <option value="0">足月</option>
			    <option value="1">早产</option>
				<option value="2">顺产</option>
				<option value="3">剖腹产</option>
				<option value="4">产钳助产</option>
				<option value="5">吸引器助产</option>
				<option value="6">双胎</option>
				<option value="7">其他异常情况</option>
			</select>
		</p>
      </div>        


      <div class="m_list_1 b_solid" style="display:none">
        <p class="box"><span><img src="../images/m_tip_10.png" /></span></p>
        <p>早产矫正龄(多选)</p>
        <p>计算矫正龄<img src="../images/tip2.jpg" align="absmiddle" /></p>
      </div>   
          
     
      <div class="m_list_1 b_solid  m_top_10 ${report.consignorNameShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>完成问卷人姓名</p>
        <p><span>&nbsp;</span><input type="text" value="${report.consignorName}" id="consignorName" name="consignorName" ${empty report.consignorName ? "" : "disabled"}/></p>
      </div>    
      
      <div class="m_list_1 b_solid ${report.consignorTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>与儿童的关系</p>
        <p>
			<select name="consignorType" id="consignorType" class="fs" ${ empty report.consignorType ? "" : "disabled"}>
			    <option value="" ${empty report.consignorType ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.consignorType eq '1' ? "selected = 'selected'" : ""}>父母</option>
				<option value="2" ${report.consignorType eq '2' ? "selected = 'selected'" : ""}>祖父母</option>
				<option value="3" ${report.consignorType eq '3' ? "selected = 'selected'" : ""}>照看人</option>
				<option value="4" ${report.consignorType eq '4' ? "selected = 'selected'" : ""}>老师</option>
				<option value="5" ${report.consignorType eq '5' ? "selected = 'selected'" : ""}>其他</option>
			</select>
		</p>
      </div> 


      <div class="m_list_1 b_solid ${report.weightShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>出生时体重(克)</p>
        <p><span>&nbsp;</span><input type="text" value="${report.weight}" id="weight" name="weight" ${empty report.weight ? "" : "disabled"}/></p>
      </div> 

      <div class="m_list_1 b_solid ${report.addressShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>通信地址</p>
        <p><span>&nbsp;</span><input type="text" value="${report.address}" id="address" name="address" ${empty report.address ? "" : "disabled"}/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.zipShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>邮政编码</p>
        <p><span>&nbsp;</span><input type="text" value="${report.zip}" id="zip" name="zip" ${empty report.zip ? "" : "disabled"}/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.emailShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>电子邮箱</p>
        <p><span>&nbsp;</span><input type="text" value="${report.email}" id="email" name="email" ${empty report.email ? "" : "disabled"}/></p>
      </div>  
 
      
      <div class="m_list_1 b_solid ${report.telShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>联系电话</p>
        <p><span>&nbsp;</span><input type="text" value="${report.tel}" id="tel" name="tel" ${empty report.tel ? "" : "disabled"}/></p>
      </div>   


      <div class="m_list_1 b_solid ${report.patronnInfoShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_10.png" /></span></p>
        <p>协助填写问卷人</p>
        <p><span>&nbsp;</span><input type="text" value="${report.patronnInfo}" id="patronnInfo" name="patronnInfo" ${empty report.patronnInfo ? "" : "disabled"}/></p>
      </div> 

      
      <div class="m_list_1 b_solid ${report.motnerCultureDegreeShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲文化程度</p>
        <p>
			<select name="motherCultureDegree" id="motherCultureDegree" class="fs" ${ empty report.motherCultureDegree ? "" : "disabled"}>
			    <option value="" ${empty report.motherCultureDegree ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.motherCultureDegree eq '1' ? "selected = 'selected'" : ""}>小学</option>
				<option value="8" ${report.motherCultureDegree eq '8' ? "selected = 'selected'" : ""}>完成部分初中课程</option>
				<option value="2" ${report.motherCultureDegree eq '2' ? "selected = 'selected'" : ""}>初中毕业</option>
				<option value="3" ${report.motherCultureDegree eq '3' ? "selected = 'selected'" : ""}>完成部分高中课程</option>
				<option value="4" ${report.motherCultureDegree eq '4' ? "selected = 'selected'" : ""}>高中毕业</option>
				<option value="5" ${report.motherCultureDegree eq '5' ? "selected = 'selected'" : ""}>完成部分大学课程（至少一年）</option>
				<option value="6" ${report.motherCultureDegree eq '6' ? "selected = 'selected'" : ""}>大学毕业（大专或大学）</option>
				<option value="7" ${report.motherCultureDegree eq '7' ? "selected = 'selected'" : ""}>硕士毕业或以上</option>
			</select>
		</p>
      </div>  


      <div class="m_list_1 b_solid ${report.motherCareerCategoryShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲职业大类</p>
        <p>
			<select name="motherCareerCategory" id="motherCareerCategory" class="fs" ${ empty report.motherCareerCategory ? "" : "disabled"}>
			    <option value="" ${empty report.motherCareerCategory ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.motherCareerCategory eq '1' ? "selected = 'selected'" : ""}>第一大类</option>
				<option value="2" ${report.motherCareerCategory eq '2' ? "selected = 'selected'" : ""}>第二大类</option>
				<option value="3" ${report.motherCareerCategory eq '3' ? "selected = 'selected'" : ""}>第三大类</option>
				<option value="4" ${report.motherCareerCategory eq '4' ? "selected = 'selected'" : ""}>第四大类</option>
				<option value="5" ${report.motherCareerCategory eq '5' ? "selected = 'selected'" : ""}>第五大类</option>
				<option value="6" ${report.motherCareerCategory eq '6' ? "selected = 'selected'" : ""}>第六大类</option>
				<option value="7" ${report.motherCareerCategory eq '7' ? "selected = 'selected'" : ""}>第七大类</option>
				<option value="8" ${report.motherCareerCategory eq '8' ? "selected = 'selected'" : ""}>第八大类</option>
				<option value="9" ${report.motherCareerCategory eq '9' ? "selected = 'selected'" : ""}>第九大类</option>
				<option value="10" ${report.motherCareerCategory eq '10' ? "selected = 'selected'" : ""}>第十大类</option>
			</select>
		</p>
      </div>
      
      <div class="m_list_1 b_solid ${report.motherCareerShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲职业</p>
        <p>
			<select name="motherCareer" id="motherCareer" class="fs">
			</select>
		</p>
      </div>
      

      <div class="m_list_1 b_solid ${report.fatherCultureDegreeShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲文化程度</p>
        <p>
			<select name="fatherCultureDegree" id="fatherCultureDegree" class="fs" ${ empty report.fatherCultureDegree ? "" : "disabled"}>
			    <option value="" ${empty report.fatherCultureDegree ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.fatherCultureDegree eq '1' ? "selected = 'selected'" : ""}>小学</option>
				<option value="8" ${report.fatherCultureDegree eq '8' ? "selected = 'selected'" : ""}>完成部分初中课程</option>
				<option value="2" ${report.fatherCultureDegree eq '2' ? "selected = 'selected'" : ""}>初中毕业</option>
				<option value="3" ${report.fatherCultureDegree eq '3' ? "selected = 'selected'" : ""}>完成部分高中课程</option>
				<option value="4" ${report.fatherCultureDegree eq '4' ? "selected = 'selected'" : ""}>高中毕业</option>
				<option value="5" ${report.fatherCultureDegree eq '5' ? "selected = 'selected'" : ""}>完成部分大学课程（至少一年）</option>
				<option value="6" ${report.fatherCultureDegree eq '6' ? "selected = 'selected'" : ""}>大学毕业（大专或大学）</option>
				<option value="7" ${report.fatherCultureDegree eq '7' ? "selected = 'selected'" : ""}>硕士毕业或以上</option>
			</select>
		</p>
      </div> 


      <div class="m_list_1 b_solid ${report.fatherCareerCategoryShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲职业大类</p>
        <p>
			<select name="caregiversCultureDegree" id="fatherCareerCategory" class="fs" ${ empty report.fatherCareerCategory ? "" : "disabled"}>
			    <option value="" ${empty report.fatherCareerCategory ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.fatherCareerCategory eq '1' ? "selected = 'selected'" : ""}>第一大类</option>
				<option value="2" ${report.fatherCareerCategory eq '2' ? "selected = 'selected'" : ""}>第二大类</option>
				<option value="3" ${report.fatherCareerCategory eq '3' ? "selected = 'selected'" : ""}>第三大类</option>
				<option value="4" ${report.fatherCareerCategory eq '4' ? "selected = 'selected'" : ""}>第四大类</option>
				<option value="5" ${report.fatherCareerCategory eq '5' ? "selected = 'selected'" : ""}>第五大类</option>
				<option value="6" ${report.fatherCareerCategory eq '6' ? "selected = 'selected'" : ""}>第六大类</option>
				<option value="7" ${report.fatherCareerCategory eq '7' ? "selected = 'selected'" : ""}>第七大类</option>
				<option value="8" ${report.fatherCareerCategory eq '8' ? "selected = 'selected'" : ""}>第八大类</option>
				<option value="9" ${report.fatherCareerCategory eq '9' ? "selected = 'selected'" : ""}>第九大类</option>
				<option value="10" ${report.fatherCareerCategory eq '10' ? "selected = 'selected'" : ""}>第十大类</option>
			</select>
		</p>
      </div>  

	
	 <div class="m_list_1 b_solid ${report.fatherCareerShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲职业小类</p>
        <p>
			<select name="fatherCareer" id="fatherCareer" class="fs">
			</select>
		</p>
      </div>
	
	
	<div class="m_list_1 b_solid ${report.caregiversCultureDegreeShow ? '' : 'divhide'}">
	    <p class="box"><span></span></p>
	    <p>主要照顾者文化程度</p>
	    <p>
			<select name="caregiversCultureDegree" id="caregiversCultureDegree" class="fs" ${ empty report.caregiversCultureDegree ? "" : "disabled"}>
			    <option value="" ${empty report.caregiversCultureDegree ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.caregiversCultureDegree eq '1' ? "selected = 'selected'" : ""}>小学</option>
				<option value="8" ${report.caregiversCultureDegree eq '8' ? "selected = 'selected'" : ""}>完成部分初中课程</option>
				<option value="2" ${report.caregiversCultureDegree eq '2' ? "selected = 'selected'" : ""}>初中毕业</option>
				<option value="3" ${report.caregiversCultureDegree eq '3' ? "selected = 'selected'" : ""}>完成部分高中课程</option>
				<option value="4" ${report.caregiversCultureDegree eq '4' ? "selected = 'selected'" : ""}>高中毕业</option>
				<option value="5" ${report.caregiversCultureDegree eq '5' ? "selected = 'selected'" : ""}>完成部分大学课程（至少一年）</option>
				<option value="6" ${report.caregiversCultureDegree eq '6' ? "selected = 'selected'" : ""}>大学毕业（大专或大学）</option>
				<option value="7" ${report.caregiversCultureDegree eq '7' ? "selected = 'selected'" : ""}>硕士毕业或以上</option>
			</select>
	    </p>
	</div>

    <div class="m_list_1 b_solid ${report.motherBirthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>母亲生日*</p>
        <p>
			<input type="date" name="motherBirthDay" class="fs1" value="${report.motherBirthDay}" ${empty report.motherBirthDay ? "" : "disabled"}/>
		</p>
    </div>       
   
   <div class="m_list_1 b_solid ${report.fatherBirthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>父亲生日*</p>
        <p>
        	<input type="date" name="fatherBirthDay" class="fs1" value="${report.fatherBirthDay}" ${empty report.fatherBirthDay ? "" : "disabled"}/>
        </p>
   </div> 
   
   <div class="m_list_1 b_solid ${report.maritalStatusShow ? '' : 'divhide'}">
	    <p class="box"><span></span></p>
	    <p>婚姻状况</p>
	    <p>
			<select name="maritalStatus" id="maritalStatus" class="fs" ${ empty report.maritalStatus ? "" : "disabled"}>
			    <option value="" ${empty report.maritalStatus ? "selected = 'selected'" : ""}>请选择</option>
			    <option value="1" ${report.maritalStatus eq '1' ? "selected = 'selected'" : ""}>未婚</option>
				<option value="2" ${report.maritalStatus eq '2' ? "selected = 'selected'" : ""}>已婚</option>
				<option value="3" ${report.maritalStatus eq '3' ? "selected = 'selected'" : ""}>离异</option>
			</select>
	    </p>
	</div>
     
     
	</div>
    


   <div class="m_foot">
        <p><a href="#" id="createReportBtn"><span class="m_foot_l b_blue f_white">创建报告</span></a></p>
        <p><a href="#"><span class="m_foot_r b_white f_blue">取消</span></a></p>
    </div>
    
    </form>
</div>


<script type="text/javascript">
	var enterNo = "20252a32e38c44f9ac02ca623f4ee503";
	var scaleNo = "SCALENO001";
	var jobData = {
        Career1 : ['请选择','按日计酬的散工','公共空间清洁工','家庭清洁工','农场工人','食品零售','后厨食物制备工','餐馆楼面杂工'],
        Career2 : ['请选择','垃圾收集工','快餐厨子','出租车司机','鞋类销售员','流水线工人','泥瓦匠','行李搬运工'],
        Career3 : ['请选择','油漆工','建筑技工','售货员','卡车司机','厨师','销售柜台','办公室文员'],
        Career4 : ['请选择','汽车修理工','打字员','锁匠','农场工','木工','前台接待/总机','建筑工人','理发/发型师'],
        Career5 : ['请选择','机械师/机工','乐手/音乐制作人','簿记员/档案管理员','秘书','保险销售员','家具木工','人事管理','焊工'],
        Career6 : ['请选择','部门主管','图书管理员','航空机械师','视觉艺术工作者','电工','行政管理人员','现役军人','采购员'],
        Career7 : ['请选择','护士','熟练技工','医疗卫生专业技术员','顾问/咨询师','经理/经纪','警察','消防员','财务经理','物理治疗师','作业治疗师','言语治疗师'],
        Career8 : ['请选择','机械工程师','原子能工程师','电子工程师','教育行政人员','兽医','军官','中学教师','小学教师','特殊教育教师'],
        Career9 : ['请选择','医生','律师','大学任教老师','化工工程师','航天工程师','法官','首席执行官','高级管理人员','公务员','心理师','药剂师','会计师'],
        Career10 : ['请选择','未受雇佣','其它']
    }
	

$(function () {
	
	var motherCareerCategory = '${report.motherCareerCategory}';
	var motherCareer= '${report.motherCareer}';
	if(motherCareerCategory!=''){
		//赋值职业小类
		var html = '';
		html += '<option value=\""\>'+"请选择"+'</option>';
	    jobData['Career'+motherCareerCategory].map(function(val,i){
	          if(motherCareer != '' && motherCareer==i){
	          	  html += '<option value=\"'+i+'\" selected = \"selected\">'+val+'</option>';
	          }else{
	          	  html += '<option value=\"'+i+ '\">'+val+'</option>';
	          }
	    });
	    $("#motherCareer").empty().append(html);
	}
	
	$("#motherCareerCategory").on("change",function(){
		var type = $(this).val();
		if(type!=''){
			//赋值职业小类
			var html = '';
		    jobData['Career'+type].map(function(val,i){
		          html += '<option value=\"'+i+ '\">'+val+'</option>';
		    });
		    $("#motherCareer").empty().append(html);
		}
	}) 
//-----------

	var fatherCareerCategory = '${report.fatherCareerCategory}';
	var fatherCareer= '${report.fatherCareer}';
	if(fatherCareerCategory!=''){
		//赋值职业小类
		var html = '';
		html += '<option value=\""\>'+"请选择"+'</option>';
	    jobData['Career'+fatherCareerCategory].map(function(val,i){
	          if(fatherCareer != '' && fatherCareer==i){
	          	  html += '<option value=\"'+i+'\" selected = \"selected\">'+val+'</option>';
	          }else{
	          	  html += '<option value=\"'+i+ '\">'+val+'</option>';
	          }
	    });
	    $("#fatherCareer").empty().append(html);
	}
	
	$("#fatherCareerCategory").on("change",function(){
		var type = $(this).val();
		if(type!=''){
			//赋值职业小类
			var html = '';
		    jobData['Career'+type].map(function(val,i){
		          html += '<option value=\"'+i+ '\">'+val+'</option>';
		    });
		    $("#fatherCareer").empty().append(html);
		}
	}) 
	
    
    //创建报告按钮绑定事件
    $("#createReportBtn").on('click',function(){
        $("#reportForm").attr("action",util.requestURL+"/mobile/html/question");
        $("#reportForm").submit();
    })
    
    //施测者列表初始化,没传值，才去接口获取
    var medicName = '${report.medicName}';
    if(medicName==''){
    	$.ajax({
        	url: util.requestURL+'/api/v1/medicMchat/listMedic',
        	data:{"enterpriseNo":enterNo,"scaleNo":scaleNo},
            type: 'POST',
            success: function(data){
              if(data.code == 1){
              	  var user = data.data.recordList[0];
               	  $("#medicName").html(user.realName);
               	  $("#m_test_name input[name='medicNo']").val(user.medicNo);
               	  $("#m_test_name input[name='medicName']").val(user.realName);
               	  var html = "<option value=''>"+'切换'+"</option>"
				  var userList = data.data.recordList;
               	  for (var i = 0; i < userList.length; i++) {
						var user = userList[i];
						html += "<option value='"+user.medicNo+"'>"+user.realName+"</option>";	
				  }
				  $("#changeTestName").empty().append(html);
              }else{
              	  //接口获取失败
              	  $("#medicName").html("未查询到施测者信息");
              }
            }
        });
    }
    
	
	$("#changeTestName").on('change',function(){
		var medicNo = $(this).children('option:selected').val(); 
		var medicName = $(this).children('option:selected').html();
		if(medicNo!=''){
			$("#medicName").html(medicName);
	      	$("#m_test_name input[name='medicNo']").val(medicNo);
	      	$("#m_test_name input[name='medicName']").val(medicName);
		}
		
	})
    
});
	
	
	
	/**
	* 获取施测者列表
	*/
	function getTestName(){
		
		$.ajax({
	    	url: util.requestURL+'/api/v1/medicMchat/listMedic',
	    	data:{"enterpriseNo":enterNo,"scaleNo":scaleNo},
	        type: 'POST',
	        success: function(data){
	          if(data.code == 1){
	          	  var userList = data.data.recordList;
	           	  for (var i = 0; i < userList.length; i++) {
						var user = userList[i];
						$("#payment_testname_mask_ul").empty().append("<li value='"+user.medicNo+"'>"+user.realName+"</li>");
				  }
				  //展示列表框
				  $("#bg").css({
            			display: "block", 
            			height: $(document).height()
        		  });
			      var $box = $('.payment_testname_mask');
			      $box.css({
			            display: "block",
			      });
				  $(".payment_testname_mask li").on('click',function () {
				        $("#bg,.payment_testname_mask").css("display", "none");
				        $("#medicName").html($(this).html());
				        $("#m_test_name input[name='medicNo']").val($(this).attr('value'));
				        $("#m_test_name input[name='medicName']").val($(this).html());
    			  });
	          }else{
	          	  //接口获取失败
	          	  $("#medicName").html("未查询到施测者信息");
	          }
	        }
	    });
		
	}

</script>

</body>
</html>
