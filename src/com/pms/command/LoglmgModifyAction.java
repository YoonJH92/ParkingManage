package com.pms.command;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.http.Part;
import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.sun.glass.ui.Application;
public class LoglmgModifyAction implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {         
            	PmsLogDao dao=PmsLogDao.getInstance();     	
            	ArrayList<String>url=dao.imgUpdate(request);
        	
		return  "redirect:loglist.do?"+url.get(0);
	}
}


	
