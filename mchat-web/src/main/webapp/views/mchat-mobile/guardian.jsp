<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>监护人</title>
<script type="text/javascript" src="../../mobile/js/zepto.min.js"></script>
<script type='text/javascript' src='../../mobile/js/sm.min.js' charset='utf-8'></script>
<script src="../../mobile/js/layer_mobile/layer.js"></script>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/util.js"></script>
<link rel="Stylesheet" type="text/css" href="../../mobile/css/style.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/m_1.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/mobile.css" />
<link rel="stylesheet" type="text/css" href="../../mobile/css/sm.min.css">
<link rel="stylesheet" type="text/css" href="../../mobile/css/payment.css" />

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
	input{border:0; text-align:right; margin-right:10px; width:80%;}
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
    <p class="m_guardian_read">使用说明</p>
    </div>
    
    <div id="m_test_name" class="m_list_1 b_solid">
    <p>施测者：<span id="testName">${report.medicName}</span>
    	<input type="hidden" name="medicNo" value="${report.medicNo}">
    	<input type="hidden" name="medicName" value="${report.medicName}">
    </p>
    <p><a href="javascript:void(0)" ${empty report.medicName ? "onclick='getTestName();'" : ""}>切换 <img src="../images/tip1.jpg" align="absmiddle" /></a></p>
    <div class="payment_testname_mask">
        <ul id="payment_testname_mask_ul">
        </ul>
    </div>	
    </div>



    <div class="m_mid">
      <div class="m_list b_solid ${report.testeeNameShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_1.png" /></span></p>
        <p>儿童姓名*</p>
        <p><span>&nbsp;</span><input type="text" value="${report.testeeName}" name="testeeName" id="testeeName" ${empty report.testeeName ? "" : "disabled='disabled'"}/></p>
      </div>
      
      
      <div class="m_list b_solid ${report.cardTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>身份信息*</p>
        <p class="payment_time_title">
            <input type="hidden" value="${report.cardType}" class="payment_time_value" name="cardType" id="cardType"/>
            <span>
            	<em style="font-style:normal">
            		<c:choose>
            			<c:when test="${report.cardType eq '0'}">
            				儿童身份证
            			</c:when>
            		</c:choose>
            		<c:choose>
            			<c:when test="${report.cardType eq '1'}">
            				父亲身份证
            			</c:when>
            		</c:choose>
            		<c:choose>
            			<c:when test="${report.cardType eq '2'}">
            				母亲身份证
            			</c:when>
            		</c:choose>
            		<c:choose>
            			<c:when test="${report.cardType eq '3'}">
            				诊疗卡号
            			</c:when>
            		</c:choose>
            		<c:choose>
            			<c:when test="${report.cardType eq '9'}">
            				其他
            			</c:when>
            		</c:choose>
            	</em>
            	 >
           	</span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_time_mask" >
            <ul>
                <li value="0">儿童身份证</li>
                <li value="1">父亲身份证</li>
                <li value="2">母亲身份证</li>
                <li value="3">诊疗卡号</li>
                <li value="9">其他</li>
            </ul>
        </div>
      </div>


      <div class="m_list b_solid ${report.cardNoShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_3.png" /></span></p>
        <p>身份证号码*</p>
        <p><span>&nbsp;</span><input type="text" value="${report.cardNo}" id="cardNo" name="cardNo" ${empty report.cardNo ? "" : "disabled='disabled'"}/></p>
      </div>      



      <div class="m_list_1 b_solid m_top_20 ${report.sexShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_4.png" /></span></p>
        <p>性别*</p>
        <p class="payment_sex_title">
            <c:choose>
            	<c:when test="${!empty report.sex}">
            		<c:if test="${report.sex eq '0'}">
            			<input type="hidden" value="0" class="payment_sex_value" id="sex" name="sex"/>
            			<span><em>男</em> ></span>	
            		</c:if>
            		<c:if test="${report.sex eq '1'}">
            			<input type="hidden" value="1" class="payment_sex_value" id="sex" name="sex"/>
            			<span><em>女</em> ></span>
            		</c:if>
                </c:when>
            	<c:otherwise>
            		<input type="hidden" value="0" class="payment_sex_value" id="sex" name="sex"/>
            		<span><em>男</em> ></span>		
            	</c:otherwise>
            </c:choose>
            <div id="bg">
            </div>
		</p>
        <div class="payment_sex_mask">
            <ul>
                <li value="0">男</li>
                <li value="1">女</li>
            </ul>
        </div>	
      </div>  
      
      <div class="m_list_1 b_solid ${report.birthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>出生日期*</p>
        <p><input id="my-input" type="text" name="birthDay" value="${report.birthDay}" ${empty report.birthDay ? "" : "disabled:'disabled'"}/><span style="margin-right:15px">></span></p>
      </div>  
      
      <div class="m_list_1 b_solid ${report.testDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>完成问卷日期*</p>
        <p><input id="my-input1" type="text" name="testDay" value="${report.testDay}" ${empty report.testDay ? "" : "disabled:'disabled'"}/><span style="margin-right:15px">></span></p>
      </div>  
      
      <div class="m_list_1 b_solid" style="display:none">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>儿童的生活龄</p>
        <p>计算年龄<img src="../images/tip2.jpg" align="absmiddle" /></p>
      </div>  


	  
      <div class="m_list_1 b_solid m_top_20 ${report.gestationalWeeksDaysShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_8.png" /></span></p>
        <p>宝宝的孕周*</p>
        <p class="payment_week_title">
            <input type="hidden" value="${empty report.gestationalWeeks ? 4 : report.gestationalWeeks}" class="payment_week_value" name="gestationalWeeks"/>
            <span><em>
            	<c:choose>
            		<c:when test="${!empty report.gestationalWeeks}">
            			${report.gestationalWeeks}
            		</c:when>
	            	<c:otherwise>4</c:otherwise>
	            </c:choose>
            	周</em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_week_mask">
            <ul style="height:300px; overflow-y:scroll ">
                <li value="1">1周</li>
                <li value="2">2周</li>
				<li value="3">3周</li>
				<li value="4">4周</li>
				<li value="5">5周</li>
				<li value="6">6周</li>
				<li value="7">7周</li>
				<li value="8">8周</li>
				<li value="9">9周</li>
				<li value="10">10周</li>
				<li value="11">11周</li>
				<li value="12">12周</li>
				<li value="13">13周</li>
				<li value="14">14周</li>
				<li value="15">15周</li>
				<li value="16">16周</li>
				<li value="17">17周</li>
				<li value="18">18周</li>
				<li value="19">19周</li>
				<li value="20">20周</li>			
            </ul>
        </div>	
      </div>  
	   
      <div class="m_list_1 b_solid ${report.birthsShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>出生时情况(多选)</p>
        <p class="payment_situation_title">
		    <input type="hidden" value="${report.births}" class="payment_situation_value" name="births"/>
            <span><em>
            	${report.birthsResult}
            </em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_situation_mask">
		    <div class="payment_close" style="background-color:#fff; height:30px; line-height:30px; border-bottom:1px #ccc solid">关闭选项</div>
			<div class="payment_clear" style="background-color:#fff;height:30px;line-height:30px; border-bottom:1px #ccc solid">清除选项</div>
			<div style="clear:left"></div>
            <ul>
                <li value="0">足月</li>
                <li value="1">早产</li>
                <li value="2">顺产</li>
                <li value="3">剖腹产</li>
                <li value="4">产钳助产</li>
                <li value="5">吸引器助产</li>
                <li value="6">双胎</li>
                <li value="7">其他异常情况</li>
            </ul>
        </div>	
      </div>        

      <div class="m_list_1 b_solid" style="display:none">
        <p class="box"><span><img src="../images/m_tip_10.png" /></span></p>
        <p>早产矫正龄(多选)</p>
        <p>计算矫正龄<img src="../images/tip2.jpg" align="absmiddle" /></p>
      </div>   
          
          
          
          
      <div class="m_list_1 b_solid  m_top_20 ${report.consignorNameShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>完成问卷人姓名</p>
        <p><span>&nbsp;</span><input type="text" value="${report.consignorName}" id="consignorName" name="consignorName" ${empty report.consignorName ? "" : "disabled:'disabled'"}/></p>
      </div>    
      
      <div class="m_list_1 b_solid ${report.consignorTypeShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_2.png" /></span></p>
        <p>与儿童的关系</p>
        <p class="payment_nexus_title">
            <input type="hidden" value="${report.consignorType}" class="payment_nexus_value" name="consignorType" ${ empty report.consignorType ? "" : "disabled:'disabled'"}/>
            <span><em>
            	<c:choose>
            		<c:when test="${report.consignorType eq '1'}">父母</c:when>
            		<c:when test="${report.consignorType eq '2'}">祖父母</c:when>
            		<c:when test="${report.consignorType eq '3'}">照看人</c:when>
            		<c:when test="${report.consignorType eq '4'}">老师</c:when>
            		<c:when test="${report.consignorType eq '5'}">其他</c:when>
            	</c:choose>
            </em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_nexus_mask">
            <ul>  
                <li value="1">父母</li>
                <li value="2">祖父母</li>
                <li value="3">照看人</li>
                <li value="4">老师</li>
                <li value="5">其他</li>
            </ul>
        </div>	
      </div> 

      <div class="m_list_1 b_solid ${report.weightShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_7.png" /></span></p>
        <p>出生时体重(克)</p>
        <p><span>&nbsp;</span><input type="text" value="${report.weight}" id="weight" name="weight" ${empty report.weight ? "" : "disabled:'disabled'"}/></p>
      </div> 

      <div class="m_list_1 b_solid ${report.addressShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_6.png" /></span></p>
        <p>通信地址</p>
        <p><span>&nbsp;</span><input type="text" value="${report.address}" id="address" name="address" ${empty report.address ? "" : "disabled:'disabled'"}/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.zipShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>邮政编码</p>
        <p><span>&nbsp;</span><input type="text" value="${report.zip}" id="zip" name="zip" ${empty report.zip ? "" : "disabled:'disabled'"}/></p>
      </div> 
      
      <div class="m_list_1 b_solid ${report.emailShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>电子邮箱</p>
        <p><span>&nbsp;</span><input type="text" value="${report.email}" id="email" name="email" ${empty report.email ? "" : "disabled:'disabled'"}/></p>
      </div>  
 
      
      <div class="m_list_1 b_solid ${report.telShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>联系电话</p>
        <p><span>&nbsp;</span><input type="text" value="${report.tel}" id="tel" name="tel" ${empty report.tel ? "" : "disabled:'disabled'"}/></p>
      </div>   


      <div class="m_list_1 b_solid ${report.patronnInfoShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_10.png" /></span></p>
        <p>协助填写问卷人</p>
        <p><span>&nbsp;</span><input type="text" value="${report.patronnInfo}" id="patronnInfo" name="patronnInfo" ${empty report.patronnInfo ? "" : "disabled:'disabled'"}/></p>
      </div> 

      
      <div class="m_list_1 b_solid ${report.motnerCultureDegreeShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲文化程度</p>
        <p class="payment_culture_title">
            <input type="hidden" value="${report.motherCultureDegree}" class="payment_culture_value" name="motherCultureDegree"/>
            <span><em>
            	<c:choose>
            		<c:when test="${!empty report.motherCultureDegree}">
            			<c:choose>
            				<c:when test="${report.motherCultureDegree eq '1'}">小学</c:when>
            				<c:when test="${report.motherCultureDegree eq '8'}">完成部分初中课程</c:when>
            				<c:when test="${report.motherCultureDegree eq '2'}">初中毕业</c:when>
            				<c:when test="${report.motherCultureDegree eq '3'}">完成部分高中课程</c:when>
            				<c:when test="${report.motherCultureDegree eq '4'}">高中毕业</c:when>
            				<c:when test="${report.motherCultureDegree eq '5'}">完成部分大学课程（至少一年）</c:when>
            				<c:when test="${report.motherCultureDegree eq '6'}">大学毕业（大专或大学）</c:when>
            				<c:when test="${report.motherCultureDegree eq '7'}">硕士毕业或以上</c:when>
            			</c:choose>
            		</c:when>
            		<c:otherwise>
            			请选择
            		</c:otherwise>
            	</c:choose>
            	</em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_culture_mask">
            <ul>
                <li value="1">小学</li>
                <li value="8">完成部分初中课程</li>
                <li value="2">初中毕业</li>
                <li value="3">完成部分高中课程</li>
                <li value="4">高中毕业</li>
                <li value="5">完成部分大学课程（至少一年）</li>
                <li value="6">大学毕业（大专或大学）</li>
                <li value="7">硕士毕业或以上</li>
            </ul>
        </div>	
      </div>  



      <div class="m_list_1 b_solid ${report.motherCareerCategoryShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲职业大类</p>
        <p class="payment_occupation_title">
            <input type="hidden" value="${report.motherCareerCategory}" class="payment_occupation_value" name="motherCareerCategory"/>
            <span><em>
				
				<c:choose>
            		<c:when test="${!empty report.motherCareerCategory}">
            			<c:choose>
            				<c:when test="${report.motherCareerCategory eq '1'}">第一大类</c:when> 				
            				<c:when test="${report.motherCareerCategory eq '2'}">第二大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '3'}">第三大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '4'}">第四大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '5'}">第五大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '6'}">第六大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '7'}">第七大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '8'}">第八大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '9'}">第九大类</c:when>
            				<c:when test="${report.motherCareerCategory eq '10'}">第十大类</c:when>
            			</c:choose>
            		</c:when>
            		<c:otherwise>
            			请选择
            		</c:otherwise>
            	</c:choose>
			
			</em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_occupation_mask">
            <ul>
                <li value="1">第一大类</li>
                <li value="2">第二大类</li>
                <li value="3">第三大类</li>
                <li value="4">第四大类</li>
                <li value="5">第五大类</li>
                <li value="6">第六大类</li>
                <li value="7">第七大类</li>
                <li value="8">第八大类</li>
                <li value="9">第九大类</li>
                <li value="10">第十大类</li>
            </ul>
        </div>	
      </div>
      
      <div class="m_list_1 b_solid ${report.motherCareerShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>母亲职业</p>
        <p class="payment_occupation_small_title">
            <input type="hidden" value="${report.motherCareer}" class="payment_occupation_small_value" name="motherCareer"/>
            <span><em>
    				<c:choose>
	            		<c:when test="${!empty report.motherCareer}">
	            			${report.motherCareer}
	            		</c:when>
	            		<c:otherwise>
	            			请选择小类
	            		</c:otherwise>
            		</c:choose>             
            </em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_occupation_small_mask">
            <ul>
            </ul>
        </div>	
      </div>
      

      <div class="m_list_1 b_solid ${report.fatherCultureDegreeShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲文化程度</p>
        <p class="payment_culture2_title">
            <input type="hidden" value="${report.fatherCultureDegree}" class="payment_culture2_value" name="fatherCultureDegree"/>
            <span><em>
 					
 					<c:choose>
            		<c:when test="${!empty report.fatherCultureDegree}">
            			<c:choose>
            				<c:when test="${report.fatherCultureDegree eq '1'}">小学</c:when>
            				<c:when test="${report.fatherCultureDegree eq '8'}">完成部分初中课程</c:when>
            				<c:when test="${report.fatherCultureDegree eq '2'}">初中毕业</c:when>
            				<c:when test="${report.fatherCultureDegree eq '3'}">完成部分高中课程</c:when>
            				<c:when test="${report.fatherCultureDegree eq '4'}">高中毕业</c:when>
            				<c:when test="${report.fatherCultureDegree eq '5'}">完成部分大学课程（至少一年）</c:when>
            				<c:when test="${report.fatherCultureDegree eq '6'}">大学毕业（大专或大学）</c:when>
            				<c:when test="${report.fatherCultureDegree eq '7'}">硕士毕业或以上</c:when>
            			</c:choose>
            		</c:when>
            		<c:otherwise>
            			请选择
            		</c:otherwise>
            	</c:choose>
            	
            	</em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_culture2_mask">
            <ul>
                <li value="1">小学</li>
                <li value="8">完成部分初中课程</li>
                <li value="2">初中毕业</li>
                <li value="3">完成部分高中课程</li>
                <li value="4">高中毕业</li>
                <li value="5">完成部分大学课程（至少一年）</li>
                <li value="6">大学毕业（大专或大学）</li>
                <li value="7">硕士毕业或以上</li>
            </ul>
        </div>	
      </div> 



      <div class="m_list_1 b_solid ${report.fatherCareerCategoryShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲职业大类</p>
        <p class="payment_occupation2_title">
            <input type="hidden" value="${report.fatherCareerCategory}" class="payment_occupation2_value" name="fatherCareerCategory"/>
            <span><em>
     			
     			<c:choose>
            		<c:when test="${!empty report.fatherCareerCategory}">
            			<c:choose>
            				<c:when test="${report.fatherCareerCategory eq '1'}">第一大类</c:when> 				
            				<c:when test="${report.fatherCareerCategory eq '2'}">第二大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '3'}">第三大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '4'}">第四大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '5'}">第五大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '6'}">第六大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '7'}">第七大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '8'}">第八大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '9'}">第九大类</c:when>
            				<c:when test="${report.fatherCareerCategory eq '10'}">第十大类</c:when>
            			</c:choose>
            		</c:when>
            		<c:otherwise>
            			请选择
            		</c:otherwise>
            	</c:choose>
            	       
            </em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_occupation2_mask">
            <ul>
                <li value="1">第一大类</li>
                <li value="2">第二大类</li>
                <li value="3">第三大类</li>
                <li value="4">第四大类</li>
                <li value="5">第五大类</li>
                <li value="6">第六大类</li>
                <li value="7">第七大类</li>
                <li value="8">第八大类</li>
                <li value="9">第九大类</li>
                <li value="10">第十大类</li>
            </ul>
        </div>	
      </div>  

	
	 <div class="m_list_1 b_solid ${report.fatherCareerShow ? '' : 'divhide'}">
        <p class="box"><span></span></p>
        <p>父亲职业小类</p>
        <p class="payment_occupation2_small_title">
            <input type="hidden" value="${report.fatherCareer}" class="payment_occupation2_small_value" name="fatherCareer"/>
            <span><em>
            		<c:choose>
	            		<c:when test="${!empty report.fatherCareer}">
	            			${report.fatherCareer}
	            		</c:when>
	            		<c:otherwise>
	            			请选择小类
	            		</c:otherwise>
            		</c:choose> 
            	</em> ></span>
            <div id="bg">
            </div>
		</p>
        <div class="payment_occupation2_small_mask">
            <ul>
            </ul>
        </div>	
      </div>
	
	
	<div class="m_list_1 b_solid ${report.caregiversCultureDegreeShow ? '' : 'divhide'}">
	    <p class="box"><span></span></p>
	    <p>主要照顾者文化程度</p>
	    <p class="payment_culture3_title">
	        <input type="hidden" value="${report.caregiversCultureDegree}" class="payment_culture3_value" name="caregiversCultureDegree"/>
	        <span><em>
	        	
	        	<c:choose>
            		<c:when test="${!empty report.caregiversCultureDegree}">
            			<c:choose>
            				<c:when test="${report.caregiversCultureDegree eq '1'}">小学</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '8'}">完成部分初中课程</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '2'}">初中毕业</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '3'}">完成部分高中课程</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '4'}">高中毕业</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '5'}">完成部分大学课程（至少一年）</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '6'}">大学毕业（大专或大学）</c:when>
            				<c:when test="${report.caregiversCultureDegree eq '7'}">硕士毕业或以上</c:when>
            			</c:choose>
            		</c:when>
            		<c:otherwise>
            			请选择
            		</c:otherwise>
            	</c:choose>
	        	
	        </em> ></span>
	        <div id="bg">
	        </div>
	    </p>
	    <div class="payment_culture3_mask">
	        <ul>
	            <li value="1">小学</li>
	            <li value="8">完成部分初中课程</li>
	            <li value="2">初中毕业</li>
	            <li value="3">完成部分高中课程</li>
	            <li value="4">高中毕业</li>
	            <li value="5">完成部分大学课程（至少一年）</li>
	            <li value="6">大学毕业（大专或大学）</li>
	            <li value="7">硕士毕业或以上</li>
	        </ul>
	    </div>
	</div>

    <div class="m_list_1 b_solid ${report.motherBirthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>母亲生日*</p>
        <p><input id="my-input2" type="text" name="motherBirthDay" value="${report.motherBirthDay}" ${empty report.motherBirthDay ? "" : "disabled:'disabled'"}/><span style="margin-right:15px">></span></p>
    </div>       
   
   <div class="m_list_1 b_solid ${report.fatherBirthDayShow ? '' : 'divhide'}">
        <p class="box"><span><img src="../images/m_tip_5.png" /></span></p>
        <p>父亲生日*</p>
        <p><input id="my-input3" type="text" name="fatherBirthDay" value="${report.fatherBirthDay}" ${empty report.fatherBirthDay ? "" : "disabled:'disabled'"}/><span style="margin-right:15px">></span></p>
   </div> 
   
   <div class="m_list_1 b_solid ${report.maritalStatusShow ? '' : 'divhide'}">
	    <p class="box"><span></span></p>
	    <p>婚姻状况</p>
	    <p class="payment_wedding_title">
	        <input type="hidden" value="${report.maritalStatus}" class="payment_wedding_value" name="maritalStatus"/>
	        <span><em>
	   			<c:choose>
	   				<c:when test="${!empty report.maritalStatus}">
	   					<c:choose>
	   						<c:when test="${report.maritalStatus eq '1'}">未婚</c:when>
	   						<c:when test="${report.maritalStatus eq '2'}">已婚</c:when>
	   						<c:when test="${report.maritalStatus eq '3'}">离异</c:when>
	   					</c:choose>
	   				</c:when>
	   				<c:otherwise>
	   					请选择
	   				</c:otherwise>
	   			</c:choose>     
	       </em> ></span>
	        <div id="bg">
	        </div>
	    </p>
	    <div class="payment_wedding_mask">
	        <ul>
	            <li value="1">未婚</li>
	            <li value="2">已婚</li>
	            <li value="3">离异</li>
	        </ul>
	    </div>
	</div>
     
     
     
   
    </div>
    


   <div class="m_foot">
        <p><a href="#" id="createReportBtn"><span class="m_foot_l b_blue f_white">创建报告</span></a></p>
        <p><a href="#"><span class="m_foot_r b_white f_blue">取消</span></a></p>
    </div>
    
    </form>
