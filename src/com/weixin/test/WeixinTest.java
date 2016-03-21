package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) {
		try{
			AccessToken token = WeixinUtil.getExitAccessToken();
			System.out.println("票据："+token.getToken());
			System.out.println("有效时间："+token.getExpiresIn());
			String userDir = System.getProperty("user.dir");
			System.out.println(userDir);
			//String path = "F:/Penguins2.jpg";
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "image");
			//System.out.println(mediaId);
			
			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result == 0){
				System.out.println("菜单创建成功");
			}else{
				System.out.println(result);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
