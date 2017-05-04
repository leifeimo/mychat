<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/public/include.jsp" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>mchat首页</title>
<script src="js/jquery.min.js"></script>
<script src="js/all.js"></script>
<!--[if lte IE 9]><script src="js/selectivizr-min.js"></script><![endif]-->
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<link rel="Stylesheet" type="text/css" href="<%=basePath%>css/pc_2.css" />

</head>
<body>
<div id="content" >
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
		<!--左侧菜单 -->
        <div class="s_mid_t">
            <div style="height:195px;">
                <p><img src="<%=basePath%>images/l_1.jpg" /></p>
                <p class="s_mid_t_adm" >系统管理员</p>
            </div>
            <div class="l_menu">
              <p class="l_menu_1"><a href="#">我的首页</a></p>
              <p class="l_menu_2 l_menu_current"><a href="#">用户档案</a></p>
              <div class="l_m_2" style="display:none">
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
		
		<!--中间正文内容 -->
        <div class="r_content">
            <div class="r_title">
                <p id="tap1" class="tap1">公告发布</p>
                <p id="tap2" class="tap2">报告信息</p>
            </div>
            <div class="r_article">
                <div class="article_list" style="display:none">
                    <h4 class="article_list_title">报告信息</h4>
                    <p class="article_info_date">M-CHAT报告：23 | 剩余M-CHAT报告：87</p>
                   <div class="article_list_content">
	                   <div class="article_list_th">
	                     <p>报告编号</p>
	                     <p>儿童姓名</p>
	                     <p>出生日期</p>
	                     <p>创建日期</p>
	                     <p>性别</p>
	                     <p>问卷</p>
	                     <p>类型</p>
	                     <p>下载</p>
	                   </div>
	                   <div class="article_list_th_ul">
	                       <ul class="article_ul">
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>明明</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>小强</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>明明</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>小强</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>明明</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                         <li>
	                           <span>T2016A0023</span>
	                           <span>小强</span>
	                           <span>2014-11-5</span>
	                           <span>2015-10-3</span>
	                           <span>男</span>
	                           <span>10</span>
	                           <span>M-CHAT</span>
	                           <span><a href="#"><img src="<%=basePath%>images/down1.png" /></a></span>
	                         </li>
	                       </ul>
	                   </div>
                   </div>
                </div>
                
                <div class="article_info">
                    <h4 class="article_info_title">发布公告的标题</h4>
                    <p class="article_info_date">发布时间：2016-07-04 00:00:00</p>
                   <div class="article_content">
    今年1月12日上午，萧山警方接到北干街道某平台公司负责人报案称，当天上班后，突然发现公司账户138万余元资金消失。
    案发后，杭州萧山公安分局刑侦大队立即开展调查，发现账户资金被转入一个可疑账户，于是立即与杭州市公安局反通讯(网络)诈骗中心联系，启动快速止损机制，对涉案账户进行紧急冻结，在接到报案后一小时内成功冻结资金100余万
    萧山刑侦大队随后会同网警大队、北干派出所成立专案组，对涉案账户以及赃款流向进行分析，专案组迅速锁定两名可疑人员，通过侦查，发现一名嫌疑人身在广东东莞，另一名嫌疑人身在湖南长沙。
                    </div>
                </div> 
                    

                <div class="announcement">
                    <div class="announcement_tip">
                    	<img src="<%=basePath%>images/announcement.png" />&nbsp;公告
                    </div>
                    <p class="announcement_more" style=""><a href="#">MORE ></a>&nbsp;&nbsp;</p>
                    <div class="announcement_line" style="margin:0 5px;clear:both">
	                    <p><a href="#">1、英国脱欧之后开了...</a></p>
	                    <p><a href="#">2、多拉合子，英国及...</a></p>
	                    <p><a href="#">3、济将大幅放缓，体...</a></p>
                    </div>
                </div>   
 
            </div>
        </div>
    </div>
</div>

</body>
</html>
