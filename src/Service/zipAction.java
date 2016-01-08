package Service;

import com.opensymphony.xwork2.ActionSupport;
public class zipAction extends ActionSupport{
	 
	 /**
	 * 处理上传文件，获得电子书目录信息及其存放路径等
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
		
		
		 String parent=zipPath.substring(0,zipPath.lastIndexOf("."));//解压文件的存放路径
		 DoZip.unZipFiles(zipPath,parent);//解压文件	 
		 return SUCCESS;
	}
}
