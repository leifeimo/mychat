<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>登录页面</title>
<script src="js/jquery.min.js"></script>
<!--[if lte IE 9]><script src="js/selectivizr-min.js"></script><![endif]-->
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/pc_2.css" />
<style>

.login_tip{
	height:60px;
	line-height:60px;
	color:#d6edc4;
}
#content{
	background-image:url(images/bg4.jpg) !important;
}
.l_login{
	background-color:#8dcac2;
	width:292px;
	height:360px;
	padding:20px;
	border:1px #accdd2 solid;
	-moz-box-shadow:0px 0px 5px #ADADAD; -webkit-box-shadow:0px 0px 5px #ADADAD; box-shadow:0px 0px 5px #ADADAD;
	-moz-border-radius:5px; 
    border-radius: 5px; 
}


.l_login_cho p{
	float:left;
	width:146px;
	height:48px;
	line-height:48px;
	text-align:center;
	-moz-border-radius:15px 15px 0 0; 
    border-radius: 15px 15px 0 0;
	cursor:pointer;
}
.l_login_cho p:nth-child(1){
	background-color:#d6edc4;
	color:#467476;
	
}
.l_login_cho p:nth-child(2){
	background-color:#298793;
	color:#e2eff1;
	background-image:url(images/login_5.jpg);
	
}
.l_login_cho img{
	vertical-align:middle	
}



.log_form{
	clear:left;
	height:310px;
	background-color:#d6edc4;
	border:1px #bbdfbe solid;
}
.log_form p{
	margin-top:45px;
	margin-left:26px;
}
.lbg1{
    background: url(images/login_1.jpg) no-repeat scroll left center transparent;
}
.lbg2{
    background: url(images/login_2.jpg) no-repeat scroll left center transparent;
}
.login_tex{
	width:166px;
    padding-left: 70px;
	background-color:#FFF;
	height:45px;
	line-height:45px;
	border:0;
	-moz-border-radius:4px; 
    border-radius: 4px; 
}

.login_tex:hover
{
	width:164px;
	height:43px;
	line-height:43px;
	border:1px #469ca5 solid;
}

.login_btn{
	width:237px;
	height:45px;
	line-height:45px;
	border:1px #469ca5 solid;
	background: url(images/lbtn.jpg) no-repeat scroll left center transparent;
	color:#fff;
	-moz-border-radius:4px; 
    border-radius: 4px;
	cursor:pointer;
}
.login_btn:hover{
	background-image:!important;
	color:#ffa200;
	border:1px #ffa200 solid;

}
.login_btn2{
	width:237px;
	height:45px;
	line-height:45px;
	border:1px #469ca5 solid;
	background-color:#d6edc4;
	color:#397c92;
	-moz-border-radius:4px; 
    border-radius: 4px;
	cursor:pointer;
}

.login_btn2:hover{
	color:#ffa200;
	border:1px #ffa200 solid;
}


.forget{
	display:block;
	float:right;
	margin:10px 40px 0 0; 
	color:#65948e; 
	font-size:12px;
}
.forget a{
	text-decoration:underline;
}
#tab2{
	background-image:url(images/login_6.jpg) !important;
	 border:1px #3e979e solid !important;
}
#tab2 .forget{
	color:#fff;
}
</style>
</head>

<body>


<div id="content">
    <div id="head" class="b_blues" style="height:60px; width:100%">
        <div id="head_top">
		    <div style="float:left">
	        	<div class="head_logo"><img src="<%=basePath%>images/logo.jpg" /></div>
			</div>
			<div id="login_out">
			    <p class="login_tip">
				请您登录系统平台！
				</p>
				
			</div>
		</div>
	</div>
    
    <div class="l_login">
          <div class="l_login_cho">
              <p id="tab_1"><img src="<%=basePath%>images/login_3.png"/>&nbsp;&nbsp;测试者登录</p>
              <p id="tab_2"><img src="<%=basePath%>images/login_4.png" />&nbsp;&nbsp;管理员登录</p>
          </div>
          
          <div class="log_form" id="tab1">
              <p>
                  <input type="text" class="login_tex lbg1" placeholder="请输入您的账号" />
              </p>
              <p>
                  <input type="text" class="login_tex lbg2" placeholder="请输入您的密码" />
                  <span class="forget"><a href="#">忘记密码?</a></span>
              </p>
              <p>
                  <input type="button" class="login_btn" value="登录" />
              </p>
          </div>

          <div class="log_form" id="tab2" style="display:none">
              <p>
                  <input type="text" class="login_tex lbg1" placeholder="请输入您的账号" />
              </p>
              <p>
                  <input type="text" class="login_tex lbg2" placeholder="请输入您的密码" />
                  <span class="forget"><a href="#">忘记密码?</a></span>
              </p>
              <p>
                  <input type="button" class="login_btn2 " value="登录" />
              </p>
          </div>
      </div>
</div>

<script type="text/javascript">
$(function(){ 
    $(window).resize(function(){
        $('.l_login').css({
            position:'absolute',
            left:($(window).width() - $('.l_login').outerWidth())/2,
            top:($(window).height() - $('.l_login').outerHeight())/2 + $(document).scrollTop()
        });
		
		$("#content").css({height:$(window).height()});
    });

    $(window).resize();

	$("#tab_1").click(function(){
		$(".log_form").hide();
		$("#tab1").show();
	});
	
	$("#tab_2").click(function(){
		$(".log_form").hide();
		$("#tab2").show();
	});
	
}); 



</script>
</body>
</html>
