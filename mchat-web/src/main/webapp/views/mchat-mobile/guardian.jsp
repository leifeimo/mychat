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
<script type="text/javascript" src="../../mobile/js/mdialog.js"></script>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/util.js"></script>
<script src="../../mobile/js/picker/picker.min.js"></script>
<link rel="Stylesheet" type="text/css" href="../../mobile/css/style.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/m_1.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/mobile.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/payment.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/icons/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../../mobile/js/mdialog.css">
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
	
	.m-select-box {
		position:fixed;
		top:0;
		left:0;
		bottom: 0;
		width: 100%;
		height: 100%;
		background: rgba(0,0,0,.5);
		display: none;
		z-index: 9999;
	}

 
	.m-select-info{
		background: #0081d2;
		width: 90%;
		margin: 30% auto;
	}
	.m-select-info-t{
		text-align: center;
		color:#fff;
		border-bottom: 1px solid #fff;
		font-size:1.125rem;
		line-height: 32px;
	}
	.m-select-info-c-t{
		color:#fff;
		border-bottom: 1px solid #fff;
		font-size:1rem;
		line-height: 34px;
		padding: 0 0 0 8px;
	}
	.m-select-info-c-t-2{
		color:#fff;
		border-bottom: 1px solid #fff;
		font-size:1rem;
		line-height: 34px;
		padding: 0 0 0 8px;
	}
	.m-s-s{
		text-align: center;
		color:#fff;
		font-size:1.125rem;
		line-height: 32px;
	}
	.m-select-info-c-t i{
		float: right;
		margin: 0 8px 0 0;
		display: none;
	}
	.m-select-info-c-t-2 i{
		float: right;
		margin: 0 8px 0 0;
		display: none;
	}
	    .show-g {
	    display: block !important;
	}
</style>


</head>

