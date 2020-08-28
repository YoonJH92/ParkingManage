package com.pms.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dao.SettingDAO;
import com.pms.dto.SettingDTO;
import com.pms.dto.memberManageDTO;

public class MemberInsertCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Calendar cal = Calendar.getInstance(); // stopDate 사용하기 위해서 만듬
		String startDate = request.getParameter("startDate"); // startDate 파라미터값 저장
 		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date toDate = format.parse(startDate); // date 형식으로 변환 
		cal.setTime(toDate); 
		cal.add(Calendar.MONTH, 1);	 
		String stopDate = format.format(cal.getTime()); // String 타입으로 1달 후 데이터 저장
		
		// 설정에서 월정액 요금 가져오기 위해서 사용
		SettingDAO setDao = SettingDAO.getInstance();
		SettingDTO set = setDao.settItem();
		
		// 파라미터 저장
		memberManageDTO mem = new memberManageDTO();
		mem.setEmail(request.getParameter("email"));
		mem.setCNUM(request.getParameter("CNUM"));
		mem.setName(request.getParameter("name"));
		mem.setPay(set.getMonth_fare());
		mem.setPhone(request.getParameter("phone"));
		mem.setStartDate(startDate);
		mem.setStopDate(stopDate);
		mem.setType(request.getParameter("type"));

		MemberManageDAO dao = MemberManageDAO.getInstance();
		dao.insertMember(mem);
		
		return "redirect:member.do";
	}

}