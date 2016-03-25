<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.weixin.data.SqlConn"
    	import="com.weixin.user.User"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=2.0;" />
<title>每日签到</title>
<style type="text/css">
body {background:#fffff0; max-width:400px;margin:0 auto;}
p {font-family:"黑体";font-size:26px;margin:0; font-weight:bold; font-style:italic; white-space:nowrap;}
.tab {background:#ffffff; border-radius:0;  
		 margin:0 10px; padding:15px;
		border:1px; border-style: solid;border-color:rgb(242,242,242);
		}
.tab1{text-align:center;margin: 0px auto;height:230px;}
.signImg{width:180px; height:180px; margin-bottom:8px; }
.tab2{margin-top:10px;height:200px;}
.headImg {width:55px; height:55px;}
.headImgDiv{border-radius:50%;overflow:hidden; }
.headAll{float:left; margin:5px 5px;}
.wenzi{float:left;margin:0;margin-right:5px;}
.lastwenzi{white-space:nowrap;}
.tab3{margin-top:10px;height:60px;}

</style>
</head>
<body>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String openId = request.getParameter("openid");//用request得到
		SqlConn sc = new SqlConn();
		User user = new User();
		user = sc.selectUser(openId);			
		String headImgUrl = user.getHeadimgurl();
		String[] allSignHead = sc.selectAllSign();
		int allSignCount = allSignHead.length;
		
	%> 
	<div class="tab tab1">
		<div class="tab1 signImg">
			<img class="signImg" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/f45083185e224c3b8e5e9df297e5fb5b.jpeg" >
		</div>
		<div >
			<img class="wenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/yijianchi.png" >
			<p class="wenzi"><% out.println(user.getSignCount());%></p>
			<img class="wenzi lastwenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/tian.png" >
		</div>
	</div>
	<div class="tab tab2">
		<table>
			<tr><td>
			<div >
				<img class="wenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/jinridaka.png" >
				<p class="wenzi"><% out.println(allSignCount);%></p>
				<img class="wenzi lastwenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/ren.png" >
			</div>
			</td></tr>
			<tr><td>					
			<%for(int i=0;i<allSignCount;i++){ %>
					<div class="headImg headImgDiv headAll">		
						<img class="headImg" alt="" src="<%=allSignHead[i]%>" >											
					</div>
			<%  
				if(i>7) break;
				}%><h1 class="wenzi">...</h1>
			</td></tr>
		</table>					
	</div>
	<div class="tab tab3">
		<img class="wenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/dakatongji.png" >
		<p class="wenzi"><% out.println(user.getSignAllCount());%></p>
		<img class="wenzi" alt="" src="http://tongyuan.tunnel.qydev.com/Weixin/files/tian.png" >	
	</div>
	
</body>
</html>