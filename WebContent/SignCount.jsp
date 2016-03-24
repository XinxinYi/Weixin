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
body {background:#fffff0;}
p {color: blue;font-family:"微软雅黑","黑体","宋体";font-size:20px;}
.tab {background:#ffffff; border-radius:5%;  
		 margin:0 10px; padding:15px;
		border:1px; border-style: solid;border-color:rgb(242,242,242);
		}
.tab1{text-align:center;}
.tab2{margin-top:10px;height:200px;}
.headImg {width:80px; height:80px; }
.headImgDiv{border-radius:50%;overflow:hidden; }
.headAll{float:left; margin:0 5px;}
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
		<div class="headImg headImgDiv tab1">
			<img class="headImg" alt="" src="<%=headImgUrl%>" >
		</div>
		<p>您已连续签到<% out.println(user.getSignCount());%>天！</p>
		<p>您共签到<% out.println(user.getSignAllCount());%>天！</p>
	</div>
	<div class="tab tab2">
		<p>今天签到总人数：<% out.println(allSignCount);%></p>
		<%for(int i=0;i<allSignCount;i++){ %>
				<div class="headImg headImgDiv headAll">		
					<img class="headImg" alt="" src="<%=allSignHead[i]%>" >											
				</div>
		<%}%>			
		
	</div>
</body>
</html>