package com.weixin.test;

import com.weixin.data.SqlConn;
import com.weixin.po.AccessToken;
import com.weixin.user.User;
import com.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) {
		try{
			AccessToken token = WeixinUtil.getExitAccessToken();
			System.out.println("Ʊ�ݣ�"+token.getToken());
			System.out.println("��Чʱ�䣺"+token.getExpiresIn());
			String userDir = System.getProperty("user.dir");
			System.out.println(userDir);
			//String path = "F:/Penguins2.jpg";
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "image");
			//System.out.println(mediaId);
			
			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result == 0){
				System.out.println("�˵������ɹ�");
			}else{
				System.out.println(result);
			}
			
			/*
			String fromUserName = "111";
			User user = new User();
			SqlConn sc = new SqlConn();
			user = WeixinUtil.getUser(fromUserName);
			user.setSignCount(1);
			user.setSignAllCount(1);
			user.setTodaySign(true);
			sc.updateUser(user);
			*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
