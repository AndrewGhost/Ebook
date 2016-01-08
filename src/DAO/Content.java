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
	
	/** 方法getContent:
	 *  获取epub格式电子书目录，主要依靠电子书里的.ncx文件
	 *  @param  filePath,.ncx文件路径
	 *  @param  flag=1,表示查询作者和书名，flag=0,表示查询章节和内容
	 *  @author Guoxin
	 *  @latest 2016/1/3
	 * */
	
	@SuppressWarnings("unchecked")
	public static Map getContent(String filePath,int flag) throws UnsupportedEncodingException, FileNotFoundException{
		  //章节到章节内容的映射，或者作者，书名的映射
		  Map map= new LinkedHashMap();
		   // 创建saxReader对象  		
	      SAXReader reader = new SAXReader();
	    
	     // 通过read方法读取一个文件 转换成Document对象  
	      try {
	    	//File file=new File(filePath);
	       // FileInputStream fis=new FileInputStream(file);
	        
	         Document document = reader.read(filePath);//new BufferedReader(new InputStreamReader(fis, "UTF-8")));// 读取XML文件	
			 //获取根节点元素对象  
	       
	        Element node = document.getRootElement();
	        
	        map.clear(); 
	        if(flag==1){//读书的名字和作者
	        	
	        	Element titleNode=node.element("docTitle").element("text");
		        String  bookName=titleNode.getText();
		        map.put("bookname", bookName);
		        System.out.println("书名:"+bookName);     
		        Element authorNode=node.element("docAuthor").element("text");
		        String  author=authorNode.getText();
		        map.put("author", author);
		        System.out.println("作家:"+author);       
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
	 
	
	/** 方法locateFile:
	 *  实现在解压后的电子书文件夹中，查找某个文件或文件夹
	 *  flag=1,根据文件名查找；flag=0，根据文件扩展名查找
	 *  @param  bookPath,电子书存放路径
	 *  @param  fileName,需要查找的文件名
	 *  @param  flag
	 *  @param  result   查找结果
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
