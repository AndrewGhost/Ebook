package Service;

import com.opensymphony.xwork2.ActionSupport;
public class zipAction extends ActionSupport{
	 
	 /**
	 * �����ϴ��ļ�����õ�����Ŀ¼��Ϣ������·����
	 * @author Guoxin
	 * @latest 2016/1/3
	 */
	private static final long serialVersionUID = 1L;
	private String zipPath;
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public String getZipPath() {
		return zipPath;
	}
	public String execute() throws Exception{
		
		
		 String parent=zipPath.substring(0,zipPath.lastIndexOf("."));//��ѹ�ļ��Ĵ��·��
		 DoZip.unZipFiles(zipPath,parent);//��ѹ�ļ�	 
		 return SUCCESS;
	}
}