<body>
	
	<!--新增s-->
    <div class="m-select-box">
        <div class="m-select-info">
            <div class="m-select-info-t">
                出生时情况（多选）
            </div>
            <div class="m-select-info-c">
                <div class="m-select-info-c-t">
                    足月
                    <i class="iconfont" data-id="0" data-value="足月">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    早产
                    <i class="iconfont" data-id="1" data-value="早产">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    顺产
                    <i class="iconfont" data-id="2" data-value="顺产">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    剖腹产
                    <i class="iconfont" data-id="3" data-value="剖腹产">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    产钳助产
                    <i class="iconfont" data-id="4" data-value="产钳助产">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    吸引器助产
                    <i class="iconfont" data-id="5" data-value="吸引器助产">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    双胎
                    <i class="iconfont" data-id="6" data-value="双胎">&#xe602;</i>
                </div>
                <div class="m-select-info-c-t">
                    其他异常情况
                    <i class="iconfont" data-id="7" data-value="其他异常情况">&#xe602;</i>
                </div>
            </div>
            <div class="m-s-s">确定</div>
        </div>
    </div>


	<div>
	<form action="" id="reportForm" accept-charset="UTF-8" method="post">
    <div id="m_guardian">
    <p class="m_faces"><img src="../images/faces.png" /></p>
    <p class="m_guardian_name f_white">监护人</p>
    <p class="m_guardian_read"><span>使用说明</span></p>
    </div>
    
    <div id="m_test_name" class="m_list_1 b_solid ${empty report.enterpriseNo ? 'divhide' : ''}">
    <p>施测者：<span id="medicName">${report.medicName}</span>
    	<input type="hidden" name="scaleNo" value="${report.scaleNo}">
    	<input type="hidden" name="enterpriseNo" value="${report.enterpriseNo}">
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
        <p><span>&nbsp;</span><input type="text" value="${report.testeeName}" name="testeeName" id="testeeName"}/></p>
      </div>
      
      
      <div class="m_list b_solid ${report.cardTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>身份信息*</p>
        <p>
			<select name="cardType" id="cardType" class="fs"}>
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
        <p><span>&nbsp;</span><input type="text" value="${report.cardNo}" id="cardNo" name="cardNo"/></p>
      </div>      


      <div class="m_list_1 b_solid m_top_20 ${report.sexShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_4.png" /></span></p>
        <p>性别*</p>
        <p>
			<select name="sex" id="sex" class="fs"}>
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
        	<input type="date" name="birthDay" class="fs1" value="${report.birthDay}"/>
        </p>
      </div>  
      
      <div class="m_list_1 b_solid ${report.testDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>完成问卷日期*</p>
        <p>
        	<input type="date" name="testDay" class="fs1" value="${report.testDay}"/>
        </p>
      </div>  
      
      <div class="m_list_1 b_solid" style="display:none">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>儿童的生活龄</p>
        <p>计算年龄<img src="../images/tip2.jpg" align="absmiddle" /></p>
      </div>  


	  
      <div class="m_list_1 b_solid m_top_20 ${report.gestationalWeeksDaysShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_8.png" style="height:20px;"/></span></p>
        <p>宝宝的孕周*</p> 
        <p>
        	<input type="hidden" name="gestationalWeeks" value="${report.gestationalWeeks}"/>
		    <input type="hidden" name="gestationalDays" value="${report.gestationalDays}"/>
		    <select id="gestationalWeeks" class="fs">
			    
			    <c:choose>
			    	<c:when test="${not empty report.gestationalWeeks && not empty report.gestationalDays}">
			    		<option selected="selected">${report.gestationalWeeks}周${report.gestationalDays}天</option>
			    	</c:when>
			    	<c:when test="${not empty report.gestationalWeeks && empty report.gestationalDays}">
			    		<option selected="selected">${report.gestationalWeeks}周</option>
			    	</c:when>
			    </c:choose>
			</select>
		</p>
      </div>  
	   
	   
	   
      <div class="m_list_1 b_solid ${report.birthsShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>出生时情况(多选)</p>
        <p class="payment_situation_title">
		   	 <input type="hidden" value="${report.births}" name="births" id="births"/>
		   	 <input type="text" class="fs1" value="${report.birthsResult}" name="birthsResult" id="birthsResult"/>
		   	 <!--
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
			  -->
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
        <p><span>&nbsp;</span><input type="text" value="${report.consignorName}" id="consignorName" name="consignorName"/></p>
      </div>    
      
      <div class="m_list_1 b_solid ${report.consignorTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>与儿童的关系</p>
        <p>
			<select name="consignorType" id="consignorType" class="fs">
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
        <p><span>&nbsp;</span><input type="text" value="${report.weight}" id="weight" name="weight"/></p>
      </div> 

      <div class="m_list_1 b_solid ${report.addressShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>通信地址</p>
        <p><span>&nbsp;</span><input type="text" value="${report.address}" id="address" name="address"/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.zipShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>邮政编码</p>
        <p><span>&nbsp;</span><input type="text" value="${report.zip}" id="zip" name="zip"/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.emailShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>电子邮箱</p>
        <p><span>&nbsp;</span><input type="text" value="${report.email}" id="email" name="email"/></p>
      </div>  
 
      
      <div class="m_list_1 b_solid ${report.telShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>联系电话</p>
        <p><span>&nbsp;</span><input type="text" value="${report.tel}" id="tel" name="tel"/></p>
      </div>   


      <div class="m_list_1 b_solid ${report.patronnInfoShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_10.png" /></span></p>
        <p>协助填写问卷人</p>
        <p><span>&nbsp;</span><input type="text" value="${report.patronnInfo}" id="patronnInfo" name="patronnInfo"/></p>
      </div> 

      
      <div class="m_list_1 b_solid ${report.motherCultureDegreeShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲文化程度</p>
        <p>
			<select name="motherCultureDegree" id="motherCultureDegree" class="fs">
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
			<select name="motherCareerCategory" id="motherCareerCategory" class="fs">
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
			<select name="fatherCultureDegree" id="fatherCultureDegree" class="fs">
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
			<select name="fatherCareerCategory" id="fatherCareerCategory" class="fs">
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
			<select name="caregiversCultureDegree" id="caregiversCultureDegree" class="fs">
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

    <div class="m_list_1 b_solid ${report.motherBirthdayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>母亲生日*</p>
        <p>
			<input type="date" name="motherBirthday" class="fs1" value="${report.motherBirthday}"/>
		</p>
    </div>       
   
   <div class="m_list_1 b_solid ${report.fatherBirthdayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>父亲生日*</p>
        <p>
        	<input type="date" name="fatherBirthday" class="fs1" value="${report.fatherBirthday}"/>
        </p>
   </div> 
   
   <div class="m_list_1 b_solid ${report.maritalStatusShow ? '' : 'divhide'}">
	    <p class="box"><span></span></p>
	    <p>婚姻状况</p>
	    <p>
			<select name="maritalStatus" id="maritalStatus" class="fs">
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
	var enterNo = '${report.enterpriseNo}';
	var scaleNo = '${report.scaleNo}';
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
	
	
	var GWpicker, weekList, dayList;
	function initControl(){
        //出生情况
	    $(".payment_situation_title").bind("touchstart", function () {
	        $(".m-select-box").show();
	        $("body").css({ overflow: "hidden" })
	    }); 
	    $(".m-select-box").bind("touchstart", function () {
	        $(".m-select-box").hide();
	        $("body").css({ overflow: null })
	    });
	    $(".m-select-info").bind("touchstart", function () {
	        event.stopPropagation();
	    }); 
	    $(".m-select-info-c-t").on("touchstart", function () {
	        $(this).css({ background: "#025d97" });
	        if ($(this).children("i").hasClass("show-g")) {
	            $(this).children("i").removeClass("show-g");
	        } else {
	            $(this).children("i").addClass("show-g");
	        }
	    })
	    $(".m-select-info-c-t").on("touchend", function () {
	        $(this).css({ background: "#0081d2" });
	    })

	    $(".m-s-s").on("touchstart", function () {
	        $(".m-select-box").hide(); 
	        $("body").css({ overflow: null }) 
	        getBirthArray(); 
	    });
	    
	    //根据参数默认选中
	    var birthsResult = "${report.birthsResult}";
	    if(birthsResult!=null&&birthsResult!=undefined&&birthsResult!=""){
	    	var arr = birthsResult.split(";");
	    	for(i=0;i<arr.length;i++){
	    		$(".m-select-info-c .m-select-info-c-t i[data-value='"+arr[i]+"']").addClass("show-g");
	    	}
	    }
	    // end--- 出生情况
	}
	
	function initWeekDayControl(){
		//孕周
	    weekList = new Array();
	    for (var i = 0; i <= 40; i++) {
	        var week = (i + 20);
	        weekList[i] = {text:week+"周",value:week};
	    }
	    dayList = new Array();
	    for (var i = 0; i < 7; i++) {
	        dayList[i] = { text: i + "天", value: i };
	    } 
	    GWpicker = new Picker({
	        data: [weekList, dayList]
	    });

	    $("#gestationalWeeks").on("touchstart", function () {
	        GWpicker.show();
	    });
	    GWpicker.on('picker.change', function (index, selectedIndex) {
	       // alert(index);
	    });
	    GWpicker.on('picker.select', function (selectedVal, selectedIndex) {
	       // debugger;
	        var v1 = (selectedIndex + "").split(",")[0];
	        var v2 = (selectedIndex + "").split(",")[1];
	        
	        var GWValue = weekList[v1].value + "," + dayList[v2].value;
	        var GWText = weekList[v1].text + dayList[v2].text;
			
	        var gestationalWeeks = weekList[v1].value;
	        var gestationalDays = dayList[v2].value;
	      	$("#reportForm input[name='gestationalWeeks']").val(gestationalWeeks);
	      	$("#reportForm input[name='gestationalDays']").val(gestationalDays);
	        var html = "<option selected='selected'>"+GWText+"</option>";
	      	$("#gestationalWeeks").empty().append(html);
	    });  
        // end --孕周
	}
	
	function getBirthArray() {
	    var birthsArray = "";
	    var birthsArrayName = "";
	    $.each($(".m-select-info-c-t i"), function (i, v) {
	        if ($(this).hasClass("show-g")) {
	            birthsArray += $(this).attr("data-id") + ";";
	            birthsArrayName += $(this).attr("data-value") + ";";
	        }
	    });
	    if (birthsArray != "") {
	        birthsArray = birthsArray.substring(0, birthsArray.length - 1);
	        birthsArrayName = birthsArrayName.substring(0, birthsArrayName.length - 1);
	    }
	    //alert("birthsArray="+birthsArray);
	    //alert("birthsArrayName=" + birthsArrayName);
	    $("#births").val(birthsArray);
	    $("#birthsResult").val(birthsArrayName);
	}


