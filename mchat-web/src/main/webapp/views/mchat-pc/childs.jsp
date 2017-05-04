<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>3</title>
<script src="js/jquery.min.js"></script>
<script src="js/all.js"></script>
<!--[if lte IE 9]><script src="js/selectivizr-min.js"></script><![endif]-->
<link rel="Stylesheet" type="text/css" href="css/style.css" />
<link rel="Stylesheet" type="text/css" href="css/pc_2.css" />
<link rel="Stylesheet" type="text/css" href="css/pc_archives.css" />
<style>

.search_list_content{
    margin-top:40px;
    width:950px;
}
.search_list_th{
   color:#FFF;
   height:35px;
   line-height:35px;
   text-align:center;
}
.search_list_th p{
   float:left;
   background-color:#feaa48;
 
}
.search_list_th p:nth-child(1){
	width:10%;
}
.search_list_th p:nth-child(2){
	width:16%;
}
.search_list_th p:nth-child(3){
	width:12%;
}
.search_list_th p:nth-child(4){
	width:12%;
}
.search_list_th p:nth-child(5){
	width:14%;
}
.search_list_th p:nth-child(6){
	width:10%;
}
.search_list_th p:nth-child(7){
	width:16%;
}
.search_list_th p:nth-child(8){
	width:10%;
}

ul{
	list-style-type:none;
}
.search_list_th_ul{
   text-align:center;
   color:#333333;
}					

.search_ul li{
   height:30px;
   line-height:30px;
}
.search_ul li:nth-child(odd){
   background-color:#ffffff;
}
.search_ul li:nth-child(even){
   background-color:#f8f8f8;
}
.search_list_th_ul span{
   float:left;
   font-size:14px;
}
.search_list_th_ul span img{
	margin-top:10px;
}
.search_list_th_ul span:nth-child(1){
	width:10%;

}
.search_list_th_ul span:nth-child(2){
	width:16%;
}
.search_list_th_ul span:nth-child(3){
	width:12%;
}
.search_list_th_ul span:nth-child(4){
	width:12%;
}
.search_list_th_ul span:nth-child(5){
	width:14%;
}
.search_list_th_ul span:nth-child(6){
	width:10%;
}
.search_list_th_ul span:nth-child(7){
	width:16%;
}
.search_list_th_ul span:nth-child(8){
	width:10%;
}
</style>
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
              <p class="l_menu_2 l_menu_current"><a href="#">用户档案</a></p>
              
              <div class="l_m_2">
                  <p class="l_menu_2_1 l_m_current"><a href="#">儿童查询</a></p>
                  <p class="l_menu_2_2"><a href="#">报告查询</a></p>
                  <p class="l_menu_2_3"><a href="#">作废查询</a></p>
                  <p class="l_menu_2_4"><a href="#">补充表格</a></p>
              </div>
              <p class="l_menu_3"><a href="#">报告查询</a></p>
              <p class="l_menu_4"><a href="#">我的账号</a></p>
              <p class="l_menu_5"><a href="#">创建报告</a></p>
            </div>
            
            <div>
                <img src="<%=basePath%>images/bg2.jpg" />
            </div>
        
        </div>

        <div class="r_content">
            <div class="r_search">
               <div class="r_search_l">
                   <div>
                       儿童姓名：<input class="chat_input" style="height:30px; width:200px;" type="text" />
                   </div>
                   <div>
                       出生日期：<select name="" class="chat_input" style="height:30px;width:200px"></select>
                   </div>
                   
               </div>
               <div class="r_search_m">
                   <div>
                       身份信息：<input class="chat_input" style="height:30px; width:200px;" type="text" />
                   </div>
                   <div>
                       施测日期：<select name="" class="chat_input" style="height:30px;width:200px"></select>
                   </div>
               </div>
               <div class="r_search_r">
               <input type="button" class="r_search_r_btn" value="搜索" />
               </div>
               
               
            </div>
            
            <div class="r_article">     


                   <div class="search_list_content">

                     <div class="search_list_th">
                       <p>儿童姓名</p>
                       <p>身份信息</p>
                       <p>出生日期</p>
                       <p>报告总数量</p>
                       <p>我的施测数量</p>
                       <p>性别</p>
                       <p>创建时间</p>
                       <p>查看</p>
                     </div>
                     
                     <div class="search_list_th_ul">
                         <ul class="search_ul">
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                           <li>
                             <span>刘开明</span>
                             <span>a1234567890</span>
                             <span>2014-11-5</span>
                             <span>1</span>
                             <span>1</span>
                             <span>男</span>
                             <span>2017 11-11 12:36:21</span>
                             <span><a href="#"><img src="<%=basePath%>images/looks.png" /></a></span>
                           </li>
                         </ul>
                     </div>
                   
                     

                     
                     
                     <div class="s_page m_top_90">
                         <div class="s_page_num">
                           <span><a href="#"><上一页></a></span>
                           <span><a href="#">1</a></span>
                           <span class="s_page_current">2</span>
                           <span><a href="#">3</a></span>
                           <span><a href="#">4</a></span>
                           <span><a href="#">5</a></span>
                           <span><a href="#">6</a></span>
                           <span><a href="#">7</a></span>
                           <span><a href="#">8</a></span>
                           <span><a href="#">...</a></span>
                           <span><a href="#">20</a></span>
                           <span><a href="#">下一页></a></span>
                         </div>
                     </div>
                   </div>               
            </div>
        </div>
    </div>
</div>

</body>
</html>
