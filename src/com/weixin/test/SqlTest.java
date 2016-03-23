package com.weixin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.weixin.data.SqlConn;
import com.weixin.util.WeixinUtil;

public class SqlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
		      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
		      //Class.forName("org.gjt.mm.mysql.Driver");
		     System.out.println("Success loading Mysql Driver!");
		    }
		    catch (Exception e) {
		      System.out.print("Error loading Mysql Driver!");
		      e.printStackTrace();
		    }
		    try {
		      Connection connect = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/test??useUnicode=true&characterEncoding=utf8","root","1234");
		           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

		    
		      //String sql = "CREATE TABLE weixin_users (openid varchar(100) not null, nickname varchar(20), sex int null, language varchar(20),city varchar(20),province varchar(20),country varchar(20),headimgurl varchar(200),subscribe_time varchar(20),unionid varchar(20),remark varchar(20),groupid varchar(20),lastSignTime varchar(20),signCount varchar(20),todaySign varchar(20)) ENGINE = MyISAM  DEFAULT CHARSET = utf8;";

		    // Statement stmt = connect.prepareStatement(sql);
		     // stmt.executeUpdate(sql); 
		      
		      //System.out.println("Success ! create table weixin_users");
		    //String insert = "insert into weixin_users(openid,nickname,sex,language,city,province,country,headimgurl,subscribe_time,unionid,remark,groupid,lastSignTime,signCount,todaySign) values('oh5ZIvxfeuQlG-q---94Vb8PHhBA','昕昕','2','null','海淀','北京','null','user.getHeadimgurl()','1458289587','null','null','null','2016-03-23 06:55:11','0','false'";
		  	//String insert = "insert into weixin_users values('94Vb8PHhBA','昕昕','2','null','haidian','beijing','null','getHeadimgurl','1458289587','null','null','null','2016-03-23-06:55:11','0','false')";
			 String insert = "insert into weixin_users values('oh5ZIvxfeuQlG-q---94Vb8PHhBA','昕昕','2','null','海淀','北京','null','rl','1458289587','null','null','null','2016-03-23-07:04:19','0','false')";    
		      Statement stmt = connect.createStatement();
		       stmt.executeUpdate(insert);
		       ResultSet rs = stmt.executeQuery("select * from weixin_users");
		      //System.out.println(rs);
		      //user 为你表的名称
		     
		  
		     
		      
		      
		while (rs.next()) {
		        System.out.println(rs.getString("openid"));
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		  }
	

}
