package DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryBook extends ActionSupport{

	/**
	 *  ��ѯһ���������Ŀ¼��Ϣ
	 */
	private static final long serialVersionUID = 1L;
	private String fileIndex;//��������ļ�������������20160104_1,��ʾ2016��1��4���ϴ��ĵ�һ����
	private String chapterPath;//�����½������ڵ������е����·��
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
		StringBuffer contentFilePath=new StringBuffer();//Ŀ¼�ļ�·����һ��Ϊ.ncx
		Map map=new HashMap();
	    Content.locateFile(bookPath, ".ncx", 0,contentFilePath);//����Ŀ¼�ļ�·��
	 	
	    map=Content.getContent(contentFilePath.toString(),0);//��ȡ��������
	    ActionContext.getContext().getValueStack().set("map", map);
	    return SUCCESS;
		
	}

}