$(function () {
	
	//var births = '${report.births}';
	//if(births==''){
		initControl();
	//}
	
	var gestationalWeeks = '${report.gestationalWeeks}';
	if(gestationalWeeks==''){
		initWeekDayControl();
	}
	
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
	
    //使用说明
    $(".m_guardian_read").on('click',function(){
    	var url = util.requestURL+"/mobile/html/instructions";
    	window.location.href = url;
    })
	
	
    //创建报告按钮绑定事件
    $("#createReportBtn").on('click',function(){
    	var enterpriseNo = $("#reportForm input[name='enterpriseNo']").val();
    	var scaleNo = $("#reportForm input[name='scaleNo']").val();
    	var medicNo = $("#reportForm input[name='medicNo']").val();
    	var medicName = $("#reportForm input[name='medicName']").val();
    	var testeeName = $("#reportForm input[name='testeeName']").val();
    	var cardType = $("#cardType").children('option:selected').val(); 
    	var cardNo = $("#reportForm input[name='cardNo']").val();
    	var sex = $("#sex").children('option:selected').val();
    	if(sex!=null&&sex!=""){
    		sex = parseInt(sex);
    	}else{
    		sex = -1;
    	}
    	var birthDay = $("#reportForm input[name='birthDay']").val();  	
        var birthYear = 0;
        var birthMonth = 0;
        var birthToday = 0;
        if(birthDay!=null&&birthDay!=''){
        	birthYear = parseInt(birthDay.split("-")[0]);
        	birthMonth = parseInt(birthDay.split("-")[1]);
        	birthToday = parseInt(birthDay.split("-")[2]);
        }
        var testDay = $("#reportForm input[name='testDay']").val();
        var testYear = 0;
        var testMonth = 0;
        var testToday = 0;
    	if(testDay!=null&&testDay!=''){
    		testYear=parseInt(testDay.split("-")[0]);
    		testMonth=parseInt(testDay.split("-")[1]);
    		testToday=parseInt(testDay.split("-")[2]);
    	}
    	var gestationalWeeks = $("#reportForm input[name='gestationalWeeks']").val();
    	if(gestationalWeeks!=null&&gestationalWeeks!=""){
    		gestationalWeeks = parseInt(gestationalWeeks);
    	}else{
    		gestationalWeeks = 0;
    	}
    	var gestationalDays = $("#reportForm input[name='gestationalDays']").val();
    	if(gestationalDays!=null&&gestationalDays!=""){
    		gestationalDays = parseInt(gestationalDays);
    	}else{
    		gestationalDays = 0;
    	}
    	var births = $("#reportForm input[name='births']").val();
    	var consignorName = $("#reportForm input[name='consignorName']").val();
    	var consignorType = $("#consignorType").children('option:selected').val();
    	var weight = $("#reportForm input[name='weight']").val();
    	if(weight!=null&&weight!=""){
    		weight = parseInt(weight);
    	}else{
    		weight = 0;
    	}
    	var address = $("#reportForm input[name='address']").val();
    	var zip = $("#reportForm input[name='zip']").val();
    	var email = $("#reportForm input[name='email']").val();
    	var tel = $("#reportForm input[name='tel']").val();
    	var remarks = $("#reportForm input[name='remarks']").val();
    	var patronnInfo = $("#reportForm input[name='patronnInfo']").val();
    	var motherCultureDegree = $("#motherCultureDegree").children('option:selected').val();
    	if(motherCultureDegree!=null&&motherCultureDegree!=""){
    		motherCultureDegree = parseInt(motherCultureDegree);
    	}else{
    		motherCultureDegree = 0;
    	}
    	var motherCareerCategory = $("#motherCareerCategory").children('option:selected').val();
    	if(motherCareerCategory!=null&&motherCareerCategory!=""){
    		motherCareerCategory = parseInt(motherCareerCategory);
    	}else{
    		motherCareerCategory = 0;
    	}
    	var motherCareer = $("#motherCareer").children('option:selected').val();
    	var fatherCultureDegree = $("#fatherCultureDegree").children('option:selected').val();
    	if(fatherCultureDegree!=null&&fatherCultureDegree!=""){
    		fatherCultureDegree = parseInt(fatherCultureDegree);
    	}else{
    		fatherCultureDegree = 0;
    	}
    	var fatherCareerCategory = $("#fatherCareerCategory").children('option:selected').val();
    	if(fatherCareerCategory!=null&&fatherCareerCategory!=""){
    		fatherCareerCategory = parseInt(fatherCareerCategory);
    	}else{
    		fatherCareerCategory = 0;
    	}
    	var fatherCareer = $("#fatherCareer").children('option:selected').val();
    	var caregiversCultureDegree = $("#caregiversCultureDegree").children('option:selected').val();
    	if(caregiversCultureDegree!=null&&caregiversCultureDegree!=""){
    		caregiversCultureDegree = parseInt(caregiversCultureDegree);
    	}else{
    		caregiversCultureDegree = 0;
    	}
    	var motherBirthday = $("#reportForm input[name='motherBirthday']").val();
    	var fatherBirthday = $("#reportForm input[name='fatherBirthday']").val();
    	var maritalStatus = $("#maritalStatus").children('option:selected').val(); 
    	if(maritalStatus!=null&&maritalStatus!=""){
    		maritalStatus = parseInt(maritalStatus);
    	}else{
    		maritalStatus = 0;
    	}
    	
		var obj1 = {
			enterpriseNo : enterpriseNo,
			scaleNo : scaleNo,
			medicNo : medicNo,
			medicName:medicName,	
	        testeeName: testeeName,
	        sex: sex,
	        cardType: cardType,
	        cardNo: cardNo,
	        birthDay: birthDay,
	        birthYear : birthYear,
	        birthMonth : birthMonth,
	        birthToday : birthToday,
	        testDay: testDay,
	        testYear : testYear,
	        testMonth : testMonth,
	        testToday : testToday,
	        gestationalWeeks: gestationalWeeks,
	        gestationalDays : gestationalDays,
	        births: births,
	        level: 1,
	        consignorName: consignorName,
	        consignorType: consignorType,
			birthWeight: weight,
	        address: address,
	        zip: zip,
	        tel: tel,
	        email : email,
	        remarks: remarks,
	        patronnInfo:patronnInfo,
	        motherCultureDegree: motherCultureDegree,
	        motherCareerCategory: motherCareerCategory,
	        motherCareer: motherCareer,
	        fatherCultureDegree: fatherCultureDegree,
	        fatherCareerCategory: fatherCareerCategory,
	        fatherCareer: fatherCareer,
	        caregiversCultureDegree: caregiversCultureDegree,
	        motherBirthday: motherBirthday,
	        fatherBirthday: fatherBirthday,      
	        maritalStatus: maritalStatus
    	}    	
    	console.log(obj1);
    	$.ajax({
        	type : "POST",
        	url : util.requestURL+"/api/v1/medicMchat/verifyBasicInformation",
        	data : obj1,
        	success : function(data){
        		if(data.code=='1'){
        			$("#reportForm").attr("action",util.requestURL+"/mobile/html/question");
                    $("#reportForm").submit();
        		}else{
        			new TipBox({
        				type:"error",
        				str:data.msg,
        				hasBtn:true
        			})
        		}
        	}
        })
    	
    	
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
               	  //$("#m_test_name input[name='enterpriseNo']").val(user.enterpriseNo);
               	  $("#m_test_name input[name='medicNo']").val(user.medicNo);
               	  $("#m_test_name input[name='medicName']").val(user.realName);
               	  var html = "<option value='' enterpriseNo=''>"+'切换'+"</option>"
				  var userList = data.data.recordList;
               	  for (var i = 0; i < userList.length; i++) {
						var user = userList[i];
						html += "<option value='"+user.medicNo+"' enterpriseNo='"+user.enterpriseNo+"'>"+user.realName+"</option>";	
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
		//var enterpriseNo = $(this).children('option:selected').attr('enterpriseNo');
		if(medicNo!=''){
			$("#medicName").html(medicName);
	      	$("#m_test_name input[name='medicNo']").val(medicNo);
	      	$("#m_test_name input[name='medicName']").val(medicName);
	      	//$("#m_test_name input[name='enterpriseNo']").val(enterpriseNo);
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
