package DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryBook extends ActionSupport{

	/**
	 *  查询一本书的所有目录信息
	 */
	private static final long serialVersionUID = 1L;
	private String fileIndex;//电子书的文件名索引，例如20160104_1,表示2016年1月4日上传的第一本书
	private String chapterPath;//具体章节内容在电子书中的相对路径
	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}
	public String getFileIndex() {
		return fileIndex;
	}
	public void setChapterPath(String chapterPath) {
		this.chapterPath = chapterPath;
	}
	public String getChapterPath() {
		return chapterPath;
	}
	@SuppressWarnings("unchecked")
	public String execute()throws Exception{
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		String bookPath=root+"/"+fileIndex;
		StringBuffer contentFilePath=new StringBuffer();//目录文件路径，一般为.ncx
		Map map=new HashMap();
	    Content.locateFile(bookPath, ".ncx", 0,contentFilePath);//查找目录文件路径
	 	
	    map=Content.getContent(contentFilePath.toString(),0);//获取书名内容
	    ActionContext.getContext().getValueStack().set("map", map);
	    return SUCCESS;
		
	}

}
