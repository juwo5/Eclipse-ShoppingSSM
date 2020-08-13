package com.ju.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.omg.CORBA.portable.OutputStream;

public class FileItem {
	public static FileItem createFileItem(File file,String fileName){
		FileItemFactory factory=new DiskFileItemFactory(16,null);
		FileItem item=factory.createItem(fileName,"text/plain", true, file.getName());
		int bytesRead=0;
		byte[] buffer=new byte[8192];
		try {
			FileInputStream fis = new FileInputStream(file);
			OutputStream os=item.getOutputStream();
			while ((bytesRead=fis.read(0,8192))!=-1) {
				os.write(buffer,0,bytesRead);
			}
			os.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
