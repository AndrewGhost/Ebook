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
		 * ʵ���ļ��ϴ������������أ������ٵ���zipAction�����ϴ��ļ�
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
			String newfilename=dtStr.format(date)+"_"+id.toString();//����ѹ�ļ�����
			newfilename+=".zip";//��epubתΪzip��ʽ���ڱ���
			id++;
			String sendedPath=root+"\\"+newfilename;
			//zipPath��Ϊ�������ϴ����ŵ��ļ�·���������ݸ�zipAction
			setZipPath(sendedPath);
			OutputStream os = new FileOutputStream(new File(root, newfilename));
			System.out.println(fileFileName+"�ϴ��ɹ�");
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
