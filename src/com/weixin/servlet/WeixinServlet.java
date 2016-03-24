package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.data.SqlConn;
import com.weixin.data.UserData;
import com.weixin.user.User;
import com.weixin.util.CheckUtil;
import com.weixin.util.MessageUtil;
import com.weixin.util.WeixinUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)
		throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try{
			Map<String,String> map = MessageUtil.xmlToMap(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String creatTime = map.get("CreateTime ");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String msgId = map.get("MsgId ");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){				
				message = MessageUtil.initNewsMessage(toUserName, fromUserName);											
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");				
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){					
					String nickName = WeixinUtil.getUser(fromUserName).getNickname();
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.subscribeText(nickName));						 
					User user = new User();
					user = WeixinUtil.getUser(fromUserName);											
					SqlConn sc = new SqlConn();
					sc.insertUser(user);
					
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					String key = map.get("EventKey");
					if(key.equals("21_qiandao")){
						//message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
						User user = new User();
						SqlConn sc = new SqlConn();
						user = sc.selectUser(fromUserName);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						if(user.isTodaySign()){
							//今天已签到
							message = MessageUtil.initText(toUserName, fromUserName, "您今日已签到！");	
						}else {
							user.setTodaySign(true);
							user.setLastSignTime(sdf.format(new Date()));
							if(WeixinUtil.todaySign(user.getLastSignTime(),sdf.format(new Date()))){
								//连续签到
								user.setSignCount(user.getSignCount()+1);								
								message = MessageUtil.initText(toUserName, fromUserName, "连续签到"+user.getSignCount()+"天");								
							}else{
								//没有连续签到
								user.setSignCount(1);							
								message = MessageUtil.initText(toUserName, fromUserName, "您昨天没有签到，今日连续签到1天！");
							}											
						sc.updateUser(user);
						}							
						
						
					}else if(key.equals("31_saoma")){
						message = MessageUtil.initText(toUserName, fromUserName, "扫码成功");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		 
						User user = new User();
						user = WeixinUtil.getUser(fromUserName);						
						user.setLastSignTime(sdf.format(new Date()));																		
						SqlConn sc = new SqlConn();
						sc.updateUser(user);
						
					}	
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCAN.equals(eventType)){
					String key= map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName,key);
				}				
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String Label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName,Label);
			}
			
			//System.out.println(message);
			out.print(message);
			
		}catch(DocumentException e){
			e.printStackTrace();
		}finally{
			out.close();
		}
}
}
