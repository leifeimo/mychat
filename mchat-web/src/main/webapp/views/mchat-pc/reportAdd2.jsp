<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>添加报告</title>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/all.js"></script>
<!--[if lte IE 9]><script src="js/selectivizr-min.js"></script><![endif]-->
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/pc_2.css" />
<script>
$(function(){ 
  layer.open({
	type: 2,
	shade: [0.8, '#000'],
	title: false,
	scrollbar: false,
	closeBtn:true,
	area: ['640px', '740px'],
	content: 'pop2.html'
  
  });
}); 
</script>
</head>

<body>

<div id="content">
    <div id="head" class="b_blues" style="height:60px; width:100%">
	
        <div id="head_top">
		    <div style="float:left">
			    <div class="head_logo"><img src="<%=basePath%>images/logo.jpg" /></div>
		        <div class="head_title">欢迎您使用平台管理系统！</div>
			</div>
			
			<div id="login_out">
			    <div id="login_out_btn" class="b_white">
				<a href="#"><img src="<%=basePath%>images/out.png" align="absmiddle" />&nbsp;&nbsp;退出系统</a>
				</div>
			</div>
		</div>

	</div>
        
    <div id="head_mid">
        <div>
            <div class="cho_sys f_l"><a href="#">选择系统</a></div>
            <div class="f_r head_date" >2017年3月10日，星期五，农历正月十五</div>
        </div>
    </div>
    
    <div class="s_mid">
    
        <div class="s_mid_t">
            <div style="height:195px;">
                <p><img src="<%=basePath%>images/l_1.jpg" /></p>
                <p class="s_mid_t_adm" >系统管理员</p>
            </div>
            
            <div class="l_menu">
              <p class="l_menu_1"><a href="#">我的首页</a></p>
              <p class="l_menu_2"><a href="#">用户档案</a></p>
              
              <div class="l_m_2" style="display:none">
                  <p class="l_menu_2_1 l_m_current"><a href="#">儿童查询</a></p>
                  <p class="l_menu_2_2"><a href="#">报告查询</a></p>
                  <p class="l_menu_2_3"><a href="#">作废查询</a></p>
                  <p class="l_menu_2_4"><a href="#">补充表格</a></p>
              </div>
              <p class="l_menu_3"><a href="#">报告查询</a></p>
              <p class="l_menu_4"><a href="#">我的账号</a></p>
              <p class="l_menu_5 l_menu_current"><a href="#">创建报告</a></p>
            </div>
            
            <div>
                <img src="<%=basePath%>images/bg2.jpg" />
            </div>
        
        </div>


        <div class="r_content">
            <div class="r_title">
                <p id="tap1_2" class="tap1_2">创建M-CHAT报告</p>
            </div>
            <div class="r_article">     


              <div class="r_form">
                  <div>儿童姓名:</div>
                  <div><input class="chat_input" style="width:350px" type="text" /></div>
              </div>
              
              <div class="r_form">
                  <div>性别:</div>
                  <div><select class="chat_input" style="width:350px"></select></div>
              </div>
              
              <div class="r_form">
                  <div>身份信息:</div>
                  <div>
                  <select name="" class="chat_input" style="width:120px"></select>
                  <input class="chat_input" style="width:225px" type="text" />
                  </div>
              </div> 
              
              <div class="r_form">
                  <div>事件标题:</div>
                  <div><input class="chat_input" style="width:350px" type="text" /></div>
              </div>
              
              <div class="r_form">
                  <div>&nbsp;</div>
                  <div><p class="check_history" style="width:125px;">检查历史档案</p></div>
              </div>
              
              <BR />
              <div class="r_form">
                  <div>出生日期:</div>
                  <div><select class="chat_input" style="width:350px"></select></div>
              </div> 
              <div class="r_form">
                  <div>完成问卷日期:</div>
                  <div><select class="chat_input" style="width:350px"></select></div>
              </div> 
              <div class="r_form">
                  <div>&nbsp;</div>
                  <div><p class="check_history f_l" style="width:125px;">计算年龄</p><p>&nbsp;&nbsp;&nbsp;&nbsp;儿童生活年龄：一月一天</p></div>
              </div>
              
              <BR />
              <div class="r_form">
                  <div>宝宝的孕周:</div>
                  <div>
                  <input class="chat_input r_form_week" style="width:155px" type="text" />
                  <input class="chat_input r_form_day" style="width:155px" type="text" />
                  </div>
              </div>         
              <div class="r_form">
                  <div>出生时情况(多选):</div>
                  <div>
                  <select class="chat_input"  style="width:350px">
                  </select>
                  </div>
              </div>
              <div class="r_form">
                  <div>&nbsp;</div>
                  <div><p class="check_history f_l"  style="width:125px;">计算矫正年龄</p><p>&nbsp;&nbsp;&nbsp;&nbsp;早起矫正年龄：一月一天</p></div>
              </div>
              
              <BR />
              <div class="r_form">
                  <div>&nbsp;</div>
                  <div>温馨提示：上面信息为必填信息，请全部填写，谢谢</div>
              </div>
              
              <BR />
              <div class="r_form">
                  <div>&nbsp;</div>
                  <div><p class="check_history f_l"  style="width:350px;">+增加填写问卷人信息填写</p></div>
              </div>
 
            </div>
            <div class="r_form_sub">
                <p><input type="button" class="r_form_s r_form_submit" value="确认" /></p>
                <p><input type="button" class="r_form_s r_form_reset" value="取消" /></p>
            </div>
            
            
        </div>

    </div>
</div>

</body>
</html>
