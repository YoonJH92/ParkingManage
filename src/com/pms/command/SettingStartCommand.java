package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.SettingDAO;
import com.pms.dto.SettingDTO;

public class SettingStartCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		SettingDTO sdto = new SettingDTO();
		SettingDAO sdao = new SettingDAO();
		
		
		
		sdto = sdao.settItem();
		
		request.setAttribute("sdto", sdto);
		
		
		return "login/setting";
	}

}
