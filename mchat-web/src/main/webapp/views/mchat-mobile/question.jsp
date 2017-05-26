<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

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
	<div id="contentDiv">
		<input type="hidden" name="medicName" value="${report.medicName}"/>
		<input type="hidden" name="testeeName" value="${report.testeeName}"/>
	    <input type="hidden" name="cardType" value="${report.cardType}">
	    <input type="hidden" name="cardNo" value="${report.cardNo}">
	    <input type="hidden" name="sex" value="${report.sex}">
	    <input type="hidden" name="birthDay" value="${report.birthDay}">
	    <input type="hidden" name="testDay" value="${report.testDay}">
	    <input type="hidden" name="gestationalWeeks" value="${report.gestationalWeeks}">
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
	    <input type="hidden" name="fatherCareerCategory" value="${report.fatherCareerCategory}">
	    <input type="hidden" name="caregiversCultureDegree" value="${report.caregiversCultureDegree}">
	    <input type="hidden" name="motherBirthDay" value="${report.motherBirthDay}">
	    <input type="hidden" name="fatherBirthDay" value="${report.fatherBirthDay}">
	    <input type="hidden" name="maritalStatus" value="${report.maritalStatus}">
	    <div id="m_guardian">
	    <p>< 返回</p>
	    <p>填写问卷</p>
	    </div>
	    
	    <div id="m_test_name">
	    <p>施测者：<span>${report.medicName}</span></p>
	    <p>年龄组： 36&nbsp;&nbsp; </p>
	    </div>
		
	    <div class="m_mid">
	        <div class="m_question">
	            <p id="question">1、如果你指向房间内的某样物体，你的孩子会看它吗？（例如，你指着一个玩具或动物，你的孩子会看这个玩具或动物吗？）</p>
	        </div>
	        
	        
	        <div class="m_top_20">
	            <p class="m_q1"><a id="showPic" href="javascript:;"><img src="../../mobile/images/m_q_1.jpg" /></a></p>
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

<script>
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
	var testDay = $("#contentDiv input[name='testDay']").val();
	var gestationalWeeks = $("#contentDiv input[name='gestationalWeeks']").val();
	if(gestationalWeeks!=null&&gestationalWeeks!=""){
		gestationalWeeks = parseInt(gestationalWeeks);
	}else{
		gestationalWeeks = 0;
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
	var motherCareerCategory = $("#contentDiv input[name='motherCareerCategory']").val();
	if(motherCareerCategory!=null&&motherCareerCategory!=""){
		motherCareerCategory = parseInt(motherCareerCategory);
	}else{
		motherCareerCategory = 0;
	}
	var motherCareer = $("#contentDiv input[name='motherCareer']").val();
	var fatherCultureDegree = $("#contentDiv input[name='fatherCultureDegree']").val();
	var fatherCareerCategory = $("#contentDiv input[name='fatherCareerCategory']").val();
	if(fatherCareerCategory!=null&&fatherCareerCategory!=""){
		fatherCareerCategory = parseInt(fatherCareerCategory);
	}else{
		fatherCareerCategory = 0;
	}
	var fatherCareer = $("#contentDiv input[name='fatherCareer']").val();
	var caregiversCultureDegree = $("#contentDiv input[name='caregiversCultureDegree']").val();
	var motherBirthDay = $("#contentDiv input[name='motherBirthDay']").val();
	var fatherBirthDay = $("#contentDiv input[name='fatherBirthDay']").val();
	var maritalStatus = $("#contentDiv input[name='maritalStatus']").val();
	
	var obj1 = {
		medicName:medicName,	
        testeeName: testeeName,
        sex: sex,
        cardType: cardType,
        cardNo: cardNo,
        birthDay: birthDay,
        birthYear: parseInt(birthDay.split("-")[0]),
        birthMonth: parseInt(birthDay.split("-")[1]),
        birthToday: parseInt(birthDay.split("-")[2]),
        testDay: testDay,
        testYear: parseInt(testDay.split("-")[0]),
        testMonth: parseInt(testDay.split("-")[1]),
        testToday: parseInt(testDay.split("-")[2]),
        gestationalWeeks: gestationalWeeks,
        births: births,
        level: 1,
        consignorName: consignorName,
        consignorType: consignorType,
		birthWeight: weight,
        address: address,
        zip: zip,
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
        motherBirthDay: motherBirthDay,
        fatherBirthDay: fatherBirthDay,      
        maritalStatus: maritalStatus
      }
	
    $(document).ready(function(){
        var index = 0;
        var answer = {};
        $("#showGif").on('click',function(){
            layer.open({
                title: [
                  '视频',
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
                				setTime:3000,
                				type:"success",
                				str:msg.msg,
                				hasBtn:true
                			})
                		}else{
                			new TipBox({
                				setTime:3000,
                				type:"error",
                				str:msg.msg,
                				hasBtn:true
                			})
                		}
                		window.location.href =  util.requestURL+"/mobile/html/guardian.html"
                	}
                });
            }
            checkChoose();
            tabQuestion();
            
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
</script>

</body>
</html>
