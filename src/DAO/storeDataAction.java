package DAO;
import java.util.LinkedHashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
public class storeDataAction extends ActionSupport {
	/**
	 * 
	 *  实现将上传电子书的书名，作者，文件名等信息
	 *  存入数据库
	 *  @author Guoxin
	 *  @latest 2016/1/4
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	private String zipPath;
	private boolean isUploaded;

	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}

	public String getZipPath() {
		return zipPath;
	}
	public void setUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

	public boolean isUploaded() {
		return isUploaded;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		
		 String parent=zipPath.substring(0,zipPath.lastIndexOf("."));//解压文件存放路径
		 StringBuffer contentFilePath=new StringBuffer();//目录文件路径，一般为.ncx
		 String chapterPath;
		 Map map=new LinkedHashMap();
		 Book book=new Book();	
		 Content.locateFile(parent, ".ncx", 0,contentFilePath);//查找目录文件路径
		 chapterPath=parent.substring(parent.lastIndexOf("\\")+1);
	     map=Content.getContent(contentFilePath.toString(),1);//获取书名内容
	     
	     book.setBookname(map.get("bookname").toString());
	     book.setAuthor(map.get("author").toString());
	     book.setChapterPath(contentFilePath.substring(contentFilePath.indexOf(chapterPath),contentFilePath.lastIndexOf("\\")));
	     book.setBookFile(chapterPath);
	     HibernateUtils.add(book);
	        	   
		 return SUCCESS;
	}
	
}
