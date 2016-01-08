package DAO;
import java.util.LinkedHashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
public class storeDataAction extends ActionSupport {
	/**
	 * 
	 *  ʵ�ֽ��ϴ�����������������ߣ��ļ�������Ϣ
	 *  �������ݿ�
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
		
		 String parent=zipPath.substring(0,zipPath.lastIndexOf("."));//��ѹ�ļ����·��
		 StringBuffer contentFilePath=new StringBuffer();//Ŀ¼�ļ�·����һ��Ϊ.ncx
		 String chapterPath;
		 Map map=new LinkedHashMap();
		 Book book=new Book();	
		 Content.locateFile(parent, ".ncx", 0,contentFilePath);//����Ŀ¼�ļ�·��
		 chapterPath=parent.substring(parent.lastIndexOf("\\")+1);
	     map=Content.getContent(contentFilePath.toString(),1);//��ȡ��������
	     
	     book.setBookname(map.get("bookname").toString());
	     book.setAuthor(map.get("author").toString());
	     book.setChapterPath(contentFilePath.substring(contentFilePath.indexOf(chapterPath),contentFilePath.lastIndexOf("\\")));
	     book.setBookFile(chapterPath);
	     HibernateUtils.add(book);
	        	   
		 return SUCCESS;
	}
	
}
