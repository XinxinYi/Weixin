package com.weixin.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import com.weixin.user.User;

public class UserData {
	static String filename = "../../workspace/Weixin/userData.xml";
	
	public UserData(){
		
	}
	
	public static User getUser(){
		User user = new User();

		
		return user;
	}
	public static User[] getAllUser(){
		User[] allUser = new User[10];
		return allUser;
	}
	public static void addUser(User user) throws FileNotFoundException, IOException{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(filename);
			System.out.println();
			if( document.getElementsByTagName("users").getLength() == 0){
				System.out.println("userΪ�գ�");
				
				// �������ڵ� �������������� ;     
		        Element root = new Element("users");
		        // �����ڵ���ӵ��ĵ��У�     
		        Document Doc = new Document(root);
		        Element elements = new Element("user");
		        elements.addContent(new Element("openid").setText(user.getOpenid()));
		        elements.addContent(new Element("nickname").setText(user.getNickname()));
		        elements.addContent(new Element("sex").setText(Integer.toString(user.getSex())));
		        elements.addContent(new Element("language").setText(user.getLanguage()));
		        elements.addContent(new Element("city").setText(user.getCity()));
		        elements.addContent(new Element("province").setText(user.getProvince()));
		        elements.addContent(new Element("country").setText(user.getCountry()));
		        elements.addContent(new Element("headimgurl").setText(user.getHeadimgurl()));
		        elements.addContent(new Element("subscribe_time").setText(user.getSubscribe_time()));
		        elements.addContent(new Element("unionid").setText(user.getUnionid()));
		        elements.addContent(new Element("remark").setText(user.getRemark()));       
		        elements.addContent(new Element("lastSignTime").setText(user.getLastSignTime()));
		        elements.addContent(new Element("signCount").setText(Integer.toString(user.getSignCount())));
		        elements.addContent(new Element("groupid").setText(user.getGroupid()));
		        elements.addContent(new Element("todaySign").setText(Boolean.toString(user.isTodaySign())));
				root.addContent(elements); 
				// ��� books.xml �ļ���    
		        // ʹxml�ļ� ����Ч��  
		        Format format = Format.getPrettyFormat();  
		        XMLOutputter XMLOut = new XMLOutputter(format);  
		        XMLOut.output(Doc, new FileOutputStream(filename));  
			}else{
				
				System.out.println("user��Ϊ�գ�");
				
				Element elements = new Element("user");
		        elements.addContent(new Element("openid").setText(user.getOpenid()));
		        elements.addContent(new Element("nickname").setText(user.getNickname()));
		        elements.addContent(new Element("sex").setText(Integer.toString(user.getSex())));
		        elements.addContent(new Element("language").setText(user.getLanguage()));
		        elements.addContent(new Element("city").setText(user.getCity()));
		        elements.addContent(new Element("province").setText(user.getProvince()));
		        elements.addContent(new Element("country").setText(user.getCountry()));
		        elements.addContent(new Element("headimgurl").setText(user.getHeadimgurl()));
		        elements.addContent(new Element("subscribe_time").setText(user.getSubscribe_time()));
		        elements.addContent(new Element("unionid").setText(user.getUnionid()));
		        elements.addContent(new Element("remark").setText(user.getRemark()));       
		        elements.addContent(new Element("lastSignTime").setText(user.getLastSignTime()));
		        elements.addContent(new Element("signCount").setText(Integer.toString(user.getSignCount())));
		        elements.addContent(new Element("groupid").setText(user.getGroupid()));
		        elements.addContent(new Element("todaySign").setText(Boolean.toString(user.isTodaySign())));
				
		     /*
				// ��� books.xml �ļ���    
		        // ʹxml�ļ� ����Ч��  
		        Format format = Format.getPrettyFormat();  
		        XMLOutputter XMLOut = new XMLOutputter(format);  
		        XMLOut.output(Doc, new FileOutputStream(filename));  */
			}
			
			
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
           
           
        
		
	}
	public static boolean removeUser(){
		boolean result = false;
		return result;
	}
}
