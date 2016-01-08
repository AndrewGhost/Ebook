package Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

	public class uploadFile extends ActionSupport{
		
		/**
		 * 实现文件上传至服务器本地，后面再调用zipAction处理上传文件
		 * @latest 2016/1/3
		 * @author Guoxin
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static Integer id=1;
		private File file;
		private String fileFileName="test";
	    private String fileContentType;
	    private String zipPath;
	    public void setFile(File file) {
			this.file = file;
		}


		public File getFile() {
			return file;
		}
		public void setFileFileName(String fileFileName) {
			this.fileFileName = fileFileName;
		}


		public String getFileFileName() {
			return fileFileName;
		}


		public void setFileContentType(String fileContentType) {
			this.fileContentType = fileContentType;
		}


		public String getFileContentType() {
			return fileContentType;
		}

		public void setZipPath(String zipPath) {
			this.zipPath = zipPath;
		}


		public String getZipPath() {
			return zipPath;
		}


		public String execute() throws Exception { // latest revised at 2016/1/2 
			
			String root = ServletActionContext.getServletContext().getRealPath("/upload");
			InputStream is = new FileInputStream(file);
			Date date=new Date();
			SimpleDateFormat dtStr=new SimpleDateFormat("yyyyMMdd");
			String newfilename=dtStr.format(date)+"_"+id.toString();//给解压文件命名
			newfilename+=".zip";//将epub转为zip格式存在本地
			id++;
			String sendedPath=root+"\\"+newfilename;
			//zipPath作为电子书上传后存放的文件路径参数传递给zipAction
			setZipPath(sendedPath);
			OutputStream os = new FileOutputStream(new File(root, newfilename));
			System.out.println(fileFileName+"上传成功");
	        byte[] buffer = new byte[1024];
	        int length = 0;	        
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        os.close();
	        is.close();
			return SUCCESS;		
			
		}


}
