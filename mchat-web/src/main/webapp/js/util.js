jQuery.support.cors=!0;var util={requestURL:"http://localhost/mchat-web",getCurrentDateTime:function(){var e=new Date,t=e.getFullYear(),r=e.getMonth()+1,a=e.getDate(),n=e.getDay(),o=t;o=r>9?o+"年"+r:o+"年0"+r,o=a>9?o+"月"+a+"日":o+"月0"+a+"日";var i="";return 0==n?i="星期日":1==n?i="星期一":2==n?i="星期二":3==n?i="星期三":4==n?i="星期四":5==n?i="星期五":6==n&&(i="星期六"),o=o+"， "+i},showCal:function(){{var e=new Date,t=e.getFullYear(),r=e.getMonth()+1,a=e.getDate();e.getDay(),parseInt(e.getTime()/1e3)}return 100>t&&(t="19"+t),this.GetLunarDay(t,r,a)},GetLunarDay:function(e,t,r){function a(e,t){return e>>t&1}function n(){u=3!=arguments.length?new Date:new Date(arguments[0],arguments[1],arguments[2]);var e,t,r,n,o=!1,m=u.getYear();for(1900>m&&(m+=1900),e=365*(m-1921)+Math.floor((m-1921)/4)+g[u.getMonth()]+u.getDate()-38,u.getYear()%4==0&&u.getMonth()>1&&e++,t=0;;t++){for(n=c[t]<4095?11:12,r=n;r>=0;r--){if(e<=29+a(c[t],r)){o=!0;break}e=e-29-a(c[t],r)}if(o)break}i=1921+t,s=n-r+1,l=e,12==n&&(s==Math.floor(c[t]/65536)+1&&(s=1-s),s>Math.floor(c[t]/65536)+1&&s--)}function o(){var e="";return 1>s?(e+="(闰)",e+=f.charAt(-s-1)):e+=f.charAt(s-1),e+="月",e+=11>l?"初":20>l?"十":30>l?"廿":"三十",(l%10!=0||10==l)&&(e+=m.charAt((l-1)%10)),e}var i,s,l,u,c=new Array(100),g=new Array(12),m="一二三四五六七八九十",f="正二三四五六七八九十冬腊";return c=new Array(2635,333387,1701,1748,267701,694,2391,133423,1175,396438,3402,3749,331177,1453,694,201326,2350,465197,3221,3402,400202,2901,1386,267611,605,2349,137515,2709,464533,1738,2901,330421,1242,2651,199255,1323,529706,3733,1706,398762,2741,1206,267438,2647,1318,204070,3477,461653,1386,2413,330077,1197,2637,268877,3365,531109,2900,2922,398042,2395,1179,267415,2635,661067,1701,1748,398772,2742,2391,330031,1175,1611,200010,3749,527717,1452,2742,332397,2350,3222,268949,3402,3493,133973,1386,464219,605,2349,334123,2709,2890,267946,2773,592565,1210,2651,395863,1323,2707,265877),g[0]=0,g[1]=31,g[2]=59,g[3]=90,g[4]=120,g[5]=151,g[6]=181,g[7]=212,g[8]=243,g[9]=273,g[10]=304,g[11]=334,1921>e||e>2020?"":(t=parseInt(t)>0?t-1:11,n(e,t,r),o())},getTime:function(){var e=this.getCurrentDateTime(),t=this.showCal();return e+"， 农历"+t},getUserInfo:function(){if(sessionStorage.token){{sessionStorage.token}this.client({url:this.requestURL+"/web/v1/medic/getInformation",success:function(e){1==e.code||layer.msg(e.msg),console.log(e)}})}else layer.msg("请先登录！"),window.location.href="login.html"},client:function(e){var t=layer.load(0),r=setTimeout(function(){layer.close(t),layer.msg("请求超时，请检查网络！")},5e3);e.xhrFields={withCredentials:!0},e.data||(e.data={}),e.crossDomain=!0,e.complete=function(e){"10002"==e.responseJSON.code&&(layer.msg("登录超时，请重新登录！"),window.location.href="../html/login.html"),clearTimeout(r),layer.close(t)},e.error=function(e){layer.msg(e.status+" "+e.statusText),clearTimeout(r),layer.close(t)},$.ajax(e)},pageClient:function(e,t){var r=layer.load(0),a=setTimeout(function(){layer.close(r),layer.msg("请求超时，请检查网络！")},5e3);e.xhrFields={withCredentials:!0},e.data||(e.data={}),e.crossDomain=!0,e.data.pageNum=t,util.currentPage=t,e.complete=function(e){"10002"==e.responseJSON.code&&(layer.msg("登录超时，请重新登录！"),window.location.href="../html/login.html"),clearTimeout(a),layer.close(r)},e.error=function(e){layer.msg(e.status+" "+e.statusText),clearTimeout(a),layer.close(r)},$.ajax(e)},currentPage:1,getDateStr:function(e){if(""==e)return"";var t=e.split("-");return t.map(function(e,r){t[r]=parseInt(e)}),t.join("-")},getAge:function(e){var t,r,a,n;t=e.split("-"),r=new Date(t[1]+"-"+parseInt(t[2])+"-"+parseInt(t[0])),a=new Date,n=parseInt(Math.abs(r-a)/1e3/60/60/24);var o=parseInt(n/30),i=n%30;return o+"月"+i+"天"},getTimeFromNum:function(e,t){var r=new Date(e),a=r.getFullYear(),n=r.getMonth()+1;n=10>n?"0"+n:n;var o=r.getDate();o=10>o?"0"+o:o;var i=r.getHours();i=10>i?"0"+i:i;var s=r.getMinutes(),l=r.getSeconds();return s=10>s?"0"+s:s,l=10>l?"0"+l:l,1==t?a+"-"+n+"-"+o+" "+i+":"+s+":"+l:a+"-"+n+"-"+o},getQueryString:function(e){var t=new RegExp("(^|&)"+e+"=([^&]*)(&|$)");if(window.location.href.indexOf("?")>0){var r=window.location.href.split("?")[1].match(t);return null!=r?unescape(r[2]):null}return""}};