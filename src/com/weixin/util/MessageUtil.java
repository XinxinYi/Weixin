package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_SHORTVIDEW = "shortvideo";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";
	
	/*
	 * xmlתΪmap����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/*
	 * ���ı�����ת��Ϊxml
	 * 
	 * 
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		
		return xstream.toXML(textMessage);
	}
	/*
	 * ƴ���ı���Ϣ
	 * 
	 * 
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		 
		text.setCreateTime(sdf.format(new Date()));
		text.setContent(content);
		return textMessageToXml(text);
		
	}
	
	/*
	 * ���˵�
	 * 
	 * 
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ���Ĺ�ע���밴�ղ˵���ʾ������\n");
		sb.append("1-��˾����\n");
		sb.append("2-��Ʒ����\n");
		sb.append("�ظ��������˲˵���");
		return sb.toString();
	}
	public static String firstText(){
		StringBuffer sb = new StringBuffer();
		sb.append("����˾��Ҫ��Ӫ��ҵˮ�ã�����ѹ���á���õȡ�rg.apache.catalina.core.StandardContext reload\n");
		return sb.toString();
	}
	public static String secondText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��Ʒ����vv��xx����������һ�����з��ĳ�ˮ�ã��ʺϴ����ũ��Ʒ��ȵ���ҵ������\n");
		return sb.toString();
	}

}
