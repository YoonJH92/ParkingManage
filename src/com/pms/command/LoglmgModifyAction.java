package com.pms.command;
import java.io.File;
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
            	dao.imgUpdate(request);           
        
		return  "redirect:loglist.do";
	}
}


	
