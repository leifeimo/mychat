<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" /> 
<title>问卷调查</title>
<script type="text/javascript" src="../../mobile/js/zepto.min.js"></script>
<script type="text/javascript" src="../../mobile/js/mdialog.js"></script>
<script src="../../mobile/js/layer_mobile/layer.js"></script>
<script src="../../mobile/js/questions.js" ></script>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/util.js"></script>
<link rel="Stylesheet" type="text/css" href="../../mobile/css/style.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/m_2.css" />
<link rel="Stylesheet" type="text/css" href="../../mobile/css/mobile.css" />
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
</style>


</head>

<body>
	<form method="post" id="reportForm">
	<div id="contentDiv">
		<input type="hidden" name="enterpriseNo" value="${report.enterpriseNo}">
		<input type="hidden" name="scaleNo" value="${report.scaleNo}">
		<input type="hidden" name="medicNo" value="${report.medicNo}">
		<input type="hidden" name="medicName" value="${report.medicName}"/>
		<input type="hidden" name="testeeName" value="${report.testeeName}"/>
	    <input type="hidden" name="cardType" value="${report.cardType}">
	    <input type="hidden" name="cardNo" value="${report.cardNo}">
	    <input type="hidden" name="sex" value="${report.sex}">
	    <input type="hidden" name="birthDay" value="${report.birthDay}">
	    <input type="hidden" name="testDay" value="${report.testDay}">
	    <input type="hidden" name="gestationalWeeks" value="${report.gestationalWeeks}">
	    <input type="hidden" name="gestationalDays" value="${report.gestationalDays}">
	    <input type="hidden" name="births" value="${report.births}">
	    <input type="hidden" name="consignorName" value="${report.consignorName}">
	    <input type="hidden" name="consignorType" value="${report.consignorType}">
	    <input type="hidden" name="weight" value="${report.weight}">
	    <input type="hidden" name="address" value="${report.address}">
	    <input type="hidden" name="zip" value="${report.zip}">
	    <input type="hidden" name="email" value="${report.email}">
	    <input type="hidden" name="tel" value="${report.tel}">
	    <input type="hidden" name="remarks" value="${report.remarks}">
	    <input type="hidden" name="patronnInfo" value="${report.patronnInfo}">
	    <input type="hidden" name="motherCultureDegree" value="${report.motherCultureDegree}">
	    <input type="hidden" name="motherCareerCategory" value="${report.motherCareerCategory}">
	    <input type="hidden" name="motherCareer" value="${report.motherCareer}">
	    <input type="hidden" name="fatherCultureDegree" value="${report.fatherCultureDegree}">
	    <input type="hidden" name="fatherCareerCategory" value="${report.fatherCareerCategory}">
	    <input type="hidden" name="fatherCareer" value="${report.fatherCareer}">
	    <input type="hidden" name="caregiversCultureDegree" value="${report.caregiversCultureDegree}">
	    <input type="hidden" name="motherBirthday" value="${report.motherBirthday}">
	    <input type="hidden" name="fatherBirthday" value="${report.fatherBirthday}">
	    <input type="hidden" name="maritalStatus" value="${report.maritalStatus}">
	    <div id="m_guardian">
	    <p>&nbsp;</p>
	    <p>填写问卷</p>
	    </div>
	    
	    <div id="m_test_name">
	    <p>施测者：<span>${report.medicName}</span></p>  
	    <!-- <p>年龄组： 36&nbsp;&nbsp; </p> -->
	    </div>
		
	    <div class="m_mid">
	        <div class="m_question">
	            <p id="question">1、如果你指向房间内的某样物体，你的孩子会看它吗？（例如，你指着一个玩具或动物，你的孩子会看这个玩具或动物吗？）</p>
	        </div>
	        
	        
	        <div class="m_top_20">
	           <!--  <p class="m_q1"><a id="showPic" href="javascript:;"><img src="../../mobile/images/m_q_1.jpg" /></a></p> -->
	            <p class="m_q2"><a id="showGif" href="javascript:;"><img src="../../mobile/images/m_q_2.jpg" /></a></p>
	        </div>
	        
	
	        <div style="padding:10px;margin-top:20px;">
	            <p class="m_pass" data-value="Y" ><img id="m_pass_img" src="../../mobile/images/m_nopass.jpg"  align="absmiddle" />是</p>
	            <p class="m_nopass" data-value="N" ><img id="m_nopass_img" src="../../mobile/images/m_nopass.jpg" align="absmiddle"  />否</p>
	        </div>
	        
	        <div class="c_l"></div>
	          
	    </div>
	    
 
	    <div class="m_foot">
	        <p><a href="javascript:;" id="last"><span class="m_foot_l b_white f_blue">上一条</span></a></p>
	        <p><a href="javascript:;" id="next"><span class="m_foot_r b_blue f_white">下一条</span></a></p>
	    </div>
	</div>
	</form>