</div>

<script>jQuery.noConflict()</script>
<script type="text/javascript">
	var birthDay = '${report.birthDay}';
	var testDay = '${report.testDay}';
	var motherBirthDay = '${report.motherBirthDay}';
	var fatherBirthDay = '${report.fatherBirthDay}';
	if(birthDay==''){
		$("#my-input").calendar({
			value: '2015-12-05'
		});
	}
	if(testDay==''){
		$("#my-input1").calendar({
			value: '2015-12-05'
		});
	}
	if(motherBirthDay==''){
		$("#my-input2").calendar({
			value: '2015-12-05'
		});
	}
	if(fatherBirthDay==''){
		$("#my-input3").calendar({
			value: '2015-12-05'
		});
	}
</script>

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
	
	var births = '${report.births}';
	if(births==''){
		$(".payment_situation_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_situation_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    

    $(".payment_close").on('click',function () {
        $("#bg,.payment_situation_mask").css("display", "none");
    });
    $(".payment_clear").on('click',function () {
	    $(".payment_situation_title em").text("");
        $(".payment_situation_value").val("");
    });
    $(".payment_situation_mask li").on('click',function () {

	   var str = $(".payment_situation_value").val();
	　　var sear=new RegExp($(this).attr('value'));
	　　if(!sear.test(str)){
           $(".payment_situation_title em").text($(this).html()+";"+$(".payment_situation_title em").html());
		   $(".payment_situation_value").val($(this).attr('value')+";"+$(".payment_situation_value").val());
       }

    });
	
	
//------------	
	
	var sex = '${report.sex}';
	
	if(sex==''){
		$(".payment_sex_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_sex_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    
    $(".payment_sex_mask li").on('click',function () {
        $("#bg,.payment_sex_mask").css("display", "none");
        $(".payment_sex_title em").text($(this).html());
		$(".payment_sex_value").val($(this).attr('value'));
    });
	
//------------
	var gestationalWeeks = '${report.gestationalWeeks}';
	if(gestationalWeeks==''){
		$(".payment_week_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_week_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    $(".payment_week_mask li").on('click',function () {
        $("#bg,.payment_week_mask").css("display", "none");
        $(".payment_week_title em").text($(this).html());
		$(".payment_week_value").val($(this).attr('value'));
    });	
//------------	

	var consignorType =  '${report.consignorType}';
	if(consignorType == ''){
		 $(".payment_nexus_title").click(function () {
		        $("#bg").css({
		            display: "block", height: $(document).height()
		        });
		        var $box = $('.payment_nexus_mask');
		        $box.css({
		            display: "block",
		        });
		 });
	}
   
    $(".payment_nexus_mask li").on('click',function () {
        $("#bg,.payment_nexus_mask").css("display", "none");
        $(".payment_nexus_title em").text($(this).html());
		$(".payment_nexus_value").val($(this).attr('value'));
    });	
	
	
//------------	
	var motherCultureDegree = '${report.motherCultureDegree}';
	if(motherCultureDegree == ''){
		$(".payment_culture_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_culture_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    
    $(".payment_culture_mask li").on('click',function () {
        $("#bg,.payment_culture_mask").css("display", "none");
        $(".payment_culture_title em").text($(this).html());
		$(".payment_culture_value").val($(this).attr('value'));
    });	

//------------	
	var fatherCultureDegree= '${report.fatherCultureDegree}';
	if(fatherCultureDegree==''){
		$(".payment_culture2_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_culture2_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    
    $(".payment_culture2_mask li").on('click',function () {
        $("#bg,.payment_culture2_mask").css("display", "none");
        $(".payment_culture2_title em").text($(this).html());
		$(".payment_culture2_value").val($(this).attr('value'));
    });	

//-----------
	
	var caregiversCultureDegree = '${report.caregiversCultureDegree}';
	if(caregiversCultureDegree==''){
		$(".payment_culture3_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_culture3_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
	
    $(".payment_culture3_mask li").on('click',function () {
        $("#bg,.payment_culture3_mask").css("display", "none");
        $(".payment_culture3_title em").text($(this).html());
		$(".payment_culture3_value").val($(this).attr('value'));
    });	



//------------
	var motherCareerCategory = '${report.motherCareerCategory}';
	if(motherCareerCategory==''){
		$(".payment_occupation_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_occupation_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    
    $(".payment_occupation_mask li").on('click',function () {
        $("#bg,.payment_occupation_mask").css("display", "none");
        $(".payment_occupation_title em").text($(this).html());
        var type = $(this).attr('value');
		$(".payment_occupation_value").val(type);
		//赋值职业小类
		if(type != ''){
			var html = '';
	        jobData['Career'+type].map(function(val,i){
	          	html += '<li value="'+i+'" >'+val+'</li>';
	        });
	        $(".payment_occupation_small_mask ul").empty()
	        $(".payment_occupation_small_mask ul").append(html);
	        $(".payment_occupation_small_mask ul li").click(motherCarrerFun);
		}
    });		
	
	
//------
	 var motherCareer= '${report.motherCareer}';
	 if(motherCareer==''){
		 $(".payment_occupation_small_title").click(function () {
			 	var bigType = $(".payment_occupation_value").val();
			 	if(bigType != ''){
			 		$("#bg").css({
			            display: "block", height: $(document).height()
			        });
			        var $box = $('.payment_occupation_small_mask');
			        $box.css({
			            display: "block",
			        });
			 	}else{
			 		layer.open({
	                    content: '请先选择职业大类'
	                    ,skin: 'msg'
	                    ,time: 2 //2秒后自动关闭
	                });
			 	}
			 	
		    });
	 }
	 
//-----------
	var fatherCareerCategory= '${report.fatherCareerCategory}';
	if(fatherCareerCategory==''){
		$(".payment_occupation2_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_occupation2_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    
    $(".payment_occupation2_mask li").on('click',function () {
        $("#bg,.payment_occupation2_mask").css("display", "none");
        $(".payment_occupation2_title em").text($(this).html());
		var type = $(this).attr('value');
		$(".payment_occupation2_value").val(type);
		//赋值职业小类
		if(type != ''){
			var html = '';
	        jobData['Career'+type].map(function(val,i){
	          	html += '<li value="'+i+'" >'+val+'</li>';
	        });
	        $(".payment_occupation2_small_mask ul").empty()
	        $(".payment_occupation2_small_mask ul").append(html);
	        $(".payment_occupation2_small_mask ul li").click(fatherCarrerFun);
		}
    });		
	
//-----------	
	var fatherCareer = '${report.fatherCareer}';
	if(fatherCareer==''){
		$(".payment_occupation2_small_title").click(function () {
			var bigType = $(".payment_occupation2_value").val(); 
			if(bigType != ''){
				$("#bg").css({
			          display: "block", height: $(document).height()
			     });
			     var $box = $('.payment_occupation2_small_mask');
			     $box.css({
			          display: "block",
			     });
			}else{
				layer.open({
	                content: '请先选择职业大类'
	                ,skin: 'msg'
	                ,time: 2 //2秒后自动关闭
	            });
			}
		});
		
	}
	
//------------	
	var cardType = '${report.cardType}';
	if(cardType==''){
		$(".payment_time_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_time_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
    

    $(".payment_time_mask li").on('click',function () {
        $("#bg,.payment_time_mask").css("display", "none");
        $(".payment_time_title em").text($(this).html());
        $("#cardType").val($(this).attr("value"));
    });

//------------
	var maritalStatus = '${report.maritalStatus}';
	if(maritalStatus==''){
		$(".payment_wedding_title").click(function () {
	        $("#bg").css({
	            display: "block", height: $(document).height()
	        });
	        var $box = $('.payment_wedding_mask');
	        $box.css({
	            display: "block",
	        });
	    });
	}
	
    $(".payment_wedding_mask li").on('click',function () {
        $("#bg,.payment_wedding_mask").css("display", "none");
        $(".payment_wedding_title em").text($(this).html());
		$(".payment_wedding_value").val($(this).attr('value'));
    });	
	    
//-----------------    
    
    
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
               	  $("#testName").html(user.realName);
               	  $("#m_test_name input[name='medicNo']").val(user.medicNo);
               	  $("#m_test_name input[name='medicName']").val(user.realName);
              }else{
              	  //接口获取失败
              	  $("#testName").html("未查询到施测者信息");
              }
            }
        });
    }  
});
	
	
	function motherCarrerFun() {
	     $("#bg,.payment_occupation_small_mask").css("display", "none");
	     $(".payment_occupation_small_title em").text($(this).html());
	     var type = $(this).attr('value');
		 $(".payment_occupation_small_value").val(type);	
	}
	
	function fatherCarrerFun() {
	     $("#bg,.payment_occupation2_small_mask").css("display", "none");
	     $(".payment_occupation2_small_title em").text($(this).html());
	     var type = $(this).attr('value');
		 $(".payment_occupation2_small_value").val(type);	
	}
	
	
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
				        $("#testName").html($(this).html());
				        $("#m_test_name input[name='medicNo']").val($(this).attr('value'));
				        $("#m_test_name input[name='medicName']").val($(this).html());
    			  });
	          }else{
	          	  //接口获取失败
	          	  $("#testName").html("未查询到施测者信息");
	          }
	        }
	    });
		
	}

</script>

</body>
</html>
