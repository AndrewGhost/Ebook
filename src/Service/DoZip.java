package Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
public class DoZip {
	 
	 /**
	  * ��ѹ�����鵽ָ��Ŀ¼
	  * @param zipPath
	  * @author Guoxin
	   */
	public static void unZipFiles(String zipPath,String Parent)throws IOException{
		try {
			File file=new File(zipPath);
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(zipPath));//����Դzip·��
			BufferedInputStream Bin=new BufferedInputStream(Zin);
		    Parent=zipPath.substring(0,zipPath.lastIndexOf(".")); //���·�����ļ���Ŀ¼��
			File Fout=null;
			ZipEntry entry;
			try {
				while((entry = Zin.getNextEntry())!=null){
				    if(entry.isDirectory())continue;  
					Fout=new File(Parent,entry.getName());
					if(!Fout.exists()){
						(new File(Fout.getParent())).mkdirs();
					}
					FileOutputStream out=new FileOutputStream(Fout);
					BufferedOutputStream Bout=new BufferedOutputStream(out);
					int b;
					while((b=Bin.read())!=-1){
						Bout.write(b);
					}
					Bout.close();
					out.close();
					//System.out.println(Fout);	
				}
				Bin.close();
				Zin.close();
			    file.delete();
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------��ѹ���------------------");
	}// public static
}//