<script>
	var enterpriseNo = $("#contentDiv input[name='enterpriseNo']").val();
	var scaleNo = $("#contentDiv input[name='scaleNo']").val();
	var medicNo = $("#contentDiv input[name='medicNo']").val();
	var medicName = $("#contentDiv input[name='medicName']").val();
	var testeeName = $("#contentDiv input[name='testeeName']").val();
	var cardType = $("#contentDiv input[name='cardType']").val();
	var cardNo = $("#contentDiv input[name='cardNo']").val();
	var sex = $("#contentDiv input[name='sex']").val();
	if(sex!=null&&sex!=""){
		sex = parseInt(sex);
	}else{
		sex = -1;
	}
	var birthDay = $("#contentDiv input[name='birthDay']").val();  	
    var birthYear = 0;
    var birthMonth = 0;
    var birthToday = 0;
    if(birthDay!=null&&birthDay!=''){
    	birthYear = parseInt(birthDay.split("-")[0]);
    	birthMonth = parseInt(birthDay.split("-")[1]);
    	birthToday = parseInt(birthDay.split("-")[2]);
    }
    var testDay = $("#contentDiv input[name='testDay']").val();
    var testYear = 0;
    var testMonth = 0;
    var testToday = 0;
	if(testDay!=null&&testDay!=''){
		testYear=parseInt(testDay.split("-")[0]);
		testMonth=parseInt(testDay.split("-")[1]);
		testToday=parseInt(testDay.split("-")[2]);
	}
	var gestationalWeeks = $("#contentDiv input[name='gestationalWeeks']").val();
	if(gestationalWeeks!=null&&gestationalWeeks!=""){
		gestationalWeeks = parseInt(gestationalWeeks);
	}else{
		gestationalWeeks = 0;
	}
	var gestationalDays = $("#contentDiv input[name='gestationalDays']").val();
	if(gestationalDays!=null&&gestationalDays!=""){
		gestationalDays = parseInt(gestationalDays);
	}else{
		gestationalDays = 0;
	}
	var births = $("#contentDiv input[name='births']").val();
	var consignorName = $("#contentDiv input[name='consignorName']").val();
	var consignorType = $("#contentDiv input[name='consignorType']").val();
	var weight = $("#contentDiv input[name='weight']").val();
	if(weight!=null&&weight!=""){
		weight = parseInt(weight);
	}else{
		weight = 0;
	}
	var address = $("#contentDiv input[name='address']").val();
	var zip = $("#contentDiv input[name='zip']").val();
	var email = $("#contentDiv input[name='email']").val();
	var tel = $("#contentDiv input[name='tel']").val();
	var remarks = $("#contentDiv input[name='remarks']").val();
	var patronnInfo = $("#contentDiv input[name='patronnInfo']").val();
	var motherCultureDegree = $("#contentDiv input[name='motherCultureDegree']").val();
	if(motherCultureDegree!=null&&motherCultureDegree!=""){
		motherCultureDegree = parseInt(motherCultureDegree);
	}else{
		motherCultureDegree = 0;
	}
	var motherCareerCategory = $("#contentDiv input[name='motherCareerCategory']").val();
	if(motherCareerCategory!=null&&motherCareerCategory!=""){
		motherCareerCategory = parseInt(motherCareerCategory);
	}else{
		motherCareerCategory = 0;
	}
	var motherCareer = $("#contentDiv input[name='motherCareer']").val();
	var fatherCultureDegree = $("#contentDiv input[name='fatherCultureDegree']").val();
	if(fatherCultureDegree!=null&&fatherCultureDegree!=""){
		fatherCultureDegree = parseInt(fatherCultureDegree);
	}else{
		fatherCultureDegree = 0;
	}
	var fatherCareerCategory = $("#contentDiv input[name='fatherCareerCategory']").val();
	if(fatherCareerCategory!=null&&fatherCareerCategory!=""){
		fatherCareerCategory = parseInt(fatherCareerCategory);
	}else{
		fatherCareerCategory = 0;
	}
	var fatherCareer = $("#contentDiv input[name='fatherCareer']").val();
	var caregiversCultureDegree = $("#contentDiv input[name='caregiversCultureDegree']").val();
	if(caregiversCultureDegree!=null&&caregiversCultureDegree!=""){
		caregiversCultureDegree = parseInt(caregiversCultureDegree);
	}else{
		caregiversCultureDegree = 0;
	}
	var motherBirthday = $("#contentDiv input[name='motherBirthday']").val();
	var fatherBirthday = $("#contentDiv input[name='fatherBirthday']").val();
	var maritalStatus = $("#contentDiv input[name='maritalStatus']").val();
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
        email:email,
        tel: tel,
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
	
    $(document).ready(function(){
        var index = 0;
        var answer = {};
        $("#showGif").on('click',function(){
            layer.open({
                title: [
                  '动图',
                  'background-color: #00c2bc; color:#fff;'
                ]
                ,style: ''
                ,
                 btn: '关闭',
                 content: '<img class="gif" src="../../mobile/images/pic/'+(index+1)+'.gif" alt="" />'
            });
        });

        $("#showPic").on('click',function(){
            layer.open({
                title: [
                  '流程表',
                  'background-color: #00c2bc; color:#fff;'
                ]
                ,style: ''
                ,
                 btn: '关闭',
                 content: '<div><img src="../../mobile/images/m_pop.jpg" width="100%" height="100%" /></div>'
            });

        }); 

        $("#last").on('click',function(){
            if(index > 0){
                index -= 1
            }else{
              layer.open({
                content: '已经是第一题！'
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
              });
            }
            checkChoose();
            tabQuestion();
            
        });

        $("#next").on('click',function(){
            if(!check()){
                return false
            }
            if(index < questions.length-1 && !answer.hasOwnProperty('topic_20')){
                index += 1
            }
            checkChoose();
            tabQuestion();
            if(answer.hasOwnProperty('topic_20')){
                $(this).find('span').text('提交');
                for(key in answer){
                	obj1[key] = answer[key];
                }
                console.log(obj1);
                $.ajax({
                	url : util.requestURL+'/api/v1/medicMchat/createMchatReport',
                	data : obj1,
                	type : 'POST',
                	success : function(msg){
                		if(msg!=null&&msg.code==1){
                			new TipBox({
                				type:"success",
                				str:msg.msg,
                				hasBtn:true
                			})
                		}else{
                			new TipBox({
                				type:"error",
                				str:msg.msg,
                				hasBtn:true
                			})
                		}
                		setTimeout(function(){
                			window.location.href =  util.requestURL+"/mobile/html/report";
                		}, 3000);
                	}
                });
            }
        });

        function tabQuestion(){
            $("#question").text((index+1)+'.'+questions[index]);
        }

        $('.m_pass').on('click',function  () {
            document.getElementById("m_pass_img").src="../../mobile/images/m_pass.jpg";
            document.getElementById("m_nopass_img").src="../../mobile/images/m_nopass.jpg";
            answer['topic_'+(index+1)] = $(this).attr('data-value');
            if(answer.hasOwnProperty('topic_20')){
                $("#next").find('span').text('提交');
            }
        });
        
        $('.m_nopass').on('click',function  () {
            document.getElementById("m_pass_img").src="../../mobile/images/m_nopass.jpg";
            document.getElementById("m_nopass_img").src="../../mobile/images/m_pass.jpg";
            answer['topic_'+(index+1)] = $(this).attr('data-value');
            if(answer.hasOwnProperty('topic_20')){
                $("#next").find('span').text('提交');
            }
        });

        function check(){
            $('.m_nopass,.m_pass').find('img').attr('src','../../mobile/images/m_nopass.jpg');
            if(answer.hasOwnProperty('topic_'+(index+1))){
                return true;
            }else{

                 layer.open({
                    content: '请先答题'
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                  });
                return false;
            }
        }
        function checkChoose(){
            if(answer.hasOwnProperty('topic_'+(index+1))){
                if(answer['topic_'+(index+1)] == 'Y'){
                    document.getElementById("m_pass_img").src="../../mobile/images/m_pass.jpg";
                    document.getElementById("m_nopass_img").src="../../mobile/images/m_nopass.jpg";
                }else{
                    document.getElementById("m_pass_img").src="../../mobile/images/m_nopass.jpg";
                    document.getElementById("m_nopass_img").src="../../mobile/images/m_pass.jpg";
                }
            }
        } 
    });
	
	
	//返回信息页面
	function backToReport(){
		$("#reportForm").attr("action",util.requestURL+'/mobile/html/report');
		$("#reportForm").submit();
	}
</script>

</body>
</html>
