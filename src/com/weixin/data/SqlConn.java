package com.weixin.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.weixin.user.User;

public class SqlConn {
	private static final String CONN_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	
	private Connection conn = null;  
    private Statement stmt = null; 
    
	public void connSQL(){
		try {
		      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		      conn = DriverManager.getConnection(CONN_URL,USERNAME,PASSWORD);
		      //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
		      
		      //System.out.println("Success connect Mysql server!");
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from user");
		     
		      //user Ϊ��������
		    }catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    } 
		  }
	
	// disconnect to MySQL  
     void deconnSQL() {  
        try {  
            if (conn != null)  
                conn.close();  
        } catch (Exception e) {  
            System.out.println("�ر����ݿ����� ��");  
            e.printStackTrace();  
        }  
     }
     	  
     //����һ���û����û���עʱ����
    public void insertUser(User user){  	
    	String insert = "insert into weixin_users" + " values('"+user.getOpenid()+"','"+user.getNickname()+"','"+user.getSex()+"','"+user.getLanguage()+"','"+user.getCity()+"','"+user.getProvince()+"','"+ user.getCountry()+"','"+user.getHeadimgurl()+"','"+user.getSubscribe_time()+"','"+user.getUnionid()+"','"+user.getRemark()+"','"+user.getGroupid()+"','"+user.getLastSignTime()+"','"+user.getSignCount()+"','" + user.getSignAllCount() + "','" +user.isTodaySign()+"')";
    	
    	System.out.println(insert);
    	try {	
			this.connSQL();
			stmt = conn.createStatement();
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       	                 
    	this.deconnSQL(); 
    }  
    //�����û���Ϣ���û�ǩ��ʱ����
    public void updateUser(User user){
    	String update = "UPDATE weixin_users set headimgurl='" +user.getHeadimgurl()+"',lastSignTime ='"+user.getLastSignTime()+"', signCount='"+user.getSignCount()+ "', signAllCount='" +user.getSignAllCount() + "', todaySign='"+user.isTodaySign()+"' where openid='"+user.getOpenid()+"'";    	
    	//System.out.println(update);
    	try {	
			this.connSQL();
			stmt = conn.createStatement();
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	this.deconnSQL();
    }
     
    public User selectUser(String openId){
    	String select = "select * from weixin_users where openid = '" +openId + "'";
    	User user = new User();
    	try {	
			this.connSQL();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			while (rs.next()) {	
				user.setOpenid(rs.getString("openid"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getInt("sex"));
				user.setLanguage(rs.getString("language"));
				user.setCity(rs.getString("city"));
				user.setProvince(rs.getString("province"));
				user.setHeadimgurl(rs.getString("headimgurl"));
				user.setSubscribe_time(rs.getString("subscribe_time"));
				user.setUnionid(rs.getString("unionid"));
				user.setRemark(rs.getString("remark"));
				
				user.setLastSignTime(rs.getString("lastSignTime"));
				user.setSignCount(rs.getInt("signCount"));
				user.setSignAllCount(rs.getInt("signAllCount"));
				user.setTodaySign(rs.getBoolean("todaySign"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	this.deconnSQL();
    	return user;
    }
    
    public String[] selectAllSign(){    	
    	String selectAll = "select * from weixin_users where todaySign = 'true' order by lastSignTime";
    	String selectCount = "select count(*)  as countSign from (select * from weixin_users where todaySign = 'true' order by lastSignTime desc) as signCount";
    	try {	
    		this.connSQL();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectAll);
			//��ȡ����������Ŀ����������ǩ��������
			int length = 0;
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery(selectCount);
			while(rs2.next()){
				length = rs2.getInt("countSign");
			}
			
			//System.out.println(length);
			String[] allHead = new String[length];
			int tmp = 0;
				while(rs.next()){
					allHead[tmp] = rs.getString("headimgurl");
					tmp++;					
				}	
							
			this.deconnSQL();
	    	return allHead;
    	}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			}  
    	return null;
    	}
     
    public void deleteUser(String openId){
    	String delete = "delete from weixin_users where openid= '" + openId +"'";      	
    	this.connSQL();		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	this.deconnSQL();
    }
    
    
     
}
