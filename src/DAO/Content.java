package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Content {
	
	/** ����getContent:
	 *  ��ȡepub��ʽ������Ŀ¼����Ҫ�������������.ncx�ļ�
	 *  @param  filePath,.ncx�ļ�·��
	 *  @param  flag=1,��ʾ��ѯ���ߺ�������flag=0,��ʾ��ѯ�½ں�����
	 *  @author Guoxin
	 *  @latest 2016/1/3
	 * */
	
	@SuppressWarnings("unchecked")
	public static Map getContent(String filePath,int flag) throws UnsupportedEncodingException, FileNotFoundException{
		  //�½ڵ��½����ݵ�ӳ�䣬�������ߣ�������ӳ��
		  Map map= new LinkedHashMap();
		   // ����saxReader����  		
	      SAXReader reader = new SAXReader();
	    
	     // ͨ��read������ȡһ���ļ� ת����Document����  
	      try {
	    	//File file=new File(filePath);
	       // FileInputStream fis=new FileInputStream(file);
	        
	         Document document = reader.read(filePath);//new BufferedReader(new InputStreamReader(fis, "UTF-8")));// ��ȡXML�ļ�	
			 //��ȡ���ڵ�Ԫ�ض���  
	       
	        Element node = document.getRootElement();
	        
	        map.clear(); 
	        if(flag==1){//��������ֺ�����
	        	
	        	Element titleNode=node.element("docTitle").element("text");
		        String  bookName=titleNode.getText();
		        map.put("bookname", bookName);
		        System.out.println("����:"+bookName);     
		        Element authorNode=node.element("docAuthor").element("text");
		        String  author=authorNode.getText();
		        map.put("author", author);
		        System.out.println("����:"+author);       
		        System.out.println("----------------------------------------");      
	        }
	        else{
	                  	
	        	Element navMapNode=node.element("navMap");
		        List  nodes=navMapNode.elements("navPoint");
		        for(Iterator it=nodes.iterator();it.hasNext();){	        	
		        	 Element elm = (Element) it.next();
		        	 Element contentNode=elm.element("content");
		        	 Attribute attrId=contentNode.attribute("src");
		        	 String textId=attrId.getText();
		        	 Attribute attrPlayOrder=elm.attribute("playOrder");
		        	 @SuppressWarnings("unused")
					 String textPlayOrder=attrPlayOrder.getText();
		        	 Element navLabelNode=elm.element("navLabel").element("text");
		        	 String  textChapter=navLabelNode.getText();
		        	 map.put(textChapter, textId);
		        	
		        }
	        }//else
	        	               	
	       }//try
	      catch (DocumentException e) {	
				e.printStackTrace();
			}
	       //map=sortByValue(map);
	       return map;
		} 
	 
	
	/** ����locateFile:
	 *  ʵ���ڽ�ѹ��ĵ������ļ����У�����ĳ���ļ����ļ���
	 *  flag=1,�����ļ������ң�flag=0�������ļ���չ������
	 *  @param  bookPath,��������·��
	 *  @param  fileName,��Ҫ���ҵ��ļ���
	 *  @param  flag
	 *  @param  result   ���ҽ��
	 *  @author Guoxin
	 *  @latest 2016/1/3
	 * */
	public static void locateFile(String bookPath,String fileName,int flag,StringBuffer sb){
		   
	       File   root=new File(bookPath);
		   File []files=root.listFiles();
		   for(int i=0;i<files.length;i++){
			   if(flag==1)
			   {
				   if(files[i].getName().equals(fileName))
				   {   					
					   sb.append(files[i].getAbsolutePath());
					   
				   }
			   }
			   if(flag==0)
			   {
				   if(files[i].getName().endsWith(fileName))					   
				   {					  
					   sb.append(files[i].getAbsolutePath());		   
				   }
				  
			   }
			   if(files[i].isDirectory()){
				     locateFile(files[i].getAbsolutePath(),fileName,flag,sb);			 
			   }			   
		   }//for
		  
	}//static
}
