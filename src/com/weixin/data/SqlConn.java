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
     
     // execute selection language  
     ResultSet selectSQL(String sql) {  
         ResultSet rs = null;  
         try {  
             stmt = conn.prepareStatement(sql);  
             rs = stmt.executeQuery(sql);  
         } catch (SQLException e) {  
             e.printStackTrace();  
         }  
         return rs;  
     }  
	
     // execute insertion language  
     boolean insertSQL(String sql) {  
         try {  
             stmt = conn.createStatement();  
             stmt.executeUpdate(sql);          
             return true;  
         } catch (SQLException e) {  
             System.out.println("�������ݿ�ʱ����");  
             e.printStackTrace();  
         } 
         return false;  
     }
     
     //execute delete language  
     boolean deleteSQL(String sql) {  
         try {  
             stmt = conn.prepareStatement(sql);  
             stmt.executeUpdate(sql);  
             return true;  
         } catch (SQLException e) {  
             System.out.println("�������ݿ�ʱ����");  
             e.printStackTrace();  
         } catch (Exception e) {  
             System.out.println("����ʱ����");  
             e.printStackTrace();  
         }  
         return false;  
     }  
     
   //execute update language  
     boolean updateSQL(String sql) {  
         try {  
             stmt = conn.prepareStatement(sql);  
             stmt.executeUpdate(sql);  
             return true;  
         } catch (SQLException e) {  
             System.out.println("�������ݿ�ʱ����");  
             e.printStackTrace();  
         } catch (Exception e) {  
             System.out.println("����ʱ����");  
             e.printStackTrace();  
         }  
         return false;  
     }  
	  

    public void insertUser(User user){  	
    	String insert = "insert into weixin_users" + " values('"+user.getOpenid()+"','"+user.getNickname()+"','"+user.getSex()+"','"+user.getLanguage()+"','"+user.getCity()+"','"+user.getProvince()+"','"+ user.getCountry()+"','"+user.getHeadimgurl()+"','"+user.getSubscribe_time()+"','"+user.getUnionid()+"','"+user.getRemark()+"','"+user.getGroupid()+"','"+user.getLastSignTime()+"','"+user.getSignCount()+"','"+user.isTodaySign()+"')";
    	
    	//System.out.println(insert);
    	try {	
			this.connSQL();
			stmt = conn.createStatement();
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       	
    	String s = "select * from weixin_users"; 
    	 
            System.out.println("insert successfully");  
            ResultSet resultSet = this.selectSQL(s);  
            this.layoutStyle2(resultSet);  
        
    	this.deconnSQL(); 
    }  
    
    public void updateUser(User user){
    	String update = "update weixin_users set lastSignTime = "+user.getLastSignTime()+"signCount = "+user.getSignCount()+"todaySign ="+user.isTodaySign()+" where openid= "+user.getOpenid();
    	String s = "select * from weixin_users";
    	if (this.updateSQL(update) == true) {  
            System.out.println("update successfully");  
            ResultSet resultSet = this.selectSQL(s);     
            this.layoutStyle2(resultSet);  
        }
    	this.deconnSQL();
    }
    public void deleteUser(User user){
    	String delete = "delete from weixin_users where openid= " + user.getOpenid();  
    	String s = "select * from weixin_users";
    	if (this.insertSQL(delete) == true) {  
            System.out.println("delete successfully");  
            ResultSet resultSet = this.selectSQL(s);  
            this.layoutStyle2(resultSet);  
        }
    	this.deconnSQL();
    }
    
    // show data in ju_users  
    void layoutStyle2(ResultSet rs) {  
        System.out.println("-----------------");  
        System.out.println("ִ�н��������ʾ:");  
        System.out.println("-----------------");  
        System.out.println(" �û�ID" + "/t/t" + "�Ա�ID" + "/t/t" + "�û���"+ "/t/t" + "����");  
        System.out.println("-----------------");  
        try {  
            while (rs.next()) {  
                System.out.println(rs.getInt("ju_userID") + "/t/t"  
                        + rs.getString("taobaoID") + "/t/t"  
                        + rs.getString("ju_userName")  
                         + "/t/t"+ rs.getString("ju_userPWD"));  
            }  
        } catch (SQLException e) {  
            System.out.println("��ʾʱ���ݿ����");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("��ʾ����");  
            e.printStackTrace();  
        }  
    }  
}
