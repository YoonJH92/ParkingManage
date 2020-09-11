package com.pms.command;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class TestCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String path = request.getSession().getServletContext().getRealPath("fileUpload");
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		

		System.out.println("경로 : " + path +"<br/>");
		
	    String subject ="";
		int maxSize =1024 *1024 *10;
		
		MultipartRequest multi =new MultipartRequest(request,path,maxSize,"utf-8",new DefaultFileRenamePolicy());
        
        String name = multi.getFilesystemName("fileName");


        String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
  
        File oldFile = new File(path + name);
        File newFile = new File(path + now);
       
        oldFile.renameTo(newFile); // 파일명 변경

		
        //File f = multi.getFile("fileName");
        
        return null;
		//return "stat/test";
	}
	
}
